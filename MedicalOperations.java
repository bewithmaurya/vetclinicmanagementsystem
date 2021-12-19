import java.sql.SQLException;

public class MedicalOperations extends Base 
{
	int status = 0;
	String sqlquery, sqlqury, apid, mid;
	Boolean flag = true;

	public MedicalOperations()
	{

	}

	public MedicalOperations(String Med_Id) 
	{
		super.Id = Med_Id;
	}

	public MedicalOperations(String Vacc_Id, String Vacc_Name) 
	{
		super.Id = Vacc_Id;
		super.Vaccination = Vacc_Name;
	}

	public MedicalOperations(String Med_Id, String Med_Name, String Drugs, String Type, String Company)
	{
		super.Id = Med_Id;
		super.Name = Med_Name;
		super.Drugs = Drugs;
		super.Type = Type;
		super.Company = Company;
	}

	public MedicalOperations(String Drug_Id, String Drug_Name, String Description)
	{
		super.Id = Drug_Id;
		super.Drugs = Drug_Name;
		super.Description = Description;
	}

	public Boolean Med_userValidate()
	{
		sqlquery = "select ID from MEDICINE_DETAILS";
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

	public int Med_saveRecord() 
	{
		sqlquery = "insert into MEDICINE_DETAILS(ID,NAME,DRUGS,TYPE,COMPANY) values('" + super.Id + "','" + super.Name + "','" + super.Drugs + "','" + super.Type + "','" + super.Company + "')";
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

	public String Med_getid()
	{
		String sql = "select ID from MEDICINE_DETAILS";
		conn = Connections.getConnections();
		mid = "MED-0";
		try 
		{
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) 
			{
				mid = rs.getString(1);
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
		mid = "MED-" + Integer.toString(Integer.parseInt(mid.substring(4, mid.length())) + 1);
		return mid;
	}

	public int Med_update()
	{
		sqlquery = "update MEDICINE_DETAILS set NAME='" + super.Name.toUpperCase() + "',DRUGS='" + super.Drugs.toUpperCase() + "',TYPE='" + super.Type + "',COMPANY='" + super.Company + "' where ID='" + super.Id + "'";
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

	public int Med_delete() 
	{
		sqlquery = "delete from MEDICINE_DETAILS where MEDICINE_ID='" + super.Id + "'";
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

	public Boolean Drug_userValidate()
	{
		sqlquery = "select DRUGS_ID from DRUGS_DETAILS";
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

	public int Drug_saveRecord()
	{
		sqlquery = "insert into DRUGS_DETAILS(DRUGS_ID,DRUGS_NAME,DESCRIPTION) values('" + super.Id + "','"+ super.Drugs + "','" + super.Description + "')";
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

	public String Drug_getId() 
	{
		String sql = "select DRUGS_ID from DRUGS_DETAILS";
		conn = Connections.getConnections();
		apid = "DRUG-0";
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
				e.printStackTrace();
			}
		}
		apid = "DRUG-" + Integer.toString(Integer.parseInt(apid.substring(5, apid.length())) + 1);
		return apid;
	}

	public int Drug_update() 
	{
		sqlquery = "update DRUGS_DETAILS set DRUGS_NAME='" + super.Drugs.toUpperCase() + "',DESCRIPTION='"+ super.Description.toUpperCase() + "' where DRUGS_ID='" + super.Id + "'";
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

	public int Drug_delete()
	{
		sqlquery = "delete from DRUGS_DETAILS where DRUGS_ID='" + super.Id + "'";
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

	//////////////////////////////////

	public Boolean Vacc_userValidate() 
	{
		sqlquery = "select ID from VACCINATION_DETAILS";
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

	public int Vacc_saveRecord() 
	{
		sqlquery = "insert into VACCINATION_DETAILS(ID,VACCINATION) values('" + super.Id + "','" + super.Vaccination+ "')";
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

	public String Vacc_getId() 
	{
		String sql = "select ID from VACCINATION_DETAILS";
		conn = Connections.getConnections();
		apid = "VACC-0";
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
		apid = "VACC-" + Integer.toString(Integer.parseInt(apid.substring(5, apid.length())) + 1);
		return apid;
	}

	public int Vacc_update()
	{
		sqlquery = "update VACCINATION_DETAILS set VACCINATION='" + super.Vaccination.toUpperCase() + "' where ID='"+ super.Id + "'";
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

	public int Vacc_delete()
	{
		sqlquery = "delete from VACCINATION_DETAILS where ID='" + super.Id + "'";
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

}
