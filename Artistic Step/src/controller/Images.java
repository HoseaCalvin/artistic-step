package controller;

import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Images {
	
	static ImageView image;
	
	private static Images images;
	
	public static Images getInstance() {
		if(images == null) {
			images = new Images();
		}
		
		return images;
	}
	
	public static void setImageInstance(ImageView image) {
		// Pass the reference of the "image" from MenuUI.
		Images.image = image;
	}

	public void changeImage() {
		String[] imageContainer = {
				"file:///C:/Users/hosea/eclipse-workspace/Artistic%20Step/image/product/Shoes_1.jpg",
				"file:///C:/Users/hosea/eclipse-workspace/Artistic%20Step/image/product/Shoes_2.jpg",
				"file:///C:/Users/hosea/eclipse-workspace/Artistic%20Step/image/product/Shoes_3.jpg",
				"file:///C:/Users/hosea/eclipse-workspace/Artistic%20Step/image/product/Shoes_4.jpg",
				"file:///C:/Users/hosea/eclipse-workspace/Artistic%20Step/image/product/Shoes_5.jpg",
				"file:///C:/Users/hosea/eclipse-workspace/Artistic%20Step/image/product/Shoes_6.jpg",
				"file:///C:/Users/hosea/eclipse-workspace/Artistic%20Step/image/product/Shoes_7.jpg",
				"file:///C:/Users/hosea/eclipse-workspace/Artistic%20Step/image/product/Shoes_8.jpg",
				"file:///C:/Users/hosea/eclipse-workspace/Artistic%20Step/image/product/Shoes_9.jpg",
				"file:///C:/Users/hosea/eclipse-workspace/Artistic%20Step/image/product/Shoes_10.jpg"
		};
		
		Random rand = new Random();
		int length = rand.nextInt(imageContainer.length);
		
		// The image shown will be randomized.
		image.setImage(new Image(imageContainer[length]));
	}
	
	public void setDefaultImage() {
		image.setImage(new Image(
				"file:///C:/Users/hosea/eclipse-workspace/Artistic%20Step/image/product/Shoes_NULL.jpg"));
	}
	
	public ImageView getImage() {
		return image;
	}
}
