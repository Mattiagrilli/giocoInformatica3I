package gioco;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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
		
		String rigaLetta;
		try (
				FileReader flussoCaratteri = new FileReader("c:\\Users\\francescofondacci\\Desktop\\livello1.txt");
				BufferedReader lettoreDiRighe = new BufferedReader(flussoCaratteri);
				){
			
			lettoreDiRighe.readLine();
			while( (rigaLetta = lettoreDiRighe.readLine())!=null ) {
				switch(rigaLetta) {
				case "A":
					
				}
			}
		} catch (IOException m) {
			m.printStackTrace();
		}
	}
	

	public static void main(String[] args) {
		launch();
	}
}