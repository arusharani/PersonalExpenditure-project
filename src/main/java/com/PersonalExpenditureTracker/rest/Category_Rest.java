package com.PersonalExpenditureTracker.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PersonalExpenditureTracker.Repo.CategoriesRepo;
import com.PersonalExpenditureTracker.entities.Categories;

@RestController
public class Category_Rest {
	@Autowired
	private CategoriesRepo crepo;
	//11
	@GetMapping("/allcategories")
	public List<Categories> allcategories(){
		return crepo.findAll();
	}

}
