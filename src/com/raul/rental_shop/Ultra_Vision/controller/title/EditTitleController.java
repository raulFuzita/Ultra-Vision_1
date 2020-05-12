package com.raul.rental_shop.Ultra_Vision.controller.title;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.raul.rental_shop.Ultra_Vision.util.datepickerformat.DatePickerFormat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class EditTitleController implements Initializable {

	@FXML private AnchorPane titleAnchor;
	@FXML private TextField nameField;
	@FXML private TextField costField;
	@FXML private TextField genreField;
	@FXML private TextField additional1Field;
	@FXML private TextField additional2Field;
	@FXML private DatePicker datePicker;
	@FXML private Label additional1Label;
	@FXML private Label additional2Label;
	@FXML private ToggleGroup mediaGroup;
	@FXML private RadioButton cdRadio;
	@FXML private RadioButton dvdRadio;
	@FXML private RadioButton bluerayRadio;
	@FXML private Pane mainDiv;
	
	@FXML private Button cancelBtn;
	@FXML private Button updateBtn;
	
	private AnchorPane pane = null;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		DatePickerFormat.format(datePicker, "dd-MM-yyyy");
	}
	
	@FXML
	public void actionUpdate() {
		
	}
	
	@FXML
	public void actionCancel() {
		
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

	public TextField getNameField() {
		return nameField;
	}

	public TextField getCostField() {
		return costField;
	}

	public TextField getGenreField() {
		return genreField;
	}

	public TextField getAdditional1Field() {
		return additional1Field;
	}

	public TextField getAdditional2Field() {
		return additional2Field;
	}

	public Label getAdditional1Label() {
		return additional1Label;
	}

	public Label getAdditional2Label() {
		return additional2Label;
	}

	public ToggleGroup getMediaGroup() {
		return mediaGroup;
	}

	public RadioButton getCdRadio() {
		return cdRadio;
	}

	public RadioButton getDvdRadio() {
		return dvdRadio;
	}

	public RadioButton getBluerayRadio() {
		return bluerayRadio;
	}

	public Button getUpdateBtn() {
		return updateBtn;
	}

	public DatePicker getDatePicker() {
		return datePicker;
	}

}
