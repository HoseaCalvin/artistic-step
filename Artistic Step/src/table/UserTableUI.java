package table;

import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Shoes;

public class UserTableUI {
	
	public static TableView<Shoes> userTable;

	public static TableView<Shoes> createUserTable() {
		userTable = new TableView<>();
			
		TableColumn<Shoes, String> modelColumn = new TableColumn<>("Model");
		TableColumn<Shoes, String> brandColumn = new TableColumn<>("Brand");
		TableColumn<Shoes, String> colorColumn = new TableColumn<>("Color");
		TableColumn<Shoes, Integer> priceColumn = new TableColumn<>("Price");
		TableColumn<Shoes, Integer> quantityColumn = new TableColumn<>("Quantity");
		
		modelColumn.setCellValueFactory(new PropertyValueFactory<Shoes, String>("model"));
		brandColumn.setCellValueFactory(new PropertyValueFactory<Shoes, String>("brand"));
		colorColumn.setCellValueFactory(new PropertyValueFactory<Shoes, String>("color"));
		priceColumn.setCellValueFactory(new PropertyValueFactory<Shoes, Integer>("price"));
		quantityColumn.setCellValueFactory(new PropertyValueFactory<Shoes, Integer>("quantity"));
		
		userTable.getColumns().add(modelColumn);
		userTable.getColumns().add(brandColumn);
		userTable.getColumns().add(colorColumn);
		userTable.getColumns().add(priceColumn);
		userTable.getColumns().add(quantityColumn);
		
		userTable.setPadding(new Insets(20, 20, 20, 20));
		userTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
		userTable.getStylesheets().add(ShoesTableUI.class.getResource("/styles/tableview.css").toExternalForm());
		
		return userTable;
	}

}
