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
    
    public static final String MYSQL_PAYMENT_INSERT = "mysql.payment.insert";
    public static final String MYSQL_PAYMENT_UPDATE = "mysql.payment.update";
    public static final String MYSQL_PAYMENT_DELETE = "mysql.payment.delete";
    public static final String MYSQL_RESERVATION_INSERT = "mysql.reservation.insert";
    public static final String MYSQL_RESERVATION_UPDATE = "mysql.reservation.update";
    public static final String MYSQL_RESERVATION_DELETE = "mysql.reservation.delete";
    public static final String MYSQL_ROOM_INSERT = "mysql.room.insert";
    public static final String MYSQL_ROOM_UPDATE = "mysql.room.update";
    public static final String MYSQL_ROOM_DELETE = "mysql.room.delete";
    public static final String MYSQL_ROOM_TYPE_INSERT = "mysql.room_type.insert";
    public static final String MYSQL_ROOM_TYPE_UPDATE = "mysql.room_type.update";
    public static final String MYSQL_ROOM_TYPE_DELETE = "mysql.room_type.delete";
    public static final String MYSQL_USER_INSERT = "mysql.user.insert";
    public static final String MYSQL_USER_UPDATE = "mysql.user.update";
    public static final String MYSQL_USER_DELETE = "mysql.user.delete";
    public static final String MYSQL_USER_GET_BY_USERNAME = "mysql.user.get_by_username";
    
    public static final String USERS_USERS_ID = "users.users_id";
    public static final String USERS_USERNAME = "users.username";
    public static final String USERS_PASSWORD = "users.password";
    public static final String USERS_EMAIL = "users.email";
    public static final String USERS_PHONE_NUMBER = "users.phone_number";
    public static final String USERS_FIRST_NAME = "users.first_name";
    public static final String USERS_LAST_NAME = "users.last_name";
    public static final String USERS_PATRONYMIC = "users.patronymic";
    public static final String USERS_ROLE = "users.role";

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
