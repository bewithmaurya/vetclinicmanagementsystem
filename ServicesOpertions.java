import java.sql.SQLException;

public class ServicesOpertions extends Base 
{
	int status = 0;
	String sqlquery, sqlqury, apid;
	Boolean flag = true;

	public ServicesOpertions() 
	{

	}

	public ServicesOpertions(String Serv_Id)
	{
		super.Id = Serv_Id;
	}

	public ServicesOpertions(String Serv_Id, String Serv_Name, String Fee) 
	{
		super.Id = Serv_Id;
		super.Name = Serv_Name;
		super.Fee = Fee;
	}

	public Boolean userValidate()
	{
		sqlquery = "select ID from SERVICE_DETAILS";
		super.conn = Connections.getConnections();
		try 
		{
			super.ps = super.conn.prepareStatement(sqlquery);
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		try 
		{
			super.rs = super.ps.executeQuery();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		try
		{
			while (rs.next()) 
			{
				if (super.Id.equals(super.rs.getString(1))) 
				{
					flag = false;
				}
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return flag;
	}

	public int saveRecord()
	{
		sqlquery = "insert into SERVICE_DETAILS(ID,SERVICE,FEE) values('" + super.Id + "','" + super.Name + "','"+ super.Fee + "')";
		super.conn = Connections.getConnections();
		int status = 0;
		try
		{
			super.ps = super.conn.prepareStatement(sqlquery);
			status = ps.executeUpdate();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return status;
	}

	public String getId() 
	{
		String sql = "select ID from SERVICE_DETAILS";
		conn = Connections.getConnections();
		apid = "SERV-0";
		try 
		{
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) 
			{
				apid = rs.getString(1);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		} 
		finally
		{
			try
			{
				conn.close();
			}
			catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		apid = "SERV-" + Integer.toString(Integer.parseInt(apid.substring(5, apid.length())) + 1);
		return apid;
	}

	public int update() 
	{
		sqlquery = "update SERVICE_DETAILS set SERVICE='" + super.Name.toUpperCase() + "',FEE='" + super.Fee+ "' where ID='" + super.Id + "'";
		super.conn = Connections.getConnections();
		try
		{
			super.ps = conn.prepareStatement(sqlquery);
			status = ps.executeUpdate();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				conn.close();
			} 
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		return status;
	}

	public String[] app_search()
	{
		String info[] = new String[5];
		sqlquery = "select SERVICE,FEE from SERVICE_DETAILS where ID='" + super.Id + "'";
		super.conn = Connections.getConnections();
		try
		{
			super.ps = conn.prepareStatement(sqlquery);
			super.rs = ps.executeQuery();
			if (rs.next()) 
			{
				info[0] = rs.getString(1);
				info[1] = rs.getString(2);
				info[2] = rs.getString(3);
				info[3] = rs.getString(4);
				info[4] = rs.getString(5);
			} 
			else
			{
				info[0] = null;
			}
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		} 
		finally 
		{
			try
			{
				conn.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		return info;
	}
}