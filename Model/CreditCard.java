package HouseSelling.Model;

import java.util.Date;

public class CreditCard {
  private String cardNumber;
  private Date expirationDate;
  private Buyer buyer;
  private String simpleDate;

  public CreditCard(String cardNumber, Date expirationDate, Buyer buyer) {
    this.cardNumber = cardNumber;
    this.expirationDate = expirationDate;
    this.buyer = buyer;
    simpleDate = "";
  }

  public String getCardNumber() {
    return cardNumber;
  }

  public void setCardNumber(String cardNumber) {
    this.cardNumber = cardNumber;
  }

  public Date getExpirationDate() {
    return expirationDate;
  }

  public void setExpirationDate(Date expirationDate) {
    this.expirationDate = expirationDate;
  }

  public Buyer getBuyer() {
    return buyer;
  }

  public void setBuyer(Buyer buyer) {
    this.buyer = buyer;
  }
  

  public String getSimpleDate() {
    return simpleDate;
  }

  public void setSimpleDate(String s) {
    this.simpleDate = s;
  }
  

  @Override
  public String toString() {
    return "CreditCard{" +
        "cardNumber='" + cardNumber + '\'' +
        ", expirationDate=" + expirationDate +
        ", buyer=" + buyer +
        '}';
  }
}
