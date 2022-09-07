package CryproCoin;

public class MyCoin {
	
	private Coinable coin;
	private double amount;
	
	
	public MyCoin(Coinable coin, double amount) {
		this.coin = coin;
		this.amount = amount;
	}
	
	
	public void buy(double buy) {
		amount +=buy;
	}
	
	public void sell(double sell) {
		amount-=sell;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public double getDollarValue() {
		return coin.getValue() * amount;
	}
	
	public Coinable getCoin() {
		return coin;
	}
	
	public void setAmount(double amount) {
		this.amount += amount;
	}
	
	/*
	 * By Uriel Malka www.GitHub.com/urielmalka
	 */
}
