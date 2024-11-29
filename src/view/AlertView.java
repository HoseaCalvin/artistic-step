package view;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertView {

	private static Alert popup;
	
	public static void getErrorMessage(String header, String context) {
		popup = new Alert(AlertType.ERROR);
		popup.setTitle("Error");
		popup.setHeaderText(header);
		popup.setContentText(context);
		popup.showAndWait();
	}
	
	public static void getInformationMessage(String header, String context) {
		popup = new Alert(AlertType.INFORMATION);
		popup.setTitle("Confirmation");
		popup.setHeaderText(header);
		popup.setContentText(context);
		popup.showAndWait();
	}

}
