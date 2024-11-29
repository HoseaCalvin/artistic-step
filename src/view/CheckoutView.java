package view;

import controller.CheckoutController;
import controller.ImagePaneController;
import controller.TopMenuController;
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

public class CheckoutView extends SideMenuView {

	private Shoes selectedShoes; 

	public Scene createMainScene() {
		BorderPane mainLayout = new BorderPane();
		mainLayout.setLeft(leftPane());
		mainLayout.setTop(TopMenuController.getInstance().getPane());
		mainLayout.setCenter(centerPane());
		mainLayout.getStylesheets().add(getClass().getResource("/styles/mainmenu.css").toExternalForm());
		
		checkoutButton.setOnAction(null);
		ComponentTemplate.menuButtonIndicator(checkoutButton);
		
		CartTable.getInstance().getTable().getSelectionModel().clearSelection();
		
		return new Scene(mainLayout);
	}
	
	private VBox centerPane() {
		VBox viewLayout = new VBox();
		viewLayout.setId("menuLayout");
		viewLayout.getChildren().addAll(tableCard(), footerCard());
	
		return viewLayout;
	}

	private VBox footerCard() {
		Label priceInfo = new Label("Total Price\t\t: ");
		Label price = new Label("");
		
		Label totalInfo = new Label("Grand Total\t\t:");
		Label total = new Label("");
		
		Label moneyInfo = new Label("Money\t\t\t:");
		TextField money = ComponentTemplate.createTextField("Input Money");
		
		Button formButton = ComponentTemplate.createFormButton("Checkout");
		
		GridPane formLayout = new GridPane();
		formLayout.setId("formLayout");
		formLayout.setHgap(RegionTemplate.H_GAP);
		formLayout.setVgap(RegionTemplate.V_GAP);
		formLayout.getColumnConstraints().addAll(
				RegionTemplate.columnConstraints(20), 
				RegionTemplate.columnConstraints(60)
			);
		formLayout.add(priceInfo, 0, 0);
		formLayout.add(price, 1, 0);
		formLayout.add(totalInfo, 0, 1);
		formLayout.add(total, 1, 1);
		formLayout.add(moneyInfo, 0, 2);
		formLayout.add(money, 1, 2);
		formLayout.add(RegionTemplate.separateHeight(15), 0, 3);
		formLayout.add(formButton, 0, 4);
		
		HBox footerLayout = new HBox();
		footerLayout.setId("footerLayout");
		footerLayout.getChildren().addAll(formLayout, ImagePaneController.getInstance().getPane());
		HBox.setHgrow(formLayout, Priority.ALWAYS);
		
		VBox footerCard = new VBox();
		footerCard.setId("footerCard");
		footerCard.getChildren().add(footerLayout);
		
		setTableAction(price);
		CheckoutController.getInstance().calculateTotalPrice(total);
		CheckoutController.getInstance().setButtonAction(formButton, total, money);
		
		return footerCard;
	}
	
	private VBox tableCard() {
		VBox tableCard = new VBox();
		tableCard.setId("tableCard");
		tableCard.getChildren().add(CartTable.getInstance().getTable());
		
		return tableCard;
	}

	private void setTableAction(Label price) {
		CartTable.getInstance().getTable().getSelectionModel().selectedItemProperty().addListener((obs, oldItem, newItem) -> {
			selectedShoes = newItem;

			CheckoutController.getInstance().calculateShoesPrice(price);

			if(selectedShoes != null) {
				ImagePaneController.getInstance().changeImage(selectedShoes.getId());					
			}
		});
	}
}
