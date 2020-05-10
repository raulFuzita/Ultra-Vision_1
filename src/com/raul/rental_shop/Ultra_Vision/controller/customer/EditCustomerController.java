package com.raul.rental_shop.Ultra_Vision.controller.customer;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import com.raul.rental_shop.Ultra_Vision.model.DAO;
import com.raul.rental_shop.Ultra_Vision.model.customer.CustomerDAO;
import com.raul.rental_shop.Ultra_Vision.model.customer.CustomerEntity;
import com.raul.rental_shop.Ultra_Vision.model.customer.NullCustomerEntity;
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
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		privilegeBox.getItems().addAll("Customer", "Admin");
		memberBox.getItems().addAll(
				"Music Lover",
				"Video Lover",
				"TV Lover",
				"Premium");
	}
	
	@FXML
	public void actionUpdate() {
		
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

	@FXML
	public void actionCancel() {
		
		try {
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
		
		String birthday = this.birthdayPick.getPromptText();
		
		// https://www.java-examples.com/convert-date-string-one-format-another-format-using-simpledateformat
		SimpleDateFormat sdfSource = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			Date date = (Date) sdfSource.parse(birthday);
			SimpleDateFormat sdfDestination = new SimpleDateFormat("yyyy-MM-dd");
			birthday = sdfDestination.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		this.customer.setBirthday(birthday);
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

	public CustomerEntity getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerEntity customer) {
		this.customer = customer;
	}
	
	
}
