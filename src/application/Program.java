/*
 * Application made for database studies with JDBC
 * Student: Hideyuki Takahashi
 * Date: 10/05/2022
 * Professor: Nelio Alves / DevSuperior
 */

package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Seller;

public class Program 
{
	public static void main(String[] args) 
	{
		
		SellerDao sellerDao = DaoFactory.createSellerDao();
		System.out.println("========= Teste 1: seller findById =========");
		Seller seller = sellerDao.findById(3);
		System.out.println(seller);
		
		
	}
}
