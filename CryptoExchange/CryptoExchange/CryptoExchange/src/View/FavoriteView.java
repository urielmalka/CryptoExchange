package View;

import java.util.ArrayList;

import CryproCoin.Coin;
import CryproCoin.Market;
import javafx.scene.layout.Pane;

public class FavoriteView extends Pane{
	
	private ArrayList<CoinRectangle> coinRectangles;
	private int counter;

	public FavoriteView(int xPos , int yPos) {
		this.relocate(xPos, yPos);
		coinRectangles = new ArrayList<CoinRectangle>();
	}
	
	public CoinRectangle addFav(CoinRectangle coinRectangle) {
		
		CoinRectangle newCoinR = new CoinRectangle(0,0,coinRectangle.getCoin());
		coinRectangles.add(newCoinR);
		this.getChildren().clear();
		setCoin();
		this.getChildren().addAll(coinRectangles);
		return newCoinR;
	}
	
	public void removeFav(CoinRectangle coinRectangle) {
		coinRectangles.remove(coinRectangle);
		this.getChildren().clear();
		setCoin();
		this.getChildren().addAll(coinRectangles);
	}
	
	public void setCoin() {
		counter = 0;
		for(CoinRectangle coinR : coinRectangles) {
			coinR.setLocation(0,counter);
			coinR.getMenuButton().on();
			counter += 50;
		}
	}
	
	/*
	 * By Uriel Malka www.GitHub.com/urielmalka
	 */
}
