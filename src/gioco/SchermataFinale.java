package gioco;

import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class SchermataFinale {
	
	private Scene menuScene;
	private Stage primaryStage;
	private MediaPlayer playerMusica;

	public SchermataFinale(Stage primaryStage, MediaPlayer playerMusica) {
		super();
		this.primaryStage = primaryStage;
		this.playerMusica=playerMusica;
		//DIMENSIONI SCHERMO
		Screen screen = Screen.getPrimary();
		Rectangle2D bounds = screen.getVisualBounds();
		double larghezzaSchermo = bounds.getWidth();
		double altezzaSchermo = bounds.getHeight();

		ImageView sfondoMenu = null;
		try {
			Image backgroundImage = new Image(getClass().getResourceAsStream("fine.png"));
			sfondoMenu = new ImageView(backgroundImage);
			sfondoMenu.setFitWidth(larghezzaSchermo);
			sfondoMenu.setFitHeight(altezzaSchermo);
			sfondoMenu.setPreserveRatio(false);
		} catch (Exception e) {
			System.err.println("Errore nel caricamento dell'immagine di sfondo del menu: " + e.getMessage());
		}

		Button bGioca = new Button("Riavvia");
		Button bEsci = new Button("Esci");

		String stileBottone = "-fx-font-size: 30px; -fx-padding: 10px 30px; -fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold;";
		bGioca.setStyle(stileBottone);
		bEsci.setStyle(stileBottone);

		bGioca.setOnAction(e -> avviaGioco());
		bEsci.setOnAction(e -> primaryStage.close());

		VBox bottoni = new VBox(20);//ORGANIZZA BOTTONI IN UNA COLONNA VERTICALE
		bottoni.setAlignment(Pos.CENTER);
		bottoni.getChildren().addAll(bGioca, bEsci);

		StackPane root = new StackPane();
		if (sfondoMenu != null) {
			root.getChildren().add(sfondoMenu);
		}
		root.getChildren().add(bottoni);
		menuScene = new Scene(root, larghezzaSchermo, altezzaSchermo);
	}

	private void avviaGioco() {
		Livello1 primoLivello = new Livello1("livello1.txt", primaryStage, playerMusica);
		primaryStage.setScene(primoLivello.getScene());
	}

	public Scene getScene() {
		return menuScene;
	}


}
