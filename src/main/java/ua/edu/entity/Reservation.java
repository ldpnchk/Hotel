package ua.edu.entity;

import java.util.Date;

public class Reservation {
	
	private int id;
	private Date reservationDate;
	private Date startDate;
	private Date endDate;
	private String clientComment;
	private String administratorComment;
	
	private ReservationStatus reservationStatus;
	private User client;
	private RoomType roomType;
	private Room room;
	
	public Reservation() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(Date reservationDate) {
		this.reservationDate = reservationDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
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
	
	public static final class ReservationBuilder {
		
		private Reservation reservation = new Reservation();
		
		public ReservationBuilder(){
			
		}

		public ReservationBuilder setId(int id) {
			reservation.id = id;
			return this;
		}

		public ReservationBuilder setReservationDate(Date reservationDate) {
			reservation.reservationDate = reservationDate;
			return this;
		}

		public ReservationBuilder setStartDate(Date startDate) {
			reservation.startDate = startDate;
			return this;
		}

		public ReservationBuilder setEndDate(Date endDate) {
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
				.append(", room=").append(room).append("]").toString();
	}

}
