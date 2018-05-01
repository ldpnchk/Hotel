package ua.edu.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import ua.edu.dao.PaymentDao;
import ua.edu.entity.Payment;

public class JdbcPaymentDao implements PaymentDao{
	
	private static final String INSERT = "INSERT INTO payment (total, date, payment_method, reservation_id) "
			+ "VALUES (?, ?, ?, ?);";
	private static final String UPDATE = "UPDATE payment SET total = ?, date = ? payment_method = ?, "
			+ "reservation_id = ? WHERE payment_id = ?;";
	private static final String DELETE = "DELETE FROM payment WHERE payment_id = ?";
	private static final String GET_BY_ID = "SELECT * FROM payment WHERE payment_id = ?;";
	
	private Connection connection;
	
	public JdbcPaymentDao(Connection connection){
		this.connection = connection;
	}

	public void create(Payment payment) throws SQLException {
		
	}

	public void update(Payment t) {

	}

	public void delete(int id) {

	}

	public Payment getById(int id) {
		return null;
	}

}
