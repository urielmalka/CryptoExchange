package Controller;

import Model.WalletModel;
import View.WalletView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class DepositeController {
	
	public DepositeController(WalletView walletView , WalletModel walletModel) {
		
		EventHandler<ActionEvent> deposite = new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				
				walletModel.deposite(walletView.getDeposite());
				walletView.getTextFieldDeposite().clear();
				
				//For Wallet Pane
				walletView.getWalletFatherCoinView().setCoinView();
				
			}
			
		};
		walletView.addEventDeposite(deposite);
		
		//check value buy in text buy field : listener
		walletView.getTextFieldDeposite().textProperty().addListener(new ChangeListener<String>() {
				    @Override
				    public void changed(ObservableValue<? extends String> observable,
				            String oldValue, String newValue) {
				    	
				    	
				    	newValue = walletModel.checkInput(newValue);

				    	if(newValue.length() == 0) {
				    		walletView.getTextFieldDeposite().setText("");
				    		return;
				    	}else
				    		walletView.getTextFieldDeposite().setText(newValue);
				    						 			 			    				    	
				    }
		});
		
	}
	
	/*
	 * By Uriel Malka www.GitHub.com/urielmalka
	 */

}
