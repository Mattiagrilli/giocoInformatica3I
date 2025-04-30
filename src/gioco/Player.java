package gioco;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Player extends ImageView {
	
	public Player(int x, int y,String percorso) {
		super();
		this.setX(x);
		this.setY(y);
		Image img = new Image(getClass().getResourceAsStream(percorso));
		this.setImage(img);
	}
	

}
