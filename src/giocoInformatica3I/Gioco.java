package giocoInformatica3I;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Gioco extends Application {
	
	public static final int LARGHEZZA = 800;
    public static final int ALTEZZA = 600;
    private Pane root;
    private Scene scene;
    
    @Override
    public void start(Stage stage) {
        root = new Pane();
        scene = new Scene(root, LARGHEZZA, ALTEZZA);

        stage.setTitle("Gioco JavaFX - Finestra");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
