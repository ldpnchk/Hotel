package ua.edu.model.dto;

import java.math.BigDecimal;

public class RoomTypeDto {
	
	private int id;
	private String name;
	private int capacity;
	private String description;
	
	private BigDecimal pricePerNight;
	private BigDecimal totalPrice;
	
	public RoomTypeDto() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPricePerNight() {
		return pricePerNight;
	}

	public void setPricePerNight(BigDecimal pricePerNight) {
		this.pricePerNight = pricePerNight;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public static final class RoomTypeDtoBuilder {
		
		private RoomTypeDto roomTypeDto = new RoomTypeDto();
		
		public RoomTypeDtoBuilder(){
			
		}

		public RoomTypeDtoBuilder setId(int id) {
			roomTypeDto.id = id;
			return this;
		}

		public RoomTypeDtoBuilder setName(String name) {
			roomTypeDto.name = name;
			return this;
		}

		public RoomTypeDtoBuilder setCapacity(int capacity) {
			roomTypeDto.capacity = capacity;
			return this;
		}

		public RoomTypeDtoBuilder setDescription(String description) {
			roomTypeDto.description = description;
			return this;
		}
		
		public RoomTypeDtoBuilder setPricePerNight(BigDecimal pricePerNight) {
			roomTypeDto.pricePerNight = pricePerNight;
			return this;
		}

		public RoomTypeDtoBuilder setTotalPrice(BigDecimal totalPrice) {
			roomTypeDto.totalPrice = totalPrice;
			return this;
		}
		
		public RoomTypeDto build(){
			return roomTypeDto;
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
		RoomTypeDto other = (RoomTypeDto) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return new StringBuilder().append("RoomTypeDto [id=").append(id)
				.append(", name=").append(name)
				.append(", capacity=").append(capacity)
				.append(", description=").append(description)
				.append(", pricePerNight=").append(pricePerNight)
				.append(", totalPrice=").append(totalPrice).append("]").toString();
	}
	
}
