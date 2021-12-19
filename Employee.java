import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.*;
import java.util.Date;
import javax.swing.*;
import javax.swing.table.*;
import com.toedter.calendar.JDateChooser;
import net.proteanit.sql.DbUtils;

class Emp extends ValidateForm implements ActionListener
{
	public JFrame frm;
	public PreparedStatement ps;
	public ResultSet rs;
	public Connection conn;
	public JButton btnNEW, btnUploade, btnEDIT, btnCLEAR;
	public DefaultTableModel jtable;
	public JTable tabview;
	public JLabel lblId, lbluser, lblsAnswer, lblpass, lblconfpass, lblSearch, lblsQueston, lblQualification,lblVeterinaryManagementSystem, lblEmployeeRegister, barlabel, lblEmailId, lblName, lblDOB, lblPost,lblGender, lblFull_Address, lblMob_No, lblimg;
	public JTextField txtId, txtUser_Id, txtsAnswer, txtName, txtimg, txtDOB, txtSearch, txtQualification, txtPost,txtMob_No, txtEmailId;
	public JComboBox<Object> cmbGender, cmbSecQueston;
	public JTextArea txtFull_Address;
	public JPasswordField txtPASS, txt_ConfPASS;
	public Color hexa1;
	public JScrollPane js, jsAddress;
	public TableRowSorter<TableModel> rowSorter;

	public JDateChooser dateChooser;
	String arr[] = { "MALE", "FEMALE" };
	String arr1[] = { "NICK NAME", "FAVOURITE COLOR", "FAVOURITE FOOD", "FAVOURITE PLACE" };

	Font f = new Font("Consolas", Font.BOLD, 13);

	JFileChooser fch = new JFileChooser();
	String path = "";
	String filename = "";
	String picname = "";

	Emp()
	{
		frm = new JFrame();
		frm.setSize(1300, 630);

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
		lblVeterinaryManagementSystem.setBounds(320, 12, 635, 30);
		frm.add(lblVeterinaryManagementSystem);

		Canvas canvas = new Canvas();
		canvas.setBackground(Color.WHITE);
		canvas.setForeground(Color.RED);
		canvas.setBounds(320, 40, 635, 5);
		frm.add(canvas);

		lblEmployeeRegister = new JLabel("Employee Register");
		lblEmployeeRegister.setForeground(Color.WHITE);
		lblEmployeeRegister.setFont(new Font("Magneto", Font.BOLD, 23));
		lblEmployeeRegister.setBounds(525, 60, 300, 27);
		frm.add(lblEmployeeRegister);

		barlabel = new JLabel();
		Image userimg = new ImageIcon(this.getClass().getResource("/images/emp_bar.png")).getImage();
		barlabel.setIcon(new ImageIcon(userimg));
		barlabel.setBounds(0, 0, 1300, 105);
		frm.add(barlabel);

		lblId = new JLabel("ID");
		lblId.setBounds(20, 130, 100, 30);
		lblId.setFont(f);
		lblId.setForeground(Color.BLUE);
		frm.add(lblId);

		txtId = new JTextField();
		txtId.setBounds(150, 130, 220, 30);
		txtId.setFont(f);
		txtId.setBackground(Color.WHITE);
		txtId.setForeground(Color.BLACK);
		txtId.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(txtId);

		lblName = new JLabel("NAME");
		lblName.setBounds(420, 130, 100, 30);
		lblName.setFont(f);
		lblName.setForeground(Color.BLUE);
		frm.add(lblName);

		txtName = new JTextField();
		txtName.setBounds(550, 130, 220, 30);
		txtName.setFont(f);
		txtName.setBackground(Color.WHITE);
		txtName.setForeground(Color.BLACK);
		txtName.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(txtName);

		lblDOB = new JLabel("DATE OF BIRTH");
		lblDOB.setBounds(20, 170, 100, 30);
		lblDOB.setFont(f);
		lblDOB.setForeground(Color.BLUE);
		frm.add(lblDOB);

		dateChooser = new JDateChooser();
		dateChooser.setBounds(150, 170, 220, 30);
		dateChooser.setFont(f);
		dateChooser.setBackground(Color.WHITE);
		dateChooser.setForeground(Color.BLACK);
		dateChooser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(dateChooser);

		lbluser = new JLabel("USER ID");
		lbluser.setBounds(420, 170, 80, 30);
		lbluser.setFont(f);
		lbluser.setForeground(Color.BLUE);
		frm.add(lbluser);

		txtUser_Id = new JTextField();
		txtUser_Id.setBounds(550, 170, 220, 30);
		txtUser_Id.setFont(f);
		txtUser_Id.setBackground(Color.WHITE);
		txtUser_Id.setForeground(Color.BLACK);
		txtUser_Id.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(txtUser_Id);

		lblGender = new JLabel("GENDER");
		lblGender.setBounds(20, 210, 100, 30);
		lblGender.setFont(f);
		lblGender.setForeground(Color.BLUE);
		frm.add(lblGender);

		cmbGender = new JComboBox<Object>(arr);
		cmbGender.setBounds(150, 210, 220, 30);
		cmbGender.setFont(f);
		cmbGender.setSelectedIndex(-1);
		cmbGender.setBackground(Color.WHITE);
		cmbGender.setForeground(Color.BLACK);
		cmbGender.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(cmbGender);

		lblsQueston = new JLabel("SECURITY QUESTION");
		lblsQueston.setBounds(420, 210, 150, 30);
		lblsQueston.setFont(f);
		lblsQueston.setForeground(Color.BLUE);
		frm.add(lblsQueston);

		cmbSecQueston = new JComboBox<Object>(arr1);
		cmbSecQueston.setBounds(550, 210, 220, 30);
		cmbSecQueston.setFont(f);
		cmbSecQueston.setSelectedIndex(-1);
		cmbSecQueston.setBackground(Color.WHITE);
		cmbSecQueston.setForeground(Color.BLACK);
		cmbSecQueston.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(cmbSecQueston);

		lblQualification = new JLabel("QUALIFICATION");
		lblQualification.setBounds(20, 250, 100, 30);
		lblQualification.setFont(f);
		lblQualification.setForeground(Color.BLUE);
		frm.add(lblQualification);

		txtQualification = new JTextField();
		txtQualification.setBounds(150, 250, 220, 30);
		txtQualification.setFont(f);
		txtQualification.setBackground(Color.WHITE);
		txtQualification.setForeground(Color.BLACK);
		txtQualification.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(txtQualification);

		lblsAnswer = new JLabel("ANSWER");
		lblsAnswer.setBounds(420, 250, 150, 30);
		lblsAnswer.setFont(f);
		lblsAnswer.setForeground(Color.BLUE);
		frm.add(lblsAnswer);

		txtsAnswer = new JTextField();
		txtsAnswer.setBounds(550, 250, 220, 30);
		txtsAnswer.setFont(f);
		txtsAnswer.setBackground(Color.WHITE);
		txtsAnswer.setForeground(Color.BLACK);
		txtsAnswer.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(txtsAnswer);

		lblPost = new JLabel("POST");
		lblPost.setBounds(20, 290, 100, 30);
		lblPost.setFont(f);
		lblPost.setForeground(Color.BLUE);
		frm.add(lblPost);

		txtPost = new JTextField();
		txtPost.setBounds(150, 290, 220, 30);
		txtPost.setFont(f);
		txtPost.setBackground(Color.WHITE);
		txtPost.setForeground(Color.BLACK);
		txtPost.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(txtPost);

		lblpass = new JLabel("PASSWORD");
		lblpass.setBounds(420, 290, 80, 30);
		lblpass.setFont(f);
		lblpass.setForeground(Color.BLUE);
		frm.add(lblpass);

		txtPASS = new JPasswordField();
		txtPASS.setBounds(550, 290, 220, 30);
		txtPASS.setFont(f);
		txtPASS.setBackground(Color.WHITE);
		txtPASS.setForeground(Color.BLACK);
		txtPASS.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(txtPASS);

		lblMob_No = new JLabel("MOBILE NO.");
		lblMob_No.setBounds(20, 330, 100, 30);
		lblMob_No.setFont(f);
		lblMob_No.setForeground(Color.BLUE);
		frm.add(lblMob_No);

		txtMob_No = new JTextField();
		txtMob_No.setBounds(150, 330, 220, 30);
		txtMob_No.setFont(f);
		txtMob_No.setBackground(Color.WHITE);
		txtMob_No.setForeground(Color.BLACK);
		txtMob_No.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(txtMob_No);

		lblconfpass = new JLabel("CONFIRM PASSWORD");
		lblconfpass.setBounds(420, 330, 150, 30);
		lblconfpass.setFont(f);
		lblconfpass.setForeground(Color.BLUE);
		frm.add(lblconfpass);

		txt_ConfPASS = new JPasswordField();
		txt_ConfPASS.setBounds(550, 330, 220, 30);
		txt_ConfPASS.setFont(f);
		txt_ConfPASS.setBackground(Color.WHITE);
		txt_ConfPASS.setForeground(Color.BLACK);
		txt_ConfPASS.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(txt_ConfPASS);

		lblimg = new JLabel("IMAGE");
		lblimg.setBounds(20, 370, 100, 30);
		lblimg.setFont(f);
		lblimg.setForeground(Color.BLUE);
		frm.add(lblimg);

		txtimg = new JTextField();
		txtimg.setBounds(150, 370, 450, 30);
		txtimg.setFont(f);
		txtimg.setBackground(Color.WHITE);
		txtimg.setForeground(Color.BLACK);
		frm.add(txtimg);

		btnUploade = new JButton("UPLOAD");
		btnUploade.setBounds(610, 370, 160, 30);
		btnUploade.setFont(f);
		btnUploade.setForeground(Color.BLUE);
		btnUploade.setBackground(hexa1);
		btnUploade.setToolTipText("Temporirly,The button doesnot work");
		btnUploade.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(btnUploade);
		btnUploade.setEnabled(false);

		lblEmailId = new JLabel("E-Mail ID");
		lblEmailId.setBounds(20, 410, 100, 30);
		lblEmailId.setFont(f);
		lblEmailId.setForeground(Color.BLUE);
		frm.add(lblEmailId);

		txtEmailId = new JTextField();
		txtEmailId.setBounds(150, 410, 620, 30);
		txtEmailId.setFont(f);
		txtEmailId.setBackground(Color.WHITE);
		txtEmailId.setForeground(Color.BLACK);
		txtEmailId.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(txtEmailId);

		lblFull_Address = new JLabel("FULL ADDRESS");
		lblFull_Address.setBounds(20, 450, 100, 30);
		lblFull_Address.setFont(f);
		lblFull_Address.setForeground(Color.BLUE);
		frm.add(lblFull_Address);

		jsAddress = new JScrollPane();
		txtFull_Address = new JTextArea();
		jsAddress.setBounds(150, 450, 620, 60);
		txtFull_Address.setFont(f);
		txtFull_Address.setBackground(Color.WHITE);
		txtFull_Address.setForeground(Color.BLACK);
		txtFull_Address.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jsAddress.setViewportView(txtFull_Address);
		frm.add(jsAddress);

		btnNEW = new JButton("NEW");
		btnNEW.setBounds(20, 530, 235, 35);
		btnNEW.setFont(f);
		btnNEW.setForeground(Color.BLUE);
		btnNEW.setBackground(hexa1);
		btnNEW.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNEW.addActionListener(this);
		frm.add(btnNEW);

		btnEDIT = new JButton("EDIT");
		btnEDIT.setBounds(275, 530, 235, 35);
		btnEDIT.setFont(f);
		btnEDIT.setForeground(Color.BLUE);
		btnEDIT.setBackground(hexa1);
		btnEDIT.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEDIT.addActionListener(this);
		frm.add(btnEDIT);

		btnCLEAR = new JButton("CLEAR");
		btnCLEAR.setBounds(535, 530, 235, 35);
		btnCLEAR.setFont(f);
		btnCLEAR.setForeground(Color.BLUE);
		btnCLEAR.setBackground(hexa1);
		btnCLEAR.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCLEAR.addActionListener(this);
		frm.add(btnCLEAR);

		lblSearch = new JLabel("SEARCH");
		lblSearch.setBounds(860, 130, 100, 30);
		lblSearch.setForeground(Color.BLUE);
		lblSearch.setFont(f);
		lblSearch.setBackground(Color.BLUE);
		frm.add(lblSearch);

		jtable = new DefaultTableModel();
		tabview = new JTable(jtable);
		js = new JScrollPane();
		js.setBounds(790, 170, 480, 400);
		tabview.setFont(f);
		tabview.setForeground(Color.blue);
		tabview.setBackground(Color.WHITE);
		Object[] columns = { "ID", "NAME", "Date Of Birth", "GENDER", "QUALIFICATION", "SPECIALIZATION", "MOBILE NO", "EMAIL ID", "ADDRESS" };
		jtable.setColumnIdentifiers(columns);
		tabview.setModel(jtable);
		tabview.setRowHeight(30);
		tabview.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		ShowTable();
		tabview.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				int i = tabview.getSelectedRow();
				TableModel model = tabview.getModel();
				txtId.setText(model.getValueAt(i, 0).toString());
				txtName.setText(model.getValueAt(i, 1).toString());
				try 
				{
					Date date = new SimpleDateFormat("yyyy-MM-dd").parse(model.getValueAt(i, 2).toString());
					dateChooser.setDate(date);
				} 
				catch (ParseException e1)
				{
					e1.printStackTrace();
				}
				cmbGender.setSelectedItem(model.getValueAt(i, 3));
				txtQualification.setText(model.getValueAt(i, 4).toString());
				txtPost.setText(model.getValueAt(i, 5).toString());
				txtMob_No.setText(model.getValueAt(i, 6).toString());
				txtEmailId.setText(model.getValueAt(i, 7).toString());
				txtFull_Address.setText(model.getValueAt(i, 8).toString());
				txtName.grabFocus();
			}
		});
		txtSearch = new JTextField();
		txtSearch.setBounds(930, 130, 230, 30);
		txtSearch.setFont(f);
		txtSearch.setBackground(Color.WHITE);
		txtSearch.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		txtSearch.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyReleased(KeyEvent e)
			{
				DefaultTableModel table = (DefaultTableModel) tabview.getModel();
				String search = txtSearch.getText();
				TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(table);
				tabview.setRowSorter(tr);
				tr.setRowFilter(RowFilter.regexFilter(search));
			}

		});
		frm.add(txtSearch);
		js.setViewportView(tabview);
		frm.add(js);

		disabled();

		frm.setVisible(true);
	}

	public void ShowTable()
	{

		String sqlqry = "SELECT ID,NAME,DOB,GENDER,QUALIFICATION,POST,MOBILE_NO,EMAILID,ADDRESS FROM EMPLOYEE_DETAILS";
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

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) 
	{
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
				String ddate = dateFormat.format(dateChooser.getDate());
				if (frmvalidate()) 
				{
					RegisterOperation ro = new RegisterOperation(txtId.getText(), txtName.getText().toUpperCase(),ddate, cmbGender.getSelectedItem().toString().toUpperCase(), txtQualification.getText().toUpperCase(), txtPost.getText().toUpperCase(), txtMob_No.getText(), txtEmailId.getText().toLowerCase(), txtFull_Address.getText().toUpperCase(), txtUser_Id.getText(), cmbSecQueston.getSelectedItem().toString(), txtsAnswer.getText(), txtPASS.getText());
					if (ro.emp_userValidate()) 
					{
						if (strongPassword(txtPASS.getText()))
						{
							if (ro.emp_saverecord() == 1) 
							{
								JOptionPane.showMessageDialog(null, "USER SUCCESFULL REGISTER");
								ShowTable();
								clear();
								disabled();
								btnNEW.setText("NEW");
							}
							else 
							{
								JOptionPane.showMessageDialog(null, "USER NOT REGISTER");
							}
						} 
						else 
						{
							JOptionPane.showMessageDialog(null,"Week Password!Password Must Contain Capital Letter,Special Symbol & Digit[N/0/@]");
							txtPASS.setText("");
							txtPASS.grabFocus();
						}
					}
					else 
					{
						JOptionPane.showMessageDialog(null, "Record Already Inserted");
					}
				}
			}
		} 
		else if (e.getSource().equals(btnEDIT)) 
		{
			if (e.getActionCommand().equalsIgnoreCase("EDIT")) 
			{
				btnEDIT.disable();
				editenabled();
				btnEDIT.setText("UPDATE");
			} 
			else if (e.getActionCommand().equalsIgnoreCase("UPDATE"))
			{
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy");
				String ddate = dateFormat.format(dateChooser.getDate());
				if (frmvalidateforupdate()) 
				{
					RegisterOperation ro = new RegisterOperation(txtId.getText().toUpperCase(), txtName.getText().toUpperCase(), ddate, cmbGender.getSelectedItem().toString().toUpperCase(), txtQualification.getText().toUpperCase(), txtPost.getText().toUpperCase(), txtMob_No.getText(), txtEmailId.getText().toLowerCase(), txtFull_Address.getText().toUpperCase());
					if (ro.emp_updatebyadmin() == 1)
					{
						JOptionPane.showMessageDialog(null, "SUCESSFULLY UPDATED");
						ShowTable();
						btnEDIT.setText("EDIT");
						btnEDIT.setEnabled(true);
						disabled();
						clear();
					}
					else 
					{
						JOptionPane.showMessageDialog(null, "NOT UPDATED");
					}
				}
			}
		} 
		else if (e.getSource().equals(btnCLEAR))
		{
			clear();
		}
	}

	public void getid()
	{
		RegisterOperation ro = new RegisterOperation();
		txtId.setText(ro.emp_getid());
	}

	public void clear() 
	{
		txtId.setText("");
		txtName.setText("");
		dateChooser.setDate(null);
		txtUser_Id.setText("");
		cmbGender.setSelectedIndex(-1);
		cmbSecQueston.setSelectedIndex(-1);
		txtsAnswer.setText("");
		txtQualification.setText("");
		txtPost.setText("");
		txtMob_No.setText("");
		txtEmailId.setText("");
		txtPASS.setText("");
		txt_ConfPASS.setText("");
		txtFull_Address.setText("");
	}

	public void editenabled() 
	{
		txtName.setEditable(true);
		dateChooser.setEnabled(true);
		cmbGender.setEditable(true);
		cmbGender.setEnabled(true);
		txtQualification.setEditable(true);
		txtPost.setEditable(true);
		txtMob_No.setEditable(true);
		txtEmailId.setEditable(true);
		txtFull_Address.setEditable(true);
	}

	public void enabled() 
	{
		txtName.setEditable(true);
		dateChooser.setEnabled(true);
		txtUser_Id.setEditable(true);
		txtMob_No.setEditable(true);
		txtEmailId.setEditable(true);
		cmbGender.setEnabled(true);
		cmbGender.setEditable(true);
		cmbSecQueston.setEnabled(true);
		cmbSecQueston.setEditable(true);
		txtQualification.setEditable(true);
		txtPost.setEditable(true);
		txtsAnswer.setEditable(true);
		txtPASS.setEditable(true);
		txt_ConfPASS.setEditable(true);
		txtFull_Address.setEditable(true);
	}

	public void disabled()
	{
		txtId.setEditable(false);
		txtName.setEditable(false);
		dateChooser.setEnabled(false);
		txtUser_Id.setEditable(false);
		txtMob_No.setEditable(false);
		txtimg.setEditable(false);
		txtEmailId.setEditable(false);
		cmbGender.setEnabled(false);
		cmbSecQueston.setEnabled(false);
		txtQualification.setEditable(false);
		txtPost.setEditable(false);
		txtsAnswer.setEditable(false);
		txtPASS.setEditable(false);
		txt_ConfPASS.setEditable(false);
		txtFull_Address.setEditable(false);
	}

	@SuppressWarnings("deprecation")
	public boolean frmvalidate() 
	{
		boolean flag = true;
		if (ValidateForm.validateAnyString(txtName.getText()) == false) 
		{
			JOptionPane.showMessageDialog(null, "Enter Name ");
			txtName.grabFocus();
			flag = false;
		} 
		else if (cmbGender.getSelectedIndex() == -1)
		{
			JOptionPane.showMessageDialog(null, "Choose Your Gender");
			cmbGender.grabFocus();
			flag = false;
		} 
		else if (ValidateForm.validateMobileNo(txtMob_No.getText()) == false) 
		{
			JOptionPane.showMessageDialog(null, "Enter Correct phone no..");
			txtMob_No.grabFocus();
			flag = false;
		}
		else if (ValidateForm.validateEmail(txtEmailId.getText()) == false) 
		{
			JOptionPane.showMessageDialog(null, "Enter email id..");
			txtEmailId.grabFocus();
			flag = false;
		}
		else if (txtUser_Id.getText().equals(""))
		{
			JOptionPane.showMessageDialog(null, "Enter UserId");
			txtUser_Id.grabFocus();
			flag = false;
		} 
		else if (cmbSecQueston.getSelectedIndex() == -1) 
		{
			JOptionPane.showMessageDialog(null, "Choose Security Question");
			cmbSecQueston.grabFocus();
			flag = false;
		}
		else if (cmbSecQueston.getSelectedIndex() == -1) 
		{
			JOptionPane.showMessageDialog(null, "Choose Security Question");
			cmbSecQueston.grabFocus();
			flag = false;
		} 
		else if (txtFull_Address.getText().length() == 0)
		{
			JOptionPane.showMessageDialog(null, "Enter Address");
			cmbSecQueston.grabFocus();
			flag = false;
		} 
		else if (txtPASS.getText().length() == 0) 
		{
			JOptionPane.showMessageDialog(null, "ENTER PASSWORD");
			txtPASS.grabFocus();
			flag = false;
		} 
		else if (txt_ConfPASS.getText().length() == 0) 
		{
			JOptionPane.showMessageDialog(null, "Enter Confirm Password");
			txt_ConfPASS.grabFocus();
			flag = false;
		}
		else if (!txtPASS.getText().equals(txt_ConfPASS.getText())) 
		{
			JOptionPane.showMessageDialog(null, "PASSWORD AND CONFIRM PASSWORD DO NOT MATCH");
			txtPASS.grabFocus();
			flag = false;
		}
		return flag;
	}

	public boolean frmvalidateforupdate()
	{
		boolean flag = true;
		if (ValidateForm.validateAnyString(txtName.getText()) == false) 
		{
			JOptionPane.showMessageDialog(null, "Enter Name ");
			txtName.grabFocus();
			flag = false;
		}
		else if (cmbGender.getSelectedIndex() == -1) 
		{
			JOptionPane.showMessageDialog(null, "Choose Your Gender");
			cmbGender.grabFocus();
			flag = false;
		} 
		else if (ValidateForm.validateAnyString(txtQualification.getText()) == false)
		{
			JOptionPane.showMessageDialog(null, "Enter Qualification ");
			txtName.grabFocus();
			flag = false;
		}
		else if (ValidateForm.validateAnyString(txtPost.getText()) == false) 
		{
			JOptionPane.showMessageDialog(null, "Enter Specialization ");
			txtName.grabFocus();
			flag = false;
		}
		else if (ValidateForm.validateMobileNo(txtMob_No.getText()) == false)
		{
			JOptionPane.showMessageDialog(null, "Enter Correct phone no..");
			txtMob_No.grabFocus();
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
			cmbSecQueston.grabFocus();
			flag = false;
		}
		return flag;
	}

	public boolean strongPassword(String pass)
	{
		boolean sflag = false;
		int spacecount, digitcount, capcount;
		spacecount = digitcount = capcount = 0;
		char[] ch = pass.toCharArray();
		for (char c : ch)
		{
			if (c >= 65 && c <= 90) 
			{
				capcount++;
			}
			if (c > 48 && c <= 57)
			{
				digitcount++;
			}
			if (!(c >= 48 && c <= 57) && !(c >= 65 && c <= 90) && !(c >= 97 && c <= 122))
			{
				spacecount++;
			}
			if (spacecount > 0 && digitcount > 0 && capcount > 0)
			{
				sflag = true;
			}
		}
		return sflag;
	}
}

public class Employee
{
	Emp e = new Emp();
}
