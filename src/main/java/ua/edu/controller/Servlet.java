package ua.edu.controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.edu.entity.*;
import ua.edu.service.*;

public class Servlet extends HttpServlet {
	
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    	processRequest(request, response);
    }
	
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    	processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	PaymentService pas = new PaymentService();
    	ReservationService res = new ReservationService();
    	RoomService ros = new RoomService();
    	RoomTypeService rts = new RoomTypeService();
    	UserService uss = new UserService();
    	
    	User user = new User();
    	user.setLogin("login");
    	user.setPassword("password");
    	user.setEmail("email@i.a");
    	user.setPhoneNumber("+0987654321");
    	user.setFirstName("Ivan");
    	user.setLastName("Ivanov");
    	user.setUserRole(UserRole.CLIENT);
    	
    	RoomType roomType = new RoomType();
    	roomType.setName("Standart");
    	roomType.setCapacity(2);
    	roomType.setPrice(400);
    	roomType.setDescription("Best room in your life!");
    	
    	Room room = new Room();
    	room.setRoomNumber("101");
    	room.setRoomType(roomType);
    	
    	Reservation reservation = new Reservation();
    	reservation.setReservationDate(Date.valueOf(LocalDate.now()));
    	reservation.setStartDate(Date.valueOf(LocalDate.now()));
    	reservation.setEndDate(Date.valueOf(LocalDate.now()));
    	reservation.setReservationStatus(ReservationStatus.NEW);
    	reservation.setClient(user);
    	reservation.setRoomType(roomType);
    	
    	Payment payment = new Payment();
    	payment.setDate(Date.valueOf(LocalDate.now()));
    	payment.setPaymentMethod(PaymentMethod.CASH);
    	payment.setTotal(500);
    	payment.setReservation(reservation);
    	
    	uss.createUser(user);
    	user.setPatronymic("Ivanovich");
    	uss.updateUser(user);
    	uss.deleteUser(user.getId());
    	
    	rts.createRoomType(roomType);
    	roomType.setPrice(450);
    	rts.updateRoomType(roomType);
    	rts.deleteRoomType(roomType.getId());
    	
    	rts.createRoomType(roomType);
    	ros.createRoom(room);
    	room.setRoomNumber("102");
    	ros.updateRoom(room);
    	ros.deleteRoom(room.getId());
    	rts.deleteRoomType(roomType.getId());
    	
    	uss.createUser(user);
    	rts.createRoomType(roomType);
    	res.createReservation(reservation);
    	reservation.setAdministratorComment("Good");
    	res.updateReservation(reservation);
    	res.deleteReservation(reservation.getId());
    	uss.deleteUser(user.getId());
    	rts.deleteRoomType(roomType.getId());
    	
    	uss.createUser(user);
    	rts.createRoomType(roomType);
    	res.createReservation(reservation);
    	pas.createPayment(payment);
    	payment.setTotal(550);
    	pas.updatePayment(payment);
    	pas.deletePayment(payment.getId());
    	res.deleteReservation(reservation.getId());
    	uss.deleteUser(user.getId());
    	rts.deleteRoomType(roomType.getId());
    }

}