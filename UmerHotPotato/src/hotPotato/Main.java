//JavaFX Hot Potato
//Umer Ahmad
//December 1, 2018
//This program is a game that simulates the popular hot potato game
//Program requires Java prerequisites (JDK, java.io, JavaFx, libraries, etc.)

//Importing necessities
package hotPotato;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

//Main class and method
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			//Loads the fxml file for reference, creates the scene (window), loads style sheet for reference, and sets scene
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("HotPotato.fxml"));
			Scene scene = new Scene(root,491,487);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
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
