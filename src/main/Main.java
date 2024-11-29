package main;
	
import controller.HomeController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	
	public static Stage mainWindow;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		mainWindow = primaryStage;
		primaryStage.setResizable(true);
		primaryStage.setMaximized(true);
		primaryStage.setMinHeight(800);
		primaryStage.setMinWidth(1400);
		primaryStage.setHeight(800);
		primaryStage.setWidth(1400);
		primaryStage.setTitle("Artistic Step");
		primaryStage.setScene(HomeController.getInstance().getScene());
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
