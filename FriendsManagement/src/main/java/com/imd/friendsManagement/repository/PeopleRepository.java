package com.imd.friendsManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.imd.friendsManagement.entity.People;

/**
 * 
 * @author Gilang ZW
 *
 */
public interface PeopleRepository extends JpaRepository<People, Long>, JpaSpecificationExecutor<People>{
   
   public People findByEmailAddress(String emailAddress);
   
}
