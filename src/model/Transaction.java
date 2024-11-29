package model;

import java.time.LocalDate;

public class Transaction {

	private String transactionId;
	private String shoesId;
	private String shoesName;
	private String shoesColor;
	private LocalDate date;
	private int quantity;
	
	public Transaction(String transactionId, String shoesId, String shoesColor, LocalDate date, int quantity) {
		this.transactionId = transactionId;
		this.shoesId = shoesId;
		this.shoesColor = shoesColor;
		this.date = date;
		this.quantity = quantity;
	}
	
	public Transaction(LocalDate date, String shoesName, String shoesColor, int quantity) {
		this.date = date;
		this.shoesName = shoesName;
		this.shoesColor = shoesColor;
		this.quantity = quantity;
	}

	public String getTransactionId() {
		return transactionId;
	}
	
	public String getShoesId() {
		return shoesId;
	}
	
	public String getShoesName() {
		return shoesName;
	}
	
	public String getShoesColor() {
		return shoesColor;
	}

	public LocalDate getDate() {
		return date;
	}

	public int getQuantity() {
		return quantity;
	}

}
