package View;

import CryproCoin.Trader;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class AnalyticView extends Pane implements Runnable{
	
	private GraphView graphView;
	private Thread balanceThread;
	private Label nameLabel,balanceLabel;
	private int graphCounter;
	private double balance;
	
	public AnalyticView(int xPos , int yPos) {
		
		nameLabel = new Label();
		nameLabel.setText("Balance:");
		nameLabel.relocate(30, 0);
		nameLabel.setFont(new Font("Ariel",40));
		nameLabel.setTextFill(Color.YELLOW);
		
		balanceLabel = new Label();
		balanceLabel.setText(String.format("%.2f", Trader.getWallet().getBalance()) + "$");
		balanceLabel.relocate(225, 0);
		balanceLabel.setFont(new Font("Ariel",40));
		balanceLabel.setTextFill(Color.YELLOW);
		
		
		graphView = new GraphView(0, 50);
		
		this.relocate(xPos, yPos);
		this.getChildren().addAll(graphView,nameLabel,balanceLabel);
		
		balanceThread = new Thread(this);
		balanceThread.start();
	}

	@Override
	public void run() {
		graphCounter = 0;
		while(true) {
			try {Thread.sleep(2000);
			} catch (InterruptedException e) {e.printStackTrace();}
			
			try {
				balance = Trader.getWallet().getBalance();
				graphView.addData(graphCounter++, Trader.getWallet().getBalance());
				graphView.getYAxis().setLowerBound(balance - balance*0.001);
				graphView.getYAxis().setUpperBound(balance + balance*0.001);
				Platform.runLater(() -> {				
					balanceLabel.setText(String.format("%.2f", balance) + "$");
				});


			}catch (IllegalStateException e) {}
			catch (Exception e) {}
		}
		
		
	}

	
	/*
	 * By Uriel Malka www.GitHub.com/urielmalka
	 */
	
}
