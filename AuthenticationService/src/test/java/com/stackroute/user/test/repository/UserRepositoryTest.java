package com.stackroute.user.test.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.stackroute.user.model.User;
import com.stackroute.user.repository.UserAuthRepository;

import org.mockito.MockitoAnnotations;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    
    @Autowired
    private UserAuthRepository userAuthRepository;
    
    private User user;
    
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        user = new User();
        user.setUserId("100");
        user.setPassword("123456");
        user.setCpassword("123456");
    }

    @AfterEach
    public void tearDown() throws Exception {
    	userAuthRepository.deleteAll();
    }


    @Test
    public void testRegisterUserSuccess() {
    	userAuthRepository.save(user);
        User fetchUser = userAuthRepository.findById(user.getUserId()).get();
        assertThat(user.getUserId(), is(fetchUser.getUserId()));
    }

    @Test
    public void testLoginUserSuccess() {
    	userAuthRepository.save(user);
        User fetchUser = userAuthRepository.findById(user.getUserId()).get();
        assertThat(user.getUserId(), is(fetchUser.getUserId()));
    }

}
