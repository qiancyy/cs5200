package HouseSelling.servlet;

import HouseSelling.Dao.BuyerDao;
import HouseSelling.Dao.MessageDao;
import HouseSelling.Model.Buyer;
import HouseSelling.Model.Message;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/messageinfo")
public class MessageInfo extends HttpServlet {

	protected MessageDao messageDao;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Message> list;
		String id = req.getParameter("userId");
		try {
			list = messageDao.getMessageByUserId(Integer.parseInt(id));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		req.setAttribute("list", list);
		req.getRequestDispatcher("MessageInfo.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
	}

	@Override
	public void init() throws ServletException {
		messageDao = MessageDao.getInstance();
	}

}