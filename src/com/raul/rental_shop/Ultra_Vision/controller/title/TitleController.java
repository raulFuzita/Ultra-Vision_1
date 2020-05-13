package com.raul.rental_shop.Ultra_Vision.controller.title;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.raul.rental_shop.Ultra_Vision.model.DAO;
import com.raul.rental_shop.Ultra_Vision.model.Session;
import com.raul.rental_shop.Ultra_Vision.model.checkout.CheckoutEntity;
import com.raul.rental_shop.Ultra_Vision.model.customer.CustomerEntity;
import com.raul.rental_shop.Ultra_Vision.model.customer.NullCustomerEntity;
import com.raul.rental_shop.Ultra_Vision.model.rental.RentalDAO;
import com.raul.rental_shop.Ultra_Vision.model.title.MusicDAO;
import com.raul.rental_shop.Ultra_Vision.model.title.MusicEntity;
import com.raul.rental_shop.Ultra_Vision.model.title.TVDAO;
import com.raul.rental_shop.Ultra_Vision.model.title.TVEntity;
import com.raul.rental_shop.Ultra_Vision.model.title.TitleDAO;
import com.raul.rental_shop.Ultra_Vision.model.title.TitleEntity;
import com.raul.rental_shop.Ultra_Vision.model.title.VideoDAO;
import com.raul.rental_shop.Ultra_Vision.model.title.VideoEntity;
import com.raul.rental_shop.Ultra_Vision.util.dateformat.DateFormat;
import com.raul.rental_shop.Ultra_Vision.util.dialogwindow.Dialog;
import com.raul.rental_shop.Ultra_Vision.util.dialogwindow.FactoryDialogWindow;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class TitleController implements Initializable {

	@FXML private AnchorPane titleAnchor;
	@FXML private TextField searchField;
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
	@FXML private Label countBkLabel;
	@FXML private HBox bottomSide;
	
	private AnchorPane pane = null;
	private TitleEntity rowData = null;
	FactoryDialogWindow dialogMaker = new FactoryDialogWindow();
	private MusicEntity ms;
	private VideoEntity vd;
	private TVEntity tv;
	private EditTitleController etc = null;
	private CustomerEntity customer;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		this.populateTableView();
		int counter = Session.INSTANCE.getTitles().size();
		countBkLabel.setText(""+counter);
		
		customer = Session.INSTANCE.get();
		
		Optional<CustomerEntity> optional = Optional.ofNullable(customer);
		CustomerEntity c = optional.orElse(new NullCustomerEntity());
		
		if (!c.getPrivilege().equalsIgnoreCase("admin")) {
			this.deleteBtn.setVisible(false);
			this.updateBtn.setVisible(false);
			this.addBtn.setVisible(false);
		}
		
		// https://stackoverflow.com/questions/26563390/detect-doubleclick-on-row-of-tableview-javafx
		this.table.setRowFactory( tv -> {
			TableRow<TitleEntity> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 1 && (!row.isEmpty())) {
					this.rowData = row.getItem();
				} else if (event.getClickCount() == 2 && (!row.isEmpty())) {
					this.rowData = row.getItem();
					this.actionView();
				}
			});
			return row;
		});
	}
	
	@FXML
	private void searchChanged() {
		
		String text = this.searchField.getText();
		if (text.isEmpty()) {
			populateTableView();
		} else {
			actionSearch(text);
		}
		
		System.out.println("Working");
		
	}
	
	public void actionSearch(String text) {
		
		text = this.searchField.getText();
		
		TitleDAO dao = new TitleDAO();
		List<TitleEntity> list = null;
		try {
			list = dao.search(text);
		
		ObservableList<TitleEntity> obs = FXCollections.observableArrayList(list);
		
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
	
	@FXML
	public void actionDelete() {
		if (rowData == null) {
			String msg = "Select a row first";
			FactoryDialogWindow fdw = new FactoryDialogWindow();
			fdw.makeDiagInfo(msg);
		} else {
			
			String msg = "Do you really want to delete this title?";
			FactoryDialogWindow fdw = new FactoryDialogWindow();
			Dialog dig = fdw.makeDiagOption(msg);
			
			if (dig.isOption()) {
				DAO<TitleEntity> dao = new TitleDAO();
				try {
					if (dao.remove(rowData)) {
						msg = "Title has been deleted successfully";
					} else {
						msg = "Something happended the title wasn't removed";
					}
					fdw.makeDiagInfo(msg);
					populateTableView();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@FXML
	public void actionBasket() {
		if (rowData == null) {
			String msg = "Select a row first";
			FactoryDialogWindow fdw = new FactoryDialogWindow();
			fdw.makeDiagInfo(msg);
		} else {
			
			int rentedTitle = Session.INSTANCE.getTitles().size();
			int id = Session.INSTANCE.get().getMembershipCardNumber();
			int rents = 0;
			String membershipUser = Session.INSTANCE.get().getMembershipPlan();
			String membershipTitle = this.rowData.getTypeTitle();
			boolean allowedBasket = false;
			
			if (membershipUser.equalsIgnoreCase(membershipTitle)) {
				allowedBasket = true;
			} else if (membershipUser.equalsIgnoreCase("PR")) {
				allowedBasket = true;
			}
			
			RentalDAO dao = new RentalDAO();
			
			try {
				rents = dao.ownerRents(id);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			rentedTitle += rents;
			
			if (rentedTitle < 4) {
				if (allowedBasket) {
					CustomerEntity c = Session.INSTANCE.get();
					CheckoutEntity ce = new CheckoutEntity(c, this.rowData);
					Session.INSTANCE.getTitles().add(ce);
					int counter = Session.INSTANCE.getTitles().size();
					countBkLabel.setText(""+counter);
					System.out.println("Title's added successfully");
				} else {
					this.dialogMaker.makeDiagInfo(
						"Your membership doesn't support this plan. "
						+ "\nPlease, upgrade your membership.");
				}
			} else {
				this.dialogMaker.makeDiagInfo("You can't rent more than 4 titles!");
			}
		}
	}
	
	@FXML
	public void actionAdd() {
		String path = "/com/raul/rental_shop/Ultra_Vision/view/title/OptionTitleView.fxml";
		loadChildView(path);
	}
	
	@FXML
	public void actionView() {
		if (rowData == null) {
			String msg = "Select a row first";
			FactoryDialogWindow fdw = new FactoryDialogWindow();
			fdw.makeDiagInfo(msg);
		} else {
			
			String path = "/com/raul/rental_shop/Ultra_Vision/view/title/ShowTitleView.fxml";
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource(path));
			ShowTitleController stc = null;
			
			try {
				this.pane = loader.load();
				stc = loader.getController();
				
				String typePlan = this.rowData.getTypeTitle();
				
				
				if (typePlan.equalsIgnoreCase("ML")) {
					
					stc.getAdditional1Label().setText("Artist:");
					stc.getAdditional2Label().setText("Album:");
					MusicEntity ms = new MusicEntity();
					MusicDAO dao = new MusicDAO();
					ms = dao.get(this.rowData.getCode());
					
					stc.getAdditional1Field().setText(ms.getArtist());
					stc.getAdditional2Field().setText(ms.getAlbum());
					
				} else if (typePlan.equalsIgnoreCase("VL")) {
					
					stc.getAdditional1Label().setText("Director:");
					stc.getAdditional2Label().setText("Description:");
					VideoEntity vd = new VideoEntity();
					VideoDAO dao = new VideoDAO();
					
					System.out.println("code: " + this.rowData.getCode());
					
					vd = dao.get(this.rowData.getCode());
					
					System.out.println(vd);
					
					stc.getAdditional1Field().setText(vd.getDirector());
					stc.getAdditional2Field().setText(vd.getDescription());
					
				} else if (typePlan.equalsIgnoreCase("TV")) {
					
					stc.getAdditional1Label().setText("Artist:");
					stc.getAdditional2Label().setVisible(false);
					stc.getAdditional2Field().setVisible(false);
					TVEntity tv = new TVEntity();
					TVDAO dao = new TVDAO();
					tv = dao.get(this.rowData.getCode());
					stc.getAdditional1Field().setText(tv.getCharacterSeries());
					
				}
				
				stc.getNameField().setText(this.rowData.getName());
				stc.getGenreField().setText(this.rowData.getGenre());
				stc.getMediaLabel().setText(this.rowData.getMediaFormat());
				stc.getCostField().setText("€ " + this.rowData.getCost());
				
				String date = this.rowData.getYear();
				date = DateFormat.format(date, "yyyy-MM-dd", "dd/MM/yyyy");
				stc.getYearField().setText(date);
				
			} catch (IOException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
			this.mainDiv.getChildren().setAll(pane);
		}
	}
	
	@FXML
	public void actionEdit() {
		
		if (rowData == null) {
			String msg = "Select a row first";
			FactoryDialogWindow fdw = new FactoryDialogWindow();
			fdw.makeDiagInfo(msg);
		} else {
			
			String path = "/com/raul/rental_shop/Ultra_Vision/view/title/EditTitleView.fxml";
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource(path));
			
			
			try {
				this.pane = loader.load();
				etc = loader.getController();
				
				String typePlan = this.rowData.getTypeTitle();
				
				System.out.println(this.rowData);
				
				if (typePlan.equalsIgnoreCase("ML")) {
					
					etc.getCdRadio().setSelected(true);
					etc.getDvdRadio().setDisable(true);
					etc.getBluerayRadio().setDisable(true);
					
					etc.getAdditional1Label().setText("Artist:");
					etc.getAdditional2Label().setText("Album:");
					
					MusicDAO dao = new MusicDAO();
					ms = dao.get(this.rowData.getCode());
					etc.getAdditional1Field().setText(ms.getArtist());
					etc.getAdditional2Field().setText(ms.getAlbum());
					
				} else if (typePlan.equalsIgnoreCase("VL")) {
					
					etc.getCdRadio().setDisable(true);
					etc.getDvdRadio().setDisable(false);
					etc.getBluerayRadio().setDisable(false);
					
					if (this.rowData.getMediaFormat().equalsIgnoreCase("DVD")) {
						etc.getDvdRadio().setSelected(true);
					} else {
						etc.getBluerayRadio().setSelected(true);
					}
					
					etc.getAdditional1Label().setText("Director:");
					etc.getAdditional2Label().setText("Description:");
					
					VideoDAO dao = new VideoDAO();
					vd = dao.get(this.rowData.getCode());
					etc.getAdditional1Field().setText(vd.getDirector());
					etc.getAdditional2Field().setText(vd.getDescription());
					
				} else if (typePlan.equalsIgnoreCase("TV")) {
					
					etc.getCdRadio().setDisable(true);
					etc.getDvdRadio().setDisable(false);
					etc.getBluerayRadio().setDisable(false);
					
					if (this.rowData.getMediaFormat().equalsIgnoreCase("DVD")) {
						etc.getDvdRadio().setSelected(true);
					} else {
						etc.getBluerayRadio().setSelected(true);
					}
					
					etc.getAdditional1Label().setText("Artist:");
					etc.getAdditional2Label().setVisible(false);
					etc.getAdditional2Field().setVisible(false);
					
					TVDAO dao = new TVDAO();
					tv = dao.get(this.rowData.getCode());
					etc.getAdditional1Field().setText(tv.getCharacterSeries());
					
				}
				
				etc.getNameField().setText(this.rowData.getName());
				etc.getGenreField().setText(this.rowData.getGenre());
				etc.getCostField().setText("" + this.rowData.getCost());
				etc.getDatePicker().setValue(LocalDate.parse(this.rowData.getYear()));
				
				etc.getUpdateBtn().setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent arg0) {
						
						if (etc.validateFields()) {
							
							if (typePlan.equalsIgnoreCase("ML")) {
								MusicDAO dao = new MusicDAO();
								try {
									dao.update(ms);
								} catch (SQLException e) {
									e.printStackTrace();
								}
							} else if (typePlan.equalsIgnoreCase("VL")) {
								VideoDAO dao = new VideoDAO();
								try {
									dao.update(vd);
								} catch (SQLException e) {
									e.printStackTrace();
								}
							} else if (typePlan.equalsIgnoreCase("TV")) {
								TVDAO dao = new TVDAO();
								try {
									dao.update(tv);
								} catch (SQLException e) {
									e.printStackTrace();
								}
							}
							
							TitleEntity t = new TitleEntity();
							
							t.setCode(rowData.getCode());
							t.setName(etc.getNameField().getText());
							t.setGenre(etc.getGenreField().getText());
							
							
							LocalDate year = etc.getDatePicker().getValue();
							t.setYear(year.toString());
							
							
							t.setTypeTitle(rowData.getTypeTitle());
							
							double cost = Double.parseDouble(etc.getCostField().getText());
							t.setCost(cost);
							
							RadioButton chk = (RadioButton) etc.getMediaGroup().getSelectedToggle();
							
							t.setMediaFormat(chk.getText());
							
							
							TitleDAO tDAO = new TitleDAO();
							try {
								if(tDAO.update(t)) {
									dialogMaker.makeDiagInfo("It has been updated successfully");
								} else {
									dialogMaker.makeDiagInfo("Unfortunatelly it wasn't updated.");
								}
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
					}
				});
				
			} catch (IOException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
			this.mainDiv.getChildren().setAll(pane);
		}
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
