//JavaFX Monkey Game
//Umer Ahmad
//December 5, 2018
//This program is a game in which you play as a monkey and try to collect as many bananas as possible, avoiding obstacles
//Program requires Java prerequisites (JDK, java.io, JavaFx, libraries, etc.)

//Importing necessities
package monkeyGame;
import java.io.File;
import java.util.ArrayList;


import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;


/** MonkeyGame class to store all methods and constructor - contains main game loops. */
public class MonkeyGameController {

	//Scene Variables
	Scene gameScene;
	@FXML Canvas gameCanvas;
	GraphicsContext gc;
	
	//Setting up mediaplayers for various sound files
	String soundTrack = "Jungle Game.mp3";    
	Media OST = new Media(new File(soundTrack).toURI().toString());
	MediaPlayer mediaPlayer = new MediaPlayer(OST);
	
	
	String portalNoise = "Portal Noise.mp3";    
	Media soundEffect1 = new Media(new File(portalNoise).toURI().toString());
	MediaPlayer mediaPlayer2 = new MediaPlayer(soundEffect1);
	
	String soundTrack2 = "Space Music.mp3";    
	Media OST2 = new Media(new File(soundTrack2).toURI().toString());
	MediaPlayer mediaPlayer3 = new MediaPlayer(OST2);
	
	String gunShot = "Gunshot.mp3";    
	Media soundEffect2 = new Media(new File(gunShot).toURI().toString());
	MediaPlayer mediaPlayer4 = new MediaPlayer(soundEffect2);
	
	//Statistic variables to track
	int monkeyLives = 3;
	int bananasEaten = 0;
	double totalDistance = 0;
	
	//Booleans to check if actions have been done
	boolean coconutAdded = false;
	boolean randomizedPosition = false;
	
	//Doubles to represent a random location on the map to place powerup, and determine if its been gained
	double randomPowerUpX;
	double randomPowerUpY;
	boolean powerUpGained = false;
	
	//Time variables to track length of powerup
	long timerStop;
	long startTime;
	long duration;
	boolean timerSet = false;
	
	//Teleport variables to banish into space if abused
	int teleportCount = 0;
	boolean tooManyTeleports = false;
	
	//String to manipulate background
	String backgroundImage;
	
	//Booleans of gamemode
	boolean singlePlayer = false;
	boolean multiPlayer = false;
	

	/** Method to grab scene from previous controller.
	 * @param primaryStage A Stage that represents the current window.
	 */
	public void getScene(Stage primaryStage) {
		gameScene = primaryStage.getScene();
	}
	
	/** Method to process menu clicks.
	 * @param evt A ActionEvent variable that is referenced in FXML file for menu clicks.
	 */
		public void menuClickHandler(ActionEvent evt) {
			
			//Assigning menu clicks to variables
			MenuItem clickedMenu = (MenuItem) evt.getTarget();
			String menuLabel = clickedMenu.getText();

		
			//If single player is clicked, reset all variables and clear background, and set single player to true
			if ("Single Player".equals(menuLabel)) {
				singlePlayer = true;
				monkeyLives = 3;
				bananasEaten = 0;
				gc = gameCanvas.getGraphicsContext2D();
				backgroundImage = "images/background.png";
				Image background = new Image(backgroundImage);
				gc.clearRect(0, 0, gameCanvas.getWidth(), gameCanvas.getHeight());
				gc.drawImage(background, 0, 0);
				mediaPlayer3.stop();
				mediaPlayer.play();
				multiPlayer = false;
			}
			
			//If multiplayer is clicked, do same as previous but with multiplayer boolean
			if ("Multiplayer".equals(menuLabel)) {
				multiPlayer = true;
				monkeyLives = 6;
				bananasEaten = 0;
				gc = gameCanvas.getGraphicsContext2D();
				backgroundImage = "images/background.png";
				Image background = new Image(backgroundImage);
				gc.clearRect(0, 0, gameCanvas.getWidth(), gameCanvas.getHeight());
				gc.drawImage(background, 0, 0);
				mediaPlayer3.stop();
				mediaPlayer.play();
				singlePlayer = false;
			}
					
			
			//Exit platform if quit
			if ("Quit".equals(menuLabel)) {
				Platform.exit();
			}
			
		
		}
	
	/** Method of the gameloop which contains majority of the game code.*/
	public void gameLoop() {
		
		//Setting up background and canvas
		gc = gameCanvas.getGraphicsContext2D();
		backgroundImage = "images/background.png";
		Image background = new Image(backgroundImage);
		gc.clearRect(0, 0, gameCanvas.getWidth(), gameCanvas.getHeight());
		gc.drawImage(background, 0, 0);
		
		//Arraylist of coconuts to dynamically add and keep track
		ArrayList<Coconut> coconutList = new ArrayList<Coconut>();
		coconutList.add(new Coconut(gc,gameCanvas));
		
		//Initializing items from the various classses
		Banana banana = new Banana(gc,gameCanvas);
		RottenBanana rottenBanana = new RottenBanana(gc,gameCanvas);
		Monkey monkey = new Monkey(gc,gameCanvas);
		Gorilla gorilla = new Gorilla(gc,gameCanvas);
		Powerup powerUp = new Powerup(gc,gameCanvas);
		Score score = new Score(gc,gameCanvas);
		Hunter hunter = new Hunter(gc,gameCanvas);
		
		PurplePortal purplePortal = new PurplePortal(gc,gameCanvas);
		PurplePortal purplePortal2 = new PurplePortal(gc,gameCanvas);
		GreenPortal greenPortal = new GreenPortal(gc,gameCanvas);
		GreenPortal greenPortal2 = new GreenPortal(gc,gameCanvas);
		
		//Arraylist of inputs to determine where the monkey and gorilla should be placed
		ArrayList<String> input = new ArrayList<String>();
		ArrayList<String> input2 = new ArrayList<String>();
		
		//Play main soundtrack
		mediaPlayer.play();
		
		 //Add every button press to two different arrays, the other one to keep track of 2nd player in multiplayer
		 gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
	            @Override
	            public void handle(KeyEvent e) {
	                String code = e.getCode().toString();
	                if (!input.contains(code)) {
	                    input.add(code);
	                	
	                }
	                
	                if (!input2.contains(code)) {
	                	input2.add(code);
	                }
	                	
	            }		
	        });
		 	
		 	//When key is released, remove input from arraylist, and stop sound effects
	        gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
	            @Override
	            public void handle(KeyEvent e) {
	                String code = e.getCode().toString();
	                if (input.contains(code)) {
	                    input.remove(code);
	                	mediaPlayer2.stop();
	                	mediaPlayer4.stop();
	                
	                }
	                
	                if (input2.contains(code)) {
	                	input2.remove(code);
	                	mediaPlayer2.stop();
	                	mediaPlayer4.stop();
	                }
	            }
	        });

		
		new AnimationTimer() {
			// actual game loop that repeats
			@Override
			public void handle(long currentNanoTime) {
				
				//Single player game mode
				if(singlePlayer == true) {
					
					//Initialize background
					Image background = new Image(backgroundImage);
					
					//If too many teleports have been done, change background and images, and change music
					if (tooManyTeleports == true) {
						backgroundImage = "images/spacebackground.jpg";
						background = new Image(backgroundImage);
						mediaPlayer.stop();
						mediaPlayer3.play();
						if (powerUpGained == false || duration <= 0) {
							monkey.setImageName("images/spacemonkey.png");
						}
						if (powerUpGained == true || duration >0) {
							monkey.setImageName("images/spaceship.gif");
						}
						
						powerUp.setImageName("images/spaceship.gif");
					} 
					
					
					//Clear canvas,and set background image onto it
					gc.clearRect(0, 0, gameCanvas.getWidth(), gameCanvas.getHeight());
					gc.drawImage(background, 0, 0);
					
					//Place and move all items in the game, and set given parameters
					banana.place(banana.getX(),banana.getY());
					rottenBanana.place(rottenBanana.getX(),rottenBanana.getY() );
					monkey.move(input);
					
					purplePortal.setX(); purplePortal.setY(); 
					purplePortal2.setX2(); purplePortal2.setY2(); 
					
					greenPortal.setX(); greenPortal.setY();
					greenPortal2.setX2(); greenPortal2.setY2();
					
					
					//If there haven't been too many teleports, place portals and if a monkey collides with the portal, teleport to corresponding portal, add to teleport count and play portal sound effect
					if (tooManyTeleports == false) {
						purplePortal.place(purplePortal.getX(), purplePortal.getY());
						greenPortal.place(greenPortal.getX(), greenPortal.getY());
						
						purplePortal2.place(purplePortal2.getX2(), purplePortal2.getY2());
						greenPortal2.place(greenPortal2.getX(), greenPortal2.getY());
						
						if (monkey.collision(purplePortal.getX(), purplePortal.getY() ,purplePortal.getWidth(), purplePortal.getHeight()) == true) {
							monkey.setX(purplePortal2.getX() + purplePortal2.getWidth() + 40); 
							monkey.setY(purplePortal2.getY());
							teleportCount += 1;
							mediaPlayer2.play();
						}
						
						if (monkey.collision(purplePortal2.getX(), purplePortal2.getY() ,purplePortal2.getWidth(), purplePortal2.getHeight()) == true) {
							monkey.setX(purplePortal.getX() - purplePortal.getWidth()); 
							monkey.setY(purplePortal.getY());
							teleportCount += 1;
							mediaPlayer2.play();
						}
						
						
						if (monkey.collision(greenPortal.getX(), greenPortal.getY() ,greenPortal.getWidth(), greenPortal.getHeight()) == true) {
							monkey.setX(greenPortal2.getX() + greenPortal2.getWidth() + 40); 
							monkey.setY(greenPortal2.getY());
							teleportCount += 1;
							mediaPlayer2.play();
						}
						
						if (monkey.collision(greenPortal2.getX(), greenPortal2.getY() ,greenPortal2.getWidth(), greenPortal2.getHeight()) == true) {
							monkey.setX(greenPortal.getX() - greenPortal.getWidth()); 
							monkey.setY(greenPortal.getY());
							teleportCount += 1;
							mediaPlayer2.play();
						}
					}
					
					
					//If there have been too many teleports per stage, set toomanyteleports to true
					if (teleportCount == 3) {
						tooManyTeleports = true;
					}
					
					//Update display and variables
					banana.setBananasEaten(bananasEaten);
					monkey.setMonkeyLives(monkeyLives); 
					score.display(monkey, banana, powerUp);
					
					
					//Update total distance variable to display on end game screen
					totalDistance = monkey.getTotalDistance();
					
					
					//Random doubles for respawns on map
					double randomBananaX = (int) (Math.random() * (gameCanvas.getWidth() - (banana.getWidth() * 3)) + banana.getWidth());
					double randomBananaY = (int) (Math.random() * (gameCanvas.getHeight() - (banana.getHeight() *3)) + banana.getHeight());
					
					double randomMonkeyX = (int) (Math.random() * (gameCanvas.getWidth() - (monkey.getWidth() * 3)) + monkey.getWidth());
					double randomMonkeyY = (int) (Math.random() * (gameCanvas.getHeight() - (monkey.getHeight() *3)) + monkey.getHeight());
					
					double randomRottenBananaX = (int) (Math.random() * (gameCanvas.getWidth() - (rottenBanana.getWidth() * 3)) + rottenBanana.getWidth());
					double randomRottenBananaY = (int) (Math.random() * (gameCanvas.getHeight() - (rottenBanana.getHeight() *3)) + rottenBanana.getHeight());
					
					
					//If monkey collied with a banana or rotten banana, add or subtract a point correspondingly, and respawn banana
					if (monkey.collision(banana.getX(), banana.getY() ,banana.getWidth(), banana.getHeight()) == true) {
						banana.setX(randomBananaX);
						banana.setY(randomBananaY);
						bananasEaten += 1;
						banana.setBananasEaten(bananasEaten);
						coconutAdded = false;
						randomizedPosition = false;
						powerUpGained = false;  
					}
					
					if (monkey.collision(rottenBanana.getX(), rottenBanana.getY() ,rottenBanana.getWidth(), rottenBanana.getHeight()) == true) {
						rottenBanana.setX(randomRottenBananaX);
						rottenBanana.setY(randomRottenBananaY);
						bananasEaten -= 2;
						banana.setBananasEaten(bananasEaten);
						coconutAdded = false;
						randomizedPosition = false;
						powerUpGained = false;
					}
					
					//If a banana spawns in a rotten banana respawn
					if (banana.collision(rottenBanana.getX(), rottenBanana.getY() ,rottenBanana.getWidth(), rottenBanana.getHeight()) == true) {
						banana.setX(randomBananaX);
						banana.setY(randomBananaY);   
					}
					
					
					//If portals are still available, and a banana spawns in one, respawn
					if (tooManyTeleports == false)
						if (banana.collision(purplePortal.getX(), purplePortal.getY(), purplePortal.getWidth(), purplePortal.getHeight())) {
							banana.setX(randomRottenBananaX);
							banana.setY(randomRottenBananaY);
						}
						
						if (banana.collision(purplePortal2.getX(), purplePortal2.getY(), purplePortal2.getWidth(), purplePortal2.getHeight())) {
							banana.setX(randomRottenBananaX);
							banana.setY(randomRottenBananaY);
						}
						if (banana.collision(greenPortal.getX(), greenPortal.getY(), greenPortal.getWidth(), greenPortal.getHeight())) {
							banana.setX(randomRottenBananaX);
							banana.setY(randomRottenBananaY);
						}
						if (banana.collision(greenPortal2.getX(), greenPortal2.getY(), greenPortal2.getWidth(), greenPortal2.getHeight())) {
							banana.setX(randomRottenBananaX);
							banana.setY(randomRottenBananaY);
						}
						
						
						if (rottenBanana.collision(purplePortal.getX(), purplePortal.getY(), purplePortal.getWidth(), purplePortal.getHeight())) {
							rottenBanana.setX(randomRottenBananaX);
							rottenBanana.setY(randomRottenBananaY);
						}
						
						if (rottenBanana.collision(purplePortal2.getX(), purplePortal2.getY(), purplePortal2.getWidth(), purplePortal2.getHeight())) {
							rottenBanana.setX(randomRottenBananaX);
							rottenBanana.setY(randomRottenBananaY);
						}
						if (rottenBanana.collision(greenPortal.getX(), greenPortal.getY(), greenPortal.getWidth(), greenPortal.getHeight())) {
							rottenBanana.setX(randomRottenBananaX);
							rottenBanana.setY(randomRottenBananaY);
						}
						if (rottenBanana.collision(greenPortal2.getX(), greenPortal2.getY(), greenPortal2.getWidth(), greenPortal2.getHeight())) {
							rottenBanana.setX(randomRottenBananaX);
							rottenBanana.setY(randomRottenBananaY);
						}
					
				
					//If a powerup has been gained, or the duration of it is still above 0, start timer and new duration of powerup
					if (powerUpGained == true || duration > 0) {
						startTime = System.currentTimeMillis();
						if (timerSet == false && powerUpGained == true) {
							timerStop = startTime + 7000;
							timerSet = true;
						}
						duration = timerStop - startTime;
						powerUp.setPowerUpDuration(duration);
						
						//If duration is exhausted, set speed back to normal
						if (startTime >= timerStop && startTime <= timerStop + 20) {
							monkey.setSpeed(4);
						}
						
					}
					
					//If total score is an interval of 6 up to 30, add a coconut and a powerup, if a powerup is gained - double your speed
					if (bananasEaten == 6 ^ bananasEaten == 12 ^ bananasEaten == 18 ^ bananasEaten == 24 ^ bananasEaten == 30) {
						if (randomizedPosition == false) {
							randomPowerUpX = (int) (Math.random() * (gameCanvas.getWidth() - (powerUp.getWidth() * 3)) + powerUp.getWidth());
							randomPowerUpY = (int) (Math.random() * (gameCanvas.getHeight() - (powerUp.getHeight() *3)) + powerUp.getHeight());
							randomizedPosition = true;
							timerSet = false;
						}
						if (powerUpGained == false) {
							powerUp.place(randomPowerUpX, randomPowerUpY);
						}
						
						if (monkey.collision(randomPowerUpX, randomPowerUpY ,powerUp.getWidth(), powerUp.getHeight()) == true && powerUpGained == false) {
							monkey.setSpeed(8);
							powerUpGained = true;
							   
						}
						if (coconutAdded == false) {
							if(coconutList.size() < 5)
							coconutList.add(new Coconut(gc,gameCanvas));
							coconutAdded = true;
							teleportCount = 0;
						}
					}
					//For all elements in the cocounut array list, move each coconut, and if a monkey collides with any - randomize its position and remove a life
					for (int i = 0; i < coconutList.size(); i++) {
					    coconutList.get(i).move();
					    if (monkey.collision(coconutList.get(i).getX(), coconutList.get(i).getY() ,coconutList.get(i).getWidth(), coconutList.get(i).getHeight()) == true) {
							monkey.setX(randomMonkeyX);
							monkey.setY(randomMonkeyY);
							monkeyLives -= 1;
							monkey.setMonkeyLives(monkeyLives); 
						}
					    
					    //If there have been too many teleports, change image of coconut to asteroid
					    if (tooManyTeleports == true) {
					    	coconutList.get(i).setImageName("images/asteroid.png");
					    }
					}
					
					//If monkeylives is exhausted, reset all variables and open end screen
					if (monkeyLives <= 0) {
						openEnd();
						singlePlayer = false;
						gc.clearRect(0, 0, gameCanvas.getWidth(), gameCanvas.getHeight());
						gc.drawImage(background, 0, 0);
						monkeyLives = 3;
						bananasEaten = 0;
						teleportCount = 0;
						monkey.setTotalX(0);
						monkey.setTotalY(0);
						monkey.setSpeed(4);
						tooManyTeleports = false;
						powerUpGained = false;
						duration = 0;
						powerUp.setPowerUpDuration(duration);
						coconutList.clear();
						coconutList.add(new Coconut(gc,gameCanvas));
						input.clear();
						monkey.setImageName("images/monkey.png");
						powerUp.setImageName("images/powerup.gif");
						banana.setBananasEaten(bananasEaten);
						monkey.setMonkeyLives(monkeyLives);
						for (int i = 0; i < coconutList.size(); i++) {
						    coconutList.get(i).setImageName("images/coconut.png");
						   
						}
					}
					
				}
				
				
				//Multiplayer gamemode - majority of code is similiar to singleplayer, with the exception of an added hunter with a gunshot noise and second player, everything that occurs for monkey, occurs for the gorilla aswell
				if(multiPlayer == true) {
					// clear the whole canvas each frame
					Image background = new Image(backgroundImage);
					if (tooManyTeleports == true) {
						backgroundImage = "images/spacebackground.jpg";
						background = new Image(backgroundImage);
						mediaPlayer.stop();
						mediaPlayer3.play();
						if (powerUpGained == false || duration <= 0) {
							monkey.setImageName("images/spacemonkey.png");
							gorilla.setImageName("images/spacegorilla.gif");
						}
						if (powerUpGained == true || duration >0) {
							monkey.setImageName("images/spaceship.gif");
							gorilla.setImageName("images/spaceship.gif");
						}
						
						powerUp.setImageName("images/spaceship.gif");
					} 
					
					gc.clearRect(0, 0, gameCanvas.getWidth(), gameCanvas.getHeight());
					gc.drawImage(background, 0, 0);
					banana.place(banana.getX(),banana.getY());
					rottenBanana.place(rottenBanana.getX(),rottenBanana.getY() );
					monkey.move(input);
					gorilla.move(input2);
					hunter.move();
		
					
					purplePortal.setX(); purplePortal.setY(); 
					purplePortal2.setX2(); purplePortal2.setY2(); 
					
					greenPortal.setX(); greenPortal.setY();
					greenPortal2.setX2(); greenPortal2.setY2();
					

					
					if (tooManyTeleports == false) {
						purplePortal.place(purplePortal.getX(), purplePortal.getY());
						greenPortal.place(greenPortal.getX(), greenPortal.getY());
						
						purplePortal2.place(purplePortal2.getX2(), purplePortal2.getY2());
						greenPortal2.place(greenPortal2.getX(), greenPortal2.getY());
						
						if (monkey.collision(purplePortal.getX(), purplePortal.getY() ,purplePortal.getWidth(), purplePortal.getHeight()) == true) {
							monkey.setX(purplePortal2.getX() + purplePortal2.getWidth() + 40); 
							monkey.setY(purplePortal2.getY());
							teleportCount += 1;
							mediaPlayer2.play();
						}
						
						if (monkey.collision(purplePortal2.getX(), purplePortal2.getY() ,purplePortal2.getWidth(), purplePortal2.getHeight()) == true) {
							monkey.setX(purplePortal.getX() - purplePortal.getWidth()); 
							monkey.setY(purplePortal.getY());
							teleportCount += 1;
							mediaPlayer2.play();
						}
						
						
						if (monkey.collision(greenPortal.getX(), greenPortal.getY() ,greenPortal.getWidth(), greenPortal.getHeight()) == true) {
							monkey.setX(greenPortal2.getX() + greenPortal2.getWidth() + 40); 
							monkey.setY(greenPortal2.getY());
							teleportCount += 1;
							mediaPlayer2.play();
						}
						
						if (monkey.collision(greenPortal2.getX(), greenPortal2.getY() ,greenPortal2.getWidth(), greenPortal2.getHeight()) == true) {
							monkey.setX(greenPortal.getX() - greenPortal.getWidth()); 
							monkey.setY(greenPortal.getY());
							teleportCount += 1;
							mediaPlayer2.play();
						}
						
						
						
						
						if (gorilla.collision(purplePortal.getX(), purplePortal.getY() ,purplePortal.getWidth(), purplePortal.getHeight()) == true) {
							gorilla.setX(purplePortal2.getX() + purplePortal2.getWidth() + 40); 
							gorilla.setY(purplePortal2.getY());
							teleportCount += 1;
							mediaPlayer2.play();
						}
						
						if (gorilla.collision(purplePortal2.getX(), purplePortal2.getY() ,purplePortal2.getWidth(), purplePortal2.getHeight()) == true) {
							gorilla.setX(purplePortal.getX() - purplePortal.getWidth()); 
							gorilla.setY(purplePortal.getY());
							teleportCount += 1;
							mediaPlayer2.play();
						}
						
						
						if (gorilla.collision(greenPortal.getX(), greenPortal.getY() ,greenPortal.getWidth(), greenPortal.getHeight()) == true) {
							gorilla.setX(greenPortal2.getX() + greenPortal2.getWidth() + 40); 
							gorilla.setY(greenPortal2.getY());
							teleportCount += 1;
							mediaPlayer2.play();
						}
						
						if (gorilla.collision(greenPortal2.getX(), greenPortal2.getY() ,greenPortal2.getWidth(), greenPortal2.getHeight()) == true) {
							gorilla.setX(greenPortal.getX() - greenPortal.getWidth()); 
							gorilla.setY(greenPortal.getY());
							teleportCount += 1;
							mediaPlayer2.play();
						}
						
					}
					
					
					
					if (teleportCount == 3) {
						tooManyTeleports = true;
					}
					
					banana.setBananasEaten(bananasEaten);
					monkey.setMonkeyLives(monkeyLives); 
					score.display(monkey, banana, powerUp);
				
					
					totalDistance = monkey.getTotalDistance() + gorilla.getTotalDistance();
					
					double randomBananaX = (int) (Math.random() * (gameCanvas.getWidth() - (banana.getWidth() * 3)) + banana.getWidth());
					double randomBananaY = (int) (Math.random() * (gameCanvas.getHeight() - (banana.getHeight() *3)) + banana.getHeight());
					
					double randomMonkeyX = (int) (Math.random() * (gameCanvas.getWidth() - (monkey.getWidth() * 3)) + monkey.getWidth());
					double randomMonkeyY = (int) (Math.random() * (gameCanvas.getHeight() - (monkey.getHeight() *3)) + monkey.getHeight());
					
					double randomGorillaX = (int) (Math.random() * (gameCanvas.getWidth() - (gorilla.getWidth() * 3)) + gorilla.getWidth());
					double randomGorillaY = (int) (Math.random() * (gameCanvas.getHeight() - (gorilla.getHeight() *3)) + gorilla.getHeight());
					
					double randomRottenBananaX = (int) (Math.random() * (gameCanvas.getWidth() - (rottenBanana.getWidth() * 3)) + rottenBanana.getWidth());
					double randomRottenBananaY = (int) (Math.random() * (gameCanvas.getHeight() - (rottenBanana.getHeight() *3)) + rottenBanana.getHeight());
					
					
					//If monkey or gorilla is within 300 pixels of the hunter, they get shot - respawn - and lose a life
					if (monkey.getX() > hunter.getX() && monkey.getX() <= hunter.getX() + 300) {
						if (monkey.getY() <= hunter.getY() + 10 && monkey.getY() >= hunter.getY() - 10) {
							monkey.setX(randomMonkeyX);
							monkey.setY(randomMonkeyY);
							monkeyLives -= 1;
							monkey.setMonkeyLives(monkeyLives); 
							mediaPlayer4.play();
						}
					}
					
					if (gorilla.getX() > hunter.getX() && gorilla.getX() <= hunter.getX() + 300) {
						if (gorilla.getY() <= hunter.getY() + 10 && gorilla.getY() >= hunter.getY() - 10) {
							gorilla.setX(randomGorillaX);
							gorilla.setY(randomGorillaY);
							monkeyLives -= 1;
							gorilla.setMonkeyLives(monkeyLives); 
							mediaPlayer4.play();
						}
					}
					
					
					if (monkey.collision(banana.getX(), banana.getY() ,banana.getWidth(), banana.getHeight()) == true) {
						banana.setX(randomBananaX);
						banana.setY(randomBananaY);
						bananasEaten += 1;
						banana.setBananasEaten(bananasEaten);
						coconutAdded = false;
						randomizedPosition = false;
						powerUpGained = false;  
					}
					
					if (monkey.collision(rottenBanana.getX(), rottenBanana.getY() ,rottenBanana.getWidth(), rottenBanana.getHeight()) == true) {
						rottenBanana.setX(randomRottenBananaX);
						rottenBanana.setY(randomRottenBananaY);
						bananasEaten -= 2;
						banana.setBananasEaten(bananasEaten);
						coconutAdded = false;
						randomizedPosition = false;
						powerUpGained = false;
					}
					
					
					if (gorilla.collision(banana.getX(), banana.getY() ,banana.getWidth(), banana.getHeight()) == true) {
						banana.setX(randomBananaX);
						banana.setY(randomBananaY);
						bananasEaten += 1;
						banana.setBananasEaten(bananasEaten);
						coconutAdded = false;
						randomizedPosition = false;
						powerUpGained = false;  
					}
					
					if (gorilla.collision(rottenBanana.getX(), rottenBanana.getY() ,rottenBanana.getWidth(), rottenBanana.getHeight()) == true) {
						rottenBanana.setX(randomRottenBananaX);
						rottenBanana.setY(randomRottenBananaY);
						bananasEaten -= 2;
						banana.setBananasEaten(bananasEaten);
						coconutAdded = false;
						randomizedPosition = false;
						powerUpGained = false;
					}
					
					if (banana.collision(rottenBanana.getX(), rottenBanana.getY() ,rottenBanana.getWidth(), rottenBanana.getHeight()) == true) {
						banana.setX(randomRottenBananaX);
						banana.setY(randomRottenBananaY);   
					}
					
					
					if (tooManyTeleports == false)
						if (banana.collision(purplePortal.getX(), purplePortal.getY(), purplePortal.getWidth(), purplePortal.getHeight())) {
							banana.setX(randomRottenBananaX);
							banana.setY(randomRottenBananaY);
						}
						
						if (banana.collision(purplePortal2.getX(), purplePortal2.getY(), purplePortal2.getWidth(), purplePortal2.getHeight())) {
							banana.setX(randomRottenBananaX);
							banana.setY(randomRottenBananaY);
						}
						if (banana.collision(greenPortal.getX(), greenPortal.getY(), greenPortal.getWidth(), greenPortal.getHeight())) {
							banana.setX(randomRottenBananaX);
							banana.setY(randomRottenBananaY);
						}
						if (banana.collision(greenPortal2.getX(), greenPortal2.getY(), greenPortal2.getWidth(), greenPortal2.getHeight())) {
							banana.setX(randomRottenBananaX);
							banana.setY(randomRottenBananaY);
						}
						
						
						if (rottenBanana.collision(purplePortal.getX(), purplePortal.getY(), purplePortal.getWidth(), purplePortal.getHeight())) {
							rottenBanana.setX(randomRottenBananaX);
							rottenBanana.setY(randomRottenBananaY);
						}
						
						if (rottenBanana.collision(purplePortal2.getX(), purplePortal2.getY(), purplePortal2.getWidth(), purplePortal2.getHeight())) {
							rottenBanana.setX(randomRottenBananaX);
							rottenBanana.setY(randomRottenBananaY);
						}
						if (rottenBanana.collision(greenPortal.getX(), greenPortal.getY(), greenPortal.getWidth(), greenPortal.getHeight())) {
							rottenBanana.setX(randomRottenBananaX);
							rottenBanana.setY(randomRottenBananaY);
						}
						if (rottenBanana.collision(greenPortal2.getX(), greenPortal2.getY(), greenPortal2.getWidth(), greenPortal2.getHeight())) {
							rottenBanana.setX(randomRottenBananaX);
							rottenBanana.setY(randomRottenBananaY);
						}
					
				
	
					if (powerUpGained == true || duration > 0) {
						startTime = System.currentTimeMillis();
						if (timerSet == false && powerUpGained == true) {
							timerStop = startTime + 7000;
							timerSet = true;
						}
						duration = timerStop - startTime;
						powerUp.setPowerUpDuration(duration);
						
						if (startTime >= timerStop && startTime <= timerStop + 20) {
							gorilla.setSpeed(4);
							monkey.setSpeed(4);
						}
						
					}
					
					if (bananasEaten == 6 ^ bananasEaten == 12 ^ bananasEaten == 18 ^ bananasEaten == 24 ^ bananasEaten == 30) {
						if (randomizedPosition == false) {
							randomPowerUpX = (int) (Math.random() * (gameCanvas.getWidth() - (powerUp.getWidth() * 3)) + powerUp.getWidth());
							randomPowerUpY = (int) (Math.random() * (gameCanvas.getHeight() - (powerUp.getHeight() *3)) + powerUp.getHeight());
							randomizedPosition = true;
							timerSet = false;
						}
						if (powerUpGained == false) {
							powerUp.place(randomPowerUpX, randomPowerUpY);
						}
						
						if (monkey.collision(randomPowerUpX, randomPowerUpY ,powerUp.getWidth(), powerUp.getHeight()) == true && powerUpGained == false) {
							monkey.setSpeed(8);
							gorilla.setSpeed(8);
							powerUpGained = true;
							   
						}
						
						if (gorilla.collision(randomPowerUpX, randomPowerUpY ,powerUp.getWidth(), powerUp.getHeight()) == true && powerUpGained == false) {
							monkey.setSpeed(8);
							gorilla.setSpeed(8);
							powerUpGained = true;
							   
						}
						if (coconutAdded == false) {
							if(coconutList.size() < 5)
							coconutList.add(new Coconut(gc,gameCanvas));
							coconutAdded = true;
							teleportCount = 0;
						}
					}
					
					for (int i = 0; i < coconutList.size(); i++) {
					    coconutList.get(i).move();
					    if (monkey.collision(coconutList.get(i).getX(), coconutList.get(i).getY() ,coconutList.get(i).getWidth(), coconutList.get(i).getHeight()) == true) {
							monkey.setX(randomMonkeyX);
							monkey.setY(randomMonkeyY);
							monkeyLives -= 1;
							monkey.setMonkeyLives(monkeyLives); 
						}
					    
					    if (gorilla.collision(coconutList.get(i).getX(), coconutList.get(i).getY() ,coconutList.get(i).getWidth(), coconutList.get(i).getHeight()) == true) {
							gorilla.setX(randomGorillaX);
							gorilla.setY(randomGorillaY);
							monkeyLives -= 1;
							monkey.setMonkeyLives(monkeyLives); 
						}
					    
					    if (tooManyTeleports == true) {
					    	coconutList.get(i).setImageName("images/asteroid.png");
					    }
					}
					
					if (monkeyLives <= 0) {
						openEnd();
						multiPlayer = false;
						gc.clearRect(0, 0, gameCanvas.getWidth(), gameCanvas.getHeight());
						gc.drawImage(background, 0, 0);
						monkeyLives = 6;
						bananasEaten = 0;
						teleportCount = 0;
						monkey.setTotalX(0);
						monkey.setTotalY(0);
						monkey.setSpeed(4);
						gorilla.setTotalX(0);
						gorilla.setTotalY(0);
						gorilla.setSpeed(4);
						hunter.setX(0);
						tooManyTeleports = false;
						powerUpGained = false;
						duration = 0;
						powerUp.setPowerUpDuration(duration);
						coconutList.clear();
						coconutList.add(new Coconut(gc,gameCanvas));
						input.clear();
						input2.clear();
						monkey.setImageName("images/monkey.png");
						gorilla.setImageName("images/gorilla.gif");
						powerUp.setImageName("images/powerup.gif");
						banana.setBananasEaten(bananasEaten);
						monkey.setMonkeyLives(monkeyLives);
						for (int i = 0; i < coconutList.size(); i++) {
						    coconutList.get(i).setImageName("images/coconut.png");
						   
						}
					}
					
				}
            }
		}.start();	
		
		
		
		
		
		
		
	}
	
	//Method to open end screen
	private void openEnd() {
		try {
			//Create a new scene under the end.fxml guidance, and apply css
			FXMLLoader loader = new FXMLLoader(getClass().getResource("End.fxml"));
			BorderPane endGameRoot = (BorderPane)loader.load();
			Scene endGameScene = new Scene(endGameRoot,804,609);
			endGameScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());	
			
			
			//Send bananas eaten and total distance travelled to next controller
			EndScreenController currentController = (loader.getController());
			currentController.getScoreAndDistance(bananasEaten, totalDistance);
			
			//Initialize stage and show 
			Stage endGameStage = new Stage();
			endGameStage.setScene(endGameScene);
			endGameStage.setScene(endGameScene);
			endGameStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}

	}
	
}
