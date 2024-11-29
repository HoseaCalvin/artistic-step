package view;

import controller.RegistrationController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import template.ComponentTemplate;
import template.ImageTemplate;
import template.RegionTemplate;

public class RegistrationView {
	
	public Scene createMainScene() {
		StackPane mainLayout = new StackPane();
		mainLayout.setId("mainLayout");
		mainLayout.getChildren().addAll(ComponentTemplate.pageBox(600, 800), formLayout());
		mainLayout.getStylesheets().add(getClass().getResource("/styles/register.css").toExternalForm());
		
		return new Scene(mainLayout);
	}

	private GridPane formLayout() {
		ImageView logo = new ImageView(ImageTemplate.logo);
		logo.setFitHeight(100);
		logo.setFitWidth(200);
		logo.setPreserveRatio(true);
		
		Label header = new Label("Register");
		header.setId("title");
		
		Label nameInfo = new Label("Full Name\t\t\t:");
		TextField name = ComponentTemplate.createTextField("More than 7 characters");
		
		Label emailInfo = new Label("G-mail\t\t\t\t:");
		TextField email = ComponentTemplate.createTextField("Domain: @gmail.com");
		
		Label dateOfBirthInfo = new Label("Date of Birth\t\t\t:");
		DatePicker dateOfBirth = new DatePicker();
		
		Label genderInfo = new Label("Gender\t\t\t\t:");
		RadioButton male = new RadioButton("Male");
		RadioButton female = new RadioButton("Female");
		toggleGroupRadioButton(male, female);
		
		Label passwordInfo = new Label("Password\t\t\t\t:");
		PasswordField password = ComponentTemplate.createPasswordField("More than 10 characters");
		
		Label rePasswordInfo = new Label("Re-Password\t\t\t:");
		PasswordField rePassword = ComponentTemplate.createPasswordField("Re-enter password");
		
		Button registerButton = new Button("Register");
		Button backButton = new Button("Back");
		
		VBox headerLayout = new VBox(15);
		headerLayout.setId("headerLayout");
		headerLayout.getChildren().addAll(logo, header);
		
		GridPane formLayout = new GridPane();
		formLayout.setId("formLayout");
		formLayout.add(headerLayout, 0, 0, 2, 1);
		formLayout.add(RegionTemplate.separateHeight(15), 0, 1);
		formLayout.add(nameInfo, 0, 2);
		formLayout.add(name, 1, 2);
		formLayout.add(emailInfo, 0, 3);
		formLayout.add(email, 1, 3);
		formLayout.add(dateOfBirthInfo, 0, 4);
		formLayout.add(dateOfBirth, 1, 4);
		formLayout.add(genderInfo, 0, 5);
		formLayout.add(genderLayout(male, female), 1, 5);
		formLayout.add(passwordInfo, 0, 6);
		formLayout.add(password, 1, 6);
		formLayout.add(rePasswordInfo, 0, 7);
		formLayout.add(rePassword, 1, 7);
		formLayout.add(RegionTemplate.separateHeight(15), 0, 8);
		formLayout.add(registerButton, 0, 9);
		formLayout.add(backButton, 1, 9);
		
		RegistrationController.getInstance().setRegisterButtonAction(registerButton, name, email, 
				dateOfBirth, male, female, password, rePassword);
		RegistrationController.getInstance().setBackButtonAction(backButton);
		
		return formLayout;
	}
	
	private void toggleGroupRadioButton(RadioButton male, RadioButton female) {
		ToggleGroup toggleRadioButton = new ToggleGroup();
		toggleRadioButton.getToggles().addAll(male, female);
	}
	
	private HBox genderLayout(RadioButton male, RadioButton female) {
		HBox genderLayout = new HBox(10);
		genderLayout.getChildren().addAll(male, female);
		
		return genderLayout;
	}

}
