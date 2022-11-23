package HouseSelling.Dao;

import static HouseSelling.Utils.Helper.closeResource;

import HouseSelling.Model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDao extends AbstractDao<User> {

	public UserDao() {
	}

	private static UserDao instance = null;

	public synchronized static UserDao getInstance() {
		if (instance == null)
			instance = new UserDao();
		return instance;
	}

	@Override
	public User create(User user) throws SQLException {
		String st = "INSERT INTO User(firstName,middleName,lastName,email,phoneNumber,password,zip,address) VALUES(?,?,?,?,?,?,?,?)";
		PreparedStatement preSt = connection.prepareStatement(st, Statement.RETURN_GENERATED_KEYS);
		ResultSet resultSet = null;
		// fill the ? in preSt
		preSt.setString(1, user.getFirstName());
		preSt.setString(2, user.getMiddleName());
		preSt.setString(3, user.getLastName());
		preSt.setString(4, user.getEmail());
		preSt.setString(5, user.getPhoneNumber());
		preSt.setString(6, user.getPassword());
		preSt.setInt(7, user.getZip());
		preSt.setString(8, user.getAddress());
		preSt.executeUpdate();
		resultSet = preSt.getGeneratedKeys();
		int generateId = resultSet.getInt(1);
		user.setUserId(generateId);
		closeResource(preSt, resultSet);
		return user;
	}

	@Override
	public void delete(User user) throws SQLException {
		String st = "DELETE FROM User WHERE userId=?";
		PreparedStatement preSt = connection.prepareStatement(st);
		ResultSet resultSet = null;
		// fill the ? in preSt
		preSt.setInt(1, user.getUserId());
		preSt.executeUpdate();
		closeResource(preSt, resultSet);
	}

	@Override
	public User getById(int id) throws SQLException {
		String st = "SELECT * FROM User WHERE userId=?";
		PreparedStatement preSt = connection.prepareStatement(st);
		ResultSet resultSet = null;
		preSt.setInt(1, id);
		resultSet = preSt.executeQuery();
		if (resultSet.next()) {
			String firstName = resultSet.getString("firstName");
			String middleName = resultSet.getString("middleName");
			String lastName = resultSet.getString("lastName");
			String email = resultSet.getString("email");
			String phoneNumber = resultSet.getString("phoneNumber");
			String password = resultSet.getString("password");
			int zip = resultSet.getInt("zip");
			String address = resultSet.getString("address");
			return new User(id, firstName, middleName, lastName, email, phoneNumber, password, zip, address);
		}
		closeResource(preSt, resultSet);
		return null;
	}

	public void update(User user) throws SQLException {
		String st = "UPDATE User SET firstName=?,middleName=?,lastName=?,email=?,phoneNumber=?,zip=?,address=?, password=? WHERE userId=?";
		PreparedStatement preSt = connection.prepareStatement(st);
		preSt.setString(1, user.getFirstName());
		preSt.setString(2, user.getMiddleName());
		preSt.setString(3, user.getLastName());
		preSt.setString(4, user.getEmail());
		preSt.setString(5, user.getPhoneNumber());
		preSt.setInt(6, user.getZip());
		preSt.setString(7, user.getAddress());
		preSt.setString(8, user.getPassword());
		preSt.setInt(9, user.getUserId());
		ResultSet resultSet = null;
		preSt.executeUpdate();
		closeResource(preSt, resultSet);
	}
}
