package View;

import java.util.ArrayList;

import com.sun.jdi.Field;

import CryproCoin.MyCoin;
import CryproCoin.Trader;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class WalletView extends Pane implements Runnable{
	
	
	private Label balanceLabel,nameLabel;
	private Image imgDeposite;
	private ImageView imageViewDeposite; 
	private Button deposite;
	private Thread cointThread;
	private TextField depositeField;
	private WalletFatherCoinView walletFatherCoinView;
	private ScrollCoinView scrollCoinView;

	
	public WalletView(int xPos , int yPos) {
		
		
		walletFatherCoinView = new WalletFatherCoinView(0, 0);
		scrollCoinView = new ScrollCoinView(20,70,walletFatherCoinView);
		scrollCoinView.setPrefViewportWidth(400);
		scrollCoinView.setPrefViewportHeight(230);
		
		
		nameLabel = new Label();
		nameLabel.setText("Balance:");
		nameLabel.relocate(30, 0);
		nameLabel.setFont(new Font("Ariel",40));
		nameLabel.setTextFill(Color.YELLOW);
		
		balanceLabel = new Label();
		balanceLabel.setText(String.format("%.2f", Trader.getWallet().getBalance()) + "$");
		balanceLabel.relocate(240, 0);
		balanceLabel.setFont(new Font("Ariel",40));
		balanceLabel.setTextFill(Color.YELLOW);
		
		
		this.relocate(xPos, yPos);
		this.getChildren().addAll(nameLabel,balanceLabel,scrollCoinView);
		
		cointThread = new Thread(this);
		cointThread.start();
		depositeView();
		
	}
	
	
	private void depositeView() {
		imgDeposite = new Image("file:icons/deposite.png");
	    imageViewDeposite = new ImageView(imgDeposite);
	    imageViewDeposite.setFitHeight(30);
	    imageViewDeposite.setPreserveRatio(true);
	      
	    //Setting the location of the button
	    deposite = new Button();
	    deposite.setTranslateX(210);
	    deposite.setTranslateY(300);
	    deposite.setPrefSize(80, 80);
	    deposite.setGraphic(imageViewDeposite);
	    deposite.setBackground(null);
	    deposite.setStyle("-fx-background-color: transparent;");
	    
	    depositeField = new TextField();
	    depositeField.setPromptText("Amount (USD)");
	    depositeField.setTranslateX(80);
	    depositeField.setTranslateY(328);
	    depositeField.setMaxWidth(100);
	    
	    
	    this.getChildren().addAll(depositeField,deposite);
	}
	
	public void addEventDeposite(EventHandler<ActionEvent> dep) {
		this.deposite.setOnAction(dep);
	}

	
	@Override
	public void run() {
		while(true) {
			try {Thread.sleep(500);
			} catch (InterruptedException e) {e.printStackTrace();}
			Platform.runLater(() -> {				
				balanceLabel.setText(String.format("%.2f", Trader.getWallet().getBalance()) + "$");		
			});				
			
		}
		
		
	}


	public WalletFatherCoinView getWalletFatherCoinView() {
		return walletFatherCoinView;
	}


	public double getDeposite() {
		try {
			return Double.parseDouble(depositeField.getText());
		}catch (NumberFormatException e) {
			return 0;
		}
		
	}
	
	public Button getButtonDeposite() {
		return deposite;
	}
	
	public TextField getTextFieldDeposite() {
		return depositeField;
	}

	/*
	 * By Uriel Malka www.GitHub.com/urielmalka
	 */
}
