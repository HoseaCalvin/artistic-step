package table;

import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Shoes;

public class ShoesTableUI {
	
	public static TableView<Shoes> shoesTable;

	public static TableView<Shoes> createShopTable() {
		shoesTable = new TableView<>();
			
		TableColumn<Shoes, String> modelColumn = new TableColumn<>("Model");
		TableColumn<Shoes, String> brandColumn = new TableColumn<>("Brand");
		TableColumn<Shoes, Integer> priceColumn = new TableColumn<>("Price");
		
		modelColumn.setCellValueFactory(new PropertyValueFactory<Shoes, String>("model"));
		brandColumn.setCellValueFactory(new PropertyValueFactory<Shoes, String>("brand"));
		priceColumn.setCellValueFactory(new PropertyValueFactory<Shoes, Integer>("price"));
		
		shoesTable.getColumns().add(modelColumn);
		shoesTable.getColumns().add(brandColumn);
		shoesTable.getColumns().add(priceColumn);
		
		shoesTable.setPadding(new Insets(20, 20, 20, 20));
		shoesTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
		shoesTable.getStylesheets().add(ShoesTableUI.class.getResource("/styles/tableview.css").toExternalForm());
		
		return shoesTable;
	}

}
