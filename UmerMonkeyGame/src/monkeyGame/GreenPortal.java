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

/** Green Portal class to store all getters and setters. */
public class GreenPortal {    
    
    // declare fields (i.e. variables)
	
	// Global integer of bananas eaten (to keep track and display it later). */
	int bananasEaten = 0;
	
	// Global double x value (co-ordinate on x-axis).*/
	double x;
	// Global double y value (co-ordinate on y-axis).*/
	double y;
	
	// Global String of image name (to insert into image). */
	String imageName = "images/greenportal.gif";
	// Image variable that makes use of image name, to display onto canvas.*/
	Image image = new Image(imageName);
	
	// GraphicsContext variable that allows us to refer to gfx.*/
	GraphicsContext gc;
	// FXML id of gameCanvas to draw on. */
	@FXML Canvas gameCanvas;
    
    // contructors -----------------------------------------------
	
	/** Green Portal constructor that initializes canvas and graphics context without an image name
	 * @param gc A graphics context variable to refer for gfx.
	 * @param canvas A FXML variable to represent the canvas to be drawed on.
	 */
    public GreenPortal(GraphicsContext gc, Canvas canvas) {
        super();
        this.gc = gc;
        this.gameCanvas = canvas;
        // can put code for random x and y here
        // now that we have a reference to our gc and gameCanvas
    }

    /** Green Portal constructor that initializes canvas and graphics context without an image name
	 * @param gc A graphics context variable to refer for gfx.
	 * @param canvas A FXML variable to represent the canvas to be drawed on.
	 * @param imageName A string value to dynamically change image of object.
	 */
    public GreenPortal(String imageName, GraphicsContext gc, Canvas canvas) {
        super();
        this.imageName = imageName;
        this.gc = gc;
        this.gameCanvas = canvas;
    }

    // all the getters and setters ----------------------------------
    
    /** Gets the X value of the Green Portal.
	 * @return A double representing the X co-ordinate.
	 */
    public double getX() {
        return x;
    }

    /** Sets the X value.*/
    public void setX() {
        this.x = (gameCanvas.getWidth() - this.getWidth() - 5);
    }

    /** Gets the Y value of the Green Portal.
	 * @return A double representing the Y co-ordinate.
	 */
    public double getY() {
        return y;
    }

    /** Sets the Y value.*/
    public void setY() {
        this.y = 5;
    }
    
    
    /** Gets the second X value of the Green Portal.
	 * @return A double representing the X co-ordinate.
	 */
    public double getX2() {
        return x;
    }

    /** Sets the second X.*/
    public void setX2() {
        this.x = this.getWidth() - 160;
    }

    /** Gets the second Y value of the Green Portal.
	 * @return A double representing the second Y co-ordinate.
	 */
    public double getY2() {
        return y;
    }
    /** Sets the second Y value.*/
    public void setY2() {
        this.y = gameCanvas.getHeight() - this.getHeight() - 5;
    }

    /** Gets the image name of the Green Portal.
	 * @return A string representing the file of reference to the image of the Green Portal.
	 */
    public String getImageName() {
        return imageName;
    }

    /** Sets the image name of the Green Portal to the parameterd string.
	 * @param imageName A string representing the file of reference to the image of the Green Portal.
	 */
    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
    
    /** Gets the width of the Green Portal image.
	 * @return A double representing the Green Portal image width.
	 */
    public double getWidth() {
        return image.getWidth();
    }
    
    /** Gets the height of the Green Portal image.
   	 * @return A double representing the Green Portal image height.
   	 */
    public double getHeight() {
        return image.getHeight();
    }
    
    /** Gets the number of Green Portal's eaten, to be referenced in the score class for updated display and stats.
   	 * @return A double representing the number of bananas eaten.
   	 */
    public int getBananasEaten() {
        return bananasEaten;
    }

    /** Sets the number of Green Portal's eaten.
   	 * @param bananasEaten2 A integer representing the number of Green Portal's eaten, to be later referenced in another class.
   	 */
    public void setBananasEaten(int bananasEaten2) {
        bananasEaten = bananasEaten2;
    }
    
    /** Method to place Green Portal on the parametered X and Y position.
   	 * @param xPos A double representing a x position - most likely random, to place the Green Portal at.
   	 * @param yPos A double representing a y position - most likely random, to place the Green Portal at
   	 */
    public void place(double xPos, double yPos) {
        this.gc.drawImage(this.image, xPos, yPos);
    
    }

	
}
