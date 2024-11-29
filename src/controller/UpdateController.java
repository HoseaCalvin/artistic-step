package controller;

import database.CartDatabase;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Shoes;
import table.CartTable;
import view.AlertView;
import view.UpdateView;

public class UpdateController {
	
	private static UpdateController updateController;
	
	public static UpdateController getInstance() {
		if(updateController == null) {
			updateController = new UpdateController();
		}
		
		return updateController;
	}
	
	public Scene getScene() {
		UpdateView updateInterface = new UpdateView();
		
		return updateInterface.createMainScene();
	}
	
	public void setButtonAction(Button formButton, Shoes selectedShoes, TextField color) {
		formButton.setOnAction(e -> {
			UpdateController.getInstance().updateContent(selectedShoes, color);
				
			AlertView.getInformationMessage("Update Successful", "Your product has been updated!");
		});
	}

	public void updateContent(Shoes selectedShoes, TextField color) {
		String shoesColor = color.getText();
		
		CartDatabase cartDatabase = new CartDatabase();
		cartDatabase.update(shoesColor, selectedShoes.getId());
		
		color.clear();
		
		CartTable.getInstance().refreshTable();
	}

	

}
