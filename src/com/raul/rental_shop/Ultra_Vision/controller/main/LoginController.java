package com.raul.rental_shop.Ultra_Vision.controller.main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import com.raul.rental_shop.Ultra_Vision.model.Session;
import com.raul.rental_shop.Ultra_Vision.model.customer.CustomerDAO;
import com.raul.rental_shop.Ultra_Vision.model.customer.CustomerEntity;
import com.raul.rental_shop.Ultra_Vision.model.customer.MembershipCardDAO;
import com.raul.rental_shop.Ultra_Vision.model.customer.MembershipCardEntity;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * @author Raul Macedo Fuzita
 * 
 * @version 13.05.20
 * <br>Version is based on the last update date.
 * 
 * @apiNote
 * <p>LoginController is a simple controller class to handle the login view<br>
 * and login model.</p>
 * 
 * @role This class play a rolo as a login window
 * 
 * <p>All attributes in this class are private.<p>
 */
public class LoginController implements Initializable {

	@FXML private TextField membershipField;
	@FXML private ImageView imageView; // Reserver for the logo
	@FXML private PasswordField passwordFiled; // hide what you type in
	@FXML private CheckBox showPwd; // revels what is typed in the password field
	@FXML private Button cancelBtn; // simple cancel button
	@FXML private Button loginBtn; // simple button to trigger the login process.
	@FXML private AnchorPane loginAnchor;
	@FXML private Label hiddenPwd; // When showPwd checkbox is selected it show the password.
	@FXML private Label memberNumLabel; // just a label.
	
	private Stage root = null;
	
	/**
	 * This method is invoked after @FXML is set. The parameters of initialize
	 * is not used here.
	 * 
	 * @param arg0 is a type of URL.
	 * @param arg1 is a type of ResourceBundle;
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		/* Loads image file in the image buffer. When an image source is 
		 * out of the src folder you could face issues to load straight 
		 * from a Image, or ImageIO class. */
		BufferedImage img;
		try {
			img = ImageIO.read(new File("resources/images/shorter_logo2.jpg"));
			Image icon = SwingFXUtils.toFXImage(img, null );
			
			this.imageView.setImage(icon);
			this.imageView.setCache(true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		System.out.println("Login Window is running");
	}
	
	/**
	 * This method will validate credential and password of a customer.
	 */
	@FXML
	public void login() {
		
		// gets the id which is a membership number.
		String membNumber = membershipField.getText();
		// gets the password typed in the password field
		String password = passwordFiled.getText();
		
		// Calls the valMemberInput method to validate the typed membership.
		if (valMemberInput()) {
			
			// Establishes a connection to the customer table.
			CustomerDAO dao = new CustomerDAO();
			MembershipCardEntity m = new MembershipCardEntity();
			// Establishes a connection to the membership card table.
			MembershipCardDAO mDAO = new MembershipCardDAO();
			
			try {
				// Converts from String to int
				int id = Integer.parseInt(membNumber);
				// Gets a customer informations according to the id passed.
				CustomerEntity user = dao.get(id);
				
				// A password can't be null. If it is it returns an empty value.
				String input = (user.getPassword() != null) ? user.getPassword() : "";
				
				/* The value of the password field has to match with the password
				 * in the database.*/
				if(input.equals(password)) {
					
					System.out.println("You're logged");
					/* If customer membership and password matches to the one 
					 * n the database then it is stored in the session class.*/
					Session.INSTANCE.set(user);
					
					// based on the membership number a membership card is requested.
					m = mDAO.get(user.getMembershipCardNumber());
					
					/* If there is no record in the membership card table that 
					 * matches the customer membership number it is understood 
					 * this customer is a new brand account. SO it'll sets a 
					 * membership card loyalty. 
					 * If a customer has already a membership card this 
					 * verification is ignored. */
					if (m.getMembershipCardNumber() == 0) {
						
						// Sets a loyalty card
						m.setMembershipCardNumber(user.getMembershipCardNumber());
						m.setPoints(0);
						
						if (!mDAO.add(m)) {
							System.out.println("Error to create a Membership card");
						}
					}
					
					root = (Stage) loginAnchor.getScene().getWindow();
					// If everything is ok it loads the main window.
					loadMainView();
					root.close();
				} else {
					System.out.println("Fails: User doesn't exist");
				}
				
			} catch (NumberFormatException | SQLException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	/**
	 * This method will revel what a user is typing in the password field.
	 */
	@FXML
	public void showPassword() {
		// If shoPwd chckbox is selected the password is shown.
		if (showPwd.isSelected()) {
			this.hiddenPwd.setText(passwordFiled.getText());
			this.hiddenPwd.setVisible(true);
		} else {
			// Hide everything again.
			this.hiddenPwd.setText("");
			this.hiddenPwd.setVisible(false);
		}
	}
	
	/**
	 * This method will check if a membership number has no letter or symbols.
	 * 
	 * @return a boolean value. If a membership number match the pattern
	 * it is returned true. Otherwise false.
	 */
	@FXML
	public boolean valMemberInput(){
		
		String inputText = this.membershipField.getText();
		if (inputText.matches("^([0-9])+$")) {
			this.memberNumLabel.setVisible(false);
			return true;
		}
		
		this.memberNumLabel.setVisible(true);
		return false;
	}

	/**
	 * This method fills the system when it is called
	 */
	@FXML
	public void close() {
		System.exit(0);
	}
	
	/**
	 * This method will load all necessary dependencies for the main view.
	 * A fxml, css, and image is loaded to the main window.
	 */
	private void loadMainView() {
		try {
			// Just an ordinary declaration to load a fxml file
			Parent parent = (Parent) FXMLLoader.load(getClass()
					.getResource("/com/raul/rental_shop/Ultra_Vision/view/main/MainView.fxml"));
			
			Stage stage = new Stage();
			stage.setScene(new Scene(parent));
			
			// Just an ordinary declaration to load an external stylesheet
			stage.getScene().getStylesheets().add(getClass()
					.getResource("/com/raul/rental_shop/Ultra_Vision/view/css/stylesheet.css")
					.toExternalForm());
			
			// Not allow resize the window
			stage.setResizable(false);
			
			/* Loads image file in the image buffer. When an image source is 
			 * out of the src folder you could face issues to load straight 
			 * from a Image, or ImageIO class. */
			BufferedImage img = ImageIO.read(new File("resources/images/icon.png"));
			Image icon = SwingFXUtils.toFXImage(img, null );
			
			// Sets a icon for the main window
			stage.getIcons().add(icon);
			// Sets a title for main window
			stage.setTitle("Ultra Vision");
			// show the main window
			stage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
