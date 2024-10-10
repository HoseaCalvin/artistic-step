package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ShopDatabase {

	Connection connection;
	Statement statement;
	ResultSet resultSet;
	
	public ShopDatabase() {
		connect();
	}
	
	private void connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/artistic_step", "root", "");
			statement = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ResultSet select() {
		try {
			resultSet = statement.executeQuery("SELECT * FROM shop_shoes");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return resultSet;
	}

}
