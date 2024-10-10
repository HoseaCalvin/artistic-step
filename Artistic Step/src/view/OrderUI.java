package view;

import java.util.concurrent.atomic.AtomicInteger;

import controller.Images;
import controller.Order;
import javafx.geometry.Insets;
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
import javafx.scene.text.Text;
import table.ShoesTableUI;

public class OrderUI extends SideMenuUI {
	
	private static OrderUI orderView;
	
	private Text model;
	private Text brand;
	private AtomicInteger initialPrice;
	
	private Button formButton;
	
	public OrderUI() {
		// Text allows passing String by reference.
		model = new Text();
		brand = new Text();
		
		// AtomicInteger allows passing Integer by reference.
		initialPrice = new AtomicInteger();
		
		// 'formButton' must be set to global to allow better manipulation.
		formButton = createFormButton("Order");
	}
	
	public static OrderUI getInstance() {
		if(orderView == null) {
			orderView = new OrderUI();
		}
		
		return orderView;
	}

	public Scene createMainScene() {
		BorderPane mainLayout = new BorderPane();
		mainLayout.setLeft(leftPane());
		mainLayout.setTop(topPane());
		mainLayout.setCenter(centerPane());
		mainLayout.getStylesheets().add(getClass().getResource("/styles/menu.css").toExternalForm());
		
		// Prevents user from clicking the same button in the same page.
		orderButton.setOnAction(null);
		
		// Prevents user from clicking the button before clicking a table row.
		formButton.setDisable(true);
		
		// Indicates the current menu by darkening the button color.
		menuButtonIndicator(orderButton);
		
		return new Scene(mainLayout);
	}
	
	private VBox centerPane() {
		VBox orderLayout = new VBox();
		orderLayout.getChildren().addAll(tableCard(), footerCard());
		orderLayout.getStylesheets().add(getClass().getResource("/styles/submenu.css").toExternalForm());
		
		return orderLayout;
	}
	
	private VBox footerCard() {		
		// Shoes Form
		Label idInfo = createLabel("Shoes ID\t\t\t:");
		Label id = createLabel("");
		
		Label priceInfo = createLabel("Total Price\t\t:");
		Label price = createLabel("");
		
		Label colorInfo = createLabel("Color\t\t\t:");
		TextField color = createTextField("Input Color");
		
		Label quantityInfo = createLabel("Quantity\t\t\t:");
		Spinner<Integer> quantity = new Spinner<Integer>(1, 100, 1, 1);
		
		Label moneyInfo = createLabel("Money\t\t\t:");
		TextField money = createTextField("Input Money");
		
		GridPane formLayout = new GridPane();
		formLayout.setPadding(new Insets(FORM_TOP_PADDING, FORM_RIGHT_PADDING, 
				FORM_BOTTOM_PADDING, FORM_LEFT_PADDING));
		formLayout.setHgap(H_GAP);
		formLayout.setVgap(V_GAP);
		formLayout.getColumnConstraints().addAll(columnConstraints(20), columnConstraints(65));
		formLayout.add(idInfo, 0, 1);
		formLayout.add(id, 1, 1);
		formLayout.add(priceInfo, 0, 2);
		formLayout.add(price, 1, 2);
		formLayout.add(colorInfo, 0, 3);
		formLayout.add(color, 1, 3);
		formLayout.add(quantityInfo, 0, 4);
		formLayout.add(quantity, 1, 4);
		formLayout.add(moneyInfo, 0, 5);
		formLayout.add(money, 1, 5);
		formLayout.add(separateHeight(15), 0, 6);
		formLayout.add(formButton, 0, 7);
		
		HBox footerLayout = new HBox();
		footerLayout.setId("footerLayout");
		footerLayout.getChildren().addAll(formLayout, imagePane());
		HBox.setHgrow(formLayout, Priority.ALWAYS);
		
		VBox footerCard = new VBox();
		footerCard.setPadding(new Insets(CARD_TOP_PADDING, CARD_RIGHT_PADDING, 
				CARD_BOTTOM_PADDING, CARD_LEFT_PADDING));
		footerCard.getChildren().add(footerLayout);
		
		// Commits an action when a button is clicked.
		formButton.setOnAction(e -> setButtonAction(id, price, color, quantity, money));
		
		// Updates quantity value if the product amount is incremented or decremented.
		updateQuantity(quantity, price);
		
		// Commits several actions when a table row is clicked.
		setTableAction(id, price);

		return footerCard;
	}
	
	private VBox tableCard() {
		VBox tableCard = new VBox();
		tableCard.setPadding(new Insets(CARD_TOP_PADDING, CARD_RIGHT_PADDING, 
				CARD_BOTTOM_PADDING, CARD_LEFT_PADDING));
		tableCard.getChildren().add(ShoesTableUI.shoesTable);
		
		return tableCard;
	}
	
	private void updateQuantity(Spinner<Integer> quantity, Label price) {
		//'priceData' shows the total price based on 'quantityData' value and the price of the shoes.
		quantity.valueProperty().addListener((observable, oldValue, newValue) -> {
			Order.getInstance().updateQuantity(price, initialPrice, quantity);
		});
	}
	
	private void setButtonAction(Label id, Label price, TextField color, 
			Spinner<Integer> quantity, TextField money) {
			Order.getInstance().validateContent(id, model, brand, color, price, quantity, money);
	}
	
	private void setTableAction(Label id, Label price) {
		ShoesTableUI.shoesTable.getSelectionModel().selectedItemProperty().addListener(e -> {
			Order.getInstance().getRowValue(id, price);
			Order.getInstance().getData(model, brand, initialPrice);
			
			// Allows user to click button after selecting a table row.
			formButton.setDisable(false);
			
			// Changes image every time a table row is clicked.
			Images.getInstance().changeImage();
		});
	}

	private TextField createTextField(String promptText) {
		TextField textField = new TextField();
		textField.setPromptText(promptText);
		textField.setMaxHeight(10);
		textField.setMaxWidth(150);
		
		return textField;
	}
}
