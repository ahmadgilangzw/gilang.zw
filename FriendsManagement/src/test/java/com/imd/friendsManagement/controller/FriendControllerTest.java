package com.imd.friendsManagement.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.imd.friendsManagement.utility.Constants;
import com.imd.friendsManagement.utility.PojoJsonMapper;

/**
 * 
 * @author Gilang ZW
 *
 */
@SuppressWarnings("deprecation")
@RunWith(SpringRunner.class)
public class FriendControllerTest {
	
	private final Logger LOGGER = LoggerFactory.getLogger(FriendControllerTest.class);

	private RestTemplate restTemplate = new TestRestTemplate();

	@SuppressWarnings("rawtypes")
	@Test
	public void addFriend() throws URISyntaxException, JsonProcessingException {

		LOGGER.info("### start test addFriend() ");
		
		List<String> listString = new ArrayList<String>();
		listString.add("andy@example.com");
		listString.add("john@example.com");
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("friends", listString);
		String pInput = PojoJsonMapper.toJson(param);
		
		LOGGER.info("### snd : "+pInput);
		
		RequestEntity request = RequestEntity
				.post(new URI(Constants.BASE_URI + Constants.HEADER_PATH + Constants.SUB_PATH.ADD_FRIEND))
				.accept(MediaType.APPLICATION_JSON).body(pInput);
		ResponseEntity<String> response = restTemplate.exchange(request, String.class);

		LOGGER.info("### rsp : "+response.getStatusCode());
		
		if(response.getStatusCode().equals(HttpStatus.OK)){
			//get status code 200(SUKSES) if two email not to be friend yet
			Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
		}else{
			//get status code 500(INTERNAL SERVER ERROR) if two email already to be friend
			Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		}
		
		LOGGER.info("### finish test addFriend() ");
	}
	
	@SuppressWarnings("rawtypes")
	@Test
	public void findAllFriend() throws URISyntaxException, JsonProcessingException {

		LOGGER.info("### start test findAllFriend() ");
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("email", "andy@example.com");
		String pInput = PojoJsonMapper.toJson(param);
		
		LOGGER.info("### snd : "+pInput);
		
		RequestEntity request = RequestEntity
				.post(new URI(Constants.BASE_URI + Constants.HEADER_PATH + Constants.SUB_PATH.FIND_ALL_FRIEND))
				.accept(MediaType.APPLICATION_JSON).body(pInput);
		ResponseEntity<String> response = restTemplate.exchange(request, String.class);

		LOGGER.info("### rsp : "+response.getStatusCode());
		
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
		
		LOGGER.info("### finish test findAllFriend() ");
	}
	
	@SuppressWarnings("rawtypes")
	@Test
	public void findFBetweenTwoEmail() throws URISyntaxException, JsonProcessingException {

		LOGGER.info("### start test findFBetweenTwoEmail() ");
		
		List<String> listString = new ArrayList<String>();
		listString.add("andy@example.com");
		listString.add("john@example.com");
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("friends", listString);
		String pInput = PojoJsonMapper.toJson(param);
		
		LOGGER.info("### snd : "+pInput);
		
		RequestEntity request = RequestEntity
				.post(new URI(Constants.BASE_URI + Constants.HEADER_PATH + Constants.SUB_PATH.FIND_F_BETWEEN_TWO_EMAIL))
				.accept(MediaType.APPLICATION_JSON).body(pInput);
		ResponseEntity<String> response = restTemplate.exchange(request, String.class);

		LOGGER.info("### rsp : "+response.getStatusCode());
		
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
		
		LOGGER.info("### finish test findFBetweenTwoEmail() ");
	}

}
