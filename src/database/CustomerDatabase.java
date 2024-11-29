package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Customer;

public class CustomerDatabase extends Database {
	
	public CustomerDatabase() {
		connect();
	}

	public void insert(Customer user) {
		try {
			String query = "INSERT INTO mscustomer VALUES (?, ?, ?, ?, ?, ?)";
			
			preStatement = connection.prepareStatement(query);
			preStatement.setString(1, user.getId());
			preStatement.setString(2, user.getName());
			preStatement.setString(3, user.getEmail());
			preStatement.setDate(4, toDate(user.getDateOfBirth()));
			preStatement.setString(5, user.getGender());
			preStatement.setString(6, user.getPassword());
			
			preStatement.execute();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public ResultSet select() {
		try {
			String query = "SELECT * FROM mscustomer";
			
			resultSet = statement.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return resultSet;
	}
	
	public static ArrayList<Customer> getUserList() {
		ArrayList<Customer> userList = new ArrayList<>();
		
		CustomerDatabase userDatabase = new CustomerDatabase();
		
		ResultSet resultSet = userDatabase.select();
		
		try {
			while(resultSet.next()) {
				userList.add(new Customer(
						resultSet.getString("CustomerID"),
						resultSet.getString("CustomerName"),
						resultSet.getString("CustomerEmail"),
						resultSet.getDate("CustomerDOB").toLocalDate(),
						resultSet.getString("CustomerGender"),
						resultSet.getString("CustomerPassword")
						));
			}

		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return userList;
	}

}
