package CryproCoin;

import java.util.ArrayList;

public class Trader {
	
	private static Trader trader = new Trader(); 
	
	private static Wallet wallet;
	private static ArrayList<Coin> favorite;
	
	private Trader() {
		wallet = new Wallet();
		favorite = new ArrayList<Coin>();
	}
	
	
	public static Trader getTrader() {
		return trader;
	}
	
	public static void addFavorite(Coin coin) {
		favorite.add(coin);
	}
	
	public static void removeFavorite(Coin coin) {
		favorite.remove(coin);
	}
	
	public static Wallet getWallet() {
		return wallet;
	}


	public static ArrayList<Coin> getFavorite() {
		return favorite;
	}
	
	
	
	/*
	 * By Uriel Malka www.GitHub.com/urielmalka
	 */
	
	
}
