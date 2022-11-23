package HouseSelling.servlet;

import HouseSelling.Dao.BuyerDao;
import HouseSelling.Dao.CreditCardDao;
import HouseSelling.Model.Buyer;
import HouseSelling.Model.CreditCard;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/creditcardinfo")
public class CreditCardInfo extends HttpServlet {
    protected CreditCardDao creditCardDao;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
      List<CreditCard> res = new ArrayList<>();

      String userId = req.getParameter("userId");

      req.setAttribute("userId", userId);

      try {
        for(CreditCard creditcard:creditCardDao.getOwnedCreditCards(Integer.parseInt(userId))) {
        	Date date = creditcard.getExpirationDate();
        	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        	System.out.println(dateFormat.format(date));
        	creditcard.setSimpleDate(dateFormat.format(date));
        	res.add(creditcard);
        }
           
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
      req.setAttribute("list",res);
      req.getRequestDispatcher("CreditCardInfo.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
      this.doGet(req,resp);
    }

    @Override
    public void init() throws ServletException {
      creditCardDao = CreditCardDao.getInstance();
    }
  }
