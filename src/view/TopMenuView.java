package view;

import controller.TopMenuController;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import model.Customer;
import template.ComponentTemplate;
import template.ImageTemplate;

public class TopMenuView {
	
	public HBox topPane() {
		HBox topPane = new HBox();
		topPane.setId("topPane");
		topPane.getChildren().addAll(logoPane(), buttonPane());
		HBox.setHgrow(buttonPane(), Priority.ALWAYS);
		
		return topPane;
	}
	
	private HBox buttonPane() {
		Button logoutButton = ComponentTemplate.createFormButton("Log Out");
		
		HBox buttonPane = new HBox();
		buttonPane.setId("buttonPane");
		buttonPane.getChildren().addAll(logoutButton);
		HBox.setHgrow(buttonPane, Priority.ALWAYS);
		
		TopMenuController.getInstance().setButtonAction(logoutButton);
		
		return buttonPane;
	}
	
	private GridPane logoPane() {
		ImageView logo = new ImageView(ImageTemplate.logo);
		logo.setFitHeight(80);
		logo.setFitWidth(80);
		logo.setPreserveRatio(true);
		
		Label header = new Label("Artistic Step");
		header.setId("header");
		
		Label greeting = new Label("Hello, " + Customer.getCurrentCustomer().getName());
		greeting.setId("greeting");
		
		GridPane logoPane = new GridPane();
		logoPane.setId("logoPane");
		logoPane.add(logo, 0, 0, 1, 2);
		logoPane.add(header, 1, 0);
		logoPane.add(greeting, 1, 1);
		
		return logoPane;
	}
}
