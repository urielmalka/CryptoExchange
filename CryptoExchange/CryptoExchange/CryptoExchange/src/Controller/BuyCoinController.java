package Controller;

import CryproCoin.Trader;
import Model.Model;
import View.BuyCoinView;
import View.View;
import View.WalletCoinView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class BuyCoinController {
	
	private View view;
	private Model model;
	private String showValue,balanceStr;
	private double value,buyValue;
	
	public BuyCoinController(View view , Model model , BuyCoinView buyCoinView) {
		
		this.view = view;
		this.model = model;

		EventHandler<ActionEvent> buy = new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {

				//Return MyCoin Object , if null - MyCoin in HashMap
				model.getWalletModel().buy(Trader.getWallet().addMyCoin(buyCoinView.getCoin(), value),buyValue);
				buyCoinView.getBuyField().clear();
				
				
				//For Wallet Pane
				view.getWalletView().getWalletFatherCoinView().setCoinView();
			}	
			
		};
		buyCoinView.addEventButtonBuy(buy);
		
		EventHandler<ActionEvent> sell = new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				
				//Return MyCoin Object , if null - MyCoin in HashMap
				model.getWalletModel().sell(Trader.getWallet().addMyCoin(buyCoinView.getCoin(), value),buyValue);
				buyCoinView.getSellField().clear();
				Trader.getWallet().clearCoinIfZero();
				
				//For Wallet Pane
				view.getWalletView().getWalletFatherCoinView().setCoinView();
				
			}
			
		};
		buyCoinView.addEventButtonSell(sell);
		
		//check value buy in text buy field : listener
		buyCoinView.getBuyField().textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable,
		            String oldValue, String newValue) {
		    	
		    	balanceStr = String.format("%f", model.getWalletModel().getCoinBalance("USD"));
		    	
		    	newValue = model.getBuyCoinModel().checkInput(newValue);

		    	if(newValue.length() == 0) {
		    		buyCoinView.getEqualBuyLabel().setText("");
		    		buyCoinView.getBuyField().setText("");
		    		return;
		    	}else
		    		buyCoinView.getBuyField().setText(newValue);
		    		
		    	
		    	buyValue = Double.parseDouble(newValue);
		    	
		    	if(Double.parseDouble(balanceStr) < Double.parseDouble(newValue)) {
		    		newValue = balanceStr;
		    		buyCoinView.getBuyField().setText(newValue);
		    		buyValue = Double.parseDouble(balanceStr);
		    	}
		    	  			    	
		    	showValue = model.getBuyCoinModel().buyRecive(Double.parseDouble(newValue),buyCoinView.getCoin().getValue());	
		    		    
		    	if(showValue.length() != 0)
		    		showValue = "= " + showValue + buyCoinView.getCoin().getCoinName();
		    	buyCoinView.getEqualBuyLabel().setText(showValue);
		    	
		    	
		    	
		    	
		    }
		});
		
		//check value sell in text sell field : listener
		buyCoinView.getSellField().textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable,
		            String oldValue, String newValue) {
		    	
		    	balanceStr = String.format("%f", model.getWalletModel().getCoinBalance(buyCoinView.getCoin().getCoinName()));
		    	
		    	
		    	
		    	newValue = model.getBuyCoinModel().checkInput(newValue);
		    	
		    	if(newValue.length() == 0) {
		    		buyCoinView.getEqualSellLabel().setText("");
		    		buyCoinView.getSellField().setText("");
		    		return;
		    	}else
		    		buyCoinView.getSellField().setText(newValue);
		    	
		    	if(Double.parseDouble(balanceStr) < Double.parseDouble(newValue)) {
		    		newValue = balanceStr;
		    		buyCoinView.getSellField().setText(newValue);
		    		buyValue = Double.parseDouble(balanceStr);
		    	}
		    	
		    	showValue = model.getBuyCoinModel().sellRecive(Double.parseDouble(newValue),buyCoinView.getCoin().getValue());	
		    	
		    	if(showValue.length() != 0)
		    		showValue = "= " + showValue + "$";
		    	buyCoinView.getEqualSellLabel().setText(showValue);
		    		


		    }
		});
		
		
		
	
		
	}
	
	/*
	 * By Uriel Malka www.GitHub.com/urielmalka
	 */

}
