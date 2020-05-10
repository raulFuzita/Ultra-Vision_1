package com.raul.rental_shop.Ultra_Vision.controller.title;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import com.raul.rental_shop.Ultra_Vision.model.title.TitleDAO;
import com.raul.rental_shop.Ultra_Vision.model.title.TitleEntity;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class TitleController implements Initializable {

	@FXML private AnchorPane titleAnchor;
	@FXML private TextField searchBtn;
	@FXML private TableView<TitleEntity> table;
	@FXML private TableColumn<TitleEntity, Integer> codeCol;
	@FXML private TableColumn<TitleEntity, String> nameCol;
	@FXML private TableColumn<TitleEntity, Double> costCol;
	@FXML private TableColumn<TitleEntity, String> memberPlanCol;
	@FXML private TableColumn<TitleEntity, String> yearCol;
	@FXML private TableColumn<TitleEntity, String> mediaCol;
	@FXML private Pane mainDiv;
	
	@FXML private Button deleteBtn;
	@FXML private Button updateBtn;
	@FXML private Button basketBtn;
	@FXML private Button viewBtn;
	@FXML private Button addBtn;
	
	private AnchorPane pane = null;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.populateTableView();
	}
	
	@FXML
	public void actionDelete() {
		
	}
	
	@FXML
	public void actionBasket() {
		
	}
	
	@FXML
	public void actionAdd() {
		String path = "/com/raul/rental_shop/Ultra_Vision/view/title/OptionTitleView.fxml";
		loadChildView(path);
	}
	
	@FXML
	public void actionView() {
		String path = "/com/raul/rental_shop/Ultra_Vision/view/title/ShowTitleView.fxml";
		loadChildView(path);
	}
	
	@FXML
	public void actionEdit() {
		String path = "/com/raul/rental_shop/Ultra_Vision/view/title/EditTitleView.fxml";
		loadChildView(path);
	}
	
	private void loadChildView(final String path) {
		try {
			pane = FXMLLoader.load(getClass()
					.getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.mainDiv.getChildren().setAll(pane);
	}
	
	private void populateTableView() {
		
		TitleDAO dao = new TitleDAO();
		
		try {
			List<TitleEntity> list = dao.getAll();
			
			ObservableList<TitleEntity> obs = FXCollections
					.observableArrayList(list);
			
			codeCol.setCellValueFactory(new PropertyValueFactory<>("Code"));
			nameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
			costCol.setCellValueFactory(new PropertyValueFactory<>("Cost"));
			memberPlanCol.setCellValueFactory(new PropertyValueFactory<>("TypeTitle"));
			yearCol.setCellValueFactory(new PropertyValueFactory<>("Year"));
			mediaCol.setCellValueFactory(new PropertyValueFactory<>("MediaFormat"));
			
			this.table.setItems(obs);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
