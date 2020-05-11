package com.raul.rental_shop.Ultra_Vision.controller.rental;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.raul.rental_shop.Ultra_Vision.model.customer.CustomerEntity;
import com.raul.rental_shop.Ultra_Vision.model.customer.NullCustomerEntity;
import com.raul.rental_shop.Ultra_Vision.model.rental.RentalDAO;
import com.raul.rental_shop.Ultra_Vision.model.rental.RentalEntity;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class RentalController implements Initializable {

	@FXML private AnchorPane rentalAnchor;
	@FXML private TextField searchBtn;
	@FXML private TableView<RentalEntity> table;
	@FXML private TableColumn<RentalEntity, String> rentAtCol;
	@FXML private TableColumn<RentalEntity, String> returnAtCol;
	@FXML private TableColumn<RentalEntity, String> customerCol;
	@FXML private TableColumn<RentalEntity, Integer> membershipCol;
	@FXML private TableColumn<RentalEntity, String> titleCol;
	@FXML private TableColumn<RentalEntity, String> mediaCol;
	@FXML private Pane mainDiv;
	
	@FXML private Button deleteBtn;
	@FXML private Button editBtn;
	@FXML private Button viewBtn;
	@FXML private Button returnedBtn;
	
	private AnchorPane pane = null;
	private CustomerEntity customer;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		Optional<CustomerEntity> optional = Optional.ofNullable(customer);
		CustomerEntity c = optional.orElse(new NullCustomerEntity());
		
		/*if (!c.getPrivilege().equalsIgnoreCase("admin")) {
			this.deleteBtn.setVisible(false);
			this.updateBtn.setVisible(false);
		}*/
		
		populateTableView();
	}
	
	@FXML
	public void actionDelete() {
		
	}
	
	@FXML
	public void actionReturn() {
		
	}
	
	@FXML
	public void actionView() {
		String path = "/com/raul/rental_shop/Ultra_Vision/view/rental/ShowRentalView.fxml";
		loadChildView(path);
	}
	@FXML
	public void actionEdit() {
		String path = "/com/raul/rental_shop/Ultra_Vision/view/rental/EditRentalView.fxml";
		loadChildView(path);
	}
	
	private void loadChildView(final String path) {
		try {
			pane = FXMLLoader.load(getClass()
					.getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.mainDiv.getChildren().setAll(pane);
	}
	
	private void populateTableView() {
		
		RentalDAO dao = new RentalDAO();
		
		try {
			
			List<RentalEntity> list = dao.getAll();
			
			ObservableList<RentalEntity> obs = FXCollections
					.observableArrayList(list);
			
			rentAtCol.setCellValueFactory(new PropertyValueFactory<>("RentAt"));
			returnAtCol.setCellValueFactory(new PropertyValueFactory<>("ReturnAt"));
			customerCol.setCellValueFactory(new PropertyValueFactory<>("Fullname"));
			membershipCol.setCellValueFactory(
						new PropertyValueFactory<>("CustomerMembershipNumber"));
			titleCol.setCellValueFactory(new PropertyValueFactory<>("TitleName"));
			mediaCol.setCellValueFactory(new PropertyValueFactory<>("MediaFormat"));
			
			this.table.setItems(obs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
