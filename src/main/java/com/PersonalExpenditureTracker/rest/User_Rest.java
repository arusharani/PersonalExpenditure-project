package com.PersonalExpenditureTracker.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PersonalExpenditureTracker.Repo.User_Repo;
import com.PersonalExpenditureTracker.entities.Users;

@RestController
public class User_Rest {
	@Autowired
	private User_Repo urepo;
	
	
	@GetMapping("/allusers")
	public List<Users> allUsers(){
		return urepo.findAll();
		
	}

}
