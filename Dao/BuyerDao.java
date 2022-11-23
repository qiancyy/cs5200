package HouseSelling.Dao;

import static HouseSelling.Utils.Helper.closeResource;

import HouseSelling.Model.Buyer;
import HouseSelling.Model.User;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BuyerDao extends AbstractDao<Buyer> {

	public BuyerDao() {
	}

	private static BuyerDao instance = null;

	public synchronized static BuyerDao getInstance() {
		if (instance == null)
			instance = new BuyerDao();
		return instance;
	}

	@Override
	public Buyer create(Buyer buyer) throws SQLException {
		User user = UserDao.getInstance()
				.create(new User(-1, buyer.getFirstName(), buyer.getMiddleName(), buyer.getLastName(), buyer.getEmail(),
						buyer.getPhoneNumber(), buyer.getPassword(), buyer.getZip(), buyer.getAddress()));
		int id = user.getUserId();
		buyer.setUserId(id);
		String st = "INSERT INTO Buyer(buyerId,DOB) VALUES(?,?)";
		PreparedStatement preSt = connection.prepareStatement(st);
		ResultSet resultSet = null;
		// fill the ? in preSt
		preSt.setInt(1, id);
		preSt.setDate(2, new Date(buyer.getDOB().getTime()));
		preSt.executeUpdate();
		closeResource(preSt, resultSet);
		return buyer;
	}

	@Override
	public void delete(Buyer buyer) throws SQLException {
		UserDao.getInstance().delete(buyer);

	}

	@Override
	public Buyer getById(int id) throws SQLException {
		String st = "SELECT * FROM Buyer INNER JOIN User ON Buyer.buyerId=User.userId WHERE buyerId=?";
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
			java.util.Date dob = new java.util.Date(resultSet.getDate("DOB").getTime());
			return new Buyer(id, firstName, middleName, lastName, email, phoneNumber, password, zip, address, dob);
		}
		closeResource(preSt, resultSet);
		return null;
	}

	public void update(Buyer buyer) throws SQLException {
		String st = "UPDATE Buyer SET DOB=? where buyerId=?";
		PreparedStatement preSt = connection.prepareStatement(st);
		ResultSet resultSet = null;
		preSt.setDate(1, new Date(buyer.getDOB().getTime()));
		preSt.setInt(2, buyer.getUserId());

		UserDao.getInstance().update(buyer);
		preSt.executeUpdate();
	}
}
