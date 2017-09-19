package com.imd.friendsManagement.service;

import java.util.List;

import com.imd.friendsManagement.entity.Friend;

/**
 * 
 * @author Gilang ZW
 *
 */
public interface FriendService extends CrudService<Friend>{
    
    public List<Friend> findByEmailPeople(String emailPeople);
    
    public Friend findByEmailPeopleAndEmailFriend(String emailPeople, String emailFriend);
}
