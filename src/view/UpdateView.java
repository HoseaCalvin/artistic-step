package view;

import controller.ImagePaneController;
import controller.TopMenuController;
import controller.UpdateController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import model.Shoes;
import table.CartTable;
import template.ComponentTemplate;
import template.RegionTemplate;

public class UpdateView extends SideMenuView {
	
	private Shoes selectedShoes;
	
	public Scene createMainScene() {
		BorderPane mainLayout = new BorderPane();
		mainLayout.setLeft(leftPane());
		mainLayout.setTop(TopMenuController.getInstance().getPane());
		mainLayout.setCenter(centerPane());
		mainLayout.getStylesheets().add(getClass().getResource("/styles/mainmenu.css").toExternalForm());
		
		updateButton.setOnAction(null);
		ComponentTemplate.menuButtonIndicator(updateButton);
		
		CartTable.getInstance().getTable().getSelectionModel().clearSelection();
		
		return new Scene(mainLayout);
	}
	
	private VBox centerPane() {
		VBox updateLayout = new VBox();
		updateLayout.getChildren().addAll(tableCard(), footerCard());
		
		return updateLayout;
	}

	private VBox footerCard() {
		Label colorInfo = new Label("Color\t\t\t:");
		TextField color = ComponentTemplate.createTextField("Input Color");

		Button formButton = ComponentTemplate.createFormButton("Update");
		GridPane formLayout = new GridPane();
		formLayout.setId("formLayout");
		formLayout.setHgap(RegionTemplate.H_GAP);
		formLayout.setVgap(RegionTemplate.V_GAP);
		formLayout.getColumnConstraints().addAll(
				RegionTemplate.columnConstraints(20), 
				RegionTemplate.columnConstraints(65)
			);
		formLayout.add(colorInfo, 0, 0);
		formLayout.add(color, 1, 0);
		formLayout.add(RegionTemplate.separateHeight(15), 0, 1);
		formLayout.add(formButton, 0, 2);
		
		HBox footerLayout = new HBox();
		footerLayout.setId("footerLayout");
		footerLayout.getChildren().addAll(formLayout, ImagePaneController.getInstance().getPane());
		HBox.setHgrow(formLayout, Priority.ALWAYS);
		
		VBox footerCard = new VBox();
		footerCard.setId("footerCard");
		footerCard.getChildren().add(footerLayout);

		setButtonBind(formButton, color);
		setTableAction(formButton, color);
			
		return footerCard;
	}
	
	private VBox tableCard() {
		VBox tableCard = new VBox();
		tableCard.setId("tableCard");
		tableCard.getChildren().add(CartTable.getInstance().getTable());
		
		return tableCard;
	}
	
	private void setButtonBind(Button formButton, TextField color) {
		formButton.disableProperty().bind(color.textProperty().isEmpty()
				.or(CartTable.getInstance().getTable().getSelectionModel().selectedItemProperty().isNull()));
	}
	
	private void setTableAction(Button formButton, TextField color) {
		CartTable.getInstance().getTable().getSelectionModel().selectedItemProperty().addListener((obs, oldItem, newItem) -> {
			selectedShoes = newItem;

			if(selectedShoes != null) {
				ImagePaneController.getInstance().changeImage(selectedShoes.getId());
			}
		     
			UpdateController.getInstance().setButtonAction(formButton, selectedShoes, color);
			
			color.clear();
			
		});
	}

}
