package com.guorui.smart.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.guorui.smart.model.Customer;
import com.guorui.smart.util.DatabaseUtil;

public class CustomerService {

	private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);

	private final QueryRunner queryRunner = new QueryRunner();

	public List<Customer> getCustomerList() {
		String sql = "select * from customer";

		List<Customer> customers = DatabaseUtil.queryEntityList(Customer.class, sql);

		return customers;

	}

	public Customer getCustomer(Integer id) {
		String sql = "select * from customer where id=?";
		Customer customer = DatabaseUtil.queryEntity(Customer.class, sql, new Object[] { 1 });
		return customer;

	}

	public Customer createCustomer(Customer customer) {
		Customer cus = null;
		Integer maxId = 0;
		try {
			maxId = DatabaseUtil.QUERY_RUNNER.query(DatabaseUtil.getConnection(), "select max(id) from customer", rs -> {
				int re = 0;
				if (rs.next()) {
					re = rs.getInt(1);
				}
				return re;
			});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		logger.info(maxId.toString());
		customer.setId(maxId + 1);
		DatabaseUtil.insert(customer);
		
		cus = DatabaseUtil.queryEntity(Customer.class, "select * from customer where id=?", customer.getId());
		
		return cus;

	}

	public boolean updateCustomer(Customer customer) {
		return DatabaseUtil.update(customer, new Integer(customer.getId()));

	}

	public boolean deleteCustomer(Integer id) {
		return DatabaseUtil.delete(Customer.class, id);

	}

}
