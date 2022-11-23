package HouseSelling.servlet;

import HouseSelling.Dao.BuyerDao;
import HouseSelling.Dao.CreditCardDao;
import HouseSelling.Dao.SellerDao;
import HouseSelling.Model.Buyer;
import HouseSelling.Model.CreditCard;
import HouseSelling.Model.Seller;
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
@WebServlet("/creditcardcreate")
public class CreditCardCreate extends HttpServlet {
  protected CreditCardDao creditCardDao;
  protected String id;
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
	id = req.getParameter("userId");
	    //id = "2";
	System.out.println(id);
    req.getRequestDispatcher("CreditCardCreate.jsp").forward(req,resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    CreditCard creditCard;
    Map<String, String> messages = new HashMap<>();
    req.setAttribute("messages", messages);
//    String id = req.getParameter("userId");
    //id = "2";
    System.out.println(id);
    String cardNumber= req.getParameter("cardNumber");
    String expirationDate = req.getParameter("expirationDate");
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date date = new Date();
    try {
      date = dateFormat.parse(expirationDate);
      Buyer buyer = BuyerDao.getInstance().getById(Integer.parseInt(id));
      creditCard = creditCardDao.create(new CreditCard(cardNumber,date,buyer));
      messages.put("success", "Successfully created " + creditCard.getCardNumber());
    } catch (SQLException e) {
      throw new RuntimeException(e);
    } catch (ParseException e) {
      throw new RuntimeException(e);
    }
    req.getRequestDispatcher("CreditCardCreate.jsp").forward(req,resp);
  }

  @Override
  public void init() throws ServletException {
    creditCardDao = CreditCardDao.getInstance();
  }
}
