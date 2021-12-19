import java.sql.SQLException;

public class ForgetPasswordOperation extends Base 
{
	String sqlquery;

	public ForgetPasswordOperation(String userid)
	{
		super.UserId = userid;
	}

	public ForgetPasswordOperation(String userid, String sqanswer, String password) 
	{
		super.UserId = userid;
		super.Answer = sqanswer;
		super.Password = password;
	}

	// <--------------ADMIN-------------->//

	public String admin_getSecQuestion() 
	{
		sqlquery = "select SECURITYQUESTION from ADMIN_DETAILS where USERID='" + super.UserId + "'";
		String ques = "";
		super.conn = Connections.getConnections();
		try 
		{
			super.ps = conn.prepareStatement(sqlquery);
			super.rs = ps.executeQuery();
			while (rs.next()) 
			{
				ques = rs.getString(1);
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
		return ques;
	}

	public String admin_getName() 
	{
		sqlquery = "select FULLNAME from ADMIN_DETAILS where USERID='" + super.UserId + "'";
		String name = "";
		super.conn = Connections.getConnections();
		try 
		{
			super.ps = conn.prepareStatement(sqlquery);
			super.rs = ps.executeQuery();
			while (rs.next()) 
			{
				name = rs.getString(1);
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
		return name;
	}

	public Boolean admin_answerValidate() 
	{
		boolean flag = false;
		sqlquery = "select ANSWER from ADMIN_DETAILS where USERID='" + super.UserId + "'";
		super.conn = Connections.getConnections();
		try 
		{
			super.ps = conn.prepareStatement(sqlquery);
			super.rs = ps.executeQuery();
			while (rs.next())
			{
				if (super.Answer.equals(rs.getString(1))) 
				{
					flag = true;
				} 
				else 
				{
					flag = false;
				}
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
		return flag;
	}

	public boolean admin_updatePassword() 
	{
		boolean flag = false;
		sqlquery = "update ADMIN_DETAILS set PASSWORD= '" + super.Password + "'where USERID='" + super.UserId + "'";
		super.conn = Connections.getConnections();
		try 
		{
			super.ps = conn.prepareStatement(sqlquery);
			int status = ps.executeUpdate();
			if (status == 1) 
			{
				flag = true;
			} 
			else 
			{
				flag = false;
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
		return flag;
	}

	// <--------------RECEPTIONIST-------------->//

	public String rec_getSecQuestion() 
	{
		sqlquery = "select SECURITY_QUESTION from RECEPTIONIST_DETAILS where USERID='" + super.UserId + "'";
		String ques = "";
		super.conn = Connections.getConnections();
		try 
		{
			super.ps = conn.prepareStatement(sqlquery);
			super.rs = ps.executeQuery();
			while (rs.next())
			{
				ques = rs.getString(1);
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
		return ques;
	}

	public String rec_getName() 
	{
		sqlquery = "select NAME from RECEPTIONIST_DETAILS where USERID='" + super.UserId + "'";
		String name = "";
		super.conn = Connections.getConnections();
		try 
		{
			super.ps = conn.prepareStatement(sqlquery);
			super.rs = ps.executeQuery();
			while (rs.next())
			{
				name = rs.getString(1);
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
		return name;
	}

	public Boolean rec_answerValidate() 
	{
		boolean flag = false;
		sqlquery = "select ANSWER from RECEPTIONIST_DETAILS where USERID='" + super.UserId + "'";
		super.conn = Connections.getConnections();
		try 
		{
			super.ps = conn.prepareStatement(sqlquery);
			super.rs = ps.executeQuery();
			while (rs.next())
			{
				if (super.Answer.equals(rs.getString(1)))
				{
					flag = true;
				}
				else
				{
					flag = false;
				}
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
		return flag;
	}

	public boolean rec_updatePassword()
	{
		boolean flag = false;
		sqlquery = "update RECEPTIONIST_DETAILS set PASSWORD= '" + super.Password + "'where USERID='" + super.UserId+ "'";
		super.conn = Connections.getConnections();
		try
		{
			super.ps = conn.prepareStatement(sqlquery);
			int status = ps.executeUpdate();
			if (status == 1)
			{
				flag = true;
			}
			else
			{
				flag = false;
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
		return flag;
	}

	// <--------------DOCTOR-------------->//

	public String doc_getSecQuestion() 
	{
		sqlquery = "select SECURITY_QUESTION from DOCTOR_DETAILS where USERID='" + super.UserId + "'";
		String ques = "";
		super.conn = Connections.getConnections();
		try 
		{
			super.ps = conn.prepareStatement(sqlquery);
			super.rs = ps.executeQuery();
			while (rs.next()) 
			{
				ques = rs.getString(1);
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
		return ques;
	}

	public String doc_getName()
	{
		sqlquery = "select NAME from DOCTOR_DETAILS where USERID='" + super.UserId + "'";
		String name = "";
		super.conn = Connections.getConnections();
		try 
		{
			super.ps = conn.prepareStatement(sqlquery);
			super.rs = ps.executeQuery();
			while (rs.next()) 
			{
				name = rs.getString(1);
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
		return name;
	}

	public Boolean doc_answerValidate() 
	{
		boolean flag = false;
		sqlquery = "select ANSWER from DOCTOR_DETAILS where USERID='" + super.UserId + "'";
		super.conn = Connections.getConnections();
		try 
		{
			super.ps = conn.prepareStatement(sqlquery);
			super.rs = ps.executeQuery();
			while (rs.next())
			{
				if (super.Answer.equals(rs.getString(1)))
				{
					flag = true;
				} 
				else 
				{
					flag = false;
				}
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
		return flag;
	}

	public boolean doc_updatePassword() 
	{
		boolean flag = false;
		sqlquery = "update DOCTOR_DETAILS set PASSWORD= '" + super.Password + "'where USERID='" + super.UserId + "'";
		super.conn = Connections.getConnections();
		try 
		{
			super.ps = conn.prepareStatement(sqlquery);
			int status = ps.executeUpdate();
			if (status == 1) 
			{
				flag = true;
			} 
			else 
			{
				flag = false;
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
		return flag;
	}

	// <--------------EMPLOYEE-------------->//

	public String emp_getSecQuestion() 
	{
		sqlquery = "select SECURITY_QUESTION from EMPLOYEE_DETAILS where USERID='" + super.UserId + "'";
		String ques = "";
		super.conn = Connections.getConnections();
		try
		{
			super.ps = conn.prepareStatement(sqlquery);
			super.rs = ps.executeQuery();
			while (rs.next()) 
			{
				ques = rs.getString(1);
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
		return ques;
	}

	public String emp_getName() 
	{
		sqlquery = "select NAME from EMPLOYEE_DETAILS where USERID='" + super.UserId + "'";
		String name = "";
		super.conn = Connections.getConnections();
		try 
		{
			super.ps = conn.prepareStatement(sqlquery);
			super.rs = ps.executeQuery();
			while (rs.next()) 
			{
				name = rs.getString(1);
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
		return name;
	}

	public Boolean emp_answerValidate()
	{
		boolean flag = false;
		sqlquery = "select ANSWER from EMPLOYEE_DETAILS where USERID='" + super.UserId + "'";
		super.conn = Connections.getConnections();
		try 
		{
			super.ps = conn.prepareStatement(sqlquery);
			super.rs = ps.executeQuery();
			while (rs.next()) 
			{
				if (super.Answer.equals(rs.getString(1)))
				{
					flag = true;
				} 
				else 
				{
					flag = false;
				}
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
		return flag;
	}

	public boolean emp_updatePassword() 
	{
		boolean flag = false;
		sqlquery = "update EMPLOYEE_DETAILS set PASSWORD= '" + super.Password + "'where USERID='" + super.UserId + "'";
		super.conn = Connections.getConnections();
		try 
		{
			super.ps = conn.prepareStatement(sqlquery);
			int status = ps.executeUpdate();
			if (status == 1)
			{
				flag = true;
			}
			else 
			{
				flag = false;
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
		return flag;
	}
}
