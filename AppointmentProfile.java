import java.awt.*;
import javax.swing.*;

class ProfileApp
{
	public JFrame frm;
	public JLabel lblVeterinaryManagementSystem, lblDoctorRegister, barlabel, lblDate, lblAppId, lblMedicalHistory, lblAppDate, lblProblem, lblState, lblAnimalName, lblEmailId, lblAppTime, lblRegNo, lblTime, lblMobile, lblOwnerName, lblFull_Address, lblDoc_Emp, lblcurrentCondition, txtDate, txtAnimalName, txtProblem,txtAppId, txtTime, txtAppDate, txtRegNo, txtRegDate, txtDoc_Emp, txtEmailId, txtAppTimet, txtOwnerName,txtMobile, cmbState, txtFull_Address, txtMedicalHistory;
	public Color hexa1;

	Font f = new Font("Consolas", Font.BOLD, 13);

	ProfileApp()
	{
		frm = new JFrame();
		frm.setSize(800, 460);

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int framewidth = frm.getSize().width;
		int frameheight = frm.getSize().height;

		int framelocationX = (dim.width - framewidth) / 2;
		int frmlocationY = (dim.height - frameheight) / 2;

		frm.setLocation(framelocationX, frmlocationY);

		frm.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/icon.png")));
		
		frm.setResizable(false);
		Color hexa = Color.decode("#BBF2FA");
		frm.getContentPane().setBackground(hexa);
		frm.setLayout(null);
		frm.setTitle("REGISTER FORM");

		hexa1 = Color.decode("#82efff");

		lblVeterinaryManagementSystem = new JLabel("Veterinary Management System");
		lblVeterinaryManagementSystem.setFont(new Font("Algerian", Font.BOLD, 34));
		lblVeterinaryManagementSystem.setForeground(Color.WHITE);
		lblVeterinaryManagementSystem.setBounds(70, 12, 650, 30);
		frm.add(lblVeterinaryManagementSystem);

		Canvas canvas = new Canvas();
		canvas.setBackground(Color.WHITE);
		canvas.setForeground(Color.RED);
		canvas.setBounds(70, 40, 635, 5);
		frm.add(canvas);

		lblDoctorRegister = new JLabel("Appointment Details");
		lblDoctorRegister.setForeground(Color.WHITE);
		lblDoctorRegister.setFont(new Font("Magneto", Font.BOLD, 23));
		lblDoctorRegister.setBounds(280, 60, 300, 27);
		frm.add(lblDoctorRegister);

		barlabel = new JLabel();
		Image userimg = new ImageIcon(this.getClass().getResource("/images/recbar.png")).getImage();
		barlabel.setIcon(new ImageIcon(userimg));
		barlabel.setBounds(0, 0, 900, 105);
		frm.add(barlabel);

		lblAppId = new JLabel("APPOINTMENT ID");
		lblAppId.setBounds(250, 130, 100, 30);
		lblAppId.setFont(f);
		lblAppId.setForeground(Color.BLUE);
		frm.add(lblAppId);

		txtAppId = new JLabel();
		txtAppId.setBounds(380, 130, 250, 30);
		txtAppId.setFont(f);
		txtAppId.setForeground(Color.BLACK);
		txtAppId.setBackground(Color.WHITE);
		frm.add(txtAppId);

		lblDate = new JLabel("DATE");
		lblDate.setBounds(20, 180, 180, 30);
		lblDate.setFont(f);
		lblDate.setForeground(Color.BLUE);
		frm.add(lblDate);

		txtDate = new JLabel();
		txtDate.setBounds(180, 180, 140, 30);
		txtDate.setFont(f);
		txtDate.setBackground(Color.WHITE);
		txtDate.setForeground(Color.BLACK);
		frm.add(txtDate);

		lblTime = new JLabel("TIME");
		lblTime.setBounds(400, 180, 180, 30);
		lblTime.setFont(f);
		lblTime.setForeground(Color.BLUE);
		frm.add(lblTime);

		txtTime = new JLabel();
		txtTime.setBounds(600, 180, 250, 30);
		txtTime.setFont(f);
		txtTime.setForeground(Color.BLACK);
		frm.add(txtTime);

		lblRegNo = new JLabel("REGISTRATION NO.");
		lblRegNo.setBounds(20, 230, 150, 30);
		lblRegNo.setFont(f);
		lblRegNo.setForeground(Color.BLUE);
		frm.add(lblRegNo);

		txtRegNo = new JLabel();
		txtRegNo.setBounds(180, 230, 250, 30);
		txtRegNo.setFont(f);
		txtRegNo.setBackground(Color.WHITE);
		txtRegNo.setForeground(Color.BLACK);
		frm.add(txtRegNo);

		lblOwnerName = new JLabel("OWNER NAME");
		lblOwnerName.setBounds(400, 230, 100, 30);
		lblOwnerName.setFont(f);
		lblOwnerName.setForeground(Color.BLUE);
		frm.add(lblOwnerName);

		txtOwnerName = new JLabel();
		txtOwnerName.setBounds(600, 230, 250, 30);
		txtOwnerName.setFont(f);
		txtOwnerName.setForeground(Color.BLACK);
		frm.add(txtOwnerName);

		lblAnimalName = new JLabel("PET NAME");
		lblAnimalName.setBounds(20, 280, 180, 30);
		lblAnimalName.setFont(f);
		lblAnimalName.setForeground(Color.BLUE);
		frm.add(lblAnimalName);

		txtAnimalName = new JLabel();
		txtAnimalName.setBounds(180, 280, 250, 30);
		txtAnimalName.setFont(f);
		txtAnimalName.setBackground(Color.WHITE);
		frm.add(txtAnimalName);

		lblMobile = new JLabel("MOBILE NO.");
		lblMobile.setBounds(400, 280, 100, 30);
		lblMobile.setFont(f);
		frm.add(lblMobile);
		lblMobile.setForeground(Color.BLUE);

		txtMobile = new JLabel();
		txtMobile.setBounds(600, 280, 250, 30);
		txtMobile.setFont(f);
		txtMobile.setForeground(Color.BLACK);
		frm.add(txtMobile);

		lblAppDate = new JLabel("APPOINTMENT DATE");
		lblAppDate.setBounds(20, 330, 180, 30);
		lblAppDate.setFont(f);
		lblAppDate.setForeground(Color.BLUE);
		frm.add(lblAppDate);

		txtAppDate = new JLabel();
		txtAppDate.setBounds(180, 330, 250, 30);
		txtAppDate.setFont(f);
		txtAppDate.setForeground(Color.BLACK);
		frm.add(txtAppDate);

		lblAppTime = new JLabel("APPOINTMENT TIME");
		lblAppTime.setBounds(400, 330, 180, 30);
		lblAppTime.setFont(f);
		lblAppTime.setForeground(Color.BLUE);
		frm.add(lblAppTime);

		txtAppTimet = new JLabel();
		txtAppTimet.setBounds(600, 330, 250, 30);
		txtAppTimet.setFont(f);
		txtAppTimet.setForeground(Color.BLACK);
		frm.add(txtAppTimet);

		lblProblem = new JLabel("PROBLEM");
		lblProblem.setBounds(20, 380, 80, 30);
		lblProblem.setFont(f);
		lblProblem.setForeground(Color.BLUE);
		frm.add(lblProblem);

		txtProblem = new JLabel();
		txtProblem.setBounds(180, 380, 250, 30);
		txtProblem.setFont(f);
		txtProblem.setForeground(Color.BLACK);
		frm.add(txtProblem);

		lblDoc_Emp = new JLabel("DOCTOR/EMPLOYEE");
		lblDoc_Emp.setBounds(400, 380, 180, 30);
		lblDoc_Emp.setFont(f);
		lblDoc_Emp.setForeground(Color.BLUE);
		frm.add(lblDoc_Emp);

		txtDoc_Emp = new JLabel();
		txtDoc_Emp.setBounds(600, 380, 250, 30);
		txtDoc_Emp.setFont(f);
		txtDoc_Emp.setForeground(Color.BLACK);
		frm.add(txtDoc_Emp);

		frm.setVisible(true);
	}
}

public class AppointmentProfile
{
	
}
