package com.masai.cabbooking.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.masai.cabbooking.exception.UserException;
import com.masai.cabbooking.model.USER;
import com.masai.cabbooking.repository.UserDao;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public USER addUser(USER user) throws UserException {

		USER output = null;

		output = userDao.findByEmail(user.getEmail());

		if (output != null) {
			throw new UserException("with this email user is already present");

		} else {
			output = userDao.save(user);
		}
		return output;
	}

	@Override
	public List<USER> getUserList() throws UserException {

		List<USER> allUsers = userDao.findAll();

		if (allUsers.isEmpty()) {

			throw new UserException("currently user is not available ! try after some time");
		}

		return allUsers;

	}

	@Override
	public USER updateUser(USER user) throws UserException {

		USER output = null;

		USER getUser = userDao.findByEmail(user.getEmail());

		if (getUser == null) {

			throw new UserException(" with this id user is not present ");

		} else {

			output = userDao.save(user);

		}

		return output;
	}

}
