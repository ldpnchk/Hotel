package ua.edu.model.entity;

public class Room {

	private int id;
	private String roomNumber;
	
	private RoomType roomType;
	
	public Room() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public RoomType getRoomType() {
		return roomType;
	}

	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}
	
	public static final class RoomBuilder {
		
		private Room room = new Room();
		
		public RoomBuilder(){
			
		}

		public RoomBuilder setId(int id) {
			room.id = id;
			return this;
		}
		
		public RoomBuilder setRoomNumber(String roomNumber) {
			room.roomNumber = roomNumber;
			return this;
		}
		
		public RoomBuilder setRoomType(RoomType roomType) {
			room.roomType = roomType;
			return this;
		}
		
		public Room build(){
			return room;
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
		Room other = (Room) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return new StringBuilder().append("Room [id=").append(id)
				.append(", roomNumber=").append(roomNumber)
				.append(", roomType=").append(roomType).append("]").toString();
	}

}
