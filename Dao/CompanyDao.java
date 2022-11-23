package HouseSelling.Dao;

import static HouseSelling.Utils.Helper.closeResource;

import HouseSelling.Model.Company;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CompanyDao extends AbstractDao<Company> {
	private static CompanyDao instance = null;

	public synchronized static CompanyDao getInstance() {
		if (instance == null)
			instance = new CompanyDao();
		return instance;
	}

	public CompanyDao() {
	}

	@Override
	public Company create(Company company) throws SQLException {
		String st = "INSERT INTO Company(companyName,description) VALUES(?,?)";
		PreparedStatement preSt = connection.prepareStatement(st, Statement.RETURN_GENERATED_KEYS);
		ResultSet resultSet = null;
		// fill the ? in preSt
		preSt.setString(1, company.getCompanyName());
		preSt.setString(2, company.getDescription());
		preSt.executeUpdate();
		resultSet = preSt.getGeneratedKeys();
		int generateId = resultSet.getInt(1);
		company.setCompanyId(generateId);
		closeResource(preSt, resultSet);
		return company;
	}

	@Override
	public void delete(Company company) throws SQLException {
		String st = "DELETE FROM Company WHERE companyId=?";
		PreparedStatement preSt = connection.prepareStatement(st);
		ResultSet resultSet = null;
		// fill the ? in preSt
		preSt.setInt(1, company.getCompanyId());
		preSt.executeUpdate();
		closeResource(preSt, resultSet);
	}

	@Override
	public Company update(Company company, String columnName, Object value) throws SQLException {
		String st = "UPDATE Company SET " + columnName + "=? " + "WHERE companyId=?";
		PreparedStatement preSt = connection.prepareStatement(st);
		ResultSet resultSet = null;
		switch (columnName) {
		case "description" -> {
			String string = (String) value;
			preSt.setString(1, string);
			company.setDescription(string);
		}
		}
		preSt.setInt(2, company.getCompanyId());
		preSt.executeUpdate();
		closeResource(preSt, resultSet);
		return company;
	}

	@Override
	public Company getById(int id) throws SQLException {
		String st = "SELECT * FROM Company WHERE companyId=?";
		PreparedStatement preSt = connection.prepareStatement(st);
		ResultSet resultSet = null;
		preSt.setInt(1, id);
		resultSet = preSt.executeQuery();
		if (resultSet.next()) {
			String companyName = resultSet.getString("companyName");
			String description = resultSet.getString("description");
			return new Company(id, companyName, description);
		}
		closeResource(preSt, resultSet);
		return null;
	}
	// problem 9
//  public List<CompanyWithHighVolume> getCompanyWithHighVolume()throws SQLException{
//    List<CompanyWithHighVolume> res = new ArrayList<>();
//    String st = "";
//    PreparedStatement preSt = connection.prepareStatement(st);
//    ResultSet resultSet = null;
//    resultSet = preSt.executeQuery();
//    while(resultSet.next()){
//      String state = resultSet.getString("state");
//      int companyId = resultSet.getInt("companyId");
//      res.add(new CompanyWithHighVolume(state,companyId));
//    }
//    closeResource(connection,preSt,resultSet);
//    return res;
//  }
//  // problem 8
//  public List<BestSellingType> getBestSellingType() throws SQLException{
//    List<BestSellingType> res = new ArrayList<>();
//    String st = "";
//    PreparedStatement preSt = connection.prepareStatement(st);
//    ResultSet resultSet = null;
//    resultSet = preSt.executeQuery();
//    while(resultSet.next()){
//      int companyId = resultSet.getInt("companyId");
//      String houseType = resultSet.getString("houseType");
//      res.add(new BestSellingType(companyId,houseType));
//    }
//    closeResource(connection,preSt,resultSet);
//    return res;
//  }
}
