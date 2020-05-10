package com.raul.rental_shop.Ultra_Vision.model.title;

import com.raul.rental_shop.Ultra_Vision.model.DAO;

public class DAOFactory {
	
	@SuppressWarnings("rawtypes")
	public static DAO makeDAO(String dao) {
		
		if (dao.equalsIgnoreCase("TL")) {
			return new TVDAO();
		} else if (dao.equalsIgnoreCase("VL")) {
			return new VideoDAO();
		} else {
			return new MusicDAO();
		}
	}
}
