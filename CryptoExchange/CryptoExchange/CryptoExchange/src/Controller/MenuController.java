package Controller;


import Model.Model;
import View.MenuButton;
import View.View;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class MenuController {
	
	private View view; 
	private Model model;
	private Controller controller;
	
	public MenuController(View view , Model model , Controller controller){
		
		EventHandler<ActionEvent> menu = new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				view.getMenu().offAllButton();
				MenuButton eventButton = (MenuButton) event.getSource();
				eventButton.onPane();
				
			}
			
		};
		view.getMenu().addEventButton(menu);
		
		
	}
	
	/*
	 * By Uriel Malka www.GitHub.com/urielmalka
	 */
}
