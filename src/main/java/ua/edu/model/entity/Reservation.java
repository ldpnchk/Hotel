package ua.edu.model.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Reservation {
	
	private int id;
	private LocalDateTime reservationDate;
	private LocalDate startDate;
	private LocalDate endDate;
	private String clientComment;
	private String administratorComment;
	
	private ReservationStatus reservationStatus;
	private User client;
	private RoomType roomType;
	private Room room;
	private Payment payment;
	
	public Reservation() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(LocalDateTime reservationDate) {
		this.reservationDate = reservationDate;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getClientComment() {
		return clientComment;
	}

	public void setClientComment(String clientComment) {
		this.clientComment = clientComment;
	}

	public String getAdministratorComment() {
		return administratorComment;
	}

	public void setAdministratorComment(String administratorComment) {
		this.administratorComment = administratorComment;
	}

	public ReservationStatus getReservationStatus() {
		return reservationStatus;
	}

	public void setReservationStatus(ReservationStatus reservationStatus) {
		this.reservationStatus = reservationStatus;
	}

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}

	public RoomType getRoomType() {
		return roomType;
	}

	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public static final class ReservationBuilder {
		
		private Reservation reservation = new Reservation();
		
		public ReservationBuilder(){
			
		}

		public ReservationBuilder setId(int id) {
			reservation.id = id;
			return this;
		}

		public ReservationBuilder setReservationDate(LocalDateTime reservationDate) {
			reservation.reservationDate = reservationDate;
			return this;
		}

		public ReservationBuilder setStartDate(LocalDate startDate) {
			reservation.startDate = startDate;
			return this;
		}

		public ReservationBuilder setEndDate(LocalDate endDate) {
			reservation.endDate = endDate;
			return this;
		}

		public ReservationBuilder setClientComment(String clientComment) {
			reservation.clientComment = clientComment;
			return this;
		}
		
		public ReservationBuilder setAdministratorComment(String administratorComment) {
			reservation.administratorComment = administratorComment;
			return this;
		}

		public ReservationBuilder setReservationStatus(ReservationStatus reservationStatus) {
			reservation.reservationStatus = reservationStatus;
			return this;
		}

		public ReservationBuilder setClient(User client) {
			reservation.client = client;
			return this;
		}

		public ReservationBuilder setRoomType(RoomType roomType) {
			reservation.roomType = roomType;
			return this;
		}

		public ReservationBuilder setRoom(Room room) {
			reservation.room = room;
			return this;
		}
		
		public ReservationBuilder setPayment(Payment payment) {
			reservation.payment = payment;
			return this;
		}

		public Reservation build(){
			return reservation;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reservation other = (Reservation) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return new StringBuilder().append("Reservation [id=").append(id)
				.append(", reservationDate=").append(reservationDate)
				.append(", startDate=").append(startDate)
				.append(", endDate=").append(endDate)
				.append(", clientComment=").append(clientComment)
				.append(", administratorComment=").append(administratorComment)
				.append(", reservationStatus=").append(reservationStatus)
				.append(", client=").append(client)
				.append(", roomType=").append(roomType)
				.append(", room=").append(room)
				.append(", payment=").append(payment).append("]").toString();
	}

}
