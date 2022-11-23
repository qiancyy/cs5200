package HouseSelling.servlet;

import HouseSelling.Dao.HouseDao;
import HouseSelling.Model.House;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/findhouses")
public class FindHouse extends HttpServlet {

  protected HouseDao houseDao;
  protected int page;
  protected String bed;
  protected String bath;
  protected String zipCode;
  protected String price;
  protected String city;

  public FindHouse() {

  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    List<House> houseList;
//    bed = req.getParameter("bed");
//    bath = req.getParameter("bath");
//    city = req.getParameter("city");
//    zipCode = req.getParameter("zipCode");
//    price = req.getParameter("price");
    //System.out.println(bed+" "+bath+" "+city+" "+zipCode+" "+price);
    page = Integer.parseInt(req.getParameter("page"));
    boolean hasNext = true;
    try {
      houseList = houseDao.filterHouse(bed, bath, city, zipCode, price,20,page);
      hasNext = houseDao.filterHouseHasNext(bed, bath, city, zipCode, price, 20, page);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    req.setAttribute("page",page);
    req.setAttribute("hasNext", hasNext);
    req.setAttribute("houseList", houseList);
    req.getRequestDispatcher("/FindHouse.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    List<House> houseList = new ArrayList<>();
//    String bed = req.getParameter("bed");
//    String bath = req.getParameter("bath");
//    String city = req.getParameter("city");
//    String zipCode = req.getParameter("zipCode");
//    String price = req.getParameter("price");
//    System.out.println(bed + " " + bath + " " + city + " " + zipCode + " " + price);
//    try {
//      houseList = houseDao.filterHouse(bed, bath, city, zipCode, price);
//      System.out.println("____");
//      if (houseList.size() != 0) {
//        System.out.println(houseList.get(0));
//      }
//    } catch (SQLException e) {
//      throw new RuntimeException(e);
//    }
//    req.setAttribute("houseList", houseList);
//    req.getRequestDispatcher("/FindHouse.jsp").forward(req, resp);
    bed = req.getParameter("bed");
    bath = req.getParameter("bath");
    city = req.getParameter("city");
    zipCode = req.getParameter("zipCode");
    price = req.getParameter("price");
    //System.out.println(bed+" "+bath+" "+city+" "+zipCode+" "+price);
    //page = Integer.parseInt(req.getParameter("page"));
    boolean hasNext = true;
    try {
      houseList = houseDao.filterHouse(bed, bath, city, zipCode, price,20,1);
      hasNext = houseDao.filterHouseHasNext(bed, bath, city, zipCode, price, 20, 1);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    req.setAttribute("page",1);
    req.setAttribute("hasNext", hasNext);
    req.setAttribute("houseList", houseList);
    req.getRequestDispatcher("/FindHouse.jsp").forward(req, resp);
  }

  @Override
  public void init() throws ServletException {
    houseDao = HouseDao.getInstance();
  }
}
