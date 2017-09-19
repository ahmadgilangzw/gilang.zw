package com.imd.friendsManagement.service;

import java.util.List;

import com.imd.friendsManagement.entity.Subscribe;

/**
 * 
 * @author Gilang ZW
 *
 */
public interface SubscribeService extends CrudService<Subscribe>{
    
	public Subscribe findByRequestorAndTarget(String requestor, String target);
	
	public List<Subscribe> findByTarget(String target);
	
}
