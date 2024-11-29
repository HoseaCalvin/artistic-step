package view;

import controller.TopMenuController;
import database.TransactionDatabase;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import table.TransactionTable;
import template.ComponentTemplate;

public class TransactionHistoryView extends SideMenuView {

	public TransactionHistoryView() {
		TransactionTable.getInstance().createTransactionTable();
		TransactionTable.getInstance().getTable().getItems().addAll(TransactionDatabase.getList());
	}
	
	public Scene createMainScene() {
		BorderPane mainLayout = new BorderPane();
		mainLayout.setLeft(leftPane());
		mainLayout.setTop(TopMenuController.getInstance().getPane());
		mainLayout.setCenter(centerPane());
		mainLayout.getStylesheets().add(getClass().getResource("/styles/mainmenu.css").toExternalForm());
		
		transactionHistoryButton.setOnAction(null);
		
		ComponentTemplate.menuButtonIndicator(transactionHistoryButton);
		
		return new Scene(mainLayout);
	}

	private VBox centerPane() {
		VBox orderLayout = new VBox();
		orderLayout.getChildren().addAll(tableCard());
		
		return orderLayout;
	}
	
	private VBox tableCard() {
		VBox tableCard = new VBox();
		tableCard.setId("tableCard");
		tableCard.getChildren().add(TransactionTable.getInstance().getTable());
	    VBox.setVgrow(TransactionTable.getInstance().getTable(), Priority.ALWAYS);
	    VBox.setVgrow(tableCard, Priority.ALWAYS);
		
		return tableCard;
	}
}
