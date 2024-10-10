package controller;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import database.UserDatabase;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import model.Shoes;
import table.ShoesTableUI;
import table.UserTableUI;
import view.AlertUI;
import view.OrderUI;

public class Order {
	
	private static Order orderController;
	
	public static Order getInstance() {
		if(orderController == null) {
			orderController = new Order();
		}
		
		return orderController;
	}
	
	public Scene getScene() {
		return OrderUI.getInstance().createMainScene();
	}
	
	public void getRowValue(Label idData, Label priceData) {
		Shoes selectedShoes = ShoesTableUI.shoesTable.getSelectionModel().getSelectedItem();
		if (selectedShoes != null) {
			idData.setText(selectedShoes.getId());
			priceData.setText(String.valueOf(selectedShoes.getPrice()));
		}
	}
	
	public void getData(Text model, Text brand, AtomicInteger price) {
		Shoes selectedShoes = ShoesTableUI.shoesTable.getSelectionModel().getSelectedItem();
		if (selectedShoes != null) {
			model.setText(selectedShoes.getModel());
			brand.setText(selectedShoes.getBrand());
			price.set(selectedShoes.getPrice());
		}
	}

	public void updateQuantity(Label priceData, AtomicInteger price, Spinner<Integer> quantityData) {
		int totalPrice = price.get() * quantityData.getValue();
		
		priceData.setText(String.valueOf(totalPrice));
	}
	
	public void validateContent(Label idData, Text modelData, Text brandData, TextField colorData, 
			Label priceData, Spinner<Integer> quantityData, TextField moneyData) {
		String model = modelData.getText();
		String brand = brandData.getText();
		
		if(moneyData.getText().isBlank() || moneyData.getText().isEmpty()) {
			AlertUI.invalidData("Empty Data", "Ensure all fields are filled.");
		} else if(!isMoneyValid(moneyData)) {
			AlertUI.invalidData("Invalid Data", "Money must be an integer!");
		} else if(Integer.parseInt(moneyData.getText()) < Integer.parseInt(priceData.getText())) {
			AlertUI.invalidData("Insufficient Balance", "Please recheck your balance amount before ordering.");
		} else {
			Order.getInstance().writeContent(idData, model, brand, colorData, priceData, quantityData, moneyData);
			AlertUI.informAction("Product Order", "The product has been successfully ordered!");
		}
	}

	public void writeContent(Label idData, String modelData, String brandData, TextField colorData, Label priceData,
			Spinner<Integer> quantityData, TextField moneyData) {
		Random rand = new Random();
		
		String transactionId = "TR" + rand.nextInt(10) + rand.nextInt(10) + rand.nextInt(10);
		String id = idData.getText();
		String color = colorData.getText();
		int price = Integer.parseInt(priceData.getText());
		int quantity = quantityData.getValue();
		int money = Integer.parseInt(moneyData.getText());
		
		UserDatabase ownerDatabase = new UserDatabase();
		
		Shoes shoes = new Shoes(transactionId, id, modelData, brandData, color, price, quantity, money);
		
		ownerDatabase.insert(shoes);
			
		// Reset 'TextField' and 'Spinner' value.
		colorData.clear();
		moneyData.clear();
		quantityData.getValueFactory().setValue(1);
		
	}
	
	private boolean isMoneyValid(TextField moneyData) {
		String money = moneyData.getText();
		
		for(char c : money.toCharArray()) {
			if(Character.isAlphabetic(c)) {
				return false;
			}
		}
		
		return true;
	}
	
}
