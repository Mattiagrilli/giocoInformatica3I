package gioco;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Finestra extends Application {
	
	Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

	double screenWidth = screenBounds.getWidth();
	double screenHeight = screenBounds.getHeight();
	
	@Override
	public void start(Stage stage) {
		Livello1 livello=new Livello1();
		Scene scene = livello.getScene();
		stage.setScene(scene);
		stage.setTitle("GiocoInformatica");
		stage.show();
		
	}
	

	public static void main(String[] args) {
		launch();
	}
}
