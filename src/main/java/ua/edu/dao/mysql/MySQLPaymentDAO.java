package ua.edu.dao.mysql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import ua.edu.dao.PaymentDao;
import ua.edu.entity.Payment;
import ua.edu.entity.PaymentMethod;
import ua.edu.exception.DatabaseException;
import ua.edu.util.ConfigManager;

public class MySQLPaymentDAO implements PaymentDao{
	
	final static Logger logger = Logger.getLogger(MySQLPaymentDAO.class);
	
	private Connection connection;
	
	public MySQLPaymentDAO(Connection connection){
		this.connection = connection;
	}

	public void create(Payment payment){
		try (PreparedStatement createStatement = connection.prepareStatement
				(ConfigManager.getInstance().getString(ConfigManager.MYSQL_PAYMENT_INSERT), 
						Statement.RETURN_GENERATED_KEYS)){
			createStatement.setInt(1, payment.getTotal());
			createStatement.setDate(2, new java.sql.Date(payment.getDate().getTime()));
			createStatement.setString(3, payment.getPaymentMethod().name().toLowerCase());
			createStatement.setInt(4, payment.getReservation().getId());
			createStatement.executeUpdate();
		
			ResultSet rs = createStatement.getGeneratedKeys();
			if (rs.next()) {
				payment.setId(rs.getInt(1));
			}
		} catch (SQLException e){
			logger.error("MySQLPaymentDAO create error: " + payment.toString(), e);
			throw new DatabaseException();
		}
	}

	public void update(Payment payment){
		try (PreparedStatement updateStatement = connection.prepareStatement
				(ConfigManager.getInstance().getString(ConfigManager.MYSQL_PAYMENT_UPDATE))){
			updateStatement.setInt(1, payment.getTotal());
			updateStatement.setDate(2, new Date(payment.getDate().getTime()));
			updateStatement.setString(3, payment.getPaymentMethod().name().toLowerCase());
			updateStatement.setInt(4, payment.getReservation().getId());
			updateStatement.setInt(5, payment.getId());
			updateStatement.executeUpdate();
		} catch (SQLException e){
			logger.error("MySQLPaymentDAO update error: " + payment.toString(), e);
			throw new DatabaseException();
		}
	}

	public void delete(int id){
		try (PreparedStatement deleteStatement = connection.prepareStatement
				(ConfigManager.getInstance().getString(ConfigManager.MYSQL_PAYMENT_DELETE))){
			deleteStatement.setInt(1, id);
			deleteStatement.executeUpdate();
		} catch (SQLException e){
			logger.error("MySQLPaymentDAO delete error: " + id, e);
			throw new DatabaseException();
		}
	}
	
	static Payment extractPaymentFromResultSet(ResultSet resultSet) throws SQLException {
		return new Payment.PaymentBuilder()
				.setId(resultSet.getInt(ConfigManager.getInstance().getString(ConfigManager.PAYMENT_PAYMENT_ID)))
				.setTotal(resultSet.getInt(ConfigManager.getInstance().getString(ConfigManager.PAYMENT_TOTAL)))
				.setDate(resultSet.getDate(ConfigManager.getInstance().getString(ConfigManager.PAYMENT_DATE)))
				.setPaymentMethod(PaymentMethod.getPaymentMethod(resultSet.getString(ConfigManager.getInstance().getString(ConfigManager.PAYMENT_PAYMENT_METHOD))))
				.build();
	}

}
