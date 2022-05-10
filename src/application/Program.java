/*
 * Application made for database studies with JDBC
 * Student: Hideyuki Takahashi
 * Date: 10/05/2022
 * Professor: Nelio Alves / DevSuperior
 */

package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program 
{
	public static void main(String[] args) 
	{

		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		System.out.println("========= Test 1: seller findById =========");
		Seller seller = sellerDao.findById(3);
		System.out.println(seller);
		
		System.out.println("\n========= Test 2: seller findByDepartment =========");
		Department department = new Department(2, null);
		List<Seller> list = sellerDao.findByDepartment(department);
		for(Seller obj: list) 	{
			System.out.println(obj);
		}

		

		
		
		
	}
}
