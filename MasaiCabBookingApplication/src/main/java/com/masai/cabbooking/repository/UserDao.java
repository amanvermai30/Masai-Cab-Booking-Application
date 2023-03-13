package com.masai.cabbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.cabbooking.model.USER;
@Repository
public interface UserDao extends JpaRepository<USER, Integer> {

        
	public USER findByMobileNumber(String mobileNumber);
	public USER findByEmail(String email);

}
