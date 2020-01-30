package com.stackroute.userprofile.util.exception;

public class UserProfileAlreadyExistsException extends Exception {


	private static final long serialVersionUID = 1L;

	public UserProfileAlreadyExistsException(String message) {
        super(message);
    }
}
