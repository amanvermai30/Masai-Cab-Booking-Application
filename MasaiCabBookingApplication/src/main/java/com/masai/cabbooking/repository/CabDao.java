package com.masai.cabbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.cabbooking.model.Cab;

public interface CabDao extends JpaRepository<Cab, Integer> {

	public Cab findByLicencePlate(String licencePlate);

}
