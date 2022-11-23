package HouseSelling.Dao;

import static HouseSelling.Utils.Helper.closeResource;

import HouseSelling.Model.Buyer;
import HouseSelling.Model.House;
import HouseSelling.Model.Loan;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LoanDao extends AbstractDao<Loan> {

	public LoanDao() {
	}

	private static LoanDao instance = null;

	public synchronized static LoanDao getInstance() {
		if (instance == null)
			instance = new LoanDao();
		return instance;
	}

	@Override
	public Loan create(Loan loan) throws SQLException {
		String st = "INSERT INTO Loan(buyerId,houseId,downPaymentAmount,lendingRate,monthlyRepaymentAmount) VALUES(?,?,?,?,?)";
		PreparedStatement preSt = connection.prepareStatement(st, Statement.RETURN_GENERATED_KEYS);
		ResultSet resultSet = null;
		// fill the ? in preSt
		preSt.setInt(1, loan.getBuyer().getUserId());
		preSt.setInt(2, loan.getHouse().getHouseId());
		preSt.setDouble(3, loan.getDownPaymentAmount());
		preSt.setDouble(4, loan.getLendingRate());
		preSt.setDouble(5, loan.getMonthlyRepaymentAmount());
		preSt.executeUpdate();
		resultSet = preSt.getGeneratedKeys();
		int generateId = resultSet.getInt(1);
		loan.setLoadId(generateId);
		closeResource(preSt, resultSet);
		return loan;
	}

	@Override
	public void delete(Loan loan) throws SQLException {
		String st = "DELETE FROM Loan WHERE loanId=?";
		PreparedStatement preSt = connection.prepareStatement(st);
		ResultSet resultSet = null;
		// fill the ? in preSt
		preSt.setInt(1, loan.getLoadId());
		preSt.executeUpdate();
		closeResource(preSt, resultSet);
	}

	@Override
	public Loan getById(int id) throws SQLException {
		String st = "SELECT * FROM Loan WHERE loanId=?";
		PreparedStatement preSt = connection.prepareStatement(st);
		ResultSet resultSet = null;
		preSt.setInt(1, id);
		resultSet = preSt.executeQuery();
		if (resultSet.next()) {
			int buyerId = resultSet.getInt("buyerId");
			Buyer buyer = BuyerDao.getInstance().getById(buyerId);
			int houseId = resultSet.getInt("houseId");
			House house = HouseDao.getInstance().getById(houseId);
			double downPaymentAmount = resultSet.getDouble("downPaymentAmount");
			double lendingRate = resultSet.getDouble("lendingRate");
			double monthlyRepaymentAmount = resultSet.getDouble("monthlyRepaymentAmount");
			return new Loan(id, buyer, house, downPaymentAmount, lendingRate, monthlyRepaymentAmount);
		}
		closeResource(preSt, resultSet);
		return null;
	}
//
//  // problem 7
//  public List<String> getFirstThreeDownPaymentRange() throws SQLException {
//    List<String> res = new ArrayList<>();
//    String st = "select "
//        + " concat(payment * 50000, \" to \", (payment + 1) * 50000) as paymentRange  "
//        + "from ( "
//        + "select "
//        + " floor(downPaymentAmount/50000) as payment "
//        + " ,count(*) as num "
//        + "from Loan "
//        + "group by payment "
//        + "order by num "
//        + "limit 3) pay;";
//    PreparedStatement preSt = connection.prepareStatement(st);
//    ResultSet resultSet = null;
//    resultSet = preSt.executeQuery();
//    while(resultSet.next()){
//      String paymentRange = resultSet.getString("paymentRange");
//      res.add(paymentRange);
//    }
//    closeResource(preSt,resultSet);
//    return res;
//  }
}
