package com.guorui.smart.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.guorui.smart.model.Customer;

public class CustomerServiceTest {
	
	private CustomerService customerService;
	
	@Before
	public void init(){
		customerService = new CustomerService();
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
		Customer customer = new Customer(3,"haha","666","145","haha@qq.com","unittest");
		customer.setEmail("haha@hotmail.com");
		customer.setName("changed");
		
		boolean updateCustomer = customerService.updateCustomer(customer);
		
		Assert.assertTrue(updateCustomer);
	}

	@Test
	public void testDeleteCustomer() {
		boolean deleteCustomer = customerService.deleteCustomer(9);
		Assert.assertTrue(deleteCustomer);
	}

}
