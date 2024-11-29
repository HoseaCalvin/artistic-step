package view;

import controller.DeleteController;
import controller.ImagePaneController;
import controller.TopMenuController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import model.Shoes;
import table.CartTable;
import template.ComponentTemplate;
import template.RegionTemplate;

public class DeleteView extends SideMenuView {
	
	private Shoes selectedShoes;
	
	public Scene createMainScene() {
		BorderPane mainLayout = new BorderPane();
		mainLayout.setLeft(leftPane());
		mainLayout.setTop(TopMenuController.getInstance().getPane());
		mainLayout.setCenter(centerPane());
		mainLayout.getStylesheets().add(getClass().getResource("/styles/mainmenu.css").toExternalForm());
		
		deleteButton.setOnAction(null);	
		ComponentTemplate.menuButtonIndicator(deleteButton);		
		
		CartTable.getInstance().getTable().getSelectionModel().clearSelection();
		
		return new Scene(mainLayout);
	}
	
	private VBox centerPane() {
		VBox deleteLayout = new VBox();
		deleteLayout.getChildren().addAll(tableCard(), footerCard());
		
		return deleteLayout;
	}
	
	private VBox footerCard() {
		Label priceInfo = new Label("Total Price\t\t: ");
		Label price = new Label("0");
		
		Button formButton = ComponentTemplate.createFormButton("Delete");
		
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
		formLayout.add(RegionTemplate.separateHeight(15), 0, 1);
		formLayout.add(formButton, 0, 2);
		
		HBox footerLayout = new HBox();
		footerLayout.setId("footerLayout");
		footerLayout.getChildren().addAll(formLayout, ImagePaneController.getInstance().getPane());
		HBox.setHgrow(formLayout, Priority.ALWAYS);
		
		VBox footerCard = new VBox();
		footerCard.setId("footerCard");
		footerCard.getChildren().add(footerLayout);
		
		setButtonBind(formButton);
		setTableAction(formButton, price);
		
		return footerCard;
	}
	
	private VBox tableCard() {
		VBox tableCard = new VBox();
		tableCard.setId("tableCard");
		tableCard.getChildren().add(CartTable.getInstance().getTable());
		
		return tableCard;
	}
	
	private void setButtonBind(Button formButton) {
		formButton.disableProperty().bind(CartTable.getInstance().getTable().getSelectionModel().selectedItemProperty().isNull());
	}
	
	private void setTableAction(Button formButton, Label price) {
		CartTable.getInstance().getTable().getSelectionModel().selectedItemProperty().addListener((obs, oldItem, newItem) -> {
			selectedShoes = newItem;

			DeleteController.getInstance().calculateShoesPrice(price);
			
			if(selectedShoes != null) {
				ImagePaneController.getInstance().changeImage(selectedShoes.getId());				
			}
			
			DeleteController.getInstance().setButtonAction(formButton, selectedShoes);
		});
	}

}
