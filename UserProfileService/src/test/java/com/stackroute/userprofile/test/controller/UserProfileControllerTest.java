package com.stackroute.userprofile.test.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.stackroute.userprofile.controller.UserProfileController;
import com.stackroute.userprofile.model.UserProfile;
import com.stackroute.userprofile.service.UserProfileService;
import com.stackroute.userprofile.util.exception.UserProfileAlreadyExistsException;
import com.stackroute.userprofile.util.exception.UserProfileNotFoundException;

import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserProfileControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserProfile userProfile;
    @MockBean
    UserProfileService userProfileService;
    @InjectMocks
    UserProfileController userProfileController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userProfileController).build();
        userProfile = new UserProfile();
        userProfile.setUserId("Jhon123");
        userProfile.setFirstName("Jhon"); 
        userProfile.setLastName("Simon");
        userProfile.setContact("9898989898");
        userProfile.setEmail("Jhon@gmail.com");
        userProfile.setCreatedAt();
    }

    @Test
    public void registerUserSuccess() throws Exception {

        when(userProfileService.registerUser(userProfile)).thenReturn(userProfile);
        mockMvc.perform(post("/api/v1/user")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(userProfile)))
                .andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void registerUserFailure() throws Exception {

        when(userProfileService.registerUser(any())).thenThrow(UserProfileAlreadyExistsException.class);
        mockMvc.perform(post("/api/v1/user")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(userProfile)))
                .andExpect(status().isConflict()).andDo(MockMvcResultHandlers.print());

    }


    @Test
    public void updateUserSuccess() throws Exception {
    	userProfile.setContact("23456789");
        when(userProfileService.updateUser(eq(userProfile.getUserId()), any())).thenReturn(userProfile);
        mockMvc.perform(put("/api/v1/userprofile/Jhon123")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(userProfile)))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void updateUserFailure() throws Exception {
    	    userProfile.setContact("23456789");
            when(userProfileService.updateUser(eq(userProfile.getUserId()), any())).thenThrow(UserProfileNotFoundException.class);
            mockMvc.perform(put("/api/v1/userprofile/Jhon123")
                    .contentType(MediaType.APPLICATION_JSON).content(asJsonString(userProfile)))
                    .andExpect(status().isNotFound()).andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void deleteUserSuccess() throws Exception {
        when(userProfileService.deleteUser("Jhon123")).thenReturn(true);
        mockMvc.perform(delete("/api/v1/userprofile/Jhon123")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }


    @Test
    public void deleteUserFailure() throws Exception {
        when(userProfileService.deleteUser("Jhon123")).thenThrow(UserProfileNotFoundException.class);
        mockMvc.perform(delete("/api/v1/userprofile/Jhon123")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(MockMvcResultHandlers.print());

    }


    @Test
    public void getByUserIdSuccess() throws Exception {

        when(userProfileService.getUserById("Jhon123")).thenReturn(userProfile);
        mockMvc.perform(get("/api/v1/userprofile/Jhon123").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getByUserIdFAilure() throws Exception {

        when(userProfileService.getUserById("Jhon123")).thenThrow(UserProfileNotFoundException.class);
        mockMvc.perform(get("/api/v1/userprofile/Jhon123").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }

    public static String asJsonString(final Object obj) {
        try {
        	ObjectMapper objmapper = new ObjectMapper();
        	objmapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        	objmapper.registerModule(new JavaTimeModule());
            return objmapper.writeValueAsString(obj);
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}