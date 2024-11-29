package table;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Shoes;

public class ShoesTable {
	
	private static ShoesTable shoesTableInstance;
	
	private TableView<Shoes> shoesTable;

	public static ShoesTable getInstance() {
		if(shoesTableInstance == null) {
			shoesTableInstance = new ShoesTable();
		}
		
		return shoesTableInstance;
	}
	
	public TableView<Shoes> createShoesTable() {
		shoesTable = new TableView<>();
			
		TableColumn<Shoes, String> modelColumn = new TableColumn<>("Name");
		TableColumn<Shoes, String> brandColumn = new TableColumn<>("Brand");
		TableColumn<Shoes, Integer> priceColumn = new TableColumn<>("Price");
		
		modelColumn.setCellValueFactory(new PropertyValueFactory<Shoes, String>("name"));
		brandColumn.setCellValueFactory(new PropertyValueFactory<Shoes, String>("brand"));
		priceColumn.setCellValueFactory(new PropertyValueFactory<Shoes, Integer>("price"));
		
		shoesTable.getColumns().add(modelColumn);
		shoesTable.getColumns().add(brandColumn);
		shoesTable.getColumns().add(priceColumn);
		
		shoesTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
		shoesTable.getStylesheets().add(ShoesTable.class.getResource("/styles/tableview.css").toExternalForm());
		
		return shoesTable;
	}
	
	public TableView<Shoes> getTable() {
		return shoesTable;
	}

}
