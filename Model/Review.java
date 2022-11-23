package HouseSelling.Model;

import java.util.Date;

public class Review {
	private int reviewId;
	private Date created;
	private String review;
	private double rating;
	private Buyer buyer;
	private Seller seller;

	public Review(int reviewId, Date created, String review, double rating, Buyer buyer, Seller seller) {
		this.reviewId = reviewId;
		this.created = created;
		this.review = review;
		this.rating = rating;
		this.buyer = buyer;
		this.seller = seller;
	}

	@Override
	public String toString() {
		return "Review{" + "reviewId=" + reviewId + ", created=" + created + ", review='" + review + '\'' + ", rating="
				+ rating + ", buyer=" + buyer + ", seller=" + seller + '}';
	}

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public Buyer getBuyer() {
		return buyer;
	}

	public void setBuyer(Buyer buyer) {
		this.buyer = buyer;
	}

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}
}
