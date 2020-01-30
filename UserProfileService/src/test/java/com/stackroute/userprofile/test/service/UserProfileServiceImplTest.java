package com.stackroute.userprofile.test.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.stackroute.userprofile.model.UserProfile;
import com.stackroute.userprofile.repository.UserProfileRepository;
import com.stackroute.userprofile.service.UserProfileServiceImpl;
import com.stackroute.userprofile.util.exception.UserProfileAlreadyExistsException;
import com.stackroute.userprofile.util.exception.UserProfileNotFoundException;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserProfileServiceImplTest {

    @Mock
    UserProfileRepository userProfileRepository;

    UserProfile userProfile;

    @InjectMocks
    UserProfileServiceImpl userProfileService;

    List<UserProfile> userList = null;
    Optional<UserProfile> options;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        userProfile = new UserProfile();
        userProfile.setUserId("Jhon123");
        userProfile.setFirstName("Jhon"); 
        userProfile.setLastName("Simon");
        userProfile.setContact("9898989898");
        userProfile.setEmail("Jhon@gmail.com");
        userProfile.setCreatedAt();
        userList = new ArrayList<>();
        userList.add(userProfile);

        options = Optional.of(userProfile);

    }

    @Test
    public void registerUserSuccess() throws UserProfileAlreadyExistsException {
        when(userProfileRepository.insert(userProfile)).thenReturn(userProfile);
        UserProfile userSaved = userProfileService.registerUser(userProfile);
        assertEquals(userProfile, userSaved);

    }
   
    @Test
    public void registerUserFailure() throws UserProfileAlreadyExistsException {
        when(userProfileRepository.insert(userProfile)).thenReturn(null);
        
        assertThrows(
        		UserProfileAlreadyExistsException.class,
                () -> { userProfileService.registerUser(userProfile); });
 

    }

    @Test
    public void updateUser() throws UserProfileNotFoundException {
        when(userProfileRepository.findById(userProfile.getUserId())).thenReturn(options);
        userProfile.setContact("1234567789");
        UserProfile fetchuser = userProfileService.updateUser(userProfile.getUserId(), userProfile);
        assertEquals(userProfile, fetchuser);

    }

    @Test
    public void deleteUserSuccess() throws UserProfileNotFoundException {
        when(userProfileRepository.findById(userProfile.getUserId())).thenReturn(options);
        boolean flag = userProfileService.deleteUser(userProfile.getUserId());
        assertEquals(true, flag);

    }

    @Test
    public void getUserById() throws UserProfileNotFoundException {

        when(userProfileRepository.findById(userProfile.getUserId())).thenReturn(options);

        UserProfile fetchedUser = userProfileService.getUserById(userProfile.getUserId());

        assertEquals(userProfile, fetchedUser);

    }

}