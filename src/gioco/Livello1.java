package gioco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Livello1{
	
	private static final int TILE_SIZE = 60;
	private Scene scene;
	boolean collisioni[][]=new boolean[33][17];
	int y=0;

	public Livello1(){
		GridPane principale = new GridPane();
		Pane gioco=new Pane();
		Image a = new Image(getClass().getResourceAsStream("a.png"));
		Image b = new Image(getClass().getResourceAsStream("b.png"));
		Image c = new Image(getClass().getResourceAsStream("c.png"));
		Image d = new Image(getClass().getResourceAsStream("d.png"));
		Image e = new Image(getClass().getResourceAsStream("e.png"));
		Image h = new Image(getClass().getResourceAsStream("h.png"));
		Image i = new Image(getClass().getResourceAsStream("i.png"));
		Image l = new Image(getClass().getResourceAsStream("l.png"));
		Image m = new Image(getClass().getResourceAsStream("m.png"));
		Image n = new Image(getClass().getResourceAsStream("n.png"));
		Image o = new Image(getClass().getResourceAsStream("o.png"));
		Image p = new Image(getClass().getResourceAsStream("p.png"));
		Image c1 = new Image(getClass().getResourceAsStream("1.png"));
		Image c2 = new Image(getClass().getResourceAsStream("2.png"));
		Image c3 = new Image(getClass().getResourceAsStream("3.png"));
		Image c4 = new Image(getClass().getResourceAsStream("4.png"));
		try (
				InputStream is = Livello1.class.getResourceAsStream("\\livelli\\livello1.txt");
		        InputStreamReader isr = new InputStreamReader(is);
		        BufferedReader br = new BufferedReader(isr);
				BufferedReader lettoreDiRighe = new BufferedReader(br);
				){
			String rigaLetta;
	
			while( (rigaLetta = lettoreDiRighe.readLine())!=null ) {
				for (int x = 0; x < rigaLetta.length(); x++) {
					//indica la posizione del carattere da sostituire
					char carattere = rigaLetta.charAt(x);
					ImageView tileView = new ImageView();
					
					
				
					switch(carattere) {
					case'a':
						tileView.setImage(a);
						collisioni[x][y]=true;
						break;
					case'b':
						tileView.setImage(b);
						collisioni[x][y]=true;
						break;
					case'c':
						tileView.setImage(c);
						collisioni[x][y]=true;
						break;
					case'd':
						tileView.setImage(d);
						collisioni[x][y]=true;
						break;
					case'e':
						tileView.setImage(e);
						collisioni[x][y]=true;
						break;
					case'h':
						tileView.setImage(h);
						collisioni[x][y]=true;
						break;
					case'i':
						tileView.setImage(i);
						collisioni[x][y]=true;
						break;
					case'l':
						tileView.setImage(l);
						collisioni[x][y]=true;
						break;
					case'm':
						tileView.setImage(m);
						collisioni[x][y]=true;
						break;
					case'n':
						tileView.setImage(n);
						collisioni[x][y]=true;
						break;
					case'o':
						tileView.setImage(o);
						collisioni[x][y]=true;
						break;
					case'p':
						tileView.setImage(p);
						collisioni[x][y]=true;
						break;
					/*case'1':
						tileView.setImage(c1);
						break;*/
					case'2':
						tileView.setImage(c2);
						collisioni[x][y]=false;
						break;
					case'3':
						tileView.setImage(c3);
						collisioni[x][y]=false;
						break;
					case'4':
						tileView.setImage(c4);
						collisioni[x][y]=false;
						break;
					default:
						tileView.setImage(c1);
						collisioni[x][y]=false;
					}
					tileView.setFitWidth(TILE_SIZE);
                    tileView.setFitHeight(TILE_SIZE);
                    principale.add(tileView, x, y);
				}
				y++;
			}
			Timeline timeline = new Timeline(new KeyFrame(
					Duration.seconds(0.02),
					x -> aggiornaTimer()));
			timeline.setCycleCount(-1);
			timeline.play();
			Player giocatore=new Player(1,750,"personaggio1.png");
			giocatore.setFitWidth(155);
			giocatore.setFitHeight(155);
            gioco.getChildren().addAll(principale,giocatore);
			scene = new Scene(gioco);

		} catch (IOException err) {
			err.printStackTrace();
		}
	}
	private void aggiornaTimer(){
		
	}
	public Scene getScene() {
		return scene;
	}
}