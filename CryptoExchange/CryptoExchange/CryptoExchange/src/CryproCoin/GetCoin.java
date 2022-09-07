package CryproCoin;


import Model.CoinSQL;

public class GetCoin {


	private double value;
	private Coin coin;
	public GetCoin(Coin coin) {
		this.coin = coin;
	}
	
	public double value() {
		value = CoinSQL.getSQL().readValueCoins(this.coin);
		value = Double.parseDouble(String.format("%.6f", value));
		return value;	
	}
	
	public double firstValue() {
		value = CoinSQL.getSQL().readValueCoins(this.coin);
		value = Double.parseDouble(String.format("%.6f", value));
		return value;	
	}
	
	/*
	 * By Uriel Malka www.GitHub.com/urielmalka
	 */
	
}
