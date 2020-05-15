package com.raul.rental_shop.Ultra_Vision.controller.main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * @author Raul Macedo Fuzita
 * 
 * @version 13.05.20
 * <br>Version is based on the last update date.
 * 
 * @apiNote
 * <p>HomeController is only for displaying a home page.<br></p>
 * 
 * @role This class just display a nice welcome page.
 * 
 * <p>All attributes in this class are private.<p>
 */
public class HomeController implements Initializable {

	@FXML private AnchorPane homePage;
	@FXML private Pane pane;
	@FXML private ImageView imageView;
	
	/**
	 * This method is invoked after @FXML is set. The parameters of initialize
	 * is not used here.
	 * 
	 * @param arg0 is a type of URL.
	 * @param arg1 is a type of ResourceBundle;
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
			
		try {
			/* Loads image file in the image buffer. When an image source is 
			 * out of the src folder you could face issues to load straight 
			 * from a Image, or ImageIO class. */
			BufferedImage img = ImageIO.read(new File("resources/images/home_img.jpg"));
			Image icon = SwingFXUtils.toFXImage(img, null );
			
			this.imageView.setImage(icon);
			this.imageView.setCache(true);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
