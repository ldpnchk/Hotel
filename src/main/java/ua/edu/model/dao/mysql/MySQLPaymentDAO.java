package ua.edu.model.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import org.apache.log4j.Logger;

import ua.edu.model.dao.PaymentDao;
import ua.edu.model.entity.Payment;
import ua.edu.model.entity.PaymentMethod;
import ua.edu.model.exception.DatabaseException;
import ua.edu.model.util.ConfigManager;

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
			createStatement.setTimestamp(2, Timestamp.valueOf(payment.getDate()));
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
			updateStatement.setTimestamp(2, Timestamp.valueOf(payment.getDate()));
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
				.setDate(resultSet.getTimestamp(ConfigManager.getInstance().getString(ConfigManager.PAYMENT_DATE)).toLocalDateTime())
				.setPaymentMethod(PaymentMethod.getPaymentMethod(resultSet.getString(ConfigManager.getInstance().getString(ConfigManager.PAYMENT_PAYMENT_METHOD))))
				.build();
	}

}
