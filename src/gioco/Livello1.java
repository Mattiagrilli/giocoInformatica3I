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
import javafx.scene.media.AudioClip;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Livello1{
	
	private static final int grandezzaTile = 47;
	private Scene scene;
	boolean collisioni[][]=new boolean[33][20];
	int y=0;
	int x=0;
	
	GridPane principale = new GridPane();
	Pane gioco=new Pane();
	
	//VARIABILI ANIMAZIONI GIOCATORE
	private Image[] Idle;
	private Image[] corsa;
	private Image[] animSalto;
	int frameCorrente = 0;
	int frameSalto = 0;
	
	//GIOCATORE
	Player giocatore;
	
	//VARIABILI PER MOVIMENTO
	double velocitaX=7;//VELOCITà MOVIMENTO LATERALE
	double velocitaY=0;//VELOCITà MOVIMENTO VERITICALE
	boolean destra=false;
	boolean sinistra=false;
	boolean scatto=false;
	int direzione=1;
	
	//VARIABILI PER SALTO
	boolean inAria=false;
	boolean salto=false;

	//VARIABILI CAMBIO LIVELLO
	private Stage stage;
    private String prossimoLivello;
    String livelloCorrente="";
    
    //AUDIO
    private MediaPlayer playerMusica;
    
    private AudioClip[] suonoPassi; 
    private int suonoCorrente = 0; 
    private double tempoUltimoPasso = 0; // Per controllare il ritmo
    private static final double ritardoPassi = 300; // Ritardo tra un passo e l'altro
    private AudioClip suonoSalto=new AudioClip(getClass().getResource("salto.wav").toExternalForm()); 
   
    
  //TIMELINE PER MOVIMENTO
	Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.02),x -> aggiornaTimer()));
	
	
	public Livello1(String fileLivello, Stage stage, MediaPlayer musicaDiSottofondo){	
		this.stage=stage;
		
		this.playerMusica = musicaDiSottofondo;
		suonoSalto.setVolume(0.3);
		 
		timeline.setCycleCount(-1);
		timeline.play();

		
		try {
	        suonoPassi = new AudioClip[]{
	            new AudioClip(getClass().getResource("passo1.wav").toExternalForm()),
	            new AudioClip(getClass().getResource("passo2.wav").toExternalForm()) 
	        };
	        for (AudioClip clip : suonoPassi) {
	            clip.setVolume(0.2); 
	        }
	    } catch (Exception e) {
	        System.err.println("Errore caricamento suoni passi: " + e.getMessage());
	    }
		
		if (fileLivello.equals("livello1.txt")) {
			prossimoLivello = "livello2.txt";
			livelloCorrente=fileLivello;
		} 
		if (fileLivello.equals("livello2.txt")) {
			prossimoLivello = "livello4";
			livelloCorrente=fileLivello;
		}
		if (fileLivello.equals("livello4")) {
			prossimoLivello = "livello5.txt";
			livelloCorrente=fileLivello;
		}
		if (fileLivello.equals("livello5.txt")) {
			prossimoLivello = "livello3.txt";
			livelloCorrente=fileLivello;
		}
		if (fileLivello.equals("livello3.txt")) {
			prossimoLivello = "livello6.txt";
			livelloCorrente=fileLivello;
		}
		if (fileLivello.equals("livello6.txt")) {
			prossimoLivello = "livello7.txt";
			livelloCorrente=fileLivello;
		}
		if (fileLivello.equals("livello7.txt")) {
			prossimoLivello = "livello8.txt";
			livelloCorrente=fileLivello;
		}
		if (fileLivello.equals("livello8.txt")) {
			prossimoLivello = "livello9.txt";
			livelloCorrente=fileLivello;
		}
		if (fileLivello.equals("livello9.txt")) {
			prossimoLivello = "livello10.txt";
			livelloCorrente=fileLivello;
		}
		if (fileLivello.equals("livello10.txt")) {
			prossimoLivello = "livello11.txt";
			livelloCorrente=fileLivello;
		}
		if (fileLivello.equals("livello11.txt")) {
			prossimoLivello = "livello12.txt";
			livelloCorrente=fileLivello;
		}
		if (fileLivello.equals("livello12.txt")) {
			prossimoLivello = "livello13.txt";
			livelloCorrente=fileLivello;
		}
		if (fileLivello.equals("livello13.txt")) {
			livelloCorrente=fileLivello;
		}
		
		//POSIZIONE INIZIALE GIOCATORE A SECONDA DEL LIVELLO
		if(livelloCorrente.equals("livello3.txt")) {
			giocatore=new Player(31*grandezzaTile,200,"idle2.png");
		}else {
			giocatore=new Player(30,200,"idle2.png");
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
		//AVVIO ANIMAZIONE
		animazioneIdle.setCycleCount(Timeline.INDEFINITE);
		animazioneIdle.play();
		
		Timeline animazioneCorsa = new Timeline(new KeyFrame(Duration.millis(100), e -> {
			if(destra&&!inAria) {
				frameCorrente = (frameCorrente + 1) % corsa.length;
				giocatore.setImage(corsa[frameCorrente]);
				 giocatore.setScaleX(1);
			}
			if (sinistra && !inAria) {
		        frameCorrente = (frameCorrente + 1) % corsa.length;
		        giocatore.setImage(corsa[frameCorrente]);
		        giocatore.setScaleX(-1); //RIBALTA L'IMMAGINE
		    }
			
		}));
		animazioneCorsa.setCycleCount(Timeline.INDEFINITE);
		animazioneCorsa.play();
		
		Image d = new Image(getClass().getResourceAsStream("d.png"));
		Image e = new Image(getClass().getResourceAsStream("e.png"));
		Image h = new Image(getClass().getResourceAsStream("h.png"));
		Image i = new Image(getClass().getResourceAsStream("i.png"));
		Image l = new Image(getClass().getResourceAsStream("l.png"));
		Image m = new Image(getClass().getResourceAsStream("m.png"));
		Image n = new Image(getClass().getResourceAsStream("n.png"));
		Image p = new Image(getClass().getResourceAsStream("portale00.png"));
		Image k = new Image(getClass().getResourceAsStream("portale01.png"));
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
					case'p':
						tileView.setImage(p);
						collisioni[x][y]=false;
						break;
					case'k':
						tileView.setImage(k);
						collisioni[x][y]=false;
						break;
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
					case'5':
						tileView.setImage(c1);
						collisioni[x][y]=true;
						break;
					default:
						tileView.setImage(c1);
						collisioni[x][y]=false;
					}
					tileView.setFitWidth(grandezzaTile);
					tileView.setFitHeight(grandezzaTile);
                    principale.add(tileView, x, y);
				}
				y++;
			}
			

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

		int xGiocatore = (int)(posX / grandezzaTile);
		int yGiocatore = (int)(posY / grandezzaTile);
		int tileSottoGiocatore = (int)((posY + giocatore.getFitHeight()) / grandezzaTile);
		int tileDavantiGiocatore = (int)((posX + (giocatore.getFitWidth()-23))/ grandezzaTile);
		int tileDietroGiocatore = (int)((posX+23) / grandezzaTile);

		/**GRAVITA
		 * tileSottoGiocatore < collisioni[0].length --> EVITA EVENTUALI ERRORI NEL VETTORE
		 * !collisioni[xGiocatore][tileSottoGiocatore]--> CONTROLLA SE LA "TILE" SOTTO IL GIOCATORE è FALSE
		 */
		if (tileSottoGiocatore < collisioni[0].length && !collisioni[xGiocatore][tileSottoGiocatore]&& !collisioni[xGiocatore+1][tileSottoGiocatore]) {
			velocitaY += 1;
			giocatore.setY(posY + velocitaY);
			inAria = true;
		} else {
			velocitaY = 0;
			inAria = false;
		}

		// Salto
		if (salto && !inAria) {
			velocitaY = -15;
			inAria = true;
			giocatore.setY(posY + velocitaY);
			suonoSalto.play();
		}

		// MOVIMENTO DESTRA
		if (destra && (xGiocatore<32&&!collisioni[tileDavantiGiocatore][yGiocatore]&&  !collisioni[tileDavantiGiocatore][yGiocatore+1])) {
			giocatore.setX(posX + velocitaX);
			
		}
		//MOVIMENTO SINISTRA
		if (sinistra&&!collisioni[tileDietroGiocatore][yGiocatore]&& !collisioni[tileDietroGiocatore][yGiocatore+1]&&xGiocatore>0) {
			giocatore.setX(posX - velocitaX);
		}
		// AUDIO PASSI
		if ((destra || sinistra) && !inAria) {
			double tempoCorrente = System.currentTimeMillis();
			// Controlla se è passato abbastanza tempo dall'ultimo passo
			if (tempoCorrente - tempoUltimoPasso > ritardoPassi) {
				if (suonoPassi != null && suonoPassi.length > 0) {
					suonoPassi[suonoCorrente].play();
					// Passa al suono successivo per la variazione
					suonoCorrente = (suonoCorrente + 1) % suonoPassi.length;
					tempoUltimoPasso = tempoCorrente;
				}
			}
		} else {
			tempoUltimoPasso = 0;
		}
		// RESET
		if(livelloCorrente.equals("livello3.txt")) {
			if (tileSottoGiocatore >= collisioni[0].length) {
				giocatore.setX(31*grandezzaTile);
				giocatore.setY(200);
				velocitaY=0;
			}
			
		}else{
			if (tileSottoGiocatore >= collisioni[0].length) {
				giocatore.setX(20);
				giocatore.setY(200);
				velocitaY = 0;
			}
			
		}
		//CONTROLLA IL LIVELLO CORRENTE E IMPOSTA IL BLOCCO D'USCITA
		if(livelloCorrente.equals("livello1.txt")) {
			if (xGiocatore ==31&& yGiocatore==13) {
				this.fermaSuoni();
				Livello1 nuovoLivello = new Livello1(prossimoLivello, stage, playerMusica);
		        stage.setScene(nuovoLivello.getScene());

			}
		}
		if(livelloCorrente.equals("livello2.txt")) {
			if (xGiocatore ==31&& yGiocatore==11) {
				this.fermaSuoni();
				Livello1 nuovoLivello = new Livello1(prossimoLivello, stage, playerMusica);
		        stage.setScene(nuovoLivello.getScene());
			}
		}
		if(livelloCorrente.equals("livello4")) {
			if (xGiocatore ==31&& yGiocatore==12) {
				this.fermaSuoni();
				Livello1 nuovoLivello = new Livello1(prossimoLivello, stage, playerMusica);
		        stage.setScene(nuovoLivello.getScene());
			}
		}
		if(livelloCorrente.equals("livello5.txt")) {
			if (xGiocatore ==3&& yGiocatore==0) {
				this.fermaSuoni();
				Livello1 nuovoLivello = new Livello1(prossimoLivello, stage, playerMusica);
		        stage.setScene(nuovoLivello.getScene());
			}
		}
		if(livelloCorrente.equals("livello3.txt")) {
			if (xGiocatore ==19&& yGiocatore==1) {
				this.fermaSuoni();
				Livello1 nuovoLivello = new Livello1(prossimoLivello, stage, playerMusica);
		        stage.setScene(nuovoLivello.getScene());
			}
		}
		if(livelloCorrente.equals("livello6.txt")) {
			if (xGiocatore ==31&& yGiocatore==4) {
				this.fermaSuoni();
				Livello1 nuovoLivello = new Livello1(prossimoLivello, stage, playerMusica);
		        stage.setScene(nuovoLivello.getScene());
			}
		}
		if(livelloCorrente.equals("livello7.txt")) {
			if (xGiocatore ==31&& yGiocatore==4) {
				this.fermaSuoni();
				Livello1 nuovoLivello = new Livello1(prossimoLivello, stage, playerMusica);
		        stage.setScene(nuovoLivello.getScene());
			}
		}
		if(livelloCorrente.equals("livello8.txt")) {
			if (xGiocatore ==32&& yGiocatore==8) {
				this.fermaSuoni();
				Livello1 nuovoLivello = new Livello1(prossimoLivello, stage, playerMusica);
		        stage.setScene(nuovoLivello.getScene());
			}
		}
		if(livelloCorrente.equals("livello9.txt")) {
			if (xGiocatore ==30&& yGiocatore==7) {
				this.fermaSuoni();
				Livello1 nuovoLivello = new Livello1(prossimoLivello, stage, playerMusica);
		        stage.setScene(nuovoLivello.getScene());
			}
		}
		if(livelloCorrente.equals("livello10.txt")) {
			if (xGiocatore ==31&& yGiocatore==1) {
				this.fermaSuoni();
				Livello1 nuovoLivello = new Livello1(prossimoLivello, stage, playerMusica);
		        stage.setScene(nuovoLivello.getScene());
			}
		}
		if(livelloCorrente.equals("livello11.txt")) {
			if (xGiocatore ==32&& yGiocatore==7) {
				this.fermaSuoni();
				Livello1 nuovoLivello = new Livello1(prossimoLivello, stage, playerMusica);
		        stage.setScene(nuovoLivello.getScene());
			}
		}
		if(livelloCorrente.equals("livello12.txt")) {
			if (xGiocatore ==32&& yGiocatore==2) {
				this.fermaSuoni();
				Livello1 nuovoLivello = new Livello1(prossimoLivello, stage, playerMusica);
		        stage.setScene(nuovoLivello.getScene());
			}
		}
		if(livelloCorrente.equals("livello13.txt")) {
			if (xGiocatore ==20&& yGiocatore==3) {
				this.fermaSuoni();
				SchermataFinale fine = new SchermataFinale(stage, playerMusica);
		        stage.setScene(fine.getScene());
			}
		}
		//System.out.println(xGiocatore);
		//System.out.println(yGiocatore);
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
		if(tasto.getCode()==KeyCode.SPACE) {
			salto=false;
		}
	}
	//SERVE PER FERMARE I SUONI QUANDI SI CAMBIA LIVELLO EVITANDO CHE SI RIPETANO
	public void fermaSuoni() {
		if (suonoPassi != null) {
			for (AudioClip clip : suonoPassi) {
				if (clip != null) {
					if (clip.isPlaying()) {
						clip.stop();
					}
				}
			}
		}
		 if (timeline != null) {
		        timeline.stop();
		        timeline = null;
		    }
	}
	//PRENDE LA SCENA PER CARICARLA IN "FINESTRA"
	public Scene getScene() {
		return scene;
	}
}