package HouseSelling.Model;

public class Loan {
  private int loadId;
  private Buyer buyer;
  private House house;
  private double downPaymentAmount;
  private double lendingRate;
  private double monthlyRepaymentAmount;

  public Loan(int loadId, Buyer buyer, House house, double downPaymentAmount, double lendingRate,
      double monthlyRepaymentAmount) {
    this.loadId = loadId;
    this.buyer = buyer;
    this.house = house;
    this.downPaymentAmount = downPaymentAmount;
    this.lendingRate = lendingRate;
    this.monthlyRepaymentAmount = monthlyRepaymentAmount;
  }

  public int getLoadId() {
    return loadId;
  }

  public void setLoadId(int loadId) {
    this.loadId = loadId;
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

  public double getDownPaymentAmount() {
    return downPaymentAmount;
  }

  public void setDownPaymentAmount(double downPaymentAmount) {
    this.downPaymentAmount = downPaymentAmount;
  }

  public double getLendingRate() {
    return lendingRate;
  }

  public void setLendingRate(double lendingRate) {
    this.lendingRate = lendingRate;
  }

  public double getMonthlyRepaymentAmount() {
    return monthlyRepaymentAmount;
  }

  public void setMonthlyRepaymentAmount(double monthlyRepaymentAmount) {
    this.monthlyRepaymentAmount = monthlyRepaymentAmount;
  }

  @Override
  public String toString() {
    return "Loan{" +
        "loadId=" + loadId +
        ", buyer=" + buyer +
        ", house=" + house +
        ", downPaymentAmount=" + downPaymentAmount +
        ", lendingRate=" + lendingRate +
        ", monthlyRepaymentAmount=" + monthlyRepaymentAmount +
        '}';
  }
}
