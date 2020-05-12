package com.raul.rental_shop.Ultra_Vision.controller.main;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.raul.rental_shop.Ultra_Vision.model.Session;
import com.raul.rental_shop.Ultra_Vision.model.customer.CustomerEntity;
import com.raul.rental_shop.Ultra_Vision.model.customer.NullCustomerEntity;

import javafx.concurrent.Task;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainController implements Initializable {

	@FXML private AnchorPane mainAnchor;
	@FXML private VBox sidebar;
	@FXML private Label closeBtn;
	@FXML private Button homeBtn;
	@FXML private Button rentalBtn;
	@FXML private Button titlesBtn;
	@FXML private Button customersBtn;
	@FXML private Pane mainDiv;
	
	@FXML private static Stage loginPopup;
	private CustomerEntity customer;
	
	private AnchorPane pane = null;
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		customer = Session.INSTANCE.get();
		
		Optional<CustomerEntity> optional = Optional.ofNullable(customer);
		CustomerEntity c = optional.orElse(new NullCustomerEntity());
		
		if (!c.getPrivilege().equalsIgnoreCase("admin")) {
			this.sidebar.getChildren().remove(customersBtn);
		}
		actionHome();
	}
	
	
	@FXML
	public void actionHome() {
		String path = "/com/raul/rental_shop/Ultra_Vision/view/main/Home.fxml";
		loadChildView(path);
	}
	
	@FXML
	public void actionRental() {
		String path = "/com/raul/rental_shop/Ultra_Vision/view/rental/RentalView.fxml";
		loadChildView(path);
	}
	
	@FXML
	public void actionTitle() {
		String path = "/com/raul/rental_shop/Ultra_Vision/view/title/TitleView.fxml";
		loadChildView(path);
	}
	
	@FXML
	public void actionCustomer() {
		String path = "/com/raul/rental_shop/Ultra_Vision/view/customer/CustomerView.fxml";
		loadChildView(path);
	}
	
	@FXML
	public void actionCheckout() {
		String path = "/com/raul/rental_shop/Ultra_Vision/view/checkout/CheckoutView.fxml";
		loadChildView(path);
	}
	
	private void loadChildView(final String path) {
		try {
			pane = FXMLLoader.load(getClass()
					.getResource(path));
			this.mainDiv.getChildren().setAll(pane);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
