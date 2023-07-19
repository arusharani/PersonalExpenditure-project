package com.PersonalExpenditureTracker.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="category")
public class Categories {
	
	@Id
	@Column(name="cat_code")
	private String catCode;
	private String catName;
	
	@OneToMany(mappedBy = "category")
	@JsonIgnore
	private List<Expenditure> exp;
	
	
	public List<Expenditure> getExp() {
		return exp;
	}
	public void setExp(List<Expenditure> exp) {
		this.exp = exp;
	}
	public String getCatCode() {
		return catCode;
	}
	public void setCatCode(String catCode) {
		this.catCode = catCode;
	}
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}
	
	

}
