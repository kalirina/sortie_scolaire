package controller;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import part_1.Configuration;

public class MainApp extends Application {
	
	public static Configuration config = new Configuration();
	private static Stage primaryStage;
	
	@Override
	public void start(Stage stage) throws Exception {
		primaryStage = stage;
		stage.setTitle("Sortie scolaire - Répartitition des chambres");
		switchScene("/resources/fxml/demarrage.fxml");
		stage.show();
	}
	
	public static void switchScene(String fxmlPath) throws IOException {
		Parent root = FXMLLoader.load(MainApp.class.getResource(fxmlPath));
        primaryStage.setScene(new Scene(root));
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
