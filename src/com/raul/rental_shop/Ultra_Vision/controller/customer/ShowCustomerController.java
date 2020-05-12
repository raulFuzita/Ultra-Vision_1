package com.raul.rental_shop.Ultra_Vision.controller.customer;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.raul.rental_shop.Ultra_Vision.model.customer.CustomerEntity;
import com.raul.rental_shop.Ultra_Vision.model.customer.NullCustomerEntity;
import com.raul.rental_shop.Ultra_Vision.util.dateformat.DateFormat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class ShowCustomerController implements Initializable {

	@FXML private AnchorPane showCustomerAnchor;
	@FXML private Label nameLabel;
	@FXML private Label surnameLabel;
	@FXML private Label phoneLabel;
	@FXML private Label birthdayLabel;
	@FXML private Label streetLabel;
	@FXML private Label cityLabel;
	@FXML private Label countryLabel;
	@FXML private Label membershipLabel;
	@FXML private Pane mainDiv;
	
	@FXML private Button cancelBtn;
	@FXML private Button editBtn;
	
	private AnchorPane pane = null;
	private CustomerEntity customer;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		loadData();
	}
	
	@FXML
	public void actionEdit() {
		
		String path = "/com/raul/rental_shop/Ultra_Vision/view/customer/EditCustomerView.fxml";
		FXMLLoader loader = new FXMLLoader();
		try {
			loader.setLocation(getClass().getResource(path));
			this.pane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		EditCustomerController scc = loader.getController();
		scc.setCustomer(customer);
		scc.loadData();
		
		this.mainDiv.getChildren().setAll(this.pane);
	}
	
	@FXML
	public void actionCancel() {
		
		try {
			pane = FXMLLoader.load(getClass()
					.getResource("/com/raul/rental_shop/Ultra_Vision/view/customer/CustomerView.fxml"));
			showCustomerAnchor.getChildren().setAll(pane);
			this.finalize();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	public void loadData() {
		Optional<CustomerEntity> optional = Optional.ofNullable(this.customer);
		CustomerEntity c = optional.orElse(new NullCustomerEntity());
		
		this.nameLabel.setText(c.getFirstname());
		this.surnameLabel.setText(c.getLastname());
		this.phoneLabel.setText(c.getPhonenumber());
		
		String date = c.getBirthday();
		date = DateFormat.format(date, "yyyy-MM-dd", "dd/MM/yyyy");
		this.birthdayLabel.setText(date);
		
		this.streetLabel.setText(c.getStreet());
		this.cityLabel.setText(c.getCity());
		this.countryLabel.setText(c.getCountry());
		this.membershipLabel.setText(c.getMembershipPlan());
	}

	public CustomerEntity getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerEntity customer) {
		this.customer = customer;
	}

}
