package com.masai.cabbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.cabbooking.model.Driver;

public interface DriverDao extends JpaRepository<Driver, Integer> {

	public Driver findByMobileNumber(String mobileNumber);

	public Driver findByEmail(String email);
}
