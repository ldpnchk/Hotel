package ua.edu.dao.connection;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;

import ua.edu.exception.DatabaseException;
import ua.edu.util.ConfigManager;

public class DataSource{
	
	final static Logger logger = Logger.getLogger(DataSource.class);

    private static volatile DataSource instance;
    private BasicDataSource dataSource;

    private DataSource(){
    	dataSource = new BasicDataSource();
    	dataSource.setDriverClassName(ConfigManager.getInstance().getString(ConfigManager.DB_DRIVER));
    	dataSource.setUrl(ConfigManager.getInstance().getString(ConfigManager.DB_URL));
    	dataSource.setUsername(ConfigManager.getInstance().getString(ConfigManager.DB_USER));
    	dataSource.setPassword(ConfigManager.getInstance().getString(ConfigManager.DB_PASSWORD));
    	dataSource.setMinIdle(Integer.parseInt(ConfigManager.getInstance().getString(ConfigManager.DB_MIN_IDLE)));
    	dataSource.setMaxIdle(Integer.parseInt(ConfigManager.getInstance().getString(ConfigManager.DB_MAX_IDLE)));
    	dataSource.setMaxOpenPreparedStatements(Integer.parseInt(ConfigManager.getInstance().getString(ConfigManager.DB_MAX_OPEN_PS)));
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

    public Connection getConnection(){
        try {
			return this.dataSource.getConnection();
		} catch (SQLException e) {
			logger.error("DataSource getConnection error", e);
			throw new DatabaseException();
		}
    }

}