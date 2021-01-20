//JavaFX Calculator
//Umer Ahmad
//October 23, 2018
//This program is one in which a calculator may be used through the effects of javaFX
//Program requires Java prerequisites (JDK, java.io, JavaFx, libraries, etc.)


//Necessary imports to construct the calculator
package UmerCalculator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;


//Main Class
public class CalculatorController {
	
	//FXML variables, and private doubles/strings for global manipulation
	@FXML
	private TextField displayField;
	private double memory = 0;
	private String operation = "";
	private double initialValue = 0;
	private double secondValue = 0;
	@FXML private Label operationType;
	
	//Main method that deals with all button clicks
	public void buttonClickHandler(ActionEvent evt) {
		
		//Assigning clicks to a button and receiving text from it
		Button clickedButton = (Button)evt.getTarget();
		String buttonLabel = clickedButton.getText();
		
		
		//If its a number or a decimal, send to digit method for processing, otherwise default to operation
		switch(buttonLabel) {
		case "0": case "1": case "2": case "3": case "4": case "5":  
		case "6": case "7": case "8": case "9": case "10": case ".":
			processDigit(buttonLabel);
			break;
		default:
			//Switch case for operations that don't need to be methodized
			switch(buttonLabel) {
			//If operation is C, clear text field and all variables
			case "C":
				initialValue = 0;
				secondValue = 0;
				operation = "";
				displayField.setText("");
				operationType.setText("");
				break;
			//If operation is +-, multiply whatever is on the textfield by -1
			case "+-":
				if (!displayField.getText().equals("")) {
					double convertToNegative = Double.parseDouble(displayField.getText()) * -1;
					int integerAnswer1 = (int) convertToNegative;
					//If the the integer version of the negative version is not the same, show double negative, else show integer
					if(integerAnswer1 != convertToNegative) {
				    	displayField.setText(String.valueOf(convertToNegative));
				    } else {
				    	displayField.setText(String.valueOf(integerAnswer1));
				    }
				}
				break;
			//If operation is MC, clear global variable memory
			case "MC":
				memory = 0;
				break;
			//If operation is MR, convert memory to string, and display it
			case "MR":
				 int integerAnswer2 = (int) memory;
				    //If the the integer version of the double memory is not the same, show memory, else show integer
				    if(integerAnswer2 != memory) {
				    	displayField.setText(String.valueOf(memory));
				    } else {
				    	displayField.setText(String.valueOf(integerAnswer2));
				    }
				break;
			//If operation is M+, take whatever is on the textfield and add into memory
			case "M+":
				if (!displayField.getText().equals("")) {
					memory += Double.parseDouble(displayField.getText());
				}
				break;
			//If operation is M-, subtract textfield from memory, inverse of M+
			case "M-":
				if (!displayField.getText().equals("")) {
					memory -= Double.parseDouble(displayField.getText());
				}
				break;
			//If operation is π, input Pi into the textfield
			case "π":
				displayField.setText(String.valueOf(Math.PI));
				break;
			//If operation is √x, square root whatever is in the text field and display result
			case "√x":
				if (!displayField.getText().equals("")) {
					double value  = Double.parseDouble(displayField.getText());
					double rootValue = Math.sqrt(value);
					displayField.setText(String.valueOf(rootValue));
				}
				break;
			//If operation is sin, cos, or tan, convert integer on textfield into radians, and do respective operation
			case "sinx":
				if (!displayField.getText().equals("")) {
					double degreeAngle = Double.parseDouble(displayField.getText());
					double radianAngle = Math.toRadians(degreeAngle);
					double sinx = Math.sin(radianAngle);
					displayField.setText(String.valueOf(sinx));
				}
				break;
			case "cosx":
				if (!displayField.getText().equals("")) {
					double degreeAngle1 = Double.parseDouble(displayField.getText());
					double radianAngle1 = Math.toRadians(degreeAngle1);
					double cosx = Math.cos(radianAngle1);
					displayField.setText(String.valueOf(cosx));
				}
				break;
			case "tanx":
				if (!displayField.getText().equals("")) {
				double degreeAngle2 = Double.parseDouble(displayField.getText());
				double radianAngle2 = Math.toRadians(degreeAngle2);
				double tanx = Math.tan(radianAngle2);
				displayField.setText(String.valueOf(tanx));
				}
				break;
			}
			//If operation is not any of the previously covered ones, send to processOperation for processing
			if (!buttonLabel.equals("C") && !buttonLabel.equals("+-") && !buttonLabel.equals("MC") &&
			   !buttonLabel.equals("M+") && !buttonLabel.equals("M-") && !buttonLabel.equals("MR")  && !buttonLabel.equals("π") && !buttonLabel.equals("√x")
			   && !buttonLabel.equals("sinx") && !buttonLabel.equals("cosx")   && !buttonLabel.equals("tanx") && !displayField.getText().equals("")) {
				processOperation(buttonLabel);
			}
		}	
	}
	
	//ProcessDigit method, where all non - operations get sent
	private void processDigit(String buttonLabel) {
		
		//If the input is a decimal, and a decimal is already on the field, don't display decimal again, otherwise add input to the text field
		if(buttonLabel.equals(".")) {
			if(!displayField.getText().contains(".")) {
				displayField.setText(displayField.getText() + buttonLabel);
			}
		} else {
			displayField.setText(displayField.getText() + buttonLabel);
		}
	}
	
	//ProcessOperation method, where all uncovered operations are sent
	private void processOperation(String buttonLabel) {
		
		//If sent operation isn't =, and isn't empty, return
		if (!buttonLabel.equals("=")) {
			if (operation != "") {
				return;
			}
			
			//Global variable operation is set to the given input, and initialValue equals what's on textfield
			operation = buttonLabel;
			initialValue = Double.parseDouble(displayField.getText());
			
			//Another switch case to display type of operation in the label on grid
			switch(operation){
			case "+":
				operationType.setText("ADD");
				break;
			case "-":
				operationType.setText("MINUS");
				break;
			case "X":
				operationType.setText("MULTIPLY");
				break;
			case "/":
				operationType.setText("DIVIDE");
				break;
			case "xⁿ":
				double roundedDisplay = Math.round(initialValue * 100d) / 100d;
				operationType.setText(roundedDisplay + "ⁿ");
				break;
			case "n√x":
				double roundedDisplay2 = Math.round(initialValue * 100d) / 100d;
				operationType.setText(roundedDisplay2 + "√x");
				break;
			}
			//Empty display once operation is input
			displayField.setText("");
		//Else if it equals = and is empty, return
		} else {
			if (operation == "") {
				return;
			}
			//If it is =, secondValue takes whats on the screen, and all values + operation is sent to the method calculate for final processing and then displays the answer
			secondValue = Double.parseDouble(displayField.getText());
			double answer = calculate(initialValue, secondValue, operation);
		    int integerAnswer3 = (int) answer;
		    //If the the integer version of the double answer is not the same, show answer, else show integer
		    if(integerAnswer3 != answer) {
		    	displayField.setText(String.valueOf(answer));
		    } else {
		    	displayField.setText(String.valueOf(integerAnswer3));
		    }
			operation = "";
			operationType.setText("");
		}
	}
	
	//Calculate method that takes parameters of the first input, second input, and operation that separated the two
	public double calculate(double firstNumber, double secondNumber, String operation) {
		
		double answer = 0;
		
		//Another switch case to do respective operation on all variables to provide requested answer
		switch(operation) {
		case "/":
			if (secondNumber == 0) {
				answer = 0;
			} else {
			answer = firstNumber / secondNumber;
			}
			break;
		case "X":
			answer = firstNumber * secondNumber;
			break;
		case "-":
			answer = firstNumber - secondNumber;
			break;
		case "+":
			answer = firstNumber + secondNumber;
			break;
		case "xⁿ":
			answer = Math.pow(firstNumber, secondNumber);
			break;
		case "n√x":
			answer = Math.pow(secondNumber, 1.0 / firstNumber);
			break;
		default:
			answer = 0;
		}
		//Return answer once it has passed through all checks and cases
		return answer;
	}
	
}
