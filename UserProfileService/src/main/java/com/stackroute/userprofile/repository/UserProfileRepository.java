package com.stackroute.userprofile.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.userprofile.model.UserProfile;

/*
* This class is implementing the MongoRepository interface for User.
* Annotate this class with @Repository annotation
* */
@Repository
public interface UserProfileRepository extends MongoRepository<UserProfile, String> {
}

