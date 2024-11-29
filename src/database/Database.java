package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import view.AlertView;

public abstract class Database {

	protected Connection connection;
	protected Statement statement;
	protected ResultSet resultSet;
	protected PreparedStatement preStatement;
	
	private final String USERNAME = "root";
	private final String PASSWORD = "";
	
	private final String DATABASE = "artistic_step";
	private final String HOST = "localhost:3306";
	
	private String hostUrl = String.format("jdbc:mysql://%s/%s", HOST, DATABASE);
	
	protected void connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			connection = DriverManager.getConnection(hostUrl, USERNAME, PASSWORD);
			statement = connection.createStatement();
		} catch (SQLException e) {
			AlertView.getErrorMessage("Connection Failure!", "Database is currently offline!");
			e.printStackTrace();
		}
	}
	
	public Date toDate(LocalDate date) {
		return Date.valueOf(date);
	}

}
