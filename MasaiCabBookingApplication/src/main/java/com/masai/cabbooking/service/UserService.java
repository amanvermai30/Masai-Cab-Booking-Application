package com.masai.cabbooking.service;

import java.util.List;

import com.masai.cabbooking.exception.UserException;
import com.masai.cabbooking.model.USER;


public interface UserService {

	public USER addUser(USER uSER) throws UserException;

	public List<USER> getUserList() throws UserException;

	public USER updateUser(USER user) throws UserException;

}
