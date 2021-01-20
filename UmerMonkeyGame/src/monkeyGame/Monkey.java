//JavaFX Monkey Game
//Umer Ahmad
//December 5, 2018
//This program is a game in which you play as a monkey and try to collect as many bananas as possible, avoiding obstacles
//Program requires Java prerequisites (JDK, java.io, JavaFx, libraries, etc.)

//Importing necessities
package monkeyGame;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


/** Monkey class to store all getters and setters. */
public class Monkey {    
    
    // declare fields (i.e. variables)
	
	// Global integer speed value (to be referenced for dx and dy).*/
    int speed = 4;
    // Global integer monkey lives value (to be referenced for when ending the game).*/
    int monkeyLives = 3;
    
    // Global double randomized x value (co-ordinate on x-axis).*/
    double x = (int) (Math.random() * 700 + 150);
    // Global double randomized y value (co-ordinate on y-axis).*/
	double y = (int) (Math.random() * 400 + 140);
	
	// Global double dx value (value to add onto the x - simulating movement).*/
    double dx = 0; 
    // Global double dy value (value to add onto the y - simulating movement).*/
    double dy = - 0;
    
    // Global double totalX value (value to measure total pixels traveled in x).*/
    double totalX = 0;
    // Global double totalY value (value to measure total pixels traveled in Y).*/
    double totalY = 0;
    
    // Global double total distance value (value to measure total pixels traveled combined x and y).*/
    double totalDistance = 0;
    
    // Global string left (to determine if the user input LEFT).*/
    String left = "LEFT";
    // Global string right (to determine if the user input RIGHT).*/
    String right = "RIGHT";
    // Global string up (to determine if the user input UP).*/
    String up = "UP";
    // Global string down (to determine if the user input DOWN).*/
    String down = "DOWN";
    
    // Global String of image name (to insert into image). */
    String imageName = "images/Monkey.png";
    // Image variable that makes use of image name, to display onto canvas.*/
    Image image = new Image(imageName);
    
    // GraphicsContext variable that allows us to refer to gfx.*/
    GraphicsContext gc;
    // FXML id of gameCanvas to draw on. */
    @FXML Canvas gameCanvas;
      
    // contructors -----------------------------------------------
    
    /** Monkey constructor that initializes canvas and graphics context without an image name
	 * @param gc A graphics context variable to refer for gfx.
	 * @param canvas A FXML variable to represent the canvas to be drawed on.
	 */
    public Monkey(GraphicsContext gc, Canvas canvas) {
        super();
        this.gc = gc;
        this.gameCanvas = canvas;
        // can put code for random x and y here
        // now that we have a reference to our gc and gameCanvas
    }

    /** Monkey constructor that initializes canvas and graphics context without an image name
	 * @param gc A graphics context variable to refer for gfx.
	 * @param canvas A FXML variable to represent the canvas to be drawed on.
	 * @param imageName A string value to dynamically change image of object.
	 */
    public Monkey(String imageName, GraphicsContext gc, Canvas canvas) {
        super();
        this.imageName = imageName;
        this.gc = gc;
        this.gameCanvas = canvas;
    }

    // all the getters and setters ----------------------------------
    
    /** Gets the speed value of the Monkey.
	 * @return A integer representing the speed value.
	 */
    public int getSpeed() {
        return speed;
    }

    /** Sets the speed value of the Monkey to the parametered integer.
	 * @param speed A integer representing the speed value. 
	 */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /** Gets the X value of the Monkey.
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

    /** Gets the Y value of the Monkey.
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

    /** Gets the dx value of the Monkey.
	 * @return A double representing the dx value (change in x).
	 */
    public double getDx() {
        return dx;
    }

    /** Sets the dx value of the Monkey.
	 * @param dx A double representing the dx value (change in x).
	 */
    public void setDx(double dx) {
        this.dx = dx;
    }

    /** Gets the dy value of the Monkey.
	 * @return A double representing the dy value (change in y).
	 */
    public double getDy() {
        return dy;
    }

    /** Sets the dy value of the Monkey.
	 * @param dy A double representing the dy value (change in y).
	 */
    public void setDy(double dy) {
        this.dy = dy;
    }
    
    /** Gets the number of monkey lives remaining.
	 * @return A integer representing number of lives remaining.
	 */
    public int getMonkeyLives() {
        return monkeyLives;
    }

    /** Sets the number of monkey lives remaining.
   	 * @param monkeyLives2 A integer representing number of lives remaining.
   	 */
    public void setMonkeyLives(int monkeyLives2) {
        monkeyLives = monkeyLives2;
    }

    /** Gets the image name of the Monkey.
	 * @return A string representing the file of reference to the image of the Monkey.
	 */
    public String getImageName() {
        return imageName;
    }

    /** Sets the image name of the Monkey to the parameterd string.
	 * @param imageName A string representing the file of reference to the image of the Monkey.
	 */
    public void setImageName(String imageName) {
        this.imageName = imageName;
        image = new Image(imageName);
    }
    
    /** Gets the width of the Monkey image.
	 * @return A double representing the Monkey image width.
	 */
    public double getWidth() {
        return image.getWidth();
    }
    
    /** Gets the height of the Monkey image.
	 * @return A double representing the Monkey image height.
	 */
    public double getHeight() {
        return image.getHeight();
    }
    
    /** Gets the total x traveled value of the Monkey.
	 * @return A double representing the total x value (distance in x).
	 */
    public double getTotalX() {
        return this.totalX;
    }
    
    /** Sets the total x traveled value of the Monkey.
	 * @param totalX A double representing the total x value (distance in x).
	 */
    public void setTotalX(double totalX) {
        this.totalX = totalX;
    }
    
    /** Gets the total y traveled value of the Monkey.
	 * @return A double representing the total y value (distance in y).
	 */
    public double getTotalY() {
        return this.totalY;
    }
    
    /** Sets the total y traveled value of the Monkey.
	 * @param totalY A double representing the total y value (distance in y).
	 */
    public void setTotalY(double totalY) {
        this.totalY = totalY;
    }
    
    /** Gets the total distanced traveled value of the Monkey.
   	 * @return A double representing the total distance value (distance of pixels).
   	 */
    public double getTotalDistance() {
    	this.totalDistance = this.totalX + this.totalY;
        return totalDistance;
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
    
    
    /** Method to update x and y position of Monkey, and display onto screen based off WASD input.
	 * @param input An arraylist of strings that contain user input, depending on the input the Monkey will move.
	 */
    public void move(ArrayList<String> input) {
    	this.dx = 0;
    	this.dy = 0;
    	
    	if (input.contains(this.left) && !(this.x <= 0) ){
    		this.dx = -this.speed;
    	}
    	
    	if (input.contains(this.right) && !(this.x >= this.gameCanvas.getWidth() - this.image.getWidth())){
 		   	this.dx = this.speed;
 		}
    	
 
    	if (input.contains(this.up)  && !(this.y <= 0) ){
    		this.dy = -this.speed;
 		}
 	
    	if (input.contains(this.down) && !(this.y >= this.gameCanvas.getHeight() - this.image.getHeight())){
		    this.dy = this.speed;
		}
    	
    	if(input.contains(this.left) && input.contains(this.right)) {
    		this.dx = 0;
    	}
    	
    	if(input.contains(this.up) && input.contains(this.down)) {
    		this.dy = 0;
    	}

              
        this.x += this.dx;
        this.y += this.dy;
        
        this.totalX += java.lang.Math.abs(this.dx);
        this.totalY += java.lang.Math.abs(this.dy);
        
        this.gc.drawImage(this.image, this.x, this.y);
    
    }


}
