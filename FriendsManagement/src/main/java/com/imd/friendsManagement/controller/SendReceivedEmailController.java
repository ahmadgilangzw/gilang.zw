package com.imd.friendsManagement.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import com.imd.friendsManagement.entity.SendReceivedEmail;
import com.imd.friendsManagement.entity.Subscribe;
import com.imd.friendsManagement.service.FriendService;
import com.imd.friendsManagement.service.PeopleService;
import com.imd.friendsManagement.service.SendReceivedEmailService;
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
public class SendReceivedEmailController {

	private final Logger LOGGER = LoggerFactory.getLogger(SendReceivedEmailController.class);

	private static final String PARAM_SENDER = "sender";
	private static final String PARAM_TEXT = "text";
	private static final String PARAM_RECIPIENTS = "recipients";

	@Autowired
	private PeopleService peopleService;

	@Autowired
	private SubscribeService subcribeService;

	@Autowired
	private FriendService friendService;

	@Autowired
	private SendReceivedEmailService sendReceivedEmailService;

	@RequestMapping(value = Constants.SUB_PATH.SEND_EMAIL, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addSubscribe(@RequestBody String parameter) {
		LOGGER.info("### rcv : " + parameter);

		String result;
		Map<String, Object> param = new HashMap<String, Object>();
		try {
			if (parameter == null) {
				throw new BusinessException(Constants.ERROR_CODE.RC002);
			} else {
				JSONObject object = new JSONObject(parameter);
				String sender = (String) object.get(PARAM_SENDER);
				String text = (String) object.get(PARAM_TEXT);

				if (getPeopleData(sender) == null) {
					throw new BusinessException(Constants.ERROR_CODE.FRIEND.RCF002);
				} else {
					String recipients = "";
					List<String> listRecipients = new ArrayList<String>();

					// get List Friend
					List<Friend> listFriend = friendService.findByEmailPeople(sender);
					listFriend = getJustFriend(listFriend);

					// get List Subscribe
					List<Subscribe> listSubscribe = subcribeService.findByTarget(sender);
					listSubscribe = getJusSubscribe(listSubscribe);
					
					List<String> listMentioned = getEmailMentioned(text);

					// get List Recipients
					listRecipients = getRecipientsList(listFriend, listSubscribe, listMentioned);

					saveData(sender, text, recipients);
					param.put(Constants.UTILS.SUCCESS, true);
					param.put(PARAM_RECIPIENTS, listRecipients);
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

	private People getPeopleData(String email) {
		return peopleService.findByEmailAddress(email);
	}

	private void saveData(String sender, String text, String recipients) {
		SendReceivedEmail s = new SendReceivedEmail();
		s.setSender(sender);
		s.setText(text);
		s.setRecipients(recipients);
		s.setStatus(Constants.STATUS.SUBSCRIBE);
		s.setCreated(new Date());
		sendReceivedEmailService.save(s);
	}

	private List<Friend> getJustFriend(List<Friend> list) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getStatus().equals(Constants.STATUS.BLOKED)) {
				list.remove(i);
				i--;
			}
		}
		return list;
	}

	private List<Subscribe> getJusSubscribe(List<Subscribe> listSubscribe) {
		for (int i = 0; i < listSubscribe.size(); i++) {
			if (listSubscribe.get(i).getStatus().equals(Constants.STATUS.UNSUBSCRIBE)) {
				listSubscribe.remove(i);
				i--;
			}
		}
		return listSubscribe;
	}

	private List<String> getRecipientsList(List<Friend> listFriend, List<Subscribe> listSubscribe, List<String> listMentioned) {
		List<String> listString = new ArrayList<String>();
		for (int i = 0; i < listFriend.size(); i++) {
			listString.add(listFriend.get(i).getEmailFriend());
		}

		for (int i = 0; i < listSubscribe.size(); i++) {
			if(!listString.contains(listSubscribe.get(i).getRequestor())){
				listString.add(listSubscribe.get(i).getRequestor());
			}
		}
		
		for (int i = 0; i < listMentioned.size(); i++) {
			if (!listString.contains(listMentioned.get(i))) {
				listString.add(listMentioned.get(i));
			}
		}

		return listString;
	}
	
	private List<String> getEmailMentioned(String text){
		 List<String> listString = new ArrayList<String>();
		 Matcher m = Pattern.compile("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+").matcher(text);
		 while (m.find()) {
			 listString.add(m.group());
		 }
		 return listString;
	}
}
