package ua.edu.dao.connection;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;

import ua.edu.util.ConfigurationManager;

public class DataSource{

    private static volatile DataSource instance;
    private BasicDataSource dataSource;

    private DataSource(){
    	dataSource = new BasicDataSource();
    	dataSource.setDriverClassName(ConfigurationManager.getInstance().getString(ConfigurationManager.DB_DRIVER));
    	dataSource.setUrl(ConfigurationManager.getInstance().getString(ConfigurationManager.DB_URL));
    	dataSource.setUsername(ConfigurationManager.getInstance().getString(ConfigurationManager.DB_USER));
    	dataSource.setPassword(ConfigurationManager.getInstance().getString(ConfigurationManager.DB_PASSWORD));
    	dataSource.setMinIdle(Integer.parseInt(ConfigurationManager.getInstance().getString(ConfigurationManager.DB_MIN_IDLE)));
    	dataSource.setMaxIdle(Integer.parseInt(ConfigurationManager.getInstance().getString(ConfigurationManager.DB_MAX_IDLE)));
    	dataSource.setMaxOpenPreparedStatements(Integer.parseInt(ConfigurationManager.getInstance().getString(ConfigurationManager.DB_MAX_OPEN_PS)));
    }

    public static DataSource getInstance(){
        if(instance == null){
            synchronized (DataSource.class){
                if(instance == null){
                	instance = new DataSource();
                }
            }
        }
        return instance;
    }

    public Connection getConnection() throws SQLException{
        return this.dataSource.getConnection();
    }

}