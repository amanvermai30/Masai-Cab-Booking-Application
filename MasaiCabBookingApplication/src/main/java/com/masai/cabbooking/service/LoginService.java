package com.masai.cabbooking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.masai.cabbooking.model.UserLogin;
import com.masai.cabbooking.model.USER;
import com.masai.cabbooking.repository.UserDao;

@Service
public class LoginService implements UserDetailsService {

	@Autowired
	private UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		USER uSER = null;

		uSER = userDao.findByEmail(email);

		if (uSER == null) {
			uSER = userDao.findByMobileNumber(email);
		}
		if (uSER == null) {
			throw new UsernameNotFoundException("Not user Found With Email " + email + " please Enter Valid Email");
		}
		return new UserLogin(uSER);
	}

}
