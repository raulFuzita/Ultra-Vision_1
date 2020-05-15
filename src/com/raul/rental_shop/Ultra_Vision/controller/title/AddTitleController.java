package com.raul.rental_shop.Ultra_Vision.controller.title;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.raul.rental_shop.Ultra_Vision.util.dateformat.DateFormat;
import com.raul.rental_shop.Ultra_Vision.util.datepickerformat.DatePickerFormat;
import com.raul.rental_shop.Ultra_Vision.util.dialogwindow.FactoryDialogWindow;

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

/**
 * @author Raul Macedo Fuzita
 * 
 * @version 13.05.20
 * <br>Version is based on the last update date.
 * 
 * @apiNote
 * <p>AddTitleController add a new title record based on the field of the window</p>
 * 
 * @role This class will add a new title record. 
 * 
 * <p>All attributes in this class are private.<p>
 */
public class AddTitleController implements Initializable {

	@FXML private AnchorPane titleAnchor;
	@FXML private TextField nameField;
	@FXML private TextField costField;
	@FXML private TextField genreField;
	@FXML private TextField additional1Field;
	@FXML private TextField additional2Field;
	@FXML private DatePicker datePicker;
	@FXML private ToggleGroup mediaGroup;
	@FXML private RadioButton cdRadio;
	@FXML private RadioButton dvdRadio;
	@FXML private RadioButton bluerayRadio;
	@FXML private Label additional1Label;
	@FXML private Label additional2Label;
	@FXML private Label yearLabel;
	@FXML private Pane mainDiv;
	
	@FXML private Button cancelBtn;
	@FXML private Button addBtn;
	
	private AnchorPane pane = null;
	private FactoryDialogWindow fdw = new FactoryDialogWindow();
	
	/**
	 * This method is invoked after @FXML is set. The parameters of initialize
	 * is not used here.
	 * 
	 * @param arg0 is a type of URL.
	 * @param arg1 is a type of ResourceBundle;
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		DatePickerFormat.format(datePicker, "dd-MM-yyyy");
	}
	
	@FXML
	public void actionAdd() {
		// It is not used currently
	}
	
	/**
	 * This method close the current pane and load the previous one.
	 */
	@FXML
	public void actionCancel() {
		try {
			// Just a simple FXML loader
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
	
	/**
	 * This method will reset all field values
	 */
	public void cleanFields() {
		this.nameField.setText("");
		this.costField.setText("");
		this.genreField.setText("");
		this.additional1Field.setText("");
		this.additional2Field.setText("");
		this.datePicker.setValue(null);
	}
	
	public boolean validateFields() {
		
		String year = "";
		
		if(this.datePicker.getValue() != null) {
			LocalDate date = this.datePicker.getValue();
			year = date.toString();
			year = DateFormat.format(year, "yyyy-MM-dd", "dd/MM/yyyy");
		} else {
			LocalDate currentDate = LocalDate.now();
			this.datePicker.setValue(currentDate);
			DatePickerFormat.format(datePicker, "dd/MM/yyyy");
		}
		
		
		if (!year.matches("^(3[01]|[12][0-9]|0[1-9]])/(1[0-2]|0[1-9])/[0-9]{4}$")) {
			this.fdw.makeDiagInfo("Wrong date format, please the supported "
					+ "\nformat is dd/MM/yyyy");
			return false;
		}
		
		return true;
	}


	/**
	 * This method returns an object reference type TextField
	 * 
	 * @return nameField which is a type of TextField.
	 */
	public TextField getNameField() {
		return nameField;
	}

	/**
	 * This method returns an object reference type TextField
	 * 
	 * @return costField which is a type of TextField.
	 */
	public TextField getCostField() {
		return costField;
	}

	/**
	 * This method returns an object reference type TextField
	 * 
	 * @return genreField which is a type of TextField.
	 */
	public TextField getGenreField() {
		return genreField;
	}

	/**
	 * This method returns an object reference type TextField
	 * 
	 * @return additional1Field which is a type of TextField.
	 */
	public TextField getAdditional1Field() {
		return additional1Field;
	}

	/**
	 * This method returns an object reference type TextField
	 * 
	 * @return additional2Field which is a type of TextField.
	 */
	public TextField getAdditional2Field() {
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
	 * This method returns an object reference type DatePicker
	 * 
	 * @return datePicker which is a type of DatePicker.
	 */
	public DatePicker getDatePicker() {
		return datePicker;
	}

	/**
	 * This method returns an object reference type Button
	 * 
	 * @return addBtn which is a type of Button.
	 */
	public Button getAddBtn() {
		return addBtn;
	}

	/**
	 * This method returns an object reference type RadioButton
	 * 
	 * @return cdRadio which is a type of RadioButton.
	 */
	public RadioButton getCdRadio() {
		return cdRadio;
	}

	/**
	 * This method returns an object reference type RadioButton
	 * 
	 * @return dvdRadio which is a type of RadioButton.
	 */
	public RadioButton getDvdRadio() {
		return dvdRadio;
	}

	/**
	 * This method returns an object reference type RadioButton
	 * 
	 * @return bluerayRadio which is a type of RadioButton.
	 */
	public RadioButton getBluerayRadio() {
		return bluerayRadio;
	}

	/**
	 * This method returns an object reference type ToggleGroup
	 * 
	 * @return mediaGroup which is a type of ToggleGroup.
	 */
	public ToggleGroup getMediaGroup() {
		return mediaGroup;
	}
	
}
