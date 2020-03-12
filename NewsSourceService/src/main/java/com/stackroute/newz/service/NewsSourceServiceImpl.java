package com.stackroute.newz.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.stackroute.newz.model.NewsSource;
import com.stackroute.newz.repository.NewsSourceRepository;
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

@Service
@Component
public class NewsSourceServiceImpl implements NewsSourceService {

	/*
	 * Autowiring should be implemented for the NewsDao and MongoOperation.
	 * (Use Constructor-based autowiring) Please note that we should not create any
	 * object using the new keyword.
	 */
	
	@Autowired
	private NewsSourceRepository newsSourceRepo;

	@Autowired
	public NewsSourceServiceImpl(NewsSourceRepository newsSourceRepo) {
		this.newsSourceRepo = newsSourceRepo;
	}

	/*
	 * This method should be used to save a newsSource.
	 */

	@Override
	public boolean addNewsSource(NewsSource newsSource) {
		NewsSource newsSourceObj = newsSourceRepo.insert(newsSource);
		if(newsSourceObj!=null) {
			return true;
		}
		return false;
	}

	/* This method should be used to delete an existing newsSource. */

	@Override
	public boolean deleteNewsSource(int newsSourceId) {
		Optional<NewsSource> newsSrc = newsSourceRepo.findById(newsSourceId);
		if(newsSrc!=null && newsSrc.isPresent()) {
			newsSourceRepo.deleteById(newsSourceId);
			return true;
		}
		return false;
	}

	/* This method should be used to update an existing newsSource. */
	
	@Override
	public NewsSource updateNewsSource(NewsSource newsSource, int newsSourceId) throws NewsSourceNotFoundException {
		Optional<NewsSource> newsSrc = newsSourceRepo.findById(newsSourceId);
		if(newsSrc.isPresent()) {
			newsSourceRepo.save(newsSource);
			return newsSource;
		}else {
			throw new NewsSourceNotFoundException("News Source not Found");
		}
	}

	/* This method should be used to get a specific newsSource for an user. */

	@Override
	public NewsSource getNewsSourceById(String userId, int newsSourceId) throws NewsSourceNotFoundException {
		try {
			List<NewsSource> newsSrcList = newsSourceRepo.findAllNewsSourceByNewsSourceCreatedBy(userId);
		
			if(newsSrcList!=null) {
				Optional<NewsSource> newsSrcObj = newsSrcList.stream()
						.filter(ns -> ns.getNewsSourceId() == newsSourceId).findAny();
				
				if(newsSrcObj.isPresent()) {
					return newsSrcObj.get();
				}else {
					throw new NewsSourceNotFoundException("News Not Found");
				}
			}
		}catch(NoSuchElementException ne) {
			ne.printStackTrace();
		}
		return null;
	}

	
	 /* This method should be used to get all newsSource for a specific userId.*/

	@Override
	public List<NewsSource> getAllNewsSourceByUserId(String createdBy) {
		return newsSourceRepo.findAllNewsSourceByNewsSourceCreatedBy(createdBy);
	}

}