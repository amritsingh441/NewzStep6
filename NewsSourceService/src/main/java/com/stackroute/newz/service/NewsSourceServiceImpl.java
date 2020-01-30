package com.stackroute.newz.service;

import java.util.List;

import com.stackroute.newz.model.NewsSource;
import com.stackroute.newz.util.exception.NewsSourceNotFoundException;

/*
* Service classes are used here to implement additional business logic/validation 
* This class has to be annotated with @Service annotation.
* @Service - It is a specialization of the component annotation. It doesn't currently 
* provide any additional behavior over the @Component annotation, but it's a good idea 
* to use @Service over @Component in service-layer classes because it specifies intent 
* better. Additionally, tool support and additional behavior might rely on it in the 
* future.
* */


public class NewsSourceServiceImpl implements NewsSourceService {

	/*
	 * Autowiring should be implemented for the NewsDao and MongoOperation.
	 * (Use Constructor-based autowiring) Please note that we should not create any
	 * object using the new keyword.
	 */

	/*
	 * This method should be used to save a newsSource.
	 */

	@Override
	public boolean addNewsSource(NewsSource newsSource) {
		return false;
	}

	/* This method should be used to delete an existing newsSource. */

	@Override
	public boolean deleteNewsSource(int newsSourceId) {
		return false;
	}

	/* This method should be used to update an existing newsSource. */
	
	@Override
	public NewsSource updateNewsSource(NewsSource newsSource, int newsSourceId) throws NewsSourceNotFoundException {
		return null;
	}

	/* This method should be used to get a specific newsSource for an user. */

	@Override
	public NewsSource getNewsSourceById(String userId, int newsSourceId) throws NewsSourceNotFoundException {
		return null;
	}

	
	 /* This method should be used to get all newsSource for a specific userId.*/

	@Override
	public List<NewsSource> getAllNewsSourceByUserId(String createdBy) {
		return null;
	}

}
