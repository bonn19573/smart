package com.guorui.smart.service;

import java.util.List;

import com.guorui.smart.model.Customer;
import com.guorui.smart.util.DatabaseUtil;

public class CustomerService {
	
	public List<Customer> getCustomerList(){
		String sql = "select * from customer";
		
		List<Customer> customers = DatabaseUtil.queryEntityList(Customer.class, sql);
		
		return customers;
		
	}
	
	public Customer getCustomer(Integer id){
		String sql = "select * from customer where id=?";
		Customer customer = DatabaseUtil.queryEntity(Customer.class, sql, new Object[]{1});
		return customer;
		
	}
	
	public boolean createCustomer(Customer customer){
		return DatabaseUtil.insert(Customer.class, customer);
		
	}
	
	public boolean updateCustomer(Customer customer){
		return false;
		
	}
	
	public boolean deleteCustomer(Integer id){
		return false;
		
	}

}
