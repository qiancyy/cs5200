package HouseSelling.Dao;

import static HouseSelling.Utils.Helper.closeResource;

import HouseSelling.Model.Message;
import HouseSelling.Model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessageDao extends AbstractDao<Message> {
	private static MessageDao instance = null;

	public synchronized static MessageDao getInstance() {
		if (instance == null)
			instance = new MessageDao();
		return instance;
	}

	public MessageDao() {
	}

	@Override
	public Message create(Message message) throws SQLException {
		String st = "INSERT INTO Message(sendTime,content,fromID,toID) VALUES(?,?,?,?)";
		PreparedStatement preSt = connection.prepareStatement(st, Statement.RETURN_GENERATED_KEYS);
		ResultSet resultSet = null;
		// fill the ? in preSt
		preSt.setTimestamp(1, new Timestamp(message.getSendTime().getTime()));
		preSt.setString(2, message.getContent());
		preSt.setInt(3, message.getFrom().getUserId());
		preSt.setInt(4, message.getTo().getUserId());
		preSt.executeUpdate();
		resultSet = preSt.getGeneratedKeys();
		int generateId = resultSet.getInt(1);
		message.setMessageId(generateId);
		closeResource(preSt, resultSet);
		return message;
	}

	@Override
	public void delete(Message message) throws SQLException {
		String st = "DELETE FROM Message WHERE messageId=?";
		PreparedStatement preSt = connection.prepareStatement(st);
		ResultSet resultSet = null;
		// fill the ? in preSt
		preSt.setInt(1, message.getMessageId());
		preSt.executeUpdate();
		closeResource(preSt, resultSet);
	}

	@Override
	public Message getById(int id) throws SQLException {
		String st = "SELECT * FROM Message WHERE messageId=?";
		PreparedStatement preSt = connection.prepareStatement(st);
		ResultSet resultSet = null;
		preSt.setInt(1, id);
		resultSet = preSt.executeQuery();
		if (resultSet.next()) {
			Date sendTime = new Date(resultSet.getTimestamp("sendTime").getTime());

			String content = resultSet.getString("content");
			User userFrom = UserDao.getInstance().getById(resultSet.getInt("fromID"));
			User userTo = UserDao.getInstance().getById(resultSet.getInt("toID"));
			return new Message(id, sendTime, content, userFrom, userTo);
		}
		closeResource(preSt, resultSet);
		return null;
	}

	public List<Message> getMessageByUserId(int userId) throws SQLException {
		List<Message> res = new ArrayList<>();
		String st = "SELECT * FROM Message WHERE fromID=?";
		PreparedStatement preSt = connection.prepareStatement(st);
		ResultSet resultSet = null;
		preSt.setInt(1, userId);
		resultSet = preSt.executeQuery();
		while (resultSet.next()) {
			int messageId = resultSet.getInt("messageId");
			Date sendTime = new Date(resultSet.getTimestamp("sendTime").getTime());
			String content = resultSet.getString("content");
			User from = UserDao.getInstance().getById(resultSet.getInt("fromID"));
			User to = UserDao.getInstance().getById(resultSet.getInt("toID"));
			res.add(new Message(messageId, sendTime, content, from, to));
		}
		;
		resultSet = null;
		st = "SELECT * FROM Message WHERE toID=?";
		preSt = connection.prepareStatement(st);
		preSt.setInt(1, userId);
		resultSet = preSt.executeQuery();
		while (resultSet.next()) {
			int messageId = resultSet.getInt("messageId");
			Date sendTime = new Date(resultSet.getTimestamp("sendTime").getTime());
			String content = resultSet.getString("content");
			User from = UserDao.getInstance().getById(resultSet.getInt("fromID"));
			User to = UserDao.getInstance().getById(resultSet.getInt("toID"));
			res.add(new Message(messageId, sendTime, content, from, to));
		}
		closeResource(preSt, resultSet);
		return res;
	}
}
