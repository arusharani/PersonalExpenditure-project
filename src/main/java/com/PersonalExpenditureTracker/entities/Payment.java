package com.PersonalExpenditureTracker.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="payments")
public class Payment {
	@Id
	private int code;
	private String names;
	private String remarks;
	@Column(name="user_name")
	private String userName;
	
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="user_name",insertable = false,updatable = false)
	private Users users;
	
	
	@OneToMany(mappedBy = "pay")
	@JsonIgnore
	private List<Expenditure> exp;
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Users getUsers() {
		return users;
	}
	public void setUsers(Users users) {
		this.users = users;
	}
	
	public List<Expenditure> getExp() {
		return exp;
	}
	public void setExp(List<Expenditure> exp) {
		this.exp = exp;
	}
	public Users getUser() {
		return users;
	}
	public void setUser(Users users) {
		this.users = users;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getNames() {
		return names;
	}
	public void setNames(String names) {
		this.names = names;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	

}
