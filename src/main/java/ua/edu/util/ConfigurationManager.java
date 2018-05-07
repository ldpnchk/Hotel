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
    public static final String MYSQL_RESERVATION_GET_BY_ID_WITH_USER_AND_ROOM_TYPE_AND_ROOM_AND_PAYMENT = "mysql.reservation.get_by_id_with_user_and_room_type_and_room_and_payment";
    public static final String MYSQL_ROOM_INSERT = "mysql.room.insert";
    public static final String MYSQL_ROOM_UPDATE = "mysql.room.update";
    public static final String MYSQL_ROOM_DELETE = "mysql.room.delete";
	public static final String MYSQL_ROOM_TYPE_GET_FREE_ROOM_TYPES_BY_DATES_AND_CAPACITY = "mysql.room_type.get_free_room_types_by_dates_and_capacity";
    public static final String MYSQL_ROOM_TYPE_INSERT = "mysql.room_type.insert";
    public static final String MYSQL_ROOM_TYPE_UPDATE = "mysql.room_type.update";
    public static final String MYSQL_ROOM_TYPE_DELETE = "mysql.room_type.delete";
    public static final String MYSQL_USER_INSERT = "mysql.user.insert";
    public static final String MYSQL_USER_UPDATE = "mysql.user.update";
    public static final String MYSQL_USER_DELETE = "mysql.user.delete";
    public static final String MYSQL_USER_GET_BY_USERNAME = "mysql.user.get_by_username";
  
    public static final String PAYMENT_PAYMENT_ID = "payment.payment_id";
    public static final String PAYMENT_TOTAL = "payment.total";
    public static final String PAYMENT_DATE = "payment.date";
    public static final String PAYMENT_PAYMENT_METHOD = "payment.payment_method";
    public static final String PAYMENT_RESERVATION_ID = "payment.reservation_id";
    
    public static final String RESERVATION_RESERVATION_ID = "reservation.reservation_id";
    public static final String RESERVATION_RESERVATION_DATE = "reservation.reservation_date";
    public static final String RESERVATION_START_DATE = "reservation.start_date";
    public static final String RESERVATION_END_DATE = "reservation.end_date";
    public static final String RESERVATION_STATUS = "reservation.status";
    public static final String RESERVATION_CLIENT_COMMENT = "reservation.client_comment";
    public static final String RESERVATION_ADMINISTRATOR_COMMENT = "reservation.administrator_comment";

    public static final String ROOM_ROOM_ID = "room.room_id";
    public static final String ROOM_ROOM_NUMBER = "room.room_number";
    public static final String ROOM_ROOM_TYPE_ID = "room.room_type_id";
    
    public static final String ROOM_TYPE_ROOM_TYPE_ID = "room_type.room_type_id";
    public static final String ROOM_TYPE_NAME = "room_type.name";
    public static final String ROOM_TYPE_CAPACITY = "room_type.capacity";
    public static final String ROOM_TYPE_PRICE = "room_type.price";
    public static final String ROOM_TYPE_DESCRIPTION = "room_type.description";
    
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
