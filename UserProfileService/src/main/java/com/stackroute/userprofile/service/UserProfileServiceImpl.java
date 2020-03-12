package com.stackroute.userprofile.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.stackroute.userprofile.model.UserProfile;
import com.stackroute.userprofile.repository.UserProfileRepository;
import com.stackroute.userprofile.util.exception.UserProfileAlreadyExistsException;
import com.stackroute.userprofile.util.exception.UserProfileNotFoundException;

/*
 * Service classes are used here to implement additional business logic/validation 
 * This class has to be annotated with @Service annotation.
 * @Service - It is a specialization of the component annotation. It doesn't currently 
 * provide any additional behavior over the @Component annotation, but it's a good idea 
 * to use @Service over @Component in service-layer classes because it specifies intent 
 * better. Additionally, tool support and additional behavior might rely on it in the 
 * future.
 * */

@Service
@Component
public class UserProfileServiceImpl implements UserProfileService {

	/*
	 * Autowiring should be implemented for the UserProfileRepository. (Use
	 * Constructor-based autowiring) Please note that we should not create any
	 * object using the new keyword.
	 */
	private UserProfileRepository userProfileRepository;
	
	@Autowired
	public UserProfileServiceImpl (UserProfileRepository userProfileRepository) {
		this.userProfileRepository = userProfileRepository;
	}
	/*
	 * This method should be used to save a new userprofile.Call the corresponding method
	 * of Respository interface.
	 */

	public UserProfile registerUser(UserProfile user) throws UserProfileAlreadyExistsException {
		
		UserProfile uProf = userProfileRepository.insert(user);
		if(uProf==null) {
			throw new UserProfileAlreadyExistsException("User Profile already existed");
		}
		return uProf;
	}

	/*
	 * This method should be used to update a existing userprofile.Call the corresponding
	 * method of Respository interface.
	 */

	@Override
	public UserProfile updateUser(String userId, UserProfile user) throws UserProfileNotFoundException {
		Optional<UserProfile> uProf = userProfileRepository.findById(userId);
		if(uProf.isPresent()) {
			 userProfileRepository.save(user);
			 return user;
		}
			throw new UserProfileNotFoundException("User Profile Not Found");
	}

	/*
	 * This method should be used to delete an existing user. Call the corresponding
	 * method of Respository interface.
	 */

	@Override
	public boolean deleteUser(String userId) throws UserProfileNotFoundException {
		Optional<UserProfile> uProf = userProfileRepository.findById(userId);
		if(uProf.isPresent()) {
			userProfileRepository.delete(uProf.get());
			return true;
		}
		return false;
	}

	/*
	 * This method should be used to get userprofile by userId.Call the corresponding
	 * method of Respository interface.
	 */

	@Override
	public UserProfile getUserById(String userId) throws UserProfileNotFoundException {
		Optional<UserProfile> uProf = userProfileRepository.findById(userId);
		if(uProf.isPresent()) {
			return uProf.get();
		}
		return null;
	}
}