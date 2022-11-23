package HouseSelling.servlet;

import HouseSelling.Dao.CreditCardDao;
import HouseSelling.Dao.SearchDao;
import HouseSelling.Model.CreditCard;
import HouseSelling.Model.Search;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/searchinfo")
public class SearchInfo extends HttpServlet {
	protected SearchDao searchDao;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Search> res;

		String userId = req.getParameter("userId");
		try {
			res = searchDao.getSearchListByUserId(Integer.parseInt(userId));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		req.setAttribute("list", res);
		req.getRequestDispatcher("SearchInfo.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}

	@Override
	public void init() throws ServletException {
		searchDao = SearchDao.getInstance();
	}
}
