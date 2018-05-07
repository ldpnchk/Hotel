package ua.edu.dao.mysql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ua.edu.dao.PaymentDao;
import ua.edu.entity.Payment;
import ua.edu.entity.PaymentMethod;
import ua.edu.entity.Reservation;
import ua.edu.util.ConfigurationManager;

public class MySQLPaymentDAO implements PaymentDao{
	
	private Connection connection;
	
	public MySQLPaymentDAO(Connection connection){
		this.connection = connection;
	}

	public void create(Payment payment) throws SQLException {
		PreparedStatement createStatement = connection.prepareStatement
				(ConfigurationManager.getInstance().getString(ConfigurationManager.MYSQL_PAYMENT_INSERT), 
						Statement.RETURN_GENERATED_KEYS);
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
		PreparedStatement updateStatement = connection.prepareStatement
				(ConfigurationManager.getInstance().getString(ConfigurationManager.MYSQL_PAYMENT_UPDATE));
		updateStatement.setInt(1, payment.getTotal());
		updateStatement.setDate(2, new Date(payment.getDate().getTime()));
		updateStatement.setString(3, payment.getPaymentMethod().name().toLowerCase());
		updateStatement.setInt(4, payment.getReservation().getId());
		updateStatement.setInt(5, payment.getId());
		updateStatement.executeUpdate();
		updateStatement.close();
	}

	public void delete(int id) throws SQLException {
		PreparedStatement deleteStatement = connection.prepareStatement
				(ConfigurationManager.getInstance().getString(ConfigurationManager.MYSQL_PAYMENT_DELETE));
		deleteStatement.setInt(1, id);
		deleteStatement.executeUpdate();
		deleteStatement.close();
	}
	
	static Payment extractPaymentFromResultSet(ResultSet resultSet) throws SQLException {
		return new Payment.PaymentBuilder()
				.setId(resultSet.getInt(ConfigurationManager.getInstance().getString(ConfigurationManager.PAYMENT_PAYMENT_ID)))
				.setTotal(resultSet.getInt(ConfigurationManager.getInstance().getString(ConfigurationManager.PAYMENT_TOTAL)))
				.setDate(resultSet.getDate(ConfigurationManager.getInstance().getString(ConfigurationManager.PAYMENT_DATE)))
				.setPaymentMethod(PaymentMethod.getPaymentMethod(resultSet.getString(ConfigurationManager.getInstance().getString(ConfigurationManager.PAYMENT_PAYMENT_METHOD))))
				.build();
	}

}
