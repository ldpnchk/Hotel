package ua.edu.service;

import java.sql.Connection;
import java.sql.SQLException;

import ua.edu.dao.DaoFactory;
import ua.edu.dao.PaymentDao;
import ua.edu.dao.connection.DataSource;
import ua.edu.entity.Payment;

public class PaymentService {
	
	private static PaymentService instance;
	
	public static PaymentService getInstance(){
        if(instance == null){
            synchronized (PaymentService.class){
                if(instance == null){
                	instance = new PaymentService();
                }
            }
        }
        return instance;
    }
	
	public void createPayment(Payment payment){
        try (Connection connection = DataSource.getInstance().getConnection()){
        	PaymentDao paymentDao = DaoFactory.getDaoFactory().createPaymentDao(connection);
    		paymentDao.create(payment);
        } catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updatePayment(Payment payment){
        try (Connection connection = DataSource.getInstance().getConnection()){
        	PaymentDao paymentDao = DaoFactory.getDaoFactory().createPaymentDao(connection);
    		paymentDao.update(payment);
        } catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deletePayment(int id){
        try (Connection connection = DataSource.getInstance().getConnection()){
        	PaymentDao paymentDao = DaoFactory.getDaoFactory().createPaymentDao(connection);
    		paymentDao.delete(id);
        } catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
