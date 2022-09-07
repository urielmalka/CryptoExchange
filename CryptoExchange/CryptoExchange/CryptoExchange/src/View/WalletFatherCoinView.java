package View;

import java.util.ArrayList;

import CryproCoin.MyCoin;
import CryproCoin.Trader;
import javafx.scene.layout.Pane;

public class WalletFatherCoinView extends Pane{
	
	private ArrayList<WalletCoinView> walletCoinView;
	
	public WalletFatherCoinView(int xPos , int yPos) {
		
		walletCoinView = new ArrayList<WalletCoinView>();
		
		this.relocate(xPos, yPos);
	}
	
	//Set walletCoinView on Pane
	public void setCoinView() {
			int counterRec=0;
			
			this.getChildren().removeAll(walletCoinView);
			walletCoinView.clear();
			
			for(MyCoin coin : Trader.getWallet().getHold()) {
				walletCoinView.add(new WalletCoinView(0,counterRec,coin));
				counterRec += 50;
			}
			this.getChildren().addAll(walletCoinView);
	}
	
	/*
	 * By Uriel Malka www.GitHub.com/urielmalka
	 */
}
