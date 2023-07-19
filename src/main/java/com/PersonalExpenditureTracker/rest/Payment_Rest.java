package com.PersonalExpenditureTracker.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.PersonalExpenditureTracker.Repo.Payment_Repo;
import com.PersonalExpenditureTracker.entities.Payment;

@RestController
public class Payment_Rest {
	@Autowired
	private Payment_Repo prepo;
	

	//10
	@GetMapping("/allpayments")
	public List<Payment> allPaymentsOfUser(){
		String username=SecurityContextHolder.getContext().getAuthentication().getName();
		return prepo.findByPayment(username);
	}
	//9//1
	@PostMapping("/add/payment")
	public Payment addPayment(@RequestBody Payment p) {
		String username=SecurityContextHolder.getContext().getAuthentication().getName();
		
		var paycode=prepo.findById(p.getCode());
		if(paycode.isPresent())
		{
			throw new ResponseStatusException(HttpStatus.ALREADY_REPORTED,"code is already present");
		}
		else {
			
			if(p.getUserName().equals(username))
			{	
				prepo.save(p);
				return p;
			}
			else {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"invalid data");
			}
		}
	}
	
	@DeleteMapping("/deletePayment/{code}")
	public void deletePay(@PathVariable("code") int code) {
		String username=SecurityContextHolder.getContext().getAuthentication().getName();
		var v = prepo.findByUserNameAndCode(username, code);
		if(v.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Invalid data");
		}
		else prepo.deleteById(code);
	}
	
	@PutMapping("/updatePayment/{code}/{pname}")
	public void updatePay(@PathVariable("code") int code, @PathVariable("pname") String payname)
	{
		String username=SecurityContextHolder.getContext().getAuthentication().getName();
		var a = prepo.findById(code);
		if(a.isPresent())
		{
			var b = a.get();
			if(b.getUser().getUserName().equals(username))
			{
				b.setNames(payname);
				prepo.save(b);
			}
		}
	}
	
}
