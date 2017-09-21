package com.imd.friendsManagement.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.imd.friendsManagement.entity.Friend;
import com.imd.friendsManagement.entity.People;
import com.imd.friendsManagement.service.FriendService;
import com.imd.friendsManagement.service.PeopleService;
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
public class FriendController {

	private final Logger LOGGER = LoggerFactory.getLogger(FriendController.class);

	private static final String PARAM_FRIENDS = "friends";
	private static final String PARAM_EMAIL = "email";
	private static final String PARAM_COUNT = "count";

	@Autowired
	private FriendService service;

	@Autowired
	private PeopleService peopleService;

	@RequestMapping(value = Constants.SUB_PATH.ADD_FRIEND, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> registerFriend(@RequestBody String parameter) {
		LOGGER.info("### rcv : " + parameter);

		String result;
		Map<String, Object> param = new HashMap<String, Object>();
		try {
			if (parameter == null) {
				throw new BusinessException(Constants.ERROR_CODE.RC002);
			} else {
				JSONObject object = new JSONObject(parameter);
				JSONArray msgFriends = (JSONArray) object.get(PARAM_FRIENDS);

				Friend f = new Friend();
				f.setEmailPeople((String) msgFriends.get(0));
				f.setEmailFriend((String) msgFriends.get(1));

				People emailPeople = new People();
				People emailFriend = new People();

				if (f.getEmailPeople() == null || f.getEmailFriend() == null) {
					throw new BusinessException(Constants.ERROR_CODE.FRIEND.RCF001);
				} else if (f.getEmailPeople() != null) {
					emailPeople = peopleService.findByEmailAddress(f.getEmailPeople());
					emailFriend = peopleService.findByEmailAddress(f.getEmailFriend());

					if (emailPeople == null || emailFriend == null) {
						throw new BusinessException(Constants.ERROR_CODE.FRIEND.RCF002);
					}

					Friend friend = service.findByEmailPeopleAndEmailFriend(emailPeople.getEmailAddress(),
							emailFriend.getEmailAddress());
					if (friend != null) {
						throw new BusinessException(Constants.ERROR_CODE.FRIEND.RCF003);
					}
				}

				saveData(emailPeople, emailFriend);

				param.put(Constants.UTILS.SUCCESS, true);
				result = PojoJsonMapper.toJson(param);
				LOGGER.info("### snd : " + result);
				return new ResponseEntity<>(result, HttpStatus.OK);
			}
		} catch (BusinessException e) {
			result = EngineUtils.getMessageBusinessException(e);
			LOGGER.info("### snd : " + result);
			throw new RuntimeException(result);
		} catch (Exception e) {
			result = EngineUtils.getMessageException(e);
			LOGGER.info("### snd : " + result);
			e.printStackTrace();
			throw new RuntimeException(result);
		}
	}

	@RequestMapping(value = Constants.SUB_PATH.FIND_ALL_FRIEND, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> findAllFriend(@RequestBody String parameter) {
		LOGGER.info("### rcv : " + parameter);

		String result;
		Map<String, Object> param = new HashMap<String, Object>();
		try {
			if (parameter == null) {
				throw new BusinessException(Constants.ERROR_CODE.RC002);
			} else {
				JSONObject object = new JSONObject(parameter);
				String email = (String) object.get(PARAM_EMAIL);
				List<Friend> listFriend = service.findByEmailPeople(email);
				if (listFriend.isEmpty()) {
					throw new BusinessException(Constants.ERROR_CODE.FRIEND.RCF004);
				} else {
					List<String> listString = new ArrayList<>();
					for (int i = 0; i < listFriend.size(); i++) {
						listString.add(listFriend.get(i).getEmailFriend());
					}
					
					param.put(Constants.UTILS.SUCCESS, true);
					param.put(PARAM_FRIENDS, listString);
					param.put(PARAM_COUNT, listString.size());

					result = PojoJsonMapper.toJson(param);
					LOGGER.info("### snd : " + result);
					return new ResponseEntity<>(result, HttpStatus.OK);
				}
			}
		} catch (BusinessException e) {
			result = EngineUtils.getMessageBusinessException(e);
			LOGGER.info("### snd : " + result);
			throw new RuntimeException(result);
		} catch (Exception e) {
			result = EngineUtils.getMessageException(e);
			LOGGER.info("### snd : " + result);
			e.printStackTrace();
			throw new RuntimeException(result);
		}
	}

	@RequestMapping(value = Constants.SUB_PATH.FIND_F_BETWEEN_TWO_EMAIL, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<String> findAllFriendBetweenTwoEmail(@RequestBody String parameter) {
		LOGGER.info("### rcv : " + parameter);

		String result;
		Map<String, Object> param = new HashMap<String, Object>();
		try {
			if (parameter == null) {
				throw new BusinessException(Constants.ERROR_CODE.RC002);
			} else {
				JSONObject object = new JSONObject(parameter);
				JSONArray paramFriend = (JSONArray) object.get(PARAM_FRIENDS);

				String firstEmail = (String) paramFriend.get(0);
				String secondEmail = (String) paramFriend.get(1);

				List<Friend> listFirst = service.findByEmailPeople(firstEmail);
				List<Friend> listSecond = service.findByEmailPeople(secondEmail);

				if (listFirst.isEmpty() && listSecond.isEmpty()) {
					throw new BusinessException(Constants.ERROR_CODE.FRIEND.RCF004);
				} else {
					List<String> listString = new ArrayList<String>();
					listString = getCommonEmail(listFirst, listSecond);

					param.put(Constants.UTILS.SUCCESS, true);
					param.put(PARAM_FRIENDS, listString);
					param.put(PARAM_COUNT, listString.size());

					result = PojoJsonMapper.toJson(param);
					LOGGER.info("### snd : " + result);
					return new ResponseEntity<>(result, HttpStatus.OK);
				}
			}
		} catch (BusinessException e) {
			result = EngineUtils.getMessageBusinessException(e);
			LOGGER.info("### snd : " + result);
			throw new RuntimeException(result);
		} catch (Exception e) {
			result = EngineUtils.getMessageException(e);
			LOGGER.info("### snd : " + result);
			e.printStackTrace();
			throw new RuntimeException(result);
		}
	}

	private void saveData(People emailPeople, People emailFriend) {
		Friend fr = new Friend();
		fr.setEmailPeople(emailPeople.getEmailAddress());
		fr.setEmailFriend(emailFriend.getEmailAddress());
		fr.setStatus(Constants.STATUS.FRIEND);
		fr.setCreated(new Date());
		fr.setUpdated(new Date());
		service.save(fr);
	}

	private List<String> getCommonEmail(List<Friend> listFirst, List<Friend> listSecond) {
		List<String> listResult = new ArrayList<String>();
		for (int i = 0; i < listFirst.size(); i++) {
			for (int j = 0; j < listSecond.size(); j++) {
				if (listFirst.get(i).getEmailFriend().equals(listSecond.get(j).getEmailFriend())) {
					listResult.add(listFirst.get(i).getEmailFriend());
					listSecond.remove(j);
					j--;
				}
			}
		}
		return listResult;
	}

}
