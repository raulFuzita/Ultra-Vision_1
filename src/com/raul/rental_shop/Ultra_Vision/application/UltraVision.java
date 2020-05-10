package com.raul.rental_shop.Ultra_Vision.application;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import com.raul.rental_shop.Ultra_Vision.model.Database;
import com.raul.rental_shop.Ultra_Vision.view.main.BootstrapWindow;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class UltraVision extends Application {
	
	static BootstrapWindow bootstrapWindow = null;

	public static void main(String[] args) {
		try {
			System.out.println("Starting app...");
			bootstrapWindow = new BootstrapWindow();
			Database.getInstance();
			Database.connect();
			launch(args);
		} catch (Exception e) {
			System.out.println("Test");
			bootstrapWindow.dispose();
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		try {
			
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass()
					.getResource("/com/raul/rental_shop/Ultra_Vision/view/main/LoginView.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass()
					.getResource("/com/raul/rental_shop/Ultra_Vision/view/css/stylesheet.css")
					.toExternalForm());
			primaryStage.setScene(scene);
			
			BufferedImage img = ImageIO.read(new File("resources/images/icon.png"));
			Image icon = SwingFXUtils.toFXImage(img, null );
			
			primaryStage.getIcons().add(icon);
			
			primaryStage.setTitle("Ultra Vision");
			System.out.println("Going to show app...");
			bootstrapWindow.dispose();
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			System.out.println("Something bad happend");
			bootstrapWindow.dispose();
			e.printStackTrace();
		}
	}
	

}
