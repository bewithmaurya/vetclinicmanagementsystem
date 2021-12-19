import java.sql.*;

public class AnimalDetailsOperations extends Base 
{
	int status = 0;
	String sqlquery, sqlqury, did, rid, eid;
	Boolean flag = true;

	public AnimalDetailsOperations()
	{

	}

	public AnimalDetailsOperations(String id)
	{
		super.Id = id;
	}

	public AnimalDetailsOperations(String id, String History) 
	{
		super.Id = id;
		super.MedicalHistory = History;
	}

	public AnimalDetailsOperations(String RegDate, String RegNo, String Owner_Name, String AnimalName, String Gender,String Age, String Species, String Breed, String Height, String Weight, String Mob_No, String EmailId,String State, String Address, String Condition, String MedicalHistory) 
	{
		super.RegDate = RegDate;
		super.RegNo = RegNo;
		super.Owner_Name = Owner_Name;
		super.AnimalName = AnimalName;
		super.Gender = Gender;
		super.Age = Age;
		super.Species = Species;
		super.Breed = Breed;
		super.Height = Height;
		super.Weight = Weight;
		super.Mobile_No = Mob_No;
		super.Emailid = EmailId;
		super.State = State;
		super.Address = Address;
		super.Condition = Condition;
		super.MedicalHistory = MedicalHistory;
	}

	public AnimalDetailsOperations(String RegDate, String RegNo, String Owner_Name, String AnimalName, String Gender,String Age, String Species, String Breed, String Height, String Weight, String Mob_No, String EmailId,String State, String Address)
	{
		super.RegDate = RegDate;
		super.RegNo = RegNo;
		super.Owner_Name = Owner_Name;
		super.AnimalName = AnimalName;
		super.Gender = Gender;
		super.Age = Age;
		super.Species = Species;
		super.Breed = Breed;
		super.Height = Height;
		super.Weight = Weight;
		super.Mobile_No = Mob_No;
		super.Emailid = EmailId;
		super.State = State;
		super.Address = Address;
	}

	// <-----Animal Details----->//

	public ResultSet sec_recordforApp(String id) 
	{
		String Sql = "select OWNER_NAME,PET_NAME,GENDER,AGE,HEIGHT,WEIGHT,MOBILE_NO from ANIMAL_DETAILS where REG_NO=?";
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
		sqlquery = "select REG_NO from ANIMAL_DETAILS";
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
				if (super.RegNo.equals(super.rs.getString(1))) 
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
		sqlquery = "insert into ANIMAL_DETAILS(REG_DATE,REG_NO,OWNER_NAME,PET_NAME,GENDER,AGE,SPECIES,BREED,HEIGHT,WEIGHT,MOBILE_NO,EMAILID,STATE,ADDRESS) values('" + super.RegDate + "','" + super.RegNo + "','" + super.Owner_Name + "','" + super.AnimalName + "','" + super.Gender + "','" + super.Age + "','" + super.Species + "','" + super.Breed + "','" + super.Height + "','" + super.Weight + "','" + super.Mobile_No + "','" + super.Emailid + "','" + super.State + "','" + super.Address + "')";
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
		String sql = "select REG_NO from ANIMAL_DETAILS";
		conn = Connections.getConnections();
		rid = "PET-0";
		try
		{
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next())
			{
				rid = rs.getString(1);
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
		rid = "PET-" + Integer.toString(Integer.parseInt(rid.substring(4, rid.length())) + 1);
		return rid;
	}

	public int animal_update() 
	{
		sqlquery = "update ANIMAL_DETAILS set MEDICAL_HISTORY='" + super.MedicalHistory.toUpperCase() + "' where REG_NO='" + super.Id + "'";
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
