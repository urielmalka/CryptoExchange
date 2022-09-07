package Controller;

import java.util.ArrayList;

import CryproCoin.Trader;
import Model.Model;
import View.CoinRectangle;
import View.View;

public class Controller {
	
	private View view;
	private Model model;
	private MenuController menuController;
	private ArrayList<CoinRectangleController> coinRectangleController;
	private ArrayList<FavoriteController> favoriteController;
	private DepositeController depositeController;
	
	

	public Controller(View view , Model model){
		this.view = view;
		this.model = model;	
		
		depositeController = new DepositeController(view.getWalletView(), model.getWalletModel());
		
		menuController = new MenuController(view, model, this);
		coinRectangleController = new ArrayList<CoinRectangleController>();
		favoriteController = new ArrayList<FavoriteController>();
		CreateArrayListRectangles();
		
		
	}
	
	public void CreateArrayListRectangles() {
		for(CoinRectangle coinR : view.getMarketView().getCoinRec()) {
			coinRectangleController.add(new CoinRectangleController(view,model,this,coinR)); 
			if(Trader.getFavorite().contains(coinR.getCoin())) {
				coinR.getMenuButton().on();
				favoriteController.add(new FavoriteController(view,model,this,coinR,view.getFavoriteView().addFav(coinR)));

			}
	}
			
	}
	
	public ArrayList<FavoriteController> getFavoriteController() {
		return favoriteController;
	}
	
	/*
	 * By Uriel Malka www.GitHub.com/urielmalka
	 */
}
