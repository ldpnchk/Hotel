package ua.edu.entity;

public class User {
	
	private int id;
	private String username;
	private String password;
	private String email;
	private String phoneNumber;
	private String firstName;
	private String lastName;
	private String patronymic;

	private UserRole userRole;

	public User() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPatronymic() {
		return patronymic;
	}

	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}
	
	public static final class UserBuilder {
		
		private User user = new User();
		
		public UserBuilder(){
			
		}

		public UserBuilder setId(int id) {
			user.id = id;
			return this;
		}

		public UserBuilder setUsername(String username) {
			user.username = username;
			return this;
		}

		public UserBuilder setPassword(String password) {
			user.password = password;
			return this;
		}

		public UserBuilder setEmail(String email) {
			user.email = email;
			return this;
		}

		public UserBuilder setPhoneNumber(String phoneNumber) {
			user.phoneNumber = phoneNumber;
			return this;
		}

		public UserBuilder setFirstName(String firstName) {
			user.firstName = firstName;
			return this;
		}

		public UserBuilder setLastName(String lastName) {
			user.lastName = lastName;
			return this;
		}

		public UserBuilder setPatronymic(String patronymic) {
			user.patronymic = patronymic;
			return this;
		}

		public UserBuilder setUserRole(UserRole userRole) {
			user.userRole = userRole;
			return this;
		}
		
		public User build(){
			return user;
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
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return new StringBuilder().append("User [id=").append(id)
				.append(", username=").append(username)
				.append(", password=").append(password)
				.append(", email=").append(email)
				.append(", phoneNumber=").append(phoneNumber)
				.append(", firstName=").append(firstName)
				.append(", lastName=").append(lastName)
				.append(", patronymic=").append(patronymic)
				.append(", userRole=").append(userRole).append("]").toString();
	}	

}
