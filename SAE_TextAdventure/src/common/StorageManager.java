package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StorageManager {
	
	private Connection conn;
	private Statement stmt;
	private ResultSet result;
	
	
	public StorageManager(String databaseLocation) throws ClassNotFoundException, SQLException
	{
		//Connction zu databank etabliert
		String driver ="net.ucanaccess.jdbc.UcanaccessDriver";
		
			Class.forName(driver);
			conn = DriverManager.getConnection("jdbc:ucanacess://"+databaseLocation);
		
	} // ende der Constructor!
	
	public ResultSet query(String query) throws SQLException
	{
		 	stmt=conn.createStatement();
			result =stmt.executeQuery(query);
		
		 return result;
	} // end of query Function!
	
	public int update(String query) throws SQLException
	{
		stmt = conn.createStatement();
		int done = stmt.executeUpdate(query);
		return done;
	}
	public int updateReturnID(String query) throws SQLException
	{
		stmt=conn.createStatement();
		int id =-1;
		stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
		result =stmt.getGeneratedKeys();
		if(result.next())
		{
			id = result.getInt(1);
		}
		return id;
	}
	
	

}
