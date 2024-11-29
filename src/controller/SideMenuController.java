package controller;

import javafx.scene.control.Button;
import main.Main;

public class SideMenuController {

	private static SideMenuController sideMenu;
	
	public static SideMenuController getInstance() {
		if(sideMenu == null) {
			sideMenu = new SideMenuController();
		}
		
		return sideMenu;
	}
	
	public void setButtonsAction(Button orderButton, Button updateButton, Button deleteButton, 
			Button checkoutButton, Button transactionHistoryButton) {
		orderButton.setOnAction(e -> {
			Main.mainWindow.setScene(OrderController.getInstance().getScene());
		});

		updateButton.setOnAction(e -> {
			Main.mainWindow.setScene(UpdateController.getInstance().getScene());
		});

		deleteButton.setOnAction(e -> {
			Main.mainWindow.setScene(DeleteController.getInstance().getScene());
		});
		
		checkoutButton.setOnAction(e -> {
			Main.mainWindow.setScene(CheckoutController.getInstance().getScene());
		});

		transactionHistoryButton.setOnAction(e -> {
			Main.mainWindow.setScene(TransactionHistoryController.getInstance().getScene());
		});
	}

}
