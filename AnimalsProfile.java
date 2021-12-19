import java.awt.*;
import javax.swing.*;

class ProfileAnimal 
{
	public JFrame frm;
	public JLabel lblVeterinaryManagementSystem, lblDoctorRegister, barlabel, lblregistration, lblAnimalName,lblMedicalHistory, lblBreed, lblWeight, lblState, lblAge, lblEmailId, lblHeight, lblName, lblRegDate,lblSpecies, lblGender, lblFull_Address, lblMob_No, lblcurrentCondition, txtregistration, txtAge, txtWeight,txtAnimalName, dateChooser, txtBreed, txtName, txtRegDate, txtMob_No, txtEmailId, txtHeight, cmbGender,cmbSpecies, cmbState, txtFull_Address;
	public Color hexa1;
	public JTextArea txtMedicalHistory;
	Font f = new Font("Consolas", Font.BOLD, 13);

	ProfileAnimal() 
	{
		frm = new JFrame();
		frm.setSize(900, 650);

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
		lblVeterinaryManagementSystem.setBounds(120, 12, 650, 30);
		frm.add(lblVeterinaryManagementSystem);

		Canvas canvas = new Canvas();
		canvas.setBackground(Color.WHITE);
		canvas.setForeground(Color.RED);
		canvas.setBounds(120, 40, 635, 5);
		frm.add(canvas);

		lblDoctorRegister = new JLabel("Patient Details");
		lblDoctorRegister.setForeground(Color.WHITE);
		lblDoctorRegister.setFont(new Font("Magneto", Font.BOLD, 23));
		lblDoctorRegister.setBounds(350, 60, 300, 27);
		frm.add(lblDoctorRegister);

		barlabel = new JLabel();
		Image userimg = new ImageIcon(this.getClass().getResource("/images/recbar.png")).getImage();
		barlabel.setIcon(new ImageIcon(userimg));
		barlabel.setBounds(0, 0, 900, 105);
		frm.add(barlabel);

		lblregistration = new JLabel("REGISTRATION NO");
		lblregistration.setBounds(20, 130, 180, 30);
		lblregistration.setFont(f);
		lblregistration.setForeground(Color.BLUE);
		frm.add(lblregistration);

		txtregistration = new JLabel();
		txtregistration.setBounds(180, 130, 140, 30);
		txtregistration.setFont(f);
		txtregistration.setBackground(Color.WHITE);
		txtregistration.setForeground(Color.BLACK);
		frm.add(txtregistration);

		lblRegDate = new JLabel("REGISTRATION DATE");
		lblRegDate.setBounds(450, 130, 180, 30);
		lblRegDate.setFont(f);
		lblRegDate.setForeground(Color.BLUE);
		frm.add(lblRegDate);

		dateChooser = new JLabel();
		dateChooser.setBounds(600, 130, 250, 30);
		dateChooser.setFont(f);
		dateChooser.setForeground(Color.BLACK);
		frm.add(dateChooser);

		lblName = new JLabel("OWNER NAME");
		lblName.setBounds(20, 180, 100, 30);
		lblName.setFont(f);
		lblName.setForeground(Color.BLUE);
		frm.add(lblName);

		txtName = new JLabel();
		txtName.setBounds(180, 180, 250, 30);
		txtName.setFont(f);
		txtName.setBackground(Color.WHITE);
		txtName.setForeground(Color.BLACK);
		frm.add(txtName);

		lblAnimalName = new JLabel("PET NAME");
		lblAnimalName.setBounds(450, 180, 100, 30);
		lblAnimalName.setFont(f);
		lblAnimalName.setForeground(Color.BLUE);
		frm.add(lblAnimalName);

		txtAnimalName = new JLabel();
		txtAnimalName.setBounds(600, 180, 250, 30);
		txtAnimalName.setFont(f);
		txtAnimalName.setForeground(Color.BLACK);
		txtAnimalName.setBackground(Color.WHITE);
		frm.add(txtAnimalName);

		lblGender = new JLabel("GENDER");
		lblGender.setBounds(20, 230, 100, 30);
		lblGender.setFont(f);
		lblGender.setForeground(Color.BLUE);
		frm.add(lblGender);

		cmbGender = new JLabel();
		cmbGender.setBounds(180, 230, 250, 30);
		cmbGender.setFont(f);
		cmbGender.setForeground(Color.BLACK);
		frm.add(cmbGender);

		lblAge = new JLabel("AGE");
		lblAge.setBounds(450, 230, 180, 30);
		lblAge.setFont(f);
		lblAge.setForeground(Color.BLUE);
		frm.add(lblAge);

		txtAge = new JLabel();
		txtAge.setBounds(600, 230, 250, 30);
		txtAge.setFont(f);
		txtAge.setBackground(Color.WHITE);
		frm.add(txtAge);

		lblSpecies = new JLabel("SPECIES");
		lblSpecies.setBounds(20, 280, 100, 30);
		lblSpecies.setFont(f);
		frm.add(lblSpecies);
		lblSpecies.setForeground(Color.BLUE);

		cmbSpecies = new JLabel();
		cmbSpecies.setBounds(180, 280, 250, 30);
		cmbSpecies.setFont(f);
		cmbSpecies.setForeground(Color.BLACK);
		frm.add(cmbSpecies);

		lblBreed = new JLabel("BREED");
		lblBreed.setBounds(450, 280, 180, 30);
		lblBreed.setFont(f);
		lblBreed.setForeground(Color.BLUE);
		frm.add(lblBreed);

		txtBreed = new JLabel();
		txtBreed.setBounds(600, 280, 250, 30);
		txtBreed.setFont(f);
		txtBreed.setForeground(Color.BLACK);
		frm.add(txtBreed);

		lblHeight = new JLabel("HEIGHT");
		lblHeight.setBounds(20, 330, 100, 30);
		lblHeight.setFont(f);
		lblHeight.setForeground(Color.BLUE);
		frm.add(lblHeight);

		txtHeight = new JLabel();
		txtHeight.setBounds(180, 330, 250, 30);
		txtHeight.setFont(f);
		txtHeight.setForeground(Color.BLACK);
		frm.add(txtHeight);

		lblWeight = new JLabel("WEIGHT");
		lblWeight.setBounds(450, 330, 80, 30);
		lblWeight.setFont(f);
		lblWeight.setForeground(Color.BLUE);
		frm.add(lblWeight);

		txtWeight = new JLabel();
		txtWeight.setBounds(600, 330, 250, 30);
		txtWeight.setFont(f);
		txtWeight.setForeground(Color.BLACK);
		frm.add(txtWeight);

		lblMob_No = new JLabel("MOBILE NO.");
		lblMob_No.setBounds(20, 380, 100, 30);
		lblMob_No.setFont(f);
		lblMob_No.setForeground(Color.BLUE);
		frm.add(lblMob_No);

		txtMob_No = new JLabel();
		txtMob_No.setBounds(180, 380, 250, 30);
		txtMob_No.setFont(f);
		txtMob_No.setForeground(Color.BLACK);
		frm.add(txtMob_No);

		lblEmailId = new JLabel("E-Mail ID");
		lblEmailId.setBounds(450, 380, 180, 30);
		lblEmailId.setFont(f);
		lblEmailId.setForeground(Color.BLUE);
		frm.add(lblEmailId);

		txtEmailId = new JLabel();
		txtEmailId.setBounds(600, 380, 250, 30);
		txtEmailId.setFont(f);
		txtEmailId.setForeground(Color.BLACK);
		frm.add(txtEmailId);

		lblState = new JLabel("STATE");
		lblState.setBounds(20, 430, 100, 30);
		lblState.setFont(f);
		lblState.setForeground(Color.BLUE);
		frm.add(lblState);

		cmbState = new JLabel();
		cmbState.setBounds(180, 430, 250, 30);
		cmbState.setFont(f);
		cmbState.setForeground(Color.BLACK);
		frm.add(cmbState);

		lblFull_Address = new JLabel("FULL ADDRESS");
		lblFull_Address.setBounds(450, 430, 100, 30);
		lblFull_Address.setFont(f);
		lblFull_Address.setForeground(Color.BLUE);
		frm.add(lblFull_Address);

		txtFull_Address = new JLabel();
		txtFull_Address.setBounds(600, 430, 250, 30);
		txtFull_Address.setFont(f);
		txtFull_Address.setForeground(Color.BLACK);
		frm.add(txtFull_Address);

		lblMedicalHistory = new JLabel("MEDICAL HISTORY");
		lblMedicalHistory.setBounds(20, 480, 180, 30);
		lblMedicalHistory.setForeground(Color.BLUE);
		frm.add(lblMedicalHistory);
		
		JScrollPane js = new JScrollPane();
		txtMedicalHistory = new JTextArea();
		js.setBounds(180, 480, 600, 90);
		txtMedicalHistory.setFont(f);
		txtMedicalHistory.setBackground(hexa);
		txtMedicalHistory.setForeground(Color.BLACK);
		txtMedicalHistory.setEditable(false);
		js.setViewportView(txtMedicalHistory);
		frm.add(js);

		frm.setVisible(true);
	}
}

public class AnimalsProfile 
{
	
}
