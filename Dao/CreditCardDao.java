package HouseSelling.Dao;

import static HouseSelling.Utils.Helper.closeResource;

import HouseSelling.Model.Buyer;
import HouseSelling.Model.CreditCard;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CreditCardDao extends AbstractDao<CreditCard> {

	private static CreditCardDao instance = null;

	public synchronized static CreditCardDao getInstance() {
		if (instance == null) {
			instance = new CreditCardDao();
		}
		return instance;
	}

	public CreditCardDao() {
	}

	@Override
	public CreditCard create(CreditCard creditCard) throws SQLException {
		String st = "INSERT INTO CreditCard(cardNumber,expirationDate,buyerId) VALUES(?,?,?)";
		PreparedStatement preSt = connection.prepareStatement(st);
		ResultSet resultSet = null;
		// fill the ? in preSt
		preSt.setString(1, creditCard.getCardNumber());
		preSt.setDate(2, new Date(creditCard.getExpirationDate().getTime()));
		preSt.setInt(3, creditCard.getBuyer().getUserId());
		preSt.executeUpdate();
		closeResource(preSt, resultSet);
		return creditCard;
	}

	@Override
	public void delete(CreditCard creditCard) throws SQLException {
		String st = "DELETE FROM CreditCard WHERE cardNumber=?";
		PreparedStatement preSt = connection.prepareStatement(st);
		ResultSet resultSet = null;
		// fill the ? in preSt
		preSt.setString(1, creditCard.getCardNumber());
		preSt.executeUpdate();
		closeResource(preSt, resultSet);
	}

	public CreditCard getByCreditCard(String cardNumber) throws SQLException {
		String st = "SELECT * FROM CreditCard WHERE cardNumber=?";
		PreparedStatement preSt = connection.prepareStatement(st);
		ResultSet resultSet = null;
		preSt.setString(1, cardNumber);
		resultSet = preSt.executeQuery();
		if (resultSet.next()) {
			String cardNumber1 = resultSet.getString("cardNumber");
			java.util.Date expirationDate = new java.util.Date(resultSet.getDate("expirationDate").getTime());
			Buyer buyer = BuyerDao.getInstance().getById(resultSet.getInt("buyerId"));
			return new CreditCard(cardNumber1, expirationDate, buyer);
		}
		closeResource(preSt, resultSet);
		return null;
	}

	public List<CreditCard> getOwnedCreditCards(int userId) throws SQLException {
		List<CreditCard> res = new ArrayList<>();
		String st = "SELECT cardNumber from CreditCard where buyerId=?";
		PreparedStatement preSt = connection.prepareStatement(st);
		ResultSet resultSet = null;
		preSt.setInt(1, userId);
		resultSet = preSt.executeQuery();
		while (resultSet.next()) {
			res.add(this.getByCreditCard(resultSet.getString("cardNumber")));
		}
		closeResource(preSt, resultSet);
		return res;
	}

	public CreditCard update(CreditCard creditCard) throws SQLException {
		String st = "UPDATE CreditCard SET expirationDate=? WHERE cardNumber=?";
		PreparedStatement preSt = connection.prepareStatement(st);
		preSt.setDate(1, new Date(creditCard.getExpirationDate().getTime()));
		preSt.setString(2, creditCard.getCardNumber());
		ResultSet resultSet = null;
		preSt.executeUpdate();
		closeResource(preSt, resultSet);
		return creditCard;

	}
}
