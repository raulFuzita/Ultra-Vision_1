package com.raul.rental_shop.Ultra_Vision.util.dialogwindow;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FactoryDialogWindow {
	
	private static Parent parent = null;
	private static Stage stagePopup;

	public Dialog makeDiagInfo(String message) {
		try {
			return windowMaker("DialogInfoWindow.fxml", message);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Dialog makeDiagOption(String message) {
		try {
			return windowMaker("DialogOptionWindow.fxml", message);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Dialog makeDiagInput(String message) {
		try {
			return windowMaker("DialogInputWindow.fxml", message);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private Dialog windowMaker(final String filename, String message) 
			throws IOException {
		
		Package pk = this.getClass().getPackage();
		String pkPath = "/" + (pk.getName().replaceAll("\\.", "/"));
		String path = pkPath + "/" + filename;
		
		FXMLLoader loader = new FXMLLoader();
		
		loader.setLocation(getClass().getResource(path));
		
		parent = loader.load();
		
		Dialog controller = loader.getController();
		controller.setTextArea(message);
		
		stagePopup = new Stage();
		stagePopup.setScene(new Scene(parent));
		
		stagePopup.setResizable(false);
		stagePopup.initModality(Modality.APPLICATION_MODAL);
		stagePopup.showAndWait();
		
		return controller;
	}
}
