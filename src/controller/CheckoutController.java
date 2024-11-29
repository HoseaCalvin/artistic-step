package controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

import database.CartDatabase;
import database.TransactionDatabase;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Shoes;
import model.Transaction;
import table.CartTable;
import table.TransactionTable;
import view.AlertView;
import view.CheckoutView;

public class CheckoutController {
	
	private static CheckoutController checkoutController;
	
	private CartDatabase cartDatabase = new CartDatabase();
	private TransactionDatabase transactionDatabase = new TransactionDatabase();
	
	public static CheckoutController getInstance() {
		if(checkoutController == null) {
			checkoutController = new CheckoutController();
		}
		
		return checkoutController;
	}
	
	public Scene getScene() {
		CheckoutView viewInterface = new CheckoutView();
		
		return viewInterface.createMainScene();
	}
	
	public void calculateShoesPrice(Label price) {
		Shoes selectedShoes = CartTable.getInstance().getTable().getSelectionModel().getSelectedItem();
		
		int totalPrice = 0;
		if(selectedShoes != null) {
			totalPrice = selectedShoes.getPrice() * selectedShoes.getQuantity();
			
			price.setText(String.valueOf(totalPrice));
		}
	}
	
	public void calculateTotalPrice(Label total) {
		ArrayList<Shoes> shoesList = CartDatabase.getCartList();
		
		int totalPrice = 0;
		for(int i = 0; i < shoesList.size(); i++) {
			totalPrice += shoesList.get(i).getPrice() * shoesList.get(i).getQuantity();
		}
		
		total.setText(String.valueOf(totalPrice));
	}
	
	public void setButtonAction(Button formButton, Label total, TextField money) {
		formButton.setOnAction(e -> {
			if(isMoneySufficient(total, money)) {	
				for(int i = 0; i < CartTable.getInstance().getTable().getItems().size(); i++) {
					Shoes shoes = CartTable.getInstance().getTable().getItems().get(i);
					
					transactionDatabase.insert(
							new Transaction(generateTransactionId(), 
									shoes.getId(), 
									shoes.getColor(), 
									LocalDate.now(), 
									shoes.getQuantity()
								));
					cartDatabase.delete(shoes.getId());	
				}
				
				AlertView.getInformationMessage("Checkout Successful", "All products have been checked out!");
				
				CartTable.getInstance().refreshTable();
				TransactionTable.getInstance().refreshTable();
			} else {
				AlertView.getInformationMessage("Checkout Failed", "Insufficient Money!");
			}
		});
	}
	
	private boolean isMoneySufficient(Label total, TextField money) {
		int moneySpent = Integer.parseInt(money.getText());
		int totalPrice = Integer.parseInt(total.getText());
		
		if(moneySpent > totalPrice) {
			return true;
		} else {
			return false;
		}
	}
	
	private String generateTransactionId() {
		Random random = new Random();
		
		return "TR" + random.nextInt(10) + random.nextInt(10) + random.nextInt(10);
	}
}
