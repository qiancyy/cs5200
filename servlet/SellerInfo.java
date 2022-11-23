package HouseSelling.servlet;

import HouseSelling.Dao.HouseDao;
import HouseSelling.Dao.SellerDao;
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

@WebServlet("/sellerinfo")
public class SellerInfo extends HttpServlet {
	protected SellerDao sellerDao;
	protected HouseDao houseDao;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Seller seller;
		List<House> list;
//    String id = req.getParameter("userId");
		String id = "1";
		try {
			seller = sellerDao.getById(Integer.parseInt(id));
			list = houseDao.getHouseBySeller(Integer.parseInt(id));

			System.out.println(seller);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		req.setAttribute("list", list);
		req.setAttribute("seller", seller);
		req.getRequestDispatcher("/SellerInfo.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Seller seller;
		String id = req.getParameter("userId");
		try {
			seller = sellerDao.getById(Integer.parseInt(id));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		req.setAttribute("seller", seller);
		req.getRequestDispatcher("SellerInfo.jsp").forward(req, resp);
	}

	@Override
	public void init() throws ServletException {
		sellerDao = SellerDao.getInstance();
		houseDao = HouseDao.getInstance();
	}

}
