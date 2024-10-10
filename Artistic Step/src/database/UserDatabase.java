package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import model.Shoes;

public class UserDatabase {

	Connection connection;
	Statement statement;
	PreparedStatement preStatement;
	ResultSet resultSet;
	
	Random rand;
	
	public UserDatabase() {
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
	
	public void insert(Shoes shoes) {
		try {
			preStatement = connection.prepareStatement("INSERT INTO user_shoes (transaction_id, id, model, brand, color, price, quantity, money) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
			preStatement.setString(1, shoes.getTransactionId());
			preStatement.setString(2, shoes.getId());
			preStatement.setString(3, shoes.getModel());
			preStatement.setString(4, shoes.getBrand());
			preStatement.setString(5, shoes.getColor());
			preStatement.setInt(6, shoes.getPrice());
			preStatement.setInt(7, shoes.getQuantity());
			preStatement.setInt(8, shoes.getMoney());
			
			preStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ResultSet select() {
		try {
			resultSet = statement.executeQuery("SELECT * FROM user_shoes");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return resultSet;
	}
	
	public void update(Shoes shoes) {
		try {
			preStatement = connection.prepareStatement("UPDATE user_shoes SET color = ? WHERE transaction_id = ?");
			preStatement.setString(1, shoes.getColor());
			preStatement.setString(2, shoes.getTransactionId());
			
			preStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(String transactionId) {
		try {
			preStatement = connection.prepareStatement("DELETE FROM user_shoes WHERE transaction_id = ?");
			preStatement.setString(1, transactionId);
			
			preStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
