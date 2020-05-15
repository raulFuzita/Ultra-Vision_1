package com.raul.rental_shop.Ultra_Vision.controller.customer;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import com.raul.rental_shop.Ultra_Vision.model.DAO;
import com.raul.rental_shop.Ultra_Vision.model.customer.CustomerDAO;
import com.raul.rental_shop.Ultra_Vision.model.customer.CustomerEntity;
import com.raul.rental_shop.Ultra_Vision.model.customer.NullCustomerEntity;
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

/**
 * @author Raul Macedo Fuzita
 * 
 * @version 13.05.20
 * <br>Version is based on the last update date.
 * 
 * @apiNote
 * <p>EditTitleController will edit and update some information about a customer record.</p>
 * 
 * @role This class will edit some informations about a selected customer record. 
 * 
 * <p>All attributes in this class are private.<p>
 */
public class EditCustomerController implements Initializable {

	@FXML private AnchorPane editCustomerAnchor;
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
	
	@FXML private Button cancelBtn;
	@FXML private Button updateBtn;
	
	private AnchorPane pane = null;
	private CustomerEntity customer;
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
		
		// Format datepicker. Check out the documentation of DatePickerFormat
		DatePickerFormat.format(birthdayPick, "dd/MM/yyyy");
	}
	
	@FXML
	public void actionUpdate() {
		
		if (this.validateFields()) {
			
			String msg = "Do you really want to update this user?";
			FactoryDialogWindow fdw = new FactoryDialogWindow();
			Dialog dig = fdw.makeDiagOption(msg);
			
			if (dig.isOption()) {
				DAO<CustomerEntity> dao = new CustomerDAO();
				try {
					
					this.getChanges();
					
					if (dao.update(this.customer)) {
						msg = "User infomations has been updated successfully";
					} else {
						msg = "Something happended user information wasn't updated.";
					}
					fdw.makeDiagInfo(msg);
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
			// Just a simple FXML loader
			pane = FXMLLoader.load(getClass()
					.getResource("/com/raul/rental_shop/Ultra_Vision/view/customer/CustomerView.fxml"));
			editCustomerAnchor.getChildren().setAll(pane);
			this.finalize();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	private void getChanges() {
		
		this.customer.setFirstname(this.nameField.getText());
		this.customer.setLastname(this.surnameField.getText());
		this.customer.setPhonenumber(this.phoneField.getText());
		
		LocalDate birthday = this.birthdayPick.getValue();
		
		this.customer.setBirthday(birthday.toString());
		this.customer.setStreet(this.streetField.getText());
		this.customer.setCity(this.cityField.getText());
		this.customer.setCountry(this.countryField.getText());

		int privilageId = this.privilegeBox.getSelectionModel()
				.getSelectedIndex();

		if (privilageId == 1) {
			this.customer.setPrivilege("Admin");
		} else {
			this.customer.setPrivilege("Customer");
		}

		int memberPlanId = this.memberBox.getSelectionModel()
				.getSelectedIndex();

		switch(memberPlanId) {
		
		case 0:
			this.customer.setMembershipPlan("ML");
		break;

		case 1:
			this.customer.setMembershipPlan("VL");
		break;

		case 2:
			this.customer.setMembershipPlan("TL");
		break;

		case 3:
			this.customer.setMembershipPlan("PR");
		break;
		
		default:
			this.customer.setMembershipPlan("ML");

		}

		if (!this.passwordField.getText().isEmpty()) {
			this.customer.setPassword(this.passwordField.getText());
		}

	}
	
	public void loadData() {
		Optional<CustomerEntity> optional = Optional.ofNullable(this.customer);
		CustomerEntity c = optional.orElse(new NullCustomerEntity());
		
		this.nameField.setText(c.getFirstname());
		this.surnameField.setText(c.getLastname());
		this.phoneField.setText(c.getPhonenumber());
		this.birthdayPick.setValue(LocalDate.parse(c.getBirthday()));
		this.streetField.setText(c.getStreet());
		this.cityField.setText(c.getCity());
		this.countryField.setText(c.getCountry());
		
		int privilageId = 0;
		
		if (c.getPrivilege().equalsIgnoreCase("Admin")) {
			privilageId = 1;
		} else {
			privilageId = 0;
		}
		
		this.privilegeBox.getSelectionModel().select(privilageId);
		
		int memberPlanId = 0;
		
		switch(c.getMembershipPlan()) {
			case "ML":
				memberPlanId = 0;
			break;
			
			case "VL":
				memberPlanId = 1;
			break;
			
			case "TL":
				memberPlanId = 2;
			break;
			
			case "PR":
				memberPlanId = 3;
			break;
		}
		
		this.memberBox.getSelectionModel().select(memberPlanId);
		
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
		
		if (!birthday.matches("^(3[01]|[12][0-9]|0[1-9]])/(1[0-2]|0[1-9])/[0-9]{4}$")) {
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
	 * This method returns an object reference type CustomerEntity
	 * 
	 * @return customer which is a type of CustomerEntity.
	 */
	public CustomerEntity getCustomer() {
		return customer;
	}

	/**
	 * This method will set a CustomerEntity value to the object.
	 * 
	 * @param customer is a type of CustomerEntity.
	 */
	public void setCustomer(CustomerEntity customer) {
		this.customer = customer;
	}
	
	
}
