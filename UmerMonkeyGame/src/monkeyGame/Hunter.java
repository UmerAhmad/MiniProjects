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


/** Hunter class to store all getters and setters. */
public class Hunter {    
    
    // declare fields (i.e. variables)
  
	// Global double speed value (to be referenced for dx and dy).*/
    double speed = 0.5;
    // Global double x value (co-ordinate on x-axis).*/
    double  x = 0;
    // Global double y value (co-ordinate on y-axis).*/
	double y = 300;
	// Global double dx value (value to add onto the x - simulating movement).*/
    double dx = speed;
    
    // Global String of image name (to insert into image). */
    String imageName = "images/hunter.png";
    // Image variable that makes use of image name, to display onto canvas.*/
    Image image = new Image(imageName);
    
    // GraphicsContext variable that allows us to refer to gfx.*/
    GraphicsContext gc;
    // FXML id of gameCanvas to draw on. */
    @FXML Canvas gameCanvas;

    // contructors -----------------------------------------------
    
    /** Hunter constructor that initializes canvas and graphics context without an image name
	 * @param gc A graphics context variable to refer for gfx.
	 * @param canvas A FXML variable to represent the canvas to be drawed on.
	 */
    public Hunter(GraphicsContext gc, Canvas canvas) {
        super();
        this.gc = gc;
        this.gameCanvas = canvas;
        // can put code for random x and y here
        // now that we have a reference to our gc and gameCanvas
    }

    /** Hunter constructor that initializes canvas and graphics context without an image name
	 * @param gc A graphics context variable to refer for gfx.
	 * @param canvas A FXML variable to represent the canvas to be drawed on.
	 * @param imageName A string value to dynamically change image of object.
	 */
    public Hunter(String imageName, GraphicsContext gc, Canvas canvas) {
        super();
        this.imageName = imageName;
        this.gc = gc;
        this.gameCanvas = canvas;
    }

    // all the getters and setters ----------------------------------
    
    /** Gets the speed value of the Hunter.
	 * @return A integer representing the speed value.
	 */
    public double getSpeed() {
        return speed;
    }
    
    /** Sets the speed value of the Hunter to the parametered integer.
	 * @param speed A integer representing the speed value. 
	 */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /** Gets the X value of the Hunter.
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

    /** Gets the Y value of the Hunter.
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

    /** Gets the dx value of the Hunter.
	 * @return A double representing the dx value (change in x).
	 */
    public double getDx() {
        return dx;
    }
    
    /** Sets the dx value of the Hunter.
	 * @param dx A double representing the dx value (change in x).
	 */
    public void setDx(double dx) {
        this.dx = dx;
    }

    /** Gets the image name of the Hunter.
   	 * @return A string representing the file of reference to the image of the Hunter.
   	 */
    public String getImageName() {
        return imageName;
    }

    /** Sets the image name of the Hunter to the parameterd string.
	 * @param imageName A string representing the file of reference to the image of the Hunter.
	 */
    public void setImageName(String imageName) {
        this.imageName = imageName;
        image = new Image(imageName);
    }
    
    /** Gets the width of the Hunter image.
	 * @return A double representing the Hunter image width.
	 */
    public double getWidth() {
        return image.getWidth();
    }
    
    /** Gets the height of the Hunter image.
	 * @return A double representing the Hunter image height.
	 */
    public double getHeight() {
        return image.getHeight();
    }
    
  
    /** Method to move the Hunter with a random dx and dy speed, if it collides with a wall - send it in opposite direction. Draw it on the game canvas. */
    public void move() {
    
        this.x += this.dx;
        
        if(this.x == 900) {
        	this.dx = -speed;
        }
        
        if(this.x == 0) {
        	this.dx = speed;
        }
        
        
        this.gc.drawImage(this.image, this.x, this.y);
    
    }


}
