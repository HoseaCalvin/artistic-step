package table;

import java.time.LocalDate;

import database.TransactionDatabase;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Transaction;

public class TransactionTable {

	private static TransactionTable transactionTableInstance;
	
	private TableView<Transaction> transactionTable;
	
	public static TransactionTable getInstance() {
		if(transactionTableInstance == null) {
			transactionTableInstance = new TransactionTable();
		}
		
		return transactionTableInstance;
	}
	
	public TableView<Transaction> createTransactionTable() {
		transactionTable = new TableView<>();
		
		TableColumn<Transaction, LocalDate> transactionDateColumn = new TableColumn<>("Date");
		TableColumn<Transaction, String> shoesNameColumn = new TableColumn<>("Shoes Name");
		TableColumn<Transaction, String> shoesColorColumn = new TableColumn<>("Shoes Color");
		TableColumn<Transaction, Integer> shoesQuantityColumn = new TableColumn<>("Quantity");
		
		transactionDateColumn.setCellValueFactory(new PropertyValueFactory<Transaction, LocalDate>("date"));
		shoesNameColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("shoesName"));
		shoesColorColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("shoesColor"));
		shoesQuantityColumn.setCellValueFactory(new PropertyValueFactory<Transaction, Integer>("quantity"));
		
		transactionTable.getColumns().add(transactionDateColumn);
		transactionTable.getColumns().add(shoesNameColumn);
		transactionTable.getColumns().add(shoesColorColumn);
		transactionTable.getColumns().add(shoesQuantityColumn);
		
		transactionTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
		transactionTable.getStylesheets().add(getClass().getResource("/styles/tableview.css").toExternalForm());
		
		return transactionTable;
	}
	
	public void refreshTable() {
		transactionTable.getItems().clear();
		transactionTable.getItems().addAll(TransactionDatabase.getList());
		
		transactionTable.refresh();
	}
	
	public TableView<Transaction> getTable() {
		return transactionTable;
	}

}
