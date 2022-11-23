package HouseSelling.Model;

public class Search {
	private int searchId;
	private boolean status;
	private double price;
	private int bed;
	private int bath;
	private double acreLot;
	private String fullAddress;
	private String street;
	private String city;
	private String state;
	private int zipCode;
	private int houseSize;
	private Buyer buyer;

	public Search(int searchId, boolean status, double price, int bed, int bath, double acreLot, String fullAddress,
			String street, String city, String state, int zipCode, int houseSize, Buyer buyer) {
		this.searchId = searchId;
		this.status = status;
		this.price = price;
		this.bed = bed;
		this.bath = bath;
		this.acreLot = acreLot;
		this.fullAddress = fullAddress;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.houseSize = houseSize;
		this.buyer = buyer;
	}

	public int getSearchId() {
		return searchId;
	}

	public void setSearchId(int searchId) {
		this.searchId = searchId;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getBed() {
		return bed;
	}

	public void setBed(int bed) {
		this.bed = bed;
	}

	public int getBath() {
		return bath;
	}

	public void setBath(int bath) {
		this.bath = bath;
	}

	public double getAcreLot() {
		return acreLot;
	}

	public void setAcreLot(double acreLot) {
		this.acreLot = acreLot;
	}

	public String getFullAddress() {
		return fullAddress;
	}

	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	public int getHouseSize() {
		return houseSize;
	}

	public void setHouseSize(int houseSize) {
		this.houseSize = houseSize;
	}

	public Buyer getBuyer() {
		return buyer;
	}

	public void setBuyer(Buyer buyer) {
		this.buyer = buyer;
	}

	@Override
	public String toString() {
		return "Search{" + "searchId=" + searchId + ", status=" + status + ", price=" + price + ", bed=" + bed
				+ ", bath=" + bath + ", acreLot=" + acreLot + ", fullAddress='" + fullAddress + '\'' + ", street='"
				+ street + '\'' + ", city='" + city + '\'' + ", state='" + state + '\'' + ", zipCode=" + zipCode
				+ ", houseSize=" + houseSize + ", buyer=" + buyer + '}';
	}
}
