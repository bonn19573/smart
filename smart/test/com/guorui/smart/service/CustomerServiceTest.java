package com.guorui.smart.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.guorui.smart.model.Customer;
import com.guorui.smart.util.DatabaseUtil;

public class CustomerServiceTest {
	
	private CustomerService customerService = new CustomerService();
	
	@Before
	public void init() throws IOException{
		String file = "sql/customer_init.sql";
		DatabaseUtil.executeSqlFile(file);
	}

	@Test
	public void testGetCustomerList() {
		List<Customer> customerList = customerService.getCustomerList();
		
		customerList.stream().forEach(System.out::println);
		Assert.assertEquals(2, customerList.size());
	}

	@Test
	public void testGetCustomer() {
		Customer customer = customerService.getCustomer(1);
		
		Assert.assertEquals("Guo Rui", customer.getName());
	}

	@Test
	public void testCreateCustomer() {
		Customer customer = new Customer(3,"haha","666","145","haha@qq.com","unittest");
		
		Customer createCustomer = customerService.createCustomer(customer);
		
		Assert.assertEquals(customer, createCustomer);
		
	}

	@Test
	public void testUpdateCustomer() {
		Customer customer = new Customer(2,"haha","666","145","haha@qq.com","unittest");
		customer.setEmail("haha@hotmail.com");
		customer.setName("changed");
		
		boolean updateCustomer = customerService.updateCustomer(customer);
		
		Assert.assertTrue(updateCustomer);
	}

	@Test
	public void testDeleteCustomer() {
		boolean deleteCustomer = customerService.deleteCustomer(2);
		Assert.assertTrue(deleteCustomer);
	}

}
