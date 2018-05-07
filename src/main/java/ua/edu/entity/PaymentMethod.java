package ua.edu.entity;

public enum PaymentMethod {
	
	CASH, 
	CARD;
	
	public String getValue(){
		return this.name().toLowerCase();
	}
	
	public static PaymentMethod getPaymentMethod(String role){
		return PaymentMethod.valueOf(role.toUpperCase());
	}

}
