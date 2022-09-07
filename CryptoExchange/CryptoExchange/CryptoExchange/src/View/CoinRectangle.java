package View;



import CryproCoin.Coin;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
public class CoinRectangle extends Pane implements Runnable{
	
	private MenuButton favorite;
	private Label nameLabel,valueLabel;
	private Thread cointThread;
	private Coin coin;
	private BuyCoinView buyCoinView;
	
	/*
	 * Rectangle view for coin.
	 */

	public CoinRectangle(int xPos , int yPos,Coin coin) {
		this.coin = coin; 
		
		buyCoinView = new BuyCoinView(50, 90,coin);
		
		favorite = new MenuButton("icons/star.png","icons/star_on.png",0,0);
		favorite.setSize(20,0);//Default
		
		nameLabel = new Label();
		nameLabel.setText(coin.getCoinName());
		nameLabel.relocate(125, 0);
		nameLabel.setFont(new Font("Ariel",20));
		nameLabel.setTextFill(Color.YELLOW);
		
		
		
		valueLabel = new Label();
		valueLabel.setText(String.format("%.6f", coin.getValue()) + "$");
		valueLabel.relocate(250, 0);
		valueLabel.setFont(new Font("Ariel",20));
		valueLabel.setTextFill(Color.YELLOW);
		
		this.setMinWidth(400);
		

		this.relocate(xPos, yPos);
		this.getChildren().addAll(favorite,nameLabel,valueLabel);
		
		//set color on pane
		this.addEventHandler(MouseEvent.MOUSE_MOVED, event -> {
		    this.setStyle("-fx-background-color: rgba(255, 255, 255, 0.2); -fx-background-radius: 5;");
		});
		
		//set clear on pane
		this.setOnMouseExited(event -> {
		    this.setStyle(null);
		});	
		
		cointThread = new Thread(this);
		cointThread.start();
	}
	


	
	public void addEventPane(EventHandler<MouseEvent> click) {
		this.setOnMouseClicked(click);
	}
	

	@Override
	public void run() {
		while(true) {
			try {Thread.sleep(5);
			} catch (InterruptedException e) {e.printStackTrace();}
			Platform.runLater(() -> {
				if(coin.getValue() > coin.getLast())
					valueLabel.setTextFill(Color.GREEN);
				else if(coin.getValue() < coin.getLast())
					valueLabel.setTextFill(Color.RED);
				valueLabel.setText(String.format("%.6f", coin.getValue()) + "$");	
			});				
			
		}
		
		
	}

	public Coin getCoin() {
		return coin;
	}

	public MenuButton getMenuButton() {
		return favorite;
	}
	
	
	
	public void setLocation(int xPos, int yPos) {
		this.relocate(xPos, yPos);
	}
	
	public BuyCoinView getBuyCoinView() {
		return buyCoinView;
	}



	/*
	 * By Uriel Malka www.GitHub.com/urielmalka
	 */
	

}
