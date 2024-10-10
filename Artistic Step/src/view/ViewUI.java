package view;

import controller.Images;
import controller.View;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import table.UserTableUI;

public class ViewUI extends SideMenuUI {

	private Button formButton;
	
	public ViewUI() {
		// 'formButton' must be set to global to allow better manipulation.
		formButton = createFormButton("Print");
	}

	public Scene createMainScene() {
		BorderPane mainLayout = new BorderPane();
		mainLayout.setLeft(leftPane());
		mainLayout.setTop(topPane());
		mainLayout.setCenter(centerPane());
		mainLayout.getStylesheets().add(getClass().getResource("/styles/menu.css").toExternalForm());
		
		// Prevents user from clicking the same button in the same page.
		viewButton.setOnAction(null);
		
		// Prevents user from clicking the button before clicking a table row.
		formButton.setDisable(true);
		
		// Indicates the current menu by darkening the button color.
		menuButtonIndicator(viewButton);
		
		return new Scene(mainLayout);
	}
	
	private VBox centerPane() {
		VBox viewLayout = new VBox();
		viewLayout.setId("menuLayout");
		viewLayout.getChildren().addAll(tableCard(), footerCard());
		viewLayout.getStylesheets().add(getClass().getResource("/styles/submenu.css").toExternalForm());
	
		return viewLayout;
	}

	private VBox footerCard() {
		// Shoes Form
		Label transactionIdInfo = createLabel("Transaction ID\t\t:");
		Label transactionId = createLabel("");
		
		Label shoesIdInfo = createLabel("Shoes ID\t\t\t:");
		Label shoesId = createLabel("");
		
		Label moneyInfo = createLabel("Money\t\t\t:");
		Label money = createLabel("");
		
		Label changeInfo = createLabel("Change\t\t\t:");
		Label change = createLabel("");
		
		formButton.setOnAction(e -> setButtonAction());

		// Commits several actions when a table row is clicked.
		setTableAction(transactionId, shoesId, money, change);
		
		GridPane formLayout = new GridPane();
		formLayout.setPadding(new Insets(FORM_TOP_PADDING, FORM_RIGHT_PADDING, 
				FORM_BOTTOM_PADDING, FORM_LEFT_PADDING));
		formLayout.setHgap(H_GAP);
		formLayout.setVgap(V_GAP);
		formLayout.getColumnConstraints().addAll(columnConstraints(20), columnConstraints(60));
		formLayout.add(transactionIdInfo, 0, 1);
		formLayout.add(transactionId, 1, 1);
		formLayout.add(shoesIdInfo, 0, 2);
		formLayout.add(shoesId, 1, 2);
		formLayout.add(moneyInfo, 0, 3);
		formLayout.add(money, 1, 3);
		formLayout.add(changeInfo, 0, 4);
		formLayout.add(change, 1, 4);
		formLayout.add(separateHeight(15), 0, 5);
		formLayout.add(formButton, 0, 6);
		
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
	
	private void setButtonAction() {
		View.getInstance().printReceipt();
		AlertUI.informAction("Transaction Receipt Printing", 
				"Your transaction receipt has been printed. Please check the file directory!");
	}

	private void setTableAction(Label transactionId, Label shoesId, Label money, Label change) {
		UserTableUI.userTable.getSelectionModel().selectedItemProperty().addListener(e -> {
			View.getInstance().getRowValue(transactionId, shoesId, money, change);
			
			formButton.setDisable(false);
			
			// Change image every time a table row is clicked.
			Images.getInstance().changeImage();
		});
	}
}
