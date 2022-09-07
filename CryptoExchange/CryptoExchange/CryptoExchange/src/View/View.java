package View;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class View {
	
	private Stage primaryStage;
	private Group root;
	private Scene scene;
	private Menu menu;
	private MarketView marketView;
	private ScrollCoinView scrollCoinView;
	private FavoriteView favoriteView;
	private AnalyticView analyticView;
	private WalletView walletView;
	
	public View(Stage primaryStage){
		
		
		
		marketView = new MarketView(0, 0);
		scrollCoinView = new ScrollCoinView(50, 90, marketView);
		
		favoriteView = new FavoriteView(50, 90);
		analyticView = new AnalyticView(50, 90);
		walletView = new WalletView(50, 90);

		
		menu = new Menu(0, 0, this);
				

		
		this.primaryStage = primaryStage;
		root = new Group();		
		scene = new Scene(root,500,500);
		scene.setFill(Color.rgb(45, 57, 87));
		root.getChildren().addAll(menu,scrollCoinView);
		
		//SQL UPDATE
		walletView.getWalletFatherCoinView().setCoinView();
		favoriteView.setCoin();
		
		
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

	public Menu getMenu() {
		return menu;
	}

	public Group getRoot() {
		return root;		
	}

	public MarketView getMarketView() {
		return marketView;
	}
	
	public ScrollPane getScrollPane() {
		return scrollCoinView;
	}
	
	public WalletView getWalletView() {
		return walletView;
	}
	
	
	public FavoriteView getFavoriteView() {
		return favoriteView;
	}
	
	public AnalyticView getAnalyticView() {
		return analyticView;
	}

	
	
	
	
	/*
	 * By Uriel Malka www.GitHub.com/urielmalka
	 */
	

}
