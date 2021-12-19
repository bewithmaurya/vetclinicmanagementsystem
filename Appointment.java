import java.awt.*;
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.sql.*;
import java.text.*;
import java.util.Calendar;
import java.util.Date;
import javax.swing.*;
import javax.swing.text.MaskFormatter;

import com.toedter.calendar.JDateChooser;

class Appt extends ValidateForm implements ActionListener, FocusListener, ItemListener
{
	public PreparedStatement ps;
	public ResultSet rs;
	public Connection conn;
	public JFrame frm;
	public JButton btnBOOK, btnClear, btnPrint;
	public JLabel lblVeterinaryManagementSystem, lblAppointment, barlabel, lblregistration, lblTime, lblAppmntId, lblAppmntDate, lblVtime, lblAnimalName, lblFee, lblMedicalHistory, lblWeight, lblDoc_Emp, lblProblem,lblAge, lblService, lblHeight, lblName, lblDate, lblGender, lblMob_No, Heading,Normal1,Normal2,Normal3;
	public JTextField txtregistration, txtApmntId, txtAppmntDate, txtVtime, txtAge, txtProblem, txtFee, txtWeight,txtAnimalName, txtName, txtMob_No, txtHeight;
	public JComboBox<Object> cmbGender, cmbServices, cmbDoc_Emp;
	public JDateChooser dateChooser;
	public JSpinner spinner;
	public Color hexa1;
	public JTextArea textArea;
	String gender[] = { "MALE", "FEMALE" };
	String pet[] = { "BIRDS", "CATS", "DOG", "RABBITS" };
	public static String date, time;

	Font f = new Font("Consolas", Font.BOLD, 13);
	Font heading = new Font("Bookman Old Style", Font.BOLD, 18);

	public MaskFormatter mf1, mf2, mf3, mf4;

	Appt() 
	{
		frm = new JFrame();
		frm.setSize(1270, 620);

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
		frm.setTitle("BOOK APPOINTMENT");

		hexa1 = Color.decode("#82efff");

		lblVeterinaryManagementSystem = new JLabel("Veterinary Management System");
		lblVeterinaryManagementSystem.setFont(new Font("Algerian", Font.BOLD, 34));
		lblVeterinaryManagementSystem.setForeground(Color.WHITE);
		lblVeterinaryManagementSystem.setBounds(330, 12, 650, 30);
		frm.add(lblVeterinaryManagementSystem);

		Canvas canvas = new Canvas();
		canvas.setBackground(Color.WHITE);
		canvas.setForeground(Color.RED);
		canvas.setBounds(330, 40, 635, 5);
		frm.add(canvas);

		lblAppointment = new JLabel("Appointment");
		lblAppointment.setForeground(Color.WHITE);
		lblAppointment.setFont(new Font("Magneto", Font.BOLD, 23));
		lblAppointment.setBounds(500, 60, 300, 27);
		frm.add(lblAppointment);

		barlabel = new JLabel();
		Image userimg = new ImageIcon(this.getClass().getResource("/images/recbar.png")).getImage();
		barlabel.setIcon(new ImageIcon(userimg));
		barlabel.setBounds(0, 0, 1300, 105);
		frm.add(barlabel);

		String curdate = new SimpleDateFormat("MMM dd, yyyy").format(Calendar.getInstance().getTime());
		String curtime = new SimpleDateFormat("hh:mm a").format(new Date()).toString();

		lblDate = new JLabel();
		lblDate.setBounds(595, 110, 150, 30);
		lblDate.setFont(heading);
		lblDate.setText(curdate);
		lblDate.setForeground(Color.WHITE);
		frm.add(lblDate);

		lblTime = new JLabel();
		lblTime.setBounds(730, 110, 150, 30);
		lblTime.setFont(heading);
		lblTime.setText(curtime);
		lblTime.setForeground(Color.WHITE);
		frm.add(lblTime);

		lblregistration = new JLabel("REGISTRATION NO");
		lblregistration.setBounds(150, 130, 200, 30);
		lblregistration.setFont(new Font("Consolas", Font.BOLD, 20));
		lblregistration.setForeground(Color.BLUE);
		frm.add(lblregistration);

		txtregistration = new JTextField();
		txtregistration.setBounds(340, 130, 230, 30);
		txtregistration.setFont(f);
		txtregistration.setBackground(Color.WHITE);
		txtregistration.setForeground(Color.BLACK);
		txtregistration.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		txtregistration.addFocusListener(this);
		frm.add(txtregistration);

		lblAppmntId = new JLabel("APPOINTMENT ID");
		lblAppmntId.setBounds(20, 180, 100, 30);
		lblAppmntId.setFont(f);
		lblAppmntId.setForeground(Color.BLUE);
		frm.add(lblAppmntId);

		txtApmntId = new JTextField();
		txtApmntId.setBounds(150, 180, 250, 30);
		txtApmntId.setFont(f);
		txtApmntId.setBackground(Color.WHITE);
		txtApmntId.setForeground(Color.BLACK);
		txtApmntId.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		getidText();
		frm.add(txtApmntId);

		lblName = new JLabel("OWNER NAME");
		lblName.setBounds(450, 180, 100, 30);
		lblName.setFont(f);
		lblName.setForeground(Color.BLUE);
		frm.add(lblName);

		txtName = new JTextField();
		txtName.setBounds(580, 180, 250, 30);
		txtName.setFont(f);
		txtName.setBackground(Color.WHITE);
		txtName.setForeground(Color.BLACK);
		txtName.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(txtName);

		lblMob_No = new JLabel("MOBILE NO.");
		lblMob_No.setBounds(20, 230, 100, 30);
		lblMob_No.setFont(f);
		lblMob_No.setForeground(Color.BLUE);
		frm.add(lblMob_No);

		txtMob_No = new JTextField();
		txtMob_No.setBounds(150, 230, 250, 30);
		txtMob_No.setFont(f);
		txtMob_No.setBackground(Color.WHITE);
		txtMob_No.setForeground(Color.BLACK);
		txtMob_No.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(txtMob_No);

		lblAnimalName = new JLabel("PET NAME");
		lblAnimalName.setBounds(450, 230, 100, 30);
		lblAnimalName.setFont(f);
		lblAnimalName.setForeground(Color.BLUE);
		frm.add(lblAnimalName);

		txtAnimalName = new JTextField();
		txtAnimalName.setBounds(580, 230, 250, 30);
		txtAnimalName.setFont(f);
		txtAnimalName.setBackground(Color.WHITE);
		txtAnimalName.setForeground(Color.BLACK);
		txtAnimalName.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(txtAnimalName);

		lblGender = new JLabel("GENDER");
		lblGender.setBounds(20, 280, 100, 30);
		lblGender.setFont(f);
		lblGender.setForeground(Color.BLUE);
		frm.add(lblGender);

		cmbGender = new JComboBox<Object>(gender);
		cmbGender.setBounds(150, 280, 250, 30);
		cmbGender.setFont(f);
		cmbGender.setSelectedIndex(-1);
		cmbGender.setBackground(Color.WHITE);
		cmbGender.setForeground(Color.BLACK);
		cmbGender.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(cmbGender);

		lblAge = new JLabel("AGE");
		lblAge.setBounds(450, 280, 150, 30);
		lblAge.setFont(f);
		lblAge.setForeground(Color.BLUE);
		frm.add(lblAge);

		txtAge = new JTextField();
		txtAge.setBounds(580, 280, 250, 30);
		txtAge.setFont(f);
		txtAge.setBackground(Color.WHITE);
		txtAge.setForeground(Color.BLACK);
		txtAge.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(txtAge);

		lblHeight = new JLabel("HEIGHT");
		lblHeight.setBounds(20, 330, 100, 30);
		lblHeight.setFont(f);
		frm.add(lblHeight);
		lblHeight.setForeground(Color.BLUE);

		try 
		{
			mf3 = new MaskFormatter("###CM");
		} 
		catch (ParseException e) 
		{
			e.printStackTrace();
		}

		txtHeight = new JFormattedTextField(mf3);
		txtHeight.setBounds(150, 330, 250, 30);
		txtHeight.setFont(f);
		txtHeight.setBackground(Color.WHITE);
		txtHeight.setForeground(Color.BLACK);
		txtHeight.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(txtHeight);

		lblWeight = new JLabel("WEIGHT");
		lblWeight.setBounds(450, 330, 150, 30);
		lblWeight.setFont(f);
		lblWeight.setForeground(Color.BLUE);
		frm.add(lblWeight);

		try
		{
			mf4 = new MaskFormatter("###KG");
		} 
		catch (ParseException e)
		{
			e.printStackTrace();
		}

		txtWeight = new JFormattedTextField(mf4);
		txtWeight.setBounds(580, 330, 250, 30);
		txtWeight.setFont(f);
		txtWeight.setBackground(Color.WHITE);
		txtWeight.setForeground(Color.BLACK);
		txtWeight.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(txtWeight);

		lblAppmntDate = new JLabel("APPOINTMENT DATE");
		lblAppmntDate.setBounds(20, 380, 150, 30);
		lblAppmntDate.setFont(f);
		lblAppmntDate.setForeground(Color.BLUE);
		frm.add(lblAppmntDate);

		dateChooser = new JDateChooser();
		dateChooser.setBounds(150, 380, 250, 30);
		dateChooser.setFont(f);
		dateChooser.setBackground(Color.WHITE);
		dateChooser.setForeground(Color.BLACK);
		dateChooser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(dateChooser);

		lblVtime = new JLabel("APPOINTMENT TIME");
		lblVtime.setBounds(450, 380, 150, 30);
		lblVtime.setFont(f);
		lblVtime.setForeground(Color.BLUE);
		frm.add(lblVtime);

		Date time = new Date();
		SpinnerDateModel sm = new SpinnerDateModel(time, null, null, Calendar.HOUR_OF_DAY);

		spinner = new JSpinner(sm);
		spinner.setBounds(580, 380, 250, 30);
		JSpinner.DateEditor de = new JSpinner.DateEditor(spinner, "hh:mm a");
		spinner.setEditor(de);
		spinner.setBackground(Color.WHITE);
		spinner.setForeground(Color.BLACK);
		spinner.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(spinner);

		lblProblem = new JLabel("PROBLEM");
		lblProblem.setBounds(20, 430, 150, 30);
		lblProblem.setFont(f);
		lblProblem.setForeground(Color.BLUE);
		frm.add(lblProblem);

		txtProblem = new JTextField();
		txtProblem.setBounds(150, 430, 250, 30);
		txtProblem.setFont(f);
		txtProblem.setBackground(Color.WHITE);
		txtProblem.setForeground(Color.BLACK);
		txtProblem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(txtProblem);

		lblService = new JLabel("SERVICES");
		lblService.setBounds(450, 430, 100, 30);
		lblService.setFont(f);
		lblService.setForeground(Color.BLUE);
		frm.add(lblService);

		cmbServices = new JComboBox<Object>();
		cmbServices.setBounds(580, 430, 250, 30);
		cmbServices.setFont(f);
		cmbServices.setBackground(Color.WHITE);
		cmbServices.setForeground(Color.BLACK);
		cmbServices.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cmbServices.setSelectedIndex(-1);
		srvcour();
		cmbServices.addItemListener(this);
		frm.add(cmbServices);

		lblDoc_Emp = new JLabel("DOCTOR/EMPLOYEE");
		lblDoc_Emp.setBounds(20, 480, 150, 30);
		lblDoc_Emp.setFont(f);
		lblDoc_Emp.setForeground(Color.BLUE);
		frm.add(lblDoc_Emp);

		cmbDoc_Emp = new JComboBox<Object>();
		cmbDoc_Emp.setBounds(150, 480, 250, 30);
		cmbDoc_Emp.setFont(f);
		cmbDoc_Emp.setBackground(Color.WHITE);
		cmbDoc_Emp.setForeground(Color.BLACK);
		cmbDoc_Emp.setSelectedIndex(-1);
		cmbDoc_Emp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(cmbDoc_Emp);

		lblFee = new JLabel("CHARGES");
		lblFee.setBounds(450, 480, 150, 30);
		lblFee.setFont(f);
		lblFee.setForeground(Color.BLUE);
		frm.add(lblFee);

		txtFee = new JTextField();
		txtFee.setBounds(580, 480, 250, 30);
		txtFee.setFont(f);
		txtFee.setEditable(false);
		txtFee.setBackground(Color.WHITE);
		txtFee.setForeground(Color.BLACK);
		txtFee.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(txtFee);

		btnBOOK = new JButton("BOOK");
		btnBOOK.setBounds(20, 530, 392, 35);
		btnBOOK.setFont(f);
		btnBOOK.setForeground(Color.BLUE);
		btnBOOK.setBackground(hexa1);
		btnBOOK.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBOOK.addActionListener(this);
		frm.add(btnBOOK);

		btnClear = new JButton("CLEAR");
		btnClear.setBounds(440, 530, 392, 35);
		btnClear.setFont(f);
		btnClear.setForeground(Color.BLUE);
		btnClear.setBackground(hexa1);
		btnClear.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnClear.addActionListener(this);
		frm.add(btnClear);
		
		Heading = new JLabel("               \tLOVING HAND PET CLINIC        ");
		Normal1 = new JLabel("Dr. Rakesh Kumar (B.V.Sc & Ah)                                Kishore (Pandit Jee)");
		Normal2 = new JLabel("(Vety. Physician & Surgeon)                                                 (Vety. Liv. Dev.)");
		Normal3 = new JLabel("Mob.: 9958938441                                                               Mob.:7838556716");
		
		
		textArea = new JTextArea();
		textArea.setBounds(840, 130, 395, 380);
		textArea.append("***********************************************************************************\n"+Heading.getText()+"\n**********************************************************************************\n"+Normal1.getText()+"\n"+Normal2.getText()+"\n"+Normal3.getText()+"\n-----------------------------------------------------------------------------------------------------\n");
		frm.add(textArea);

		 btnPrint = new JButton("Print");
			btnPrint.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0)
				{
					try {
						textArea.print();
					} catch (PrinterException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			btnPrint.setBounds(845, 530, 388, 35);
			btnPrint.setFont(f);
			btnPrint.setForeground(Color.BLUE);
			btnPrint.setBackground(hexa1);
			btnClear.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			frm.add(btnPrint);
		frm.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource().equals(btnBOOK))
		{
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy");
			String ddate = dateFormat.format(dateChooser.getDate());
			String curdate = new SimpleDateFormat("dd/MMM/yyyy").format(Calendar.getInstance().getTime());
			date = curdate;
			SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");
			String appointmenttime = timeFormat.format(spinner.getValue());
			if (frmvalidate())
			{
				AppointmentOperation ao = new AppointmentOperation(txtregistration.getText().toUpperCase(), date,lblTime.getText(), txtApmntId.getText(), txtName.getText().toUpperCase(), txtAnimalName.getText().toUpperCase(), txtMob_No.getText().toUpperCase(), cmbGender.getSelectedItem().toString().toUpperCase(), txtAge.getText(), txtHeight.getText().toUpperCase(), txtWeight.getText().toUpperCase(), ddate, appointmenttime, txtProblem.getText().toUpperCase(), cmbServices.getSelectedItem().toString().toUpperCase(), txtFee.getText().toUpperCase(), cmbDoc_Emp.getSelectedItem().toString());
				if (ao.userValidate()) 
				{
					if (ao.saverecord() == 1) 
					{
						JOptionPane.showMessageDialog(null, "APPOINTMENT SUCCESFULL BOOK");
						textArea.append("\t\t\t   Date : "+curdate+"\nOwner Name : "+txtName.getText()+"\nPet Name : "+txtAnimalName.getText()+"\n\n\n"+"Registration Number : "+txtregistration.getText()+"\nAppointment Number : "+txtApmntId.getText()+"\nAppointment Date : "+ddate+"\nProblem : "+txtProblem.getText()+"\nServices : "+cmbServices.getSelectedItem().toString()+"\nFee : "+txtFee.getText());
						txtregistration.setText("");
						txtregistration.setEditable(true);
						clearForm();
						frm.dispose();
					}

				}
				else 
				{
					JOptionPane.showMessageDialog(null, "Record Already Inserted");
				}

			}

		} 
		else if (e.getSource().equals(btnClear))
		{
			clearForm();
		}
	}

	public void clearForm() 
	{
		txtregistration.setText("");
		txtName.setText("");
		txtMob_No.setText("");
		txtAnimalName.setText("");
		cmbGender.setSelectedIndex(-1);
		txtAge.setText("");
		txtHeight.setText("");
		txtWeight.setText("");
		dateChooser.setDate(null);
		txtProblem.setText("");
		cmbServices.setSelectedIndex(-1);
		cmbDoc_Emp.setSelectedIndex(-1);
		txtFee.setText("");
	}

	public boolean frmvalidate()
	{
		boolean flag = true;
		if (txtregistration.getText().equals(""))
		{
			JOptionPane.showMessageDialog(null, "Enter Registration No. ");
			txtregistration.grabFocus();
			flag = false;
		} 
		else if (ValidateForm.validateAnyString(txtName.getText()) == false)
		{
			JOptionPane.showMessageDialog(null, "Enter Owner Name ");
			txtName.grabFocus();
			flag = false;
		}
		else if (ValidateForm.validateMobileNo(txtMob_No.getText()) == false)
		{
			JOptionPane.showMessageDialog(null, "Enter Correct phone no..");
			txtMob_No.grabFocus();
			flag = false;
		}
		else if (ValidateForm.validateAnyString(txtAnimalName.getText()) == false)
		{
			JOptionPane.showMessageDialog(null, "Enter Animal name ");
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
		else if (ValidateForm.validateheiWei(txtHeight.getText()) == false) 
		{
			JOptionPane.showMessageDialog(null, "Choose Species");
			txtHeight.grabFocus();
			flag = false;
		}
		else if (ValidateForm.validateheiWei(txtWeight.getText()) == false) 
		{
			JOptionPane.showMessageDialog(null, "Enter Breed ");
			txtWeight.grabFocus();
			flag = false;
		}
		return flag;
	}

	@Override
	public void focusGained(FocusEvent e) 
	{

	}

	@Override
	public void focusLost(FocusEvent e) 
	{
		AnimalDetailsOperations ado = new AnimalDetailsOperations();
		ResultSet rs = ado.sec_recordforApp((txtregistration.getText()));
		try 
		{
			if (rs.next())
			{
				txtName.setText(rs.getString(1));
				txtAnimalName.setText(rs.getString(2));
				if (rs.getString(3).equals(" MALE")) 
				{
					cmbGender.setSelectedIndex(0);
				} 
				else if (rs.getString(3).equals(" FEMALE"))
				{
					cmbGender.setSelectedIndex(1);
				}
				cmbGender.setSelectedItem(rs.getString(3));
				txtAge.setText(rs.getString(4));
				txtHeight.setText(rs.getString(5));
				txtWeight.setText(rs.getString(6));
				txtMob_No.setText(rs.getString(7));
			}
		} 
		catch (SQLException e1) 
		{
			e1.printStackTrace();
		}

	}

	public void srvcour() 
	{
		String sqlqry = "select SERVICE from SERVICE_DETAILS";
		conn = Connections.getConnections();
		try 
		{
			ps = conn.prepareStatement(sqlqry);
			rs = ps.executeQuery();
			while (rs.next()) 
			{
				cmbServices.addItem(rs.getString(1));
				cmbServices.setSelectedIndex(-1);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	public void fee(String serv) 
	{
		String query = "select FEE from SERVICE_DETAILS WHERE SERVICE='" + serv + "'";
		conn = Connections.getConnections();
		try 
		{
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) 
			{
				txtFee.setText(rs.getString(1));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		txtFee.getText();
	}

	public void getidText()
	{
		AppointmentOperation ao = new AppointmentOperation();
		txtApmntId.setText(ao.getid());
		txtApmntId.setEditable(false);
	}

	public void doccour()
	{
		String sqlqry = "select NAME from DOCTOR_DETAILS";
		conn = Connections.getConnections();
		try 
		{
			ps = conn.prepareStatement(sqlqry);
			rs = ps.executeQuery();
			while (rs.next()) 
			{
				cmbDoc_Emp.addItem(rs.getString(1));
				cmbDoc_Emp.setSelectedIndex(-1);
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	public void empcour() 
	{
		String sqlqry = "select NAME from EMPLOYEE_DETAILS";
		conn = Connections.getConnections();
		try
		{
			ps = conn.prepareStatement(sqlqry);
			rs = ps.executeQuery();
			while (rs.next()) 
			{
				cmbDoc_Emp.addItem(rs.getString(1));
				cmbDoc_Emp.setSelectedIndex(-1);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e)
	{
		cmbDoc_Emp.removeAllItems();
		if ((cmbServices.getSelectedItem().toString()).equals("CONSULTANT CHARGES"))
		{
			doccour();
			fee(cmbServices.getSelectedItem().toString());
		} 
		else 
		{
			empcour();
			fee(cmbServices.getSelectedItem().toString());
		}

	}
}

public class Appointment 
{
	Appt ap = new Appt();
}
