package com.supinfo.sup.entity;

import java.io.Serializable;

public class User implements Serializable{
	private Long Id;
	private String Password;
	private String Email;
	
	public User(){}
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}

	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	
}
