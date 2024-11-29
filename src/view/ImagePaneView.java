package view;

import controller.ImagePaneController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class ImagePaneView {

	public ImagePaneView() {
		// TODO Auto-generated constructor stub
	}
	
	public VBox imagePane() {
		ImageView image = new ImageView();		
		image.setFitHeight(200);
		image.setFitWidth(200);
		image.setPreserveRatio(true);
		
		VBox imageLayout = new VBox(10);
		imageLayout.setPadding(new Insets(30, 30, 30, 30));
		imageLayout.setAlignment(Pos.TOP_CENTER);
		imageLayout.setId("imagePane");
		imageLayout.getChildren().addAll(image);
		
		ImagePaneController.setImageInstance(image);
		ImagePaneController.getInstance().setDefaultImage();
		ImagePaneController.getInstance().getImage();
		
		return imageLayout;
	}

}
