package view;

import controller.ImagePaneController;
import controller.OrderController;
import controller.TopMenuController;
import database.CartDatabase;
import database.ShoesDatabase;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import model.Shoes;
import table.CartTable;
import table.ShoesTable;
import template.ComponentTemplate;
import template.RegionTemplate;

public class OrderView extends SideMenuView {
	
	private Shoes selectedShoes;
	
	public OrderView() {
		ShoesTable.getInstance().createShoesTable();
		ShoesTable.getInstance().getTable().getItems().addAll(ShoesDatabase.getShoesList());
		
		CartTable.getInstance().createCartTable();
		CartTable.getInstance().getTable().getItems().addAll(CartDatabase.getCartList());
	}

	public Scene createMainScene() {
		BorderPane mainLayout = new BorderPane();
		mainLayout.setLeft(leftPane());
		mainLayout.setTop(TopMenuController.getInstance().getPane());
		mainLayout.setCenter(centerPane());
		mainLayout.getStylesheets().add(getClass().getResource("/styles/mainmenu.css").toExternalForm());
		
		orderButton.setOnAction(null);
		
		ComponentTemplate.menuButtonIndicator(orderButton);
		
		return new Scene(mainLayout);
	}
	
	private VBox centerPane() {
		VBox orderLayout = new VBox();
		orderLayout.getChildren().addAll(tableCard(), footerCard());
		
		return orderLayout;
	}
	
	private VBox footerCard() {		
		Label priceInfo = new Label("Total Price\t\t:");
		Label price = new Label("0");
		
		Label colorInfo = new Label("Color\t\t\t:");
		TextField color = ComponentTemplate.createTextField("Input Color");
		
		Label quantityInfo = new Label("Quantity\t\t\t:");
		Spinner<Integer> quantity = new Spinner<Integer>(1, 100, 1, 1);
		
		Button formButton = ComponentTemplate.createFormButton("Order");
		
		GridPane formLayout = new GridPane();
		formLayout.setId("formLayout");
		formLayout.setHgap(RegionTemplate.H_GAP);
		formLayout.setVgap(RegionTemplate.V_GAP);
		formLayout.getColumnConstraints().addAll(
				RegionTemplate.columnConstraints(20), 
				RegionTemplate.columnConstraints(65)
			);
		formLayout.add(priceInfo, 0, 0);
		formLayout.add(price, 1, 0);
		formLayout.add(colorInfo, 0, 1);
		formLayout.add(color, 1, 1);
		formLayout.add(quantityInfo, 0, 2);
		formLayout.add(quantity, 1, 2);
		formLayout.add(RegionTemplate.separateHeight(15), 0, 3);
		formLayout.add(formButton, 0, 4);
		
		HBox footerLayout = new HBox();
		footerLayout.setId("footerLayout");
		footerLayout.getChildren().addAll(formLayout, ImagePaneController.getInstance().getPane());
		HBox.setHgrow(formLayout, Priority.ALWAYS);
		
		VBox footerCard = new VBox();
		footerCard.setId("footerCard");
		footerCard.getChildren().add(footerLayout);
		
		setButtonBind(formButton, color);
		setTableAction(formButton, price, color, quantity);
		
		return footerCard;
	}
	
	private VBox tableCard() {
		VBox tableCard = new VBox();
		tableCard.setId("tableCard");
		tableCard.getChildren().add(ShoesTable.getInstance().getTable());
		
		return tableCard;
	}
	
	private void setButtonBind(Button formButton, TextField color) {
		formButton.disableProperty().bind(color.textProperty().isEmpty()
				.or(ShoesTable.getInstance().getTable().getSelectionModel().selectedItemProperty().isNull()));
	}
	
	private void setTableAction(Button formButton, Label price, TextField color, Spinner<Integer> quantity) {
		ShoesTable.getInstance().getTable().getSelectionModel().selectedItemProperty().addListener((obs, oldItem, newItem) -> {
			selectedShoes = newItem;
	
			ImagePaneController.getInstance().changeImage(selectedShoes.getId());
			
			OrderController.getInstance().getPriceValue(price);
			OrderController.getInstance().updateQuantity(price, quantity, selectedShoes);
			OrderController.getInstance().setButtonAction(formButton, selectedShoes, color, quantity);
			
			color.clear();
			quantity.getValueFactory().setValue(1);
		});
	}
}
