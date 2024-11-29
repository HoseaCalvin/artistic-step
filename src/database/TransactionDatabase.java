package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Customer;
import model.Transaction;

public class TransactionDatabase extends Database {

	public TransactionDatabase() {
		connect();
	}
	
	public void insert(Transaction transaction) {
		try {
			String query = "INSERT INTO transactionhistory VALUES (?, ?, ?, ?, ?, ?)";
			
			preStatement = connection.prepareStatement(query);
			preStatement.setString(1, transaction.getTransactionId());
			preStatement.setString(2, Customer.getCurrentCustomer().getId());
			preStatement.setString(3, transaction.getShoesId());
			preStatement.setDate(4, toDate(transaction.getDate()));
			preStatement.setString(5, transaction.getShoesColor());
			preStatement.setInt(6, transaction.getQuantity());
			
			preStatement.execute();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public ResultSet select() {
		try {
			String query = "SELECT "
					+ "TransactionDate, "
					+ "ShoesName, "
					+ "Color, "
					+ "Quantity "
					+ "FROM transactionhistory t "
					+ "JOIN msshoes s ON t.ShoesID = s.ShoesID "
					+ "WHERE CustomerID = ?";
			
			preStatement = connection.prepareStatement(query);
			preStatement.setString(1, Customer.getCurrentCustomer().getId());
			
			resultSet = preStatement.executeQuery();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return resultSet;
	}
	
	public static ArrayList<Transaction> getList() {
		ArrayList<Transaction> transactionList = new ArrayList<>();
		
		TransactionDatabase transactionDatabase = new TransactionDatabase();
		
		ResultSet resultSet = transactionDatabase.select();
		
		try {
			while(resultSet.next()) {
				transactionList.add(new Transaction(
						resultSet.getDate("TransactionDate").toLocalDate(),
						resultSet.getString("ShoesName"),
						resultSet.getString("Color"),
						resultSet.getInt("Quantity")
					));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return transactionList;
	}
}
