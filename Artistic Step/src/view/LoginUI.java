package view;

import controller.Order;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import main.Main;

public class LoginUI {

	public Scene mainScene() {
		ImageView logo = new ImageView(
				"file:///C:/Users/hosea/eclipse-workspace/Artistic%20Step/image/logo/Shoes_Logo.png");
		logo.setFitHeight(150);
		logo.setFitWidth(250);
		logo.setPreserveRatio(true);
		
		Label title = new Label("Artistic Step");
		title.setId("title");

		// Added a constraint to provide more space between "title" and "loginButton".
		Region spaceConstraint = new Region();
		spaceConstraint.setPrefHeight(20);
		
		Button loginButton = createMainButton("Log In");
		loginButton.setOnAction(e -> {
			Order order = new Order();
			Main.mainWindow.setScene(order.getScene());
		});
		
		VBox mainPane = new VBox(15);
		mainPane.setAlignment(Pos.CENTER);
		mainPane.getChildren().addAll(logo, title, spaceConstraint, loginButton);
		
		StackPane mainLayout = new StackPane();
		mainLayout.setId("mainLayout");
		mainLayout.getChildren().addAll(landingPageBackground(), mainPane);
		mainLayout.getStylesheets().add(getClass().getResource("/styles/main.css").toExternalForm());
		
		return new Scene(mainLayout);
	}
	
	private Button createMainButton(String text) {
		Button button = new Button(text);
		button.setPrefWidth(300);
		button.setPrefHeight(50);
		button.setId("mainButton");
		
		return button;
	}
	
	private Rectangle landingPageBackground() {
		Rectangle rectangle = new Rectangle();
		rectangle.setHeight(400);
		rectangle.setWidth(600);
		rectangle.setArcHeight(25);
		rectangle.setArcWidth(25);
		rectangle.setFill(Color.WHITE);
		rectangle.setStroke(Color.BLACK);
		rectangle.setStrokeWidth(1.5);
		
		return rectangle;
	}
	

}
