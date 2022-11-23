package HouseSelling.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Helper {

//  public static void closeResource(Connection connection, PreparedStatement preparedStatement,
//      ResultSet resultSet)
//      throws SQLException {
//      if (connection != null) {
//        connection.close();
//      }
//      if (preparedStatement != null) {
//        preparedStatement.close();
//      }
//      if(resultSet != null){
//        resultSet.close();
//      }
//  }
	  public static void closeResource( PreparedStatement preparedStatement,
		      ResultSet resultSet)
		      throws SQLException {
		      if (preparedStatement != null) {
		        preparedStatement.close();
		      }
		      if(resultSet != null){
		        resultSet.close();
			}
		}
}
