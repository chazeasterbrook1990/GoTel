package com.codingdojo.projectsandalgorithms.groupproject.gotel.Controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codingdojo.projectsandalgorithms.groupproject.gotel.Models.Booking;
import com.codingdojo.projectsandalgorithms.groupproject.gotel.Repositories.BookingRepository;
import com.codingdojo.projectsandalgorithms.groupproject.gotel.Repositories.UserRepository;
import com.codingdojo.projectsandalgorithms.groupproject.gotel.Services.BookingService;
import com.codingdojo.projectsandalgorithms.groupproject.gotel.Services.HotelService;
import com.fasterxml.jackson.databind.JsonNode;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/gotel")
public class UserController {

	@Autowired
	UserRepository userRepo;
	@Autowired
	BookingService bookingService;
	@Autowired
	BookingRepository bookingRepo;
	@Autowired
	HotelService hotelService;

	@GetMapping("/home")
	public String userHome(Model model, HttpSession session) throws IOException, InterruptedException {
		Long id = (Long) session.getAttribute("loggedInUser");
		if (id == null) {
			return "redirect:/";
		}
		model.addAttribute("userName", userRepo.findById(id).get().getUserName());
		List<Booking> bookings = bookingRepo.findAllByUserId(id);
		model.addAttribute("bookings", bookings);
		return "home.jsp";
	}

	@GetMapping("/booking/{bookingId}/edit")
	public String editPage(HttpSession session, @PathVariable("bookingId") Long bookingId, Model model)
			throws IOException, InterruptedException {

		Long id = (Long) session.getAttribute("loggedInUser");
		if (id == null) {
			return "redirect:/";
		}
		String hotelId = bookingRepo.findById(bookingId).get().getHotelId();
		LocalDate checkInDate = bookingRepo.findById(bookingId).get().getCheckInDate();
		LocalDate checkOutDate = bookingRepo.findById(bookingId).get().getCheckOutDate();

		JsonNode details = hotelService.getHotelDetails(hotelId, checkInDate, checkOutDate);
		model.addAttribute("details", details);
		List<JsonNode> importantInfo = new ArrayList<>();
		for (JsonNode result : details.get("hotel_important_information_with_codes")) {
			importantInfo.add(result);
		}

		model.addAttribute("importantInfo", importantInfo);
		model.addAttribute("arrival", checkInDate);
		model.addAttribute("departure", checkOutDate);
		model.addAttribute("booking", bookingRepo.findById(bookingId).get());

		return "editbooking.jsp";
	}

	@PostMapping("/create-booking/{hotelName}/{hotelId}/{city}")
	public String createBooking(@Valid @ModelAttribute("booking") Booking booking, BindingResult result, Model model,
			HttpSession session, @PathVariable("hotelId") String hotelId, @PathVariable("hotelName") String hotelName,
			@PathVariable("city") String city) {
		if (session.getAttribute("loggedInUser") == null) {
			return "redirect:/gotel/home";
		} else if (result.hasErrors()) {
			model.addAttribute("newBooking", new Booking());
			return "hoteldetails.jsp";
		} else {
			Long userId = (Long) session.getAttribute("loggedInUser");
			booking.setUser(userRepo.findById(userId).get());
			booking.setHotelId(hotelId);
			bookingService.create(booking);

		}

		return "redirect:/gotel/home";
	}

	@PostMapping("/booking/{bookingId}/update")
	public String editBooking(@Valid @ModelAttribute("booking") Booking booking, BindingResult result, Model model,
			HttpSession session, @PathVariable("bookingId") Long bookingId) {
		if (result.hasErrors()) {
			return "editbooking.jsp";
		}
		Booking bookingEdit = bookingRepo.findById(bookingId).get();
		bookingEdit.setCheckInDate(booking.getCheckInDate());
		bookingEdit.setCheckOutDate(booking.getCheckOutDate());
		bookingRepo.save(bookingEdit);
		return "redirect:/gotel/home";
	}

	@PostMapping("/booking/{id}/delete")
	public String deleteListing(@ModelAttribute("bookingDelete") Booking booking, @PathVariable Long id) {
		bookingRepo.deleteById(id);
		return "redirect:/gotel/home";
	}
}