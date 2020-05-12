package com.raul.rental_shop.Ultra_Vision.util.dialogwindow;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class DialogOptionController implements Initializable, Dialog {

	@FXML private AnchorPane popup;
	@FXML private TextArea textArea;
	@FXML private Button yesBtn;
	@FXML private Button noBtn;
	private boolean option;
	
	private Stage stage = new Stage();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Scene scene = popup.getScene();
		this.stage.setScene(scene);
	}
	
	@FXML
	public void loseFocus() {
		popup.setFocusTraversable(true);
	}

	@FXML
	public void actionYes() {
		option = true;
		this.closeWindow();
	}
	
	@FXML
	public void actionNo() {
		option = false;
		this.closeWindow();
	}
	
	private void closeWindow() {
		Stage stage = (Stage) popup.getScene().getWindow();
		stage.close();
	}
	
	@Override
	public String getTextArea() {
		return textArea.getText();
	}
	
	@Override
	public void setTextArea(String text) {
		this.textArea.setText(text);
	}

	@Override
	public boolean isOption() {
		return option;
	}
	
	public void show() {
		stage.showAndWait();
	}
	
	public void close() {
		stage.close();
	}

	@Override
	public String output() {
		// TODO Auto-generated method stub
		return null;
	}
}
