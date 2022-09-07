package View;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;


public class ScrollCoinView extends ScrollPane{
	
	public ScrollCoinView(int xPos , int yPos,Pane pane){
		
		
		this.relocate(xPos, yPos);
		this.setStyle("-fx-background-color :transparent;\r\n"
					 + "-fx-border-color :transparent;\r\n"
				     +"-fx-background: #2D3957;\n\r"
				     + "-fx-padding: 0;");
		this.setContent(pane);
		this.setHbarPolicy(ScrollBarPolicy.NEVER);
		this.setVbarPolicy(ScrollBarPolicy.NEVER);
		this.setPrefViewportWidth(400);
		this.setPrefViewportHeight(380);
		
		
	}
	
	/*
	 * By Uriel Malka www.GitHub.com/urielmalka
	 */
}
