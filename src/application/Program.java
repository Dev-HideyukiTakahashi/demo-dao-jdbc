/*
 * Application made for database studies with JDBC
 * Student: Hideyuki Takahashi
 * Date: 10/05/2022
 * Professor: Nelio Alves / DevSuperior
 */

package application;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program 
{
	public static void main(String[] args) 
	{
		Scanner scan = new Scanner(System.in);

		SellerDao sellerDao = DaoFactory.createSellerDao();
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		
		System.out.println("========= Test 1: seller findById =========");
		System.out.println(sellerDao.findById(3));
		
		System.out.println("\n========= Test 1: department findById =========");	
		System.out.println(departmentDao.findById(2));
		
		
		System.out.println("\n\n========= Test 2: seller findByDepartment =========");
		Department department = new Department(2, null);
		List<Seller> sellerList = sellerDao.findByDepartment(department);
		sellerList.forEach(System.out::println);
		
		System.out.println("\n\n========= Test 3: seller findAll =========");
		sellerList = sellerDao.findAll();
		sellerList.forEach(System.out::println);
		
		System.out.println("\n========= Test 3: department findByDepartment =========");
		List<Department> depList = departmentDao.findAll();
		depList.forEach(System.out::println);
		
		
//		System.out.println("\n\n========= Test 4: seller insert =========");
//		Seller newSeller = new Seller(null, "Greg", "greg@gmail.com", new Date(), 4000.0, department);
//		sellerDao.insert(newSeller);
//		System.out.println("Inserted! New Id = " + newSeller.getId());
//		
//		System.out.println("\n========= Test 4: department insert =========");
//		Department newDepartment = new Department(null, "Devops");
//		departmentDao.insert(newDepartment);
//		System.out.println("Inserted! New Id = " + newDepartment.getId());
		
		
	/*	System.out.println("\n\n========= Test 5: seller update =========");
		Seller sellerUpdate = sellerDao.findById(1);
		sellerUpdate.setName("Bruce Wayne");
		sellerDao.update(sellerUpdate);
		System.out.println("Update completed.");
		
		System.out.println("\n========= Test 5: department update =========");
		Department departmentUpdate = departmentDao.findById(6);
		departmentUpdate.setName("Developer");
		departmentDao.update(departmentUpdate);
		System.out.println("Update completed.");                                 */
		
		

	/*	System.out.println("\n\n========= Test 6: seller delete =========");
		System.out.println("Enter id for delete test: ");
		int idSeller = scan.nextInt();
		sellerDao.deleteById(idSeller);
		System.out.println("Delete completed");                                   
		
		System.out.println("\n========= Test 6: department delete =========");
		System.out.println("Enter id for delete test: ");
		int idDepartment = scan.nextInt();
		departmentDao.deleteById(idDepartment);
		System.out.println("Delete completed"); 
																				  */
		scan.close();
		


		
		
	}
}
