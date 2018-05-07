package ua.edu.entity;

public class RoomType {
	
	private int id;
	private String name;
	private int capacity;
	private int price;
	private String description;
	
	public RoomType() {

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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public static final class RoomTypeBuilder {
		
		private RoomType roomType = new RoomType();
		
		public RoomTypeBuilder(){
			
		}

		public RoomTypeBuilder setId(int id) {
			roomType.id = id;
			return this;
		}

		public RoomTypeBuilder setName(String name) {
			roomType.name = name;
			return this;
		}

		public RoomTypeBuilder setCapacity(int capacity) {
			roomType.capacity = capacity;
			return this;
		}

		public RoomTypeBuilder setPrice(int price) {
			roomType.price = price;
			return this;
		}

		public RoomTypeBuilder setDescription(String description) {
			roomType.description = description;
			return this;
		}
		
		public RoomType build(){
			return roomType;
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
		RoomType other = (RoomType) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return new StringBuilder().append("RoomType [id=").append(id)
				.append(", name=").append(name)
				.append(", capacity=").append(capacity)
				.append(", price=").append(price)
				.append(", description=").append(description).append("]").toString();
	}

}
