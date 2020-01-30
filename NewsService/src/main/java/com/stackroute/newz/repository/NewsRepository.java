package com.stackroute.newz.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.stackroute.newz.model.UserNews;

/*
* This class is implementing the MongoRepository interface for Note.
* Annotate this class with @Repository annotation
* */

public interface NewsRepository extends MongoRepository<UserNews, String> {

}
