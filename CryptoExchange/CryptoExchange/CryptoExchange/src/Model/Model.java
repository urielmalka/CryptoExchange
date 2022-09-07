package Model;

import CryproCoin.Trader;

public class Model {
	
	private WalletModel walletModel;
	private BuyCoinModel buyCoinModel;
 
	public Model(){
		
		//SQL reading
		CoinSQL.getSQL().read();
		
		
		walletModel = new WalletModel(this);
		buyCoinModel = new BuyCoinModel(this);
		walletModel.addCoinsToMap(Trader.getWallet().getHold());
		

		
	}
	
	
	public WalletModel getWalletModel() {
		return walletModel;
	}
	
	public BuyCoinModel getBuyCoinModel() {
		return buyCoinModel;
	}

	/*
	 * By Uriel Malka www.GitHub.com/urielmalka
	 */
	
}
