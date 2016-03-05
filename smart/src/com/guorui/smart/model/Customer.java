package com.guorui.smart.model;

public class Customer {
	
	private int id;
	private String name;
	private String contact;
	private String telephone;
	private String email;
	private String remark;
	
	
	public Customer() {
	}
	public Customer(int id, String name, String contact, String telephone, String email, String remark) {
		this.id = id;
		this.name = name;
		this.contact = contact;
		this.telephone = telephone;
		this.email = email;
		this.remark = remark;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", contact=" + contact + ", telephone=" + telephone + ", email=" + email + ", comment=" + remark + "]";
	}
	

}
