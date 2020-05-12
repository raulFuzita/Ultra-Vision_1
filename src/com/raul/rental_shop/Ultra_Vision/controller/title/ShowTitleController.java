package com.raul.rental_shop.Ultra_Vision.controller.title;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.raul.rental_shop.Ultra_Vision.model.title.TitleEntity;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

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
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

	@FXML
	public void actionClose() {
		
		try {
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

	public Label getNameField() {
		return nameField;
	}

	public Label getGenreField() {
		return genreField;
	}

	public Label getAdditional1Field() {
		return additional1Field;
	}

	public Label getAdditional2Field() {
		return additional2Field;
	}

	public Label getAdditional1Label() {
		return additional1Label;
	}

	public Label getAdditional2Label() {
		return additional2Label;
	}

	public Label getMediaLabel() {
		return mediaLabel;
	}

	public Label getCostField() {
		return costField;
	}

	public Label getYearField() {
		return yearField;
	}
	
}
