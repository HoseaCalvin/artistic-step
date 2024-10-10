package model;

import java.sql.ResultSet;
import java.sql.SQLException;

import database.ShopDatabase;
import database.UserDatabase;
import table.ShoesTableUI;
import table.UserTableUI;

public class Shoes {
	
	private String transactionId;
	private String id;
	private String model;
	private String brand;
	private String color;
	private int price;
	private int quantity;
	private int money;
	
	public Shoes(String id, String model, String brand, int price) {
		this.id = id;
		this.model = model;
		this.brand = brand;
		this.price = price;
	}
	
	public Shoes(String id, String model, String brand, String color, int price, int quantity, int money) {
		this(id, model, brand, price);
		this.color = color;
		this.quantity = quantity;
		this.money = money;
	}
	
	public Shoes(String transactionId, String id, String model, String brand, String color, int price, int quantity, int money) {
		this(id, model, brand, color, price, quantity, money);
		this.transactionId = transactionId;
	}
	
	public String getTransactionId() {
		return transactionId;
	}
	
	public String getId() {
		return id;
	}

	public String getModel() {
		return model;
	}

	public String getBrand() {
		return brand;
	}

	public String getColor() {
		return color;
	}

	public int getPrice() {
		return price;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public int getMoney() {
		return money;
	}
	
	public static void readShopTable() {
		ShopDatabase shoesDatabase = new ShopDatabase();
		
		ResultSet resultSet = shoesDatabase.select();

		try {
			while(resultSet.next()) {
				Object[] shoes = {
						resultSet.getString("id"),
						resultSet.getString("model"),
						resultSet.getString("brand"),
						resultSet.getInt("price")
				};
				
				ShoesTableUI.shoesTable.getItems().add(new Shoes(
						shoes[0].toString(), 
						shoes[1].toString(), 
						shoes[2].toString(), 
						(int) shoes[3]));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void readUserTable() {
		UserDatabase ownerDatabase = new UserDatabase();
		
		ResultSet resultSet = ownerDatabase.select();
		
		try {
			while(resultSet.next()) {
				Object[] shoes = {
						resultSet.getString("transaction_id"),
						resultSet.getString("id"),						
						resultSet.getString("model"),
						resultSet.getString("brand"),
						resultSet.getString("color"),
						resultSet.getInt("price"),
						resultSet.getInt("quantity"),
						resultSet.getInt("money")
				};
				
				UserTableUI.userTable.getItems().add(new Shoes(
						shoes[0].toString(),
						shoes[1].toString(),
						shoes[2].toString(),
						shoes[3].toString(),
						shoes[4].toString(),
						(int) shoes[5],
						(int) shoes[6],
						(int) shoes[7]
					));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
