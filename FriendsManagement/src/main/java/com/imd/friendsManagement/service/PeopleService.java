package com.imd.friendsManagement.service;

import com.imd.friendsManagement.entity.People;

/**
 * 
 * @author Gilang ZW
 *
 */
public interface PeopleService extends CrudService<People>{
    
    public People findByEmailAddress(String emailAddress);
}
