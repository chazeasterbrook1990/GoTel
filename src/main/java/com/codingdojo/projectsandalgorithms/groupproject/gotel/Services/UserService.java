package com.codingdojo.projectsandalgorithms.groupproject.gotel.Services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.codingdojo.projectsandalgorithms.groupproject.gotel.Models.LoginUser;
import com.codingdojo.projectsandalgorithms.groupproject.gotel.Models.User;
import com.codingdojo.projectsandalgorithms.groupproject.gotel.Repositories.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepo;
    
    public User register(User newUser, BindingResult result) {
        String enteredPassword = newUser.getPassword();
    	
    	Optional<User> potentialUser = userRepo.findByEmail(newUser.getEmail());
    	if(potentialUser.isPresent()) {
    		result.rejectValue("email", "User.exists", "You already have an account!");
    	}
      
        if(!enteredPassword.equals(newUser.getConfirm())) {
    	    result.rejectValue("confirm", "Matches", "The Confirm Password must match Password!");
    	}
       
        if(result.hasErrors()) {
    		return null;
    	}
        
        String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
        newUser.setPassword(hashed);
        
        return userRepo.save(newUser);
    }
    
        public User login(LoginUser newLoginObject, BindingResult result) {
     
        	String enteredPassword = newLoginObject.getPassword();

        	Optional<User> potentialUser = userRepo.findByEmail(newLoginObject.getEmail());

	        if(!potentialUser.isPresent()) {
	        	result.rejectValue("email", "not.exists", "User does not exist!");
	        	return null;
	        }
	        User user = potentialUser.get();
	
	        if(!BCrypt.checkpw(enteredPassword, user.getPassword())) {
	            result.rejectValue("password", "Matches", "Invalid Password!");
	        }
	    	if(result.hasErrors()) {
	    		return null;
	    	}
	    	else {
	    		return user;
	    	}
    }
        
       
}