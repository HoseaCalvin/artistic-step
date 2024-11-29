package controller;

import javafx.scene.Scene;
import view.HomeView;

public class HomeController {

	public static HomeController homeController;
	
	public HomeController() {
		// TODO Auto-generated constructor stub
	}
	
	public static HomeController getInstance() {
		if(homeController == null) {
			homeController = new HomeController();
		}
		
		return homeController;
	}
	
	public Scene getScene() {
		HomeView homeInterface = new HomeView();
		
		return homeInterface.createMainScene();
	}

}
