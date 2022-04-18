package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {

		SellerDao sd = DaoFactory.createSellerDao();

	
		List<Seller> list = sd.findAll();
		for(Seller s : list) {
			System.out.println(s);
		}
	}

}
