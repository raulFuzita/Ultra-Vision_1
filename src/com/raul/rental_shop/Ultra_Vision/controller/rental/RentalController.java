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

/**
 * @author Raul Macedo Fuzita
 * 
 * @version 13.05.20
 * <br>Version is based on the last update date.
 * 
 * @apiNote
 * <p>RentalController will load a table with all rental records but also<br>
 * provide a control pane to execute simple operations such as<br>
 * view a rental record information, delete a rental record, and<br>
 * return one.<br>
 * Some of available operations are handle half in the due controller and<br>
 * another half by this controller.</p>
 * 
 * @role This will manage other controllers that change a rental state.
 * 
 * <p>All attributes in this class are private.<p>
 */
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
	
	/**
	 * This method is invoked after @FXML is set. The parameters of initialize
	 * is not used here.
	 * 
	 * @param arg0 is a type of URL.
	 * @param arg1 is a type of ResourceBundle;
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		customer = Session.INSTANCE.get();
		
		Optional<CustomerEntity> optional = Optional.ofNullable(customer);
		CustomerEntity c = optional.orElse(new NullCustomerEntity());
		
		// Hides what doesn't concern a customer who is not an admin.
		if (!c.getPrivilege().equalsIgnoreCase("admin")) {
			this.deleteBtn.setVisible(false);
		}
		
		/*
		 * The code get a row selection or double click event is from an article
		 * in the stackoverflow. The article link is available below.
		 * 
		 * https://stackoverflow.com/questions/26563390/
		 * detect-doubleclick-on-row-of-tableview-javafx
		 */
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
	
	public void actionSearch(String text) {
		
		text = this.searchField.getText();
		
		RentalDAO dao = new RentalDAO();
		List<RentalEntity> list;
		
		try {
			
			
			
			if (customer.getPrivilege().equalsIgnoreCase("admin")) {
				list = dao.search(text);
			} else {
				list = dao.ownerSearch(customer.getMembershipCardNumber(), text);
			}
			
			
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

	/**
	 * This method will get the titles from the customer session and load to 
	 * the JavaFX table.
	 */
	private void populateTableView() {
		
		RentalDAO dao = new RentalDAO();
		List<RentalEntity> list = null;
		
		try {
			
			if (customer.getPrivilege().equalsIgnoreCase("admin")) {
				list = dao.getAll();
			} else {
				list = dao.ownersItems(customer.getMembershipCardNumber());
			}
			
			ObservableList<RentalEntity> obs = FXCollections
					.observableArrayList(list);
			
			/* It is important to highlight that PropertyFactory will look for
			 * the getter methods. You have to ignore the prefix get and put 
			 * the rest of the name of the method in the argument. */
			rentAtCol.setCellValueFactory(new PropertyValueFactory<>("RentAt"));
			returnAtCol.setCellValueFactory(new PropertyValueFactory<>("ReturnAt"));
			
			customerCol.setCellValueFactory(new PropertyValueFactory<>("Fullname"));
			membershipCol.setCellValueFactory(
						new PropertyValueFactory<>("CustomerMembershipNumber"));
			titleCol.setCellValueFactory(new PropertyValueFactory<>("TitleName"));
			mediaCol.setCellValueFactory(new PropertyValueFactory<>("MediaFormat"));
			
			// After everything is set each column is finally added to the table.
			this.table.setItems(obs);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
