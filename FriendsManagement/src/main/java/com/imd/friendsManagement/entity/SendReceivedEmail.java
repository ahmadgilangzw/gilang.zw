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
@Table(name = "t_send_re_email")
public class SendReceivedEmail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3470423433007349165L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "sender")
	private String sender;

	@Column(name = "text")
	private String text;

	@Column(name = "recipients")
	private String recipients;

	@Column(name = "status")
	private String status;

	@Column(name = "created")
	private Date created;

	public SendReceivedEmail() {
		super();
	}

	public SendReceivedEmail(String recipients) {
		super();
		this.recipients = recipients;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getRecipients() {
		return recipients;
	}

	public void setRecipients(String recipients) {
		this.recipients = recipients;
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

}
