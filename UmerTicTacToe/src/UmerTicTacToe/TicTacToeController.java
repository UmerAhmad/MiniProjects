//JavaFX Tic Tac Toe
//Umer Ahmad
//November 9, 2018
//This program is one in which a game of tic tac toe is displayed along with extras
//Program requires Java prerequisites (JDK, java.io, JavaFx, libraries, etc.)


//Necessary imports to construct the tic tac toe game
package UmerTicTacToe;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

//Main class in which all methods reside
public class TicTacToeController {
	
	//Setting up secondary stage for popup, and various boolean, FXML, and integer variables for global manipulation
	static Stage secondaryStage;
	private boolean isFirstPlayer = true;
	@FXML Button b1;
	@FXML Button b2;
	@FXML Button b3;
	@FXML Button b4;
	@FXML Button b5;
	@FXML Button b6;
	@FXML Button b7;
	@FXML Button b8;
	@FXML Button b9;
	
	@FXML private Label defaultLabelX;
	@FXML private Label easyLabelX;
	@FXML private Label hardLabelX;
	@FXML private Label defaultLabelO;
	@FXML private Label easyLabelO;
	@FXML private Label hardLabelO;
	@FXML private Label outcomeLabel;
	
	@FXML GridPane gameBoard;
	
	boolean play1v1 = false;
	boolean playComputerEasy = false;
	boolean playComputerHard = false;
	
	int easyScoreX = 0;
	int easyScoreO = 0;
	
	int defaultScoreX = 0;
	int defaultScoreO = 0;
	
	int hardScoreX = 0;
	int hardScoreO = 0;
	
	
	//Main method that processes button clicks
	public void buttonClickHandler(ActionEvent evt) {
		
		//Setting the button click to a variable, and creating an array of buttons for ease of use
		Button clickedButton = (Button)evt.getTarget();
		String buttonLabel = clickedButton.getText();
		Button buttonArray[] = {b1, b2, b3, b4, b5, b6, b7, b8,b9};
		
		//If the 1v1 option was selected, play in an alternating manner against you and an opponent
		if (play1v1 == true) {
			//If there was a three in row, falsify all options
			if (find3InARow() == true) {
				play1v1 = false;
				playComputerEasy = false;
				playComputerHard = false;
			}
			if ("".equals(buttonLabel) && isFirstPlayer) {
				clickedButton.setText("X");
				clickedButton.setStyle("-fx-text-fill: #FF7700");
				isFirstPlayer = false;
			} else if("".equals(buttonLabel) && !isFirstPlayer && find3InARow() == false) {
				clickedButton.setText("O");
				clickedButton.setStyle("-fx-text-fill: #FAEAD0");
				isFirstPlayer = true;
			}
			//Calling 3 in a row to determine if there is a winner after every button click
			find3InARow();
		}
		
		//If statement for computer easy difficulty
		if (playComputerEasy == true) {
			if (find3InARow() == true) {
				play1v1 = false;
				playComputerEasy = false;
				playComputerHard = false;
			}
			if (isFirstPlayer == true){
				if ("".equals(buttonLabel)) {
					clickedButton.setText("X");
					clickedButton.setStyle("-fx-text-fill: #FF7700");
					isFirstPlayer = false;
					}
			}
			//If it isn't first players turn, and there hasn't been a 3 in a row, find a random spot on the board and click it with 'o'
			while (isFirstPlayer == false && find3InARow() == false) {
				Button randomElement = buttonArray[(int)(Math.random()*9)];
				if (randomElement.getText() == "") {
					randomElement.setText("O");
					randomElement.setStyle("-fx-text-fill: #FAEAD0");
					isFirstPlayer = true;
				}
				//If the grid is full of answers, break the while loop
				if(buttonArrayIsFull(buttonArray) == true) {
					break;
				}
			}
			find3InARow();
		}
		
		//If statement for hard difficulty 
		if (playComputerHard == true) {
			if (find3InARow() == true) {
				play1v1 = false;
				playComputerEasy = false;
				playComputerHard = false;
			}
			
			//If first players turn, and the spot was empty when clicked, fill it with X and style it
			if (isFirstPlayer == true){
				if ("".equals(buttonLabel)) {
					clickedButton.setText("X");
					clickedButton.setStyle("-fx-text-fill: #FF7700");
					isFirstPlayer = false;
					}
			}
			//If it isn't first players turn, and there hasn't been 3 in a row, call function hardComputerMove()
			if (isFirstPlayer == false  && find3InARow() == false) {
				hardComputerMove();
				isFirstPlayer = true;
				}
			find3InARow();
			}	
		
		//For every button click, call outcomePredictor, to predict outcome
		outcomePredictor();
		
		
	}

	
	//Method to find 3 in a row
	private boolean find3InARow(){
		
		//Various button arrays, and a cumulative array of arrays to make the method more efficient
		Button row1[] = {b1,b2,b3}; Button row2[] = {b4,b5,b6}; Button row3[] = {b7,b8,b9};
		Button column1[] = {b1,b4,b7}; Button column2[] = {b2,b5,b8}; Button column3[] = {b3,b6,b9};
		Button diagonal1[] = {b1,b5,b9}; Button diagonal2[] = {b3,b5,b7}; 
		Button[][] gridConditions = {row1,row2,row3,column1,column2,column3,diagonal1,diagonal2};
		
		//If any combination of all the arrays are true, highlight them and determine a winner
		for (int i = 0; i < gridConditions.length; i++) {
			if (!gridConditions[i][0].getText().equals("") && gridConditions[i][0].getText() == gridConditions[i][1].getText() && gridConditions[i][0].getText()  == gridConditions[i][2].getText()) {
				highlightWinningCombo(gridConditions[i][0], gridConditions[i][1], gridConditions[i][2]);		
				
				
				//Various if statements for label and score manipulation
				if (play1v1 == true) {
					if (gridConditions[i][0].getText().equals("X")) {
						defaultScoreX += 1;
						defaultLabelX.setText("X: " + defaultScoreX);
						defaultLabelX.setStyle("-fx-text-fill: #FF7700");
						play1v1 = false;
						return true;
					}
					if (gridConditions[i][0].getText().equals("O")) {
						defaultScoreO += 1;
						defaultLabelO.setText("O: " + defaultScoreO);
						defaultLabelO.setStyle("-fx-text-fill: #FAEAD0");
						play1v1 = false;
						return true;
					}
				}
				if (playComputerEasy == true) {
					if (gridConditions[i][0].getText().equals("X")) {
						easyScoreX += 1;
						easyLabelX.setText("X: " + easyScoreX);
						easyLabelX.setStyle("-fx-text-fill: #FF7700");
						playComputerEasy = false;
						return true;
					}
					if (gridConditions[i][0].getText().equals("O")) {
						easyScoreO += 1;
						easyLabelO.setText("O: " + easyScoreO);
						easyLabelO.setStyle("-fx-text-fill: #FAEAD0");
						playComputerEasy = false;
						return true;
					}
				}
				if (playComputerHard == true) {
					if (gridConditions[i][0].getText().equals("X")) {
						hardScoreX += 1;
						hardLabelX.setText("X: " + hardScoreX);
						hardLabelX.setStyle("-fx-text-fill: #FF7700");
						playComputerHard = false;
						return true;
					}
					if (gridConditions[i][0].getText().equals("O")) {
						hardScoreO += 1;
						hardLabelO.setText("X: " + hardScoreO);
						hardLabelO.setStyle("-fx-text-fill: #FAEAD0");
						playComputerHard = false;
						return true;
					}
				}
				
			}
			
		}
	
		//If there was no winner, return false
		return false;
	}
	
	//Method to highlight the specified buttons
	private void highlightWinningCombo(Button first, Button second, Button third) {
		first.getStyleClass().add("winning-button");
		second.getStyleClass().add("winning-button");
		third.getStyleClass().add("winning-button");
	}
	
	//Method to handle all menu clicks
	public void menuClickHandler(ActionEvent evt) {
		
		//Assigning menu clicks to variables
		MenuItem clickedMenu = (MenuItem) evt.getTarget();
		String menuLabel = clickedMenu.getText();

		//If any of the menu clicks were on the play options, falsify any current game, and reset the board
		if ("Play 1v1".equals(menuLabel) || "Play Computer Easy".equals(menuLabel) || "Play Computer Hard".equals(menuLabel)) {
			play1v1 = false;
			playComputerEasy = false;
			playComputerHard = false;
			isFirstPlayer = true;
			ObservableList<Node> buttons = gameBoard.getChildren();
			buttons.forEach(btn -> {
				((Button) btn).setText("");
				btn.getStyleClass().remove("winning-button");
				btn.getStyleClass().remove("winning-button");
				btn.getStyleClass().remove("winning-button");
				btn.getStyleClass().remove("winning-button");
			});
			
			outcomeLabel.setText("N/A");
			outcomeLabel.setStyle("-fx-text-fill: #88A9BA");
		}
		
		
		//If statements to start a game for corresponding mode
		if ("Play 1v1".equals(menuLabel)) {
			play1v1 = true;
		}
		
		if ("Play Computer Easy".equals(menuLabel)) {
			playComputerEasy = true;
		}
		
		
		if ("Play Computer Hard".equals(menuLabel)) {
			playComputerHard = true;
		}
		
		//Reset all global variables in regards to score keeping
		if ("Reset Scores".equals(menuLabel)) {
			defaultScoreX = 0;
			defaultScoreO = 0;
			easyScoreX = 0;
			easyScoreO = 0;
			hardScoreX = 0;
			hardScoreO = 0;
			defaultLabelX.setText("X: " + defaultScoreX); defaultLabelO.setText("O: " + defaultScoreO);
			easyLabelX.setText("X: " + easyScoreX); easyLabelO.setText("O: " + easyScoreO);
			hardLabelX.setText("X: " + hardScoreX); hardLabelO.setText("O: " + hardScoreO);
		
		}
		
		
		//Exit platform if quit
		if ("Quit".equals(menuLabel)) {
			Platform.exit();
		}
		
		//Open corresponding windows for about or how to play
		if ("About".equals(menuLabel)) {
			openAboutWindow();
		}
		
		if ("How To Play".equals(menuLabel)) {
			openHowToWindow();
		}
	}
	
	//Method to open how to play
	private void openHowToWindow() {
		try {
			//Loading the pop up that was created
			Pane howTo = (Pane)FXMLLoader.load(getClass().getResource("howToPlay.fxml"));
					
			//Creating a new scene
			Scene howToScene = new Scene(howTo,301,372);

			howToScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			//Creating new stage to utilize scene
			secondaryStage = new Stage();
			secondaryStage.setScene(howToScene);
			secondaryStage.setResizable(false);
			secondaryStage.showAndWait();
			} catch(Exception e) {
				e.printStackTrace();
			}
	}
	
	//Method to open about, exact same as previous but with different scenes
	private void openAboutWindow() {
		try {
			Pane about = (Pane)FXMLLoader.load(getClass().getResource("about.fxml"));
			Scene aboutScene = new Scene(about,301,372);

			aboutScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			secondaryStage = new Stage();
			secondaryStage.setScene(aboutScene);
			secondaryStage.setResizable(false);
			secondaryStage.showAndWait();
			} catch(Exception e) {
				e.printStackTrace();
			}
	}
	
	//Method to close the pop up windows if requested
	public void closeWindowButtonClickHandler (ActionEvent evt) {
		secondaryStage.close();
	}
	
	//Method that was called upon to check if the grid is full of plays
	public boolean buttonArrayIsFull (Button buttonArray[]) {
		for (int i = 0; i<buttonArray.length; i++) {
			if (buttonArray[i].getText().equals("")) {
				return false;
			}
		}
		return true;
	}
	
	
	//Method to determine hard computers move
	private void hardComputerMove() {
	
		//Similar utilization of the button arrays to the find 3 in a row
		Button buttonArray[] = {b1, b2, b3, b4, b5, b6, b7, b8,b9}; 
		Button row1[] = {b1,b2,b3}; Button row2[] = {b4,b5,b6}; Button row3[] = {b7,b8,b9};
		Button column1[] = {b1,b4,b7}; Button column2[] = {b2,b5,b8}; Button column3[] = {b3,b6,b9};
		Button diagonal1[] = {b1,b5,b9}; Button diagonal2[] = {b3,b5,b7}; 
		
		Button[][] gridConditions = {row1,row2,row3,column1,column2,column3,diagonal1,diagonal2};
		
		//Variable to determine if in the main loop something was done
		boolean moveOver = false;
		
		//For loop to check every possibility of 2 in a row, and block it or complete it. Styling is also done simultaneously.
		for (int i = 0; i < gridConditions.length; i++) {
			if (!gridConditions[i][0].getText().equals("") && gridConditions[i][0].getText() == gridConditions[i][1].getText()) {
				if (!gridConditions[i][2].getText().equals("O") && !gridConditions[i][2].getText().equals("X")) {
					gridConditions[i][2].setText("O");
					gridConditions[i][2].setStyle("-fx-text-fill: #FAEAD0");
					moveOver = true;
				}
				break;				
			}
				
			if (!gridConditions[i][1].getText().equals("") && gridConditions[i][1].getText() == gridConditions[i][2].getText()) {
				if (!gridConditions[i][0].getText().equals("O") && !gridConditions[i][0].getText().equals("X")) {
					gridConditions[i][0].setText("O");
					gridConditions[i][0].setStyle("-fx-text-fill: #FAEAD0");
					moveOver = true;
				}
				break;
			}
				
			if (!gridConditions[i][0].getText().equals("") && gridConditions[i][0].getText() == gridConditions[i][2].getText()) {
				if (!gridConditions[i][1].getText().equals("O") && !gridConditions[i][1].getText().equals("X")) {
					gridConditions[i][1].setText("O");
					gridConditions[i][1].setStyle("-fx-text-fill: #FAEAD0");
					moveOver = true;
				}
				break;
			}
				
		}
		
		//If no move was done in the main for loop, then just pick a random spot
		while (moveOver == false) {
			Button randomElement = buttonArray[(int)(Math.random()*9)];
			if (randomElement.getText() == "") {
				randomElement.setText("O");
				randomElement.setStyle("-fx-text-fill: #FAEAD0");
				moveOver = true;
			}
			if(buttonArrayIsFull(buttonArray) == true) {
				break;
			}
		}
		
	}
	
	//Method to determine and display a predicted outcome
	private void outcomePredictor() {
	
		//Similar utilization of the various button arrays
		Button row1[] = {b1,b2,b3}; Button row2[] = {b4,b5,b6}; Button row3[] = {b7,b8,b9};
		Button column1[] = {b1,b4,b7}; Button column2[] = {b2,b5,b8}; Button column3[] = {b3,b6,b9};
		Button diagonal1[] = {b1,b5,b9}; Button diagonal2[] = {b3,b5,b7}; 
		
		Button[][] gridConditions = {row1,row2,row3,column1,column2,column3,diagonal1,diagonal2};
		
		//Similar to previous method, if any combination of two in a row is found, find them more likely to win. Also set the label and style it as well.
		for (int i = 0; i < gridConditions.length; i++) {
			if (!gridConditions[i][0].getText().equals("") && gridConditions[i][0].getText() == gridConditions[i][1].getText()) {
				if (gridConditions[i][0].getText() == "X") {
					outcomeLabel.setText("X");
					outcomeLabel.setStyle("-fx-text-fill: #FF7700");
				}
				if (gridConditions[i][0].getText() == "O") {
					outcomeLabel.setText("O");
					outcomeLabel.setStyle("-fx-text-fill: #FAEAD0");
				}
			break;				
			}
				
			if (!gridConditions[i][1].getText().equals("") && gridConditions[i][1].getText() == gridConditions[i][2].getText()) {
				if (gridConditions[i][1].getText() == "X") {
					outcomeLabel.setText("X");
					outcomeLabel.setStyle("-fx-text-fill: #FF7700");
				}
				if (gridConditions[i][1].getText() == "O") {
					outcomeLabel.setText("O");
					outcomeLabel.setStyle("-fx-text-fill: #FAEAD0");
				}
			break;
			}
				
			if (!gridConditions[i][0].getText().equals("") && gridConditions[i][0].getText() == gridConditions[i][2].getText()) {
				if (gridConditions[i][2].getText() == "X") {
					outcomeLabel.setText("X");
					outcomeLabel.setStyle("-fx-text-fill: #FF7700");
				}
				if (gridConditions[i][2].getText() == "O") {
					outcomeLabel.setText("O");
					outcomeLabel.setStyle("-fx-text-fill: #FAEAD0");
				}
				break;
			}
				
		}
	}
}






