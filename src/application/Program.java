/*
 * Application made for database studies with JDBC
 * Student: Hideyuki Takahashi
 * Date: 10/05/2022
 * Professor: Nelio Alves / DevSuperior
 */

package application;

import java.util.Date;

import model.entities.Department;
import model.entities.Seller;

public class Program {
	public static void main(String[] args) {
		Department dp = new Department(1, "Books");
		Seller seller = new Seller(22, "Bob", "bob@gmail.com", new Date(), 3000.00, dp);
		
		System.out.println(dp);
		System.out.println(seller);
	}
}
