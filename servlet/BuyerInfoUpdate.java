package HouseSelling.servlet;

import HouseSelling.Dao.BuyerDao;
import HouseSelling.Model.Buyer;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/buyerupdate")
public class BuyerInfoUpdate extends HttpServlet {
  protected BuyerDao buyerDao;
  protected String userId;
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    Buyer buyer;
    userId = req.getParameter("userId");
    try {
      buyer = buyerDao.getById(Integer.parseInt(userId));
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    req.setAttribute("buyer",buyer);
    req.getRequestDispatcher("BuyerInfoUpdate.jsp").forward(req,resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
	  System.out.println(1);
	  Map<String, String> messages = new HashMap<String, String>();
	    req.setAttribute("messages", messages);
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date date;
    try {
      date = dateFormat.parse(req.getParameter("DOB"));
    } catch (ParseException e) {
      throw new RuntimeException(e);
    }

    Buyer buyer = new Buyer(
        Integer.parseInt(userId),
        req.getParameter("firstName"),
        req.getParameter("middleName"),
        req.getParameter("lastName"),
        req.getParameter("email"),
        req.getParameter("phoneNumber"),
        req.getParameter("password"),
        Integer.parseInt(req.getParameter("zip")),
        req.getParameter("address"),
        date
    );
    System.out.println(buyer);
    try {
      buyerDao.update(buyer);
      messages.put("success", "Successfully updated!");
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    req.setAttribute("buyer",buyer);
    req.getRequestDispatcher("BuyerInfo.jsp").forward(req,resp);
  }

  @Override
  public void init() throws ServletException {
		buyerDao = BuyerDao.getInstance();
	}

}
