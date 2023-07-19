package com.PersonalExpenditureTracker.Repo;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.PersonalExpenditureTracker.entities.Expenditure;

public interface Expenditure_Repo extends JpaRepository<Expenditure, Integer>{

	List<Expenditure> findByUser_UserNameAndTagsContaining(String username,String tags);



	Page<Expenditure> findByUser_UserNameAndCategory_CatCode(String name,String cat, PageRequest of);

	Page<Expenditure> findByUser_UserNameAndPay_Names(String string, String i, PageRequest of);



	Page<Expenditure> findByUser_UserNameAndSpentOnBetween(String username,Date string1, Date string2, PageRequest of);


	@Query("select sum(e.amount), e.category.catCode from Expenditure e where month(e.spentOn)=:month group by e.category.catCode ")
	List<String> totalAmount(@Param("month") int month);



	List<Expenditure> findTop2ByUser_UserNameOrderByAmountDesc(String name);
	

}
