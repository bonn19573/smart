package com.guorui.smart.util;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatabaseUtil {

	public static final QueryRunner QUERY_RUNNER = new QueryRunner();

	private static final ThreadLocal<Connection> CONN = new ThreadLocal<>();

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

			// Class.forName(DRIVER_CLASS);
		} catch (IOException e) {
			LOGGER.error("Load properties file failed", e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// catch (ClassNotFoundException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

	}

	public static Connection getConnection() {

		Connection connection = CONN.get();

		if (connection == null) {
			try {
				connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				CONN.set(connection);
			}
		}
		return connection;
	}

	public static void closeConnection() {
		Connection connection = CONN.get();
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			} finally {
				connection = null;
			}
		}
	}

	public static <T> List<T> queryEntityList(Class<T> entityClass, String sql, Object... params) {
		Connection conn = getConnection();
		List<T> list = null;
		try {
			list = QUERY_RUNNER.query(conn, sql, new BeanListHandler<>(entityClass), params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnection();
		}

		return list;
	}

	public static <T> T queryEntity(Class<T> entityClass, String sql, Object... params) {
		T entity = null;

		try {
			entity = QUERY_RUNNER.query(getConnection(), sql, new BeanHandler<>(entityClass), params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
//			closeConnection();
		}

		return entity;
	}

	public static <T> int executeUpdate(Class<T> entityClass, String sql, Object... params) {
		int rows = 0;

		try {
			rows = QUERY_RUNNER.update(getConnection(), sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		closeConnection();

		return rows;
	}

	public static <T> boolean insert(T entity) {
		Class<?> entityClass = entity.getClass();
		StringBuilder sql1 = new StringBuilder("insert into " + entityClass.getSimpleName() + " (");
		StringBuilder sql2 = new StringBuilder(" values (");
		Object[] params = new Object[entityClass.getDeclaredFields().length];

		Field[] fields = entityClass.getDeclaredFields();

		int index = 0;
		for (Field field : fields) {
			field.setAccessible(true);
			LOGGER.info("field:" + field.getName());
			sql1.append(field.getName() + ",");
			sql2.append("?,");
			try {
				params[index] = field.get(entity);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			index++;
		}

		LOGGER.info("111111:" + sql1.toString());

		int lastIndexOf = sql1.lastIndexOf(",");
		sql1 = sql1.replace(lastIndexOf, sql1.length(), ") ");
		int lastIndexOf2 = sql2.lastIndexOf(",");
		sql2.replace(lastIndexOf2, sql2.length(), ") ");

		sql1.append(sql2);

		LOGGER.info("sql1:" + sql1);
		LOGGER.info("params:" + params);

		try {
			QUERY_RUNNER.insert(getConnection(), sql1.toString(), new BeanHandler<>(entityClass), params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
//			closeConnection();
		}

		return true;
	}
	
	public static <T,I> boolean update(T entity,I id){
		Class<?> entityClass = entity.getClass();
		
		T entity2 = (T) queryEntity(entityClass, "select * from "+entityClass.getSimpleName()+" where id=?", id);
		if(entity2 == null){
			throw new RuntimeException("record not exist in database, cannot update");
		}
		
		StringBuilder stringBuilder = new StringBuilder("update "+entityClass.getSimpleName() +" set ");
		Object[] params = new Object[entityClass.getDeclaredFields().length];
		Field[] declaredFields = entityClass.getDeclaredFields();
		int index = 0;
		for (Field field : declaredFields) {
			field.setAccessible(true);
			if(!field.getName().equals("id")){
				stringBuilder.append(field.getName()+"=?, ");
				try {
					params[index++] = field.get(entity);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		}
		int lastIndexOf = stringBuilder.lastIndexOf(",");
		stringBuilder.replace(lastIndexOf, stringBuilder.length(), "");
		stringBuilder.append(" where id=?");
		params[index++] = id;
		
		LOGGER.info("update sql:"+stringBuilder.toString());
		LOGGER.info("params:"+params);
		
		int update = 0;
		try {
			update = QUERY_RUNNER.update(getConnection(), stringBuilder.toString(), params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return update>0;
	}
	
	
	public static <T,I> boolean delete(Class<T> entityClass,I id){
		int update = 0;
		try {
			update = QUERY_RUNNER.update(getConnection(), "delete from "+entityClass.getSimpleName()+" where id=?", id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return update>0;
	}



}
