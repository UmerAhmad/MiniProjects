//JavaFX Hot Potato
//Umer Ahmad
//December 1, 2018
//This program is a game that simulates the popular hot potato game
//Program requires Java prerequisites (JDK, java.io, JavaFx, libraries, etc.)

//Importing necessities
package hotPotato;
import stacksAndQueues.Queue;
import hotPotato.GameController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

//Main class, the first controller to be accessed when starting game
public class HotPotatoController {
	
	//Initialize a secondary stage, couple of FXML to be modified, and the queue
	Stage secondaryStage;
	@FXML private TextField playerInput;
	@FXML private TextArea playerList;
	Queue<String> playerQueue = new Queue<String>();
	
	//Method to process button clicks
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
				openPlayerData();
			}
			
			//If the button clicked was continue, close current window, and call playGame method
			if (buttonLabel.equals("Continue")) {
				final Node source = (Node) evt.getSource();
				final Stage stage =(Stage)source.getScene().getWindow();
				stage.close();
				playGame();
			}
			
			//If the button clicked was add player, call addPlayer function
			if (buttonLabel.equals("Add Player")){
				addPlayer();
			}
			
			//If the button clicked was remove player, and if the player hasn't inputted anything - call removePlayer function, if they have inputted an integer - call removeTargetedPlayer function
			if (buttonLabel.equals("Remove Player")){
				if (playerInput.getText().equals("")) {
				removePlayer();
				}
				if (isInteger(playerInput.getText()) == true) {
					 int trueValue = Integer.parseInt(playerInput.getText());
					 removeTargetedPlayer(trueValue);
				}
			}
			
			
		}
	
	//Method to open instructions stage
	private void openInstructions() {
		try {
			
			//Loading the pop up that was created
			Pane instructions = (Pane)FXMLLoader.load(getClass().getResource("Instructions.fxml"));
					
			//Creating a new scene
			Scene instructionsScene = new Scene(instructions,491,275);

			instructionsScene.getStylesheets().add(getClass().getResource("rankings.css").toExternalForm());

			//Creating new stage to utilize scene
			secondaryStage = new Stage();
			secondaryStage.setScene(instructionsScene);
			secondaryStage.setResizable(false);
			secondaryStage.showAndWait();
			} catch(Exception e) {
				e.printStackTrace();
			}
	}
	
	//Method to open player data stage
	private void openPlayerData() {
		try {
			//Loading the pop up that was created
			Pane playerData = (Pane)FXMLLoader.load(getClass().getResource("PlayerData.fxml"));
					
			//Creating a new scene
			Scene playerDataScene = new Scene(playerData,491,550);

			playerDataScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			//Creating new stage to utilize scene
			secondaryStage = new Stage();
			secondaryStage.setScene(playerDataScene);
			secondaryStage.setResizable(false);
			secondaryStage.showAndWait();
			} catch(Exception e) {
				e.printStackTrace();
			}
	}
	
	//Method to act as gateway to change to GameController
	private void playGame() {
		try {
			//Create a new scene under the end.fxml guidance, and apply rankings.css
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Game.fxml"));
			BorderPane hotPotatoRoot = (BorderPane)loader.load();
			Scene hotPotatoScene = new Scene(hotPotatoRoot,491,540);
			hotPotatoScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			//Send queue and stack to be retrieved in the next controller
			GameController currentController = (loader.getController());
			currentController.getQueue(playerQueue);
			
			//Initialize stage and show 
			Stage hotPotatoStage = new Stage();
			hotPotatoStage.setScene(hotPotatoScene);
			hotPotatoStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//Method to determine if parameter string is an integer
	public boolean isInteger(String playerText) {
	    try {
	        Integer.parseInt(playerText);
	        return true;
	    }
	    catch(Exception NumberFormatException) {
	        return false;
	    }
	}
	
	//Method to add player to the list and queue
	private void addPlayer() {
		//Get the text inputted into a string variable, enqueue into queue, and process queue to display names in different method - then set player input to be empty
		String playerText = playerInput.getText();
		playerQueue.enqueue(playerText);
		playerQueue.displayNames(playerList);
		playerInput.setText("");
	}
	
	//Inverse of previous method, remove the last entered player and update the display
	private void removePlayer() {
		playerQueue.dequeueLast();
		playerQueue.displayNames(playerList);
		playerInput.setText("");
	}
	
	//If the player inputs a number into the text field and clicks remove player, it will find the numbered position and remove it
	private void removeTargetedPlayer(int position) {
		playerQueue.dequeueTargeted(position);
		playerQueue.displayNames(playerList);
		playerInput.setText("");
	}
	
	//Method to process key events, if the key pressed was enter, call addPlayer function
	public void displayFieldHandler(KeyEvent evt) {
		if(evt.getCode().equals(KeyCode.ENTER)) {
			addPlayer();
		}
	}
	
	//Method to close current window
	public void closeCurrentWindow(ActionEvent evt) {
		final Node source = (Node) evt.getSource();
		final Stage stage =(Stage)source.getScene().getWindow();
		stage.close();
	}
	
	
}
