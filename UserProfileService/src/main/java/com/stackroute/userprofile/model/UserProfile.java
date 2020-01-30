package com.stackroute.userprofile.model;

import java.time.LocalDateTime;

/*
 * Please note that this class is annotated with @Document annotation
 * @Document identifies a domain object to be persisted to MongoDB.
 *  
 */

public class UserProfile {

	/*
	 * This class should have six fields (userId,firstName,
	 * lastName,contact,email,createdAt). Out of these six fields, the field
	 * userId should be annotated with @Id (This annotation explicitly specifies the document
	 * identifier). This class should also contain the getters and setters for the
	 * fields, along with the no-arg , parameterized constructor and toString
	 * method.The value of createdAt should not be accepted from the user but
	 * should be always initialized with the system date.
	 */

    
    public UserProfile() {
    }
    
    public UserProfile(String string1, String string2, String string3, String string4, String string5, LocalDateTime date) {
	}

	public String getUserId() {
		return null;
	}
	public void setUserId(String userId) {
	}
	public String getFirstName() {
		return null;
	}
	public void setFirstName(String firstName) {
	}
	public String getLastName() {
		return null;
	}
	public void setLastName(String lastName) {
	}
	public String getContact() {
		return null;
	}
	public void setContact(String contact) {
	}
	public String getEmail() {
		return null;
	}
	public void setEmail(String email) {
	}
	public LocalDateTime getCreatedAt() {
		return null;
	}
	public void setCreatedAt() {
	}
	
	
    
    
}
