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
import javafx.scene.image.Image;

/** Powerup class to store all getters and setters. */
public class Powerup {    
    
    // declare fields (i.e. variables)
	
	// Global double x value (co-ordinate on x-axis).*/
    double x;
    // Global double y value (co-ordinate on y-axis).*/
	double y;
	// Global long duration value (duration of powerup).*/
	long duration;
	
	// Global String of image name (to insert into image). */
    String imageName = "images/powerup.gif";
    // Image variable that makes use of image name, to display onto canvas.*/
    Image image = new Image(imageName);
    
    // GraphicsContext variable that allows us to refer to gfx.*/
    GraphicsContext gc;
    // FXML id of gameCanvas to draw on. */
    @FXML Canvas gameCanvas;

    
    // contructors -----------------------------------------------
    
    /** Powerup constructor that initializes canvas and graphics context without an image name
	 * @param gc A graphics context variable to refer for gfx.
	 * @param canvas A FXML variable to represent the canvas to be drawed on.
	 */
    public Powerup(GraphicsContext gc, Canvas canvas) {
        super();
        this.gc = gc;
        this.gameCanvas = canvas;
        // can put code for random x and y here
        // now that we have a reference to our gc and gameCanvas
    }

    /** Powerup constructor that initializes canvas and graphics context without an image name
	 * @param gc A graphics context variable to refer for gfx.
	 * @param canvas A FXML variable to represent the canvas to be drawed on.
	 * @param imageName A string value to dynamically change image of object.
	 */
    public Powerup(String imageName, GraphicsContext gc, Canvas canvas) {
        super();
        this.imageName = imageName;
        this.gc = gc;
        this.gameCanvas = canvas;
    }

    // all the getters and setters ----------------------------------

    
    /** Gets the X value of the Powerup.
	 * @return A double representing the X co-ordinate.
	 */
    public double getX() {
        return x;
    }

    /** Sets the X value to the parametered double.
	 * @param x A double representing the X co-ordinate.
	 */
    public void setX(double x) {
        this.x = x;
    }

    /** Gets the Y value of the Powerup.
	 * @return A double representing the Y co-ordinate.
	 */
    public double getY() {
        return y;
    }

    /** Sets the Y value to the parametered double.
	 * @param y A double representing the Y co-ordinate.
	 */
    public void setY(double y) {
        this.y = y;
    }


    /** Gets the image name of the Powerup.
	 * @return A string representing the file of reference to the image of the Powerup.
	 */
    public String getImageName() {
        return imageName;
    }

    /** Sets the image name of the Powerup to the parameterd string.
	 * @param imageName A string representing the file of reference to the image of the Powerup.
	 */
    public void setImageName(String imageName) {
        this.imageName = imageName;
        image = new Image(imageName);
    }
    
    /** Gets the width of the Powerup image.
	 * @return A double representing the Powerup image width.
	 */
    public double getWidth() {
        return image.getWidth();
    }
    
    /** Gets the height of the Powerup image.
   	 * @return A double representing the Powerup image height.
   	 */
    public double getHeight() {
        return image.getHeight();
    }
    
    /** Gets the number of seconds remaining on the power up buff.
   	 * @return A double representing the duration of power up.
   	 */
    public long getPowerUpDuration() {
        return duration;
    }

    /** Sets the number of seconds remaining on the power up buff.
   	 * @param duration2 A double representing the duration of power up.
   	 */
    public void setPowerUpDuration(long duration2) {
        duration = duration2;
    }

    
    /** Method to place Powerup on the parametered X and Y position.
   	 * @param randomX A double representing a x position - most likely random, to place the Powerup at.
   	 * @param randomY A double representing a y position - most likely random, to place the Powerup at
   	 */
    public void place(double randomX, double randomY) {
        this.gc.drawImage(this.image, randomX, randomY);
    
    }

	
}
