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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Livello1{
	
	private static final int TILE_SIZE = 60;
	private Scene scene;
	boolean collisioni[][]=new boolean[33][20];
	int y=0;
	int x=0;
	Player giocatore=new Player(30,350,"e.png");
	//VARIABILI PER MOVIMENTO
	boolean inAria=false;
	double gravita=13;
	double velocitaX=10;
	double velocitaY=0;
	boolean destra=false;
	boolean sinistra=false;
	boolean salto=false;
	boolean scatto=false;
	int direzione=1;
	

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
				for ( x = 0; x < rigaLetta.length(); x++) {
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
			//TIMELINE PER MOVIMENTO
			Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.02),x -> aggiornaTimer()));
			/*Timeline timerScatto = new Timeline(new KeyFrame(Duration.seconds(0.02),x -> aggiornaScatto()));
			timerScatto.setCycleCount(1);*/
			timeline.setCycleCount(-1);
			timeline.play();
			
			//GRANDEZZA GIOCATORE
			giocatore.setFitWidth(60);
			giocatore.setFitHeight(60);
			
            gioco.getChildren().addAll(principale,giocatore);
			scene = new Scene(gioco);
			
			scene.setOnKeyPressed(tast -> tastoPremuto(tast));
			scene.setOnKeyReleased(tast -> tastoRilasciato(tast));

		} catch (IOException err) {
			err.printStackTrace();
		}
	}
	//MOVIMENTO	
	private void aggiornaTimer(){
		int xGiocatore = (int)(giocatore.getX() / TILE_SIZE);
		int yGiocatore = (int)(giocatore.getY() / TILE_SIZE);
    	int tileSottoGiocatore = (int)((giocatore.getY() + giocatore.getFitHeight()) / TILE_SIZE);
    	int tileDavantiGiocatore = (int)((giocatore.getX() + giocatore.getFitWidth()) / TILE_SIZE);
    	int tileDietroGiocatore = (int)(giocatore.getX() / TILE_SIZE);
    	velocitaY += gravita;
    	//GRAVITA'
		if(!collisioni[xGiocatore][tileSottoGiocatore]){
			giocatore.setY(giocatore.getY()+gravita);
		}
		//MOVIMENTO A DESTRA
		if(destra && !collisioni[tileDavantiGiocatore][yGiocatore]) {
			giocatore.setX(giocatore.getX()+velocitaX);
		}
		//MOVIMENTO A SINISTRA
		if(sinistra && !collisioni[tileDietroGiocatore][yGiocatore]) {
			giocatore.setX(giocatore.getX()-velocitaX);
		}
		//SALTO
		
		if(salto && !inAria) {
			velocitaY=-75;
			inAria=true;
			giocatore.setY(giocatore.getY() + velocitaY);
			giocatore.setX(giocatore.getX() + 15*direzione);
		}
		if(collisioni[xGiocatore][tileSottoGiocatore]) {
			inAria=false;
			velocitaY=0;
		}
		//SCATTO
		if(scatto&& inAria) {
			giocatore.setX(giocatore.getX()+20*direzione);
		}
		if(tileSottoGiocatore==19) {
			giocatore.setY(350);
			giocatore.setX(30);
		}
	}
	
	private void tastoPremuto(KeyEvent tasto){
		if(tasto.getText().equals("d")) {
			destra=true;
			direzione=1;
		}
		if(tasto.getText().equals("a")) {
			sinistra=true;			
			direzione=-1;
		}
		if(tasto.getCode()==KeyCode.SHIFT) {
			scatto=true;			 
		}
		if(tasto.getCode()==KeyCode.SPACE) {
			salto=true;	
			
		}
	}
	private void tastoRilasciato(KeyEvent tasto){
		if(tasto.getText().equals("d")) {
			destra=false;	
			direzione=0;
		}
		if(tasto.getText().equals("a")) {
			sinistra=false;			
			direzione=0;
		}
		if(tasto.getCode()==KeyCode.SHIFT) {
			scatto=false;			 
		}
		if(tasto.getCode()==KeyCode.SPACE) {
			salto=false;
		}
	}
	//PRENDE LA SCENA PER CARICARLA IN "FINESTRA"
	public Scene getScene() {
		return scene;
	}
}