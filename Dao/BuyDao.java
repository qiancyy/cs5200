package HouseSelling.Dao;

import static HouseSelling.Utils.Helper.closeResource;

import HouseSelling.Model.Buy;
import HouseSelling.Model.Buyer;
import HouseSelling.Model.House;
import HouseSelling.Model.Seller;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BuyDao extends AbstractDao<Buy> {

	public BuyDao() {
	}

	private static BuyDao instance = null;

	public synchronized static BuyDao getInstance() {
		if (instance == null)
			instance = new BuyDao();
		return instance;
	}

	@Override
	public Buy create(Buy buy) throws SQLException {
		String st = "INSERT INTO Buy(buyerId,houseId,date,sellerId) VALUES(?,?,?,?)";
		PreparedStatement preSt = connection.prepareStatement(st, Statement.RETURN_GENERATED_KEYS);
		ResultSet resultSet = null;
		// fill the ? in preSt
		preSt.setInt(1, buy.getBuyer().getUserId());
		preSt.setInt(2, buy.getHouse().getHouseId());
		preSt.setDate(3, new Date(buy.getDate().getTime()));
		preSt.setInt(4, buy.getSeller().getUserId());
		preSt.executeUpdate();
		resultSet = preSt.getGeneratedKeys();
		int generateId = resultSet.getInt(1);
		buy.setTransactionId(generateId);
		closeResource(preSt, resultSet);
		return buy;
	}

	@Override
	public void delete(Buy buy) throws SQLException {
		String st = "DELETE FROM Buy WHERE transactionId=?";
		PreparedStatement preSt = connection.prepareStatement(st);
		ResultSet resultSet = null;
		// fill the ? in preSt
		preSt.setInt(1, buy.getTransactionId());
		preSt.executeUpdate();
		closeResource(preSt, resultSet);
	}

	@Override
	public Buy getById(int id) throws SQLException {
		String st = "SELECT * FROM Buy WHERE transactionId=?";
		PreparedStatement preSt = connection.prepareStatement(st);
		ResultSet resultSet = null;
		preSt.setInt(1, id);
		resultSet = preSt.executeQuery();
		if (resultSet.next()) {
			int buyerId = resultSet.getInt("buyerId");
			int houseId = resultSet.getInt("houseId");
			java.util.Date date = new java.util.Date(resultSet.getDate("date").getTime());
			int sellerId = resultSet.getInt("sellerId");
			Buyer buyer = BuyerDao.getInstance().getById(buyerId);
			Seller seller = SellerDao.getInstance().getById(sellerId);
			House house = HouseDao.getInstance().getById(houseId);
			return new Buy(id, buyer, house, date, seller);
		}
		closeResource(preSt, resultSet);
		return null;
	}

}
