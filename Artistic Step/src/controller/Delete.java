package controller;

import database.UserDatabase;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import model.Shoes;
import table.UserTableUI;
import view.DeleteUI;

public class Delete {
	
	private static Delete deleteController;
	
	public static Delete getInstance() {
		if(deleteController == null) {
			deleteController = new Delete();
		}
		
		return deleteController;
	}
	
	public Scene getScene() {
		DeleteUI deleteInterface = new DeleteUI();

		return deleteInterface.createMainScene();
	}

	public void getRowValue(Label deleteData) {
		Shoes selectedShoes = UserTableUI.userTable.getSelectionModel().getSelectedItem();
		
		if(selectedShoes != null) {
			deleteData.setText(selectedShoes.getTransactionId());
		}
	}
	
	public void deleteContent(Label deleteData) {
		UserDatabase ownerDatabase = new UserDatabase();
		
		String transactionId = deleteData.getText();
		ownerDatabase.delete(transactionId);
	}

}
