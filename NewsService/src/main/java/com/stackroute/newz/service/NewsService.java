package com.stackroute.newz.service;

import java.util.List;

import com.stackroute.newz.model.News;
import com.stackroute.newz.util.exception.NewsNotFoundException;

public interface NewsService {

	/*
	 * Should not modify this interface. You have to implement these methods in
	 * corresponding Impl classes
	 */

	boolean addNews(News news);

	boolean deleteNews(String userId, int newsId);

	boolean deleteAllNews(String userId) throws NewsNotFoundException;

	News updateNews(News news, int id, String userId) throws NewsNotFoundException;

	News getNewsByNewsId(String userId, int newsId) throws NewsNotFoundException;

	List<News> getAllNewsByUserId(String userId);

}
