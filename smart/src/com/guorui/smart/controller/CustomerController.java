package com.guorui.smart.controller;

import java.util.List;

import com.guorui.smart.annotation.Action;
import com.guorui.smart.annotation.Controller;
import com.guorui.smart.annotation.Inject;
import com.guorui.smart.model.Customer;
import com.guorui.smart.service.CustomerService;
import com.guorui.smart.view.Param;
import com.guorui.smart.view.View;

@Controller
public class CustomerController {
	
	@Inject
	private CustomerService customerService;
	
	@Action("GET:/customer")
	public View index(Param param){
		List<Customer> customerList = customerService.getCustomerList();
		
		return new View("customer.jsp").addModel("customerList",customerList);
	}

}
