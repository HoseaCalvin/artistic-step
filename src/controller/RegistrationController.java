package controller;

import java.time.LocalDate;
import java.util.Random;

import database.CustomerDatabase;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import main.Main;
import model.Customer;
import view.AlertView;
import view.RegistrationView;

public class RegistrationController {

	private static RegistrationController registrationController;
	
	public static RegistrationController getInstance() {
		if(registrationController == null) {
			registrationController = new RegistrationController();
		}
		
		return registrationController;
	}
	
	public Scene getScene() {
		RegistrationView registrationInterface = new RegistrationView();
		
		return registrationInterface.createMainScene();
	}
	
	public void setRegisterButtonAction(Button registerButton, TextField name, TextField email,
			DatePicker dateOfBirth, RadioButton male, RadioButton female, PasswordField password, 
			PasswordField rePassword) {
		registerButton.setOnAction(e -> {
			RegistrationController.getInstance().insertData(name, email, dateOfBirth, male, female, password, 
					rePassword);
		});
	}
	
	public void setBackButtonAction(Button backButton) {
		backButton.setOnAction(e -> {
			Main.mainWindow.setScene(HomeController.getInstance().getScene());
		});
	}
	
	public void insertData(TextField name, TextField email, DatePicker dateOfBirth, RadioButton male, 
			RadioButton female, PasswordField password, PasswordField rePassword) {
		String userName = name.getText();
		String userEmail = email.getText();
		LocalDate userDOB = dateOfBirth.getValue();
		String userPassword = password.getText();
		String userGender = "";
		
		if(male.isSelected()) {
			userGender = "Male";
		} else if(female.isSelected()) {
			userGender = "Female";
		}
		
		if(userName.length() <= 7) {
			AlertView.getErrorMessage("Invalid Name", "Ensure your name is more than 7 characters long!");
		} else if(!userEmail.endsWith("@gmail.com")) {
			AlertView.getErrorMessage("Invalid G-mail", "Ensure it is a Google email (@gmail.com)!");
		} else if(dateOfBirth.getValue() == null) {
			AlertView.getErrorMessage("Empty Date of Birth", "Ensure you have picked your date of birth!");
		} else if(!(male.isSelected() || female.isSelected())) {
			AlertView.getErrorMessage("Empty Gender", "Ensure you have picked your gender!");
		} else if(userPassword.length() < 10) {
			AlertView.getErrorMessage("Invalid Password", "Ensure your password is more than 10 characters long!");
		} else if(!userPassword.equals(rePassword.getText())) {
			AlertView.getErrorMessage("Invalid Password", "Ensure your passwords match!");
		} else {
			CustomerDatabase userDatabase = new CustomerDatabase();
			userDatabase.insert(new Customer(generateCustomerId(), userName, userEmail, userDOB, userGender, userPassword));
			CustomerDatabase.getUserList();
			
			AlertView.getInformationMessage("Registration Successful", "You have successfully registered an account!");
			
			Main.mainWindow.setScene(HomeController.getInstance().getScene());
		}
	}
	
	private String generateCustomerId() {
		Random rand = new Random();
		
		return "CU" + rand.nextInt(10) + rand.nextInt(10) + rand.nextInt(10);
	}

}
