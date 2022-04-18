package application;

import java.util.Date;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		SellerDao sellerDao = DaoFactory.createSellerDao();

		Department dep = new Department(2, null);
		Seller newSeller = new Seller(null, "Rubervaldo", "rubervaldo@gmail.com", new Date(), 4000.0, dep);
		sellerDao.insert(newSeller);
		
		System.out.println("Inserted! New id is = " + newSeller.getId());
		
	}

}
