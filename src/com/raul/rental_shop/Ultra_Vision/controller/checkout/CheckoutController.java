package com.raul.rental_shop.Ultra_Vision.controller.checkout;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class CheckoutController implements Initializable {

	@FXML private AnchorPane checkoutAnchor;
	@FXML private Pane mainDiv;
	@FXML private TableView tableCol;
	
	@FXML private ToggleGroup usePoints;
	@FXML private TableColumn codeCol;
	@FXML private TableColumn nameCol;
	@FXML private TableColumn formatCol;
	@FXML private TableColumn membershipCol;
	@FXML private TableColumn customerCol;
	@FXML private TableColumn costCol;
	
	@FXML private Label pointsBalanceLabel;
	@FXML private Label itemsLabel;
	@FXML private Label pointsLabel;
	@FXML private Label overdueLabel;
	
	
	@FXML private RadioButton notUsePoints;
	@FXML private RadioButton yesUsePoints;
	
	@FXML private Button closeBtn;
	@FXML private Button deleteBtn;
	@FXML private Button payBtn;
	
	private AnchorPane pane = null;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
	}
	
	@FXML
	public void actionPay() {
		
	}
	
	@FXML
	public void actionDelete() {
		
	}
	
	@FXML
	public void actionClose() {
		try {
			pane = FXMLLoader.load(getClass()
					.getResource("/com/raul/rental_shop/Ultra_Vision/view/checkout/CheckoutView.fxml"));
			checkoutAnchor.getChildren().setAll(pane);
			this.finalize();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}
