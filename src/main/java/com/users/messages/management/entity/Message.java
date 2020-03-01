package com.users.messages.management.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "message")
public class Message implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "message_id")
	private long id;
	
	@Column(name= "message")
	private String message;
	
	@Column(name = "createdTs")
	private LocalDateTime createdTs;
	
	@Column(name = "modifiedTs")
	private LocalDateTime modifiedTs;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "created_user_id")
	private Users createdBy;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "modified_user_id")
	private Users modifiedBy;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDateTime getCreatedTs() {
		return createdTs;
	}

	public void setCreatedTs(LocalDateTime createdTs) {
		this.createdTs = createdTs;
	}

	public LocalDateTime getModifiedTs() {
		return modifiedTs;
	}

	public void setModifiedTs(LocalDateTime modifiedTs) {
		this.modifiedTs = modifiedTs;
	}

	public Users getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Users createdBy) {
		this.createdBy = createdBy;
	}

	public Users getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Users modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	
}
