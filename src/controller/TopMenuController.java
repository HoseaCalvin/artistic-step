package controller;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import main.Main;
import view.TopMenuView;

public class TopMenuController {

	private static TopMenuController topMenuController;
	
	public static TopMenuController getInstance() {
		if(topMenuController == null) {
			topMenuController = new TopMenuController();
		}
		
		return topMenuController;
	}
	
	public HBox getPane() {
		TopMenuView topMenuInterface = new TopMenuView();
		
		return topMenuInterface.topPane();
	}
	
	public void setButtonAction(Button logoutButton) {
		logoutButton.setOnAction(e -> {
			Main.mainWindow.setScene(HomeController.getInstance().getScene());
		});
	}

}
