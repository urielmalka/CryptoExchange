package View;



import CryproCoin.Coin;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BuyCoinView extends Pane implements Runnable{
	
	private Coin coin;
	private Label nameLabel,valueLabel;
	private Thread cointThread;
	private Image imgBuy,imgSell,imgMax;
	private ImageView imageViewBuy,imageViewSell,imageViewMaxBuy,imageViewMaxSell;
	private Button buy,sell,buyMax,sellMax;
	private TextField buyField,sellField,equalBuyField,equalSellField;
	private int graphCounter, graphAmount;
	private GraphView graphView;

	
	public BuyCoinView(int xPos , int yPos,Coin coin) {
		
		this.coin = coin;
		
		nameLabel = new Label();
		nameLabel.setText(coin.getCoinName());
		nameLabel.relocate(15, 0);
		nameLabel.setFont(new Font("Ariel",40));
		nameLabel.setTextFill(Color.YELLOW);
		
		graphView = new GraphView(0, 50,coin);
		
		valueLabel = new Label();
		valueLabel.setText(String.format("%8f", coin.getValue()));
		valueLabel.relocate(175, 0);
		valueLabel.setFont(new Font("Ariel",40));
		valueLabel.setTextFill(Color.YELLOW);
		
		
		start();
		
		
		
		this.getChildren().addAll(graphView,nameLabel,valueLabel,buy,sell,buyField,sellField
									,buyMax,sellMax,equalBuyField,equalSellField);
		
		this.relocate(xPos, yPos);
		
		cointThread = new Thread(this);
		cointThread.start();
	}
	
	
	private void start() {
	    imgBuy = new Image("file:icons/buy.png");
	    imageViewBuy = new ImageView(imgBuy);
	    imageViewBuy.setFitHeight(40);
	    imageViewBuy.setPreserveRatio(true);
	      
	    //Setting the location of the button
	    buy = new Button();
	    buy.setTranslateX(275);
	    buy.setTranslateY(250);
	    buy.setPrefSize(80, 80);
	    buy.setGraphic(imageViewBuy);
	    buy.setBackground(null);
	    buy.setStyle("-fx-background-color: transparent;");
	    
	    imgSell = new Image("file:icons/sell.png");
	    imageViewSell = new ImageView(imgSell);
	    imageViewSell.setFitHeight(40);
	    imageViewSell.setPreserveRatio(true);
	      
	    //Setting the location of the button
	    sell = new Button();
	    sell.setTranslateX(275);
	    sell.setTranslateY(300);
	    sell.setPrefSize(80, 80);
	    sell.setGraphic(imageViewSell);
	    sell.setBackground(null);
	    sell.setStyle("-fx-background-color: transparent;");
	    
	    //Image Max
	    imgMax = new Image("file:icons/max.png");
	    imageViewMaxBuy = new ImageView(imgMax);
	    imageViewMaxBuy.setFitHeight(20);
	    imageViewMaxBuy.setPreserveRatio(true);
	    imageViewMaxSell = new ImageView(imgMax);
	    imageViewMaxSell.setFitHeight(20);
	    imageViewMaxSell.setPreserveRatio(true);
	      
	    //Max Buy Button : Setting the location of the button
	    buyMax = new Button();
	    buyMax.setTranslateX(0);
	    buyMax.setTranslateY(276);
	    buyMax.setGraphic(imageViewMaxBuy);
	    buyMax.setBackground(null);
	    buyMax.setStyle("-fx-background-color: transparent;");
	    
	      
	    //Max Sell Button : Setting the location of the button
	    sellMax = new Button();
	    sellMax.setTranslateX(0);
	    sellMax.setTranslateY(326);
	    sellMax.setGraphic(imageViewMaxSell);
	    sellMax.setBackground(null);
	    sellMax.setStyle("-fx-background-color: transparent;");
	    
	    buyField = new TextField();
	    buyField.setPromptText("Amount (USD)");
	    buyField.setTranslateX(30);
	    buyField.setTranslateY(278);
	    buyField.setMaxWidth(100);
	    
	    
	    sellField = new TextField();
	    sellField.setPromptText("Amount (" + coin.getCoinName() + ")");
	    sellField.setTranslateX(30);
	    sellField.setTranslateY(328);
	    sellField.setMaxWidth(100);
	    
	    
	    equalBuyField = new TextField();
	    equalBuyField.setPromptText("Amount (" + coin.getCoinName() + ")");
	    equalBuyField.setTranslateX(140);
	    equalBuyField.setTranslateY(278);
	    equalBuyField.setMaxWidth(140);
	    equalBuyField.setDisable(true);
	    equalBuyField.setStyle("-fx-background-color: #2D3957; -fx-text-fill: white ; -fx-font-size: 12px;");
		
		equalSellField = new TextField();
		equalSellField.setPromptText("Amount (USD)");
		equalSellField.setTranslateX(140);
		equalSellField.setTranslateY(328);
		equalSellField.setMaxWidth(140);
		equalSellField.setDisable(true);
		equalSellField.setStyle("-fx-background-color: #2D3957; -fx-text-fill: white ; -fx-font-size: 12px;");
	    
	    
	}
	
	
	@Override
	public void run() {
		graphCounter = 0;
		while(true) {
			try {Thread.sleep(2000);
			} catch (InterruptedException e) {e.printStackTrace();}
			Platform.runLater(() -> {
				if(coin.getValue() > coin.getLast())
					valueLabel.setTextFill(Color.GREEN);
				else if(coin.getValue() < coin.getLast())
					valueLabel.setTextFill(Color.RED);
				valueLabel.setText(String.format("%8f", coin.getValue()));	
			});	
			graphView.addData(graphCounter++, coin.getValue());
			graphView.getYAxis().setLowerBound(coin.getValue() - coin.getValue()*0.001);
			graphView.getYAxis().setUpperBound(coin.getValue() + coin.getValue()*0.001);
			if(graphAmount < 20)
				graphAmount++;
			else 
				graphView.removeOld();


			
		}
		
		
	}
	
	public Coin getCoin() {
		return coin;
	}
	
	//Actions
	
	public void addEventButtonBuy(EventHandler<ActionEvent> buy) {
		this.buy.setOnAction(buy);
	}
	
	public void addEventButtonSell(EventHandler<ActionEvent> sell) {
		this.sell.setOnAction(sell);
	}
	
	
	public void addEventTextFieldBuy(EventHandler<ActionEvent> buy) {
		buyField.setOnAction(buy);		
	}
	
	public TextField getEqualBuyLabel() {
		return equalBuyField;
	}
	
	public TextField getBuyField() {
		return buyField;
	}
	
	public TextField getEqualSellLabel() {
		return equalSellField;
	}
	
	public TextField getSellField() {
		return sellField;
	}
	
	
	/*
	 * By Uriel Malka www.GitHub.com/urielmalka
	 */
}
