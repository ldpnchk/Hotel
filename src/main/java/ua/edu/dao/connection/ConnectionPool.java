package ua.edu.dao.connection;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import ua.edu.util.ResourceManager;

public class ConnectionPool {
	
	private static DataSource dataSource;
	
	static {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup(
					ResourceManager.getInstance().getString(ResourceManager.CONNECTIONPOOL_JNDINAME));
			dataSource = (DataSource) envContext.lookup(
					ResourceManager.getInstance().getString(ResourceManager.CONNECTIONPOOL_DATASOURCE));
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException{
		return dataSource.getConnection();
	}

}