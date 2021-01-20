//JavaFX Monkey Game
//Umer Ahmad
//December 5, 2018
//This program is a game in which you play as a monkey and try to collect as many bananas as possible, avoiding obstacles
//Program requires Java prerequisites (JDK, java.io, JavaFx, libraries, etc.)

//Importing necessities
package monkeyGame;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

/** Main Class and Method to start primary stage and show it*/
public class Main extends Application {
	@Override
	/** Method to start and initialize scene/stage.
	 * @param primaryStage A stage to set scene upon.
	 */
	public void start(Stage primaryStage) {
		try {
			//Initialize, and load scene/primary stage, apply css and show stage
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Start.fxml"));
			BorderPane root = (BorderPane)loader.load();
			Scene scene = new Scene(root,804,609);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/** Method to launch.
	 * @param args Launch the array string.
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
