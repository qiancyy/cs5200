package HouseSelling.Model;

import java.util.Date;

public class House {

  private int houseId;
  private String status;
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
  private Date soldDate;
  private Seller seller;
  public House(){

  }
  public House(int houseId, String status, double price, int bed, int bath, double acreLot,
      String fullAddress, String street, String city, String state, int zipCode, int houseSize, Date soldDate,
      Seller seller) {
    this.houseId = houseId;
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
    this.soldDate = soldDate;
    this.seller = seller;
  }

  public int getHouseId() {
    return houseId;
  }

  public void setHouseId(int houseId) {
    this.houseId = houseId;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
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

  public Date getSoldDate() {
    return soldDate;
  }

  public void setSoldDate(Date soldDate) {
    this.soldDate = soldDate;
  }

  public Seller getSeller() {
    return seller;
  }

  public void setSeller(Seller seller) {
    this.seller = seller;
  }

  @Override
  public String toString() {
    return "House{" +
        "houseId=" + houseId +
        ", status='" + status + '\'' +
        ", price=" + price +
        ", bed=" + bed +
        ", bath=" + bath +
        ", acreLot=" + acreLot +
        ", fullAddress='" + fullAddress + '\'' +
        ", city='" + city + '\'' +
        ", state='" + state + '\'' +
        ", zipCode=" + zipCode +
        ", houseSize=" + houseSize +
        ", soldDate=" + soldDate +
        ", sellerId=" + seller +
        '}';
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }
}
