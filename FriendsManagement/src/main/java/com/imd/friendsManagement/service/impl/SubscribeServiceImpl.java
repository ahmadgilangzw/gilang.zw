package com.imd.friendsManagement.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imd.friendsManagement.entity.Subscribe;
import com.imd.friendsManagement.repository.SubscribeRepository;
import com.imd.friendsManagement.service.SubscribeService;

/**
 * 
 * @author Gilang ZW
 *
 */
@Service
public class SubscribeServiceImpl implements SubscribeService{
    
    private final Logger LOGGER = LoggerFactory.getLogger(SubscribeServiceImpl.class);
    
    @Autowired private SubscribeRepository subcribeRepository;

    @Override
    @Transactional
    public Subscribe save(Subscribe subcribe) {
    	Subscribe s = new Subscribe();
        try{
            if(subcribe.getId() != null){
                LOGGER.info("UPDATE DATA WITH ID = {}", subcribe.getId());
                subcribe.setUpdated(subcribe.getUpdated());
                return subcribeRepository.save(subcribe);
            } else {
            	LOGGER.info("SAVE DATA WITH EMAIL ADDRESS = {}", subcribe.getRequestor());
                s.setRequestor(subcribe.getRequestor());
                s.setTarget(subcribe.getTarget());
                s.setStatus(subcribe.getStatus());
                s.setCreated(subcribe.getCreated());
                s.setUpdated(subcribe.getUpdated());
            }
        } catch(Exception ex){
            LOGGER.info("error save subcribe cause : {}", ex);
        }
        return subcribeRepository.save(s);
    }

    @Override
    public Subscribe getById(Long id) {
        try{
            return subcribeRepository.findOne(id);
        } catch(Exception ex){
            LOGGER.info("error find id subcribe cause : {}", ex);
        }
        return null;
    }

    @Override
    public List<Subscribe> getAll() {
        List<Subscribe> listOfModel = subcribeRepository.findAll();
        try{
            if(!listOfModel.isEmpty()){
                return listOfModel;
            }
        } catch(Exception ex){
            LOGGER.info("error find all subcribe cause : {}", ex);
        }
        return null;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        try{
            if(id != null){
            	subcribeRepository.delete(id);
            }
        } catch(Exception ex){
            LOGGER.info("error delete subcribe cause : {}", ex);
        }
    }

	@Override
	public Subscribe findByRequestorAndTarget(String requestor, String target) {
		try{
            return subcribeRepository.findByRequestorAndTarget(requestor, target);
        } catch(Exception ex){
            LOGGER.info("error findByRequestorAndTarget subcribe cause : {}", ex);
        }
        return null;
	}

	@Override
	public List<Subscribe> findByTarget(String target) {
		try{
            return subcribeRepository.findByTarget(target);
        } catch(Exception ex){
            LOGGER.info("error findByTarget subcribe cause : {}", ex);
        }
        return null;
	}
}