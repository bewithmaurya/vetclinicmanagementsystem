import java.awt.*;
import java.awt.event.*;
import java.text.*;
import javax.swing.*;
import javax.swing.text.MaskFormatter;
import com.toedter.calendar.JDateChooser;

class RegAnimal extends ValidateForm implements ActionListener
{
	public JFrame frm;
	public JButton btnNEW, btnOLD, btnUpdate, btnClear;
	public JLabel lblVeterinaryManagementSystem, lblDoctorRegister, barlabel, lblregistration, lblAnimalName, lblMedicalHistory, lblBreed, lblWeight, lblState, lblAge, lblEmailId, lblHeight, lblName, lblRegDate, lblSpecies, lblGender, lblFull_Address, lblMob_No, lblcurrentCondition, lbllogo;
	public JTextField txtregistration, txtAge, txtWeight, txtAnimalName, txtBreed, txtName, txtRegDate, txtMob_No, txtEmailId, txtHeight;
	public JComboBox<Object> cmbGender, cmbSpecies, cmbState;
	public JTextArea txtFull_Address, txtMedicalHistory;
	public Color hexa1;
	public JDateChooser dateChooser;
	public JScrollPane jsMedicalHistory, jsAddress;
	String gender[] = { "MALE", "FEMALE" };
	String pet[] = { "BIRDS", "CATS", "DOG", "RABBITS" };
	String state[] = { "ANDHRA PRADESH", "ARUNACHAL PRADESH", "ASSAM", "BIHAR", "CHHATTISGARH", "GOA", "GUJRAT","HARYANA", "HIMACHAL PRADESH", "JAMMU & KASHMIR", "JHARKHAND", "KARNATAKA", "KERALA", "MADHYA PRADESH","MAHARASHTRA", "MANIPUR", "MEGHALAYA", "MIZORAM", "NAGALAND", "ODISHA", "PUNJAB", "RAJASTHAN", "SIKKIM","TAMIL NADU", "TELANGANA", "TRIPURA", "UTTARAKHAND", "UTTAR PRADESH", "WEST BENGAL" };

	Font f = new Font("Consolas", Font.BOLD, 13);
	public MaskFormatter mf1, mf2, mf3;

	RegAnimal() 
	{
		frm = new JFrame();
		frm.setSize(1080, 650);

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
		lblVeterinaryManagementSystem.setBounds(220, 12, 650, 30);
		frm.add(lblVeterinaryManagementSystem);

		Canvas canvas = new Canvas();
		canvas.setBackground(Color.WHITE);
		canvas.setForeground(Color.RED);
		canvas.setBounds(220, 40, 635, 5);
		frm.add(canvas);

		lblDoctorRegister = new JLabel("Patient Register");
		lblDoctorRegister.setForeground(Color.WHITE);
		lblDoctorRegister.setFont(new Font("Magneto", Font.BOLD, 23));
		lblDoctorRegister.setBounds(450, 60, 300, 27);
		frm.add(lblDoctorRegister);

		barlabel = new JLabel();
		Image userimg = new ImageIcon(this.getClass().getResource("/images/recbar.png")).getImage();
		barlabel.setIcon(new ImageIcon(userimg));
		barlabel.setBounds(0, 0, 1300, 105);
		frm.add(barlabel);

		lblregistration = new JLabel("REGISTRATION NO");
		lblregistration.setBounds(20, 130, 150, 30);
		lblregistration.setFont(f);
		lblregistration.setForeground(Color.BLUE);
		frm.add(lblregistration);

		txtregistration = new JTextField();
		txtregistration.setBounds(150, 130, 140, 30);
		txtregistration.setFont(f);
		txtregistration.setBackground(Color.WHITE);
		txtregistration.setForeground(Color.BLACK);
		txtregistration.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		txtregistration.setEditable(false);
		frm.add(txtregistration);

		btnOLD = new JButton("OLD");
		btnOLD.setBounds(300, 130, 100, 30);
		btnOLD.setFont(f);
		btnOLD.setForeground(Color.BLUE);
		btnOLD.setBackground(hexa1);
		btnOLD.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(btnOLD);
		btnOLD.addActionListener(this);

		lblRegDate = new JLabel("REGISTRATION DATE");
		lblRegDate.setBounds(450, 130, 150, 30);
		lblRegDate.setFont(f);
		lblRegDate.setForeground(Color.BLUE);
		frm.add(lblRegDate);

		dateChooser = new JDateChooser();
		dateChooser.setBounds(580, 130, 250, 30);
		dateChooser.setFont(f);
		dateChooser.setBackground(Color.WHITE);
		dateChooser.setForeground(Color.BLACK);
		dateChooser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(dateChooser);

		lblName = new JLabel("OWNER NAME");
		lblName.setBounds(20, 180, 100, 30);
		lblName.setFont(f);
		lblName.setForeground(Color.BLUE);
		frm.add(lblName);

		txtName = new JTextField();
		txtName.setBounds(150, 180, 250, 30);
		txtName.setFont(f);
		txtName.setBackground(Color.WHITE);
		txtName.setForeground(Color.BLACK);
		txtName.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(txtName);

		lblAnimalName = new JLabel("PET NAME");
		lblAnimalName.setBounds(450, 180, 100, 30);
		lblAnimalName.setFont(f);
		lblAnimalName.setForeground(Color.BLUE);
		frm.add(lblAnimalName);

		txtAnimalName = new JTextField();
		txtAnimalName.setBounds(580, 180, 250, 30);
		txtAnimalName.setFont(f);
		txtAnimalName.setForeground(Color.BLACK);
		txtAnimalName.setBackground(Color.WHITE);
		txtAnimalName.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(txtAnimalName);

		lblGender = new JLabel("GENDER");
		lblGender.setBounds(20, 230, 100, 30);
		lblGender.setFont(f);
		lblGender.setForeground(Color.BLUE);
		frm.add(lblGender);

		cmbGender = new JComboBox<Object>(gender);
		cmbGender.setBounds(150, 230, 250, 30);
		cmbGender.setFont(f);
		cmbGender.setSelectedIndex(-1);
		cmbGender.setBackground(Color.WHITE);
		cmbGender.setForeground(Color.BLACK);
		cmbGender.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(cmbGender);

		lblAge = new JLabel("AGE");
		lblAge.setBounds(450, 230, 150, 30);
		lblAge.setFont(f);
		lblAge.setForeground(Color.BLUE);
		frm.add(lblAge);

		txtAge = new JTextField();
		txtAge.setBounds(580, 230, 250, 30);
		txtAge.setFont(f);
		txtAge.setForeground(Color.BLACK);
		txtAge.setBackground(Color.WHITE);
		txtAge.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(txtAge);

		lblSpecies = new JLabel("SPECIES");
		lblSpecies.setBounds(20, 280, 100, 30);
		lblSpecies.setFont(f);
		frm.add(lblSpecies);
		lblSpecies.setForeground(Color.BLUE);

		cmbSpecies = new JComboBox<Object>(pet);
		cmbSpecies.setBounds(150, 280, 250, 30);
		cmbSpecies.setFont(f);
		cmbSpecies.setBackground(Color.WHITE);
		cmbSpecies.setForeground(Color.BLACK);
		cmbSpecies.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cmbSpecies.setSelectedIndex(-1);
		frm.add(cmbSpecies);

		lblBreed = new JLabel("BREED");
		lblBreed.setBounds(450, 280, 150, 30);
		lblBreed.setFont(f);
		lblBreed.setForeground(Color.BLUE);
		frm.add(lblBreed);

		txtBreed = new JTextField();
		txtBreed.setBounds(580, 280, 250, 30);
		txtBreed.setFont(f);
		txtBreed.setForeground(Color.BLACK);
		txtBreed.setBackground(Color.WHITE);
		txtBreed.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(txtBreed);

		lblHeight = new JLabel("HEIGHT");
		lblHeight.setBounds(20, 330, 100, 30);
		lblHeight.setFont(f);
		lblHeight.setForeground(Color.BLUE);
		frm.add(lblHeight);

		try 
		{
			mf2 = new MaskFormatter("###CM");
		} 
		catch (ParseException e) 
		{
			e.printStackTrace();
		}

		txtHeight = new JFormattedTextField(mf2);
		txtHeight.setBounds(150, 330, 250, 30);
		txtHeight.setFont(f);
		txtHeight.setForeground(Color.BLACK);
		txtHeight.setBackground(Color.WHITE);
		txtHeight.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(txtHeight);

		lblWeight = new JLabel("WEIGHT");
		lblWeight.setBounds(450, 330, 80, 30);
		lblWeight.setFont(f);
		lblWeight.setForeground(Color.BLUE);
		frm.add(lblWeight);

		try 
		{
			mf3 = new MaskFormatter("###KG");
		} 
		catch (ParseException e) 
		{
			e.printStackTrace();
		}

		txtWeight = new JFormattedTextField(mf3);
		txtWeight.setBounds(580, 330, 250, 30);
		txtWeight.setFont(f);
		txtWeight.setForeground(Color.BLACK);
		txtWeight.setBackground(Color.WHITE);
		txtWeight.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(txtWeight);

		lblMob_No = new JLabel("MOBILE NO.");
		lblMob_No.setBounds(20, 380, 100, 30);
		lblMob_No.setFont(f);
		lblMob_No.setForeground(Color.BLUE);
		frm.add(lblMob_No);

		txtMob_No = new JTextField();
		txtMob_No.setBounds(150, 380, 250, 30);
		txtMob_No.setFont(f);
		txtMob_No.setForeground(Color.BLACK);
		txtMob_No.setBackground(Color.WHITE);
		txtMob_No.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(txtMob_No);

		lblState = new JLabel("STATE");
		lblState.setBounds(450, 380, 150, 30);
		lblState.setFont(f);
		lblState.setForeground(Color.BLUE);
		frm.add(lblState);

		cmbState = new JComboBox<Object>(state);
		cmbState.setBounds(580, 380, 250, 30);
		cmbState.setFont(f);
		cmbState.setBackground(Color.WHITE);
		cmbState.setForeground(Color.BLACK);
		cmbState.setSelectedIndex(-1);
		cmbState.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(cmbState);

		lblEmailId = new JLabel("E-Mail ID");
		lblEmailId.setBounds(20, 430, 100, 30);
		lblEmailId.setFont(f);
		lblEmailId.setForeground(Color.BLUE);
		frm.add(lblEmailId);

		txtEmailId = new JTextField();
		txtEmailId.setBounds(150, 430, 680, 30);
		txtEmailId.setFont(f);
		txtEmailId.setForeground(Color.BLACK);
		txtEmailId.setBackground(Color.WHITE);
		txtEmailId.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(txtEmailId);

		lblFull_Address = new JLabel("FULL ADDRESS");
		lblFull_Address.setBounds(20, 480, 100, 30);
		lblFull_Address.setFont(f);
		lblFull_Address.setForeground(Color.BLUE);
		frm.add(lblFull_Address);

		jsAddress = new JScrollPane();
		txtFull_Address = new JTextArea();
		jsAddress.setBounds(150, 480, 680, 60);
		txtFull_Address.setFont(f);
		txtFull_Address.setBackground(Color.WHITE);
		txtFull_Address.setForeground(Color.BLACK);
		txtFull_Address.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jsAddress.setViewportView(txtFull_Address);
		frm.add(jsAddress);

		lblMedicalHistory = new JLabel("MEDICAL HISTORY");
		lblMedicalHistory.setBounds(850, 130, 170, 30);
		lblMedicalHistory.setForeground(Color.BLUE);
		frm.add(lblMedicalHistory);

		jsMedicalHistory = new JScrollPane();
		txtMedicalHistory = new JTextArea();
		jsMedicalHistory.setBounds(850, 180, 200, 90);
		txtMedicalHistory.setFont(f);
		txtMedicalHistory.setBackground(Color.WHITE);
		txtMedicalHistory.setForeground(Color.BLACK);
		txtMedicalHistory.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		txtMedicalHistory.setEditable(false);
		jsMedicalHistory.setViewportView(txtMedicalHistory);
		frm.add(jsMedicalHistory);

		btnNEW = new JButton("NEW");
		btnNEW.setBounds(150, 560, 330, 35);
		btnNEW.setFont(f);
		btnNEW.setForeground(Color.BLUE);
		btnNEW.setBackground(hexa1);
		btnNEW.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(btnNEW);
		btnNEW.addActionListener(this);

		btnClear = new JButton("CLEAR");
		btnClear.setBounds(500, 560, 330, 35);
		btnClear.setFont(f);
		btnClear.setForeground(Color.BLUE);
		btnClear.setBackground(hexa1);
		btnClear.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(btnClear);
		btnClear.addActionListener(this);

		disabled();
		frm.setVisible(true);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		if (e.getSource().equals(btnNEW))
		{
			if (e.getActionCommand().equalsIgnoreCase("NEW")) 
			{
				btnNEW.disable();
				enabled();
				getid();
				btnNEW.setText("SAVE");
			} 
			else if (e.getActionCommand().equalsIgnoreCase("SAVE")) 
			{
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy");
				String regdate = dateFormat.format(dateChooser.getDate());
				if (frmvalidate()) 
				{
					AnimalDetailsOperations ado = new AnimalDetailsOperations(regdate, txtregistration.getText().toUpperCase(), txtName.getText().toUpperCase(), txtAnimalName.getText().toUpperCase(), cmbGender.getSelectedItem().toString().toUpperCase(), txtAge.getText(), cmbSpecies.getSelectedItem().toString().toUpperCase(), txtBreed.getText().toUpperCase(), txtHeight.getText(), txtWeight.getText(), txtMob_No.getText(), txtEmailId.getText().toLowerCase(), cmbState.getSelectedItem().toString().toUpperCase(), txtFull_Address.getText().toUpperCase());
					if (ado.userValidate()) 
					{
						if (ado.saverecord() == 1)
						{
							JOptionPane.showMessageDialog(null, "REGISTER SUCCESSFULLY");
							Appt ap = new Appt();
							ap.txtregistration.setText(txtregistration.getText());
							ap.txtName.setText(txtName.getText());
							ap.txtAnimalName.setText(txtAnimalName.getText());
							ap.txtMob_No.setText(txtMob_No.getText());
							ap.cmbGender.setSelectedItem(cmbGender.getSelectedItem());
							ap.txtAge.setText(txtAge.getText());
							ap.txtHeight.setText(txtHeight.getText());
							ap.txtWeight.setText(txtWeight.getText());
							frm.dispose();
						}
					} 
					else 
					{
						JOptionPane.showMessageDialog(null, "Animal Already Inserted");
					}
				}
			}

		}
		else if (e.getSource().equals(btnClear))
		{
			clearForm();
			disabled();
			btnNEW.disable();
			btnNEW.setText("NEW");
		} 
		else if (e.getSource().equals(btnOLD)) 
		{
			new Appointment();
		}
	}

	public void clearForm() 
	{
		txtregistration.setText("");
		dateChooser.setDate(null);
		txtName.setText("");
		txtAnimalName.setText("");
		cmbGender.setSelectedIndex(-1);
		txtAge.setText("");
		cmbSpecies.setSelectedIndex(-1);
		txtBreed.setText("");
		txtHeight.setText("");
		txtWeight.setText("");
		txtMob_No.setText("");
		cmbState.setSelectedIndex(-1);
		txtEmailId.setText("");
		txtFull_Address.setText("");
	}

	public void enabled() 
	{
		dateChooser.setEnabled(true);
		txtName.setEditable(true);
		txtAnimalName.setEditable(true);
		cmbGender.setEnabled(true);
		txtAge.setEditable(true);
		cmbSpecies.setEnabled(true);
		txtBreed.setEditable(true);
		txtHeight.setEditable(true);
		txtWeight.setEditable(true);
		txtMob_No.setEditable(true);
		cmbState.setEnabled(true);
		txtEmailId.setEditable(true);
		txtFull_Address.setEditable(true);
	}

	public void disabled()
	{
		txtregistration.setEditable(false);
		dateChooser.setEnabled(false);
		txtName.setEditable(false);
		txtAnimalName.setEditable(false);
		cmbGender.setEnabled(false);
		txtAge.setEditable(false);
		cmbSpecies.setEnabled(false);
		txtBreed.setEditable(false);
		txtHeight.setEditable(false);
		txtWeight.setEditable(false);
		txtMob_No.setEditable(false);
		cmbState.setEnabled(false);
		txtEmailId.setEditable(false);
		txtFull_Address.setEditable(false);
	}

	public boolean frmvalidate() 
	{
		boolean flag = true;
		if (ValidateForm.validateAnyString(txtName.getText()) == false)
		{
			JOptionPane.showMessageDialog(null, "Enter Owner Name ");
			txtName.grabFocus();
			flag = false;
		} 
		else if (ValidateForm.validateAnyString(txtAnimalName.getText()) == false) 
		{
			JOptionPane.showMessageDialog(null, "Enter Animal Name ");
			txtAnimalName.grabFocus();
			flag = false;
		}
		else if (cmbGender.getSelectedIndex() == -1)
		{
			JOptionPane.showMessageDialog(null, "Choose Your Gender");
			cmbGender.grabFocus();
			flag = false;
		} 
		else if (ValidateForm.validateAnyNo(txtAge.getText()) == false)
		{
			JOptionPane.showMessageDialog(null, "Enter Age ");
			txtAge.grabFocus();
			flag = false;
		} 
		else if (cmbSpecies.getSelectedIndex() == -1) 
		{
			JOptionPane.showMessageDialog(null, "Choose Species");
			cmbSpecies.grabFocus();
			flag = false;
		} 
		else if (ValidateForm.validateAnyString(txtBreed.getText()) == false) 
		{
			JOptionPane.showMessageDialog(null, "Enter Breed ");
			txtBreed.grabFocus();
			flag = false;
		}
		else if (ValidateForm.validateheiWei(txtHeight.getText()) == false) 
		{
			JOptionPane.showMessageDialog(null, "Enter Height");
			txtHeight.grabFocus();
			flag = false;
		}
		else if (ValidateForm.validateheiWei(txtWeight.getText()) == false)
		{
			JOptionPane.showMessageDialog(null, "Enter Weight");
			txtWeight.grabFocus();
			flag = false;
		} 
		else if (ValidateForm.validateMobileNo(txtMob_No.getText()) == false)
		{
			JOptionPane.showMessageDialog(null, "Enter Correct phone no..");
			txtMob_No.grabFocus();
			flag = false;
		} 
		else if (cmbState.getSelectedIndex() == -1) 
		{
			JOptionPane.showMessageDialog(null, "Choose State");
			cmbState.grabFocus();
			flag = false;
		} 
		else if (ValidateForm.validateEmail(txtEmailId.getText()) == false) 
		{
			JOptionPane.showMessageDialog(null, "Enter email id..");
			txtEmailId.grabFocus();
			flag = false;
		}
		else if (txtFull_Address.getText().length() == 0)
		{
			JOptionPane.showMessageDialog(null, "Enter Address");
			txtAge.grabFocus();
			flag = false;
		}
		return flag;
	}

	public void getid()
	{
		AnimalDetailsOperations ado = new AnimalDetailsOperations();
		txtregistration.setText(ado.getid());
		txtName.grabFocus();
	}
}

public class AnimalDetails
{
	RegAnimal ra = new RegAnimal();
}
