package com.raul.rental_shop.Ultra_Vision.controller.title;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import com.raul.rental_shop.Ultra_Vision.model.title.MusicDAO;
import com.raul.rental_shop.Ultra_Vision.model.title.MusicEntity;
import com.raul.rental_shop.Ultra_Vision.model.title.TVDAO;
import com.raul.rental_shop.Ultra_Vision.model.title.TVEntity;
import com.raul.rental_shop.Ultra_Vision.model.title.TitleDAO;
import com.raul.rental_shop.Ultra_Vision.model.title.TitleEntity;
import com.raul.rental_shop.Ultra_Vision.model.title.VideoDAO;
import com.raul.rental_shop.Ultra_Vision.model.title.VideoEntity;
import com.raul.rental_shop.Ultra_Vision.util.dialogwindow.FactoryDialogWindow;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * @author Raul Macedo Fuzita
 * 
 * @version 13.05.20
 * <br>Version is based on the last update date.
 * 
 * @apiNote
 * <p>OptionTitleController will instantiate a concrete class that<br>
 * extends TitleEntity.</p>
 * 
 * @role This class will instantiate TitleEntity children classes. 
 * 
 * <p>All attributes in this class are private.<p>
 */
public class OptionTitleController implements Initializable {

	@FXML private AnchorPane titleMenuAnchor;
	@FXML private ImageView songImg;
	@FXML private ImageView concertVideoImg;
	@FXML private ImageView movieImg;
	@FXML private ImageView boxsetImg;
	@FXML private Pane mainDiv;
	
	private AnchorPane pane = null;
	private FactoryDialogWindow dialogMaker = new FactoryDialogWindow();
	private String typePlan = "ML";
	
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
		Image icon;
		
		try {
			
			// Loads icons
			img = ImageIO.read(new File("resources/images/title_menu/song.png"));
			icon = SwingFXUtils.toFXImage(img, null );
			
			this.songImg.setImage(icon);
			this.songImg.setCache(true);
			
			img = ImageIO.read(new File("resources/images/title_menu/live_concert_video.png"));
			icon = SwingFXUtils.toFXImage(img, null );
			
			this.concertVideoImg.setImage(icon);
			this.concertVideoImg.setCache(true);
			
			img = ImageIO.read(new File("resources/images/title_menu/movie.png"));
			icon = SwingFXUtils.toFXImage(img, null );
			
			this.movieImg.setImage(icon);
			this.movieImg.setCache(true);
			
			img = ImageIO.read(new File("resources/images/title_menu/boxset.png"));
			icon = SwingFXUtils.toFXImage(img, null );
			
			this.boxsetImg.setImage(icon);
			this.boxsetImg.setCache(true);
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@FXML
	public void actionSong() {
		
		String path = "/com/raul/rental_shop/Ultra_Vision/view/title/AddTitleView.fxml";
		
		try {
			
			FXMLLoader loader = new FXMLLoader();
			
			loader.setLocation(getClass().getResource(path));
			pane = loader.load();
			
			AddTitleController atc = loader.getController();
			atc.getAdditional1Label().setText("Artist:");
			atc.getAdditional1Field().setPromptText("Enter an artist name...");
			atc.getAdditional2Label().setText("Album:");
			atc.getAdditional2Field().setPromptText("Enter an album name...");
			
			atc.getCdRadio().setSelected(true);

			atc.getCdRadio().setDisable(true);
			atc.getDvdRadio().setDisable(true);
			atc.getBluerayRadio().setDisable(true);
			
			atc.getAddBtn().setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					
					if (atc.validateFields()) {
						
						System.out.println("Inside of validation");
						
						MusicEntity ms = new MusicEntity();
						TitleDAO tDAO = new TitleDAO();
						try {
							int code = tDAO.lastCode();
							ms.setCode(++code);
							ms.setName(atc.getNameField().getText());
							double cost = Double.parseDouble(atc.getCostField().getText());
							ms.setCost(cost);
							ms.setGenre(atc.getGenreField().getText());
							
							LocalDate year = atc.getDatePicker().getValue();
							ms.setYear(year.toString());
							
							ms.setArtist(atc.getAdditional1Field().getText());
							ms.setAlbum(atc.getAdditional2Field().getText());
							
							RadioButton chk = (RadioButton) atc.getMediaGroup()
										.getSelectedToggle();
							
							ms.setMediaFormat(chk.getText());

							
							ms.setTypeTitle(typePlan);
							
							TitleEntity t = ms;
							
							if(tDAO.add(t)) {
								MusicDAO mDAO = new MusicDAO();
								if(mDAO.add(ms)) {
									dialogMaker.makeDiagInfo("Music has been created successfully");
									atc.cleanFields();
								}
							}
							
							
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				}
			});
			
			this.mainDiv.getChildren().setAll(pane);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void actionLiveConcertVideo() {
		
		String path = "/com/raul/rental_shop/Ultra_Vision/view/title/AddTitleView.fxml";
		
		try {
			
			FXMLLoader loader = new FXMLLoader();
			
			loader.setLocation(getClass().getResource(path));
			pane = loader.load();
			
			AddTitleController atc = loader.getController();
			atc.getAdditional1Label().setText("Artist:");
			atc.getAdditional1Field().setPromptText("Enter an artist name...");
			atc.getAdditional2Label().setText("Album:");
			atc.getAdditional2Field().setPromptText("Enter an album name...");
			
			atc.getDvdRadio().setSelected(true);
			
			atc.getCdRadio().setDisable(true);
			atc.getDvdRadio().setDisable(true);
			atc.getBluerayRadio().setDisable(true);
			
			atc.getAddBtn().setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					
					if (atc.validateFields()) {
						
						System.out.println("Inside of validation");
						
						MusicEntity ms = new MusicEntity();
						TitleDAO tDAO = new TitleDAO();
						try {
							int code = tDAO.lastCode();
							ms.setCode(++code);
							ms.setName(atc.getNameField().getText());
							double cost = Double.parseDouble(atc.getCostField().getText());
							ms.setCost(cost);
							ms.setGenre(atc.getGenreField().getText());
							
							LocalDate year = atc.getDatePicker().getValue();
							ms.setYear(year.toString());
							
							ms.setArtist(atc.getAdditional1Field().getText());
							ms.setAlbum(atc.getAdditional2Field().getText());
							
							RadioButton chk = (RadioButton) atc.getMediaGroup()
										.getSelectedToggle();
							
							ms.setMediaFormat(chk.getText());

							
							ms.setTypeTitle(typePlan);
							
							TitleEntity t = ms;
							
							if(tDAO.add(t)) {
								MusicDAO mDAO = new MusicDAO();
								if(mDAO.add(ms)) {
									dialogMaker.makeDiagInfo("Music has been created successfully");
									atc.cleanFields();
								}
							}
							
							
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				}
			});
			
			this.mainDiv.getChildren().setAll(pane);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void actionMovie() {
		
		String path = "/com/raul/rental_shop/Ultra_Vision/view/title/AddTitleView.fxml";
		typePlan = "VL";
		
		try {
			
			FXMLLoader loader = new FXMLLoader();
			
			loader.setLocation(getClass().getResource(path));
			pane = loader.load();
			
			AddTitleController atc = loader.getController();
			
			atc.getAdditional1Label().setText("Director:");
			atc.getAdditional1Field().setPromptText("Enter a director name...");
			atc.getAdditional2Label().setText("Description:");
			atc.getAdditional2Field().setPromptText("Enter a description...");
			
			atc.getCdRadio().setDisable(true);
			atc.getDvdRadio().setSelected(true);
			atc.getDvdRadio().setDisable(false);
			atc.getBluerayRadio().setDisable(false);
			
			atc.getAddBtn().setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					
					if (atc.validateFields()) {
						VideoEntity vd = new VideoEntity();
						TitleDAO tDAO = new TitleDAO();
						try {
							int code = tDAO.lastCode();
							vd.setCode(++code);
							vd.setName(atc.getNameField().getText());
							double cost = Double.parseDouble(atc.getCostField().getText());
							vd.setCost(cost);
							vd.setGenre(atc.getGenreField().getText());
							
							LocalDate year = atc.getDatePicker().getValue();
							vd.setYear(year.toString());
							
							vd.setDirector(atc.getAdditional1Field().getText());
							vd.setDescription(atc.getAdditional2Field().getText());
							
							RadioButton chk = (RadioButton) atc.getMediaGroup()
									.getSelectedToggle();
							
							vd.setMediaFormat(chk.getText());
							
							vd.setTypeTitle(typePlan);
							
							TitleEntity t = vd;
							
							if(tDAO.add(t)) {
								VideoDAO mDAO = new VideoDAO();
								if(mDAO.add(vd)) {
									dialogMaker.makeDiagInfo("Music has been created successfully");
									atc.cleanFields();
								}
							}
							
							
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}					
					
				}
			});
			
			this.mainDiv.getChildren().setAll(pane);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void actionBoxSet() {
		
		String path = "/com/raul/rental_shop/Ultra_Vision/view/title/AddTitleView.fxml";
		typePlan = "TV";
		
		try {
			
			FXMLLoader loader = new FXMLLoader();
			
			loader.setLocation(getClass().getResource(path));
			pane = loader.load();
			
			AddTitleController atc = loader.getController();
			
			atc.getAdditional1Label().setText("Character Series:");
			atc.getAdditional1Field().setPromptText("Enter an character series...");
			atc.getAdditional2Label().setVisible(false);
			atc.getAdditional2Field().setVisible(false);
			
			atc.getCdRadio().setDisable(true);
			atc.getDvdRadio().setSelected(true);
			atc.getDvdRadio().setDisable(false);
			atc.getBluerayRadio().setDisable(false);
			
			atc.getAddBtn().setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					
					if (atc.validateFields()) {
						TVEntity tv = new TVEntity();
						TitleDAO tDAO = new TitleDAO();
						try {
							int code = tDAO.lastCode();
							tv.setCode(++code);
							tv.setName(atc.getNameField().getText());
							double cost = Double.parseDouble(atc.getCostField().getText());
							tv.setCost(cost);
							tv.setGenre(atc.getGenreField().getText());
							
							LocalDate year = atc.getDatePicker().getValue();
							tv.setYear(year.toString());
							
							tv.setCharacterSeries(atc.getAdditional1Field().getText());
							
							
							RadioButton chk = (RadioButton) atc.getMediaGroup()
									.getSelectedToggle();
							
							tv.setMediaFormat(chk.getText());
							
							tv.setTypeTitle(typePlan);
							
							TitleEntity t = tv;
							
							if(tDAO.add(t)) {
								TVDAO mDAO = new TVDAO();
								if(mDAO.add(tv)) {
									dialogMaker.makeDiagInfo("Music has been created successfully");
									atc.cleanFields();
								}
							}
							
							
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}					
					
				}
			});
			
			this.mainDiv.getChildren().setAll(pane);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method close the current pane and load the previous one.
	 */
	@FXML
	public void actionClose() {
		
		try {
			// Just a simple FXML loader
			pane = FXMLLoader.load(getClass()
					.getResource("/com/raul/rental_shop/Ultra_Vision/view/title/TitleView.fxml"));
			titleMenuAnchor.getChildren().setAll(pane);
			this.finalize();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	

}
