package com.raul.rental_shop.Ultra_Vision.controller.title;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * @author Raul Macedo Fuzita
 * 
 * @version 13.05.20
 * <br>Version is based on the last update date.
 * 
 * @apiNote
 * <p>ShowTitleController will load some information about a title.</p>
 * 
 * @role This class will load some informations about a selected title. 
 * 
 * <p>All attributes in this class are private.<p>
 */
public class ShowTitleController implements Initializable {

	@FXML private AnchorPane titleAnchor;
	@FXML private Label nameField;
	@FXML private Label costField;
	@FXML private Label genreField;
	@FXML private Label mediaLabel;
	@FXML private Label additional1Label;
	@FXML private Label additional1Field;
	@FXML private Label additional2Label;
	@FXML private Label additional2Field;
	@FXML private Label yearField;
	@FXML private Pane mainDiv;
	@FXML private Button closeBtn;
	
	private AnchorPane pane = null;
	
	/**
	 * This method is invoked after @FXML is set. The parameters of initialize
	 * is not used here.
	 * 
	 * @param arg0 is a type of URL.
	 * @param arg1 is a type of ResourceBundle;
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// It is kept for future purpose.
	}

	/**
	 * This method close the current pane and load the previous one.
	 */
	@FXML
	public void actionClose() {
		
		try {
			// Just a simple FXML loader
			pane = FXMLLoader.load(getClass()
					.getResource("/com/raul/rental_shop/Ultra_Vision/view/title/TitleView.fxml"));
			titleAnchor.getChildren().setAll(pane);
			this.finalize();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	// ============ Getter Methods ================
	
	/**
	 * This method returns an object reference type Label
	 * 
	 * @return nameField which is a type of Label.
	 */
	public Label getNameField() {
		return nameField;
	}

	/**
	 * This method returns an object reference type Label
	 * 
	 * @return genreField which is a type of Label.
	 */
	public Label getGenreField() {
		return genreField;
	}

	/**
	 * This method returns an object reference type Label
	 * 
	 * @return additional1Field which is a type of Label.
	 */
	public Label getAdditional1Field() {
		return additional1Field;
	}

	/**
	 * This method returns an object reference type Label
	 * 
	 * @return additional2Field which is a type of Label.
	 */
	public Label getAdditional2Field() {
		return additional2Field;
	}

	/**
	 * This method returns an object reference type Label
	 * 
	 * @return additional1Label which is a type of Label.
	 */
	public Label getAdditional1Label() {
		return additional1Label;
	}

	/**
	 * This method returns an object reference type Label
	 * 
	 * @return additional2Label which is a type of Label.
	 */
	public Label getAdditional2Label() {
		return additional2Label;
	}

	/**
	 * This method returns an object reference type Label
	 * 
	 * @return mediaLabel which is a type of Label.
	 */
	public Label getMediaLabel() {
		return mediaLabel;
	}

	/**
	 * This method returns an object reference type Label
	 * 
	 * @return costField which is a type of Label.
	 */
	public Label getCostField() {
		return costField;
	}

	/**
	 * This method returns an object reference type Label
	 * 
	 * @return yearField which is a type of Label.
	 */
	public Label getYearField() {
		return yearField;
	}
	
}
