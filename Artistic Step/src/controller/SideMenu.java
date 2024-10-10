package controller;

import javafx.scene.control.Button;
import main.Main;

public class SideMenu {

	private static SideMenu sideMenu;
	
	public static SideMenu getInstance() {
		if(sideMenu == null) {
			sideMenu = new SideMenu();
		}
		
		return sideMenu;
	}
	
	public void setButtonsAction(Button orderButton, Button viewButton, Button updateButton, Button deleteButton) {
		orderButton.setOnAction(e -> {
			Main.mainWindow.setScene(Order.getInstance().getScene());
		});
		
		viewButton.setOnAction(e -> {
			Main.mainWindow.setScene(View.getInstance().getScene());
		});

		updateButton.setOnAction(e -> {
			Main.mainWindow.setScene(Update.getInstance().getScene());
		});

		deleteButton.setOnAction(e -> {
			Main.mainWindow.setScene(Delete.getInstance().getScene());
		});
		
	}

}
