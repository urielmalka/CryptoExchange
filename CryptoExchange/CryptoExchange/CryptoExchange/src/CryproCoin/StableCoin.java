package CryproCoin;

public class StableCoin implements Coinable{
	
	private final int DOLLAR = 1;
	private final String coinName = "USD";
	
	public StableCoin() {}
	
	public int getDollar() {
		return DOLLAR;
	}


	@Override
	public String getCoinName() {
		return coinName;
	}

	@Override
	public double getValue() {
		return DOLLAR;
	}

	/*
	 * By Uriel Malka www.GitHub.com/urielmalka
	 */
}
