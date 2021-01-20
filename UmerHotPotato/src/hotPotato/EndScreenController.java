//JavaFX Hot Potato
//Umer Ahmad
//December 1, 2018
//This program is a game that simulates the popular hot potato game
//Program requires Java prerequisites (JDK, java.io, JavaFx, libraries, etc.)

//Importing necessities
package hotPotato;
import stacksAndQueues.Queue;
import stacksAndQueues.Stack;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;


//Main Class 
public class EndScreenController {
	
	//FXML variables with injected IDs, for manipulation
	@FXML private TextArea playerRankings;
	@FXML private Label potatoCarrier;
	@FXML private Label firstPlace;
	
	//Initializing a stack and queue to fill
	Queue<String> playerQueue = new Queue<String>();
	Stack<String> playerStack = new Stack<String>();
	
	
	//Method to grab stack and queue from previous controller, to display rankings
	public void getStackAndQueue(Queue<String> queue, Stack<String> stack) {
		playerQueue = queue;
		playerStack = stack;
		firstPlace.setText("#1 - " + playerQueue.peek());
		playerStack.displayRankings(playerRankings);
	}
	
	
	//Method to close current window when clicked
	public void closeCurrentWindow(ActionEvent evt) {
		final Node source = (Node) evt.getSource();
		final Stage stage =(Stage)source.getScene().getWindow();
		stage.close();
	}
	

}
