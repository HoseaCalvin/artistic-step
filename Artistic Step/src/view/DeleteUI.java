package view;

import controller.Delete;
import controller.Images;
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

public class DeleteUI extends SideMenuUI {
	
	private Button formButton;
	
	public DeleteUI() {
		// 'formButton' must be set to global to allow better manipulation.
		formButton = createFormButton("Delete");
	}
	
	public Scene createMainScene() {
		BorderPane mainLayout = new BorderPane();
		mainLayout.setLeft(leftPane());
		mainLayout.setTop(topPane());
		mainLayout.setCenter(centerPane());
		mainLayout.getStylesheets().add(getClass().getResource("/styles/menu.css").toExternalForm());
		
		// Prevents user from clicking the same button in the same page.
		deleteButton.setOnAction(null);
		
		// Prevents user from clicking the button before clicking a table row.
		formButton.setDisable(true);
		
		// Indicates the current menu by darkening the button color.
		menuButtonIndicator(deleteButton);
		
		return new Scene(mainLayout);
	}
	
	private VBox centerPane() {
		VBox deleteLayout = new VBox();
		deleteLayout.getChildren().addAll(tableCard(), footerCard());
		deleteLayout.getStylesheets().add(getClass().getResource("/styles/submenu.css").toExternalForm());
		
		return deleteLayout;
	}
	
	private VBox footerCard() {	
		Label idInfo = createLabel("Transaction ID\t\t:");
		Label id = createLabel("");
		
		formButton.setOnAction(e -> setButtonAction(id));
		
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
		formLayout.add(separateHeight(15), 0, 2);
		formLayout.add(formButton, 0, 3);
		
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
	
	private void setButtonAction(Label id) {
		Delete.getInstance().deleteContent(id);
		AlertUI.informAction("Product Deletion", "Your product has been successfully deleted!");
	}
	
	private void setTableAction(Label id) {
		UserTableUI.userTable.getSelectionModel().selectedItemProperty().addListener(e -> {
			Delete.getInstance().getRowValue(id);
			
			formButton.setDisable(false);
			
			// Change image every time a table row is clicked.
			Images.getInstance().changeImage();
		});
	}

}
