package com.raul.rental_shop.Ultra_Vision.controller.customer;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import com.raul.rental_shop.Ultra_Vision.model.DAO;
import com.raul.rental_shop.Ultra_Vision.model.customer.CustomerDAO;
import com.raul.rental_shop.Ultra_Vision.model.customer.CustomerEntity;
import com.raul.rental_shop.Ultra_Vision.util.dialogwindow.Dialog;
import com.raul.rental_shop.Ultra_Vision.util.dialogwindow.FactoryDialogWindow;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * @author Raul Macedo Fuzita
 * 
 * @version 13.05.20
 * <br>Version is based on the last update date.
 * 
 * @apiNote
 * <p>CustomerController will load a table with all customers but also<br>
 * provide a control pane to execute simple operations such as<br>
 * view a customer information, delete a customer, edit a customer, and add a new one.<br>
 * Some of available operations are handle half in the due controller and<br>
 * another half by this controller.</p>
 * 
 * @role This will manage other controllers that change a customer state or add new one.
 * 
 * <p>All attributes in this class are private.<p>
 */
public class CustomerController implements Initializable {

	@FXML private AnchorPane rentalAnchor;
	@FXML private TextField searchField;
	@FXML private TableView<CustomerEntity> table;
	@FXML private TableColumn<CustomerEntity, String> customerCol;
	@FXML private TableColumn<CustomerEntity, Integer> membershipCol;
	@FXML private TableColumn<CustomerEntity, String> memberPlan;
	@FXML private TableColumn<CustomerEntity, String> phoneCol;
	@FXML private TableColumn<CustomerEntity, String> addressCol;
	@FXML private Pane mainDiv;
	
	@FXML private Button deleteBtn;
	@FXML private Button editBtn;
	@FXML private Button viewBtn;
	@FXML private Button addBtn;
	
	private AnchorPane pane = null;
	private CustomerEntity rowData = null;
	
	/**
	 * This method is invoked after @FXML is set. The parameters of initialize
	 * is not used here.
	 * 
	 * @param arg0 is a type of URL.
	 * @param arg1 is a type of ResourceBundle;
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		populateTableView();
		
		/*
		 * The code get a row selection or double click event is from an article
		 * in the stackoverflow. The article link is available below.
		 * 
		 * https://stackoverflow.com/questions/26563390/
		 * detect-doubleclick-on-row-of-tableview-javafx
		 */
		this.table.setRowFactory( tv -> {
			TableRow<CustomerEntity> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 1 && (!row.isEmpty())) {
					this.rowData = row.getItem();
				} else if (event.getClickCount() == 2 && (!row.isEmpty())) {
					this.rowData = row.getItem();
					this.actionView();
				}
			});
			return row;
		});
	}
	
	/**
	 * If something is types in the search field this method is trigger.
	 * It'll manage if it should reload the table with all the data or 
	 * load only data was retrieve and filtered by the database.
	 */
	@FXML
	private void searchChanged() {
		
		String text = this.searchField.getText();
		if (text.isEmpty()) {
			populateTableView();
		} else {
			actionSearch(text);
		}
		
		System.out.println("Working");
		
	}
	
	/**
	 * This method will get all the customers from a customer table and load into
	 * a JavaFX table.
	 */
	public void actionSearch(String text) {
		
		text = this.searchField.getText();
		
		CustomerDAO dao = new CustomerDAO();
		List<CustomerEntity> list = null;
		try {
			list = dao.search(text);
		
		ObservableList<CustomerEntity> obs = FXCollections.observableArrayList(list);
		
		/* It is important to highlight that PropertyFactory will look for
		 * the getter methods. You have to ignore the prefix get and put 
		 * the rest of the name of the method in the argument. */
		customerCol.setCellValueFactory(new PropertyValueFactory<>("Firstname"));
		membershipCol.setCellValueFactory(new PropertyValueFactory<>("MembershipCardNumber"));
		memberPlan.setCellValueFactory(new PropertyValueFactory<>("MembershipPlan"));
		phoneCol.setCellValueFactory(new PropertyValueFactory<>("Phonenumber"));
		addressCol.setCellValueFactory(new PropertyValueFactory<>("Street"));
		
		// After everything is set each column is finally added to the table.
		this.table.setItems(obs);
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * This method will delete a customer record. A row has to be selected first.
	 */
	@FXML
	public void actionDelete() {
		if (rowData == null) {
			String msg = "Select a row first";
			FactoryDialogWindow fdw = new FactoryDialogWindow();
			fdw.makeDiagInfo(msg);
		} else {
			
			String msg = "Do you really want to delete this user?";
			FactoryDialogWindow fdw = new FactoryDialogWindow();
			Dialog dig = fdw.makeDiagOption(msg);
			
			if (dig.isOption()) {
				DAO<CustomerEntity> dao = new CustomerDAO();
				try {
					// Deletes the record selected
					if (dao.remove(rowData)) {
						msg = "User has been deleted successfully";
					} else {
						msg = "Something happended the user wasn't removed";
					}
					fdw.makeDiagInfo(msg);
					populateTableView();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * The an add button is trigger this method will load a new pane accroding to
	 * the path and name of the fxml file.
	 */
	@FXML
	public void actionAdd() {
		String path = "/com/raul/rental_shop/Ultra_Vision/view/customer/AddCustomerView.fxml";
		loadChildView(path);
	}
	
	/**
	 * This method will delete a customer record. A row has to be selected first.
	 */
	@FXML
	public void actionView() {
		
		if (rowData == null) {
			String msg = "Select a row first";
			FactoryDialogWindow fdw = new FactoryDialogWindow();
			fdw.makeDiagInfo(msg);
		} else {
			
			String path = "/com/raul/rental_shop/Ultra_Vision/view/customer/ShowCustomerView.fxml";
			FXMLLoader loader = new FXMLLoader();
			try {
				loader.setLocation(getClass().getResource(path));
				this.pane = loader.load();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			// Loader gets the controller of ShowCustomerController class
			ShowCustomerController scc = loader.getController();
			// Pass the selected customer to ShowCustomerController class.
			scc.setCustomer(rowData);
			// Checks the method documentation
			scc.loadData();
			// Pane is displayed
			this.mainDiv.getChildren().setAll(this.pane);
		}
	}
	
	/**
	 * This method will delete a customer record. A row has to be selected first.
	 */
	@FXML
	public void actionEdit() {
		
		if (rowData == null) {
			String msg = "Select a row first";
			FactoryDialogWindow fdw = new FactoryDialogWindow();
			fdw.makeDiagInfo(msg);
		} else {
			
			String path = "/com/raul/rental_shop/Ultra_Vision/view/customer/EditCustomerView.fxml";
			FXMLLoader loader = new FXMLLoader();
			try {
				loader.setLocation(getClass().getResource(path));
				this.pane = loader.load();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			// Loader gets the controller of EditCustomerController class
			EditCustomerController scc = loader.getController();
			// Pass the selected customer to EditCustomerController class.
			scc.setCustomer(rowData);
			// Pane is displayed
			scc.loadData();
			// Pane is displayed
			this.mainDiv.getChildren().setAll(this.pane);
		}
	}
	
	/**
	 * This method loads panes on this window by given a path and file fxml format.
	 * 
	 * @param path is a String type. It is expected a valid path and file fxml format.
	 */
	private FXMLLoader loadChildView(final String path) {
		
		FXMLLoader loader = new FXMLLoader();
		
		try {
			loader.setLocation(getClass().getResource(path));
			this.pane = loader.load();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.mainDiv.getChildren().setAll(pane);
		
		return loader;
	}
	
	/**
	 * This method will get the titles from the customer session and load to 
	 * the JavaFX table.
	 */
	private void populateTableView() {
		
		CustomerDAO dao = new CustomerDAO();
		try {
			List<CustomerEntity> list = dao.getAll();
			
			ObservableList<CustomerEntity> obs = FXCollections
					.observableArrayList(list);
			
			/* It is important to highlight that PropertyFactory will look for
			 * the getter methods. You have to ignore the prefix get and put 
			 * the rest of the name of the method in the argument. */
			customerCol.setCellValueFactory(new PropertyValueFactory<>("Firstname"));
			membershipCol.setCellValueFactory(new PropertyValueFactory<>("MembershipCardNumber"));
			memberPlan.setCellValueFactory(new PropertyValueFactory<>("MembershipPlan"));
			phoneCol.setCellValueFactory(new PropertyValueFactory<>("Phonenumber"));
			addressCol.setCellValueFactory(new PropertyValueFactory<>("Street"));
			
			// After everything is set each column is finally added to the table.
			this.table.setItems(obs);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

}
