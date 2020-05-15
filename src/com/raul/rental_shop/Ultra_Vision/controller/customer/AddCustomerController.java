package com.raul.rental_shop.Ultra_Vision.controller.customer;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.raul.rental_shop.Ultra_Vision.model.customer.CustomerDAO;
import com.raul.rental_shop.Ultra_Vision.model.customer.CustomerEntity;
import com.raul.rental_shop.Ultra_Vision.util.dateformat.DateFormat;
import com.raul.rental_shop.Ultra_Vision.util.datepickerformat.DatePickerFormat;
import com.raul.rental_shop.Ultra_Vision.util.dialogwindow.Dialog;
import com.raul.rental_shop.Ultra_Vision.util.dialogwindow.FactoryDialogWindow;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * @author Raul Macedo Fuzita
 * 
 * @version 13.05.20
 * <br>Version is based on the last update date.
 * 
 * @apiNote
 * <p>AddCustomerController add a new customer record based on the field of the window</p>
 * 
 * @role This class will add a new customer record. 
 * 
 * <p>All attributes in this class are private.<p>
 */
public class AddCustomerController implements Initializable {

	@FXML private AnchorPane titleAnchor;
	@FXML private TextField nameField;
	@FXML private TextField surnameField;
	@FXML private TextField phoneField;
	@FXML private DatePicker birthdayPick;
	@FXML private TextField streetField;
	@FXML private TextField cityField;
	@FXML private TextField countryField;
	@FXML private ComboBox<String> memberBox;
	@FXML private ComboBox<String> privilegeBox;
	@FXML private PasswordField passwordField;
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
		
		// Sets a values to a combobox.
		privilegeBox.getItems().addAll("Customer", "Admin");
		memberBox.getItems().addAll(
				"Music Lover",
				"Video Lover",
				"TV Lover",
				"Premium");
		
		// selects a default value to the combobox
		this.privilegeBox.getSelectionModel().select(0);
		this.memberBox.getSelectionModel().select(0);
		
		// Format datepicker. Check out the documentation of DatePickerFormat
		DatePickerFormat.format(birthdayPick, "dd/MM/yyyy");
	}
	
	@FXML
	public void actionAdd() {
		
		if (this.validateFields()) {
			CustomerEntity user = new CustomerEntity();
			user.setFirstname(nameField.getText());
			user.setLastname(surnameField.getText());
			user.setPhonenumber(phoneField.getText());
			
			LocalDate birthday = birthdayPick.getValue();
			
			user.setBirthday(birthday.toString());
			
			user.setStreet(streetField.getText());
			user.setCity(cityField.getText());
			user.setCountry(countryField.getText());
			
			user.setMembershipPlan(memberBox.getValue());
			
			int memberPlanId = this.memberBox.getSelectionModel()
					.getSelectedIndex();

			switch(memberPlanId) {
			
			case 0:
				user.setMembershipPlan("ML");
			break;

			case 1:
				user.setMembershipPlan("VL");
			break;

			case 2:
				user.setMembershipPlan("TL");
			break;

			case 3:
				user.setMembershipPlan("PR");
			break;
			
			default:
				user.setMembershipPlan("ML");

			}
			
			user.setPrivilege(privilegeBox.getValue());
			user.setPassword(passwordField.getText());
			
			System.out.println(user);
				
			String msg = "Do you really want to add this user?";
			FactoryDialogWindow fdw = new FactoryDialogWindow();
			Dialog dig = fdw.makeDiagOption(msg);

			if (dig.isOption()) {

				CustomerDAO dao = new CustomerDAO();

				try {
					if (dao.add(user)) {
						msg = "User has been created successfully";
					} else {
						msg = "Something happended user wasn't created.";
					}

					fdw.makeDiagInfo(msg);
					this.cleanFields();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
		}

	}

	/**
	 * This method close the current pane and load the previous one.
	 */
	@FXML
	public void actionCancel() {
		
		try {
			pane = FXMLLoader.load(getClass()
					.getResource("/com/raul/rental_shop/Ultra_Vision/view/customer/CustomerView.fxml"));
			titleAnchor.getChildren().setAll(pane);
			this.finalize();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	private boolean validateFields() {
		
		String name = this.nameField.getText();
		String surname = this.surnameField.getText();
		
		String birthday = "";
		
		if(this.birthdayPick.getValue() != null) {
			LocalDate date = this.birthdayPick.getValue();
			birthday = date.toString();
			birthday = DateFormat.format(birthday, "yyyy-MM-dd", "dd/MM/yyyy");
		}
		
		String street = this.streetField.getText();
		String city = this.cityField.getText();
		String country = this.countryField.getText();
		String password = this.passwordField.getText();
		
		if (name.isEmpty()) {
			this.fdw.makeDiagInfo("The field name must be filled");
			return false;
		} 
		if (surname.isEmpty()) {
			this.fdw.makeDiagInfo("The field surname must be filled");
			return false;
		}
		
		if (!birthday.matches("^([0-2][0-9]|(3)[0-1])(\\/)(((0)[0-9])|((1)[0-2]))(\\/)\\d{4}$")) {
			this.fdw.makeDiagInfo("Wrong date format, please the supported "
					+ "\nformat is dd/MM/yyyy");
			return false;
		}
		if (street.isEmpty()) {
			this.fdw.makeDiagInfo("The field street must be filled");
			return false;
		} 
		if (city.isEmpty()) {
			this.fdw.makeDiagInfo("The field city must be filled");
			return false;
		} 
		if (country.isEmpty()) {
			this.fdw.makeDiagInfo("The field country must be filled");
			return false;
		} 
		if (password.isEmpty()) {
			this.fdw.makeDiagInfo("The field password must be filled");
			return false;
		}
		
		return true;
	}
	
	/**
	 * This method will reset all field values
	 */
	private void cleanFields() {
		this.nameField.setText("");
		this.surnameField.setText("");
		this.phoneField.setText("");
		this.birthdayPick.setValue(null);
		this.streetField.setText("");
		this.cityField.setText("");
		this.countryField.setText("");
		this.memberBox.getSelectionModel().select(0);
		this.privilegeBox.getSelectionModel().select(0);
		this.passwordField.setText("");
	}
}
