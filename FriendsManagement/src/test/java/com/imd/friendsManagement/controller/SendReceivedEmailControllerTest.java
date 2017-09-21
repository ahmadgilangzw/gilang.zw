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
public class SendReceivedEmailControllerTest {

	private final Logger LOGGER = LoggerFactory.getLogger(SendReceivedEmailControllerTest.class);
	
	private RestTemplate restTemplate = new TestRestTemplate();
	
	@SuppressWarnings("rawtypes")
	@Test
	public void sendEmail() throws Exception {

		LOGGER.info("### start test sendEmail() ");
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("sender", "andy@example.com");
		param.put("text", "Hello World! kate@example.com");
		String pInput = PojoJsonMapper.toJson(param);

		LOGGER.info("### snd : "+pInput);
		
		RequestEntity request = RequestEntity
				.post(new URI(Constants.BASE_URI + Constants.HEADER_PATH + Constants.SUB_PATH.SEND_EMAIL))
				.accept(MediaType.APPLICATION_JSON).body(pInput);
		ResponseEntity<String> response = restTemplate.exchange(request, String.class);

		LOGGER.info("### rsp : "+response.getStatusCode());
		
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
		
		LOGGER.info("### finish test sendEmail() ");
	}

}
