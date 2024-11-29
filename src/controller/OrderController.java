package controller;

import database.CartDatabase;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import model.Shoes;
import table.CartTable;
import table.ShoesTable;
import view.AlertView;
import view.OrderView;

public class OrderController {
	
	private static OrderController orderController;
	
    private CartDatabase cartDatabase = new CartDatabase();
    
	public static OrderController getInstance() {
		if(orderController == null) {
			orderController = new OrderController();
		}
		
		return orderController;
	}
	
	public Scene getScene() {
		OrderView orderInterface = new OrderView();
		
		return orderInterface.createMainScene();
	}
	
	public void setButtonAction(Button formButton, Shoes selectedShoes, TextField color, Spinner<Integer> quantity) {
		formButton.setOnAction(e -> {
			if(!cartDatabase.isItemInCart(selectedShoes)) {
				insertToDatabase(selectedShoes, color.getText(), quantity.getValue());
				
				AlertView.getInformationMessage("Order Successful", "Your order has been added!");	
			} else {
				AlertView.getErrorMessage("Order Failed", "Product is already in your cart!");
			}
				
			color.clear();
			quantity.getValueFactory().setValue(1);
		});
	}
	
	public void getPriceValue(Label price) {
		Shoes selectedShoes = ShoesTable.getInstance().getTable().getSelectionModel().getSelectedItem();
		
		if(selectedShoes != null) {
			price.setText(String.valueOf(selectedShoes.getPrice()));
		}
	}

	public void updateQuantity(Label price, Spinner<Integer> quantity, Shoes selectedShoes) {
	    quantity.valueProperty().addListener(e -> {
	        int totalPrice = selectedShoes.getPrice() * quantity.getValue();
	        price.setText(String.valueOf(totalPrice));
	     });
	}
	
	private void insertToDatabase(Shoes selectedShoes, String color, int quantity) {
        Shoes orderShoes = new Shoes(
                selectedShoes.getId(),
                selectedShoes.getName(),
                selectedShoes.getBrand(),
                selectedShoes.getPrice(),
                color,
                quantity
        );
        
        cartDatabase.insert(orderShoes);
        
        CartTable.getInstance().refreshTable();
    }
	
}
