package HouseSelling.servlet;

import HouseSelling.Dao.BuyerDao;
import HouseSelling.Dao.MessageDao;
import HouseSelling.Dao.ReviewDao;
import HouseSelling.Model.Buyer;
import HouseSelling.Model.Message;
import HouseSelling.Model.Review;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/reviewinfo")
public class ReviewInfo extends HttpServlet {

	protected ReviewDao reviewDao;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Review> list;
		String id = req.getParameter("userId");
		String isBuyer = req.getParameter("isBuyer");
		// isBuyer = "true";
		try {
			list = reviewDao.getReviewByUserId(Integer.parseInt(id), Boolean.parseBoolean(isBuyer));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		req.setAttribute("list", list);
		req.getRequestDispatcher("ReviewInfo.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
	}

	@Override
	public void init() throws ServletException {
		reviewDao = ReviewDao.getInstance();
	}

}