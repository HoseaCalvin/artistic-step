package view;

import controller.LoginController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import template.ComponentTemplate;
import template.ImageTemplate;
import template.RegionTemplate;

public class LoginView {

	public Scene createMainScene() {		
		StackPane mainLayout = new StackPane();
		mainLayout.setId("mainLayout");
		mainLayout.getChildren().addAll(ComponentTemplate.pageBox(650, 600), loginLayout());
		mainLayout.getStylesheets().add(getClass().getResource("/styles/login.css").toExternalForm());
		
		return new Scene(mainLayout);
	}
	
	private VBox loginLayout() {
		ImageView logo = new ImageView(ImageTemplate.logo);
		logo.setFitHeight(150);
		logo.setFitWidth(250);
		logo.setPreserveRatio(true);
		
		Label title = new Label("Login");
		title.setId("title");
		
		TextField usernameField = ComponentTemplate.createTextField("G-mail");

		PasswordField passwordField = ComponentTemplate.createPasswordField("Password");
		
		Button loginButton = new Button("Log In");
		Button backButton = new Button("Back");
		
		VBox loginLayout = new VBox(15);
		loginLayout.setId("loginLayout");
		loginLayout.getChildren().addAll(
				logo, 
				title, 
				RegionTemplate.separateHeight(20),
				usernameField,
				passwordField,
				RegionTemplate.separateHeight(35),
				loginButton,
				backButton
			);
		
		LoginController.getInstance().setLoginButtonAction(loginButton, usernameField, passwordField);
		LoginController.getInstance().setBackButtonAction(backButton);
		
		return loginLayout;
	}
}
