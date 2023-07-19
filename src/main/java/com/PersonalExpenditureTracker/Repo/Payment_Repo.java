package com.PersonalExpenditureTracker.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.PersonalExpenditureTracker.entities.Payment;

public interface Payment_Repo  extends JpaRepository<Payment, Integer>{

	@Query(value = "select * from payments where user_name=:name", nativeQuery = true)
	List<Payment> findByPayment(@Param("name")String name);
	
	List<Payment> findByUserNameAndCode(String username, int code);
	
	Payment getByCode(int paycode);

}
