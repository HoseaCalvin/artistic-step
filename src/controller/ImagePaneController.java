package controller;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.ShoesDatabase;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import template.ImageTemplate;
import view.ImagePaneView;

public class ImagePaneController {
	
	static ImageView image;
	
	private static ImagePaneController images;
	
	public static ImagePaneController getInstance() {
		if(images == null) {
			images = new ImagePaneController();
		}
		
		return images;
	}
	
	public VBox getPane() {
		ImagePaneView imageInterface = new ImagePaneView();
		
		return imageInterface.imagePane();
	}
	
	public static void setImageInstance(ImageView image) {
		ImagePaneController.image = image;
	}

	public void changeImage(String shoesId) {
		ShoesDatabase shoesDatabase = new ShoesDatabase();
		
		ResultSet shoesImage = shoesDatabase.selectImage(shoesId);

		try {
			if(shoesImage.next()) {
				Blob blob = shoesImage.getBlob("ShoesPicture");
				InputStream inputImageStream = blob.getBinaryStream();
				
				image.setImage(new Image(inputImageStream));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void setDefaultImage() {
		image.setImage(new Image(ImageTemplate.nullPicture));
	}
	
	public ImageView getImage() {
		return image;
	}
}
