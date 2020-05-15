package com.raul.rental_shop.Ultra_Vision.application;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import com.raul.rental_shop.Ultra_Vision.model.Database;
import com.raul.rental_shop.Ultra_Vision.view.main.BootstrapWindow;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * @author Raul Macedo Fuzita
 * 
 * @version 13.05.20
 * <br>Version is based on the last update date.
 * 
 * @apiNote
 * <p>UltraVision will take care of all the necessary loadings.<br>
 * This class is in charge of making database connection, loading<br>
 * the resources for the JavaFX windows, handling database connection<br>
 * exceptions, and call the Login window.</p>
 * 
 * @role This class will basically bootstrap the system.
 * 
 */
public class UltraVision extends Application {
	
	/* Create a object reference type BootstrapWindow to display a nice 
	 * window while the needs are happening in the background. */
	protected static BootstrapWindow bootstrapWindow = null;

	/**
	 * This is the main method which will start the initialization process.
	 * It was decided to not delete any System.out.print even for pos-production.
	 * For a jar compilation it wont be display but it'll help for later 
	 * maintenance.
	 */
	public static void main(String[] args) {
		try {
			System.out.println("Starting app...");
			// Instantiating the BootstrapWindow class.
			bootstrapWindow = new BootstrapWindow();
			/* Loading the database connection. This class is singleton if 
			 * you want further information check out its documentation.*/
			Database.getInstance();
			Database.connect();
			// It'll launch JavaFX dependencies and then call the method start.
			launch(args);
		} catch (Exception e) {
			// If a database connection fail this catch will handle the esception.
			String content = "Database connection failed. Please, check the settings.";
			System.out.println(content);
			JOptionPane.showMessageDialog(null, content, "Ultra Vision", 
					JOptionPane.ERROR_MESSAGE);
			
			bootstrapWindow.dispose();
		}
	}

	/**
	 * 
	 * @param primaryStage is a type Stage. The argument is passed by the inner
	 * methods in launch invocation in the main method.
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		try {
			
			// Will load a fxml file
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass()
					.getResource("/com/raul/rental_shop/Ultra_Vision/"
							+ "view/main/LoginView.fxml"));
			
			Scene scene = new Scene(root);
			// Loads a css file
			scene.getStylesheets().add(getClass()
					.getResource("/com/raul/rental_shop/Ultra_Vision/"
							+ "view/css/stylesheet.css")
					.toExternalForm());
			
			primaryStage.setScene(scene);
			
			/* Loads the system logo in an buffer image*/
			BufferedImage img = ImageIO.read(new File("resources/images/icon.png"));
			Image icon = SwingFXUtils.toFXImage(img, null );
			
			primaryStage.getIcons().add(icon);
			
			primaryStage.setTitle("Ultra Vision");
			System.out.println("it is going to show the app...");
			
			/* If everything has loaded successfully the BootstrapWindow
			 * should close.*/
			bootstrapWindow.dispose();
			
			// It'll disable to resize the window.
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			/* If something unexpected happen the whole system is closed.
			 */
			System.out.println("Something bad happend");
			bootstrapWindow.dispose();
			
			String content = "Fatal erro. Please contact the support team.";
			JOptionPane.showMessageDialog(null, content, "Ultra Vision", 
					JOptionPane.ERROR_MESSAGE);
			
			e.printStackTrace();
		}
	}
	
	public void finalize() throws Throwable {
		Database.getInstance().close();
		System.out.println("Object is destroyed by the Garbage Collector.");
	}
}
