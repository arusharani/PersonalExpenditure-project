package com.PersonalExpenditureTracker.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PersonalExpenditureTracker.entities.Categories;

public interface CategoriesRepo extends JpaRepository<Categories, String>{

}
