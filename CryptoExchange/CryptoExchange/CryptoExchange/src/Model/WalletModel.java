package Model;

import java.util.ArrayList;
import java.util.HashMap;

import CryproCoin.MyCoin;
import CryproCoin.Trader;

public class WalletModel {
	
	private Model model;
	private HashMap<String,MyCoin> coins;
	
	/*
	 *  In this class we buying and selling Crypto coins to our wallet.
	 *  
	 */
	
	public WalletModel(Model model) {
		this.model = model;
		this.coins = new HashMap<String, MyCoin>();
		addMyCoinToMap(Trader.getWallet().getHold().get(0));
	}
	
	
	
	public double buyChange(double input , double value) {
		return input/value;
	}
	
	public double sellChange(double input , double value) {
		return input*value;
	}
	
	public void buy(MyCoin myCoin , double value) {
		
		addMyCoinToMap(myCoin);
		coins.get("USD").sell(value);
		myCoin.buy(buyChange(value,myCoin.getCoin().getValue()));
		
		CoinSQL.getSQL().insertUpdateBalance(myCoin.getCoin(), myCoin.getAmount());
		
		CoinSQL.getSQL().insertUpdateBalance(coins.get("USD").getCoin(),coins.get("USD").getDollarValue());

	}

	
	public void sell(MyCoin myCoin , double value) {
		addMyCoinToMap(myCoin);
		coins.get(myCoin.getCoin().getCoinName()).sell(value);
		coins.get("USD").buy(sellChange(value,myCoin.getCoin().getValue()));
		
		CoinSQL.getSQL().insertUpdateBalance(myCoin.getCoin(), myCoin.getAmount());
		
		CoinSQL.getSQL().insertUpdateBalance(coins.get("USD").getCoin(),coins.get("USD").getDollarValue());

	}

	public void addMyCoinToMap(MyCoin myCoin) {
		if(coins.get(myCoin.getCoin().getCoinName()) == null)
			coins.put(myCoin.getCoin().getCoinName(),myCoin);		
	}
	
	public void addCoinsToMap(ArrayList<MyCoin> myCoins) {
		for(MyCoin myCoin : myCoins) {
			addMyCoinToMap(myCoin);
		}
	}
	
	public double getCoinBalance(String coin) {
		if(coins.get(coin) == null)
			return 0;
		return coins.get(coin).getAmount();
	}
	
	public void deposite(double value) {
		coins.get("USD").buy(value);
		CoinSQL.getSQL().insertUpdateBalance(coins.get("USD").getCoin(),coins.get("USD").getDollarValue());
	}
	
	
	public String checkInput(String value) {
		
		boolean out;
		StringBuilder build = new StringBuilder(value);
		do{
			out = false;
			
			for(int i=0 ; i < build.length() ; i++) {			
				
				if(!(build.charAt(i) >= '0' && build.charAt(i) <= '9')) {
					build.deleteCharAt(i);
					out = true;
					break;
				}
			}		
		}while(out);
		
		return build.toString();
	}

	
	/*
	 * By Uriel Malka www.GitHub.com/urielmalka
	 */

}
