/*
 * Application made for database studies with JDBC
 * Student: Hideyuki Takahashi
 * Date: 10/05/2022
 * Professor: Nelio Alves / DevSuperior
 */

package application;

import java.util.Date;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program 
{
	public static void main(String[] args) 
	{
		
		SellerDao sellerDao = DaoFactory.createSellerDao();
		Seller seller = sellerDao.findById(3);
		System.out.println(seller);
	}
}
