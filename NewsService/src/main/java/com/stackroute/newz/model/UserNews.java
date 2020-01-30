package com.stackroute.newz.model;

import java.util.List;

/*
 * Please note that this class is annotated with @Document annotation
 * @Document identifies a domain object to be persisted to MongoDB.
 * 
 */

public class UserNews {

	/*
	 * This class should have two fields (userId, newslist).Out of these two fields,
	 * the field userId should be annotated with @Id. This class should also contain
	 * the getters and setters for the fields.
	 */

	public String getUserId() {
		return null;
	}

	public void setUserId(String userId) {
		
	}

	public List<News> getNewslist() {
		return null;
	}

	public void setNewslist(List<News> newslist) {
		
	}

}
