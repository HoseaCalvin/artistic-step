package controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import model.Shoes;
import table.UserTableUI;
import view.ViewUI;

public class View {
	
	private static View viewController;
	
	public static View getInstance() {
		if(viewController == null) {
			viewController = new View();
		}
		
		return viewController;
	}
	
	public Scene getScene() {
		ViewUI viewInterface = new ViewUI();
		
		return viewInterface.createMainScene();
	}
	
	public void getRowValue(Label transactionIdData, Label shoesIdData, Label moneyData, Label changeData) {
		Shoes selectedShoes = UserTableUI.userTable.getSelectionModel().getSelectedItem();
		
		int money = selectedShoes.getMoney();
		int change = money - selectedShoes.getPrice();
		
		if (selectedShoes != null) {
			transactionIdData.setText(selectedShoes.getTransactionId());
			shoesIdData.setText(selectedShoes.getId());
			moneyData.setText(String.valueOf(money));
			changeData.setText(String.valueOf(change));
		}
	}
	
	public void printReceipt() {
		Shoes selectedShoes = UserTableUI.userTable.getSelectionModel().getSelectedItem();
		
		try {
			BufferedWriter writeReceipt = new BufferedWriter(new FileWriter("shoes-" + selectedShoes.getTransactionId() + ".txt"));
			
			writeReceipt.write("Artistic Step\n");
			writeReceipt.write("Transaction ID: " + selectedShoes.getTransactionId() + "\n");
			writeReceipt.write("===========================\n");
			writeReceipt.write("Shoes ID\t: " + selectedShoes.getId() + "\n");
			writeReceipt.write("Model\t\t: " + selectedShoes.getModel() + "\n");
			writeReceipt.write("Brand\t\t: " + selectedShoes.getBrand() + "\n");
			writeReceipt.write("Color\t\t: " + selectedShoes.getColor() + "\n");
			writeReceipt.write("Price\t\t: " + selectedShoes.getPrice() + "\n");
			writeReceipt.write("Quantity\t: " + selectedShoes.getQuantity() + "\n");
			writeReceipt.write("Money\t\t: " + selectedShoes.getMoney() + "\n");
			
			writeReceipt.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
