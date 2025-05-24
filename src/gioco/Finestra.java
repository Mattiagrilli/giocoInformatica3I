package gioco;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Finestra extends Application {
	
	Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

	double screenWidth = screenBounds.getWidth();
	double screenHeight = screenBounds.getHeight();
	//MUSICA
	private MediaPlayer playerMusica;
	
	@Override
	public void start(Stage stage) {
		//MUSICA
		try {
			Media media = new Media(getClass().getResource("music.mp3").toExternalForm());
			playerMusica = new MediaPlayer(media);
			playerMusica.setCycleCount(MediaPlayer.INDEFINITE);
			playerMusica.play();

		} catch (Exception e) {
		}
		Livello1 livello=new Livello1("livello1.txt",stage, playerMusica);
		Scene scene = livello.getScene();
		stage.setScene(scene);
		stage.setTitle("GiocoInformatica");
		stage.show();
	}
	public static void main(String[] args) {
		launch();
	}
}