package com.imd.friendsManagement.controller;

import java.net.URI;
import java.util.HashMap;
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

import com.imd.friendsManagement.utility.Constants;
import com.imd.friendsManagement.utility.PojoJsonMapper;

/**
 * 
 * @author Gilang ZW
 *
 */
@SuppressWarnings("deprecation")
@RunWith(SpringRunner.class)
public class SubscribeControllerTest {
	
	private final Logger LOGGER = LoggerFactory.getLogger(SubscribeControllerTest.class);

	private RestTemplate restTemplate = new TestRestTemplate();

	@SuppressWarnings("rawtypes")
	@Test
	public void addSubscribe() throws Exception {

		LOGGER.info("### start test addSubscribe() ");
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("requestor", "andy@example.com");
		param.put("target", "gilang@example.com");
		String pInput = PojoJsonMapper.toJson(param);
		
		LOGGER.info("### snd : "+pInput);
		
		RequestEntity request = RequestEntity
				.post(new URI(Constants.BASE_URI + Constants.HEADER_PATH + Constants.SUB_PATH.ADD_SUBSCRIBE))
				.accept(MediaType.APPLICATION_JSON).body(pInput);
		ResponseEntity<String> response = restTemplate.exchange(request, String.class);

		LOGGER.info("### rsp : "+response.getStatusCode());
		
		if (response.getStatusCode().equals(HttpStatus.OK)) {
			// get status code 200(SUCCESS) if email not subscribe yet
			Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
		} else {
			// get status code 500(INTERNAL SERVER ERROR) if email already
			Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		}
		
		LOGGER.info("### finish test addSubscribe() ");
	}
	
	@SuppressWarnings("rawtypes")
	@Test
	public void blockPeople() throws Exception {

		LOGGER.info("### start test blockPeople() ");
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("requestor", "andy@example.com");
		param.put("target", "gilang@example.com");
		String pInput = PojoJsonMapper.toJson(param);

		LOGGER.info("### snd : "+pInput);
		
		RequestEntity request = RequestEntity
				.post(new URI(Constants.BASE_URI + Constants.HEADER_PATH + Constants.SUB_PATH.BLOCK_PEOPLE))
				.accept(MediaType.APPLICATION_JSON).body(pInput);
		ResponseEntity<String> response = restTemplate.exchange(request, String.class);

		LOGGER.info("### rsp : "+response.getStatusCode());
		
		if (response.getStatusCode().equals(HttpStatus.OK)) {
			// get status code 200(SUCCESS) if email not blocked yet
			Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
		} else {
			// get status code 500(INTERNAL SERVER ERROR) if email already blocked
			Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		}
		
		LOGGER.info("### finish test blockPeople() ");
	}

}
