package table;

import database.CartDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Shoes;

public class CartTable {
	
	private static CartTable cartTableInstance;
	
	public TableView<Shoes> cartTable;
	
	public static CartTable getInstance() {
		if(cartTableInstance == null) {
			cartTableInstance = new CartTable();
		}
		
		return cartTableInstance;
	}

	public TableView<Shoes> createCartTable() {
		cartTable = new TableView<>();
			
		TableColumn<Shoes, String> modelColumn = new TableColumn<>("Name");
		TableColumn<Shoes, String> brandColumn = new TableColumn<>("Brand");
		TableColumn<Shoes, String> colorColumn = new TableColumn<>("Color");
		TableColumn<Shoes, Integer> priceColumn = new TableColumn<>("Price");
		TableColumn<Shoes, Integer> quantityColumn = new TableColumn<>("Quantity");
		
		modelColumn.setCellValueFactory(new PropertyValueFactory<Shoes, String>("name"));
		brandColumn.setCellValueFactory(new PropertyValueFactory<Shoes, String>("brand"));
		colorColumn.setCellValueFactory(new PropertyValueFactory<Shoes, String>("color"));
		priceColumn.setCellValueFactory(new PropertyValueFactory<Shoes, Integer>("price"));
		quantityColumn.setCellValueFactory(new PropertyValueFactory<Shoes, Integer>("quantity"));
		
		cartTable.getColumns().add(modelColumn);
		cartTable.getColumns().add(brandColumn);
		cartTable.getColumns().add(colorColumn);
		cartTable.getColumns().add(priceColumn);
		cartTable.getColumns().add(quantityColumn);
		
		cartTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
		cartTable.getStylesheets().add(ShoesTable.class.getResource("/styles/tableview.css").toExternalForm());
		
		return cartTable;
	}
	
	public void refreshTable() {
		ObservableList<Shoes> updatedList = FXCollections.observableArrayList(CartDatabase.getCartList());
		
		cartTable.refresh();
		cartTable.setItems(updatedList);
	}
	
	public TableView<Shoes> getTable() {
		return cartTable;
	}

}
