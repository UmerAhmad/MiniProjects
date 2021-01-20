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

/** Banana class to store all getters and setters. */
public class Banana {    
    
    // declare fields (i.e. variables)
	
	// Global double randomized x value (co-ordinate on x-axis).*/
    double  x = (int) (Math.random() * 700 + 150);
    // Global double randomized y value (co-ordinate on y-axis).*/
	double y = (int) (Math.random() * 400 + 140);
	// Global integer of bananas eaten (to keep track and display it later). */
	int bananasEaten = 0;
	
	// Global String of image name (to insert into image). */
    String imageName = "images/Banana.png";
    
    // Image variable that makes use of image name, to display onto canvas.*/
    Image image = new Image(imageName);
    
    // GraphicsContext variable that allows us to refer to gfx.*/
    GraphicsContext gc;
 
    // FXML id of gameCanvas to draw on. */
    @FXML Canvas gameCanvas;

    
    // contructors -----------------------------------------------
    
    /** Banana constructor that initializes canvas and graphics context without an image name
	 * @param gc A graphics context variable to refer for gfx.
	 * @param canvas A FXML variable to represent the canvas to be drawed on.
	 */
    public Banana(GraphicsContext gc, Canvas canvas) {
        super();
        this.gc = gc;
        this.gameCanvas = canvas;

    }
    
    /** Banana constructor that initializes canvas and graphics context without an image name
	 * @param gc A graphics context variable to refer for gfx.
	 * @param canvas A FXML variable to represent the canvas to be drawed on.
	 * @param imageName A string value to dynamically change image of object.
	 */
    public Banana(String imageName, GraphicsContext gc, Canvas canvas) {
        super();
        this.imageName = imageName;
        this.gc = gc;
        this.gameCanvas = canvas;
    }

    
    // all the getters and setters ----------------------------------
    
    /** Gets the X value of the Banana.
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
    
    /** Gets the Y value of the Banana.
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

    /** Gets the image name of the Banana.
	 * @return A string representing the file of reference to the image of the Banana.
	 */
    public String getImageName() {
        return imageName;
    }
    
    /** Sets the image name of the Banana to the parameterd string.
	 * @param imageName A string representing the file of reference to the image of the Banana.
	 */
    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
    
    /** Gets the width of the Banana image.
	 * @return A double representing the Banana image width.
	 */
    public double getWidth() {
        return image.getWidth();
    }
    
    
    /** Gets the height of the Banana image.
   	 * @return A double representing the Banana image height.
   	 */
    public double getHeight() {
        return image.getHeight();
    }
    
    
    /** Gets the number of banana's eaten, to be referenced in the score class for updated display and stats.
   	 * @return A double representing the number of bananas eaten.
   	 */
    public int getBananasEaten() {
        return bananasEaten;
    }
    
    /** Sets the number of banana's eaten.
   	 * @param bananasEaten2 A integer representing the number of banana's eaten, to be later referenced in another class.
   	 */
    public void setBananasEaten(int bananasEaten2) {
        bananasEaten = bananasEaten2;
    }
    
    
    
    /** Collision method to detect if this object collides with another.
	 * @param otherX A double representing another items X value.
	 * @param otherY A double representing another items Y value.
	 * @param otherWidth A double representing another items width value.
	 * @param otherHeight A double representing another items height value.
	 * @return Returns true if it collides, else it's false.
	 */
    public boolean collision(double otherX, double otherY ,double otherWidth, double otherHeight) {
    	if (this.x <= (otherX + otherWidth) && otherX <= (this.x + image.getWidth())) {
    		   if (otherY <= (this.y + image.getHeight()) && this.y <= (otherY + otherHeight)) {
    				return true;
    		   }
    		}
    		
    	return false;
    }
   
    /** Method to place Banana on the parametered X and Y position.
   	 * @param xPos A double representing a x position - most likely random, to place the banana at.
   	 * @param yPos A double representing a y position - most likely random, to place the banana at
   	 */
    public void place(double xPos, double yPos) {
        this.gc.drawImage(this.image, xPos, yPos);
    
    }

	
}
