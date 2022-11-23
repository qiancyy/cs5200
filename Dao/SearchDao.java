package HouseSelling.Dao;

import static HouseSelling.Utils.Helper.closeResource;

import HouseSelling.Model.Buyer;
import HouseSelling.Model.Search;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SearchDao extends AbstractDao<Search> {
	private static SearchDao instance = null;

	public synchronized static SearchDao getInstance() {
		if (instance == null)
			instance = new SearchDao();
		return instance;
	}

	public SearchDao() {
	}

	@Override
	public Search create(Search search) throws SQLException {
		String st = "INSERT INTO Search(status,price,bed,bath,acreLot,fullAddress,street,city,state,zipCode,houseSize,userId) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement preSt = connection.prepareStatement(st, Statement.RETURN_GENERATED_KEYS);
		ResultSet resultSet = null;
		// fill the ? in preSt
		preSt.setBoolean(1, search.isStatus());
		preSt.setDouble(2, search.getPrice());
		preSt.setInt(3, search.getBed());
		preSt.setInt(4, search.getBath());
		preSt.setDouble(5, search.getAcreLot());
		preSt.setString(6, search.getFullAddress());
		preSt.setString(7, search.getStreet());
		preSt.setString(8, search.getCity());
		preSt.setString(9, search.getState());
		preSt.setInt(10, search.getZipCode());
		preSt.setInt(11, search.getHouseSize());
		preSt.setInt(12, search.getBuyer().getUserId());

		preSt.executeUpdate();
		resultSet = preSt.getGeneratedKeys();
		int generateId = resultSet.getInt(1);
		search.setSearchId(generateId);
		closeResource(preSt, resultSet);
		return search;
	}

	@Override
	public void delete(Search search) throws SQLException {
		String st = "DELETE FROM Search WHERE searchId=?";
		PreparedStatement preSt = connection.prepareStatement(st);
		ResultSet resultSet = null;
		// fill the ? in preSt
		preSt.setInt(1, search.getSearchId());
		preSt.executeUpdate();
		closeResource(preSt, resultSet);
	}

	@Override
	public Search getById(int id) throws SQLException {
		String st = "SELECT * FROM Search WHERE searchId=?";
		PreparedStatement preSt = connection.prepareStatement(st);
		ResultSet resultSet = null;
		preSt.setInt(1, id);
		resultSet = preSt.executeQuery();
		if (resultSet.next()) {
			boolean status = resultSet.getBoolean("status");
			double price = resultSet.getDouble("price");
			int bed = resultSet.getInt("bed");
			int bath = resultSet.getInt("bath");
			double acreLot = resultSet.getDouble("acreLot");

			String fullAddress = resultSet.getString("fullAddress");
			String street = resultSet.getString("street");
			String city = resultSet.getString("city");
			String state = resultSet.getString("state");
			int zipCode = resultSet.getInt("zipCode");
			int houseSize = resultSet.getInt("houseSize");
			Buyer buyer = BuyerDao.getInstance().getById(resultSet.getInt("userId"));
			return new Search(id, status, price, bed, bath, acreLot, fullAddress, street, city, state, zipCode,
					houseSize, buyer);
		}
		closeResource(preSt, resultSet);
		return null;
	}

	public List<Search> getSearchListByUserId(int userId) throws SQLException {
		String st = "SELECT searchId FROM Search WHERE userId=?";
		PreparedStatement preSt = connection.prepareStatement(st);
		ResultSet resultSet = null;
		List<Search> res = new ArrayList<>();
		preSt.setInt(1, userId);
		resultSet = preSt.executeQuery();
		while (resultSet.next()) {
			res.add(this.getById(resultSet.getInt("searchId")));
		}
		return res;
	}
}
