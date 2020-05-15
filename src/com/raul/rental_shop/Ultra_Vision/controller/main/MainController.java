package com.raul.rental_shop.Ultra_Vision.controller.main;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.raul.rental_shop.Ultra_Vision.model.Session;
import com.raul.rental_shop.Ultra_Vision.model.customer.CustomerEntity;
import com.raul.rental_shop.Ultra_Vision.model.customer.NullCustomerEntity;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @author Raul Macedo Fuzita
 * 
 * @version 13.05.20
 * <br>Version is based on the last update date.
 * 
 * @apiNote
 * <p>MainController is a simple controller class to load others.</p>
 * 
 * @role This class host all other panes in its main pane component.
 * 
 * <p>All attributes in this class are private.<p>
 */
public class MainController implements Initializable {

	@FXML private AnchorPane mainAnchor;
	@FXML private VBox sidebar; // sort of layout that organizes horizontally
	@FXML private Button homeBtn; // It working like a nav side menu.
	@FXML private Button rentalBtn; // It working like a nav side menu.
	@FXML private Button titlesBtn; // It working like a nav side menu.
	@FXML private Button customersBtn; // It working like a nav side menu.
	@FXML private Pane mainDiv; // It working like a nav side menu.
	
	@FXML private static Stage loginPopup;
	private CustomerEntity customer; // gets the customer logged at the moment.
	
	private AnchorPane pane = null;
	
	/**
	 * This method is invoked after @FXML is set. The parameters of initialize
	 * is not used here.
	 * 
	 * @param arg0 is a type of URL.
	 * @param arg1 is a type of ResourceBundle;
	 */
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		/* Gets the customer logged reference to hide elements that doesn't
		 * concern a user with low privilege access. */
		customer = Session.INSTANCE.get();
		
		/* If for some reason the session return a null object the system won't
		 * crash, it'll use a Null Object pattern.*/
		Optional<CustomerEntity> optional = Optional.ofNullable(customer);
		CustomerEntity c = optional.orElse(new NullCustomerEntity());
		
		// Hides what doesn't concern a customer who is not an admin.
		if (!c.getPrivilege().equalsIgnoreCase("admin")) {
			this.sidebar.getChildren().remove(customersBtn);
		}
		// It loads the home page
		actionHome();
	}
	
	
	@FXML
	public void actionHome() {
		// Path for a fxml file
		String path = "/com/raul/rental_shop/Ultra_Vision/view/main/Home.fxml";
		loadChildView(path);
	}
	
	@FXML
	public void actionRental() {
		// Path for a fxml file
		String path = "/com/raul/rental_shop/Ultra_Vision/view/rental/RentalView.fxml";
		loadChildView(path);
	}
	
	@FXML
	public void actionTitle() {
		// Path for a fxml file
		String path = "/com/raul/rental_shop/Ultra_Vision/view/title/TitleView.fxml";
		loadChildView(path);
	}
	
	@FXML
	public void actionCustomer() {
		// Path for a fxml file
		String path = "/com/raul/rental_shop/Ultra_Vision/view/customer/CustomerView.fxml";
		loadChildView(path);
	}
	
	@FXML
	public void actionCheckout() {
		// Path for a fxml file
		String path = "/com/raul/rental_shop/Ultra_Vision/view/checkout/CheckoutView.fxml";
		loadChildView(path);
	}
	
	/**
	 * This method loads panes on this window by given a path and file fxml format.
	 * 
	 * @param path is a String type. It is expected a valid path and file fxml format.
	 */
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
