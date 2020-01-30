package com.stackroute.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.stackroute.user.model.User;


/*
* This class is implementing the JpaRepository interface for Note.
* Annotate this class with @Repository annotation
*/


public interface UserAuthRepository extends JpaRepository<User,String>{

	public User findByUserIdAndPassword(String userId, String password);

}
