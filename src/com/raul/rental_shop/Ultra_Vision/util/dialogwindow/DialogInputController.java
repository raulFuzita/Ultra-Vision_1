package com.raul.rental_shop.Ultra_Vision.util.dialogwindow;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * @author Raul Macedo Fuzita
 * 
 * @version 13.05.20
 * <br>Version is based on the last update date.
 * 
 * @apiNote
 * <p>DialogInputController is a class to perform a dialog window like<br>
 * JOptionPane dialog does. If you want to display a message and let a user<br>
 * to input a value it is recommended you use this class.<br>
 * Have a look at the methods documentation for further informations.</p>
 * 
 * <p>This method implements two interfaces, Initializable and Dialog.<br>
 * The interface Dialog will ensure the class is able to do what was defined<br>
 * by the interface logic business. Any class that implements this interface<br>
 * can be treated as such as. 
 * 
 * 
 * @role This class will show a popup window in JavaFX.
 * 
 * <p>All attributes in this class are private.<p>
 */
public class DialogInputController implements Initializable, Dialog {

	/* The notation @FXML links the attribute to the Java fx id of a JavaFX
	 * component.
	 * 
	 * AnchorPane allows the edges of child nodes to be anchored to an offset 
	 * from the anchor pane’s edges. */
	@FXML private AnchorPane popup; // A text are to display an informative message.
	/* that'll trigger the close method a change the state of variable option */
	@FXML private TextArea textArea;
	@FXML private TextField inputField;
	@FXML private Button yesBtn; // agrees with the message on the screen
	@FXML private Button noBtn; // disagree with the message on the screen
	private String input; // Gets the user input
	private boolean option;// It'll give a simple feedback.
	
	/* Stage will be a reference of the window.*/
	private Stage stage = new Stage();
	
	/**
	 * This method is invoked after @FXML is set. The parameters of initialize
	 * is not used here.
	 * 
	 * @param arg0 is a type of URL.
	 * @param arg1 is a type of ResourceBundle;
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Scene scene = popup.getScene();
		this.stage.setScene(scene);
	}
	
	/**
	 * This method will shift focus back to the window working as popup window.
	 * This method is linked to a XML element.
	 */
	@FXML
	public void loseFocus() {
		popup.setFocusTraversable(true);
	}

	/**
	 * When the Yes button is pressed this method changes state of the variable
	 * option if you want it returns a boolean value.
	 * This method will change the option state to true. Call the method isOption.
	 * 
	 * It also gets the user input and stores it in a variable called input which
	 * can be retrieved by calling the method output.
	 */
	@FXML
	public void actionYes() {
		this.input = this.inputField.getText();
		this.option = true;
		this.closeWindow();
	}
	
	/**
	 * When the No button is pressed this method changes state of the variable
	 * option if you want it returns a boolean value.
	 * This method will change the option state to false. Call the method isOption.
	 * 
	 * It also gets the user input and stores it in a variable called input which
	 * can be retrieved by calling the method output.
	 */
	@FXML
	public void actionNo() {
		this.input = null;
		this.option = false;
		this.closeWindow();
	}
	
	/**
	 * This method is private and can't be called from outside of the class.
	 * This method perform the necessary steps to close this instance.
	 */
	private void closeWindow() {
		Stage stage = (Stage) popup.getScene().getWindow();
		stage.close();
	}
	
	/**
	 * If you need get the message displayed on the popup window you can call
	 * this method.
	 */
	@Override
	public String getTextArea() {
		return textArea.getText();
	}
	
	/**
	 * This method sets a text to be displayed on the window.
	 */
	@Override
	public void setTextArea(String text) {
		this.textArea.setText(text);
	}

	/**
	 * You might want to know if the user has clicked on the button OK or the 
	 * X button to close the windows, either way it will be caught by this method.
	 * If the user press OK it returns true. Otherwise false.
	 */
	@Override
	public boolean isOption() {
		return option;
	}
	
	/**
	 * This method will held any other action of a class until this window is closed.
	 */
	public void show() {
		stage.showAndWait();
	}
	
	/**
	 * Although this method public it'll be called when you click on OK or X button.
	 */
	public void close() {
		stage.close();
	}

	/**
	 * This method will return whatever the user typed in.
	 */
	@Override
	public String output() {
		return this.input;
	}
}
