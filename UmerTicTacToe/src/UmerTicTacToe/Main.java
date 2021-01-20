//JavaFX Tic Tac Toe
//Umer Ahmad
//November 9, 2018
//This program is one in which a game of tic tac toe is displayed along with extras
//Program requires Java prerequisites (JDK, java.io, JavaFx, libraries, etc.)


//Importing Package where all files are located and fx necessities
package UmerTicTacToe;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

//Main Class and Method thats takes in the stage as a parameter, and sets it up
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			//Loads the fxml file for reference, creates the scene (window), loads style sheet for reference, and sets scene
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("TicTacToe.fxml"));
			Scene scene = new Scene(root,429,418);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	//Method to launch fx stage
	public static void main(String[] args) {
		launch(args);
	}
}
