package com.raul.rental_shop.Ultra_Vision.controller.rental;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class ShowRentalController implements Initializable {

	@FXML private AnchorPane titleAnchor;
	@FXML private Pane closeBtn;
	@FXML private Label rentedAtLabel;
	@FXML private Label returnAtLable;
	@FXML private Label mediaLabel;
	@FXML private Label membershipLabel;
	@FXML private Label customerLabel;
	@FXML private Label titleLabel;
	@FXML private Label codeLabel;
	@FXML private Label titleTypeLabel;
	
	@FXML private Button cancelBtn;
	
	private AnchorPane pane = null;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
				
	}
	
	@FXML
	public void actionClose() {
		
		try {
			pane = FXMLLoader.load(getClass()
					.getResource("/com/raul/rental_shop/Ultra_Vision/view/rental/RentalView.fxml"));
			titleAnchor.getChildren().setAll(pane);
			this.finalize();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
	}

	public Label getRentedAtLabel() {
		return rentedAtLabel;
	}

	public void setRentedAtLabel(String rentedAtLabel) {
		this.rentedAtLabel.setText(rentedAtLabel);
	}

	public Label getReturnAtLable() {
		return returnAtLable;
	}

	public void setReturnAtLable(String returnAtLable) {
		this.returnAtLable.setText(returnAtLable);;
	}

	public Label getMediaLabel() {
		return mediaLabel;
	}

	public void setMediaLabel(String mediaLabel) {
		this.mediaLabel.setText(mediaLabel);
	}

	public Label getMembershipLabel() {
		return membershipLabel;
	}

	public void setMembershipLabel(String membershipLabel) {
		this.membershipLabel.setText(membershipLabel);
	}

	public Label getCustomerLabel() {
		return customerLabel;
	}

	public void setCustomerLabel(String customerLabel) {
		this.customerLabel.setText(customerLabel);
	}

	public Label getTitleLabel() {
		return titleLabel;
	}

	public void setTitleLabel(String titleLabel) {
		this.titleLabel.setText(titleLabel);
	}

	public Label getCodeLabel() {
		return codeLabel;
	}

	public void setCodeLabel(String codeLabel) {
		this.codeLabel.setText(codeLabel);
	}

	public Label getTitleTypeLabel() {
		return titleTypeLabel;
	}

	public void setTitleTypeLabel(String titleTypeLabel) {
		this.titleTypeLabel.setText(titleTypeLabel);
	}

	
}
