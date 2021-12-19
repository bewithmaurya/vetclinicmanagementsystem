import java.sql.ResultSet;
import java.sql.SQLException;

public class PrescriptionOperation extends Base 
{
	int status = 0;
	String sqlquery, sqlqury, did, psid, eid;
	Boolean flag = true;

	public PrescriptionOperation() 
	{

	}

	public PrescriptionOperation(String id)
	{
		super.Id = id;
	}

	public PrescriptionOperation(String Date, String PrescriptionNo, String AppNo, String RegNo, String DocName,String Owner_Name, String AnimalName, String Problem, String Prescription) 
	{
		try 
		{
			super.Date = Date;
			super.PrescriptionNo = PrescriptionNo;
			super.Id = AppNo;
			super.Name = DocName;
			super.RegNo = RegNo;
			super.Owner_Name = Owner_Name;
			super.AnimalName = AnimalName;
			super.Problem = Problem;
			super.Prescription = Prescription;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}

	}

	public ResultSet sec_recordforApp(String id)
	{
		String Sql = "select APPOINTMENT.REG_NO,APPOINTMENT.OWNER_NAME,APPOINTMENT.PET_NAME,APPOINTMENT.AGE,ANIMAL_DETAILS.BREED,APPOINTMENT.HEIGHT,APPOINTMENT.WEIGHT,APPOINTMENT.PROBLEM from APPOINTMENT INNER JOIN ANIMAL_DETAILS ON APPOINTMENT.REG_NO=ANIMAL_DETAILS.REG_NO WHERE APPOINTMENT_NO=?";
		conn = Connections.getConnections();
		try 
		{
			ps = conn.prepareStatement(Sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return rs;
	}

	public Boolean userValidate() 
	{
		sqlquery = "select PRESC_NO from PRESCRIPTION";
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
				if (super.PrescriptionNo.equals(super.rs.getString(1))) 
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

	public int doc_saverecord()
	{
		sqlquery = "insert into DOC_TREATMENT(CURDATE,PRESC_NO,APMNT_NO,REG_NO,DOCNAME,OWNER_NAME,PET_NAME,SERVICE,PRESCRIPTION) values('"+ super.Date + "','" + super.PrescriptionNo + "','" + super.Id + "','" + super.RegNo + "','"+ super.Name + "','" + super.Owner_Name + "','" + super.AnimalName + "','" + super.Problem + "','"+ super.Prescription + "')";
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

	public String doc_getid()
	{
		String sql = "select PRESC_NO from DOC_TREATMENT";
		conn = Connections.getConnections();
		psid = "PRESC-0";
		try 
		{
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) 
			{
				psid = rs.getString(1);
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
		psid = "PRESC-" + Integer.toString(Integer.parseInt(psid.substring(6, psid.length())) + 1);
		return psid;
	}

	public String emp_getid() 
	{
		String sql = "select PRESC_NO from EMP_TREATMENT";
		conn = Connections.getConnections();
		psid = "PRESC-0";
		try
		{
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next())
			{
				psid = rs.getString(1);
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
		psid = "PRESC-" + Integer.toString(Integer.parseInt(psid.substring(6, psid.length())) + 1);
		return psid;
	}

	public int emp_saverecord()
	{
		sqlquery = "insert into EMP_TREATMENT(CURDATE,PRESC_NO,APMNT_NO,REG_NO,EMPNAME,OWNER_NAME,PET_NAME,SERVICE,STATUS) values('"+ super.Date + "','" + super.PrescriptionNo + "','" + super.Id + "','" + super.RegNo + "','"+ super.Name + "','" + super.Owner_Name + "','" + super.AnimalName + "','" + super.Problem + "','"+ super.Prescription + "')";
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
}