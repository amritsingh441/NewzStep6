package com.stackroute.userprofile.service;

import com.stackroute.userprofile.model.UserProfile;
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

public class UserProfileServiceImpl implements UserProfileService {

	/*
	 * Autowiring should be implemented for the UserProfileRepository. (Use
	 * Constructor-based autowiring) Please note that we should not create any
	 * object using the new keyword.
	 */

	/*
	 * This method should be used to save a new userprofile.Call the corresponding method
	 * of Respository interface.
	 */

    public UserProfile registerUser(UserProfile user) throws UserProfileAlreadyExistsException {
        return null;
    }

	/*
	 * This method should be used to update a existing userprofile.Call the corresponding
	 * method of Respository interface.
	 */

    @Override
    public UserProfile updateUser(String userId, UserProfile user) throws UserProfileNotFoundException {
    	return null;
    }

	/*
	 * This method should be used to delete an existing user. Call the corresponding
	 * method of Respository interface.
	 */

    @Override
    public boolean deleteUser(String userId) throws UserProfileNotFoundException {
        return false;
    }
    
	/*
	 * This method should be used to get userprofile by userId.Call the corresponding
	 * method of Respository interface.
	 */

    @Override
    public UserProfile getUserById(String userId) throws UserProfileNotFoundException {
    	return null;
    }
}
