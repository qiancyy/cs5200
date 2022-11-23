package HouseSelling.Model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Buyer extends User{
  private Date DOB;

  public Buyer(int userId, String firstName, String middleName, String lastName, String email,
      String phoneNumber, String password, int zip, String address, Date dob) {
    super(userId, firstName, middleName, lastName, email, phoneNumber, password, zip, address);
    DOB = dob;
  }

  public Date getDOB() {
    return DOB;
  }

  public void setDOB(Date DOB) {
    this.DOB = DOB;
  }
  public String getSimpleDOB() {
	  DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	  return dateFormat.format(this.DOB);
  }

  @Override
  public String toString() {
    return "Buyer{" +
        "DOB=" + DOB +
        ", userId=" + userId +
        ", firstName='" + firstName + '\'' +
        ", middleName='" + middleName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", email='" + email + '\'' +
        ", phoneNumber='" + phoneNumber + '\'' +
        ", password='" + password + '\'' +
        ", zip=" + zip +
        ", address='" + address + '\'' +
        "} " + super.toString();
  }
}
