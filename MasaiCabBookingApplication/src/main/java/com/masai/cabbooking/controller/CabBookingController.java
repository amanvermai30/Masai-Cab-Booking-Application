package com.masai.cabbooking.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.masai.cabbooking.exception.UserException;
import com.masai.cabbooking.model.USER;
import com.masai.cabbooking.service.UserService;

@RestController
public class CabBookingController {

	@Autowired
	private UserService userService;

	public ResponseEntity<USER> addUser(USER user) throws UserException {
		USER output = userService.addUser(user);
		return new ResponseEntity<USER>(output, HttpStatus.CREATED);
	}

	public ResponseEntity<List<USER>> getUser() throws UserException {
		List<USER> output = userService.getUserList();
		return new ResponseEntity<List<USER>>(output, HttpStatus.OK);
	}

	public ResponseEntity<USER> updateUser(USER user) throws UserException {
		USER output = userService.updateUser(user);
		return new ResponseEntity<USER>(output, HttpStatus.OK);
	}

}
