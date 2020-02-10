package com.stackroute.newz.util.exception;

public class NewsNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public NewsNotFoundException(String message) {
		super(message);
	}
}
