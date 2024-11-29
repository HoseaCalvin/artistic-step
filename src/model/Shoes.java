package model;

public class Shoes {
	
	private String id;
	private String name;
	private String brand;
	private String color;
	private int price;
	private int quantity;
	
	public Shoes(String id, String name, String brand, int price) {
		this.id = id;
		this.name = name;
		this.brand = brand;
		this.price = price;
	}
	
	public Shoes(String id, String name, String brand, int price, String color, int quantity) {
		this(id, name, brand, price);
		this.color = color;
		this.quantity = quantity;
	}
	
	public String getId() {
		return id;
	}

	public String getName() {
		return name;
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
	
}
