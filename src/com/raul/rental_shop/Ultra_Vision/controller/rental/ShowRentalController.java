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

/**
 * @author Raul Macedo Fuzita
 * 
 * @version 13.05.20
 * <br>Version is based on the last update date.
 * 
 * @apiNote
 * <p>ShowTitleController will load some information about a rental record.</p>
 * 
 * @role This class will load some informations about a selected rental record. 
 * 
 * <p>All attributes in this class are private.<p>
 */
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
	
	/**
	 * This method is invoked after @FXML is set. The parameters of initialize
	 * is not used here.
	 * 
	 * @param arg0 is a type of URL.
	 * @param arg1 is a type of ResourceBundle;
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// It is kept for future purpose.
	}
	
	/**
	 * This method close the current pane and load the previous one.
	 */
	@FXML
	public void actionClose() {
		
		try {
			// Just a simple FXML loader
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

	// ============ Getter Methods ================
	
	/**
	 * This method returns an object reference type Label
	 * 
	 * @return rentedAtLabel which is a type of Label.
	 */
	public Label getRentedAtLabel() {
		return rentedAtLabel;
	}

	/**
	 * This method will set a String text value to the component.
	 * 
	 * @param rentedAtLabel is a type of String.
	 */
	public void setRentedAtLabel(String rentedAtLabel) {
		this.rentedAtLabel.setText(rentedAtLabel);
	}

	/**
	 * This method returns an object reference type Label
	 * 
	 * @return returnAtLable which is a type of Label.
	 */
	public Label getReturnAtLable() {
		return returnAtLable;
	}

	/**
	 * This method will set a String text value to the component.
	 * 
	 * @param returnAtLable is a type of String.
	 */
	public void setReturnAtLable(String returnAtLable) {
		this.returnAtLable.setText(returnAtLable);;
	}

	/**
	 * This method returns an object reference type Label
	 * 
	 * @return mediaLabel which is a type of Label.
	 */
	public Label getMediaLabel() {
		return mediaLabel;
	}

	/**
	 * This method will set a String text value to the component.
	 * 
	 * @param mediaLabel is a type of String.
	 */
	public void setMediaLabel(String mediaLabel) {
		this.mediaLabel.setText(mediaLabel);
	}

	/**
	 * This method returns an object reference type Label
	 * 
	 * @return membershipLabel which is a type of Label.
	 */
	public Label getMembershipLabel() {
		return membershipLabel;
	}

	/**
	 * This method will set a String text value to the component.
	 * 
	 * @param membershipLabel is a type of String.
	 */
	public void setMembershipLabel(String membershipLabel) {
		this.membershipLabel.setText(membershipLabel);
	}

	/**
	 * This method returns an object reference type Label
	 * 
	 * @return customerLabel which is a type of Label.
	 */
	public Label getCustomerLabel() {
		return customerLabel;
	}

	/**
	 * This method will set a String text value to the component.
	 * 
	 * @param customerLabel is a type of String.
	 */
	public void setCustomerLabel(String customerLabel) {
		this.customerLabel.setText(customerLabel);
	}

	/**
	 * This method returns an object reference type Label
	 * 
	 * @return titleLabel which is a type of Label.
	 */
	public Label getTitleLabel() {
		return titleLabel;
	}

	/**
	 * This method will set a String text value to the component.
	 * 
	 * @param titleLabel is a type of String.
	 */
	public void setTitleLabel(String titleLabel) {
		this.titleLabel.setText(titleLabel);
	}

	/**
	 * This method returns an object reference type Label
	 * 
	 * @return codeLabel which is a type of Label.
	 */
	public Label getCodeLabel() {
		return codeLabel;
	}

	/**
	 * This method will set a String text value to the component.
	 * 
	 * @param codeLabel is a type of String.
	 */
	public void setCodeLabel(String codeLabel) {
		this.codeLabel.setText(codeLabel);
	}

	/**
	 * This method returns an object reference type Label
	 * 
	 * @return titleTypeLabel which is a type of Label.
	 */
	public Label getTitleTypeLabel() {
		return titleTypeLabel;
	}

	/**
	 * This method will set a String text value to the component.
	 * 
	 * @param titleTypeLabel is a type of String.
	 */
	public void setTitleTypeLabel(String titleTypeLabel) {
		this.titleTypeLabel.setText(titleTypeLabel);
	}

	
}
