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
@Table(name = "m_subscribe")
public class Subscribe implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1903919328568271091L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "requestor")
	private String requestor;

	@Column(name = "target")
	private String target;

	@Column(name = "status")
	private String status;

	@Column(name = "created")
	private Date created;

	@Column(name = "updated")
	private Date updated;

	public Subscribe() {
		super();
	}
	
	public Subscribe(String requestor, String target){
		this.requestor = requestor;
		this.target = target;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getRequestor() {
		return requestor;
	}

	public void setRequestor(String requestor) {
		this.requestor = requestor;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

}
