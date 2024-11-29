package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Customer;
import model.Shoes;

public class CartDatabase extends Database {
	
	public CartDatabase() {
		connect();
	}
	
	public boolean isItemInCart(Shoes shoes) {
		try {
			String query = "SELECT "
					+ "ShoesID "
					+ "FROM cart "
					+ "WHERE ShoesID = ? AND CustomerID = ?";
			
			preStatement = connection.prepareStatement(query);
			preStatement.setString(1, shoes.getId());
			preStatement.setString(2, Customer.getCurrentCustomer().getId());
			
			resultSet = preStatement.executeQuery();
			
			if(resultSet.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public void insert(Shoes shoes) {
		try {
			String query = "INSERT INTO cart VALUES (?, ?, ?, ?)";
						
			preStatement = connection.prepareStatement(query);
			preStatement.setString(1, Customer.getCurrentCustomer().getId());
			preStatement.setString(2, shoes.getId());
			preStatement.setString(3, shoes.getColor());
			preStatement.setInt(4, shoes.getQuantity());
			
			preStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ResultSet select() {
		try {
			String query = "SELECT * FROM cart c "
					+ "JOIN msshoes s ON c.ShoesID = s.ShoesID "
					+ "JOIN msbrand b ON s.BrandID = b.BrandID "
					+ "WHERE CustomerID = ?";
			
			preStatement = connection.prepareStatement(query);
			preStatement.setString(1, Customer.getCurrentCustomer().getId());
			
			resultSet = preStatement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return resultSet;
	}
	
	public void update(String shoesColor, String shoesId) {
		try {
			String query = "UPDATE cart "
					+ "SET color = ? "
					+ "WHERE CustomerID = ? AND ShoesID = ? ";
			
			preStatement = connection.prepareStatement(query);
			preStatement.setString(1, shoesColor);
			preStatement.setString(2, Customer.getCurrentCustomer().getId());
			preStatement.setString(3, shoesId);
			
			preStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(String shoesId) {
		try {
			String query = "DELETE FROM cart "
					+ "WHERE CustomerID = ? and ShoesID = ?";
			
			preStatement = connection.prepareStatement(query);
			preStatement.setString(1, Customer.getCurrentCustomer().getId());
			preStatement.setString(2, shoesId);
			
			preStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<Shoes> getCartList() {
		ArrayList<Shoes> shoesList = new ArrayList<>();
		
		CartDatabase ownerDatabase = new CartDatabase();
		
		ResultSet resultSet = ownerDatabase.select();
		
		try {
			while(resultSet.next()) {
				shoesList.add(new Shoes(
						resultSet.getString("ShoesID"),						
						resultSet.getString("ShoesName"),
						resultSet.getString("BrandName"),
						resultSet.getInt("ShoesPrice"),
						resultSet.getString("Color"),
						resultSet.getInt("Quantity")
					));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return shoesList;
	}

}
