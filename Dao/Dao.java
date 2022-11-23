package HouseSelling.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * CRUD DAO
 */
public interface Dao<T> {
	T create(T t) throws SQLException;

	void delete(T t) throws SQLException;

}
