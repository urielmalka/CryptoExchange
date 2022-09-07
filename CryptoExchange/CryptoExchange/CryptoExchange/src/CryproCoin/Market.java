package CryproCoin;

import java.util.ArrayList;

public class Market implements Runnable{
	
	private static Market market = new Market();
	private static ArrayList<Coin> coins;
	private Thread coinThread;
	
	private Market() {
		coins = new ArrayList<Coin>();
		coinThread = new Thread(this);
		coinThread.start();
	}
	
	public static Market getMarket() {
		return market;
	}
	

	//Add coin to market
	public void addCoin(Coin coin) {
		coins.add(coin);
	}
	
	public void setCoins(ArrayList<String> newCoins) {
		for (String coinName : newCoins) {
			addCoin(new Coin(coinName));		        
		}
	}
	
	public ArrayList<Coin> getCoins(){
		return coins;
	}
	
	
	
	//Observer
	private void updateAllCoins() {
		for (Coin coin : coins) {
			coin.update();		        
		}
	}
		
	@Override
	public void run() {
		while(true) {
			updateAllCoins();
			try {Thread.sleep(500);
			} catch (InterruptedException e) {e.printStackTrace();}
		}
		
	}

	/*
	 * By Uriel Malka www.GitHub.com/urielmalka
	 */
	
}
