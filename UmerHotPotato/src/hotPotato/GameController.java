//JavaFX Hot Potato
//Umer Ahmad
//December 1, 2018
//This program is a game that simulates the popular hot potato game
//Program requires Java prerequisites (JDK, java.io, JavaFx, libraries, etc.)

//Importing necessities
package hotPotato;
import stacksAndQueues.Queue;
import stacksAndQueues.Stack;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import java.util.ArrayList;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;


//Main Class GameController, only to be accessed once the player names have been entered
public class GameController {
	
	//Various variables, including booleans, FXMLs, queues/stacks, arraylist, and music to manipulate throughout the code
	boolean playerData = false;
	boolean game = false;
	
	@FXML private TextArea currentPlayers;
	@FXML private TextArea outPlayers;
	@FXML private Label potatoCarrier;
	
	Queue<String> playerQueue = new Queue<String>();
	Stack<String> playerStack = new Stack<String>();
	
	String musicFile = "Hot Potato.mp3";    
	Media sound = new Media(new File(musicFile).toURI().toString());
	MediaPlayer mediaPlayer = new MediaPlayer(sound);
	ArrayList<String> elementList = new ArrayList<String>();
	
	
	//Method to process button clicks
	public void buttonClickHandler(ActionEvent evt) {
			//Setting the button click to a variable, and creating an array of buttons for ease of use
			Button clickedButton = (Button)evt.getTarget();
			String buttonLabel = clickedButton.getText();
			
			//If the button click is start, game hasn't started, queue isn't empty or 1 remaining, set game to true and call game method
			if (buttonLabel.equals("Start") && game == false) {
				if (playerQueue.getSize() != 1 && playerQueue.getSize() != 0) {
					game = true;
					playerQueue.displayNames(currentPlayers); //Calling displayNames method in queue class, displays names on a given text field
					game();
				//If the conditions are none of the aforementioned, open ending screen
				} else {
					openEnd();
				}
			}
			
			//If the button click play again, and there is one remaining winner, clear text areas and stacks, and fill queue with elements from arraylist
			if (buttonLabel.equals("Play Again")) {
				if (playerQueue.getSize() == 1) {
					outPlayers.setText("");
					playerStack.emptyStack();
					for (int i = 0; i < elementList.size(); i++){
						playerQueue.enqueue(elementList.get(i));
					}
					elementList.clear();
					game();
			}
		}
	}
	

	//Method to retrieve queue from previous controller
	public void getQueue(Queue<String> queue) {
		playerQueue = queue;
	
	}
	
	//Method of the main game, where the majority of work is and contains continuous GUI updates
	public void game() {
		
		//Display all names on the current players list, start the music, and change colour of potato carrier label
		playerQueue.displayNames(currentPlayers);
		mediaPlayer.play();
		potatoCarrier.setStyle("-fx-text-fill: #FFFFFF");
		
		//Creating a void task to loop around
		Task <Void> task = new Task <Void> () {
			@Override
			public Void call() throws Exception {
				//Create a random amount of loops between 10 and 25, and a counter to keep track
				int randomNumber = (int) (Math.random() * 30 + 15);
				int loopCounter = 0;
				//Continuous while loop until broken
				while (true) {
					try {
						//Multithreading delay of 300 milliseconds to update ui
						Thread.sleep(325);
					} catch (InterruptedException exc) {
						throw new Error("Unexpected Interruption!");
					}
					
					//Assign the dequeued name to a string variable 
					String potatoHolder = playerQueue.dequeue();
					
					//Making the label manipulation in a FX application thread
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							potatoCarrier.setText(potatoHolder);
						}
					});
					
					//Add to the loop counter
					loopCounter++;
					
					//If the loop counter hasn't fulfilled the amount of designated loops, add on to the queue
					if (loopCounter != randomNumber) {
						playerQueue.enqueue(potatoHolder);
					//Otherwise, back up the element to the arraylist, set label to empty, falsify game boolean
					} else {
						elementList.add(potatoHolder);
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								potatoCarrier.setText("");
								game = false;
							}
						});
						//Push the element into the stack to be displayed as a player out
						playerStack.push(potatoHolder);
						playerStack.displayNamesS(outPlayers);
						break; 
					}
				}
				//Update current players display, and stop the music
				playerQueue.displayNames(currentPlayers);
				mediaPlayer.stop();
				return null;
				}
			};
			//Start task over again
			new Thread(task).start();
		}
	

	//Method acting as a gateway to change controllers to end screen
	private void openEnd() {
		try {
			//Create a new scene under the end.fxml guidance, and apply rankings.css
			FXMLLoader loader = new FXMLLoader(getClass().getResource("end.fxml"));
			BorderPane hotPotatoRoot = (BorderPane)loader.load();
			Scene hotPotatoScene = new Scene(hotPotatoRoot,550,540);
			hotPotatoScene.getStylesheets().add(getClass().getResource("rankings.css").toExternalForm());
			
			//Send queue and stack to be retrieved in the next controller
			EndScreenController currentController = (loader.getController());
			currentController.getStackAndQueue(playerQueue,playerStack);
			
			//Initialize stage and show 
			Stage hotPotatoStage = new Stage();
			hotPotatoStage.setScene(hotPotatoScene);
			hotPotatoStage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}
	}

	
	//Method to close current window
	public void closeCurrentWindow(ActionEvent evt) {
		final Node source = (Node) evt.getSource();
		final Stage stage =(Stage)source.getScene().getWindow();
		stage.close();
	}
	


}
