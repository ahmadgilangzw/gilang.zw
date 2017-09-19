package com.imd.friendsManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.imd.friendsManagement.entity.Subscribe;

/**
 * 
 * @author Gilang ZW
 *
 */
public interface SubscribeRepository extends JpaRepository<Subscribe, Long>, JpaSpecificationExecutor<Subscribe> {
	
	public Subscribe findByRequestorAndTarget(String requestor, String target);
	
	public List<Subscribe> findByTarget(String target);

}
