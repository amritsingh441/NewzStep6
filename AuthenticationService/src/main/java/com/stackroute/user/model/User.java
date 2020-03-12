package com.stackroute.user.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/*
 * The class "User" will be acting as the data model for the User Table in the database. 
 * Please note that this class is annotated with @Entity annotation. 
 * Hibernate will scan all package for any Java objects annotated with the @Entity annotation. 
 * If it finds any, then it will begin the process of looking through that particular 
 * Java object to recreate it as a table in your database.
 */

@Entity
public class User {
	
    /*
	 * This class should have three fields (userId,password,cpassword. Out of these 
	 * five fields, the field userId should be the primary key. This class should
	 * also contain the getters and setters for the fields, along with the no-arg,
	 * parameterized constructor and toString method.
	 */

	@Id
	private String userId;
	private String password;
	private String cpassword;
	
	public User() {
	}	
	
	public User(String userId, String password, String cpassword) {
		this.userId = userId;
		this.password = password;
		this.cpassword = cpassword;
	}


	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
		
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
		
	}
	public String getCpassword() {
		return cpassword;
	}
	public void setCpassword(String cpassword) {
		this.cpassword = cpassword;
		
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", password=" + password + ", cpassword=" + cpassword + "]";
	}
	
}