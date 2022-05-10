package model.dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.CommonDataSource;

import database.DataSource;
import database.DataSourceException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao
{
	
	private Connection connection;
	
	public SellerDaoJDBC(Connection connection)
	{
		this.connection = connection;
	}

	@Override
	public void insert(Seller obj) 
	{
		PreparedStatement st = null;
		
		try{
			st = connection.prepareStatement
					(
							"INSERT INTO seller " +
							"(Name, Email, BirthDate, BaseSalary, DepartmentId) " +
							"VALUES " +
							"(?, ?, ?, ?, ?)",
							Statement.RETURN_GENERATED_KEYS
							
					);
			
			st.setString(1, obj.getName());
			st.setString(2, obj.getEmail());
			st.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()));
			st.setDouble(4, obj.getBaseSalary());
			st.setInt(5, obj.getDepartment().getId());
			
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected > 0 ) 
			{
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) 
				{
					int id = rs.getInt(1);
					obj.setId(id);
				}
				DataSource.closeResultSet(rs);
			}
			else
			{
				throw new DataSourceException("Error : No rows affected");
			}	
		}
		catch(SQLException e)
		{
			throw new DataSourceException("Error : " + e.getMessage());
		}
		finally 
		{
			DataSource.closeStatement(st);
		}
	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seller findById(Integer id) 
	{
		PreparedStatement st = null;
		ResultSet rs = null;
		try 
		{
			st = connection.prepareStatement
					(
						"SELECT seller.*,department.Name as DepName " +
						"FROM seller INNER JOIN department " +
						"ON seller.DepartmentId = department.Id " +
						"WHERE seller.Id = ?"
					);
			
			st.setInt(1, id);
			rs = st.executeQuery();
			if(rs.next()) 
			{
				Department dep = instantiateDepartment(rs);
				Seller seller = instantiateSeller(rs, dep);

				return seller;
			}
			return null;
		}
		catch(SQLException e)
		{
			throw new DataSourceException("Error : " + e.getMessage());
		}
		finally
		{
			DataSource.closeStatement(st);
			DataSource.closeResultSet(rs);
		}
	}
	
	@Override
	public List<Seller> findAll() 
	{
		PreparedStatement st = null;
		ResultSet rs = null;
		try
		{
			st = connection.prepareStatement
					(
						"SELECT seller.*,department.Name as DepName " +
						"FROM seller INNER JOIN department " +
						"ON seller.DepartmentId = department.Id " +
						"ORDER BY Name"
					);
			
			rs = st.executeQuery();
			
			List<Seller> sellerList = new ArrayList<>();
			Map<Integer, Department> map = new HashMap<>();
			
			while(rs.next()) {
				
				Department dep = map.get(rs.getInt("DepartmentId"));
				if(dep == null) 
				{
					dep = instantiateDepartment(rs);
					map.put(rs.getInt("DepartmentId"), dep);
				}
				Seller seller = instantiateSeller(rs, dep);
				sellerList.add(seller);				
			}
			return sellerList;
		}
		catch(SQLException e)
		{
			throw new DataSourceException("Error : " + e.getMessage());
		}
		finally
		{
			DataSource.closeStatement(st);
			DataSource.closeResultSet(rs);
		}
	}

	@Override
	public List<Seller> findByDepartment(Department department) 
	{
		PreparedStatement st = null;
		ResultSet rs = null;
		try
		{
			st = connection.prepareStatement
					(
						"SELECT seller.*,department.Name as DepName " +
						"FROM seller INNER JOIN department " +
						"ON seller.DepartmentId = department.Id " +
						"WHERE DepartmentId = ? " +
						"ORDER BY Name"
					);
			
			st.setInt(1, department.getId());
			rs = st.executeQuery();
			
			List<Seller> sellerList = new ArrayList<>();
			Map<Integer, Department> map = new HashMap<>();
			
			while(rs.next()) {
				
				Department dep = map.get(rs.getInt("DepartmentId"));
				if(dep == null) 
				{
					dep = instantiateDepartment(rs);
					map.put(rs.getInt("DepartmentId"), dep);
				}
				Seller seller = instantiateSeller(rs, dep);
				sellerList.add(seller);				
			}
			return sellerList;
		}
		catch(SQLException e)
		{
			throw new DataSourceException("Error : " + e.getMessage());
		}
		finally
		{
			DataSource.closeStatement(st);
			DataSource.closeResultSet(rs);
		}

	}
	
	private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException 
	{
		Seller seller = new Seller();
		seller.setId(rs.getInt("Id"));
		seller.setName(rs.getString("Name"));
		seller.setEmail(rs.getString("Email"));
		seller.setBaseSalary(rs.getDouble("BaseSalary"));
		seller.setBirthDate(rs.getDate("BirthDate"));
		seller.setDepartment(dep);
		return seller;
	}
	
	private Department instantiateDepartment(ResultSet rs) throws SQLException 
	{
		Department dep = new Department();
		dep.setId(rs.getInt("DepartmentId"));
		dep.setName(rs.getString("DepName"));
		return dep;
	}


}
