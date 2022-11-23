package HouseSelling.servlet;
import HouseSelling.Dao.CreditCardDao;
import HouseSelling.Model.CreditCard;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/creditcarddelete")
public class CreditCardDelete extends HttpServlet {
  protected CreditCardDao creditCardDao;
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    Map<String, String> messages = new HashMap<>();
    req.setAttribute("messages", messages);
    String cardNumber = req.getParameter("cardNumber");
    cardNumber = "121212112";
    
    try {
    	CreditCard creditCard = creditCardDao.getByCreditCard(cardNumber);
      creditCardDao.delete(creditCard);
      messages.put("success", "Successfully deleted card number " + creditCard.getCardNumber());
    } catch (SQLException e) {
    	
      throw new RuntimeException(e);
    }
    req.getRequestDispatcher("/DeleteResult.jsp").forward(req,resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    req.getRequestDispatcher("/DeleteResult.jsp").forward(req,resp);
  }

  @Override
  public void init() throws ServletException {
    creditCardDao = CreditCardDao.getInstance();
  }
}
