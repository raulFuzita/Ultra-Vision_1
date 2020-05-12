package com.raul.rental_shop.Ultra_Vision.util.dialogwindow;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class DialogInputController implements Initializable, Dialog {

	@FXML private AnchorPane popup;
	@FXML private TextArea textArea;
	@FXML private TextField inputField;
	@FXML private Button yesBtn;
	@FXML private Button noBtn;
	private String input;
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
		this.input = this.inputField.getText();
		this.option = true;
		this.closeWindow();
	}
	
	@FXML
	public void actionNo() {
		this.input = null;
		this.option = false;
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
		return this.input;
	}
}
