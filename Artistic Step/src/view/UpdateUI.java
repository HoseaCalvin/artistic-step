package view;

import java.util.concurrent.atomic.AtomicInteger;

import controller.Images;
import controller.Update;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import table.UserTableUI;

public class UpdateUI extends SideMenuUI {
	
	private AtomicInteger price;
	
	private Button formButton;
	
	public UpdateUI() {
		// AtomicInteger allows passing by reference.
		price = new AtomicInteger();
		
		// 'formButton' must be set to global to allow better manipulation.
		formButton = createFormButton("Update");
	}
	
	public Scene createMainScene() {
		BorderPane mainLayout = new BorderPane();
		mainLayout.setLeft(leftPane());
		mainLayout.setTop(topPane());
		mainLayout.setCenter(centerPane());
		mainLayout.getStylesheets().add(getClass().getResource("/styles/menu.css").toExternalForm());
		
		// Prevents user from clicking the same button in the same page.
		updateButton.setOnAction(null);
		
		// Prevents user from clicking the button before clicking a table row.
		formButton.setDisable(true);
		
		// Indicates the current menu by darkening the button color.
		menuButtonIndicator(updateButton);
		
		return new Scene(mainLayout);
	}
	
	private VBox centerPane() {
		VBox updateLayout = new VBox();
		updateLayout.getChildren().addAll(tableCard(), footerCard());
		updateLayout.getStylesheets().add(getClass().getResource("/styles/submenu.css").toExternalForm());
		
		return updateLayout;
	}

	private VBox footerCard() {
		Label idInfo = createLabel("Transaction ID\t\t:");
		Label id = createLabel("");
		
		Label colorInfo = createLabel("Color\t\t\t:");
		TextField color = createTextField("Input Color");

		formButton.setOnAction(e -> setButtonAction(id, color));
		
		// Commits several actions when a table row is clicked.
		setTableAction(id);
		
		GridPane formLayout = new GridPane();
		formLayout.setPadding(new Insets(FORM_TOP_PADDING, FORM_RIGHT_PADDING, 
				FORM_BOTTOM_PADDING, FORM_LEFT_PADDING));
		formLayout.setHgap(H_GAP);
		formLayout.setVgap(V_GAP);
		formLayout.getColumnConstraints().addAll(columnConstraints(20), columnConstraints(65));
		formLayout.add(idInfo, 0, 1);
		formLayout.add(id, 1, 1);
		formLayout.add(colorInfo, 0, 2);
		formLayout.add(color, 1, 2);
		formLayout.add(separateHeight(15), 0, 3);
		formLayout.add(formButton, 0, 4);
		
		HBox footerLayout = new HBox();
		footerLayout.setId("footerLayout");
		footerLayout.getChildren().addAll(formLayout, imagePane());
		HBox.setHgrow(formLayout, Priority.ALWAYS);
		
		VBox footerCard = new VBox();
		footerCard.setPadding(new Insets(CARD_TOP_PADDING, CARD_RIGHT_PADDING, 
				CARD_BOTTOM_PADDING, CARD_LEFT_PADDING));
		footerCard.getChildren().add(footerLayout);
			
		return footerCard;
	}
	
	private VBox tableCard() {
		VBox tableCard = new VBox();
		tableCard.setPadding(new Insets(CARD_TOP_PADDING, CARD_RIGHT_PADDING, 
				CARD_BOTTOM_PADDING, CARD_LEFT_PADDING));
		tableCard.getChildren().add(UserTableUI.userTable);
		
		return tableCard;
	}
	
	private void setButtonAction(Label id, TextField color) {
		// Verifies if every input is in accordance with the data type.
		try {
			Update.getInstance().updateContent(id, color);
			AlertUI.informAction("Product Update", "The product has been successfully updated!");
		} catch(Exception f) {
			f.printStackTrace();
		}
	}
	
	private void setTableAction(Label id) {
		UserTableUI.userTable.getSelectionModel().selectedItemProperty().addListener(e -> {
			Update.getInstance().getRowValue(id);
			Update.getInstance().getData(price);
			
			formButton.setDisable(false);
			
			// Change image every time a table row is clicked.
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
