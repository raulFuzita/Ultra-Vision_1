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

public class LoginController implements Initializable {

	@FXML private TextField membershipField;
	@FXML private ImageView imageView;
	@FXML private PasswordField passwordFiled;
	@FXML private CheckBox showPwd;
	@FXML private Button cancelBtn;
	@FXML private Button loginBtn;
	@FXML private AnchorPane loginAnchor;
	@FXML private Label hiddenPwd;
	@FXML private Label memberNumLabel;
	
	private Stage root = null;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
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
	
	@FXML
	public void login() {
		
		String membNumber = membershipField.getText();
		String password = passwordFiled.getText();
		
		if (valMemberInput()) {
			
			CustomerDAO dao = new CustomerDAO();
			MembershipCardEntity m = new MembershipCardEntity();
			MembershipCardDAO mDAO = new MembershipCardDAO();
			
			try {
				
				CustomerEntity user = dao.get(Integer.parseInt(membNumber));
				
				if(user.getPassword().equals(password)) {
					System.out.println("You're logged");
					Session.INSTANCE.set(user);
					
					m = mDAO.get(user.getMembershipCardNumber());
					
					if (m.getMembershipCardNumber() == 0) {
						
						m.setMembershipCardNumber(user.getMembershipCardNumber());
						m.setPoints(0);
						
						if (!mDAO.add(m)) {
							System.out.println("Error to create a Membership card");
						}
					}
					
					root = (Stage) loginAnchor.getScene().getWindow();
					loadMainView();
					root.close();
				} else {
					System.out.println("Fails");
				}
				
			} catch (NumberFormatException | SQLException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	@FXML
	public void showPassword() {
		if (showPwd.isSelected()) {
			this.hiddenPwd.setText(passwordFiled.getText());
			this.hiddenPwd.setVisible(true);
		} else {
			this.hiddenPwd.setText("");
			this.hiddenPwd.setVisible(false);
		}
	}
	
	@FXML
	public boolean valMemberInput(){
		
		String inputText = this.membershipField.getText();
		if (inputText.matches("([0-9])")) {
			this.memberNumLabel.setVisible(false);
			return true;
		}
		
		this.memberNumLabel.setVisible(true);
		return false;
	}

	@FXML
	public void close() {
		System.exit(0);
	}
	
	private void loadMainView() {
		try {
			Parent parent = (Parent) FXMLLoader.load(getClass()
					.getResource("/com/raul/rental_shop/Ultra_Vision/view/main/MainView.fxml"));
			Stage stage = new Stage();
			stage.setScene(new Scene(parent));
			stage.getScene().getStylesheets().add(getClass()
					.getResource("/com/raul/rental_shop/Ultra_Vision/view/css/stylesheet.css")
					.toExternalForm());
			stage.setResizable(false);
			
			BufferedImage img = ImageIO.read(new File("resources/images/icon.png"));
			Image icon = SwingFXUtils.toFXImage(img, null );
			
			stage.getIcons().add(icon);
			stage.setTitle("Ultra Vision");
			
			stage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
