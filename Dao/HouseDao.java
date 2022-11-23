package HouseSelling.Dao;

import static HouseSelling.Utils.Helper.closeResource;

import HouseSelling.Model.House;
import HouseSelling.Model.Seller;
import HouseSelling.Utils.ConnectionManager;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HouseDao extends AbstractDao<House> {
	private static HouseDao instance = null;

	public synchronized static HouseDao getInstance() {
		if (instance == null)
			instance = new HouseDao();
		return instance;
	}

	public HouseDao() {
	}

	@Override
	public House create(House house) throws SQLException {
		String st = "INSERT INTO House(status,price,bed,bath,acreLot,fullAddress,street,city,state,zipCode,houseSize,soldDate,sellerId) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		if (connection == null)
			connection = new ConnectionManager().getConnection();
		PreparedStatement preSt = connection.prepareStatement(st, Statement.RETURN_GENERATED_KEYS);
		ResultSet resultSet = null;
		// fill the ? in preSt
		preSt.setString(1, house.getStatus());
		preSt.setDouble(2, house.getPrice());
		preSt.setInt(3, house.getBed());
		preSt.setInt(4, house.getBath());
		preSt.setDouble(5, house.getAcreLot());
		preSt.setString(6, house.getFullAddress());
		preSt.setString(7, house.getStreet());
		preSt.setString(8, house.getCity());
		preSt.setString(9, house.getState());
		preSt.setInt(10, house.getZipCode());
		preSt.setInt(11, house.getHouseSize());
		preSt.setDate(12, new Date(house.getSoldDate().getTime()));
		preSt.setInt(13, house.getSeller().getUserId());
		preSt.executeUpdate();
		resultSet = preSt.getGeneratedKeys();
		int generateId = -1;
		if (resultSet.next()) {
			generateId = resultSet.getInt(1);
		}
		house.setHouseId(generateId);
		closeResource(preSt, resultSet);
		return house;
	}

	@Override
	public void delete(House house) throws SQLException {
		String st = "DELETE FROM House WHERE houseId=?";
		PreparedStatement preSt = connection.prepareStatement(st);
		ResultSet resultSet = null;
		// fill the ? in preSt
		preSt.setInt(1, house.getHouseId());
		preSt.executeUpdate();
		closeResource(preSt, resultSet);
	}

	public void update(House house) throws SQLException {
		System.out.println(house);
		String st = "UPDATE House Set bed=?,bath=?,price=?,acreLot=?,fullAddress=?,street=?,city=?,state=?,zipCode=?,houseSize=? WHERE houseId=?";
		PreparedStatement preSt = connection.prepareStatement(st);
		ResultSet resultSet = null;
		// fill the ? in preSt
		preSt.setInt(1, house.getBed());
		preSt.setInt(2, house.getBath());
		preSt.setDouble(3, house.getPrice());
		preSt.setDouble(4, house.getAcreLot());
		preSt.setString(5, house.getFullAddress());
		preSt.setString(6, house.getStreet());
		preSt.setString(7, house.getCity());
		preSt.setString(8, house.getState());
		preSt.setInt(9, house.getZipCode());
		preSt.setInt(10, house.getHouseSize());
		// preSt.setDate(11,new Date(house.getSoldDate().getTime()));
		preSt.setInt(11, house.getHouseId());
		preSt.executeUpdate();
		closeResource(preSt, resultSet);
	}

	@Override
	public House getById(int id) throws SQLException {
		String st = "SELECT * FROM House WHERE houseId=?";
		if (connection == null)
			connection = new ConnectionManager().getConnection();
		PreparedStatement preSt = connection.prepareStatement(st);
		ResultSet resultSet = null;
		preSt.setInt(1, id);
		resultSet = preSt.executeQuery();
		if (resultSet.next()) {
			String status = resultSet.getString("status");
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
			java.util.Date soldDate;
			if (resultSet.getDate("soldDate") == null) {
				soldDate = null;
			} else
				soldDate = new java.util.Date(resultSet.getDate("soldDate").getTime());
			Seller seller = SellerDao.getInstance().getById(resultSet.getInt("sellerId"));
			return new House(id, status, price, bed, bath, acreLot, fullAddress, street, city, state, zipCode,
					houseSize, soldDate, seller);
		}
		closeResource(preSt, resultSet);
		return null;
	}

	public List<House> getHouseBySeller(int userId) throws SQLException {
		String st = "SELECT houseId FROM House WHERE sellerId=?";
		if (connection == null)
			connection = new ConnectionManager().getConnection();
		PreparedStatement preSt = connection.prepareStatement(st);
		ResultSet resultSet = null;
		preSt.setInt(1, userId);
		resultSet = preSt.executeQuery();
		List<House> res = new ArrayList<>();
		while (resultSet.next()) {
			res.add(this.getById(resultSet.getInt("houseId")));
		}
		return res;
	}

	public List<House> filterHouse(String bed, String bath, String city, String zipCode, String price)
			throws SQLException {
		// price, bed, bath, city, zipcode
		List<House> res = new ArrayList<>();
		if (connection == null)
			connection = new ConnectionManager().getConnection();
		String st = "SELECT houseId FROM House WHERE bed=? and bath=? and city=? and zipcode=? and price < ?";
		String bed1 = bed;

		if (bed == null || bed == "") {
			bed1 = "bed";
		}
		String bath1 = bath;
		if (bath == null || bath == "") {
			bath1 = "bath";
		}
		String city1 = city;
		if (city == null || city == "") {
			city1 = "city";
		}
		String zipCode1 = zipCode;
		if (zipCode == null || zipCode == "") {
			zipCode1 = "zipCode";
		}
		String price1 = price;
		if (price == null || price == "") {
			price1 = "price";
		}
		String sst;
		if (city == null || city == "") {
			sst = "SELECT houseId FROM House WHERE bed=" + bed1 + " and bath=" + bath1 + " and zipCode=" + zipCode1
					+ " and price<=" + price1;
		} else {
			sst = "SELECT houseId FROM House WHERE bed=" + bed1 + " and bath=" + bath1 + " and zipCode=" + zipCode1
					+ " and price<=" + price1 + " and city='" + city1 + "'";
		}
//    sst+=" limit 20";

		System.out.println(sst);
		PreparedStatement preSt = connection.prepareStatement(sst);

		ResultSet resultSet = null;
		resultSet = preSt.executeQuery();
		while (resultSet.next()) {

			int id = resultSet.getInt("houseId");
			res.add(this.getById(id));
		}
		closeResource(preSt, resultSet);
		return res;
	}

	public List<House> filterHouse(String bed, String bath, String city, String zipCode, String price, int pageSize,
			int pageIndex) throws SQLException {
		// price, bed, bath, city, zipcode
		List<House> res = new ArrayList<>();
		if (connection == null)
			connection = new ConnectionManager().getConnection();
		String bed1 = bed;

		if (bed == null || bed == "") {
			bed1 = "bed";
		}
		String bath1 = bath;
		if (bath == null || bath == "") {
			bath1 = "bath";
		}
		String city1 = city;
		if (city == null || city == "") {
			city1 = "city";
		}
		String zipCode1 = zipCode;
		if (zipCode == null || zipCode == "") {
			zipCode1 = "zipCode";
		}
		String price1 = price;
		if (price == null || price == "") {
			price1 = "price";
		}
		String sst;
		if (city == null || city == "") {
			sst = "SELECT houseId FROM House WHERE bed=" + bed1 + " and bath=" + bath1 + " and zipCode=" + zipCode1
					+ " and price<=" + price1;
		} else {
			sst = "SELECT houseId FROM House WHERE bed=" + bed1 + " and bath=" + bath1 + " and zipCode=" + zipCode1
					+ " and price<=" + price1 + " and city='" + city1 + "'";
		}
//	    sst+=" limit 20";

		int offset = (pageIndex - 1) * pageSize;
		sst = sst + " LIMIT ? OFFSET ?";
		System.out.println(sst);
		PreparedStatement preSt = connection.prepareStatement(sst);
		preSt.setInt(1, pageSize);
		preSt.setInt(2, offset);
		ResultSet resultSet = null;
		resultSet = preSt.executeQuery();
		while (resultSet.next()) {

			int id = resultSet.getInt("houseId");
			res.add(this.getById(id));
		}
		closeResource(preSt, resultSet);
		return res;
	}

	public boolean filterHouseHasNext(String bed, String bath, String city, String zipCode, String price, int pageSize,
			int pageIndex) throws SQLException {
		if (connection == null)
			connection = new ConnectionManager().getConnection();
		String bed1 = bed;

		if (bed == null || bed == "") {
			bed1 = "bed";
		}
		String bath1 = bath;
		if (bath == null || bath == "") {
			bath1 = "bath";
		}
		String city1 = city;
		if (city == null || city == "") {
			city1 = "city";
		}
		String zipCode1 = zipCode;
		if (zipCode == null || zipCode == "") {
			zipCode1 = "zipCode";
		}
		String price1 = price;
		if (price == null || price == "") {
			price1 = "price";
		}
		String sst;
		if (city == null || city == "") {
			sst = "SELECT houseId FROM House WHERE bed=" + bed1 + " and bath=" + bath1 + " and zipCode=" + zipCode1
					+ " and price<=" + price1;
		} else {
			sst = "SELECT houseId FROM House WHERE bed=" + bed1 + " and bath=" + bath1 + " and zipCode=" + zipCode1
					+ " and price<=" + price1 + " and city='" + city1 + "'";
		}

		int offset = (pageIndex - 1) * pageSize;
		sst = sst + " LIMIT ? OFFSET ?";
		PreparedStatement preSt = connection.prepareStatement(sst);
		preSt.setInt(1, pageSize + 1);
		preSt.setInt(2, offset);
		ResultSet resultSet = null;
		resultSet = preSt.executeQuery();
		int cnt = 0;
		while (resultSet.next()) {
			cnt = cnt + 1;
		}
		closeResource(preSt, resultSet);
		return cnt == pageSize + 1;
	}

	public static void main(String[] args) throws SQLException {
		HouseDao housedao = HouseDao.getInstance();

		for (House house : housedao.filterHouse("3", "2", "Ponce", "731", "")) {
			System.out.println(house);
		}
	}

	// problem1
//  public List<HousePriceRange> getHousePriceRange()throws SQLException{
//    List<HousePriceRange> res = new ArrayList<>();
//    String st = "select state,max(price) as highestPrice,min(price) as lowestPrice from House where price is not null and price > 0 group by state;";
//    PreparedStatement preSt = connection.prepareStatement(st);
//    ResultSet resultSet = null;
//    resultSet = preSt.executeQuery();
//    while(resultSet.next()){
//      String state = resultSet.getString("state");
//      double highestPrice=resultSet.getDouble("highestPrice");
//      double lowestPrice = resultSet.getDouble("lowestPrice");
//      HousePriceRange housePriceRange = new HousePriceRange(state,lowestPrice,highestPrice);
//      res.add(housePriceRange);
//    }
//    closeResource(connection,preSt,resultSet);
//    return res;
//  }
//
//  // problem2
//  public List<AverageHousePrice>getAverageHousePrice()throws SQLException{
//    List<AverageHousePrice> res = new ArrayList<>();
//    String st = "select zipCode,sum(case when timestampdiff(MONTH, soldDate, CURRENT_DATE()) <= 1 THEN price else 0 end )/sum(case when timestampdiff(MONTH, soldDate, CURRENT_DATE()) <= 1 then 1 else 0 end ) as avg_month,sum(case when timestampdiff(MONTH, soldDate, CURRENT_DATE()) <= 6 THEN price else 0 end )/sum(case when timestampdiff(MONTH, soldDate, CURRENT_DATE()) <= 6 then 1 else 0 end ) as avg_half_year,sum(case when timestampdiff(YEAR, soldDate, CURRENT_DATE()) <= 1 THEN price else 0 end )/sum(case when timestampdiff(MONTH, soldDate, CURRENT_DATE()) <= 1 then 1 else 0 end ) as avg_year "
//        + "from House where price is not null and soldDate is not null and timestampdiff(YEAR, soldDate, CURRENT_DATE()) <= 1 group by zipCode;";
//    PreparedStatement preSt = connection.prepareStatement(st);
//    ResultSet resultSet = null;
//    resultSet = preSt.executeQuery();
//    while(resultSet.next()){
//      int zipCode = resultSet.getInt("zipCode");
//      double avg_month=resultSet.getDouble("avg_month");
//      double avg_half_year = resultSet.getDouble("avg_half_year");
//      double avg_year = resultSet.getDouble("avg_year");
//      AverageHousePrice averageHousePrice = new AverageHousePrice(zipCode,avg_month,avg_half_year,avg_year);
//      res.add(averageHousePrice);
//    }
//    closeResource(connection,preSt,resultSet);
//    return res;
//  }
//  // problem3
//  public List<PopularPriceRange> getPopularPriceRange()throws SQLException{
//    List<PopularPriceRange> res = new ArrayList<>();
//    String st = "select state,concat(priceRange * 100000, \" to \", (priceRange + 1) * 100000 ) as curPriceRange from (select floor(price/100000) as priceRange, state from House "
//        + "where price is not null "
//        + "and soldDate is not null "
//        + "and timestampdiff(YEAR, soldDate, CURRENT_DATE()) <= 3 ) rangeTable "
//        + "group by "
//        + " state "
//        + ",curPriceRange "
//        + "having count(*) > 0 "
//        + "limit 1;";
//    PreparedStatement preSt = connection.prepareStatement(st);
//    ResultSet resultSet = null;
//    resultSet = preSt.executeQuery();
//    while(resultSet.next()){
//      String state = resultSet.getString("state");
//      String curPriceRange = resultSet.getString("curPriceRange");
//      PopularPriceRange popularPriceRange = new PopularPriceRange(state,curPriceRange);
//      res.add(popularPriceRange);
//    }
//    closeResource(connection,preSt,resultSet);
//    return res;
//  }
//  // problem4
//  public List<String> getPopularHouseType()throws SQLException{
//    List<String> res = new ArrayList<>();
//    String st = "Ïselect houseType from (select concat(bed + \" bed \" + bath + \" bath\") as houseType "
//        + "from House "
//        + "where price is not null "
//        + "and soldDate is not null ) typeHouse "
//        + "group by houseType "
//        + "having count(*) > 0 "
//        + "limit 3;";
//    PreparedStatement preSt = connection.prepareStatement(st);
//    ResultSet resultSet = null;
//    resultSet = preSt.executeQuery();
//    while(resultSet.next()){
//      String houseType = resultSet.getString("houseType");
//      res.add(houseType);
//    }
//    closeResource(connection,preSt,resultSet);
//    return res;
//  }
//  // problem5
//  public List<String> getCityWithHighTransaction() throws SQLException {
//    List<String> res = new ArrayList<>();
//    String st = "select "
//        + " city "
//        + "from House "
//        + "where price is not null "
//        + "and soldDate is not null "
//        + "group by city "
//        + "having count(*) > 0 "
//        + "limit 10;";
//    PreparedStatement preSt = connection.prepareStatement(st);
//    ResultSet resultSet = null;
//    resultSet = preSt.executeQuery();
//    while(resultSet.next()){
//      String city = resultSet.getString("city");
//      res.add(city);
//    }
//    closeResource(connection,preSt,resultSet);
//    return res;
//  }
//  // problem6
//  public List<MonthWithHighLowVolume> getMonthWithHighLowVolume()  throws SQLException{
//    List<MonthWithHighLowVolume> res = new ArrayList<>();
//    String st = "";
//    PreparedStatement preSt = connection.prepareStatement(st);
//    ResultSet resultSet = null;
//    resultSet = preSt.executeQuery();
//    while(resultSet.next()){
//      int soldYear = resultSet.getInt("soldYear");
//      int highestMonth = resultSet.getInt("highestMonth");
//      int lowestMonth = resultSet.getInt("lowestMonth");
//      res.add(new MonthWithHighLowVolume(soldYear,highestMonth,lowestMonth));
//    }
//    closeResource(connection,preSt,resultSet);
//    return res;
//  }
}
