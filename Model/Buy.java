package HouseSelling.Model;

import java.util.Date;

public class Buy {
	private int transactionId;
	private Buyer buyer;
	private House house;
	private Date date;
	private Seller seller;

	public Buy(int transactionId, Buyer buyer, House house, Date date, Seller seller) {
		this.transactionId = transactionId;
		this.buyer = buyer;
		this.house = house;
		this.date = date;
		this.seller = seller;
	}

	@Override
	public String toString() {
		return "Buy{" + "transactionId=" + transactionId + ", buyer=" + buyer + ", house=" + house + ", date=" + date
				+ ", seller=" + seller + '}';
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public Buyer getBuyer() {
		return buyer;
	}

	public void setBuyer(Buyer buyer) {
		this.buyer = buyer;
	}

	public House getHouse() {
		return house;
	}

	public void setHouse(House house) {
		this.house = house;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}
}
