package com.stackroute.userprofile.repository;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.stackroute.userprofile.model.UserProfile;

/*
* This class is implementing the MongoRepository interface for User.
* Annotate this class with @Repository annotation
* */

public interface UserProfileRepository extends MongoRepository<UserProfile, String> {
}

