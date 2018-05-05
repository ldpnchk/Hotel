package ua.edu.util;

import java.util.ResourceBundle;

public class ConfigurationManager {
	
    private static volatile ConfigurationManager instance;

    private ResourceBundle resourceBundle;
    private static final String RESOURCE_NAME = "config";
    
    public static final String DAO_FACTORY = "dao.factory";
    
    public static final String DAO_FACTORY_MYSQL_KEY = "dao.factory.mysql.key";
    
    public static final String DB_DRIVER = "db.driver";
    public static final String DB_URL = "db.url";
    public static final String DB_USER = "db.user";
    public static final String DB_PASSWORD = "db.password";
    public static final String DB_MIN_IDLE = "db.min.idle";
    public static final String DB_MAX_IDLE = "db.max.idle";
    public static final String DB_MAX_OPEN_PS = "db.max.open.prepare.statements";

    private ConfigurationManager() {
    	resourceBundle = ResourceBundle.getBundle(RESOURCE_NAME);
    }

    public static ConfigurationManager getInstance() {
    	if (instance == null) {
            synchronized (ConfigurationManager.class) {
                if (instance == null) {
                    instance = new ConfigurationManager();
                }
            }
        }
        return instance;
    }

    public String getString(String key) {
        return resourceBundle.getString(key);
    }

}
