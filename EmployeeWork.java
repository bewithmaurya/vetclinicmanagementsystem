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

class EmpWork extends Base implements ActionListener 
{
	public static String abc = null;
	public JFrame frm;
	public PreparedStatement ps;
	public ResultSet rs;
	public Connection conn;
	public JButton btnSAVE, btnMedicine, btnVaccin, btnUpdate, btnClear;
	public JLabel lblVeterinaryManagementSystem, barlabel, lblPrescribe, lblprescription, lblEmployeeName, lblRegNo,lblTime, lblAnimalName, lblOwnerName, lblMedicalHistory, lblWeight, lblState, lblService, lblEmailId,lblHeight, lblAppointmentNo, lblDate, lblSpecies, lblRemark, lblPrescription, lblMob_No,lblcurrentCondition, lbllogo;
	public JTextField txtprescription, txtOwnerName, txtDoctorName, txtRegNo, txtService, txtWeight, txtAnimalName,txtAppointmentNo, txtDate, txtMob_No, txtEmailId, txtHeight;
	public JComboBox<Object> cmbRemark;
	public JTextArea txtPrescription, txtCurrentCondition, txtMedicalHistory;
	public Color hexa1;
	public DefaultTableModel jtable;
	public JTable tabview;
	public boolean start = true;
	public static String date;
	public JScrollPane jsCurrentCondition, jsMedicalHistory, JSPrescription;

	Font heading = new Font("Bookman Old Style", Font.BOLD, 18);
	Font f = new Font("Consolas", Font.BOLD, 13);
	String remark[] = { "SOLVED" };

	public MaskFormatter mf1, mf2, mf3;

	EmpWork() 
	{
		frm = new JFrame();
		frm.setSize(735, 630);

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
		lblVeterinaryManagementSystem.setBounds(40, 12, 650, 30);
		frm.add(lblVeterinaryManagementSystem);

		Canvas canvas = new Canvas();
		canvas.setBackground(Color.WHITE);
		canvas.setForeground(Color.RED);
		canvas.setBounds(40, 40, 635, 5);
		frm.add(canvas);

		lblPrescribe = new JLabel("Treatment Form");
		lblPrescribe.setForeground(Color.WHITE);
		lblPrescribe.setFont(new Font("Magneto", Font.BOLD, 23));
		lblPrescribe.setBounds(260, 60, 300, 27);
		frm.add(lblPrescribe);

		barlabel = new JLabel();
		Image userimg = new ImageIcon(this.getClass().getResource("/images/doc_bar.png")).getImage();
		barlabel.setIcon(new ImageIcon(userimg));
		barlabel.setBounds(0, 0, 1260, 105);
		frm.add(barlabel);

		lblEmployeeName = new JLabel();
		lblEmployeeName.setBounds(10, 120, 200, 30);
		lblEmployeeName.setFont(heading);
		lblEmployeeName.setForeground(Color.WHITE);
		frm.add(lblEmployeeName);

		String curdate = new SimpleDateFormat("MMM dd, yyyy").format(Calendar.getInstance().getTime());
		String curtime = new SimpleDateFormat("hh:mm a").format(new Date()).toString();

		lblDate = new JLabel();
		lblDate.setBounds(490, 120, 150, 30);
		lblDate.setFont(heading);
		lblDate.setText(curdate);
		lblDate.setForeground(Color.WHITE);
		frm.add(lblDate);

		lblTime = new JLabel();
		lblTime.setBounds(620, 120, 150, 30);
		lblTime.setFont(heading);
		lblTime.setText(curtime);
		lblTime.setForeground(Color.WHITE);
		frm.add(lblTime);

		lblAppointmentNo = new JLabel();
		lblAppointmentNo.setBounds(260, 120, 200, 30);
		lblAppointmentNo.setFont(new Font("Consolas", Font.BOLD, 20));
		lblAppointmentNo.setForeground(Color.RED);
		frm.add(lblAppointmentNo);

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
		txtRegNo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		txtRegNo.setForeground(Color.BLACK);
		frm.add(txtRegNo);

		lblOwnerName = new JLabel("OWNER NAME");
		lblOwnerName.setBounds(20, 220, 100, 30);
		lblOwnerName.setFont(f);
		lblOwnerName.setForeground(Color.BLUE);
		frm.add(lblOwnerName);

		txtOwnerName = new JTextField();
		txtOwnerName.setBounds(150, 220, 200, 30);
		txtOwnerName.setFont(f);
		txtOwnerName.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		txtOwnerName.setForeground(Color.BLACK);
		frm.add(txtOwnerName);

		lblAnimalName = new JLabel("PET NAME");
		lblAnimalName.setBounds(380, 220, 100, 30);
		lblAnimalName.setFont(f);
		lblAnimalName.setForeground(Color.BLUE);
		frm.add(lblAnimalName);

		txtAnimalName = new JTextField();
		txtAnimalName.setBounds(500, 220, 200, 30);
		txtAnimalName.setFont(f);
		txtAnimalName.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		txtAnimalName.setForeground(Color.BLACK);
		frm.add(txtAnimalName);

		lblService = new JLabel("SERVICES");
		lblService.setBounds(20, 270, 150, 30);
		lblService.setFont(f);
		lblService.setForeground(Color.BLUE);
		frm.add(lblService);

		txtService = new JTextField();
		txtService.setBounds(150, 270, 200, 30);
		txtService.setFont(f);
		txtService.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		txtService.setForeground(Color.BLACK);
		frm.add(txtService);

		lblRemark = new JLabel("REMARK");
		lblRemark.setBounds(380, 270, 100, 30);
		lblRemark.setFont(f);
		lblRemark.setForeground(Color.BLUE);
		frm.add(lblRemark);

		cmbRemark = new JComboBox<Object>(remark);
		cmbRemark.setBounds(500, 270, 200, 30);
		cmbRemark.setFont(f);
		cmbRemark.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cmbRemark.setSelectedIndex(-1);
		cmbRemark.setBackground(Color.WHITE);
		cmbRemark.setForeground(Color.BLACK);
		frm.add(cmbRemark);
		
		jsMedicalHistory = new JScrollPane();
		txtMedicalHistory = new JTextArea();
		txtMedicalHistory.setFont(f);
		txtMedicalHistory.setForeground(Color.BLACK);
		txtMedicalHistory.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		txtMedicalHistory.setEditable(false);
		jsMedicalHistory.setViewportView(txtMedicalHistory);

		jtable = new DefaultTableModel();
		tabview = new JTable(jtable);
		JScrollPane js = new JScrollPane(tabview);
		js.setBounds(20, 315, 680, 210);
		tabview.setFont(f);
		tabview.setForeground(Color.blue);
		tabview.setBackground(Color.WHITE);
		tabview.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tabview.setModel(jtable);
		ShowTable(lblDate.getText(), lblEmployeeName.getText());
		tabview.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				getidtext();
				int i = tabview.getSelectedRow();
				TableModel model = tabview.getModel();
				lblAppointmentNo.setText(model.getValueAt(i, 0).toString());
				txtRegNo.setText(model.getValueAt(i, 1).toString());
				txtOwnerName.setText(model.getValueAt(i, 2).toString());
				txtAnimalName.setText(model.getValueAt(i, 3).toString());
				txtService.setText(model.getValueAt(i, 4).toString());
			}
		});
		frm.add(js);

		btnSAVE = new JButton("SAVE");
		btnSAVE.setBounds(20, 540, 330, 40);
		btnSAVE.setFont(f);
		btnSAVE.setForeground(Color.BLUE);
		btnSAVE.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSAVE.setBackground(hexa1);
		frm.add(btnSAVE);
		btnSAVE.addActionListener(this);

		btnClear = new JButton("CLEAR");
		btnClear.setBounds(370, 540, 330, 40);
		btnClear.setFont(f);
		btnClear.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnClear.setForeground(Color.BLUE);
		btnClear.setBackground(hexa1);
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
			String history = date + "Problem  -  " + txtService.getText().toUpperCase() + "Solved  -  " + cmbRemark.getSelectedItem().toString().toUpperCase() + MedicalHistory;
			if (frmvalidate()) 
			{
				PrescriptionOperation po = new PrescriptionOperation(date, txtprescription.getText().toUpperCase(), lblAppointmentNo.getText().toUpperCase(), txtRegNo.getText().toUpperCase(), lblEmployeeName.getText().toUpperCase(), txtOwnerName.getText().toUpperCase(), txtAnimalName.getText().toUpperCase(), txtService.getText().toUpperCase(), cmbRemark.getSelectedItem().toString().toUpperCase());
				if (po.emp_saverecord() == 1) 
				{
					AnimalDetailsOperations ado = new AnimalDetailsOperations(txtRegNo.getText().toUpperCase(),history);
					if (ado.animal_update() == 1) 
					{
						JOptionPane.showMessageDialog(null, "SAVED SUCCESSFULLY");
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
	}

	public void clearForm() 
	{
		lblAppointmentNo.setText("");
		txtRegNo.setText("");
		txtOwnerName.setText("");
		txtAnimalName.setText("");
		cmbRemark.setSelectedIndex(-1);
		txtService.setText("");
	}

	public boolean frmvalidate() 
	{
		boolean flag = true;
		if (txtRegNo.getText().length() == 0) 
		{
			JOptionPane.showMessageDialog(null, "Enter Registration No");
			txtRegNo.grabFocus();
			flag = false;
		}
		if (txtOwnerName.getText().length() == 0) 
		{
			JOptionPane.showMessageDialog(null, "Enter Owner Name");
			txtOwnerName.grabFocus();
			flag = false;
		}
		if (txtAnimalName.getText().length() == 0)
		{
			JOptionPane.showMessageDialog(null, "Enter Pet Name");
			txtAnimalName.grabFocus();
			flag = false;
		}
		if (txtService.getText().length() == 0) 
		{
			JOptionPane.showMessageDialog(null, "Enter Service");
			txtService.grabFocus();
			flag = false;
		}
		if (cmbRemark.getSelectedIndex() == -1) 
		{
			JOptionPane.showMessageDialog(null, "Choose Status");
			cmbRemark.grabFocus();
			flag = false;
		}
		return flag;
	}

	public void getidtext() 
	{
		PrescriptionOperation po = new PrescriptionOperation();
		txtprescription.setText(po.emp_getid());
	}

	public void ShowTable(String date, String Name) 
	{
		String curdate = new SimpleDateFormat("dd/MMM/yyyy").format(Calendar.getInstance().getTime());
		String sqlqry = "select APPOINTMENT_NO,REG_NO,OWNER_NAME,PET_NAME,SERVICES from APPOINTMENT WHERE APPOINTMENT_DATE='" + curdate + "' AND DOC_EMP_NAME='" + Name + "'";
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
}

public class EmployeeWork 
{
	EmpWork ew = new EmpWork();
}
