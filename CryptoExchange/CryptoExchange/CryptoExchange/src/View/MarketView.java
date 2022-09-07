package View;

import java.util.ArrayList;

import CryproCoin.Coin;
import CryproCoin.Market;
import javafx.scene.layout.Pane;

public class MarketView extends Pane{
	
	private ArrayList<CoinRectangle> coinRec;
	private int counterRec;
	
	public MarketView(int xPos , int yPos) {
		
		coinRec = new ArrayList<CoinRectangle>();
		counterRec = 0;
		setCoin();
		this.relocate(xPos, yPos);
	}
	
	
	//For open running program
	public void setCoin() {
		for(Coin coin : Market.getMarket().getCoins()) {
			coinRec.add(new CoinRectangle(0, counterRec, coin));
			counterRec += 50;
		}
		this.getChildren().addAll(coinRec);
	}
	
	public ArrayList<CoinRectangle> getCoinRec() {
		return coinRec;
	}
	
	/*
	 * By Uriel Malka www.GitHub.com/urielmalka
	 */
}
