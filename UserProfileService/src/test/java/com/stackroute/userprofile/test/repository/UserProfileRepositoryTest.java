package com.stackroute.userprofile.test.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.stackroute.userprofile.model.UserProfile;
import com.stackroute.userprofile.repository.UserProfileRepository;


@ExtendWith(SpringExtension.class)
@DataMongoTest
public class UserProfileRepositoryTest {

    @Autowired
    private UserProfileRepository userProfileRepository;
    private UserProfile userProfile;


    @BeforeEach
    public void setUp() throws Exception {
        userProfile = new UserProfile();
        userProfile.setUserId("Jhon123");
        userProfile.setFirstName("Jhon"); 
        userProfile.setLastName("Simon");
        userProfile.setContact("9898989898");
        userProfile.setEmail("Jhon@gmail.com");
        userProfile.setCreatedAt();
    }

    @AfterEach
    public void tearDown() throws Exception {

    	userProfileRepository.deleteAll();

    }

    @Test
    public void registerUserTest() {

    	userProfileRepository.insert(userProfile);
        UserProfile fetcheduser = userProfileRepository.findById("Jhon123").get();
        
        assertEquals(userProfile.getUserId(), fetcheduser.getUserId());

    }

    @Test
    public void deleteUserTest() {
    	userProfileRepository.insert(userProfile);
    	UserProfile fetcheduser = userProfileRepository.findById("Jhon123").get();
        assertEquals("Jhon123", fetcheduser.getUserId());
        userProfileRepository.delete(fetcheduser);
        try {
        	userProfileRepository.findById("Jhon123").get();
        }catch(Exception exception) {
        	assertEquals(NoSuchElementException.class, exception.getClass());
        }

    }

    @Test
    public void updateUserTest() {
    	userProfileRepository.insert(userProfile);
    	UserProfile fetcheduser = userProfileRepository.findById("Jhon123").get();
        fetcheduser.setContact("987654321");
        userProfileRepository.save(fetcheduser);
        fetcheduser = userProfileRepository.findById("Jhon123").get();
        assertEquals("987654321", fetcheduser.getContact());
    }

    @Test
    public void getUserByIdTest() {
    	userProfileRepository.insert(userProfile);
    	UserProfile fetcheduser = userProfileRepository.findById("Jhon123").get();
        assertEquals(userProfile.getUserId(),fetcheduser.getUserId());

    }


}
