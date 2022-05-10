package database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DataSource {

	private static Connection connection;

	public static Connection getConnection() 
	{
		if (connection == null) 
		{
			try 
			{
				Properties props = loadProperties();
				String url = props.getProperty("dburl");
				connection = DriverManager.getConnection(url, props);
			} 
			catch (SQLException e) 
			{
				throw new DataSourceException("Error receiving connection : " + e.getMessage());
			}
		}
		return connection;
	}


	private static Properties loadProperties() 
	{
		try (FileInputStream fs = new FileInputStream("db.properties")) 
		{
			Properties props = new Properties();
			props.load(fs);
			return props;
		} catch (IOException e) 
		{
			throw new DataSourceException("Error in loadProperties(): " + e.getMessage());
		}
	}
	
	/*
	 *  Methods for closing
	 */
	
	public static void closeConnection() 
	{	
		if (connection != null) 
		{
			try 
			{
				connection.close();
			}			 
			catch (SQLException e) 
			{
				throw new DataSourceException("Error in close: " + e.getMessage());
			}
		}	
	}
	
	public static void closeStatement(Statement statement)
	{
		if(statement != null) 
		{
			try 
			{
				statement.close();
			}
			catch (SQLException e) 
			{
				throw new DataSourceException("Error in close: " + e.getMessage());
			}
		}
	}
	
	public static void closeResultSet(ResultSet resultSet)
	{
		if(resultSet != null) 
		{
			try 
			{
				resultSet.close();
			}
			catch (SQLException e) 
			{
				throw new DataSourceException("Error in close: " + e.getMessage());
			}
		}
	}
}
