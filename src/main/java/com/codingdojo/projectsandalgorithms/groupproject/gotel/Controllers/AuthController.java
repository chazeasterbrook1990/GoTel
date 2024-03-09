package com.codingdojo.projectsandalgorithms.groupproject.gotel.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.codingdojo.projectsandalgorithms.groupproject.gotel.Models.LoginUser;
import com.codingdojo.projectsandalgorithms.groupproject.gotel.Models.User;
import com.codingdojo.projectsandalgorithms.groupproject.gotel.Repositories.UserRepository;
import com.codingdojo.projectsandalgorithms.groupproject.gotel.Services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class AuthController {

	@Autowired
	public UserService userService;
	@Autowired
	public UserRepository userRepo;

	@GetMapping("/")
	public String index(Model model, HttpSession session) {
		if (session.getAttribute("loggedInUser") != null) {
			return "redirect:/gotel/home";
		}
		model.addAttribute("newLogin", new LoginUser());
		model.addAttribute("newUser", new User());
		return "index.jsp";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("newUser") User newUser, BindingResult result, Model model,
			HttpSession session) {

		if (result.hasErrors() || userService.register(newUser, result) != newUser) {
			model.addAttribute("newLogin", new LoginUser());
			return "index.jsp";
		} else if (session.getAttribute("loggedInUser") != null) {
			return "redirect:/gotel/home";
		} else {
			userService.register(newUser, result);
			session.setAttribute("loggedInUser", newUser.getId());
		}
		return "redirect:/gotel/home";
	}

	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, BindingResult result, Model model,
			HttpSession session) {

		User user = userService.login(newLogin, result);

		if (result.hasErrors()) {
			model.addAttribute("newUser", new User());
			return "index.jsp";
		}
		userService.login(newLogin, result);
		session.setAttribute("loggedInUser", user.getId());
		return "redirect:/gotel/home";
	}

}