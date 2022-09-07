package View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class MenuButton extends Button{
	
	private Image imgOff,imgOn;
	private ImageView imageViewOff,imageViewOn;
	private View view;
	private Pane pane;
	private ScrollPane scroll;
	private boolean isOn;
	
	public MenuButton(String off , String on , int xPos , int yPos) {		
		start(off,on,xPos,yPos);  
	}
	
	public MenuButton(String off , String on , int xPos , int yPos,View view , Pane pane) {		  
		this.view = view;
		this.pane = pane;
		start(off,on,xPos,yPos);
	}
	
	public MenuButton(String off , String on , int xPos , int yPos,View view , ScrollPane scroll) {		  
		this.view = view;
		this.scroll = scroll;
		this.pane = null;		
		start(off,on,xPos,yPos);
	}
	
	public void start(String off , String on , int xPos , int yPos) {
		imgOff = new Image("file:" + off);
	    imageViewOff = new ImageView(imgOff);
	    imageViewOff.setFitHeight(40);
	    imageViewOff.setPreserveRatio(true);
	    isOn = false;

	    imgOn = new Image("file:" + on);
	    imageViewOn = new ImageView(imgOn);
	    imageViewOn.setFitHeight(40);
	    imageViewOn.setPreserveRatio(true);
	      
	    //Setting the location of the button
	    this.setTranslateX(xPos);
	    this.setTranslateY(yPos);
	    this.setPrefSize(80, 80);
	    this.setGraphic(imageViewOff);
	    this.setBackground(null);
	    this.setStyle("-fx-background-color: transparent;");
	}
	
	public void addEventButton(EventHandler<ActionEvent> menu) {
		this.setOnAction(menu);
	}
	
	
	public void on() {
		isOn = true;
		this.setGraphic(imageViewOn);
	}
	
	public void onPane() {
		isOn = true;
		this.setGraphic(imageViewOn);
		view.getRoot().getChildren().clear();
		if(this.pane != null)
			view.getRoot().getChildren().addAll(view.getMenu(),pane);
		else
			view.getRoot().getChildren().addAll(view.getMenu(),scroll);
	}
	
	public void off() {
		isOn = false;
		this.setGraphic(imageViewOff);
	}


	public void setSize(int sizeImg,int sizeButton) {
		imageViewOff.setFitHeight(sizeImg);
		imageViewOn.setFitHeight(sizeImg);
		this.setPrefSize(sizeButton, sizeButton);
	}
	
	public boolean getIsOn() {
		return isOn;
	}
	
	/*
	 * By Uriel Malka www.GitHub.com/urielmalka
	 */
}

