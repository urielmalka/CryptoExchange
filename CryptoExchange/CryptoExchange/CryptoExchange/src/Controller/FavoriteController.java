package Controller;

import Model.CoinSQL;
import Model.Model;
import View.CoinRectangle;
import View.View;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class FavoriteController {
		
	private View view;
	private Model model;
	private CoinRectangle coinRectangle,coinForOff;
	private Controller controller;
	
	public FavoriteController(View view,Model model,Controller controller,CoinRectangle coinForOff,CoinRectangle coinR) {
		this.view = view;
		this.model = model;
		this.coinRectangle = coinR;
		this.coinForOff = coinForOff;
		
		EventHandler<ActionEvent> favorite = new EventHandler<ActionEvent>() {

			@SuppressWarnings("unlikely-arg-type")
			@Override
			public void handle(ActionEvent arg0) {	
				coinForOff.getMenuButton().off();
				controller.getFavoriteController().remove(this);
				view.getFavoriteView().removeFav(coinRectangle);
				CoinSQL.getSQL().deleteFavorite(coinR.getCoin());
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
	
	public CoinRectangle getCoinForOff() {
		return coinForOff;
	}
	
	public CoinRectangle getCoinRectangle() {
		return coinRectangle;
	}
	
	/*
	 * By Uriel Malka www.GitHub.com/urielmalka
	 */
}
