package model.dao;

import database.DataSource;
import model.dao.implement.DepartmentDaoJDBC;
import model.dao.implement.SellerDaoJDBC;

public class DaoFactory 
{
	public static SellerDao createSellerDao() 
	{
		return new SellerDaoJDBC(DataSource.getConnection());
	}
	
	public static DepartmentDao createDepartmentDao() 
	{
		return new DepartmentDaoJDBC(DataSource.getConnection());
	}
}
