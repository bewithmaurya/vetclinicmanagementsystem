import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.*;
import java.util.Calendar;
import java.util.Date;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.text.MaskFormatter;
import net.proteanit.sql.DbUtils;

class Presc extends Base implements ActionListener, WindowListener 
{
	public static String abc = null;
	public JFrame frm;
	public PreparedStatement ps;
	public ResultSet rs;
	public Connection conn;
	public JButton btnSAVE, btnMedicine, btnVaccin, btnUpdate, btnClear;
	public JLabel lblVeterinaryManagementSystem, barlabel, lblPrescribe, lblprescription, lblDoctorName, lblRegNo,lblTime, lblAnimalName, lblOwnerName, lblMedicalHistory, lblWeight, lblState, lblAge, lblEmailId, lblHeight,lblAppointmentNo, lblDate, lblSpecies, lblBreed, lblPrescription, lblMob_No, lblcurrentCondition, lbllogo;
	public JTextField txtprescription, txtOwnerName, txtDoctorName, txtRegNo, txtAge, txtWeight, txtAnimalName,txtBreed, txtAppointmentNo, txtDate, txtMob_No, txtEmailId, txtHeight;
	public JComboBox<Object> cmbSpecies, cmbState;
	public JTextArea txtPrescription, txtCurrentCondition, txtMedicalHistory;
	public Color hexa1;
	public DefaultTableModel jtable;
	public JTable tabview;
	public boolean start = true;
	public JScrollPane jsCurrentCondition, jsMedicalHistory, JSPrescription;
	public static String date;

	Font heading = new Font("Bookman Old Style", Font.BOLD, 18);
	Font f = new Font("Consolas", Font.BOLD, 13);
	
	public MaskFormatter mf1, mf2, mf3;

	Presc() 
	{
		frm = new JFrame();
		frm.setSize(1200, 560);

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
		frm.setTitle("TREATMENT FORM");
		hexa1 = Color.decode("#82efff");

		lblVeterinaryManagementSystem = new JLabel("Veterinary Management System");
		lblVeterinaryManagementSystem.setFont(new Font("Algerian", Font.BOLD, 34));
		lblVeterinaryManagementSystem.setForeground(Color.WHITE);
		lblVeterinaryManagementSystem.setBounds(280, 12, 650, 30);
		frm.add(lblVeterinaryManagementSystem);

		Canvas canvas = new Canvas();
		canvas.setBackground(Color.WHITE);
		canvas.setForeground(Color.RED);
		canvas.setBounds(280, 40, 635, 5);
		frm.add(canvas);

		lblPrescribe = new JLabel("Treatment Form");
		lblPrescribe.setForeground(Color.WHITE);
		lblPrescribe.setFont(new Font("Magneto", Font.BOLD, 23));
		lblPrescribe.setBounds(510, 60, 300, 27);
		frm.add(lblPrescribe);

		barlabel = new JLabel();
		Image userimg = new ImageIcon(this.getClass().getResource("/images/doc_bar.png")).getImage();
		barlabel.setIcon(new ImageIcon(userimg));
		barlabel.setBounds(0, 0, 1260, 105);
		frm.add(barlabel);

		lblDoctorName = new JLabel();
		lblDoctorName.setBounds(20, 120, 200, 30);
		lblDoctorName.setFont(heading);
		lblDoctorName.setForeground(Color.WHITE);
		frm.add(lblDoctorName);

		String curdate = new SimpleDateFormat("MMM dd, yyyy").format(Calendar.getInstance().getTime());
		String curtime = new SimpleDateFormat("hh:mm a").format(new Date()).toString();

		lblDate = new JLabel();
		lblDate.setBounds(935, 120, 150, 30);
		lblDate.setFont(heading);
		lblDate.setText(curdate);
		lblDate.setForeground(Color.WHITE);
		frm.add(lblDate);

		lblTime = new JLabel();
		lblTime.setBounds(1070, 120, 150, 30);
		lblTime.setFont(heading);
		lblTime.setText(curtime);
		lblTime.setForeground(Color.WHITE);
		frm.add(lblTime);

		lblAppointmentNo = new JLabel("APPOINTMENT NO");
		lblAppointmentNo.setBounds(380, 120, 200, 30);
		lblAppointmentNo.setFont(new Font("Consolas", Font.BOLD, 20));
		lblAppointmentNo.setForeground(Color.RED);
		frm.add(lblAppointmentNo);

		txtAppointmentNo = new JTextField();
		txtAppointmentNo.setBounds(590, 120, 200, 30);
		txtAppointmentNo.setFont(new Font("Consolas", Font.BOLD, 20));
		txtAppointmentNo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		txtAppointmentNo.setForeground(Color.BLACK);
		frm.add(txtAppointmentNo);

		lblprescription = new JLabel("PRESCRIPTION NO");
		lblprescription.setBounds(20, 170, 150, 30);
		lblprescription.setFont(f);
		lblprescription.setForeground(Color.BLUE);
		frm.add(lblprescription);

		txtprescription = new JTextField();
		txtprescription.setBounds(150, 170, 200, 30);
		txtprescription.setFont(f);
		txtprescription.setBackground(Color.WHITE);
		txtprescription.setForeground(Color.BLACK);
		txtprescription.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		txtprescription.setEditable(false);
		frm.add(txtprescription);

		lblRegNo = new JLabel("REGISTRATION NO");
		lblRegNo.setBounds(380, 170, 150, 30);
		lblRegNo.setFont(f);
		lblRegNo.setForeground(Color.BLUE);
		frm.add(lblRegNo);

		txtRegNo = new JTextField();
		txtRegNo.setBounds(500, 170, 200, 30);
		txtRegNo.setFont(f);
		txtRegNo.setForeground(Color.BLACK);
		txtRegNo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(txtRegNo);

		lblOwnerName = new JLabel("OWNER NAME");
		lblOwnerName.setBounds(20, 220, 100, 30);
		lblOwnerName.setFont(f);
		lblOwnerName.setForeground(Color.BLUE);
		frm.add(lblOwnerName);

		txtOwnerName = new JTextField();
		txtOwnerName.setBounds(150, 220, 200, 30);
		txtOwnerName.setFont(f);
		txtOwnerName.setForeground(Color.BLACK);
		txtOwnerName.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(txtOwnerName);

		lblAnimalName = new JLabel("PET NAME");
		lblAnimalName.setBounds(380, 220, 100, 30);
		lblAnimalName.setFont(f);
		lblAnimalName.setForeground(Color.BLUE);
		frm.add(lblAnimalName);

		txtAnimalName = new JTextField();
		txtAnimalName.setBounds(500, 220, 200, 30);
		txtAnimalName.setFont(f);
		txtAnimalName.setForeground(Color.BLACK);
		txtAnimalName.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(txtAnimalName);

		lblAge = new JLabel("AGE");
		lblAge.setBounds(20, 270, 150, 30);
		lblAge.setFont(f);
		lblAge.setForeground(Color.BLUE);
		frm.add(lblAge);

		txtAge = new JTextField();
		txtAge.setBounds(150, 270, 200, 30);
		txtAge.setFont(f);
		txtAge.setForeground(Color.BLACK);
		txtAge.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(txtAge);

		lblBreed = new JLabel("BREED");
		lblBreed.setBounds(380, 270, 100, 30);
		lblBreed.setFont(f);
		lblBreed.setForeground(Color.BLUE);
		frm.add(lblBreed);

		txtBreed = new JTextField();
		txtBreed.setBounds(500, 270, 200, 30);
		txtBreed.setFont(f);
		txtBreed.setBackground(Color.WHITE);
		txtBreed.setForeground(Color.BLACK);
		txtBreed.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(txtBreed);

		lblHeight = new JLabel("HEIGHT");
		lblHeight.setBounds(20, 320, 100, 30);
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
		txtHeight.setBounds(150, 320, 200, 30);
		txtHeight.setFont(f);
		txtHeight.setForeground(Color.BLACK);
		txtHeight.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(txtHeight);

		lblWeight = new JLabel("WEIGHT");
		lblWeight.setBounds(380, 320, 80, 30);
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
		txtWeight.setBounds(500, 320, 200, 30);
		txtWeight.setFont(f);
		txtWeight.setForeground(Color.BLACK);
		txtWeight.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(txtWeight);

		lblPrescription = new JLabel("PRESCRIPTION");
		lblPrescription.setBounds(20, 370, 100, 30);
		lblPrescription.setFont(f);
		lblPrescription.setForeground(Color.BLUE);
		frm.add(lblPrescription);

		JSPrescription = new JScrollPane();
		txtPrescription = new JTextArea();
		txtPrescription.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		JSPrescription.setBounds(150, 370, 350, 80);
		txtPrescription.setFont(f);
		txtPrescription.setForeground(Color.BLACK);
		JSPrescription.setViewportView(txtPrescription);
		frm.add(JSPrescription);

		btnMedicine = new JButton("ADD MEDICINE");
		btnMedicine.setBounds(510, 370, 190, 35);
		btnMedicine.setFont(f);
		btnMedicine.setForeground(Color.BLUE);
		btnMedicine.setBackground(hexa1);
		btnMedicine.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(btnMedicine);
		btnMedicine.addActionListener(this);

		btnVaccin = new JButton("ADD VACINATION");
		btnVaccin.setBounds(510, 415, 190, 35);
		btnVaccin.setFont(f);
		btnVaccin.setForeground(Color.BLUE);
		btnVaccin.setBackground(hexa1);
		btnVaccin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(btnVaccin);
		btnVaccin.addActionListener(this);

		lblcurrentCondition = new JLabel("CURRENT CONDITION");
		lblcurrentCondition.setBounds(720, 170, 170, 30);
		lblcurrentCondition.setForeground(Color.BLUE);
		frm.add(lblcurrentCondition);

		jsCurrentCondition = new JScrollPane();
		jsCurrentCondition.setBounds(720, 200, 210, 90);
		txtCurrentCondition = new JTextArea();
		txtCurrentCondition.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		txtCurrentCondition.setFont(f);
		txtCurrentCondition.setForeground(Color.BLACK);
		txtCurrentCondition.setEditable(false);
		jsCurrentCondition.setViewportView(txtCurrentCondition);
		frm.add(jsCurrentCondition);

		lblMedicalHistory = new JLabel("MEDICAL HISTORY");
		lblMedicalHistory.setBounds(955, 170, 150, 30);
		lblMedicalHistory.setForeground(Color.BLUE);
		frm.add(lblMedicalHistory);

		jsMedicalHistory = new JScrollPane();
		txtMedicalHistory = new JTextArea();
		jsMedicalHistory.setBounds(955, 200, 210, 90);
		txtMedicalHistory.setFont(f);
		txtMedicalHistory.setForeground(Color.BLACK);
		txtMedicalHistory.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		txtMedicalHistory.setEditable(false);
		jsMedicalHistory.setViewportView(txtMedicalHistory);
		frm.add(jsMedicalHistory);

		jtable = new DefaultTableModel();
		tabview = new JTable(jtable);
		JScrollPane js = new JScrollPane(tabview);
		js.setBounds(720, 300, 450, 210);
		tabview.setFont(f);
		tabview.setForeground(Color.blue);
		tabview.setBackground(Color.WHITE);
		tabview.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Object[] columns = { "Id", "MEDICINE", "DRUGS", "TYPE", "COMPANY" };
		jtable.setColumnIdentifiers(columns);
		tabview.setModel(jtable);

		tabview.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				getidtext();
				int i = tabview.getSelectedRow();
				TableModel model = tabview.getModel();
				txtAppointmentNo.setText(model.getValueAt(i, 0).toString());
				txtRegNo.setText(model.getValueAt(i, 1).toString());
				txtOwnerName.setText(model.getValueAt(i, 2).toString());
				txtAnimalName.setText(model.getValueAt(i, 3).toString());
				txtAge.setText(model.getValueAt(i, 4).toString());
				txtBreed.setText(model.getValueAt(i, 5).toString());
				txtHeight.setText(model.getValueAt(i, 6).toString());
				txtWeight.setText(model.getValueAt(i, 7).toString());
				txtCurrentCondition.setText(model.getValueAt(i, 8).toString());
			}
		});
		frm.add(js);

		btnSAVE = new JButton("SAVE");
		btnSAVE.setBounds(20, 470, 330, 40);
		btnSAVE.setFont(f);
		btnSAVE.setForeground(Color.BLUE);
		btnSAVE.setBackground(hexa1);
		btnSAVE.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(btnSAVE);
		btnSAVE.addActionListener(this);

		btnClear = new JButton("CLEAR");
		btnClear.setBounds(370, 470, 330, 40);
		btnClear.setFont(f);
		btnClear.setForeground(Color.BLUE);
		btnClear.setBackground(hexa1);
		btnClear.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(btnClear);
		btnClear.addActionListener(this);

		frm.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource().equals(btnSAVE)) 
		{
			String curdate = new SimpleDateFormat("dd/MMM/yyyy").format(Calendar.getInstance().getTime());
			date = curdate;
			String MedicalHistory = txtMedicalHistory.getText();
			String history = date + "Problem  -  " + txtCurrentCondition.getText().toUpperCase() + "Solved  -  " + txtPrescription.getText().toUpperCase() + MedicalHistory;
			if (frmvalidate())
			{
				PrescriptionOperation po = new PrescriptionOperation(date, txtprescription.getText().toUpperCase(), lblAppointmentNo.getText().toUpperCase(), txtRegNo.getText().toUpperCase(),lblDoctorName.getText().toUpperCase(), txtOwnerName.getText().toUpperCase(), txtAnimalName.getText().toUpperCase(), txtCurrentCondition.getText().toUpperCase(), txtPrescription.getText().toUpperCase());
				if (po.doc_saverecord() == 1) 
				{
					AnimalDetailsOperations ado = new AnimalDetailsOperations(txtRegNo.getText().toUpperCase(),history);
					if (ado.animal_update() == 1)
					{
						JOptionPane.showMessageDialog(null, "SAVED SUCCESSFULLY");
						GenerateTreat gt = new GenerateTreat();
						gt.textArea.append(curdate+"\nName : "+txtOwnerName.getText() +"  (  "+txtAnimalName.getText()+"  )                                        Age: "+txtAge.getText() +"\n\n"+"Problem : \n"+txtCurrentCondition.getText()+"\n\n\n"+"Medicine :\n"+txtPrescription.getText());
						frm.dispose();
					}
				}
				else 
				{
					JOptionPane.showMessageDialog(null, "NOT SAVED");
				}
			}

		} 
		else if (e.getSource().equals(btnClear))
		{
			clearForm();
		} 
		else if (e.getSource().equals(btnMedicine)) 
		{
			new AddMedicine();
			frm.addWindowListener(this);
		} 
		else if (e.getSource().equals(btnVaccin)) 
		{
			new AddVaccination();
			frm.addWindowListener(this);
		}
	}

	public void clearForm() 
	{
		txtAppointmentNo.setText("");
		txtRegNo.setText("");
		txtOwnerName.setText("");
		txtAnimalName.setText("");
		txtBreed.setText("");
		txtAge.setText("");
		txtHeight.setText("");
		txtWeight.setText("");
		txtPrescription.setText("");
		txtCurrentCondition.setText("");
	}

	public boolean frmvalidate()
	{
		boolean flag = true;
		if (txtAppointmentNo.getText().length() == 0) 
		{
			JOptionPane.showMessageDialog(null, "Enter Appointment No.");
			txtAppointmentNo.grabFocus();
			flag = false;
		} 
		else if (txtRegNo.getText().length() == 0) 
		{
			JOptionPane.showMessageDialog(null, "Enter Registration No.");
			txtRegNo.grabFocus();
			flag = false;
		} 
		else if (txtOwnerName.getText().length() == 0) 
		{
			JOptionPane.showMessageDialog(null, "Enter Owner Name");
			txtOwnerName.grabFocus();
			flag = false;
		} 
		else if (txtAnimalName.getText().length() == 0) 
		{
			JOptionPane.showMessageDialog(null, "Enter Pet Name");
			txtAnimalName.grabFocus();
			flag = false;
		} 
		else if (txtAge.getText().length() == 0) 
		{
			JOptionPane.showMessageDialog(null, "Enter Age");
			txtAge.grabFocus();
			flag = false;
		} 
		else if (txtBreed.getText().length() == 0) 
		{
			JOptionPane.showMessageDialog(null, "Enter Breed");
			txtBreed.grabFocus();
			flag = false;
		} 
		else if (txtHeight.getText().length() == 0) 
		{
			JOptionPane.showMessageDialog(null, "Enter Height");
			txtHeight.grabFocus();
			flag = false;
		} 
		else if (txtWeight.getText().length() == 0) 
		{
			JOptionPane.showMessageDialog(null, "Enter Weight");
			txtWeight.grabFocus();
			flag = false;
		} 
		else if (txtPrescription.getText().length() == 0) 
		{
			JOptionPane.showMessageDialog(null, "Enter Prescription");
			txtPrescription.grabFocus();
			flag = false;
		}
		return flag;
	}

	public void getidtext() 
	{
		PrescriptionOperation po = new PrescriptionOperation();
		txtprescription.setText(po.doc_getid());
	}

	public void ShowTable(String date, String Name) 
	{
		String curdate = new SimpleDateFormat("dd/MMM/yyyy").format(Calendar.getInstance().getTime());
		String sqlqry = "select APPOINTMENT.APPOINTMENT_NO,APPOINTMENT.REG_NO,APPOINTMENT.OWNER_NAME,APPOINTMENT.PET_NAME,APPOINTMENT.AGE,ANIMAL_DETAILS.BREED,APPOINTMENT.HEIGHT,APPOINTMENT.WEIGHT,APPOINTMENT.PROBLEM,ANIMAL_DETAILS.MEDICAL_HISTORY from APPOINTMENT INNER JOIN ANIMAL_DETAILS ON APPOINTMENT.REG_NO=ANIMAL_DETAILS.REG_NO WHERE APPOINTMENT_DATE='"+ curdate + "' AND DOC_EMP_NAME='" + Name + "'";
		conn = Connections.getConnections();
		try 
		{
			ps = conn.prepareStatement(sqlqry);
			rs = ps.executeQuery();
			tabview.setModel(DbUtils.resultSetToTableModel(rs));
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void windowOpened(WindowEvent e)
	{
		
	}

	@Override
	public void windowClosing(WindowEvent e) 
	{

	}

	@Override
	public void windowClosed(WindowEvent e)
	{

	}

	@Override
	public void windowIconified(WindowEvent e)
	{

	}

	@Override
	public void windowDeiconified(WindowEvent e) 
	{

	}

	@SuppressWarnings("static-access")
	@Override
	public void windowActivated(WindowEvent e) 
	{
		String value = super.Medical;
		txtPrescription.setText(abc + value);
	}

	@Override
	public void windowDeactivated(WindowEvent e) 
	{
		abc = txtPrescription.getText();
	}
}

public class prescription 
{
	Presc ps = new Presc();
}
