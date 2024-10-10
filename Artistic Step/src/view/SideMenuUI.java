package view;

import controller.Images;
import controller.SideMenu;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import model.Shoes;
import table.ShoesTableUI;
import table.UserTableUI;

public abstract class SideMenuUI {
	
	public static final double CARD_TOP_PADDING = 20;
	public static final double CARD_RIGHT_PADDING = 20;
	public static final double CARD_BOTTOM_PADDING = 20;
	public static final double CARD_LEFT_PADDING = 20;
	
	public static final double FORM_TOP_PADDING = 30;
	public static final double FORM_RIGHT_PADDING = 40;
	public static final double FORM_BOTTOM_PADDING = 40;
	public static final double FORM_LEFT_PADDING = 30;
	
	public static final double H_GAP = 15;
	public static final double V_GAP = 15;
	
	private final String orderImage = "file:///C:/Users/hosea/eclipse-workspace/Artistic%20Step/image/logo/Order_Logo.png";
	private final String viewImage = "file:///C:/Users/hosea/eclipse-workspace/Artistic%20Step/image/logo/View_Logo.png";
	private final String updateImage = "file:///C:/Users/hosea/eclipse-workspace/Artistic%20Step/image/logo/Update_Logo.png";
	private final String deleteImage = "file:///C:/Users/hosea/eclipse-workspace/Artistic%20Step/image/logo/Delete_Logo.png";
	
	protected Button orderButton;
	protected Button viewButton;
	protected Button updateButton;
	protected Button deleteButton;
	
	public SideMenuUI() {
		orderButton = createMenuButton("Order Shoes", orderImage);
		viewButton = createMenuButton("View Shoes", viewImage);		
		updateButton = createMenuButton("Update Shoes", updateImage);		
		deleteButton = createMenuButton("Delete Shoes", deleteImage);
		
		ShoesTableUI.shoesTable = ShoesTableUI.createShopTable();
		Shoes.readShopTable();
		
		UserTableUI.userTable =  UserTableUI.createUserTable();
		Shoes.readUserTable();
	}
	
	public abstract Scene createMainScene();
	
	protected VBox leftPane() {
		SideMenu.getInstance().setButtonsAction(orderButton, viewButton, updateButton, deleteButton);
		
		VBox leftPane = new VBox(15);
		leftPane.setAlignment(Pos.CENTER);
		leftPane.setId("leftPane");
		leftPane.getChildren().addAll(orderButton, viewButton, updateButton, deleteButton);

		return leftPane;
	}
	
	protected HBox topPane() {
		ImageView logo = new ImageView(
				"file:///C:/Users/hosea/eclipse-workspace/Artistic%20Step/image/logo/Shoes_Logo.png");
		logo.setFitHeight(75);
		logo.setFitWidth(75);
		logo.setPreserveRatio(true);
		
		HBox image = new HBox();
		image.setPadding(new Insets(20, 5, 20, 20));
		image.getChildren().add(logo);
		
		Label header = new Label("Artistic Step");
		header.setId("header");
		
		HBox topPane = new HBox(10);
		topPane.setId("topPane");
		topPane.setMinHeight(35);
		topPane.getChildren().addAll(image, header);
		
		return topPane;
	}
	
	protected VBox imagePane() {
		ImageView image = new ImageView();
		image.setFitHeight(250);
		image.setFitWidth(250);
		image.setPreserveRatio(true);
		
		Images.setImageInstance(image);
		Images.getInstance().setDefaultImage();
		Images.getInstance().getImage();
		
		StackPane imageContainer = new StackPane();
		imageContainer.setId("imageContainer");
		imageContainer.getChildren().addAll(image);
		
		VBox imageLayout = new VBox(10);
		imageLayout.setPadding(new Insets(30, 30, 30, 30));
		imageLayout.setAlignment(Pos.TOP_CENTER);
		imageLayout.setId("imagePane");
		imageLayout.getChildren().addAll(imageContainer);

		return imageLayout;
	}
	
	protected ColumnConstraints columnConstraints(double width) {
		ColumnConstraints column = new ColumnConstraints();
		column.setPercentWidth(width);
		
		return column;
	}
	
	protected Region separateHeight(double width) {
		Region separator = new Region();
		separator.setPrefHeight(width);
		
		return separator;
	}
	
	protected Label createLabel(String text) {
		Label label = new Label(text);
		label.setPrefHeight(10);
		label.setPrefWidth(150);
		label.setId("label");
		
		return label;
	}

	protected Button createMenuButton(String text, String image) {
		ImageView buttonImage = new ImageView(image);
		buttonImage.setFitHeight(30);
		buttonImage.setFitWidth(30);
		
		Button button = new Button(text);
		button.setPrefHeight(50);
		button.setPrefWidth(230);
		button.setPadding(new Insets(0, 0, 0, 35));
		button.setAlignment(Pos.CENTER_LEFT);
		button.setGraphic(buttonImage);
		button.setGraphicTextGap(30);
		
		return button;
	}
	
	protected Button menuButtonIndicator(Button button) {
		button.setId("menuButtonIndicator");
		
		return button;
	}

	protected Button createFormButton(String text) {
		Button button = new Button(text);
		button.setPrefHeight(10);
		button.setPrefWidth(120);
		
		return button;
	}
	
}
