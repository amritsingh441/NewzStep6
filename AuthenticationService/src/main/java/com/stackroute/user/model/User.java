package com.stackroute.user.model;


/*
 * The class "User" will be acting as the data model for the User Table in the database. 
 * Please note that this class is annotated with @Entity annotation. 
 * Hibernate will scan all package for any Java objects annotated with the @Entity annotation. 
 * If it finds any, then it will begin the process of looking through that particular 
 * Java object to recreate it as a table in your database.
 */


public class User {
	
    /*
	 * This class should have three fields (userId,password,cpassword. Out of these 
	 * five fields, the field userId should be the primary key. This class should
	 * also contain the getters and setters for the fields, along with the no-arg,
	 * parameterized constructor and toString method.
	 */


	private String userId;
	private String password;
	private String cpassword;
	
	public User() {
		super();
	}	
	
	
	public User(String userId, String password, String cpassword) {

	}	
	
	
	public String getUserId() {
		return null;
	}
	public void setUserId(String userId) {
		
	}
	public String getPassword() {
		return null;
	}
	public void setPassword(String password) {
		
	}
	public String getCpassword() {
		return null;
	}
	public void setCpassword(String cpassword) {
		
	}

}
