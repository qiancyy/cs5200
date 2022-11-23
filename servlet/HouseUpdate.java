package HouseSelling.servlet;

import HouseSelling.Dao.HouseDao;
import HouseSelling.Dao.SellerDao;
import HouseSelling.Model.House;
import HouseSelling.Model.Seller;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/houseupdate")
public class HouseUpdate extends HttpServlet {
  protected String houseId;
  protected String sellerId;
  protected HouseDao houseDao;
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
	  System.out.println("_________");
    houseId = req.getParameter("houseId");
    sellerId = req.getParameter("userId");
    //sellerId = "1";
    System.out.println(houseId);
    System.out.println(sellerId);
    House house;
    try {
      house = houseDao.getById(Integer.parseInt(houseId));
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    req.setAttribute("house",house);
    req.getRequestDispatcher("HouseUpdate.jsp").forward(req,resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String bed = req.getParameter("bed");
    String bath = req.getParameter("bath");
    String city= req.getParameter("city");
    String zipCode = req.getParameter("zipCode");
    String price = req.getParameter("price");
    SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
    Date date = new Date(System.currentTimeMillis());
    Seller seller;
    try {
      seller = SellerDao.getInstance().getById( Integer.parseInt(sellerId));
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    System.out.println(">>>>");
    System.out.println(req.getParameter("houseSize"));
    House house = new House(
        Integer.parseInt(houseId),
        "1",
        Double.parseDouble(price),
        Integer.parseInt(bed),
        Integer.parseInt(bath),
        Double.parseDouble(req.getParameter("acreLot")),
        req.getParameter("fullAddress"),
        req.getParameter("street"),
        req.getParameter("city"),
        req.getParameter("state"),
        Integer.parseInt(zipCode),
        Integer.parseInt(req.getParameter("houseSize")),
        date,
        seller
    );
    try {
      houseDao.update(house);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    req.getRequestDispatcher("HouseUpdate.jsp").forward(req,resp);



  }

  @Override
  public void init() throws ServletException {
    houseDao = HouseDao.getInstance();
  }
}
