package com.raul.rental_shop.Ultra_Vision.util.dialogwindow;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * @author Raul Macedo Fuzita
 * 
 * @version 13.05.20
 * <br>Version is based on the last update date.
 * 
 * @apiNote
 * <p>FactoryDialogWindow class has to be instantiated to access their<br>
 * methods. This is require because inside of the method windowMaker<br>
 * it is necessary use the class instance reference to access the methods<br>
 * getClass and getPackage. This trick will make this class work even if <br>
 * you move this class to another package. Keep in mind that the fxml files<br>
 * must be in the same package or directory.</p>
 * 
 * @role This class makes Popup windows.
 * 
 * <p>All attributes in this class are private.<p>
 */
public class FactoryDialogWindow {
	
	private static Parent parent = null;
	private static Stage stagePopup;

	/**
	 * This method will return an interface type of Dialog where you would be
	 * able to access the value of the clicked button or input by accessing the
	 * available methods such as isOpen and output.
	 * A popup window will be shown with a message and an OK button.
	 * 
	 * @param message is a type of String.
	 * 
	 * @return a Dialog interface type. Otherwise null.
	 */
	public Dialog makeDiagInfo(String message) {
		try {
			return windowMaker("DialogInfoWindow.fxml", message);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * This method will return an interface type of Dialog where you would be
	 * able to access the value of the clicked button or input by accessing the
	 * available methods such as isOpen and output.
	 * A popup window will be shown with a message and two buttons. Yes and No.
	 * 
	 * @param message is a type of String.
	 * 
	 * @return a Dialog interface type. Otherwise null.
	 */
	public Dialog makeDiagOption(String message) {
		try {
			return windowMaker("DialogOptionWindow.fxml", message);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * This method will return an interface type of Dialog where you would be
	 * able to access the value of the clicked button or an input by accessing the
	 * available methods such as isOpen and output.
	 * A popup window will be shown with a message, an input keyboard, an Yes
	 * button and a No button.
	 * 
	 * @param message is a type of String.
	 * 
	 * @return a Dialog interface type. Otherwise null.
	 */
	public Dialog makeDiagInput(String message) {
		try {
			return windowMaker("DialogInputWindow.fxml", message);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * 
	 * @param filename is a String type. It's expected the value be a fxml file
	 * path or name if it's in the same package.
	 * 
	 * @param message is type of String and will be the message on the popup window.
	 * 
	 * @return an interface type of Dialog where you would be
	 * able to access the value of the clicked button or an input by accessing the
	 * available methods such as isOpen and output.
	 * 
	 * @throws IOException if any problem related to a file access.
	 */
	private Dialog windowMaker(final String filename, String message) 
			throws IOException {
		// gets the package path 'till the class.
		Package pk = this.getClass().getPackage();
		
		/*
		 * Gets the whole class path and parse it to convert dot to slash.
		 */
		String pkPath = "/" + (pk.getName().replaceAll("\\.", "/"));
		// After parsing the file it is added to it  the fxml file name.
		String path = pkPath + "/" + filename;
		
		// instantiates an instance of FXMLLoader.
		FXMLLoader loader = new FXMLLoader();
		// Sets the resource according to the passed argument.
		loader.setLocation(getClass().getResource(path));
		
		parent = loader.load();
		
		/*
		 * It is only possible to get the controller if you load the resource
		 * firstly.
		 */
		Dialog controller = loader.getController();
		controller.setTextArea(message);
		
		stagePopup = new Stage();
		stagePopup.setScene(new Scene(parent));
		
		stagePopup.setResizable(false);
		stagePopup.initModality(Modality.APPLICATION_MODAL);
		stagePopup.showAndWait();
		
		return controller;
	}
}
