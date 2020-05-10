package com.raul.rental_shop.Ultra_Vision.controller.title;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import com.raul.rental_shop.Ultra_Vision.model.DAO;
import com.raul.rental_shop.Ultra_Vision.model.title.MusicDAO;
import com.raul.rental_shop.Ultra_Vision.model.title.MusicEntity;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class OptionTitleController implements Initializable {

	@FXML private AnchorPane titleMenuAnchor;
	@FXML private ImageView songImg;
	@FXML private ImageView concertVideoImg;
	@FXML private ImageView movieImg;
	@FXML private ImageView boxsetImg;
	@FXML private Pane mainDiv;
	
	private AnchorPane pane = null;
	FactoryDialogWindow dialogMaker = new FactoryDialogWindow();
	private String typePlan = "ML";
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		BufferedImage img;
		Image icon;
		
		try {
			
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
			
			if (typePlan.equalsIgnoreCase("ML")) {
				atc.getCdCheck().setSelected(true);
				atc.getDvdCheck().setSelected(false);
			} else {
				atc.getDvdCheck().setSelected(true);
				atc.getCdCheck().setSelected(false);
			}
			
			atc.getCdCheck().setDisable(true);
			atc.getDvdCheck().setDisable(true);
			atc.getBluerayCheck().setDisable(true);
			
			atc.getAddBtn().setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					
					MusicEntity ms = new MusicEntity();
					TitleDAO tDAO = new TitleDAO();
					try {
						int code = tDAO.lastCode();
						ms.setCode(++code);
						ms.setName(atc.getNameField().getText());
						double cost = Double.parseDouble(atc.getCostField().getText());
						ms.setCost(cost);
						ms.setGenre(atc.getGenreField().getText());
						ms.setYear(atc.getYearField().getText());
						ms.setArtist(atc.getAdditional1Field().getText());
						ms.setAlbum(atc.getAdditional2Field().getText());
						
						if (typePlan.equalsIgnoreCase("ML")) {
							ms.setMediaFormat(atc.getCdCheck().getText());
						} else {
							ms.setMediaFormat(atc.getDvdCheck().getText());
						}
						
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
			});
			
			this.mainDiv.getChildren().setAll(pane);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void actionLiveConcertVideo() {
		typePlan = "VL";
		actionSong();
	}
	
	@FXML
	public void actionMovie() {
		String path = "/com/raul/rental_shop/Ultra_Vision/view/title/AddTitleView.fxml";
		try {
			
			FXMLLoader loader = new FXMLLoader();
			
			loader.setLocation(getClass().getResource(path));
			pane = loader.load();
			
			AddTitleController atc = loader.getController();
			
			atc.getAdditional1Label().setText("Director:");
			atc.getAdditional1Field().setPromptText("Enter a director name...");
			atc.getAdditional2Label().setText("Description:");
			atc.getAdditional2Field().setPromptText("Enter a description...");
			atc.getCdCheck().setDisable(true);
			atc.getDvdCheck().setSelected(true);
			atc.getDvdCheck().setDisable(false);
			atc.getBluerayCheck().setDisable(false);
			
			atc.getAddBtn().setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					
					VideoEntity ms = new VideoEntity();
					TitleDAO tDAO = new TitleDAO();
					try {
						int code = tDAO.lastCode();
						ms.setCode(++code);
						ms.setName(atc.getNameField().getText());
						double cost = Double.parseDouble(atc.getCostField().getText());
						ms.setCost(cost);
						ms.setGenre(atc.getGenreField().getText());
						ms.setYear(atc.getYearField().getText());
						ms.setDirector(atc.getAdditional1Field().getText());
						ms.setDescription(atc.getAdditional2Field().getText());
						ms.setMediaFormat(atc.getCdCheck().getText());
						ms.setTypeTitle(typePlan);
						
						TitleEntity t = ms;
						
						if(tDAO.add(t)) {
							VideoDAO mDAO = new VideoDAO();
							if(mDAO.add(ms)) {
								dialogMaker.makeDiagInfo("Music has been created successfully");
								atc.cleanFields();
							}
						}
						
						
					} catch (SQLException e) {
						e.printStackTrace();
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
		try {
			
			FXMLLoader loader = new FXMLLoader();
			
			loader.setLocation(getClass().getResource(path));
			pane = loader.load();
			
			AddTitleController atc = loader.getController();
			
			atc.getAdditional1Label().setText("Character Series:");
			atc.getAdditional1Field().setPromptText("Enter an character series...");
			atc.getAdditional2Label().setVisible(false);
			atc.getAdditional2Field().setVisible(false);
			atc.getCdCheck().setDisable(true);
			atc.getDvdCheck().setDisable(false);
			atc.getBluerayCheck().setDisable(false);
			
			this.mainDiv.getChildren().setAll(pane);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void actionClose() {
		
		try {
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
