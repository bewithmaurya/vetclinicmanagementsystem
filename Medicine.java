import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;
import net.proteanit.sql.DbUtils;

class Med extends ValidateForm implements ActionListener, WindowListener 
{
	public JFrame frm;
	public PreparedStatement ps;
	public ResultSet rs;
	public Connection conn;
	public JLabel lblmid, lblVeterinaryManagementSystem, lblmedname, lblSearch, lblMedicinePortal, lbldrugs, barlabel,lbltype, lblcomp;
	public JTextField txtmid, txtmedname, txtdosremarks, txtduration, txtcompny, txtSearch;
	public JComboBox<String> cmbdrug, cmbtype, cmbmorning, cmbnoon, cmbeve;
	public JButton btnNew, btndrugs, btnEDIT, btnClear;
	public DefaultTableModel jtable;
	public JTable tabview;
	public String drugstype[] = { "TABLETS", "CAPSULE", "SYRUP", "POWDER", "INJECTION" };
	public TableRowSorter<TableModel> rowSorter;
	public String strdrugs, strtype, strdosage;

	Font f = new Font("consolas", Font.BOLD, 14);

	Med() 
	{
		frm = new JFrame();

		frm.setSize(720, 370);
		frm.setResizable(false);
		frm.setLayout(null);
		frm.setResizable(false);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int framewidth = frm.getSize().width;
		int frameheigth = frm.getSize().height;
		int framelocationX = (dim.width - framewidth) / 2;
		int framelocationY = (dim.height - frameheigth) / 2;
		frm.setLocation(framelocationX, framelocationY);
		frm.getContentPane().setBackground(Color.WHITE);
		frm.setTitle("Medicine Database");
		Color hexa = Color.decode("#BBF2FA");
		frm.getContentPane().setBackground(hexa);
		frm.addWindowListener(this);

		frm.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/icon.png")));
		
		lblVeterinaryManagementSystem = new JLabel("Veterinary Management System");
		lblVeterinaryManagementSystem.setFont(new Font("Algerian", Font.BOLD, 18));
		lblVeterinaryManagementSystem.setForeground(Color.WHITE);
		lblVeterinaryManagementSystem.setBounds(180, 12, 370, 20);
		frm.add(lblVeterinaryManagementSystem);

		Canvas canvas = new Canvas();
		canvas.setBackground(Color.WHITE);
		canvas.setForeground(Color.RED);
		canvas.setBounds(180, 33, 335, 2);
		frm.add(canvas);

		lblMedicinePortal = new JLabel("Medicine Details");
		lblMedicinePortal.setForeground(Color.WHITE);
		lblMedicinePortal.setFont(new Font("Magneto", Font.BOLD, 17));
		lblMedicinePortal.setBounds(280, 40, 204, 27);
		frm.add(lblMedicinePortal);

		barlabel = new JLabel();
		Image userimg = new ImageIcon(this.getClass().getResource("/images/emp_bar.png")).getImage();
		barlabel.setIcon(new ImageIcon(userimg));
		barlabel.setBounds(0, 0, 830, 70);
		frm.add(barlabel);

		lblmid = new JLabel("MEDICINE ID");
		lblmid.setBounds(20, 80, 90, 30);
		frm.add(lblmid);
		lblmid.setFont(f);
		lblmid.setForeground(Color.blue);

		txtmid = new JTextField();
		txtmid.setBounds(140, 80, 200, 30);
		frm.add(txtmid);
		txtmid.setEditable(false);
		txtmid.setBackground(Color.WHITE);
		txtmid.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		txtmid.setFont(f);

		lblmedname = new JLabel("MEDICINE NAME");
		lblmedname.setBounds(20, 120, 110, 30);
		frm.add(lblmedname);
		lblmedname.setFont(f);
		lblmedname.setForeground(Color.blue);

		txtmedname = new JTextField();
		txtmedname.setBounds(140, 120, 200, 30);
		txtmedname.setBackground(Color.WHITE);
		txtmedname.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(txtmedname);
		txtmedname.setFont(f);

		lbldrugs = new JLabel("DRUGS");
		lbldrugs.setBounds(20, 160, 90, 30);
		frm.add(lbldrugs);
		lbldrugs.setFont(f);
		lbldrugs.setForeground(Color.blue);

		cmbdrug = new JComboBox<String>();
		cmbdrug.setBounds(140, 160, 80, 30);
		cmbdrug.setBackground(Color.WHITE);
		cmbdrug.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(cmbdrug);
		cmbdrug.setFont(f);
		cmbdrug.setEditable(true);

		btndrugs = new JButton("ADD DRUGS");
		btndrugs.setBounds(230, 160, 110, 30);
		btndrugs.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(btndrugs);
		btndrugs.addActionListener(this);
		btndrugs.setBackground(Color.decode("#88d2f6"));

		lbltype = new JLabel("TYPE");
		lbltype.setBounds(20, 200, 80, 30);
		frm.add(lbltype);
		lbltype.setFont(f);
		lbltype.setForeground(Color.blue);

		cmbtype = new JComboBox<String>(drugstype);
		cmbtype.setBounds(140, 200, 200, 30);
		cmbtype.setBackground(Color.WHITE);
		cmbtype.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cmbtype.setSelectedIndex(-1);
		frm.add(cmbtype);
		cmbtype.setFont(f);
		cmbtype.setEditable(true);

		lblcomp = new JLabel("COMPANY NAME");
		lblcomp.setBounds(20, 240, 100, 30);
		frm.add(lblcomp);
		lblcomp.setFont(f);
		lblcomp.setForeground(Color.blue);

		txtcompny = new JTextField();
		txtcompny.setBounds(140, 240, 200, 30);
		txtcompny.setBackground(Color.WHITE);
		txtcompny.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(txtcompny);
		txtcompny.setFont(f);

		btnNew = new JButton("NEW");
		btnNew.setBounds(20, 290, 100, 30);
		btnNew.setBackground(Color.decode("#88d2f6"));
		btnNew.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNew.addActionListener(this);
		frm.add(btnNew);

		btnEDIT = new JButton("EDIT");
		btnEDIT.setBounds(130, 290, 100, 30);
		btnEDIT.setBackground(Color.decode("#88d2f6"));
		btnEDIT.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEDIT.addActionListener(this);
		frm.add(btnEDIT);

		btnClear = new JButton("CLEAR");
		btnClear.setBounds(240, 290, 100, 30);
		btnClear.setBackground(Color.decode("#88d2f6"));
		btnClear.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnClear.addActionListener(this);
		frm.add(btnClear);

		lblSearch = new JLabel("SEARCH");
		lblSearch.setBounds(370, 80, 125, 30);
		lblSearch.setForeground(Color.BLUE);
		lblSearch.setFont(f);
		lblSearch.setBackground(Color.BLUE);
		frm.add(lblSearch);

		jtable = new DefaultTableModel();
		tabview = new JTable(jtable);
		JScrollPane js = new JScrollPane(tabview);
		js.setBounds(350, 120, 340, 200);
		tabview.setFont(f);
		tabview.setForeground(Color.blue);
		tabview.setBackground(Color.WHITE);
		Object[] columns = { "Id", "MEDICINE", "DRUGS", "TYPE", "COMPANY" };
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
				txtmid.setText(model.getValueAt(i, 0).toString());
				txtmedname.setText(model.getValueAt(i, 1).toString());
				cmbdrug.setSelectedItem(model.getValueAt(i, 2));
				cmbtype.setSelectedItem(model.getValueAt(i, 3));
				txtcompny.setText(model.getValueAt(i, 4).toString());
				txtmedname.grabFocus();
			}
		});
		txtSearch = new JTextField();
		txtSearch.setBounds(450, 80, 230, 30);
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
		frm.add(js);

		disabled();

		frm.setVisible(true);
	}

	public void ShowTable() 
	{
		String sqlqry = "SELECT ID,NAME,DRUGS,TYPE,COMPANY FROM MEDICINE_DETAILS ORDER BY ID ASC";
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
		if (e.getSource().equals(btnNew))
		{
			if (e.getActionCommand().equalsIgnoreCase("NEW")) 
			{
				btnNew.disable();
				enabled();
				getid();
				btnNew.setText("SAVE");
			}
			else if (e.getActionCommand().equalsIgnoreCase("SAVE"))
			{
				if (frmvalidate()) 
				{
					MedicalOperations mo = new MedicalOperations(txtmid.getText().toUpperCase(),txtmedname.getText().toUpperCase(), cmbdrug.getSelectedItem().toString().toUpperCase(),cmbtype.getSelectedItem().toString().toUpperCase(), txtcompny.getText().toUpperCase());
					if (mo.Med_userValidate())
					{
						if (mo.Med_saveRecord() == 1)
						{
							JOptionPane.showMessageDialog(null, "ADD SUCCESSFULLY");
							ShowTable();
							clear();
							disabled();
							btnNew.setText("NEW");
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
				enabled();
				btnEDIT.setText("UPDATE");
			} 
			else if (e.getActionCommand().equalsIgnoreCase("UPDATE")) 
			{
				if (frmvalidate())
				{
					MedicalOperations mo = new MedicalOperations(txtmid.getText().toUpperCase(),txtmedname.getText().toUpperCase(), cmbdrug.getSelectedItem().toString().toUpperCase(),cmbtype.getSelectedItem().toString().toUpperCase(), txtcompny.getText().toUpperCase());
					if (mo.Med_update() == 1) 
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
		else if (e.getSource().equals(btnClear))
		{
			clear();
		}
		else if (e.getSource().equals(btndrugs))
		{
			new Drugs();
		}

	}

	public void clear() 
	{
		txtmid.setText("");
		txtmedname.setText("");
		cmbdrug.setSelectedIndex(-1);
		cmbtype.setSelectedIndex(-1);
		txtcompny.setText("");
	}

	public void disabled() 
	{
		txtmid.setEditable(false);
		txtmedname.setEditable(false);
		cmbdrug.setEnabled(false);
		cmbtype.setEnabled(false);
		txtcompny.setEditable(false);
	}

	public void enabled() 
	{
		txtmedname.setEditable(true);
		cmbdrug.setEnabled(true);
		cmbtype.setEnabled(true);
		txtcompny.setEditable(true);
	}

	public boolean frmvalidate() 
	{
		boolean flag = true;
		if (ValidateForm.validateAnyString(txtmedname.getText()) == false)
		{
			JOptionPane.showMessageDialog(null, "Enter Medicine Name ");
			txtmedname.grabFocus();
			flag = false;
		} 
		else if (cmbdrug.getSelectedIndex() == -1) 
		{
			JOptionPane.showMessageDialog(null, "Choose Your Drugs");
			cmbdrug.grabFocus();
			flag = false;
		} 
		else if (cmbtype.getSelectedIndex() == -1)
		{
			JOptionPane.showMessageDialog(null, "Choose Type");
			cmbtype.grabFocus();
			flag = false;
		} 
		else if (ValidateForm.validateAnyString(txtcompny.getText()) == false) 
		{
			JOptionPane.showMessageDialog(null, "Enter Company Name ");
			txtcompny.grabFocus();
			flag = false;
		}

		return flag;
	}

	public void srccour() 
	{
		String sqlqry = "select DRUGS_NAME from DRUGS_DETAILS";
		conn = Connections.getConnections();
		try 
		{
			ps = conn.prepareStatement(sqlqry);
			rs = ps.executeQuery();
			while (rs.next()) 
			{
				cmbdrug.addItem(rs.getString(1));
				cmbdrug.setSelectedIndex(-1);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	public void getid()
	{
		MedicalOperations mo = new MedicalOperations();
		txtmid.setText(mo.Med_getid());
	}

	@Override
	public void windowActivated(WindowEvent e) 
	{
		srccour();
	}

	@Override
	public void windowClosed(WindowEvent e) 
	{

	}

	@Override
	public void windowClosing(WindowEvent e) 
	{

	}

	@Override
	public void windowDeactivated(WindowEvent e) 
	{
		cmbdrug.removeAllItems();
	}

	@Override
	public void windowDeiconified(WindowEvent e)
	{

	}

	@Override
	public void windowIconified(WindowEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent e) 
	{

	}
}

public class Medicine 
{
	Med am = new Med();
}
