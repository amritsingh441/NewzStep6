package com.stackroute.newz.controller;

import com.stackroute.newz.service.NewsService;

/*
 * As in this assignment, we are working with creating RESTful web service, hence annotate
 * the class with @RestController annotation.A class annotated with @Controller annotation
 * has handler methods which returns a view. However, if we use @ResponseBody annotation along
 * with @Controller annotation, it will return the data directly in a serialized 
 * format. Starting from Spring 4 and above, we can use @RestController annotation which 
 * is equivalent to using @Controller and @ResposeBody annotation
 */

public class NewsController {

	/*
	 * Autowiring should be implemented for the NewsService. (Use Constructor-based
	 * autowiring) Please note that we should not create any object using the new
	 * keyword
	 */

	public NewsController(NewsService newsService) {
		
	}

	/*
	 * Define a handler method which will create a specific news by reading the
	 * Serialized object from request body and save the news details in the
	 * database.This handler method should return any one of the status messages
	 * basis on different situations: 
	 * 1. 201(CREATED) - If the news created successfully. 
	 * 2. 409(CONFLICT) - If the newsId conflicts with any existing user.
	 * 
	 * This handler method should map to the URL "/api/v1/news" using HTTP POST method
	 */

	/*
	 * Define a handler method which will delete a news from a database.
	 * This handler method should return any one of the status messages basis 
	 * on different situations: 
	 * 1. 200(OK) - If the news deleted successfully from database. 
	 * 2. 404(NOT FOUND) - If the news with specified newsId is not found.
	 *
	 * This handler method should map to the URL "/api/v1/news/{userId}/{newsId}" 
	 * using HTTP Delete method where "userId" should be replaced by a valid userId 
	 * without {} and "newsId" should be replaced by a valid newsId 
	 * without {}.
	 * 
	 */

	/*
	 * Define a handler method which will delete all the news of a specific user from 
	 * a database. This handler method should return any one of the status messages 
	 * basis on different situations: 
	 * 1. 200(OK) - If the newsId deleted successfully from database. 
	 * 2. 404(NOT FOUND) - If the note with specified newsId is not found.
	 *
	 * This handler method should map to the URL "/api/v1/news/{userId}" 
	 * using HTTP Delete method where "userId" should be replaced by a valid userId 
	 * without {} and "newsid" should be replaced by a valid newsId 
	 * without {}.
	 * 
	 */
	
	/*
	 * Define a handler method which will update a specific news by reading the
	 * Serialized object from request body and save the updated news details in a
	 * database. 
	 * This handler method should return any one of the status messages
	 * basis on different situations: 
	 * 1. 200(OK) - If the news updated successfully.
	 * 2. 404(NOT FOUND) - If the news with specified newsId is not found.
	 * 
	 * This handler method should map to the URL "/api/v1/news/{userId}/{newsId}" using 
	 * HTTP PUT method where "userId" should be replaced by a valid userId 
	 * without {} and "newsid" should be replaced by a valid newsId without {}.
	 * 
	 */
	
	/*
	 * Define a handler method which will get us the specific news by a userId.
	 * This handler method should return any one of the status messages basis on
	 * different situations: 
	 * 1. 200(OK) - If the news found successfully. 
	 * 2. 404(NOT FOUND) - If the news with specified newsId is not found.
	 * 
	 * This handler method should map to the URL "/api/v1/news/{userId}/{newsId}" 
	 * using HTTP GET method where "userId" should be replaced by a valid userId 
	 * without {} and "newsid" should be replaced by a valid newsId without {}.
	 * 
	 */
	

	/*
	 * Define a handler method which will show details of all news created by specific 
	 * user. This handler method should return any one of the status messages basis on
	 * different situations: 
	 * 1. 200(OK) - If the news found successfully. 
	 * 2. 404(NOT FOUND) - If the news with specified newsId is not found.
	 * This handler method should map to the URL "/api/v1/news/{userId}" using HTTP GET method
	 * where "userId" should be replaced by a valid userId without {}.
	 * 
	 */



}
