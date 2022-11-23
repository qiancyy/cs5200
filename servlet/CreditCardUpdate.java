package HouseSelling.servlet;

import HouseSelling.Dao.CreditCardDao;
import HouseSelling.Dao.SellerDao;
import HouseSelling.Model.CreditCard;
import HouseSelling.Model.Seller;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/creditcardupdate")
public class CreditCardUpdate extends HttpServlet {
  protected CreditCardDao creditCardDao;
  protected String cardNumber;
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    Map<String, String> messages = new HashMap<>();
    req.setAttribute("messages", messages);
    cardNumber = req.getParameter("cardNumber");
    //cardNumber = "2220123244500";
    System.out.println(cardNumber);
    try {
    	 CreditCard creditCard = creditCardDao.getByCreditCard(cardNumber);
    	 System.out.println(creditCard);
      req.setAttribute("cardNumber",creditCard.getCardNumber());
      DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

      req.setAttribute("expirationDate",dateFormat.format(creditCard.getExpirationDate()));
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    req.getRequestDispatcher("CreditCardUpdate.jsp").forward(req,resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);
    
    try {
      CreditCard creditCard = creditCardDao.getByCreditCard(cardNumber);
      DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

      creditCard.setExpirationDate(dateFormat.parse(req.getParameter("expirationDate")));
      creditCardDao.update(creditCard);
      messages.put("success", "Successfully updated!");
    } catch (SQLException e) {
      throw new RuntimeException(e);
    } catch (ParseException e) {
      throw new RuntimeException(e);
    }
    req.getRequestDispatcher("CreditCardUpdate.jsp").forward(req,resp);
  }

  @Override
  public void init() throws ServletException {
    creditCardDao = CreditCardDao.getInstance();
  }
}
