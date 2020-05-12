package com.raul.rental_shop.Ultra_Vision.controller.title;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.raul.rental_shop.Ultra_Vision.model.title.TitleEntity;
import com.raul.rental_shop.Ultra_Vision.util.datepickerformat.DatePickerFormat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class AddTitleController implements Initializable {

	@FXML private AnchorPane titleAnchor;
	@FXML private TextField nameField;
	@FXML private TextField costField;
	@FXML private TextField genreField;
	@FXML private TextField additional1Field;
	@FXML private TextField additional2Field;
	@FXML private DatePicker datePicker;
	@FXML private CheckBox cdCheck;
	@FXML private CheckBox dvdCheck;
	@FXML private CheckBox bluerayCheck;
	@FXML private Label additional1Label;
	@FXML private Label additional2Label;
	@FXML private Label yearLabel;
	@FXML private Pane mainDiv;
	
	@FXML private Button cancelBtn;
	@FXML private Button addBtn;
	
	private AnchorPane pane = null;
	private TitleEntity titleEntity = null;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		DatePickerFormat.format(datePicker, "dd-MM-yyyy");
	}
	
	@FXML
	public void actionAdd() {
		
	}
	
	@FXML
	public void actionCancel() {
		try {
			
			pane = FXMLLoader.load(getClass()
					.getResource("/com/raul/rental_shop/Ultra_Vision/view/title/OptionTitleView.fxml"));
			titleAnchor.getChildren().setAll(pane);
			
			this.finalize();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	public void cleanFields() {
		this.nameField.setText("");
		this.costField.setText("");
		this.genreField.setText("");
		this.additional1Field.setText("");
		this.additional2Field.setText("");
		this.datePicker.setValue(null);
	}

	public CheckBox getDvdCheck() {
		return dvdCheck;
	}

	public void setDvdCheck(CheckBox dvdCheck) {
		this.dvdCheck = dvdCheck;
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

	public CheckBox getCdCheck() {
		return cdCheck;
	}

	public CheckBox getBluerayCheck() {
		return bluerayCheck;
	}

	public Label getAdditional1Label() {
		return additional1Label;
	}

	public Label getAdditional2Label() {
		return additional2Label;
	}

	public DatePicker getDatePicker() {
		return datePicker;
	}

	public Button getAddBtn() {
		return addBtn;
	}
	
	
}
