//JavaFX Hot Potato
//Umer Ahmad
//December 1, 2018
//This program is a game that simulates the popular hot potato game
//Program requires Java prerequisites (JDK, java.io, JavaFx, libraries, etc.)

//Import global package and necessity for array list
package stacksAndQueues;
import java.util.ArrayList;
import stacksAndQueues.Queue;

import javafx.scene.control.TextArea;


//Main class to hold all methods
public class Queue<E> {
	
	//Initializing the queue as a generic array list
	ArrayList<E> queue;
	
	//Method to initialize queue as a new generic array list
	public Queue() {
		queue = new ArrayList<E>();
	}
	

	//Method to add an item to back of the queue
	public void enqueue(E element) {
		queue.add(element);
	}

	//Method to identify item at front of the queue, remove, and return it
	public E dequeue()	{
		boolean empty = isEmpty();
		if (!empty) { //If not empty, get first index and remove it
			E newValue = queue.get(0);
			queue.remove(newValue);
			return newValue;
		}
		return null; //If empty, return null
	}
	
	//Method to dequeue the item at the back of the queue, and return it
	public E dequeueLast()	{
		boolean empty = isEmpty();
		if (!empty) { //If not empty, get first index and remove it
			E newValue = queue.get(queue.size() - 1);
			queue.remove(newValue);
			return newValue;
		}
		return null; //If empty, return null
	}
	
	//Method to take in a position, and dequeue the element at that position
	public E dequeueTargeted(int position)	{
		boolean empty = isEmpty();
		if (!empty) { //If not empty, get first index and remove it
			E newValue = queue.get(position - 1);
			queue.remove(newValue);
			return newValue;
		}
		return null; //If empty, return null
	}

	//Method to peek at the front of the queue, returning first element
	public E peek() {
		boolean empty = isEmpty();
		if (!empty) { //If not empty, simply return first index
			E newValue = queue.get(0);
			return newValue;
		}
		return null; //If empty, return null
	}
	
	//Method to peek at the back of the queue, returning the last element
	public E peekLast() {
		boolean empty = isEmpty();
		if (!empty) { //If not empty, simply return first index
			E newValue = queue.get(queue.size() - 1);
			return newValue;
		}
		return null; //If empty, return null
	}

	//Method to determine if queue is empty
	public boolean isEmpty() {
		int size = queue.size();
		if (size == 0) { //If the size of the queue is 0, return isEmpty as true
			return true;
		}
		return false; //If size isn't 0, return isEmpty as false
	}

	//Method to get the size of the queue
	public int getSize() {
		int size = queue.size();
		return size;
	}
	
	//Frequently used method throughout the code, sets the text area empty, and fills it with the elements of the queue
	public void displayNames(TextArea playerNames) {
		playerNames.setText("");
		for (int i = 0; i < queue.size(); i++) {
			playerNames.setText(playerNames.getText() + "\n" + queue.get(i));
		}
	}
	
	//Method to system print out all elements of the queue 
	public void listElements() {
		System.out.println("\n");
		for (int i = 0; i < queue.size(); i++) {
			System.out.println(queue.get(i));
		}
	}
	
		
}
