package com.PersonalExpenditureTracker.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PersonalExpenditureTracker.entities.Users;

public interface User_Repo extends JpaRepository<Users, String>{
	

}
