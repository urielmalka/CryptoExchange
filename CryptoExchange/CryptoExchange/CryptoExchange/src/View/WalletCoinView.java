package View;

import CryproCoin.MyCoin;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class WalletCoinView extends Pane implements Runnable{
	
	private Label nameLabel,valueLabel,balanceLabel;
	private MyCoin myCoin;
	private Thread cointThread;
	
	public WalletCoinView(int xPos , int yPos , MyCoin myCoin) {
		
		this.myCoin = myCoin;
		
		start();
		
		this.relocate(xPos, yPos);
		this.getChildren().addAll(nameLabel,valueLabel,balanceLabel);
		
		cointThread = new Thread(this);
		cointThread.start();
	}
	
	
	private void start() {
		nameLabel = new Label();
		nameLabel.setText(myCoin.getCoin().getCoinName());
		nameLabel.relocate(25, 0);
		nameLabel.setFont(new Font("Ariel",20));
		nameLabel.setTextFill(Color.YELLOW);
		
		
		
		valueLabel = new Label();
		valueLabel.setText(String.format("%.6f", myCoin.getAmount()));
		valueLabel.relocate(100, 0);
		valueLabel.setFont(new Font("Ariel",20));
		valueLabel.setTextFill(Color.YELLOW);
		
		
		balanceLabel = new Label();
		balanceLabel.setText(String.format("%.2f", myCoin.getDollarValue()) + "$");
		balanceLabel.relocate(250, 0);
		balanceLabel.setFont(new Font("Ariel",20));
		balanceLabel.setTextFill(Color.YELLOW);
				
	}
	
	
	
	@Override
	public void run() {
		while(true) {
			try {Thread.sleep(500);
			} catch (InterruptedException e) {e.printStackTrace();}
			Platform.runLater(() -> {				
				balanceLabel.setText(String.format("%.2f", myCoin.getDollarValue()) + "$");			
			});				
			
		}
		
		
	}

	
	/*
	 * By Uriel Malka www.GitHub.com/urielmalka
	 */
}
