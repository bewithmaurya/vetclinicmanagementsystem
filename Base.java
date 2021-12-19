import java.sql.*;

public abstract class Base 
{
    Connection conn;
	PreparedStatement ps;
	ResultSet rs;
	String Id, Name, DOB, Gender, Qualification, Drugs, Type, Company, Service, ExName, Specilaization, Post,Description, PrescriptionNo, Prescription, Emailid, Address, UserType, FullName, UserId, Password,Mobile_No, SecurityQuestion, Answer, image, RegDate, RegNo, Owner_Name, AnimalName, Age, Species, Breed,Height, Weight, State, Condition, MedicalHistory, Date, Time, AppointmentDate, AppointmentTime, Problem,Fee, Vaccination;
	static String Medical = null;
}
