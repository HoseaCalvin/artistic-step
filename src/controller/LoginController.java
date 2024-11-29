package controller;

import java.time.LocalDate;

import database.CustomerDatabase;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import main.Main;
import model.Customer;
import view.AlertView;
import view.LoginView;

public class LoginController {

	private static LoginController login;
	
	public static LoginController getInstance() {
		if(login == null) {
			login = new LoginController();
		}
		
		return login;
	}
	
	public Scene getScene() {
		LoginView loginInterface = new LoginView();

		return loginInterface.createMainScene();
	}
	
	public boolean isAccountValid(TextField gmail, PasswordField password) {
		for(int i = 0; i < CustomerDatabase.getUserList().size(); i++) {
			if(gmail.getText().equals(CustomerDatabase.getUserList().get(i).getEmail()) && 
					password.getText().equals(CustomerDatabase.getUserList().get(i).getPassword())) {
				String id = CustomerDatabase.getUserList().get(i).getId();
				String name = CustomerDatabase.getUserList().get(i).getName();
				String email = CustomerDatabase.getUserList().get(i).getEmail();
				LocalDate birth = CustomerDatabase.getUserList().get(i).getDateOfBirth();
				String gender = CustomerDatabase.getUserList().get(i).getGender();
				String pass = CustomerDatabase.getUserList().get(i).getPassword();
				
				Customer.setCurrentCustomer(new Customer(id, name, email, birth, gender, pass));
				
				return true;
			}
		}
		
		return false;
	}
	
	public void setLoginButtonAction(Button loginButton, TextField username, PasswordField password) {
		loginButton.setOnAction(e -> {
			if(!isAccountValid(username, password)) {
				AlertView.getErrorMessage("Invalid Credentials", "Wrong username or password!");
			} else {
				Main.mainWindow.setScene(OrderController.getInstance().getScene());
			}
		});
	}
	
	public void setBackButtonAction(Button backButton) {
		backButton.setOnAction(e -> {
			Main.mainWindow.setScene(HomeController.getInstance().getScene());
		});
	}

}
