package com.codingdojo.projectsandalgorithms.groupproject.gotel.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codingdojo.projectsandalgorithms.groupproject.gotel.Repositories.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/gotel")
public class UserController {
	
	@Autowired
	UserRepository userRepo;
	
	@GetMapping("/home")
	public String userHome(Model model, HttpSession session) {
		Long id = (Long) session.getAttribute("loggedInUser");
		 if(id == null) {
			 return "redirect:/";
		 }
		 model.addAttribute("userName", userRepo.findById(id).get().getUserName());
		return "home.jsp";
	}

}