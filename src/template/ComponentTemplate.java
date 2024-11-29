package template;

import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ComponentTemplate {
	
	public static Rectangle pageBox(int height, int width) {
		Rectangle rectangle = new Rectangle();
		rectangle.setHeight(height);
		rectangle.setWidth(width);
		rectangle.setArcHeight(25);
		rectangle.setArcWidth(25);
		rectangle.setFill(Color.WHITE);
		rectangle.setStroke(Color.BLACK);
		rectangle.setStrokeWidth(1.5);
		
		return rectangle;
	}
	
	public static Button createMenuButton(String text, String image) {
		ImageView buttonImage = new ImageView(image);
		buttonImage.setFitHeight(30);
		buttonImage.setFitWidth(30);
		buttonImage.setPreserveRatio(true);
		
		Button button = new Button(text);
		button.setId("menuButton");
		button.setGraphic(buttonImage);	
		
		return button;
	}
	
	public static Button createFormButton(String text) {
		Button button = new Button(text);
		button.setId("formButton");
		
		return button;
	}
	
	public static Button menuButtonIndicator(Button button) {
		button.setId("menuButtonIndicator");
		
		return button;
	}
	
	public static TextField createTextField(String promptText) {
		TextField textField = new TextField();
		textField.setPromptText(promptText);
		
		return textField;
	}
	
	public static PasswordField createPasswordField(String promptText) {
		PasswordField passwordField = new PasswordField();
		passwordField.setPromptText(promptText);
		
		return passwordField;
	}
	
	
}
