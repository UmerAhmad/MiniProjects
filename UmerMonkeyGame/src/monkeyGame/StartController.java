//JavaFX Monkey Game
//Umer Ahmad
//December 5, 2018
//This program is a game in which you play as a monkey and try to collect as many bananas as possible, avoiding obstacles
//Program requires Java prerequisites (JDK, java.io, JavaFx, libraries, etc.)

//Importing necessities
package monkeyGame;
import monkeyGame.MonkeyGameController;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

/** StartController class to store all methods and constructor - first to be initialized when loading game. */
public class StartController {
	
	//Stage variable that allows us to refer create a new stage(instructions).
	Stage secondaryStage;
	
	/** Method to process button clicks based off given action.
	 * @param evt A ActionEvent variable that is referenced in FXML file for button clicks.
	 */
	public void buttonClickHandler(ActionEvent evt) {
		
			//Setting the button click to a variable, and creating an array of buttons for ease of use
			Button clickedButton = (Button)evt.getTarget();
			String buttonLabel = clickedButton.getText();
			
			//If the button clicked was instructions, call openInstructions method
			if (buttonLabel.equals("Instructions")) {
				openInstructions();
			}
			
			//If the button clicked was play, close current window, and call openPlayerData method
			if (buttonLabel.equals("Play")) {
				final Node source = (Node) evt.getSource();
				final Stage stage =(Stage)source.getScene().getWindow();
				stage.close();
				playGame();
				
			}
		}
	
	
	
	//Method to open instructions stage
	private void openInstructions() {
		try {
			
			//Loading the pop up that was created
			Pane instructions = (Pane)FXMLLoader.load(getClass().getResource("Instructions.fxml"));
					
			//Creating a new scene
			Scene instructionsScene = new Scene(instructions,600,400);

			instructionsScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			//Creating new stage to utilize scene
			secondaryStage = new Stage();
			secondaryStage.setScene(instructionsScene);
			secondaryStage.setResizable(false);
			secondaryStage.showAndWait();
			} catch(Exception e) {
				e.printStackTrace();
			}
	}

	
	//Method to act as gateway to change to MonkeyGameController
	 private void playGame() {
		try {
			//Create a new scene under the MonkeyGame.fxml guidance, and apply css
			FXMLLoader loader = new FXMLLoader(getClass().getResource("MonkeyGame.fxml"));
			BorderPane monkeyGameRoot = (BorderPane)loader.load();
			Scene monkeyGameScene = new Scene(monkeyGameRoot,1000,725);
			monkeyGameScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());	
			
			
			
			//Initialize stage and show 
			Stage monkeyGameStage = new Stage();
			monkeyGameStage.setScene(monkeyGameScene);
			MonkeyGameController controller = loader.getController();
			monkeyGameStage.setScene(monkeyGameScene);
			controller.getScene(monkeyGameStage);
			controller.gameLoop();
			monkeyGameStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	} 
	

	 /** Method to process clicks that are used to close current window.
		 * @param evt A ActionEvent variable that is referenced in FXML file for button clicks.
		 */
	public void closeCurrentWindow(ActionEvent evt) {
		final Node source = (Node) evt.getSource();
		final Stage stage =(Stage)source.getScene().getWindow();
		stage.close();
	}
	
	
}
