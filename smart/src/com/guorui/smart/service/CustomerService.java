package com.guorui.smart.service;

import java.util.List;

import com.guorui.smart.model.Customer;
import com.guorui.smart.util.DatabaseUtil;

public class CustomerService {
	
	public List<Customer> getCustomerList(){
		String sql = "select * from customer";
		
		List<Customer> customers = DatabaseUtil.queryEntityList(Customer.class, sql, new Object[]{});
		
		return customers;
		
	}
	
	public Customer getCustomer(Integer id){
		return null;
		
	}
	
	public boolean createCustomer(Customer customer){
		return false;
		
	}
	
	public boolean updateCustomer(Customer customer){
		return false;
		
	}
	
	public boolean deleteCustomer(Integer id){
		return false;
		
	}

}
