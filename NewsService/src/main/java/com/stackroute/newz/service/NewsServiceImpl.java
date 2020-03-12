package com.stackroute.newz.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.stackroute.newz.model.News;
import com.stackroute.newz.model.UserNews;
import com.stackroute.newz.repository.NewsRepository;
import com.stackroute.newz.util.exception.NewsNotFoundException;

/*
 * Service classes are used here to implement additional business logic/validation 
 * This class has to be annotated with @Service annotation.
 * @Service - It is a specialization of the component annotation. It doesn't currently 
 * provide any additional behavior over the @Component annotation, but it's a good idea 
 * to use @Service over @Component in service-layer classes because it specifies intent 
 * better. Additionally, tool support and additional behavior might rely on it in the 
 * future.
 * */

@Service
@Component
public class NewsServiceImpl implements NewsService {

	/*
	 * Autowiring should be implemented for the NewsDao and MongoOperation.
	 * (Use Constructor-based autowiring) Please note that we should not create any
	 * object using the new keyword.
	 */
	@Autowired
	private NewsRepository newsRepo;

	@Autowired
	public NewsServiceImpl(NewsRepository newsRepo) {
		this.newsRepo = newsRepo;
	}

	/*
	 * This method should be used to save a new news.
	 */
	@Override
	public boolean addNews(News news) {
		UserNews newUserNews = new UserNews();
		List<News> newsList = new ArrayList<>();
		newsList.add(news);
		newUserNews.setNewslist(newsList);
		UserNews savedUserNews = newsRepo.insert(newUserNews);
		if(savedUserNews!=null) {
			return true;
		}
		return false;
	}

	/* This method should be used to delete an existing news. */

	public boolean deleteNews(String userId, int newsId) {
		Optional<UserNews> userNewsObj =newsRepo.findById(userId);
		if(userNewsObj.isPresent()) {
			Optional<News> newsObj = userNewsObj.get().getNewslist().stream().filter(news -> news.getNewsId() == newsId).findAny();
			if(newsObj.isPresent()) {
				newsRepo.deleteById(Integer.toString(newsObj.get().getNewsId()));
				return true;
			}
			return false;
		}
		return false;
	}

	/* This method should be used to delete all news for a  specific userId. */

	public boolean deleteAllNews(String userId) throws NewsNotFoundException {
		try {
			Optional<UserNews> userNewsObj = newsRepo.findById(userId);
			if(userNewsObj.isPresent()) {
				if(userNewsObj.get().getNewslist()!=null) {
					userNewsObj.get().getNewslist().stream()
					.forEach(news -> newsRepo.deleteById(Integer.toString(news.getNewsId())));
					return true;
				}else {
					throw new NewsNotFoundException("News not found");
				}
			}
		}catch(NoSuchElementException ne) {
			throw new NewsNotFoundException("News not found");
		}
		return false;
	}

	/*
	 * This method should be used to update a existing news.
	 */

	public News updateNews(News news, int newsId, String userId) throws NewsNotFoundException {
		try {
			List<News> newsList = new  ArrayList<>();
			Optional<UserNews> uNewsObj = newsRepo.findById(userId);
			if(uNewsObj.isPresent()) {
				Optional<News> newsOptObj =	uNewsObj.get().getNewslist().stream()
						.filter(n -> n.getNewsId() == newsId).findAny();
				if(newsOptObj.isPresent()){
					newsList.add(news);
					uNewsObj.get().setNewslist(newsList);
					newsRepo.save(uNewsObj.get());
					return news;
				}

			}
		}catch(NoSuchElementException ne) {
			throw new NewsNotFoundException("News not found");
		}
		return null;
	}

	/*
	 * This method should be used to get a news by newsId created by specific user
	 */

	public News getNewsByNewsId(String userId, int newsId) throws NewsNotFoundException {
		try {
			Optional<UserNews> userNews = newsRepo.findById(userId);
			if(userNews.isPresent()) {
				Optional<News> newsObj = userNews.get().getNewslist().stream()
						.filter(news -> news.getNewsId() == newsId).findAny();
				if(newsObj.isPresent()) {
					return newsObj.get();
				}else {
					throw new NewsNotFoundException("News Not Found");
				}
			}
		}catch(NoSuchElementException ne) {
			throw new NewsNotFoundException("News not found");
		}
		return null;

	}

	/*
	 * This method should be used to get all news for a specific userId.
	 */

	public List<News> getAllNewsByUserId(String userId) {
		Optional<UserNews> userNews = newsRepo.findById(userId);
		if(userNews.isPresent()) {
			return userNews.get().getNewslist();
		}
		return null;
	}

}