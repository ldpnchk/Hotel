package ua.edu.model.entity;

public enum ReservationStatus {
	
	NEW, 
	APPROVED, 
	DENIED, 
	PAYED;
	
	public String getValue(){
		return this.name().toLowerCase();
	}
	
	public static ReservationStatus getReservationStatus(String role){
		return ReservationStatus.valueOf(role.toUpperCase());
	}

}
