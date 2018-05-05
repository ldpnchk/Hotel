package ua.edu.dao.mysql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ua.edu.dao.PaymentDao;
import ua.edu.entity.Payment;

public class MySQLPaymentDAO implements PaymentDao{
	
	private static final String INSERT = "INSERT INTO payment (total, date, payment_method, reservation_id) "
			+ "VALUES (?, ?, ?, ?);";
	private static final String UPDATE = "UPDATE payment SET total = ?, date = ?, payment_method = ?, "
			+ "reservation_id = ? WHERE payment_id = ?;";
	private static final String DELETE = "DELETE FROM payment WHERE payment_id = ?";
	
	private Connection connection;
	
	public MySQLPaymentDAO(Connection connection){
		this.connection = connection;
	}

	public void create(Payment payment) throws SQLException {
		PreparedStatement createStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
		createStatement.setInt(1, payment.getTotal());
		createStatement.setDate(2, new java.sql.Date(payment.getDate().getTime()));
		createStatement.setString(3, payment.getPaymentMethod().name().toLowerCase());
		createStatement.setInt(4, payment.getReservation().getId());
		createStatement.executeUpdate();
		
		ResultSet rs = createStatement.getGeneratedKeys();
		if (rs.next()) {
			payment.setId(rs.getInt(1));
		}
		
		createStatement.close();
	}

	public void update(Payment payment) throws SQLException {
		PreparedStatement updateStatement = connection.prepareStatement(UPDATE);
		updateStatement.setInt(1, payment.getTotal());
		updateStatement.setDate(2, new Date(payment.getDate().getTime()));
		updateStatement.setString(3, payment.getPaymentMethod().name().toLowerCase());
		updateStatement.setInt(4, payment.getReservation().getId());
		updateStatement.setInt(5, payment.getId());
		updateStatement.executeUpdate();
		updateStatement.close();
	}

	public void delete(int id) throws SQLException {
		PreparedStatement deleteStatement = connection.prepareStatement(DELETE);
		deleteStatement.setInt(1, id);
		deleteStatement.executeUpdate();
		deleteStatement.close();
	}

}
