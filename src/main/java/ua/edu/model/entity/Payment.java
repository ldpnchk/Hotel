package ua.edu.model.entity;

import java.util.Date;

public class Payment {
	
	private int id;
	private int total;
	private Date date;
	
	private PaymentMethod paymentMethod;
	private Reservation reservation;
	
	public Payment() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}
	
	public static final class PaymentBuilder {
		
		private Payment payment = new Payment();
		
		public PaymentBuilder() {

		}

		public PaymentBuilder setId(int id) {
			payment.id = id;
			return this;
		}

		public PaymentBuilder setTotal(int total) {
			payment.total = total;
			return this;
		}

		public PaymentBuilder setDate(Date date) {
			payment.date = date;
			return this;
		}

		public PaymentBuilder setPaymentMethod(PaymentMethod paymentMethod) {
			payment.paymentMethod = paymentMethod;
			return this;
		}

		public PaymentBuilder setReservation(Reservation reservation) {
			payment.reservation = reservation;
			return this;
		}
		
		public Payment build(){
			return payment;
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
		Payment other = (Payment) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return new StringBuilder().append("Payment [id=").append(id)
				.append(", total=").append(total)
				.append(", date=").append(date)
				.append(", paymentMethod=").append(paymentMethod)
				.append(", reservation=").append(reservation).append("]").toString();
	}

}
