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

public class CheckoutController implements Initializable {

	@FXML private AnchorPane checkoutAnchor;
	@FXML private Pane mainDiv;
	@FXML private TableView<CheckoutEntity> table;
	
	@FXML private ToggleGroup usePoints;
	@FXML private TableColumn<CheckoutEntity, Integer> codeCol;
	@FXML private TableColumn<CheckoutEntity, String> nameCol;
	@FXML private TableColumn<CheckoutEntity, String> formatCol;
	@FXML private TableColumn<CheckoutEntity, Integer> membershipCol;
	@FXML private TableColumn<CheckoutEntity, String> customerCol;
	@FXML private TableColumn<CheckoutEntity, Double> costCol;
	
	@FXML private Label pointsBalanceLabel;
	@FXML private Label itemsLabel;
	@FXML private Label pointsLabel;
	@FXML private Label subtotalLabel;
	
	
	@FXML private RadioButton notUsePoints;
	@FXML private RadioButton yesUsePoints;
	
	@FXML private Button deleteBtn;
	@FXML private Button payBtn;
	
	private double value;
	private double discount;
	private int points;
	private int cupom;
	List<CheckoutEntity> titles = new ArrayList<>();
	private CheckoutEntity rowData = null;
	FactoryDialogWindow fdw = new FactoryDialogWindow();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		this.titles = Session.INSTANCE.getTitles();

		// https://stackoverflow.com/questions/26563390/detect-doubleclick-on-row-of-tableview-javafx
		this.table.setRowFactory( tv -> {
			TableRow<CheckoutEntity> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 1 && (!row.isEmpty())) {
					this.rowData = row.getItem();
				}
			});
			return row;
		});
		
		this.loadBill();
		this.populateTableView();
		Collections.sort(this.titles);
		Collections.reverse(this.titles);
	}
	
	private void loadBill() {
		
		for (CheckoutEntity c : this.titles) {
			this.value += c.getCost();
		}
		
		this.itemsLabel.setText("€ " + this.value);
		this.points = this.loadPoints();
		this.pointsBalanceLabel.setText("" + this.points);
		this.subtotalLabel.setText("€ " + (this.value - this.discount));
	}
	
	@FXML
	public void actionPay() {
		
		CustomerEntity user = Session.INSTANCE.get();
		boolean success = false;
		Dialog d = null;
		
		if (user.getBankCard() == null) {
			
			d = fdw.makeDiagInput("Please, enter your bank card number"
					+ "\nFormat expected: XXXX-XXXX-XXXX-XXXX");
			
			boolean isCard = false;
			String bankCard = (d.output() != null) ? d.output() : "";
			
			isCard = CreditCardValidation.isCreditCard(bankCard);

			
			if (isCard) {
				d = fdw.makeDiagOption("Are you sure you want to continue?");
				if (d.isOption()) {
					
					CustomerDAO cDAO = new CustomerDAO();
					user.setBankCard(bankCard);
					try {
						cDAO.update(user);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					success = this.confirmTransaction();
				}
			} else {
				fdw.makeDiagInfo("It's not a valid bank card number!");
			}
			
		} else {
			
			d = fdw.makeDiagOption("Are you sure you want to continue?");
			if (d.isOption()) {
				success = this.confirmTransaction();
			}
		}
		
		if (success) {
			fdw.makeDiagInfo("Transaction has been done successfully.");
		}
		
		this.loadBill();
		this.populateTableView();
	}
	
	@FXML
	public void actionDelete() {
		if (this.rowData == null) {
			fdw.makeDiagInfo("Select a row first");
		} else {
			
			Dialog d = fdw.makeDiagOption("Are you sure you wnat to delete it?");
			
			if (d.isOption()) {
				Session.INSTANCE.getTitles().remove(this.rowData);
				this.populateTableView();
			}
		}
	}
	
	@FXML
	public void actionUsePoints() {
		
		if (this.points >= 100 && this.value > 0) {
			
			this.cupom = this.points / 100;
			
			for (int i = 0; i < cupom; i++) {
				discount += this.titles.get(i).getCost();
			}
			
			this.pointsLabel.setText("€ " + this.discount);
			this.subtotalLabel.setText("€ " + (this.value - this.discount));
		}
	}
	
	@FXML
	public void actionNotPoints() {
		
		this.discount = 0;
		this.cupom = 0;
		this.pointsLabel.setText("€ " + this.discount);
		this.subtotalLabel.setText("€ " + (this.value - this.discount));
	}
	
	private boolean confirmTransaction() {
		
		RentalEntity re = new RentalEntity();
		RentalDAO dao = new RentalDAO();
		MembershipCardEntity mb = new MembershipCardEntity();
		MembershipCardDAO mDAO = new MembershipCardDAO();
		
		for (CheckoutEntity c : titles) {
			LocalDateTime rentDate = LocalDateTime.now();
			re.setCustomerMembershipNumber(c.getMembership());
			re.setTitleCode(c.getCode());
			re.setRentAt(Timestamp.valueOf(rentDate));
			re.setReturnAt(Timestamp.valueOf(rentDate.plusHours(72)));
			re.setReturned(false);
			
			try {
				dao.add(re);
				
				if (this.cupom > 0) {
					this.points -= 100;
					this.cupom--;
					mb.setPoints(points);
					mb.setMembershipCardNumber(re.getCustomerMembershipNumber());
					mDAO.update(mb);
				} 
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (notUsePoints.isSelected()) {
			
			int userId = Session.INSTANCE.get().getMembershipCardNumber();
			try {
				mb = mDAO.get(userId);
				int gainPoint = titles.size() * 10;
				gainPoint += mb.getPoints();
				mb.setPoints(gainPoint);
				mb.setMembershipCardNumber(userId);
				mDAO.update(mb);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
		}
		
		this.actionNotPoints();
		
		Session.INSTANCE.getTitles().clear();
		this.value = 0;
		this.discount = 0;
		
		return true;
	}
	
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

	private void populateTableView() {
		
		List<CheckoutEntity> list = Session.INSTANCE.getTitles();
		
		ObservableList<CheckoutEntity> obs = FXCollections
				.observableArrayList(list);
		
		codeCol.setCellValueFactory(new PropertyValueFactory<>("Code"));
		nameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
		formatCol.setCellValueFactory(new PropertyValueFactory<>("Format"));
		membershipCol.setCellValueFactory(new PropertyValueFactory<>("Membership"));
		customerCol.setCellValueFactory(new PropertyValueFactory<>("Fullname"));
		costCol.setCellValueFactory(new PropertyValueFactory<>("Cost"));
		
		this.table.setItems(obs);
		
	}
}
