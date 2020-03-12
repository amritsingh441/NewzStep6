package com.stackroute.newz.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.newz.model.UserNews;

/*
* This class is implementing the MongoRepository interface for Note.
* Annotate this class with @Repository annotation
* */
@Repository
public interface NewsRepository extends MongoRepository<UserNews, String> {

}
