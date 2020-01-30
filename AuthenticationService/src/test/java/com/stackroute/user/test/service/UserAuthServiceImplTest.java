package com.stackroute.user.test.service;

import com.stackroute.user.util.exception.UserAlreadyExistsException;
import com.stackroute.user.util.exception.UserNotFoundException;
import com.stackroute.user.model.User;
import com.stackroute.user.repository.UserAuthRepository;
import com.stackroute.user.service.UserAuthServiceImpl;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

public class UserAuthServiceImplTest {

    @Mock
    private UserAuthRepository userAuthRepository;

    private User user;
    @InjectMocks
    private UserAuthServiceImpl userAuthServiceImpl;

    Optional<User> optional;


    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        user = new User();
        user.setUserId("Jhon123");
        user.setPassword("123456");
        user.setCpassword("123456");
        optional = Optional.of(user);
    }

    @Test
    public void testSaveUserSuccess() throws UserAlreadyExistsException {

        Mockito.when(userAuthRepository.save(user)).thenReturn(user);
        boolean flag = userAuthServiceImpl.saveUser(user);
        assertEquals(true, flag);

    }


    @Test
    public void testSaveUserFailure() {

        Mockito.when(userAuthRepository.findById("Jhon123")).thenReturn(optional);
        Mockito.when(userAuthRepository.save(user)).thenReturn(user);
        assertThrows(
        		UserAlreadyExistsException.class,
                    () -> { userAuthServiceImpl.saveUser(user); });

    }

    @Test
    public void testFindByUserIdAndPassword() throws UserNotFoundException {
        Mockito.when(userAuthRepository.findByUserIdAndPassword("Jhon123", "123456")).thenReturn(user);
        User fetchedUser = userAuthServiceImpl.findByUserIdAndPassword("Jhon123", "123456");
        assertEquals("Jhon123", fetchedUser.getUserId());
    }
}
