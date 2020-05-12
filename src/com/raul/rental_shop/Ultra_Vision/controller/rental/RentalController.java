package com.raul.rental_shop.Ultra_Vision.controller.rental;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.raul.rental_shop.Ultra_Vision.model.Session;
import com.raul.rental_shop.Ultra_Vision.model.customer.CustomerDAO;
import com.raul.rental_shop.Ultra_Vision.model.customer.CustomerEntity;
import com.raul.rental_shop.Ultra_Vision.model.customer.NullCustomerEntity;
import com.raul.rental_shop.Ultra_Vision.model.rental.RentalDAO;
import com.raul.rental_shop.Ultra_Vision.model.rental.RentalEntity;
import com.raul.rental_shop.Ultra_Vision.util.dateformat.DateFormat;
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

public class RentalController implements Initializable {

	@FXML private AnchorPane rentalAnchor;
	@FXML private TextField searchField;
	@FXML private TableView<RentalEntity> table;
	@FXML private TableColumn<RentalEntity, String> rentAtCol;
	@FXML private TableColumn<RentalEntity, String> returnAtCol;
	@FXML private TableColumn<RentalEntity, String> customerCol;
	@FXML private TableColumn<RentalEntity, Integer> membershipCol;
	@FXML private TableColumn<RentalEntity, String> titleCol;
	@FXML private TableColumn<RentalEntity, String> mediaCol;
	@FXML private Pane mainDiv;
	
	@FXML private Button deleteBtn;
	@FXML private Button viewBtn;
	@FXML private Button returnedBtn;
	
	private AnchorPane pane = null;
	private CustomerEntity customer;
	private RentalEntity rowData = null;
	private FactoryDialogWindow fdw = new FactoryDialogWindow();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		customer = Session.INSTANCE.get();
		
		Optional<CustomerEntity> optional = Optional.ofNullable(customer);
		CustomerEntity c = optional.orElse(new NullCustomerEntity());
		
		if (!c.getPrivilege().equalsIgnoreCase("admin")) {
			this.deleteBtn.setVisible(false);
		}
		
		// https://stackoverflow.com/questions/26563390/detect-doubleclick-on-row-of-tableview-javafx
		this.table.setRowFactory( tv -> {
			TableRow<RentalEntity> row = new TableRow<>();
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
		
		populateTableView();
	}
	
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
	
	public void actionSearch(String text) {
		
		text = this.searchField.getText();
		
		RentalDAO dao = new RentalDAO();
		
		try {
			List<RentalEntity> list = dao.search(text);
			
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
	
	@FXML
	public void actionDelete() {
		if (rowData == null) {
			String msg = "Select a row first";
			fdw.makeDiagInfo(msg);
		} else {
			String msg = "Do you realy want to delete it?";
			if (fdw.makeDiagOption(msg).isOption()) {
				RentalDAO rDAO = new RentalDAO();
				try {
					if (rDAO.remove(this.rowData)) {
						fdw.makeDiagInfo("Record has ben deleted successfully!");
						this.populateTableView();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@FXML
	public void actionReturn() {
		if (rowData == null) {
			String msg = "Select a row first";
			fdw.makeDiagInfo(msg);
		} else {
			
			LocalDateTime today = LocalDateTime.now();
			LocalDateTime returned = this.rowData.getReturnAt().toLocalDateTime();
			Duration duration = Duration.between(today, returned);
			int difference = (int) duration.toDays();
			
			if (difference < 0) {
				int overdue = Math.abs(difference);
				String msg = "You supposed to returned the titles " + overdue
						+ " day(s) ago. \nYou'll be charged extra € 5.00.";
				fdw.makeDiagInfo(msg);
			}
			
			RentalDAO rDAO = new RentalDAO();
			try {
				if (rDAO.remove(this.rowData)) {
					fdw.makeDiagInfo("Title has been returned successfully");
					this.populateTableView();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	@FXML
	public void actionView() {
		String path = "/com/raul/rental_shop/Ultra_Vision/view/rental/ShowRentalView.fxml";
		
		if (rowData == null) {
			String msg = "Select a row first";
			fdw.makeDiagInfo(msg);
		} else {
			
			FXMLLoader loader = new FXMLLoader();
			
			loader.setLocation(getClass().getResource(path));
			try {
				this.pane = loader.load();
				ShowRentalController src = loader.getController();
				
				String from = "yyyy-MM-dd hh:mm:ss", to = "hh:mm:ss dd/MM/yyyy";
				String date = this.rowData.getRentAt().toString();
				date = DateFormat.format(date, from, to);
				src.setRentedAtLabel(date);

				date = this.rowData.getReturnAt().toString();
				date = DateFormat.format(date, from, to);
				src.setReturnAtLable(date);
				
				
				src.setMembershipLabel(""+this.rowData.getCustomerMembershipNumber());
				src.setCustomerLabel(this.rowData.getFullname());
				src.setTitleLabel(this.rowData.getTitleName());
				src.setCodeLabel(""+this.rowData.getTitleCode());
				src.setMediaLabel(this.rowData.getMediaFormat());
				
				String typePlan = this.rowData.getTypePlan();
				
				if (typePlan.equalsIgnoreCase("TL")) {
					typePlan = "TV Lover";
				} else if (typePlan.equalsIgnoreCase("VL")) {
					typePlan = "Video Lover";
				} else {
					typePlan = "Music Lover";
				}
				
				src.setTitleTypeLabel(typePlan);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			this.mainDiv.getChildren().setAll(pane);
		}
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
