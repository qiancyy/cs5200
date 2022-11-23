package HouseSelling.Dao;

import HouseSelling.Utils.ConnectionManager;
import java.sql.Connection;
import java.sql.SQLException;

public abstract class AbstractDao<T> implements Dao<T> {

	protected Connection connection;

	public AbstractDao() {
		try {
			connection = new ConnectionManager().getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public T update(T t, String columnName, Object value) throws SQLException {
		return null;
	}

	public T getById(int id) throws SQLException {
		return null;
	}
}
