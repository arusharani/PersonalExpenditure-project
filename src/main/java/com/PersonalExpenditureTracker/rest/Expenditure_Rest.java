package com.PersonalExpenditureTracker.rest;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.PersonalExpenditureTracker.Repo.Expenditure_Repo;
import com.PersonalExpenditureTracker.Repo.Payment_Repo;
import com.PersonalExpenditureTracker.entities.Expenditure;

import io.swagger.v3.oas.annotations.Operation;

@RestController
public class Expenditure_Rest {
	@Autowired
	private Expenditure_Repo erepo;

	@GetMapping("/allexpenditure")
	public List<Expenditure> allexpenditure() {
		return erepo.findAll();
	}

	// 5
	@GetMapping("/allexpenses/tags/{tags}")
	public List<Expenditure> listAllExpenses(@PathVariable String tags) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		return erepo.findByUser_UserNameAndTagsContaining(username, tags);
	}

	// 2
	@GetMapping("/allexpenses/catCode")
	public List<Expenditure> allExpensesByCatcode(@RequestParam("code") String code) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		var page = erepo.findByUser_UserNameAndCategory_CatCode(username, code,
				PageRequest.of(0, 5, Sort.by("expid").descending()));
		return page.getContent();
	}

	// 3
	@GetMapping("/allexpenses/payment/{mode}")
	public List<Expenditure> allExpensesByPayment(@PathVariable("mode") String mode) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		var page = erepo.findByUser_UserNameAndPay_Names(username, mode, PageRequest.of(0, 5, Sort.by("expid")));
		return page.getContent();
	}

	// 4
	@GetMapping("/listAllExpense/Date")
	@Operation(description = "enter the date in yyyy-mm-dd")
	public List<Expenditure> listAllExpenseByDate(@RequestParam("date1") Date date1,
			@RequestParam("date2") Date date2) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		var v = erepo.findByUser_UserNameAndSpentOnBetween(username, date1, date2,
				PageRequest.of(0, 5, Sort.by("spentOn").descending()));
		return v.getContent();
	}

	// 6
	@GetMapping("/total/{month}")
	public List<String> getTotalAmount(@PathVariable("month") int month) {
		return erepo.totalAmount(month);

	}

	// 7
	@GetMapping("/top5expenses")
	public List<Expenditure> top5Expenses() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		return erepo.findTop2ByUser_UserNameOrderByAmountDesc(username);
	}

	// 8
	@DeleteMapping("/deleteExpenditure")
	public void deleteExp(@RequestParam("eid") int eid) {
		var v = erepo.findById(eid);
		if (v.isPresent()) {
			erepo.deleteById(eid);
		} else
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid data");
	}

	@PutMapping("/updateExpenditure")
	public Expenditure setExpId(@RequestParam("id") int id, @RequestParam("amount") double amount) {
		var exp = erepo.findById(id);
		if (exp.isPresent()) {
			var t = exp.get();
			t.setAmount(amount);
			erepo.save(t);
			return t;

		} else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Data not found ");
	}

	@Autowired
	private Payment_Repo payrepo;

	// 12
	@PostMapping("/addexpenditure")
	public Expenditure addExpenditure(@RequestBody Expenditure exp) {
		var payname = payrepo.getByCode(exp.getCode());

		if (exp.getUserName().compareTo(payname.getUserName()) == 0) {

			erepo.save(exp);
			return exp;
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "name mismatch");
		}
	}

}
