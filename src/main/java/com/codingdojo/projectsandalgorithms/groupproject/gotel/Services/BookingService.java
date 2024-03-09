package com.codingdojo.projectsandalgorithms.groupproject.gotel.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.projectsandalgorithms.groupproject.gotel.Models.Booking;
import com.codingdojo.projectsandalgorithms.groupproject.gotel.Repositories.BookingRepository;

@Service

public class BookingService {

	@Autowired
	private BookingRepository repoBooking;

	// Get All
	public List<Booking> all() {
		return repoBooking.findAll();
	}

	// Find One By ID
	public Booking findOne(Long id) {
		return repoBooking.findById(id).orElse(null);
	}

	// Create
	public Booking create(Booking booking) {
		return repoBooking.save(booking);
	}

	// Update
	public Booking edit(Booking booking) {
		return repoBooking.save(booking);
	}

	// Delete
	public void destroy(Long id) {
		repoBooking.deleteById(id);
	}

}
