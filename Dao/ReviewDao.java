package HouseSelling.Dao;

import static HouseSelling.Utils.Helper.closeResource;

import HouseSelling.Model.Buyer;
import HouseSelling.Model.Review;
import HouseSelling.Model.Seller;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReviewDao extends AbstractDao<Review> {
	private static ReviewDao instance = null;

	public synchronized static ReviewDao getInstance() {
		if (instance == null)
			instance = new ReviewDao();
		return instance;
	}

	public ReviewDao() {
	}

	@Override
	public Review create(Review review) throws SQLException {
		String st = "INSERT INTO Review(created,review,rating,buyerId,sellerId) VALUES(?,?,?,?,?)";
		PreparedStatement preSt = connection.prepareStatement(st, Statement.RETURN_GENERATED_KEYS);
		ResultSet resultSet = null;
		// fill the ? in preSt
		preSt.setTimestamp(1, new Timestamp(review.getCreated().getTime()));
		preSt.setString(2, review.getReview());
		preSt.setDouble(3, review.getRating());
		preSt.setInt(4, review.getBuyer().getUserId());
		preSt.setInt(5, review.getSeller().getUserId());
		preSt.executeUpdate();
		resultSet = preSt.getGeneratedKeys();
		int generateId = resultSet.getInt(1);
		review.setReviewId(generateId);
		closeResource(preSt, resultSet);
		return review;
	}

	@Override
	public void delete(Review review) throws SQLException {
		String st = "DELETE FROM Review WHERE reviewId=?";
		PreparedStatement preSt = connection.prepareStatement(st);
		ResultSet resultSet = null;
		// fill the ? in preSt
		preSt.setInt(1, review.getReviewId());
		preSt.executeUpdate();
		closeResource(preSt, resultSet);
	}

	@Override
	public Review getById(int id) throws SQLException {
		String st = "SELECT * FROM Review WHERE reviewId=?";
		PreparedStatement preSt = connection.prepareStatement(st);
		ResultSet resultSet = null;
		preSt.setInt(1, id);
		resultSet = preSt.executeQuery();
		if (resultSet.next()) {
			Date created = new Date(resultSet.getTimestamp("created").getTime());
			String review = resultSet.getString("description");
			double rating = resultSet.getDouble("rating");
			Buyer buyer = BuyerDao.getInstance().getById(resultSet.getInt("buyerId"));
			Seller seller = SellerDao.getInstance().getById(resultSet.getInt("sellerId"));
			return new Review(id, created, review, rating, buyer, seller);
		}
		closeResource(preSt, resultSet);
		return null;
	}

	public List<Review> getReviewByUserId(int userId, boolean isBuyer) throws SQLException {
		List<Review> res = new ArrayList<>();
		if (isBuyer) {
			String st = "SELECT * FROM Review WHERE buyerId=?";
			PreparedStatement preSt = connection.prepareStatement(st);
			ResultSet resultSet = null;
			preSt.setInt(1, userId);
			resultSet = preSt.executeQuery();
			while (resultSet.next()) {
				int reviewId = resultSet.getInt("reviewId");
				Date created = new Date(resultSet.getTimestamp("created").getTime());
				String review = resultSet.getString("review");
				Double rating = resultSet.getDouble("rating");
				Buyer buyer = BuyerDao.getInstance().getById(resultSet.getInt("buyerId"));
				Seller seller = SellerDao.getInstance().getById(resultSet.getInt("sellerId"));
				res.add(new Review(reviewId, created, review, rating, buyer, seller));
			}
			;
			closeResource(preSt, resultSet);
		} else {
			ResultSet resultSet = null;
			String st = "SELECT * FROM Review WHERE sellerId=?";
			PreparedStatement preSt = connection.prepareStatement(st);
			preSt.setInt(1, userId);
			resultSet = preSt.executeQuery();
			while (resultSet.next()) {
				int reviewId = resultSet.getInt("reviewId");
				Date created = new Date(resultSet.getTimestamp("created").getTime());
				String review = resultSet.getString("review");
				Double rating = resultSet.getDouble("rating");
				Buyer buyer = BuyerDao.getInstance().getById(resultSet.getInt("buyerId"));
				Seller seller = SellerDao.getInstance().getById(resultSet.getInt("sellerId"));
				res.add(new Review(reviewId, created, review, rating, buyer, seller));
			}
			closeResource(preSt, resultSet);
		}
		return res;
	}
}
