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

public class HomeController implements Initializable {

	@FXML private AnchorPane homePage;
	@FXML private Pane pane;
	@FXML private ImageView imageView;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
			
		try {
			BufferedImage img = ImageIO.read(new File("resources/images/home_img.jpg"));
			Image icon = SwingFXUtils.toFXImage(img, null );
			
			this.imageView.setImage(icon);
			this.imageView.setCache(true);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
