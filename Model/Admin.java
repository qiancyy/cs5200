package HouseSelling.Model;

public class Admin extends User {

	public Admin(int userId, String firstName, String middleName, String lastName, String email, String phoneNumber,
			String password, int zip, String address) {
		super(userId, firstName, middleName, lastName, email, phoneNumber, password, zip, address);
	}

	@Override
	public String toString() {
		return "Admin{" + "userId=" + userId + ", firstName='" + firstName + '\'' + ", middleName='" + middleName + '\''
				+ ", lastName='" + lastName + '\'' + ", email='" + email + '\'' + ", phoneNumber='" + phoneNumber + '\''
				+ ", password='" + password + '\'' + ", zip=" + zip + ", address='" + address + '\'' + "} "
				+ super.toString();
	}
}
