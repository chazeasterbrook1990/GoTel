package com.codingdojo.projectsandalgorithms.groupproject.gotel.Repositories;

import java.util.List;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.projectsandalgorithms.groupproject.gotel.Models.Booking;

@Repository

public interface BookingRepository extends CrudRepository <Booking, Long> {
	
	public List <Booking> findAll();
	
	

}
