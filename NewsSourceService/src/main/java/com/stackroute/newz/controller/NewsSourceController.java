package com.stackroute.newz.controller;

import com.stackroute.newz.service.NewsSourceService;

/*
 * As in this assignment, we are working with creating RESTful web service, hence annotate
 * the class with @RestController annotation.A class annotated with @Controller annotation
 * has handler methods which returns a view. However, if we use @ResponseBody annotation along
 * with @Controller annotation, it will return the data directly in a serialized 
 * format. Starting from Spring 4 and above, we can use @RestController annotation which 
 * is equivalent to using @Controller and @ResposeBody annotation
 */

public class NewsSourceController {

	/*
	 * Autowiring should be implemented for the NewsService. (Use Constructor-based
	 * autowiring) Please note that we should not create any object using the new
	 * keyword
	 */
	
	public NewsSourceController(NewsSourceService newsSourceService) {
	
	}
	

	/*
	 * Define a handler method which will create a specific newssource by reading the
	 * Serialized object from request body and save the newssource details in the
	 * database.This handler method should return any one of the status messages
	 * basis on different situations: 
	 * 1. 201(CREATED) - If the newssource created successfully. 
	 * 2. 409(CONFLICT) - If the newssourceId conflicts with any existing user.
	 * 
	 * This handler method should map to the URL "/api/v1/newssource" using HTTP POST method
	 */


	/*
	 * Define a handler method which will delete a newssource from a database.
	 * This handler method should return any one of the status messages basis 
	 * on different situations: 
	 * 1. 200(OK) - If the newssource deleted successfully from database. 
	 * 2. 404(NOT FOUND) - If the newssource with specified newsId is not found.
	 *
	 * This handler method should map to the URL "/api/v1/newssource/{newssourceId}" 
	 * using HTTP Delete method where "userId" should be replaced by a valid userId 
	 * without {} and "newssourceId" should be replaced by a valid newsId 
	 * without {}.
	 * 
	 */
	
	/*
	 * Define a handler method which will update a specific newssource by reading the
	 * Serialized object from request body and save the updated newssource details in a
	 * database. This handler method should return any one of the status messages
	 * basis on different situations: 
	 * 1. 200(OK) - If the newssource updated successfully.
	 * 2. 404(NOT FOUND) - If the newssource with specified newssourceId is not found.
	 * 
	 * This handler method should map to the URL "/api/v1/newssource/{newssourceId}" using 
	 * HTTP PUT method where "newssourceId" should be replaced by a valid newssourceId
	 * without {}.
	 * 
	 */
	
	/*
	 * Define a handler method which will get us the specific newssource by a userId.
	 * This handler method should return any one of the status messages basis on
	 * different situations: 
	 * 1. 200(OK) - If the newssource found successfully. 
	 * 2. 404(NOT FOUND) - If the newssource with specified newsId is not found.
	 * 
	 * This handler method should map to the URL "/api/v1/newssource/{userId}/{newssourceId}" 
	 * using HTTP GET method where "userId" should be replaced by a valid userId 
	 * without {} and "newssourceId" should be replaced by a valid newsId without {}.
	 * 
	 */
	
	/*
	 * Define a handler method which will show details of all newssource created by specific 
	 * user. This handler method should return any one of the status messages basis on
	 * different situations: 
	 * 1. 200(OK) - If the newssource found successfully. 
	 * 2. 404(NOT FOUND) - If the newssource with specified newsId is not found.
	 * This handler method should map to the URL "/api/v1/newssource/{userId}" using HTTP GET method
	 * where "userId" should be replaced by a valid userId without {}.
	 * 
	 */

    
}
