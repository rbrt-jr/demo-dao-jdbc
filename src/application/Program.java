package application;

import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.SellerDao;

public class Program {

	public static void main(String[] args) {
		
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		Scanner sc = new Scanner(System.in);

		//Seller seller = null;
		
		System.out.print("Enter id for delete test: ");
		int idTest = sc.nextInt();
		sellerDao.deleteById(idTest);
		System.out.println("Delete is completed!");
		
		sc.close();
		
		
		
	}

}
