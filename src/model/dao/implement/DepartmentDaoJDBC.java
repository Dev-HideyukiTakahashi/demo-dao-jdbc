package model.dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.DataSource;
import database.DataSourceException;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao {
	
	private Connection connection;
	
	public DepartmentDaoJDBC(Connection connection)
	{
		this.connection = connection;
	}

	@Override
	public void insert(Department obj) 
	{
		PreparedStatement st = null;
		try
		{
			st = connection.prepareStatement
					("INSERT INTO department "
					+"(Name) "
					+ "VALUES "
					+ "(?)",
					Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, obj.getName());
			int row = st.executeUpdate();
			
			if(row > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) 
				{
					int id = rs.getInt(1);
					obj.setId(id);
				}
				DataSource.closeResultSet(rs);
			}
			else {
				throw new DataSourceException("No rows affected!");
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
	public void update(Department obj) 
	{
		PreparedStatement st = null;
		try
		{
			st = connection.prepareStatement
					("UPDATE department "
					+"SET name = ? "
					+"WHERE ID = ?");
			
			st.setString(1, obj.getName());
			st.setInt(2, obj.getId());
			
			st.executeUpdate();
			

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
	public void deleteById(Integer id) 
	{
		PreparedStatement st = null;
		try
		{
			st = connection.prepareStatement
					("DELETE FROM department "
					+"WHERE Id = ?");
			
			st.setInt(1, id);
			
			st.executeUpdate();
			
			
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
	public Department findById(Integer id) 
	{
		PreparedStatement st = null;
		ResultSet rs = null;
		try 
		{
			st = connection.prepareStatement
					("SELECT * FROM department "
					+"WHERE Id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if(rs.next()) 
			{
				return instantiateDepartment(rs);
			}
			else
			{
				throw new DataSourceException("User not found!");
			}
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
	public List<Department> findAll() 
	{
		Statement st = null;
		ResultSet rs = null;
		try
		{
			st = connection.createStatement();
			rs = st.executeQuery("SELECT * FROM department");			

			List<Department> departments = new ArrayList<>();
			
			while(rs.next()) {
				departments.add(instantiateDepartment(rs));
			}
			return departments;
			
		}
		catch(SQLException e) {
			throw new DataSourceException("Error : " + e.getMessage());
		}
		finally
		{
			DataSource.closeStatement(st);
			DataSource.closeResultSet(rs);
		}
	}
	
	private Department instantiateDepartment(ResultSet rs) throws SQLException 
	{
		Department dep = new Department();
		dep.setId(rs.getInt("Id"));
		dep.setName(rs.getString("Name"));
		return dep;
	}

}
