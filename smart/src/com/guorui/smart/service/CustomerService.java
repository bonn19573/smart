package com.guorui.smart.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.guorui.smart.model.Customer;
import com.guorui.smart.util.DatabaseUtil;

public class CustomerService {
	
	public List<Customer> getCustomerList(){
		String sql = "select * from customer";
		
		List<Customer> customers = DatabaseUtil.queryEntityList(Customer.class, sql, new Object[]{});
		
//		List<Customer> customers = new ArrayList<Customer>();
//		
//		Connection connection = DatabaseUtil.getConnection();
//		try {
//			Statement statement = connection.createStatement();
//			ResultSet rs = statement.executeQuery(sql);
//			
//			while(rs.next()){
//				Customer customer = new Customer();
//				customer.setId(rs.getInt("id"));
//				customer.setName(rs.getString("name"));
//				customer.setContact(rs.getString("contact"));
//				customer.setTelephone(rs.getString("telephone"));
//				customer.setEmail(rs.getString("email"));
//				customer.setComment(rs.getString("remark"));
//				customers.add(customer);
//			}
//			
//			rs.close();
//			statement.close();
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally{
//			DatabaseUtil.closeConnection(connection);
//		}
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
