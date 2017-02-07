package ua.org.vr.service;

import ua.org.vr.model.User;

import java.util.List;

public interface UserService {

	User findById(int id);
	
	void saveUser(User user);
	
	void updateUser(User user);
	
	void deleteUserBySsn(String ssn);

	List<User> findAllUsers();
	
	User findUserBySsn(String ssn);

	boolean isUserSsnUnique(Integer id, String ssn);
	
}
