package com.imd.friendsManagement.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imd.friendsManagement.entity.Friend;
import com.imd.friendsManagement.repository.FriendRepository;
import com.imd.friendsManagement.service.FriendService;

/**
 * 
 * @author Gilang ZW
 *
 */
@Service
public class FriendServiceImpl implements FriendService{
    
    private final Logger LOGGER = LoggerFactory.getLogger(FriendServiceImpl.class);
    
    @Autowired private FriendRepository friendRepository;

    @Override
    public List<Friend> findByEmailPeople(String emailPeople) {
        try{
            return friendRepository.findByEmailPeople(emailPeople);
        } catch(Exception ex){
            LOGGER.info("error find Email People cause : {}", ex);
        }
        return null;
    }

    @Override
    @Transactional
    public Friend save(Friend friend) {
    	Friend f = new Friend();
        try{
            if(friend.getId() != null){
                LOGGER.info("UPDATE DATA WITH ID = {}", friend.getId());
                friend.setUpdated(friend.getUpdated());
                return friendRepository.save(friend);
            } else {
            	LOGGER.info("SAVE DATA WITH EMAIL ADDRESS = {}", friend.getEmailPeople());
                f.setEmailPeople(friend.getEmailPeople());
                f.setEmailFriend(friend.getEmailFriend());
                f.setStatus(friend.getStatus());
                f.setCreated(friend.getCreated());
                f.setUpdated(friend.getUpdated());
            }
        } catch(Exception ex){
            LOGGER.info("error save friend cause : {}", ex);
        }
        return friendRepository.save(f);
    }

    @Override
    public Friend getById(Long id) {
        try{
            return friendRepository.findOne(id);
        } catch(Exception ex){
            LOGGER.info("error find id friend cause : {}", ex);
        }
        return null;
    }

    @Override
    public List<Friend> getAll() {
        List<Friend> listOfModel = friendRepository.findAll();
        try{
            if(!listOfModel.isEmpty()){
                return listOfModel;
            }
        } catch(Exception ex){
            LOGGER.info("error find all friend cause : {}", ex);
        }
        return null;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        try{
            if(id != null){
            	friendRepository.delete(id);
            }
        } catch(Exception ex){
            LOGGER.info("error find id friend cause : {}", ex);
        }
    }

	@Override
	public Friend findByEmailPeopleAndEmailFriend(String emailPeople, String emailFriend) {
		try{
            return friendRepository.findByEmailPeopleAndEmailFriend(emailPeople, emailFriend);
        } catch(Exception ex){
            LOGGER.info("error findByEmailPeopleAndEmailFriend cause : {}", ex);
        }
        return null;
	}
}