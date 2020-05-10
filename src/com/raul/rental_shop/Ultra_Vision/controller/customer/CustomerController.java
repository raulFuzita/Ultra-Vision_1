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
import com.raul.rental_shop.Ultra_Vision.util.dialogwindow.DialogInfoController;
import com.raul.rental_shop.Ultra_Vision.util.dialogwindow.FactoryDialogWindow;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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

public class CustomerController implements Initializable {

	@FXML private AnchorPane rentalAnchor;
	@FXML private TextField searchBtn;
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
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		populateTableView();
		
		// https://stackoverflow.com/questions/26563390/detect-doubleclick-on-row-of-tableview-javafx
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
	
	@FXML
	public void actionAdd() {
		String path = "/com/raul/rental_shop/Ultra_Vision/view/customer/AddCustomerView.fxml";
		loadChildView(path);
	}
	
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
			
			ShowCustomerController scc = loader.getController();
			scc.setCustomer(rowData);
			scc.loadData();
			
			this.mainDiv.getChildren().setAll(this.pane);
		}
	}
	
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
			
			EditCustomerController scc = loader.getController();
			scc.setCustomer(rowData);
			scc.loadData();
			
			this.mainDiv.getChildren().setAll(this.pane);
		}
	}
	
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
	
	private void populateTableView() {
		
		CustomerDAO dao = new CustomerDAO();
		try {
			List<CustomerEntity> list = dao.getAll();
			
			ObservableList<CustomerEntity> obs = FXCollections
					.observableArrayList(list);
			
			
			customerCol.setCellValueFactory(new PropertyValueFactory<>("Firstname"));
			membershipCol.setCellValueFactory(new PropertyValueFactory<>("MembershipCardNumber"));
			memberPlan.setCellValueFactory(new PropertyValueFactory<>("MembershipPlan"));
			phoneCol.setCellValueFactory(new PropertyValueFactory<>("Phonenumber"));
			addressCol.setCellValueFactory(new PropertyValueFactory<>("Street"));
			
			this.table.setItems(obs);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

}
