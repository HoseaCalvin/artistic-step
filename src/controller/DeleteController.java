package controller;

import database.CartDatabase;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.Shoes;
import table.CartTable;
import view.AlertView;
import view.DeleteView;

public class DeleteController {
	
	private static DeleteController deleteController;
	
	public static DeleteController getInstance() {
		if(deleteController == null) {
			deleteController = new DeleteController();
		}
		
		return deleteController;
	}
	
	public Scene getScene() {
		DeleteView deleteInterface = new DeleteView();

		return deleteInterface.createMainScene();
	}
	
	public void calculateShoesPrice(Label price) {
		Shoes selectedShoes = CartTable.getInstance().getTable().getSelectionModel().getSelectedItem();
		
		int totalPrice = 0;
		if(selectedShoes != null) {
			totalPrice = selectedShoes.getPrice() * selectedShoes.getQuantity();
			
			price.setText(String.valueOf(totalPrice));
		}
	}
	
	public void setButtonAction(Button formButton, Shoes selectedShoes) {
		formButton.setOnAction(e -> {
			if(selectedShoes != null) {
				DeleteController.getInstance().deleteContent(selectedShoes);
				AlertView.getInformationMessage("Delete Successful", "Your product has been successfully deleted!");			
			} else {
				AlertView.getErrorMessage("Delete Failed", "Please select a product!");
			}
		});
	}
	
	public void deleteContent(Shoes selectedShoes) {
		CartDatabase cartDatabase = new CartDatabase();
		cartDatabase.delete(selectedShoes.getId());
		
		CartTable.getInstance().refreshTable();
	}

}
