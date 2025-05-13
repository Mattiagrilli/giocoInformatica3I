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
import javafx.stage.Stage;
import javafx.util.Duration;

public class Livello1{
	
	private static final int TILE_SIZE = 47;
	private Scene scene;
	boolean collisioni[][]=new boolean[33][20];
	int y=0;
	int x=0;
	
	//VARIABILI ANIMAZIONI GIOCATORE
	private Image[] Idle;
	private Image[] corsa;
	private Image[] animSalto;
	Player giocatore=new Player(30,250,"idle2.png");
	
	//VARIABILI PER MOVIMENTO
	double velocitaX=10;//VELOCITà MOVIMENTO LATERALE
	double velocitaY=0;//VELOCITà MOVIMENTO VERITICALE
	boolean destra=false;
	boolean sinistra=false;
	boolean scatto=false;
	int direzione=1;
	
	//VARIABILI PER SALTO
	boolean inAria=false;
	boolean salto=false;
	
	//VARIABILI PER SCATTO
	/*boolean scattoAttivato=false;
	private boolean gravitaAbilitata = true;
	private double tempoScatto = 0;
	private static final double DURATA_SCATTO = 0.5;*/
	
	
	int frameCorrente = 0;
	int frameSalto = 0;
	
	private Stage stage;
	int livelloCorrente=1;
    private String prossimoLivello;

	public Livello1(String fileLivello, Stage stage){
		this.stage=stage;
		if (fileLivello.equals("livello1.txt")) {
			prossimoLivello = "livello2.txt";
		} 
		if (fileLivello.equals("livello2.txt")) {
			prossimoLivello = "livello3.txt";
		}

		Idle =new Image[] {
				new Image(getClass().getResourceAsStream("idle1.png")),
				new Image(getClass().getResourceAsStream("idle2.png")),
				new Image(getClass().getResourceAsStream("idle3.png")),
				new Image(getClass().getResourceAsStream("idle4.png")),
				new Image(getClass().getResourceAsStream("idle5.png")),
				new Image(getClass().getResourceAsStream("idle6.png")),
		};
		
		corsa =new Image[] {
				new Image(getClass().getResourceAsStream("corsa1.png")),
				new Image(getClass().getResourceAsStream("corsa2.png")),
				new Image(getClass().getResourceAsStream("corsa3.png")),
				new Image(getClass().getResourceAsStream("corsa4.png")),
				new Image(getClass().getResourceAsStream("corsa5.png")),
				new Image(getClass().getResourceAsStream("corsa6.png")),
				new Image(getClass().getResourceAsStream("corsa7.png")),
				new Image(getClass().getResourceAsStream("corsa8.png")),
		};
		animSalto =new Image[] {
				//new Image(getClass().getResourceAsStream("salto1.png")),
				new Image(getClass().getResourceAsStream("salto2.png")),
				new Image(getClass().getResourceAsStream("salto3.png")),
				new Image(getClass().getResourceAsStream("salto4.png")),
				new Image(getClass().getResourceAsStream("salto5.png")),
				new Image(getClass().getResourceAsStream("salto6.png")),
				new Image(getClass().getResourceAsStream("salto7.png")),
				new Image(getClass().getResourceAsStream("salto8.png")),
		};
		
		Timeline animazioneIdle = new Timeline(new KeyFrame(Duration.millis(200), e -> {
			if(!destra&&!sinistra&&!inAria) {
				frameCorrente = (frameCorrente + 1) % Idle.length;
				giocatore.setImage(Idle[frameCorrente]);
			}
			if(inAria) {
				frameSalto = (frameSalto + 1) % animSalto.length;
				giocatore.setImage(animSalto[frameSalto]);
			}else {
				frameSalto=0;
			}
			
		}));
		animazioneIdle.setCycleCount(Timeline.INDEFINITE);
		animazioneIdle.play();
		
		Timeline animazioneCorsa = new Timeline(new KeyFrame(Duration.millis(100), e -> {
			if((destra||sinistra)&&!inAria) {
				frameCorrente = (frameCorrente + 1) % corsa.length;
				giocatore.setImage(corsa[frameCorrente]);
			}
			
		}));
		animazioneCorsa.setCycleCount(Timeline.INDEFINITE);
		animazioneCorsa.play();
		
		GridPane principale = new GridPane();
		Pane gioco=new Pane();
		/*Image a = new Image(getClass().getResourceAsStream("a.png"));
		Image b = new Image(getClass().getResourceAsStream("b.png"));
		Image c = new Image(getClass().getResourceAsStream("c.png"));*/
		Image d = new Image(getClass().getResourceAsStream("d.png"));
		Image e = new Image(getClass().getResourceAsStream("e.png"));
		Image h = new Image(getClass().getResourceAsStream("h.png"));
		//Image i = new Image(getClass().getResourceAsStream("i.png"));
		Image l = new Image(getClass().getResourceAsStream("l.png"));
		Image m = new Image(getClass().getResourceAsStream("m.png"));
		Image n = new Image(getClass().getResourceAsStream("n.png"));
		Image o = new Image(getClass().getResourceAsStream("o.png"));
		//Image p = new Image(getClass().getResourceAsStream("p.png"));
		Image c1 = new Image(getClass().getResourceAsStream("1.png"));
		Image c2 = new Image(getClass().getResourceAsStream("2.png"));
		Image c3 = new Image(getClass().getResourceAsStream("3.png"));
		Image c4 = new Image(getClass().getResourceAsStream("4.png"));
		try (
				InputStream is = Livello1.class.getResourceAsStream("\\livelli\\"+fileLivello);
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
					/*case'a':
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
						break;*/
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
					/*case'i':
						tileView.setImage(i);
						collisioni[x][y]=true;
						break;*/
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
					/*case'p':
						tileView.setImage(p);
						collisioni[x][y]=true;
						break;
					case'1':
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
			timeline.setCycleCount(-1);
			timeline.play();
			
			//GRANDEZZA GIOCATORE
			giocatore.setFitWidth(70);
			giocatore.setFitHeight(70);
			
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
		double posX = giocatore.getX();
		double posY = giocatore.getY();

		int xGiocatore = (int)(posX / TILE_SIZE);
		int yGiocatore = (int)(posY / TILE_SIZE);
		int tileSottoGiocatore = (int)((posY + giocatore.getFitHeight()) / TILE_SIZE);
		int tileDavantiGiocatore = (int)((posX + (giocatore.getFitWidth()-23))/ TILE_SIZE);
		int tileDietroGiocatore = (int)((posX+23) / TILE_SIZE);

		/**GRAVITA
		 * tileSottoGiocatore < collisioni[0].length --> EVITA EVENTUALI ERRORI NEL VETTORE
		 * !collisioni[xGiocatore][tileSottoGiocatore]--> CONTROLLA SE LA "TILE" SOTTO IL GIOCATORE è FALSE
		 */
		if (tileSottoGiocatore < collisioni[0].length && !collisioni[xGiocatore][tileSottoGiocatore]) {
			velocitaY += 1;
			giocatore.setY(posY + velocitaY);
			inAria = true;
		} else {
			velocitaY = 0;
			inAria = false;
			//scattoAttivato=false;
		}

		// Salto
		if (salto && !inAria) {
			velocitaY = -15;
			inAria = true;
			giocatore.setY(posY + velocitaY);
		}

		// MOVIMENTO DESTRA
		if (destra && !collisioni[tileDavantiGiocatore][yGiocatore]&& !collisioni[tileDavantiGiocatore][yGiocatore+1]) {
			giocatore.setX(posX + velocitaX);
		}
		//MOVIMENTO SINISTRA
		if (sinistra&&!collisioni[tileDietroGiocatore][yGiocatore]&& !collisioni[tileDietroGiocatore][yGiocatore+1]&&xGiocatore>0) {
			giocatore.setX(posX - velocitaX);
		}
		//SCATTO
		/*if (!scattoAttivato&&scatto && inAria&& salto) {
	        gravitaAbilitata = false;
	        tempoScatto = DURATA_SCATTO;
	        giocatore.setX(giocatore.getX() + 60 * direzione);
	        scattoAttivato=true;
	    }
	    if (tempoScatto > 0) {
	        tempoScatto -= 0.02;
	        if (tempoScatto <= 0) {
	            gravitaAbilitata = true;
	        }
	    }*/
		
		// RESET
		if (tileSottoGiocatore >= collisioni[0].length) {
			giocatore.setX(30);
			giocatore.setY(350);
			velocitaY = 0;
		}
		int z=30;
		if (xGiocatore >30) {
			//System.out.println("aaaaaaaaaaaa");
			Livello1 nuovoLivello = new Livello1(prossimoLivello, stage);
	        stage.setScene(nuovoLivello.getScene());
	        z+=30;
	        System.out.println(z);
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
		if(tasto.getText().equals("w")) {
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
		if(tasto.getText().equals("w")) {
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