package HouseSelling.servlet;

import HouseSelling.Dao.CompanyDao;
import HouseSelling.Dao.HouseDao;
import HouseSelling.Dao.SellerDao;
import HouseSelling.Model.Company;
import HouseSelling.Model.House;
import HouseSelling.Model.Seller;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/companyInfo")
public class CompanyInfo extends HttpServlet {
  protected CompanyDao companyDao;
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    Company company;
    String id = req.getParameter("companyId");
//    id = "1";
    System.out.println(id);
    try {
      company = companyDao.getById(Integer.parseInt(id));
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    req.setAttribute("company",company);
    req.getRequestDispatcher("CompanyInfo.jsp").forward(req,resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
	  Company company;
	    String id = req.getParameter("companyId");
	    System.out.println(id);
	    try {
	      company = companyDao.getById(Integer.parseInt(id));
	    } catch (SQLException e) {
	      throw new RuntimeException(e);
	    }
	    req.setAttribute("company",company);
	    req.getRequestDispatcher("CompanyInfo.jsp").forward(req,resp);
  }

  @Override
  public void init() throws ServletException {
    companyDao = CompanyDao.getInstance();
  }

}
