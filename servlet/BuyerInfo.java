package HouseSelling.servlet;

import HouseSelling.Dao.BuyerDao;
import HouseSelling.Dao.HouseDao;
import HouseSelling.Dao.SellerDao;
import HouseSelling.Model.Buyer;
import HouseSelling.Model.House;
import HouseSelling.Model.Seller;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/buyerinfo")
public class BuyerInfo extends HttpServlet {
  protected BuyerDao buyerDao;
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    Buyer buyer;
    String id = req.getParameter("userId");
    id = "2";
    try {
      buyer = buyerDao.getById(Integer.parseInt(id));
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    req.setAttribute("buyer",buyer);
    req.getRequestDispatcher("BuyerInfo.jsp").forward(req,resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    super.doPost(req, resp);
  }

  @Override
  public void init() throws ServletException {
    buyerDao = BuyerDao.getInstance();
  }

}
