package view;

import controller.SideMenuController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import template.ComponentTemplate;
import template.ImageTemplate;

public abstract class SideMenuView {

	protected Button orderButton;
	protected Button updateButton;
	protected Button deleteButton;
	protected Button checkoutButton;
	protected Button transactionHistoryButton;
	
	public SideMenuView() {
		orderButton = ComponentTemplate.createMenuButton("Order Shoes", ImageTemplate.orderShoes);		
		updateButton = ComponentTemplate.createMenuButton("Update Shoes", ImageTemplate.updateShoes);		
		deleteButton = ComponentTemplate.createMenuButton("Delete Shoes", ImageTemplate.deleteShoes);
		checkoutButton = ComponentTemplate.createMenuButton("Checkout Shoes", ImageTemplate.checkoutShoes);
		transactionHistoryButton = ComponentTemplate.createMenuButton("Transaction History", ImageTemplate.historyShoes);
	}
	
	public VBox leftPane() {
		VBox leftPane = new VBox(15);
		leftPane.setId("leftPane");
		leftPane.getChildren().addAll(orderButton, updateButton, deleteButton, 
				checkoutButton, transactionHistoryButton);
		
		SideMenuController.getInstance().setButtonsAction(orderButton, 
				updateButton, deleteButton, checkoutButton, transactionHistoryButton);

		return leftPane;
	}
	
	public abstract Scene createMainScene();
	
}
