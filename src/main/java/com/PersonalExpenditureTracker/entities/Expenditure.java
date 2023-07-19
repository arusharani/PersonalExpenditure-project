package com.PersonalExpenditureTracker.entities;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="expenditures")
public class Expenditure {
	@Id
	@Column(name="exp_id")
	private int expid;
	private double amount;
	private Date spentOn;
	@Column(name="descriptions")
	private String description;
	private String remarks;
	private String tags;
	@Column(name="user_name")
	private String userName;
	@Column(name="cat_code")
	private String catCode;
	private int code;
	
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="user_name",referencedColumnName = "user_name",insertable = false,updatable = false)
	private Users user;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="cat_code",referencedColumnName = "cat_code",insertable = false,updatable = false)
	private Categories category;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="code",referencedColumnName = "code",insertable = false,updatable = false)
	private Payment pay;
	
	
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCatCode() {
		return catCode;
	}
	public void setCatCode(String catCode) {
		this.catCode = catCode;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public Categories getCategory() {
		return category;
	}
	public void setCategory(Categories category) {
		this.category = category;
	}
	public Payment getPay() {
		return pay;
	}
	public void setPay(Payment pay) {
		this.pay = pay;
	}
	public int getExpid() {
		return expid;
	}
	public void setExpid(int expid) {
		this.expid = expid;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Date getSpentOn() {
		return spentOn;
	}
	public void setSpentOn(Date spentOn) {
		this.spentOn = spentOn;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	
	

}
