package com.teszt.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "users")
public class User {
	
	@Id @GeneratedValue @Column(unique = true)
	private Integer userId;
	
	@Column(unique = true, nullable = false, name = "userEmail")
	private String userEmail;
	
	@Column(name = "userPassword")
	private String userPassword;
	
	@Column(name = "userName")
	private String userName;

	public User() {
	}
	
	public User(String userName, String userEmail) {
		this.userName = userName;
		this.userEmail = userEmail;
	}
	
	public User(String userName, String userEmail, String userPassword) {
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	
}
