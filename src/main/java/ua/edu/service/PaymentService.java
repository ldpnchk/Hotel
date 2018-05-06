package ua.edu.service;

import java.sql.Connection;
import java.sql.SQLException;

import ua.edu.dao.PaymentDao;
import ua.edu.entity.Payment;

public class PaymentService extends Service{
	
	private static PaymentService instance;
	
	private PaymentService(){
		
	}
	
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
        try (Connection connection = dataSource.getConnection()){
        	PaymentDao paymentDao = daoFactory.createPaymentDao(connection);
    		paymentDao.create(payment);
        } catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updatePayment(Payment payment){
        try (Connection connection = dataSource.getConnection()){
        	PaymentDao paymentDao = daoFactory.createPaymentDao(connection);
    		paymentDao.update(payment);
        } catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deletePayment(int id){
        try (Connection connection = dataSource.getConnection()){
        	PaymentDao paymentDao = daoFactory.createPaymentDao(connection);
    		paymentDao.delete(id);
        } catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
