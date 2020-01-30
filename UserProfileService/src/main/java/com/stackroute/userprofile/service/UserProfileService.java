package com.stackroute.userprofile.service;



import com.stackroute.userprofile.model.UserProfile;
import com.stackroute.userprofile.util.exception.UserProfileAlreadyExistsException;
import com.stackroute.userprofile.util.exception.UserProfileNotFoundException;

public interface UserProfileService {

	/*
	 * Should not modify this interface. You have to implement these methods in
	 * corresponding Impl classes
	 */

  UserProfile registerUser(UserProfile user) throws UserProfileAlreadyExistsException;

  UserProfile updateUser(String userId,UserProfile user) throws UserProfileNotFoundException;

  boolean deleteUser(String userId) throws UserProfileNotFoundException;

  UserProfile getUserById(String userId) throws UserProfileNotFoundException;


	
	
}
