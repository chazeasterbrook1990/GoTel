package com.codingdojo.projectsandalgorithms.groupproject.gotel.Controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.codingdojo.projectsandalgorithms.groupproject.gotel.Services.HotelService;
import com.fasterxml.jackson.databind.JsonNode;

import jakarta.servlet.http.HttpSession;

@Controller
public class HotelController {
	
	@Autowired
	private HotelService hotelService;
	
	
	@GetMapping("/search")
	public String getHotelsByLocation(@RequestParam("q") String q, 
			Model model, 
			@RequestParam("arrival") LocalDate arrival, 
			@RequestParam("departure") LocalDate departure, 
			HttpSession session) 
					throws IOException, InterruptedException {
		Long id = (Long) session.getAttribute("loggedInUser");
		 if(id == null) {
			 return "redirect:/";
		 }
		
		String destinationId = hotelService.getDestinationId(q);
	    List<JsonNode> hotels=hotelService.getHotels(destinationId, q, arrival, departure);
	    model.addAttribute("hotels", hotels);
	    model.addAttribute("arrival",arrival);
	    model.addAttribute("departure",departure);
	    return "searchresults.jsp";
	}
	
	@GetMapping("hotel/{hotelId}/{arrival}/{departure}/details")
	public String getHotelDetails(@PathVariable("hotelId") String hotelId,
			@PathVariable("arrival") LocalDate arrival,
			@PathVariable("departure") LocalDate departure, 
			Model model, HttpSession session) 
					throws IOException, InterruptedException {
		Long id = (Long) session.getAttribute("loggedInUser");
		 if(id == null) {
			 return "redirect:/";
		 }
		
		JsonNode details = hotelService.getHotelDetails(hotelId, arrival, departure);
		model.addAttribute("details", details);
		List <JsonNode> importantInfo = new ArrayList<>();
		for(JsonNode result : details.get("hotel_important_information_with_codes")) {
			importantInfo.add(result);
		}
		
		model.addAttribute("importantInfo",importantInfo);
	    
		return "hoteldetails.jsp";
	}
	
}
