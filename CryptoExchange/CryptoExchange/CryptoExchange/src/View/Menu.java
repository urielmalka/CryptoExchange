package View;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;


public class Menu extends Pane{
	
	private MenuButton wallet,market,analytics,favorite;
	private ArrayList<MenuButton> buttons;
	
	public Menu(int xPos , int yPos,View view) {
		this.relocate(xPos, yPos);
		buttons = new ArrayList<MenuButton>();
		market = new MenuButton("icons/market.png","icons/market_on.png",40,10,view,view.getScrollPane());	
		wallet = new MenuButton("icons/wallet.png","icons/wallet_on.png",150,10,view,view.getWalletView());
		analytics = new MenuButton("icons/analytics.png","icons/analytics_on.png",270,10,view,view.getAnalyticView());
		favorite = new MenuButton("icons/star.png","icons/star_on.png",380,10,view,view.getFavoriteView());
		
		buttons.add(wallet);
		buttons.add(market);
		buttons.add(analytics);
		buttons.add(favorite);
		
		//default
		market.on();
		
		this.getChildren().addAll(buttons);
	 
	}
	


	public MenuButton getWallet() {
		return wallet;
	}
	
	public MenuButton getMarket() {
		return market;
	}
	
	
		
	//event all buttons in menu
	public void addEventButton(EventHandler<ActionEvent> event) {
		wallet.setOnAction(event);
		market.setOnAction(event);
		analytics.setOnAction(event);
		favorite.setOnAction(event);
	}

	public void offAllButton() {
		for(MenuButton b: buttons ) {
			b.off();
		}
	}

	/*
	 * By Uriel Malka www.GitHub.com/urielmalka
	 */

}
