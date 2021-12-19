import java.sql.*;

public class Connections 
{
    public static Connection getConnections()
    {
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String username = "vms";
		String password = "vms";
		Connection conn = null;
		try 
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, username, password);
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return conn;
	}
}
