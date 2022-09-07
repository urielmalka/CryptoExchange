package CryproCoin;

public class Coin implements Coinable{
	
	private String coinName;
	private double value,lastValue;
	private GetCoin getCoin;

	
	public Coin(String coinName){
		this.coinName = coinName;
		getCoin = new GetCoin(this);
		value = getCoin.firstValue();

	}
	
	
	public void update() {
		lastValue = value;
		this.value = getCoin.value();
	}
	
	public double getLast() {
		return lastValue;
	}
	
	@Override
	public double getValue() {
		return value;
	}

	@Override
	public String getCoinName() {
		return coinName;
	}

	
	public double calcByDollarValue(double amount) {
		return value*amount;
	}


	/*
	 * By Uriel Malka www.GitHub.com/urielmalka
	 */
	

}
