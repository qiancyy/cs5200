package HouseSelling.Model;

public class User {
	protected int userId;
	protected String firstName;
	protected String middleName;
	protected String lastName;
	protected String email;
	protected String phoneNumber;
	protected String password;
	protected int zip;
	protected String address;

	public User(int userId, String firstName, String middleName, String lastName, String email, String phoneNumber,
			String password, int zip, String address) {
		this.userId = userId;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.zip = zip;
		this.address = address;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "User{" + "userId=" + userId + ", firstName='" + firstName + '\'' + ", middleName='" + middleName + '\''
				+ ", lastName='" + lastName + '\'' + ", email='" + email + '\'' + ", phoneNumber='" + phoneNumber + '\''
				+ ", password='" + password + '\'' + ", zip=" + zip + ", address='" + address + '\'' + '}';
	}
}
