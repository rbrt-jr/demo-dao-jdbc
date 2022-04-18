package model.dao;

import model.dao.implement.SellerDaoJDBC;
import model.entities.Seller;

public class DaoFactory {

	public static SellerDao createSellerDao() {
		return new SellerDaoJDBC();
	}
}
