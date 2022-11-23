package HouseSelling.servlet;

import HouseSelling.Dao.HouseDao;
import HouseSelling.Dao.SellerDao;
import HouseSelling.Model.House;
import HouseSelling.Model.Seller;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/housecreate")
public class HouseCreate extends HttpServlet {

  protected String sellerId;
  protected HouseDao houseDao;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    sellerId = req.getParameter("sellerId");
    req.getRequestDispatcher("HouseCreate.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String bed = req.getParameter("bed");
    String bath = req.getParameter("bath");
    String city = req.getParameter("city");
    String zipCode = req.getParameter("zipCode");
    String price = req.getParameter("price");
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    Date date = new Date(System.currentTimeMillis());
    Seller seller;
    try {
      seller = SellerDao.getInstance().getById(Integer.parseInt(sellerId));
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    System.out.println(">>>>");
    System.out.println(req.getParameter("fullAddress"));
    House house = new House(
        -1,
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
      houseDao.create(house);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    req.getRequestDispatcher("HouseCreate.jsp").forward(req, resp);
  }

  @Override
  public void init() throws ServletException {
    houseDao = HouseDao.getInstance();
  }
}
