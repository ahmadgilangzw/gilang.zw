package com.imd.friendsManagement.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.imd.friendsManagement.entity.Friend;
import com.imd.friendsManagement.entity.People;
import com.imd.friendsManagement.entity.Subscribe;
import com.imd.friendsManagement.service.FriendService;
import com.imd.friendsManagement.service.PeopleService;
import com.imd.friendsManagement.service.SubscribeService;
import com.imd.friendsManagement.utility.BusinessException;
import com.imd.friendsManagement.utility.Constants;
import com.imd.friendsManagement.utility.EngineUtils;
import com.imd.friendsManagement.utility.PojoJsonMapper;

/**
 * 
 * @author Gilang ZW
 *
 */
@RestController
@RequestMapping(value = Constants.HEADER_PATH)
public class SubscribeController {

	private final Logger LOGGER = LoggerFactory.getLogger(SubscribeController.class);

	private static final String PARAM_REQUESTOR = "requestor";
	private static final String PARAM_TARGET = "target";

	@Autowired
	private PeopleService peopleService;

	@Autowired
	private SubscribeService subcribeService;

	@Autowired
	private FriendService friendService;

	@RequestMapping(value = Constants.SUB_PATH.ADD_SUBSCRIBE, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public String addSubscribe(@RequestBody String parameter) {
		LOGGER.info("### rcv : " + parameter);

		String result;
		Map<String, Object> param = new HashMap<String, Object>();
		try {
			if (parameter == null) {
				throw new BusinessException(Constants.ERROR_CODE.RC002);
			} else {
				JSONObject object = new JSONObject(parameter);
				String requestor = (String) object.get(PARAM_REQUESTOR);
				String target = (String) object.get(PARAM_TARGET);

				if (getPeopleData(requestor) == null || getPeopleData(target) == null) {
					throw new BusinessException(Constants.ERROR_CODE.FRIEND.RCF002);
				} else {

					Subscribe subcribe = subcribeService.findByRequestorAndTarget(requestor, target);
					if (subcribe != null) {
						if (subcribe.getStatus().equals(Constants.STATUS.SUBSCRIBE)) {
							throw new BusinessException(Constants.ERROR_CODE.SUBCRIBE.RCS001);
						}
					}

					saveData(requestor, target);

					param.put(Constants.UTILS.SUCCESS, true);
					result = PojoJsonMapper.toJson(param);
					LOGGER.info("### snd : " + result);
					return result;
				}
			}
		} catch (BusinessException e) {
			result = EngineUtils.getMessageBusinessException(e);
			LOGGER.info("### snd : " + result);
			return result;
		} catch (Exception e) {
			result = EngineUtils.getMessageException(e);
			LOGGER.info("### snd : " + result);
			e.printStackTrace();
			return result;
		}
	}

	@RequestMapping(value = Constants.SUB_PATH.BLOCK_PEOPLE, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public String blockPeople(@RequestBody String parameter) {
		LOGGER.info("### rcv : " + parameter);

		String result;
		Map<String, Object> param = new HashMap<String, Object>();
		try {
			if (parameter == null) {
				throw new BusinessException(Constants.ERROR_CODE.RC002);
			} else {
				JSONObject object = new JSONObject(parameter);
				String requestor = (String) object.get(PARAM_REQUESTOR);
				String target = (String) object.get(PARAM_TARGET);

				if (getPeopleData(requestor) == null || getPeopleData(target) == null) {
					throw new BusinessException(Constants.ERROR_CODE.FRIEND.RCF002);
				} else {
					Friend friend = friendService.findByEmailPeopleAndEmailFriend(requestor, target);
					Subscribe subcribe = subcribeService.findByRequestorAndTarget(requestor, target);
					if (friend != null) {
						if (friend.getStatus().equals(Constants.STATUS.BLOKED)) {
							throw new BusinessException(Constants.ERROR_CODE.SUBCRIBE.RCS002);
						}
						if (subcribe != null) {
							if (subcribe.getStatus().equals(Constants.STATUS.UNSUBSCRIBE)) {
								throw new BusinessException(Constants.ERROR_CODE.SUBCRIBE.RCS003);
							}
							subcribe.setStatus(Constants.STATUS.UNSUBSCRIBE);
							subcribeService.save(subcribe);
						} else {
							Subscribe s = new Subscribe();
							s.setRequestor(requestor);
							s.setTarget(target);
							s.setStatus(Constants.STATUS.UNSUBSCRIBE);
							s.setCreated(new Date());
							s.setUpdated(new Date());
							subcribeService.save(s);
						}
					} else {
						Friend f = new Friend();
						f.setEmailPeople(requestor);
						f.setEmailFriend(target);
						f.setStatus(Constants.STATUS.BLOKED);
						f.setCreated(new Date());
						f.setUpdated(new Date());
						friendService.save(f);

						if (subcribe != null) {
							subcribeService.delete(subcribe.getId());
						}
					}

					param.put(Constants.UTILS.SUCCESS, true);
					result = PojoJsonMapper.toJson(param);
					LOGGER.info("### snd : " + result);
					return result;
				}
			}
		} catch (BusinessException e) {
			result = EngineUtils.getMessageBusinessException(e);
			LOGGER.info("### snd : " + result);
			return result;
		} catch (Exception e) {
			result = EngineUtils.getMessageException(e);
			LOGGER.info("### snd : " + result);
			e.printStackTrace();
			return result;
		}
	}

	public People getPeopleData(String email) {
		return peopleService.findByEmailAddress(email);
	}

	public void saveData(String requestor, String target) {
		Subscribe s = new Subscribe();
		s.setRequestor(requestor);
		s.setTarget(target);
		s.setStatus(Constants.STATUS.SUBSCRIBE);
		s.setCreated(new Date());
		s.setUpdated(new Date());
		subcribeService.save(s);
	}
}
