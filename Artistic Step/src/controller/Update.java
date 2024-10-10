package controller;

import java.util.concurrent.atomic.AtomicInteger;

import database.UserDatabase;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Shoes;
import table.UserTableUI;
import view.UpdateUI;

public class Update {
	
	private static Update updateController;
	
	public static Update getInstance() {
		if(updateController == null) {
			updateController = new Update();
		}
		
		return updateController;
	}
	
	public Scene getScene() {
		UpdateUI updateInterface = new UpdateUI();
		
		return updateInterface.createMainScene();
	}
	
	public void getRowValue(Label idData) {
		Shoes selectedShoes = UserTableUI.userTable.getSelectionModel().getSelectedItem();
		if (selectedShoes != null) {
			idData.setText(selectedShoes.getTransactionId());
		}
	}
	
	public void getData(AtomicInteger price) {
		Shoes selectedShoes = UserTableUI.userTable.getSelectionModel().getSelectedItem();
		if (selectedShoes != null) {
			price.set(selectedShoes.getPrice());
		}
	}

	public void updateContent(Label idData, TextField colorData) {
		String transactionId = idData.getText();
		String color = colorData.getText();
		
		Shoes shoes = new Shoes(transactionId, null, null, null, color, 0, 0, 0);
		
		UserDatabase userDatabase = new UserDatabase();
		userDatabase.update(shoes);
		
		// Reset 'TextField' value.
		colorData.clear();
	}

	

}
