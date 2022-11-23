package HouseSelling.servlet;

import HouseSelling.Dao.HouseDao;
import HouseSelling.Dao.SellerDao;
import HouseSelling.Model.House;
import HouseSelling.Model.Seller;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/housedelete")
public class HouseDelete extends HttpServlet {
  protected String houseId;
  protected HouseDao houseDao;
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    Map<String, String> messages = new HashMap<>();
    req.setAttribute("messages", messages);
    houseId = req.getParameter("houseId");
    try {
      House house = houseDao.getById(Integer.parseInt(houseId));
      houseDao.delete(house);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    messages.put("success", "Successfully deleted house");
    req.getRequestDispatcher("DeleteResult.jsp").forward(req,resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    this.doGet(req,resp);

  }

  @Override
  public void init() throws ServletException {
    houseDao = HouseDao.getInstance();
  }
}
