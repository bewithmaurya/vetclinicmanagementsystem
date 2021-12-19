import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;
import net.proteanit.sql.DbUtils;

class Vacc extends ValidateForm implements ActionListener 
{
	public JFrame frm;
	public PreparedStatement ps;
	public ResultSet rs;
	public Connection conn;
	public JLabel lblVeterinaryManagementSystem, lblvaccid, lblSearch, lblVaccineDetails, lblvaccname, barlabel;
	public JTextField txtvaccid, txtvaccname, txtSearch;
	public JButton btnNew, btnEDIT, btnCLEAR;
	public DefaultTableModel jtable;
	public JTable tabview;
	public TableRowSorter<TableModel> rowSorter;
	public String strdrugs, strtype, strdosage;
	public JScrollPane js;

	Font f = new Font("consolas", Font.BOLD, 14);

	Vacc()
	{
		frm = new JFrame();

		frm.setSize(720, 320);
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
		frm.setTitle("Vaccination Database");
		Color hexa = Color.decode("#BBF2FA");
		frm.getContentPane().setBackground(hexa);

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

		lblVaccineDetails = new JLabel("Vaccination Details");
		lblVaccineDetails.setForeground(Color.WHITE);
		lblVaccineDetails.setFont(new Font("Magneto", Font.BOLD, 17));
		lblVaccineDetails.setBounds(270, 40, 204, 27);
		frm.add(lblVaccineDetails);

		barlabel = new JLabel();
		Image userimg = new ImageIcon(this.getClass().getResource("/images/emp_bar.png")).getImage();
		barlabel.setIcon(new ImageIcon(userimg));
		barlabel.setBounds(0, 0, 830, 70);
		frm.add(barlabel);

		lblvaccid = new JLabel("VACCINATION ID");
		lblvaccid.setBounds(20, 120, 150, 30);
		frm.add(lblvaccid);
		lblvaccid.setFont(f);
		lblvaccid.setForeground(Color.blue);

		txtvaccid = new JTextField();
		txtvaccid.setBounds(160, 120, 200, 30);
		txtvaccid.setBackground(Color.WHITE);
		txtvaccid.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(txtvaccid);
		txtvaccid.setFont(f);

		lblvaccname = new JLabel("VACCINATION NAME");
		lblvaccname.setBounds(20, 175, 150, 30);
		frm.add(lblvaccname);
		lblvaccname.setFont(f);
		lblvaccname.setForeground(Color.blue);

		txtvaccname = new JTextField();
		txtvaccname.setBounds(160, 175, 200, 30);
		txtvaccname.setBackground(Color.WHITE);
		txtvaccname.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(txtvaccname);
		txtvaccname.setFont(f);

		btnNew = new JButton("NEW");
		btnNew.setBounds(20, 235, 100, 30);
		btnNew.setBackground(Color.decode("#88d2f6"));
		btnNew.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNew.addActionListener(this);
		frm.add(btnNew);

		btnEDIT = new JButton("EDIT");
		btnEDIT.setBounds(130, 235, 100, 30);
		btnEDIT.setBackground(Color.decode("#88d2f6"));
		btnEDIT.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEDIT.addActionListener(this);
		frm.add(btnEDIT);

		btnCLEAR = new JButton("CLEAR");
		btnCLEAR.setBounds(240, 235, 100, 30);
		btnCLEAR.setBackground(Color.decode("#88d2f6"));
		btnCLEAR.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCLEAR.addActionListener(this);
		frm.add(btnCLEAR);

		lblSearch = new JLabel("SEARCH");
		lblSearch.setBounds(370, 80, 125, 30);
		lblSearch.setForeground(Color.BLUE);
		lblSearch.setFont(f);
		lblSearch.setBackground(Color.BLUE);
		frm.add(lblSearch);

		jtable = new DefaultTableModel();
		tabview = new JTable(jtable);
		js = new JScrollPane(tabview);
		js.setBounds(370, 120, 320, 150);
		tabview.setFont(f);
		tabview.setForeground(Color.blue);
		tabview.setBackground(Color.WHITE);
		Object[] columns = { "Id", "VACCINATION" };
		jtable.setColumnIdentifiers(columns);
		tabview.setModel(jtable);
		tabview.setRowHeight(30);
		ShowTable();
		tabview.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tabview.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				int i = tabview.getSelectedRow();
				TableModel model = tabview.getModel();
				txtvaccid.setText(model.getValueAt(i, 0).toString());
				txtvaccname.setText(model.getValueAt(i, 1).toString());
				txtvaccid.grabFocus();
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
		String sqlqry = "SELECT ID,VACCINATION FROM VACCINATION_DETAILS";
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
					MedicalOperations mo = new MedicalOperations(txtvaccid.getText().toUpperCase(),txtvaccname.getText().toUpperCase());
					if (mo.Vacc_userValidate())
					{
						if (mo.Vacc_saveRecord() == 1) 
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
					MedicalOperations mo = new MedicalOperations(txtvaccid.getText().toUpperCase(),txtvaccname.getText().toUpperCase());
					if (mo.Vacc_update() == 1)
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

	public void clear()
	{
		txtvaccid.setText("");
		txtvaccname.setText("");
	}

	public void disabled() 
	{
		txtvaccid.setEditable(false);
		txtvaccname.setEditable(false);
	}

	public void enabled() 
	{
		txtvaccid.setEditable(true);
		txtvaccname.setEditable(true);
	}

	public boolean frmvalidate()
	{
		boolean flag = true;
		if (txtvaccname.getText().length()==0) 
		{
			JOptionPane.showMessageDialog(null, "Enter Vaccination Name ");
			txtvaccname.grabFocus();
			flag = false;
		}

		return flag;
	}

	public void getid() 
	{
		MedicalOperations mo = new MedicalOperations();
		txtvaccid.setText(mo.Vacc_getId());
	}
}

public class Vaccination 
{
	Vacc vc = new Vacc();
}
