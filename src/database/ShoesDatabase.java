package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Shoes;

public class ShoesDatabase extends Database {
	
	public ShoesDatabase() {
		connect();
	}
	
	public ResultSet select() {
		try {
			String query = "SELECT * FROM msshoes s "
					+ "JOIN msbrand b ON s.BrandID = b.BrandID";
			
			resultSet = statement.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return resultSet;
	}
	
	public ResultSet selectImage(String shoesId) {
		try {
			String query = "SELECT ShoesPicture FROM msshoes s "
					+ "WHERE ShoesID = ? ";
			
			preStatement = connection.prepareStatement(query);
			preStatement.setString(1, shoesId);
			
			resultSet = preStatement.executeQuery();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return resultSet;
	}
	
	public static ArrayList<Shoes> getShoesList() {
		ArrayList<Shoes> shoesList = new ArrayList<>();
		
		ShoesDatabase shoesDatabase = new ShoesDatabase();
		
		ResultSet resultSet = shoesDatabase.select();

		try {
			while(resultSet.next()) {
				shoesList.add(new Shoes(
						resultSet.getString("ShoesID"),
						resultSet.getString("ShoesName"),
						resultSet.getString("BrandName"),
						resultSet.getInt("ShoesPrice")
						));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return shoesList;
	}

}
