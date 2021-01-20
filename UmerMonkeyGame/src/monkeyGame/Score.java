//JavaFX Monkey Game
//Umer Ahmad
//December 5, 2018
//This program is a game in which you play as a monkey and try to collect as many bananas as possible, avoiding obstacles
//Program requires Java prerequisites (JDK, java.io, JavaFx, libraries, etc.)

//Importing necessities
package monkeyGame;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/** Score class to store all methods and constructor. */
public class Score {
	
	// declare fields (i.e. variables)
	
	//GraphicsContext variable that allows us to refer to gfx.
    GraphicsContext gc;
    //FXML id of gameCanvas to draw on. 
    @FXML Canvas gameCanvas;
    
    
    /** Score constructor that initializes canvas and graphics context without an image name.
	 * @param gc A graphics context variable to refer for gfx.
	 * @param canvas A FXML variable to represent the canvas to be drawed on.
	 */
    public Score(GraphicsContext gc, Canvas canvas) {
        super();
        this.gc = gc;
        this.gameCanvas = canvas;
    }

    
    /** Method to display paramatered statistics onto the screen.
	 * @param monkey A monkey variable to reference monkey class to grab monkey lives.
	 * @param banana A banana variable to reference banana class to grab bananas eaten.
	 * @param powerUp A powerup variable to reference powerup class to grab powerup duration.
	 */
	public void display(Monkey monkey, Banana banana, Powerup powerUp){
		
	    String scoreStr = "Score: "+ banana.getBananasEaten();
	    gc.setFont(Font.font("ComicSansMS",FontWeight.BOLD,36));
	    gc.setFill(Color.RED);
	    gc.fillText(scoreStr, 20, 50);
	        
	    String livesStr = "Lives: " + monkey.getMonkeyLives();
	    gc.setFill(Color.RED);
	    gc.fillText(livesStr, 200, 50);
	    
	    //If the duration of a powerup is greater than 0, display length and countdown
	    if (powerUp.getPowerUpDuration() > 0) {
		    String durationStr = "Powerup Duration: " + powerUp.getPowerUpDuration() / 1000;
		    gc.setFill(Color.RED);
		    gc.fillText(durationStr, 500, 50);
	    }
	}


}
