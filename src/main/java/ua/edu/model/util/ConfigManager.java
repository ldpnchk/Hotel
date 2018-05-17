package ua.edu.model.util;

import java.util.ResourceBundle;

public class ConfigManager {

    private static volatile ConfigManager instance;

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
    public static final String MYSQL_RESERVATION_GET_BY_USER = "mysql.reservation.get.by.user";
    public static final String MYSQL_RESERVATION_GET_BY_DATES_AND_ROOM_AND_STATUS_WITH_ROOM_TYPE_AND_ROOM = "mysql.reservation.get.by.dates.and.room.and.status.with.room.type.and.room";
    public static final String MYSQL_RESERVATION_GET_BY_ID_WITH_USER_AND_ROOM_TYPE_AND_ROOM_AND_PAYMENT = "mysql.reservation.get.by.id.with.user.and.room.type.and.room.and.payment";
    public static final String MYSQL_RESERVATION_GET_BY_ID = "mysql.reservation.get.by.id";
    public static final String MYSQL_ROOM_INSERT = "mysql.room.insert";
    public static final String MYSQL_ROOM_UPDATE = "mysql.room.update";
    public static final String MYSQL_ROOM_DELETE = "mysql.room.delete";
    public static final String MYSQL_ROOM_GET_ALL = "mysql.room.get.all";
    public static final String MYSQL_ROOM_GET_FREE_ROOMS_BY_DATES_AND_ROOM_TYPE = "mysql.room.get.free.rooms.by.dates.and.room.type";
    public static final String MYSQL_ROOM_TYPE_GET_FREE_ROOM_TYPES_BY_DATES_AND_CAPACITY = "mysql.room.type.get.free.room.types.by.dates.and.capacity";
    public static final String MYSQL_ROOM_TYPE_INSERT = "mysql.room.type.insert";
    public static final String MYSQL_ROOM_TYPE_UPDATE = "mysql.room.type.update";
    public static final String MYSQL_ROOM_TYPE_DELETE = "mysql.room.type.delete";
    public static final String MYSQL_USER_INSERT = "mysql.user.insert";
    public static final String MYSQL_USER_UPDATE = "mysql.user.update";
    public static final String MYSQL_USER_DELETE = "mysql.user.delete";
    public static final String MYSQL_USER_GET_BY_USERNAME = "mysql.user.get.by.username";
    public static final String MYSQL_USER_GET_BY_EMAIL = "mysql.user.get.by.email";

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

    public static final String REDIRECT = "redirect";
    public static final String MAIN_DEFAULT = "main.default";
    public static final String PAGE_ADMIN = "page.admin";
    public static final String PAGE_CLIENT = "page.client";
    public static final String PAGE_MAIN = "page.main";
    public static final String PAGE_LOGIN = "page.login";
    public static final String PAGE_REGISTRATION = "page.registration";
    public static final String PAGE_404 = "page.404";
    public static final String PAGE_RESERVATION_DETAILS = "page.reservation.details";
    public static final String PAGE_RESERVATION = "page.reservation";
    public static final String PAGE_PROFILE = "page.profile";
    public static final String PAGE_CLIENT_RESERVATIONS = "page.clientReservations";

    public static final String URL_MAIN = "url.main";
    public static final String URL_LOGIN = "url.login";
    public static final String URL_RESERVATION_DETAILS = "url.reservation.details";
    public static final String URL_RESERVATION = "url.reservation";
    public static final String URL_PROFILE = "url.profile";

    public static final String PARAMETER_USERNAME = "parameter.username";
    public static final String PARAMETER_PASSWORD = "parameter.password";
    public static final String PARAMETER_EMAIL = "parameter.email";
    public static final String PARAMETER_PHONENUMBER = "parameter.phonenumber";
    public static final String PARAMETER_FIRSTNAME = "parameter.firstname";
    public static final String PARAMETER_LASTNAME = "parameter.lastname";
    public static final String PARAMETER_PATRONYMIC = "parameter.patronymic";
    public static final String PARAMETER_COMMENT = "parameter.comment";
    public static final String PARAMETER_RESERVATION_DATE = "parameter.reservation.date";
    public static final String PARAMETER_START_DATE = "parameter.start.date";
    public static final String PARAMETER_END_DATE = "parameter.end.date";
    public static final String PARAMETER_ROOM_TYPE_ID = "parameter.room.type.id";
    public static final String PARAMETER_RESERVATION_ID = "parameter.reservation.id";
    public static final String PARAMETER_DATEFILTER = "parameter.datefilter";
    public static final String PARAMETER_CAPACITY = "parameter.capacity";
    public static final String PARAMETER_ADMIN_COMMENT = "parameter.admin.comment";
    public static final String PARAMETER_STATUS = "parameter.status";
    public static final String PARAMETER_AMOUNT = "parameter.amount";
    public static final String PARAMETER_PAYMENT_METHOD = "parameter.payment.method";
    public static final String PARAMETER_ROOM_ID = "parameter.room.id";

    public static final String ATTRIBUTE_USER = "attribute.user";
    public static final String ATTRIBUTE_LOGGED_USERS = "attribute.logged.users";
    public static final String ATTRIBUTE_STATUSES = "attribute.statuses";
    public static final String ATTRIBUTE_RESERVATION = "attribute.reservation";
    public static final String ATTRIBUTE_OPTIONS = "attribute.options";
    public static final String ATTRIBUTE_DATE_FORMAT_ERROR = "attribute.date.format.error";
    public static final String ATTRIBUTE_RESERVATIONS = "attribute.reservations";
    public static final String ATTRIBUTE_PAYMENT_TYPES = "attribute.payment.types";
    public static final String ATTRIBUTE_ROOMS = "attribute.rooms";
    public static final String ATTRIBUTE_ROOM = "attribute.room";
    
    public static final String REGEX_EMAIL = "regex.email";
    
    public static final String INVALID_INPUT_USERNAME_EMPTY = "invalid.input.username.empty";
    public static final String INVALID_INPUT_USERNAME_SHORT = "invalid.input.username.short";
    public static final String INVALID_INPUT_USERNAME_LONG = "invalid.input.username.long";
    public static final String INVALID_INPUT_USERNAME_TAKEN = "invalid.input.username.taken";
    public static final String INVALID_INPUT_PASSWORD_EMPTY = "invalid.input.password.empty";
    public static final String INVALID_INPUT_PASSWORD_SHORT = "invalid.input.password.short";
    public static final String INVALID_INPUT_PASSWORD_LONG = "invalid.input.password.long";
    public static final String INVALID_INPUT_EMAIL_EMPTY = "invalid.input.email.empty";
    public static final String INVALID_INPUT_EMAIL_FORMAT = "invalid.input.email.format";
    public static final String INVALID_INPUT_EMAIL_TAKEN = "invalid.input.email.taken";
    public static final String INVALID_INPUT_PHONE_NUMBER_EMPTY = "invalid.input.phone.number.empty";
    public static final String INVALID_INPUT_FIRST_NAME_EMPTY = "invalid.input.first.name.empty";
    public static final String INVALID_INPUT_LAST_NAME_EMPTY = "invalid.input.last.name.empty";
    public static final String INVALID_INPUT_RESERVATION_RESERVATION_DATE = "invalid.input.reservation.reservation.date";
    public static final String INVALID_INPUT_RESERVATION_START_DATE = "invalid.input.reservation.start.date";
    public static final String INVALID_INPUT_RESERVATION_NEW_ROOM = "invalid.input.reservation.new.room";
    public static final String INVALID_INPUT_RESERVATION_DENIED_ROOM = "invalid.input.reservation.denied.room";
    public static final String INVALID_INPUT_RESERVATION_APPROVED_ROOM = "invalid.input.reservation.approved.room";

    private ConfigManager() {
        resourceBundle = ResourceBundle.getBundle(RESOURCE_NAME);
    }

    public static ConfigManager getInstance() {
        if (instance == null) {
            synchronized (ConfigManager.class) {
                if (instance == null) {
                    instance = new ConfigManager();
                }
            }
        }
        return instance;
    }

    public String getString(String key) {
        return resourceBundle.getString(key);
    }

}
