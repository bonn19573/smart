package com.guorui.smart.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatabaseUtil {
	
	private static final QueryRunner QUERY_RUNNER = new QueryRunner();
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseUtil.class);
	private static String DRIVER_CLASS;
	private static String URL;
	private static String USERNAME;
	private static String PASSWORD;
	
	static {
		try {
			Properties p = PropsUtil.loadProps("config.properties");
			DRIVER_CLASS = p.getProperty("jdbc.driver_class");
			URL = p.getProperty("jdbc.url");
			USERNAME = p.getProperty("jdbc.username");
			PASSWORD = p.getProperty("jdbc.password");
			
			//Class.forName(DRIVER_CLASS);
		} catch (IOException e) {
			LOGGER.error("Load properties file failed",e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
//		catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}
	
	public static Connection getConnection(){
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	
	public static void closeConnection(Connection connection){
		if(connection != null){
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			} finally{
				connection = null;
			}
		}
	}
	
	public static <T> List<T> queryEntityList(Class<T> entityClass, String sql, Object...params){
		Connection conn = getConnection();
		List<T> list = null;
		try {
			list = QUERY_RUNNER.query(conn, sql, new BeanListHandler<>(entityClass),params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			closeConnection(conn);
		}
		
		return list;
	}

}
