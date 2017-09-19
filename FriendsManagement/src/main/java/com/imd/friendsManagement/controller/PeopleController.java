package com.imd.friendsManagement.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.imd.friendsManagement.entity.People;
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
public class PeopleController {

	private final Logger LOGGER = LoggerFactory.getLogger(PeopleController.class);

	@Autowired
	private PeopleService service;

	@RequestMapping(value = Constants.SUB_PATH.REGISTER_PEOPLE, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public String registerPeople(@RequestBody People p) {
		LOGGER.info("### rcv : " + p);
		
		People people = new People();
		String result;
		try {
			if (p == null) {
				throw new BusinessException(Constants.ERROR_CODE.RC002);
			} else if (p.getName() == null || p.getEmailAddress() == null) {
				throw new BusinessException(Constants.ERROR_CODE.RC002);
			} else {
				People pe = service.findByEmailAddress(p.getEmailAddress());
				if(pe != null){
					throw new BusinessException(Constants.ERROR_CODE.PEOPLE.RCP001);
				}
				
				p.setCreated(new Date());
				p.setUpdated(new Date());
				people = service.save(p);
			}
			result = PojoJsonMapper.toJson(people);
			LOGGER.info("### snd : " + result);
			return result; 
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
	
}
