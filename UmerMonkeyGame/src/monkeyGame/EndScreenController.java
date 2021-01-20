//JavaFX Monkey Game
//Umer Ahmad
//December 5, 2018
//This program is a game in which you play as a monkey and try to collect as many bananas as possible, avoiding obstacles
//Program requires Java prerequisites (JDK, java.io, JavaFx, libraries, etc.)

//Importing necessities
package monkeyGame;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;



/** EndScreenController - initialized once the game is over ie. you died.*/
public class EndScreenController {
	
	//FXML variables with injected IDs, for manipulation
	// FXML id of scoreLabel to display score dynamically. */
	@FXML private Label scoreLabel;
	// FXML id of distanceLabel to display distance dynamically. */
	@FXML private Label distanceLabel;
	
	// Global integer of bananas eaten (to keep track and display it). */
	int bananasEaten;
	// Global double of distance travelled.(to keep track and display it). */
	double distanceTravelled;
	
	/** Method to handle button clicks .
	 * @param evt An action event that is referenced to from buttons in the FXML file. 
	 */
	public void buttonClickHandler(ActionEvent evt) {
			//Setting the button click to a variable, and creating an array of buttons for ease of use
			Button clickedButton = (Button)evt.getTarget();
			String buttonLabel = clickedButton.getText();
			
		
			
			//If the button click play again, and there is one remaining winner, clear text areas and stacks, and fill queue with elements from arraylist
			if (buttonLabel.equals("Quit")) {
				Platform.exit();
		}
	}
	
	
	/** Method to get score and distance from previous controller. 
	 * @param score A integer that represents score to be displayed on final screen.
	 * @param distance A double that represent total distance traveled to be displayed on final screen.
	 */
	public void getScoreAndDistance(int score, double distance) {
		bananasEaten = score;
		distanceTravelled = distance;
		scoreLabel.setText(Integer.toString(bananasEaten));
		distanceLabel.setText(Double.toString(distanceTravelled));
		

	}

	

	/** Method to close window. 
	 * @param evt An action event that is referenced to from the quit button in the FXML.
	 */
	public void closeCurrentWindow(ActionEvent evt) {
		final Node source = (Node) evt.getSource();
		final Stage stage =(Stage)source.getScene().getWindow();
		stage.close();
	}
	

}
