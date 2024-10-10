package controller;

import javafx.scene.Scene;
import view.LoginUI;

public class Login {

	private static Login login;
	
	public static Login getInstance() {
		if(login == null) {
			login = new Login();
		}
		
		return login;
	}
	
	public Scene getScene() {
		LoginUI loginInterface = new LoginUI();

		return loginInterface.mainScene();
	}

}
