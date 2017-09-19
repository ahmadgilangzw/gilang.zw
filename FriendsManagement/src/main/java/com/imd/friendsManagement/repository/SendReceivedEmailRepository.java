package com.imd.friendsManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.imd.friendsManagement.entity.SendReceivedEmail;

/**
 * 
 * @author Gilang ZW
 *
 */
public interface SendReceivedEmailRepository extends JpaRepository<SendReceivedEmail, Long>, JpaSpecificationExecutor<SendReceivedEmail> {

}
