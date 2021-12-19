import java.sql.*;
import javax.swing.JTable;

public class RegisterOperation extends Base
{
	int status = 0;
	String sqlquery, sqlqury, did, rid, eid;
	Boolean flag = true;
	JTable tabview;

	public RegisterOperation() 
	{

	}

	public RegisterOperation(String id)
	{
		super.Id = id;
	}

	public RegisterOperation(String Id, String Name, String DOB, String Gender, String Mobile, String Email,String Address) 
	{
		try 
		{
			super.Id = Id;
			super.Name = Name;
			super.DOB = DOB;
			super.Gender = Gender;
			super.Mobile_No = Mobile;
			super.Emailid = Email;
			super.Address = Address;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public RegisterOperation(String doc_Id, String doc_Name, String doc_DOB, String doc_Gender, String doc_qualify,String doc_spez, String Doc_Mob, String doc_email, String doc_address, String userid,String SecurityQuestion, String Answer, String password)
	{
		try
		{
			super.Id = doc_Id;
			super.Name = doc_Name;
			super.DOB = doc_DOB;
			super.Gender = doc_Gender;
			super.Qualification = doc_qualify;
			super.Specilaization = doc_spez;
			super.Mobile_No = Doc_Mob;
			super.Emailid = doc_email;
			super.Address = doc_address;
			super.UserId = userid;
			super.SecurityQuestion = SecurityQuestion;
			super.Answer = Answer;
			super.Password = password;
			// super.image=doc_img;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	public RegisterOperation(String rec_Id, String rec_Name, String rec_DOB, String rec_Gender, String rec_Mob,String rec_email, String rec_address, String userid, String SecurityQuestion, String Answer,String password)
	{
		try 
		{
			super.Id = rec_Id;
			super.Name = rec_Name;
			super.DOB = rec_DOB;
			super.Gender = rec_Gender;
			super.Mobile_No = rec_Mob;
			super.Emailid = rec_email;
			super.Address = rec_address;
			super.UserId = userid;
			super.SecurityQuestion = SecurityQuestion;
			super.Answer = Answer;
			super.Password = password;
			// super.image=doc_img;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public RegisterOperation(String Id, String Name, String DOB, String Gender, String Qualification, String Post,String Mobile, String Email, String Address)
	{
		try 
		{
			super.Id = Id;
			super.Name = Name;
			super.DOB = DOB;
			super.Gender = Gender;
			super.Qualification = Qualification;
			super.Post = Post;
			super.Mobile_No = Mobile;
			super.Emailid = Email;
			super.Address = Address;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	// <--------------ADMIN-------------------->//

	public RegisterOperation(String userid, String name, String squestion, String answer, String password) 
	{
		try
		{
			super.UserId = userid;
			super.Name = name;
			super.SecurityQuestion = squestion;
			super.Answer = answer;
			super.Password = password;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	public ResultSet admin_sec_record(String id) 
	{
		String Sql = "select USERID,FULLNAME,SECURITYQUESTION,ANSWER,PASSWORD from ADMIN_DETAILS where USERID=?";
		conn = Connections.getConnections();
		try 
		{
			ps = conn.prepareStatement(Sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
		} 
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

	public Boolean admin_userValidateforFP() 
	{
		sqlquery = "select USERID from ADMIN_DETAILS";
		super.conn = Connections.getConnections();
		try 
		{
			super.ps = super.conn.prepareStatement(sqlquery);
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try 
		{
			super.rs = super.ps.executeQuery();
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	public int admin_update()
	{
		sqlquery = "update ADMIN_DETAILS set FULLNAME = '" + super.Name + "',SECURITYQUESTION = '"+ super.SecurityQuestion + "',ANSWER='" + super.Answer + "',PASSWORD='" + super.Password+ "' where USERID='" + super.UserId + "'";
		super.conn = Connections.getConnections();
		try 
		{
			super.ps = conn.prepareStatement(sqlquery);
			status = ps.executeUpdate();
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
		return status;
	}
	// <---------------doctor Data------------------>//

	public ResultSet doc_sec_record(String id) 
	{
		String Sql = "select ID,NAME from DOCTOR_DETAILS where USERID=?";
		conn = Connections.getConnections();
		try
		{
			ps = conn.prepareStatement(Sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

	public ResultSet doc_sec_recordUpdate(String id) 
	{
		String Sql = "select ID,NAME,DOB,GENDER,MOBILE_NO,USERID,SECURITY_QUESTION,ANSWER,PASSWORD,EMAILID,ADDRESS from DOCTOR_DETAILS where ID=?";
		conn = Connections.getConnections();
		try 
		{
			ps = conn.prepareStatement(Sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

	public Boolean doc_userValidate()
	{
		sqlqury = "select USERID from DOCTOR_DETAILS";
		super.conn = Connections.getConnections();
		try 
		{
			super.ps = super.conn.prepareStatement(sqlqury);
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try
		{
			super.rs = super.ps.executeQuery();
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try
		{
			while (rs.next())
			{
				if (super.UserId.equals(super.rs.getString(1))) 
				{
					flag = false;
				}
			}
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	public Boolean doc_userValidateforFP() 
	{
		sqlqury = "select USERID from DOCTOR_DETAILS";
		super.conn = Connections.getConnections();
		try 
		{
			super.ps = super.conn.prepareStatement(sqlqury);
		} 
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try 
		{
			super.rs = super.ps.executeQuery();
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	public int doc_saverecord() 
	{
		sqlquery = "insert into DOCTOR_DETAILS(ID,NAME,DOB,GENDER,QUALIFICATION,SPECIALIZATION,MOBILE_NO,EMAILID,ADDRESS,USERID,SECURITY_QUESTION,ANSWER,PASSWORD) values('"+ super.Id + "','" + super.Name + "','" + super.DOB + "','" + super.Gender + "','" + super.Qualification+ "','" + super.Specilaization + "'," + super.Mobile_No + ",'" + super.Emailid + "','" + super.Address+ "','" + super.UserId + "','" + super.SecurityQuestion + "','" + super.Answer + "','" + super.Password+ "')";
		super.conn = Connections.getConnections();
		int status = 0;
		try 
		{
			super.ps = super.conn.prepareStatement(sqlquery);
			status = ps.executeUpdate();
		} 
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}

	public String doc_getid()
	{
		String sql = "select ID from DOCTOR_DETAILS";
		conn = Connections.getConnections();
		did = "DOC-0";
		try
		{
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next())
			{
				did = rs.getString(1);
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
		did = "DOC-" + Integer.toString(Integer.parseInt(did.substring(4, did.length())) + 1);
		return did;
	}

	public int doc_update()
	{
		sqlquery = "update DOCTOR_DETAILS set DOB='" + super.DOB + "',GENDER='" + super.Gender + "',MOBILE_NO='"+ super.Mobile_No + "',EMAILID='" + super.Emailid + "',ADDRESS='" + super.Address + "',USERID='"+ super.UserId + "',SECURITY_QUESTION='" + super.SecurityQuestion + "',ANSWER='" + super.Answer+ "',PASSWORD='" + super.Password + "' where ID='" + super.Id + "'";
		super.conn = Connections.getConnections();
		try
		{
			super.ps = conn.prepareStatement(sqlquery);
			status = ps.executeUpdate();
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
		return status;
	}

	public int doc_updatebyadmin() 
	{
		sqlquery = "update DOCTOR_DETAILS set NAME='" + super.Name.toUpperCase() + "',DOB='" + super.DOB + "',GENDER='"+ super.Gender + "',QUALIFICATION='" + super.Qualification + "',SPECIALIZATION='" + super.Post+ "',MOBILE_NO='" + super.Mobile_No + "',EMAILID='" + super.Emailid + "',ADDRESS='" + super.Address+ "' where ID='" + super.Id + "'";
		super.conn = Connections.getConnections();
		try 
		{
			super.ps = conn.prepareStatement(sqlquery);
			status = ps.executeUpdate();
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
		return status;
	}

	public String[] doc_search() 
	{
		String info[] = new String[5];
		sqlquery = "select NAME,DOB,GENDER,QUALIFICATION,SPECIALIZATION,MOBILE_NO,EMAILID,ADDRESS from DOCTOR_DETAILS where ID='"+ super.Id + "'";
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
		return info;
	}

	// <-----Receptionist Details----->//

	public ResultSet rec_sec_record(String id)
	{
		String Sql = "select ID,NAME from RECEPTIONIST_DETAILS where USERID=?";
		conn = Connections.getConnections();
		try
		{
			ps = conn.prepareStatement(Sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

	public ResultSet rec_sec_recordupdate(String id)
	{
		String Sql = "select ID,NAME,DOB,GENDER,MOBILE_NO,USERID,SECURITY_QUESTION,ANSWER,PASSWORD,EMAILID,ADDRESS from RECEPTIONIST_DETAILS where ID=?";
		conn = Connections.getConnections();
		try 
		{
			ps = conn.prepareStatement(Sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
		} 
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

	public Boolean rec_userValidate()
	{
		sqlquery = "select USERID from RECEPTIONIST_DETAILS";
		super.conn = Connections.getConnections();
		try 
		{
			super.ps = super.conn.prepareStatement(sqlquery);
		} 
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try
		{
			super.rs = super.ps.executeQuery();
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try 
		{
			while (rs.next())
			{
				if (super.UserId.equals(super.rs.getString(1)))
				{
					flag = false;
				}
			}
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	public Boolean rec_userValidateforFP()
	{
		sqlquery = "select USERID from RECEPTIONIST_DETAILS";
		super.conn = Connections.getConnections();
		try
		{
			super.ps = super.conn.prepareStatement(sqlquery);
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try
		{
			super.rs = super.ps.executeQuery();
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	public int rec_saverecord()
	{
		sqlquery = "insert into RECEPTIONIST_DETAILS(ID,NAME,DOB,GENDER,MOBILE_NO,EMAILID,ADDRESS,USERID,SECURITY_QUESTION,ANSWER,PASSWORD) values('"+ super.Id + "','" + super.Name + "','" + super.DOB + "','" + super.Gender + "','" + super.Mobile_No+ "','" + super.Emailid + "','" + super.Address + "','" + super.UserId + "','" + super.SecurityQuestion+ "','" + super.Answer + "','" + super.Password + "')";
		super.conn = Connections.getConnections();
		int status = 0;
		try
		{
			super.ps = super.conn.prepareStatement(sqlquery);
			status = ps.executeUpdate();
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}

	public String rec_getid()
	{
		String sql = "select ID from RECEPTIONIST_DETAILS";
		conn = Connections.getConnections();
		rid = "REC-0";
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
		rid = "REC-" + Integer.toString(Integer.parseInt(rid.substring(4, rid.length())) + 1);
		return rid;
	}

	public int rec_update()
	{
		sqlquery = "update RECEPTIONIST_DETAILS set DOB='" + super.DOB + "',GENDER='" + super.Gender + "',MOBILE_NO='"+ super.Mobile_No + "',EMAILID='" + super.Emailid + "',ADDRESS='" + super.Address + "',USERID='"+ super.UserId + "',SECURITY_QUESTION='" + super.SecurityQuestion + "',ANSWER='" + super.Answer+ "',PASSWORD='" + super.Password + "' where ID='" + super.Id + "'";
		super.conn = Connections.getConnections();
		try
		{
			super.ps = conn.prepareStatement(sqlquery);
			status = ps.executeUpdate();
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
		return status;
	}

	public int rec_updatebyadmin()
	{
		sqlquery = "update RECEPTIONIST_DETAILS set NAME='" + super.Name.toUpperCase() + "',DOB='" + super.DOB + "',GENDER='" + super.Gender + "',MOBILE_NO='" + super.Mobile_No + "',EMAILID='" + super.Emailid + "',ADDRESS='" + super.Address + "' where ID='" + super.Id + "'";
		super.conn = Connections.getConnections();
		try
		{
			super.ps = conn.prepareStatement(sqlquery);
			status = ps.executeUpdate();
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
		return status;
	}

	public String[] rec_search() 
	{
		String info[] = new String[5];
		sqlquery = "select NAME,DOB,GENDER,MOBILE_NO,EMAILID,ADDRESS from RECEPTIONIST_DETAILS where ID='" + super.Id + "'";
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
		return info;
	}

	// <---------------Employee Data----------------->//

	public ResultSet emp_sec_record(String id) 
	{
		String Sql = "select ID,NAME FROM EMPLOYEE_DETAILS where USERID=?";
		conn = Connections.getConnections();
		try
		{
			ps = conn.prepareStatement(Sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
		} 
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

	public ResultSet emp_sec_recordUpdate(String id) 
	{
		String Sql = "select ID,NAME,DOB,GENDER,MOBILE_NO,USERID,SECURITY_QUESTION,ANSWER,PASSWORD,EMAILID,ADDRESS from EMPLOYEE_DETAILS where ID=?";
		conn = Connections.getConnections();
		try 
		{
			ps = conn.prepareStatement(Sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

	public Boolean emp_userValidate() 
	{
		sqlquery = "select USERID from EMPLOYEE_DETAILS";
		super.conn = Connections.getConnections();
		try
		{
			super.ps = super.conn.prepareStatement(sqlquery);
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try
		{
			super.rs = super.ps.executeQuery();
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try
		{
			while (rs.next())
			{
				if (super.UserId.equals(super.rs.getString(1)))
				{
					flag = false;
				}
			}
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	public Boolean emp_userValidateforFP()
	{
		sqlquery = "select USERID from EMPLOYEE_DETAILS";
		super.conn = Connections.getConnections();
		try
		{
			super.ps = super.conn.prepareStatement(sqlquery);
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try 
		{
			super.rs = super.ps.executeQuery();
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	public int emp_saverecord() 
	{
		sqlquery = "insert into EMPLOYEE_DETAILS(ID,NAME,DOB,GENDER,QUALIFICATION,POST,MOBILE_NO,EMAILID,ADDRESS,USERID,SECURITY_QUESTION,ANSWER,PASSWORD) values('" + super.Id + "','" + super.Name + "','" + super.DOB + "','" + super.Gender + "','" + super.Qualification + "','" + super.Specilaization + "'," + super.Mobile_No + ",'" + super.Emailid + "','" + super.Address + "','" + super.UserId + "','" + super.SecurityQuestion + "','" + super.Answer + "','" + super.Password + "')";
		super.conn = Connections.getConnections();
		int status = 0;
		try 
		{
			super.ps = super.conn.prepareStatement(sqlquery);
			status = ps.executeUpdate();
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}

	public String emp_getid() 
	{
		String sql = "select ID from EMPLOYEE_DETAILS";
		conn = Connections.getConnections();
		eid = "EMP-0";
		try 
		{
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) 
			{
				eid = rs.getString(1);
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
		eid = "EMP-" + Integer.toString(Integer.parseInt(eid.substring(4, eid.length())) + 1);
		return eid;
	}

	public int emp_update() 
	{
		sqlquery = "update EMPLOYEE_DETAILS set DOB='" + super.DOB + "',GENDER='" + super.Gender + "',MOBILE_NO='" + super.Mobile_No + "',EMAILID='" + super.Emailid + "',ADDRESS='" + super.Address + "',USERID='" + super.UserId + "',SECURITY_QUESTION='" + super.SecurityQuestion + "',ANSWER='" + super.Answer + "',PASSWORD='" + super.Password + "' where ID='" + super.Id + "'";
		super.conn = Connections.getConnections();
		try 
		{
			super.ps = conn.prepareStatement(sqlquery);
			status = ps.executeUpdate();
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
		return status;
	}

	public int emp_updatebyadmin()
	{
		sqlquery = "update EMPLOYEE_DETAILS set NAME='" + super.Name.toUpperCase() + "',DOB='" + super.DOB + "',GENDER='" + super.Gender + "',QUALIFICATION='" + super.Qualification + "',POST='" + super.Specilaization + "',MOBILE_NO='" + super.Mobile_No + "',EMAILID='" + super.Emailid + "',ADDRESS='" + super.Address + "' where ID='" + super.Id + "'";
		super.conn = Connections.getConnections();
		try 
		{
			super.ps = conn.prepareStatement(sqlquery);
			status = ps.executeUpdate();
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
		return status;
	}

	public String[] emp_search()
	{
		String info[] = new String[5];
		sqlquery = "select NAME,DOB,GENDER,QUALIFICATION,POST,MOBILE_NO,EMAILID,ADDRESS from EMPLOYEE_DETAILS where ID='" + super.Id + "'";
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
		return info;
	}

}
