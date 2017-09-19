package com.imd.friendsManagement.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imd.friendsManagement.entity.People;
import com.imd.friendsManagement.repository.PeopleRepository;
import com.imd.friendsManagement.service.PeopleService;

/**
 * 
 * @author Gilang ZW
 *
 */
@Service
public class PeopleServiceImpl implements PeopleService{
    
    private final Logger LOGGER = LoggerFactory.getLogger(PeopleServiceImpl.class);
    
    @Autowired private PeopleRepository peopleRepository;

    @Override
    public People findByEmailAddress(String emailAddress) {
        try{
            return peopleRepository.findByEmailAddress(emailAddress);
        } catch(Exception ex){
            LOGGER.info("error find Email Address cause : {}", ex);
        }
        return null;
    }

    @Override
    @Transactional
    public People save(People people) {
    	People p = new People();
        try{
        	LOGGER.info("ID People = "+people.getId());
            if(people.getId() != null){
                LOGGER.info("UPDATE DATA WITH ID = {}", people.getId());
                people.setUpdated(people.getUpdated());
                return peopleRepository.save(people);
            } else {
            	LOGGER.info("SAVE DATA WITH EMAIL ADDRESS = {}", people.getEmailAddress());
                p.setName(people.getName());
                p.setEmailAddress(people.getEmailAddress());
                p.setCreated(people.getCreated());
                p.setUpdated(people.getUpdated());
            }
        } catch(Exception ex){
            LOGGER.info("error save people cause : {}", ex);
        }
        return peopleRepository.save(p);
    }

    @Override
    public People getById(Long id) {
        try{
            return peopleRepository.findOne(id);
        } catch(Exception ex){
            LOGGER.info("error find id people cause : {}", ex);
        }
        return null;
    }

    @Override
    public List<People> getAll() {
        List<People> listOfModel = peopleRepository.findAll();
        try{
            if(!listOfModel.isEmpty()){
                return listOfModel;
            }
        } catch(Exception ex){
            LOGGER.info("error find all people cause : {}", ex);
        }
        return null;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        try{
            if(id != null){
                peopleRepository.delete(id);
            }
        } catch(Exception ex){
            LOGGER.info("error find id people cause : {}", ex);
        }
    }
}