package com.masai.cabbooking.service;

import java.util.List;

import com.masai.cabbooking.exception.CabException;
import com.masai.cabbooking.exception.DriverException;
import com.masai.cabbooking.model.CabBooking;
import com.masai.cabbooking.model.Cab;
import com.masai.cabbooking.model.Driver;

public interface DriverService {

	public Driver addDriver(Driver driver, Cab cab) throws DriverException, CabException;

	public CabBooking getDriverById(Integer id, Integer a, Integer b, String destination, String source)
			throws DriverException;

	public List<Driver> getDriver() throws DriverException;

}
