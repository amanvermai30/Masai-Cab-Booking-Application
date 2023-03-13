package com.masai.cabbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.cabbooking.model.CabBooking;

@Repository
public interface CabBookingDao extends JpaRepository<CabBooking, Integer>{

}
