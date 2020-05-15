package com.raul.rental_shop.Ultra_Vision.controller.checkout;

import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import com.raul.rental_shop.Ultra_Vision.model.Session;
import com.raul.rental_shop.Ultra_Vision.model.checkout.CheckoutEntity;
import com.raul.rental_shop.Ultra_Vision.model.customer.CustomerDAO;
import com.raul.rental_shop.Ultra_Vision.model.customer.CustomerEntity;
import com.raul.rental_shop.Ultra_Vision.model.customer.MembershipCardDAO;
import com.raul.rental_shop.Ultra_Vision.model.customer.MembershipCardEntity;
import com.raul.rental_shop.Ultra_Vision.model.rental.RentalDAO;
import com.raul.rental_shop.Ultra_Vision.model.rental.RentalEntity;
import com.raul.rental_shop.Ultra_Vision.util.CreditCardValidation;
import com.raul.rental_shop.Ultra_Vision.util.dialogwindow.Dialog;
import com.raul.rental_shop.Ultra_Vision.util.dialogwindow.FactoryDialogWindow;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
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
 * <p>CheckoutController is a controller class to manage operations for a<br>
 * checkout. See the documentation of the methods. </p>
 * 
 * @role This class works as a controller that is a kinda bridge between 
 * the view and model. This controller will take care of the data exchange between
 * CheckoutView and CheckoutEntity.
 * 
 * <p>All attributes in this class are private.<p>
 */
public class CheckoutController implements Initializable {

	// ============= FXML Attributes =========================
	/*
	 * Each FXML attribute is linked to the XML elements in the fxml file.
	 */
	@FXML private AnchorPane checkoutAnchor;
	@FXML private Pane mainDiv;
	
	// Table elements
	@FXML private TableView<CheckoutEntity> table;
	@FXML private TableColumn<CheckoutEntity, Integer> codeCol;
	@FXML private TableColumn<CheckoutEntity, String> nameCol;
	@FXML private TableColumn<CheckoutEntity, String> formatCol;
	@FXML private TableColumn<CheckoutEntity, Integer> membershipCol;
	@FXML private TableColumn<CheckoutEntity, String> customerCol;
	@FXML private TableColumn<CheckoutEntity, Double> costCol;
	
	// Label elements
	@FXML private Label pointsBalanceLabel;
	@FXML private Label itemsLabel;
	@FXML private Label pointsLabel;
	@FXML private Label subtotalLabel;
	
	// Radio button and group elements
	@FXML private ToggleGroup usePoints;
	@FXML private RadioButton notUsePoints;
	@FXML private RadioButton yesUsePoints;
	
	// Button elements
	@FXML private Button deleteBtn;
	@FXML private Button payBtn;
	
	// ============== Other Attributes =======================
	private double value; // It'll keep the sum of all the items in the checkout
	private double discount; // It'l keep the discount to change the XML element
	private int points; // It'll keep the points to change the XML element
	private int cupom; // It'll keep the total discount
	
	// It'll store what is inside of the customer Session title list.
	private List<CheckoutEntity> titles = new ArrayList<>();
	// It'll be used to store a reference of the customer Session class.
	private CheckoutEntity rowData = null;
	/* It is an instance of the FactoryDialogWindow to display popup window.
	 * All further details are available in its documentation.*/
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
		
		// Stores the Session title list
		this.titles = Session.INSTANCE.getTitles();

		/*
		 * The code get a row selection or double click event is from an article
		 * in the stackoverflow. The article link is available below.
		 * 
		 * https://stackoverflow.com/questions/26563390/
		 * detect-doubleclick-on-row-of-tableview-javafx
		 */
		this.table.setRowFactory( tv -> {
			TableRow<CheckoutEntity> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 1 && (!row.isEmpty())) {
					this.rowData = row.getItem();
				}
			});
			return row;
		});
		
		this.loadBill(); // Calls method loadill. Check out the documentation
		this.populateTableView(); // Calls method loadill. Check out the documentation
		
		/*
		 * Collection.sort will order all the data stored in the title list
		 * in an increasing order. This will happen because TitleEntity class
		 * implements Comparable and Override the method compareTo.
		 * 
		 * The Collection.reverse will order in a decreasing order. This is necessary
		 * to apply the points deduction. The idea is automatically find the most
		 * expensive titles and deduct their price. What the feature! lol
		 */
		Collections.sort(this.titles);
		Collections.reverse(this.titles);
	}
	
	/**
	 * This method is in charged of load all values displayed in the GUI window
	 * according to the values stored in the class attributes.
	 */
	private void loadBill() {
		
		// Gets the cost of all titles and sum
		this.value = 0;
		for (CheckoutEntity c : this.titles) {
			this.value += c.getCost();
		}
		
		// Update the values on the window.
		this.itemsLabel.setText("€ " + this.value);
		this.points = this.loadPoints();
		this.pointsBalanceLabel.setText("" + this.points);
		this.subtotalLabel.setText("€ " + (this.value - this.discount));
	}
	
	/**
	 * This method will check if the user bank card was registered before if not
	 * it'll popup a window requiring the user to fill this out.
	 */
	@FXML
	public void actionPay() {
		
		// Gets the user reference from the Session.
		CustomerEntity user = Session.INSTANCE.get();
		boolean success = false;
		// This object reference will be able to get the user input
		Dialog d = null;
		
		// If there is no card register yet mthod getBankCard returns null
		if (user.getBankCard() == null) {
			
			/* Displays a popup window showing a pattern to enter a valid bank card*/
			d = fdw.makeDiagInput("Please, enter your bank card number"
					+ "\nFormat expected: XXXX-XXXX-XXXX-XXXX");
			
			// This boolean will return true if it's a valid card. Otherwise false.
			boolean isCard = false;
			/* If the user clicks on No or close the popup window it will
			 * return a null. It has to be handle.*/
			String bankCard = (d.output() != null) ? d.output() : "";
			
			/* After the user input is handled then it's validated.*/
			isCard = CreditCardValidation.isCreditCard(bankCard);

			/* If isCard value is true it should go on. Otherwise an error
			 * message is displayed. */
			if (isCard) {
				// Gets the user answer if he/she agrees with the message
				d = fdw.makeDiagOption("Are you sure you want to continue?");
				if (d.isOption()) {
					
					// Instantiates a customer DAO class
					CustomerDAO cDAO = new CustomerDAO();
					// Set the card number into the customer database table
					user.setBankCard(bankCard);
					try {
						cDAO.update(user);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					success = this.confirmTransaction();
				} else {
					this.notUsePoints.setSelected(true);
					this.actionNotPoints();
				}
			} else {
				// Just show a error message
				fdw.makeDiagInfo("It's not a valid bank card number!");
			}
			
		} else {
			
			/* If a customer has a bank card registered in the system it'll
			 * ask if the customer agrees with the rent. Once it's accorded 
			 * there is no refund. */
			d = fdw.makeDiagOption("Are you sure you want to continue?");
			if (d.isOption()) {
				/* It'll pass the responsibility of to another method that 
				 * returns a boolean*/
				success = this.confirmTransaction();
			}
		}
		
		if (success) {
			/* A nice message is displayed to make the customer aware that
			 * the transaction has been done successfully.*/
			fdw.makeDiagInfo("Transaction has been done successfully.");
		}
		
		// Calls loadBill again to update the values on the Checkout window
		this.loadBill();
		/* After all data is deleted from a title list the table on the should
		 * reload without no records.*/
		this.populateTableView();
	}
	
	/**
	 * This method will a delete a title that he/she put in the basket.
	 */
	@FXML
	public void actionDelete() {
		/* If no row of the table is selected there is no way to which title
		 * the customer means to delete.*/
		if (this.rowData == null) {
			// It'll require the customer to select a row first.
			fdw.makeDiagInfo("Select a row first");
		} else {
			
			/* It'll ask the customer if he/she is sure to delete the selected
			 * title. It can't be undone.*/
			Dialog d = fdw.makeDiagOption("Are you sure you wnat to delete it?");
			
			// If the answer is positive the title will be deleted.
			if (d.isOption()) {
				
				/* Then the selected title will be removed from the title list
				 * and again, the table will be reload.*/
				Session.INSTANCE.getTitles().remove(this.rowData);
				this.populateTableView();
			}
		}
	}
	
	/**
	 * This method will calculate and check if a customer can or cannot use 
	 * its points. If a customer can use its points then the method will
	 * calculate the discount by deducting from the most expansive title of 
	 * the list.
	 */
	@FXML
	public void actionUsePoints() {
		
		// If points is bigger than 100 should go on.
		if (this.points >= 100 && this.value > 0) {
			
			/* Each 100 points should be considered as a cupom that deducts 
			 * the price from a title item.*/
			this.cupom = this.points / 100;
			
			/* According to each most expensive item the discount is sum to the
			 * discount variable. */
			for (int i = 0; i < cupom; i++) {
				discount += this.titles.get(i).getCost();
			}
			
			/* When everything is done it should update what the user see on the
			 * screen. */
			this.pointsLabel.setText("€ " + this.discount);
			this.subtotalLabel.setText("€ " + (this.value - this.discount));
		}
	}
	
	/**
	 * This method will reset all applied discounts if the customer switch
	 * back to the no use points option.
	 */
	@FXML
	public void actionNotPoints() {
		// reset everything
		this.discount = 0;
		this.cupom = 0;
		this.pointsLabel.setText("€ " + this.discount);
		this.subtotalLabel.setText("€ " + (this.value - this.discount));
	}
	
	/**
	 * This method will do all the job to take all the titles references and
	 * stores them in the rental table with the customer id.
	 * It also holds the mechanism to deduct the points discount from the bill
	 * and update the remaining points to the MembershipCard table.
	 * 
	 * @return true if all the operations are done properly.
	 */
	private boolean confirmTransaction() {
		
		RentalEntity re = new RentalEntity();
		RentalDAO dao = new RentalDAO();
		MembershipCardEntity mb = new MembershipCardEntity();
		MembershipCardDAO mDAO = new MembershipCardDAO();
		int userId = Session.INSTANCE.get().getMembershipCardNumber();
		int gainPoint = 0;
		
		for (CheckoutEntity c : titles) {
			LocalDateTime rentDate = LocalDateTime.now();
			re.setCustomerMembershipNumber(c.getMembership());
			re.setTitleCode(c.getCode());
			re.setRentAt(Timestamp.valueOf(rentDate));
			re.setReturnAt(Timestamp.valueOf(rentDate.plusHours(72)));
			re.setReturned(false);
			
			try {
				dao.add(re);
				
				/* If there is any cupom it means the customer wants to use
				 * the loyalty card.*/
				if (this.cupom > 0) {
					// decrease 100 from the points
					this.points -= 100;
					/* decrease 1 cupom, when it becomes zero the loop will
					 * ignore this If statement*/
					this.cupom--;
					/* set the points of a object reference for the class 
					 * MembershipCardEntity, and sets the id based on the
					 * customer Session id.*/
					mb.setPoints(points);
					mb.setMembershipCardNumber(re.getCustomerMembershipNumber());
					// Updated the information to the database.
					mDAO.update(mb);
				} else {
					/* Increments 10 points for each title that its price wasn't
					 * deduct by the points. */
					gainPoint += 10;
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		

		try {
			/* It gets the points balance, then sum with the points gained if 
			 * there is any, and for the last it is added to the customer 
			 * points.  */
			mb = mDAO.get(userId);
			gainPoint += mb.getPoints();
			mb.setPoints(gainPoint);
			mb.setMembershipCardNumber(userId);
			mDAO.update(mb);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
			
		this.actionNotPoints();
		
		// Clears the title list 
		Session.INSTANCE.getTitles().clear();
		this.value = 0;
		this.discount = 0;
		
		return true;
	}
	
	/**
	 * This method method will retrieve the customer points from the database.
	 * 
	 * @return a membership card points which is an int value.
	 */
	private int loadPoints() {
		
		MembershipCardEntity mbc = new MembershipCardEntity();
		MembershipCardDAO dao = new MembershipCardDAO();
		
		int membershipNum = Session.INSTANCE.get().getMembershipCardNumber();
		
		try {
			mbc = dao.get(membershipNum);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return mbc.getPoints();
	}

	/**
	 * This method will get the titles from the customer session and load to 
	 * the JavaFX table.
	 */
	private void populateTableView() {
		
		List<CheckoutEntity> list = Session.INSTANCE.getTitles();
		
		ObservableList<CheckoutEntity> obs = FXCollections
				.observableArrayList(list);
		
		/* It is important to highlight that PropertyFactory will look for
		 * the getter methods. You have to ignore the prefix get and put 
		 * the rest of the name of the method in the argument. */
		codeCol.setCellValueFactory(new PropertyValueFactory<>("Code"));
		nameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
		formatCol.setCellValueFactory(new PropertyValueFactory<>("Format"));
		membershipCol.setCellValueFactory(new PropertyValueFactory<>("Membership"));
		customerCol.setCellValueFactory(new PropertyValueFactory<>("Fullname"));
		costCol.setCellValueFactory(new PropertyValueFactory<>("Cost"));
		
		// After everything is set each column is finally added to the table.
		this.table.setItems(obs);
		
	}
}
