package controller;

import javafx.scene.Scene;
import view.TransactionHistoryView;

public class TransactionHistoryController {

	private static TransactionHistoryController transactionHistoryController;
	
	public static TransactionHistoryController getInstance() {
		if(transactionHistoryController == null) {
			transactionHistoryController = new TransactionHistoryController();
		}
		
		return transactionHistoryController;
	}
	
	public Scene getScene() {
		TransactionHistoryView transactionHistoryInterface = new TransactionHistoryView();
		
		return transactionHistoryInterface.createMainScene();
	}

}
