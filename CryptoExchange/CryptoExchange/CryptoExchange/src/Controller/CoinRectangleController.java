package Controller;

import CryproCoin.Trader;
import Model.CoinSQL;
import Model.Model;
import View.CoinRectangle;
import View.View;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class CoinRectangleController {
	
	private View view;
	private Model model;
	private Controller controller;
	private CoinRectangle coinRectangle;
	private BuyCoinController buyCoinController;
	
	public CoinRectangleController(View view,Model model,Controller controller,CoinRectangle coinR) {
		this.view = view;
		this.model = model;
		this.controller = controller;
		this.coinRectangle = coinR;
		this.buyCoinController  = new BuyCoinController(view, model, coinRectangle.getBuyCoinView());
		
		EventHandler<ActionEvent> favorite = new EventHandler<ActionEvent>() {

			@SuppressWarnings("static-access")
			@Override
			public void handle(ActionEvent arg0) {
				if(!coinRectangle.getMenuButton().getIsOn()) {
					coinRectangle.getMenuButton().on();
					controller.getFavoriteController().add(new FavoriteController(view,model,controller,coinRectangle,view.getFavoriteView().addFav(coinRectangle)));
					Trader.getTrader().addFavorite(coinR.getCoin());
					CoinSQL.getSQL().insertFavorite(coinR.getCoin());
				}
				else {
					coinRectangle.getMenuButton().off();
					removeFav(coinRectangle);
					Trader.getTrader().removeFavorite(coinR.getCoin());
					CoinSQL.getSQL().deleteFavorite(coinR.getCoin());
				}
			}
		};
		coinRectangle.getMenuButton().addEventButton(favorite);
		
		
		EventHandler<MouseEvent> click = new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				view.getRoot().getChildren().clear();
				view.getRoot().getChildren().addAll(
						view.getMenu(),
						coinRectangle.getBuyCoinView()
						);
				
			}

			
		};
		coinRectangle.addEventPane(click);
		
		
		
		
	}
	
	
	public void removeFav(CoinRectangle coinRemove) {
		for(FavoriteController fcontroller : controller.getFavoriteController()) {
			if(fcontroller.getCoinForOff() == coinRectangle)
				view.getFavoriteView().removeFav(fcontroller.getCoinRectangle());
		}
		
	}
	
	/*
	 * By Uriel Malka www.GitHub.com/urielmalka
	 */
	
	
}
