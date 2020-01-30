package com.stackroute.user.service;

import com.stackroute.user.util.exception.UserAlreadyExistsException;
import com.stackroute.user.util.exception.UserNotFoundException;
import com.stackroute.user.model.User;

public interface UserAuthService {
	
	/*
	 * Should not modify this interface. You have to implement these methods in
	 * corresponding Impl classes
	 */

    public User findByUserIdAndPassword(String userId, String password) throws UserNotFoundException;

    boolean saveUser(User user) throws UserAlreadyExistsException;
}
