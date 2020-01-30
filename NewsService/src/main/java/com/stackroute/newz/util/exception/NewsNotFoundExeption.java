package com.stackroute.newz.util.exception;

public class NewsNotFoundExeption extends Exception {

	private static final long serialVersionUID = 1L;

	public NewsNotFoundExeption(String message) {
		super(message);
	}
}
