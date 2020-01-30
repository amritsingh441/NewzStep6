package com.stackroute.newz.service;

import java.util.List;

import com.stackroute.newz.model.NewsSource;
import com.stackroute.newz.util.exception.NewsSourceNotFoundException;

public interface NewsSourceService {
	
	/*
	 * Should not modify this interface. You have to implement these methods in
	 * corresponding Impl classes
	 */

	boolean addNewsSource(NewsSource newsSource);

	boolean deleteNewsSource(int newsSourceId);

	NewsSource updateNewsSource(NewsSource newsSource, int newsSourceId) throws NewsSourceNotFoundException;

	NewsSource getNewsSourceById(String userId,int newsSourceId) throws NewsSourceNotFoundException;

	List<NewsSource> getAllNewsSourceByUserId(String userId);
	
}
