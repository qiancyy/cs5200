package HouseSelling.Model;

public class Seller extends User {
	private String introduction;
	private Company company;

	public Seller(int userId, String firstName, String middleName, String lastName, String email, String phoneNumber,
			String password, int zip, String address, String introduction, Company company) {
		super(userId, firstName, middleName, lastName, email, phoneNumber, password, zip, address);
		this.introduction = introduction;
		this.company = company;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return "Seller{" + "introduction='" + introduction + '\'' + ", company=" + company + ", userId=" + userId
				+ ", firstName='" + firstName + '\'' + ", middleName='" + middleName + '\'' + ", lastName='" + lastName
				+ '\'' + ", email='" + email + '\'' + ", phoneNumber='" + phoneNumber + '\'' + ", password='" + password
				+ '\'' + ", zip=" + zip + ", address='" + address + '\'' + "} " + super.toString();
	}
}
