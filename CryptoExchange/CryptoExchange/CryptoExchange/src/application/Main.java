package application;

import Controller.Controller;
import Model.Model;
import View.View;
import javafx.application.Application;
import javafx.stage.Stage;



public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {	
		/**
		 * MVC design patterns
		 * 
		 * Coins values get from coinapi.io using free API 
		 * 
		 * By Uriel Malka www.GitHub.com/urielmalka
		 */
		
		//API reading coins values.
		PythonAPI pythonAPI = new PythonAPI(); 
		
		
		//UI
		Model model = new Model();
		View view = new View(primaryStage);
		
		Controller controller = new Controller(view, model);
		
		pythonAPI.join();
	}
	
	public static void main(String[] args) {
		launch(args);

		System.exit(0);
	}
}
