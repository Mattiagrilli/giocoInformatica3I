package Gioco;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
public class Finestra extends Application {
	Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

	double screenWidth = screenBounds.getWidth();
	double screenHeight = screenBounds.getHeight();
	@Override
	public void start(Stage stage) {
		Pane root = new Pane();
		Scene scene = new Scene(root, screenWidth, screenHeight);
		stage.setScene(scene);
		stage.setTitle("GiocoInformatica");
		stage.show();

	}

	public static void main(String[] args) {
		launch();
	}
	}