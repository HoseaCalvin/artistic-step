package view;

import controller.LoginController;
import controller.RegistrationController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.Main;
import template.ImageTemplate;
import template.RegionTemplate;

public class HomeView {

	private Button loginButton;
	private Button registerButton;
	
	public HomeView() {
		loginButton = new Button("Login");
		registerButton = new Button("Register");
	}
	
	public Scene createMainScene() {
		VBox mainLayout = new VBox(5);
		mainLayout.setId("mainLayout");
		mainLayout.getChildren().addAll(
				navigationLayout(),
				RegionTemplate.separateHeight(25),
				article1(),
				article2(),
				article3()
			);
		
		ScrollPane scrollLayout = new ScrollPane();
		scrollLayout.setContent(mainLayout);
		scrollLayout.setId("scrollLayout");
		scrollLayout.setFitToWidth(true);
		scrollLayout.getStylesheets().add(getClass().getResource("/styles/home.css").toExternalForm());
		
		return new Scene(scrollLayout);
	}
	
	private GridPane article1() {
		String image = ImageTemplate.trustworthyStore;
		String title = "Your Trustworthy Shoe Store!";
		String description = "Delve into a brand new shoe store and explore shoes that might become your all-time best fits!";
		
		return articleCard(image, title, description);
	}
	
	private GridPane article2() {
		String image = ImageTemplate.exoticStyles;
		String title = "Exotic Styles Await!";
		String description = "Have you been having a difficult time looking for strange yet luxurious shoes? "
				+ "Don't worry because they've been tucked in here for the right person, at the right time!";
		
		return articleCard(image, title, description);
	}
	
	private GridPane article3() {
		String image = ImageTemplate.easyPayment;
		String title = "Easy Pay, Easy Get!";
		String description = "Don't go back home just to grab some cash. Here, transactions have been made "
				+ "easier with e-wallets. Oh, don't forget to top-up! We're not looking for cockroaches!";
		
		return articleCard(image, title, description);
	}
	
	private HBox navigationLayout() {
		ImageView logo = new ImageView(ImageTemplate.logo);
		logo.setFitWidth(100);
		logo.setFitHeight(50);
		
		Label header = new Label("Artistic Step");
		header.setId("header");
		
		HBox navigationLayout = new HBox(25);
		navigationLayout.setId("navigationLayout");
		navigationLayout.getChildren().addAll(
				logo,
				header,
				RegionTemplate.separateWidth(900), 
				loginButton, 
				registerButton
			);
		
		setLoginButtonAction(loginButton);
		setRegisterButtonAction(registerButton);
		
		return navigationLayout;
	}
	
	private GridPane articleCard(String image, String titleText, String descriptionText) {
		ImageView picture = new ImageView(image);
		picture.setFitWidth(180);
		picture.setFitHeight(180);
		picture.setPreserveRatio(true);
		
		Label title = new Label(titleText);
		title.setId("title");
		
		Label description = new Label(descriptionText);
		description.setId("description");
		
		GridPane articleLayout = new GridPane();
		articleLayout.setId("articleLayout");
		articleLayout.setHgap(15);
		articleLayout.add(picture, 0, 0, 1, 2);
		articleLayout.add(title, 1, 0);
		articleLayout.add(description, 1, 1);

		return articleLayout;
	}
	
	private void setLoginButtonAction(Button loginButton) {
		loginButton.setOnAction(e -> {
			Main.mainWindow.setScene(LoginController.getInstance().getScene());
		});
	}
	
	private void setRegisterButtonAction(Button registerButton) {
		registerButton.setOnAction(e -> {
			Main.mainWindow.setScene(RegistrationController.getInstance().getScene());
		});
	}

}
