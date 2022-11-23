package HouseSelling.Model;

public class Company {
  private int companyId;
  private String companyName;
  private String description;

  public Company(int companyId, String companyName, String description) {
    this.companyId = companyId;
    this.companyName = companyName;
    this.description = description;
  }

  @Override
  public String toString() {
    return "Company{" +
        "companyId=" + companyId +
        ", companyName='" + companyName + '\'' +
        ", description='" + description + '\'' +
        '}';
  }

  public int getCompanyId() {
    return companyId;
  }

  public void setCompanyId(int companyId) {
    this.companyId = companyId;
  }

  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
