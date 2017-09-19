package com.imd.friendsManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.imd.friendsManagement.entity.Friend;

/**
 * 
 * @author Gilang ZW
 *
 */
public interface FriendRepository extends JpaRepository<Friend, Long>, JpaSpecificationExecutor<Friend> {

	public Friend findByEmailPeopleAndEmailFriend(String emailPeople, String emailFriend);
	
	public List<Friend> findByEmailPeople(String emailPeople);
	
}
