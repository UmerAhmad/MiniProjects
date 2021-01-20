//JavaFX Hot Potato
//Umer Ahmad
//December 1, 2018
//This program is a game that simulates the popular hot potato game
//Program requires Java prerequisites (JDK, java.io, JavaFx, libraries, etc.)

//Import global package and necessity for array list
package stacksAndQueues;
import java.util.ArrayList;
import javafx.scene.control.TextArea;


//Main class to hold all methods
public class Stack<E> {
	
	//Initializing the stack as a generic array list
	ArrayList<E> stack;
	
	//Method to initialize stack as a new generic array list
	public Stack() {
		stack = new ArrayList<E>();
	}

	//Method to push an element onto the 'top' of the stack
	public void push(E element) {
		stack.add(element);
	}

	//Method to pop the element on the 'top' of the stack
	public E pop()	{
		boolean empty = isEmpty();
		if (!empty) { //If not empty, get the value on 'top' of the stack and remove, return it
			int index = stack.size() - 1;
			E newValue = stack.get(index);
			stack.remove(newValue);
			return newValue;
		}
		return null; //If empty return null
	}

	//Method to take a peek at the top element without modifying
	public E peek() {
		boolean empty = isEmpty();
		if (!empty) { //If not empty, get element on top, and simply return it
			int index = stack.size() - 1;
			E newValue = stack.get(index);
			return newValue;
		}
		return null;
	}

	//Method to determine if stack is empty
	public boolean isEmpty() {
		int size = stack.size();
		if (size == 0) { //If the size of the stack is 0, or in others no elements within it, return true as isEmpty
			return true;
		}
		return false; //If size isn't 0, return false to isEmpty
	}
	
	//Method to empty out the entire stack
	public void emptyStack() {
		stack.clear();
	}
	
	//Method to display names but for stacks, used to show the players that got out
	public void displayNamesS(TextArea playerNames) {
		playerNames.setText("");
		for (int i = 0; i < stack.size(); i++) {
			playerNames.setText(playerNames.getText() + "\n" + stack.get(stack.size() - 1 - i));
		}
	}
	
	//Method to display rankings, shows the data for the end screen
	public void displayRankings(TextArea playerNames) {
		for (int i = 0; i < stack.size(); i++) {
			playerNames.setText(playerNames.getText() + "\n" + (i + 2) + ". " + stack.get(stack.size() - 1 - i));;
		}
	}
}
