package model;

import java.time.LocalDate;

public class Customer {

	private String id;
	private String name;
	private String email;
	private LocalDate dateOfBirth;
	private String gender;
	private String password;
	
	private static Customer currentCustomer = new Customer();
	
	public Customer(String id, String name, String email, LocalDate dateOfBirth, String gender, String password) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.password = password;
	}
	
	public Customer() {
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public String getEmail() {
		return email;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	
	public String getGender() {
		return gender;
	}

	public String getPassword() {
		return password;
	}
	
	public static Customer getCurrentCustomer() {
		return Customer.currentCustomer;
	}

	public static void setCurrentCustomer(Customer user) {
		currentCustomer = user;
	}
}
