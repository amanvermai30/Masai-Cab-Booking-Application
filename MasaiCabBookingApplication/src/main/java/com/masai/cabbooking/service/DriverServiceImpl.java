package com.masai.cabbooking.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import com.masai.cabbooking.exception.CabException;
import com.masai.cabbooking.exception.DriverException;
import com.masai.cabbooking.model.CabBooking;
import com.masai.cabbooking.model.Cab;
import com.masai.cabbooking.model.UserLogin;
import com.masai.cabbooking.model.Driver;
import com.masai.cabbooking.model.USER;
import com.masai.cabbooking.repository.CabBookingDao;
import com.masai.cabbooking.repository.CabDao;
import com.masai.cabbooking.repository.DriverDao;
import com.masai.cabbooking.repository.UserDao;

public class DriverServiceImpl implements DriverService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private CabDao cabDao;

	@Autowired
	private DriverDao driverDao;

	@Autowired
	private CabBookingDao cabBookingDao;

	@Override
	public Driver addDriver(Driver driver, Cab cab) throws DriverException, CabException {

		Driver output = null;

		Driver driver3 = driverDao.findByEmail(driver.getEmail());

		if (driver3 != null) {
			throw new DriverException("Driver Is Alrady Is Registered By By Given Email");
		} else {
			Cab cab1 = cabDao.findByLicencePlate(cab.getLicenceNumber());
			if (cab1 != null) {
				throw new CabException("Cab Is Alrady Registered");
			} else {
				driver.setCab(cab);
				cab.setDriver(driver);
				output = driverDao.save(driver);
			}
		}
		return output;
	}

	@Override
	public CabBooking getDriverById(Integer id, Integer a, Integer b, String destination, String source)
			throws DriverException {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		UserLogin cd = (UserLogin) principal;
		USER u = userDao.findByEmail(cd.getUsername());
		Driver t = driverDao.findById(id).orElse(null);
		if (t == null) {
			throw new DriverException("No driver found with id " + id);
		}

		Integer x = t.getCurrentPosition()[0];
		Integer y = t.getCurrentPosition()[1];
		Integer x2 = u.getCurrentPosition()[0];
		Integer y2 = u.getCurrentPosition()[1];

		boolean isInLimit = Math.sqrt((y2 - y) * (y2 - y) + (x2 - x) * (x2 - x)) <= 5;

		if (!isInLimit) {
			throw new DriverException("Driver is far away from your position ");
		}
		Integer[] arr = { a, b };
		u.setCurrentPosition(arr);
		t.setCurrentPosition(arr);

		userDao.save(u);

		CabBooking cabBooking = new CabBooking();
		cabBooking.setDestination(destination);
		cabBooking.setCab(t.getCab());
		cabBooking.setReservationDate(LocalDate.now());
		cabBooking.setSource(source);
		cabBooking.setUser(u);

		u.getCabBookings().add(cabBooking);

		userDao.save(u);
		CabBooking output = cabBookingDao.save(cabBooking);

		return output;
	}

	@Override
	public List<Driver> getDriver() throws DriverException {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserLogin cd = (UserLogin) principal;
		USER u = userDao.findByEmail(cd.getUsername());

		List<Driver> list = driverDao.findAll();

		if (list == null || list.size() == 0) {
			throw new DriverException("No driver Available");
		}

		List<Driver> output = list.stream().filter(s -> {

			Integer x = s.getCurrentPosition()[0];
			Integer y = s.getCurrentPosition()[1];
			Integer x2 = u.getCurrentPosition()[0];
			Integer y2 = u.getCurrentPosition()[1];
			return Math.sqrt((y2 - y) * (y2 - y) + (x2 - x) * (x2 - x)) <= 5;

		}).collect(Collectors.toList());

		if (output == null || output.size() == 0) {
			throw new DriverException("No driver Available");
		}
		return output;

	}

}
