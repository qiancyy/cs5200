package HouseSelling.Dao;

import static HouseSelling.Utils.Helper.closeResource;

import HouseSelling.Model.Company;
import HouseSelling.Model.Seller;
import HouseSelling.Model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SellerDao extends AbstractDao<Seller> {
	private static SellerDao instance = null;

	public synchronized static SellerDao getInstance() {
		if (instance == null)
			instance = new SellerDao();
		return instance;
	}

	public SellerDao() {
	}

	@Override
	public Seller create(Seller seller) throws SQLException {
		User user = UserDao.getInstance()
				.create(new User(-1, seller.getFirstName(), seller.getMiddleName(), seller.getLastName(),
						seller.getEmail(), seller.getPhoneNumber(), seller.getPassword(), seller.getZip(),
						seller.getAddress()));
		int id = user.getUserId();
		seller.setUserId(id);
		String st = "INSERT INTO Seller(sellerId,companyId,introduction) VALUES(?,?,?)";
		PreparedStatement preSt = connection.prepareStatement(st);
		ResultSet resultSet = null;
		// fill the ? in preSt
		preSt.setInt(1, id);
		preSt.setInt(2, seller.getCompany().getCompanyId());
		preSt.setString(3, seller.getIntroduction());
		preSt.executeUpdate();
		closeResource(preSt, resultSet);
		return seller;
	}

	@Override
	public void delete(Seller seller) throws SQLException {
//
		UserDao.getInstance().delete(seller);
		// closeResource(connection,preSt,resultSet);
	}

	@Override
	public Seller getById(int id) throws SQLException {
		String st = "SELECT * FROM Seller INNER JOIN User ON Seller.sellerId=User.userId WHERE sellerId=?";
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
			Company company = CompanyDao.getInstance().getById(resultSet.getInt("companyId"));
			String introduction = resultSet.getString("introduction");
			return new Seller(id, firstName, middleName, lastName, email, phoneNumber, password, zip, address,
					introduction, company);
		}
		closeResource(preSt, resultSet);
		return null;
	}
	// problem10
//  public List<HighestRatedSeller> getHighestRatedSeller() throws SQLException {
//    List<HighestRatedSeller> res = new ArrayList<>();
//    String st = "";
//    PreparedStatement preSt = connection.prepareStatement(st);
//    ResultSet resultSet = null;
//    resultSet = preSt.executeQuery();
//    while(resultSet.next()){
//      int companyId = resultSet.getInt("companyId");
//      int sellerId = resultSet.getInt("sellerId");
//      double avg = resultSet.getDouble("avgRating");
//      res.add(new HighestRatedSeller(companyId,sellerId,avg));
//    }
//    closeResource(connection,preSt,resultSet);
//    return res;
//  }
}
