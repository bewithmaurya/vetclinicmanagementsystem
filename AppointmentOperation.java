import java.sql.*;

public class AppointmentOperation extends Base
{
	int status = 0;
	String sqlquery, sqlqury, apid;
	Boolean flag = true;

	public AppointmentOperation() 
	{

	}

	public AppointmentOperation(String AppointmentNo)
	{
		super.Id = AppointmentNo;
	}
	// <-----Appointment----->//

	public AppointmentOperation(String RegNo, String currentDate, String currentTime, String Appointment_No, String OwnerName, String AnimalName, String Mobile, String Gender, String Age, String Height, String Weight, String Appointment_Date, String Appointment_Time, String Problem, String Services, String Fee,String Doc_EmpName) 
	{
		try
		{
			super.RegNo = RegNo;
			super.Date = currentDate;
			super.Time = currentTime;
			super.Id = Appointment_No;
			super.Owner_Name = OwnerName;
			super.AnimalName = AnimalName;
			super.Mobile_No = Mobile;
			super.Gender = Gender;
			super.Age = Age;
			super.Height = Height;
			super.Weight = Weight;
			super.AppointmentDate = Appointment_Date;
			super.AppointmentTime = Appointment_Time;
			super.Problem = Problem;
			super.Service = Services;
			super.Fee = Fee;
			super.ExName = Doc_EmpName;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	public ResultSet sec_record(String id)
	{
		String Sql = "select NAME,DOB,GENDER,MOBILE_NO,EMAILID,ADDRESS,USERID,SECURITY_QUESTION,ANSWER,PASSWORD from RECEPTIONIST_DETAILS where ID=?";
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
		sqlquery = "select APPOINTMENT_NO from APPOINTMENT";
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

	public int saverecord()
	{
		System.out.println("Test Data");
		sqlquery = "insert into APPOINTMENT(REG_NO,CURDATE,CURTIME,APPOINTMENT_NO,OWNER_NAME,PET_NAME,MOBILE_NO,GENDER,AGE,HEIGHT,WEIGHT,APPOINTMENT_DATE,APPOINTMENT_TIME,PROBLEM,SERVICES,FEE,DOC_EMP_NAME) values('" + super.RegNo + "','" + super.Date + "','" + super.Time + "','" + super.Id + "','" + super.Owner_Name + "','" + super.AnimalName + "','" + super.Mobile_No + "','" + super.Gender + "','" + super.Age + "','" + super.Height + "','" + super.Weight + "','" + super.AppointmentDate + "','" + super.AppointmentTime + "','" + super.Problem + "','" + super.Service + "','" + super.Fee + "','" + super.ExName + "')";
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

	public String getid() 
	{
		String sql = "select APPOINTMENT_NO from APPOINTMENT";
		conn = Connections.getConnections();
		apid = "VISITOR-0";
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
			// TODO Auto-generated catch block
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
		apid = "VISITOR-" + Integer.toString(Integer.parseInt(apid.substring(8, apid.length())) + 1);
		return apid;
	}
}
