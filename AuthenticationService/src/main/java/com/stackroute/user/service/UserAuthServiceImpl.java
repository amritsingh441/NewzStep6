package com.stackroute.user.service;

import com.stackroute.user.util.exception.UserAlreadyExistsException;
import com.stackroute.user.util.exception.UserNotFoundException;
import com.stackroute.user.model.User;
import com.stackroute.user.repository.UserAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import java.util.Optional;

/*
 * Service classes are used here to implement additional business logic/validation 
 * This class has to be annotated with @Service annotation.
 * @Service - It is a specialization of the component annotation. It doesn't currently 
 * provide any additional behavior over the @Component annotation, but it's a good idea 
 * to use @Service over @Component in service-layer classes because it specifies intent 
 * better. Additionally, tool support and additional behavior might rely on it in the 
 * future.
 */
@Service
@Component
public class UserAuthServiceImpl implements UserAuthService {

	/*
	 * Autowiring should be implemented for the UserAuthRepository and SQL Operation.
	 * (Use Constructor-based autowiring) Please note that we should not create any
	 * object using the new keyword.
	 */

	private UserAuthRepository userAuthRepository;
	
	@Autowired
	public UserAuthServiceImpl (UserAuthRepository userAuthRepository) {
		this.userAuthRepository = userAuthRepository;

	}
	/*
	 * This method should be used to find an existing User with correct password.
	 */

	@Override
	public User findByUserIdAndPassword(String userId, String password) throws UserNotFoundException {
		User userObj = userAuthRepository.findByUserIdAndPassword(userId, password);
		if(userObj!=null) {
			return userObj;
		}
		throw new UserNotFoundException("User Not Found");
	}

	/*
	 * This method should be used to save a new User.
	 */

	@Override
	public boolean saveUser(User user) throws UserAlreadyExistsException {
		Optional<User> userObj = userAuthRepository.findById(user.getUserId());
		if(userObj.isPresent()) {
			throw new UserAlreadyExistsException("User Already Present");
		}
		userAuthRepository.save(user);
		return true;
	}
}