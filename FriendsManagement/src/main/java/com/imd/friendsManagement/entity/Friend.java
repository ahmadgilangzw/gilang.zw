package com.imd.friendsManagement.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author Gilang ZW
 *
 */

@Entity
@Table(name = "m_friend")
public class Friend implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3935341205384223641L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "email_people")
	private String emailPeople;

	@Column(name = "email_friend")
	private String emailFriend;

	@Column(name = "status")
	private String status;

	@Column(name = "created")
	private Date created;

	@Column(name = "updated")
	private Date updated;

	public Friend(String emailPeople, String emailFriend) {
		super();
		this.emailPeople = emailPeople;
		this.emailFriend = emailFriend;
	}

	public Friend() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmailPeople() {
		return emailPeople;
	}

	public void setEmailPeople(String emailPeople) {
		this.emailPeople = emailPeople;
	}

	public String getEmailFriend() {
		return emailFriend;
	}

	public void setEmailFriend(String emailFriend) {
		this.emailFriend = emailFriend;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

}
