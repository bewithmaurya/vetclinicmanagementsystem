import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;
import net.proteanit.sql.DbUtils;

class Drg extends ValidateForm implements ActionListener
{
	public JFrame frm;
	public PreparedStatement ps;
	public ResultSet rs;
	public Connection conn;
	public JLabel lbldid, lblVeterinaryManagementSystem, lblSearch, lbldrugname, lblDrug, lbldescription, barlabel;
	public JTextField txtdid, txtdrugname, txtSearch;
	public JButton btnNew, btnClear, btnEDIT;
	public JScrollPane JSDescription;
	public JTextArea txtdescription;
	public DefaultTableModel jtable;
	public JTable tabview;
	public TableRowSorter<TableModel> rowSorter;

	Font f = new Font("consolas", Font.BOLD, 14);

	Drg() 
	{
		frm = new JFrame("Drugs Details");
		frm.setSize(700, 350);
		frm.setLayout(null);
		frm.setResizable(false);
		frm.getContentPane().setBackground(Color.decode("#c1fff0"));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int framewidth = frm.getSize().width;
		int frameheigth = frm.getSize().height;
		int framelocationX = (dim.width - framewidth) / 2;
		int framelocationY = (dim.height - frameheigth) / 2;
		frm.setLocation(framelocationX, framelocationY);

		frm.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/icon.png")));
		
		lblVeterinaryManagementSystem = new JLabel("Veterinary Management System");
		lblVeterinaryManagementSystem.setFont(new Font("Algerian", Font.BOLD, 18));
		lblVeterinaryManagementSystem.setForeground(Color.WHITE);
		lblVeterinaryManagementSystem.setBounds(160, 12, 370, 20);
		frm.add(lblVeterinaryManagementSystem);

		Canvas canvas = new Canvas();
		canvas.setBackground(Color.WHITE);
		canvas.setForeground(Color.RED);
		canvas.setBounds(160, 33, 335, 2);
		frm.add(canvas);

		lblDrug = new JLabel("Drugs Details");
		lblDrug.setForeground(Color.WHITE);
		lblDrug.setFont(new Font("Magneto", Font.BOLD, 17));
		lblDrug.setBounds(280, 40, 204, 27);
		frm.add(lblDrug);

		barlabel = new JLabel();
		Image userimg = new ImageIcon(this.getClass().getResource("/images/emp_bar.png")).getImage();
		barlabel.setIcon(new ImageIcon(userimg));
		barlabel.setBounds(0, 0, 700, 70);
		frm.add(barlabel);

		lbldid = new JLabel("DRUGS ID");
		lbldid.setBounds(20, 80, 90, 30);
		frm.add(lbldid);
		lbldid.setFont(f);
		lbldid.setForeground(Color.blue);

		txtdid = new JTextField();
		txtdid.setBounds(140, 80, 200, 30);
		txtdid.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(txtdid);
		txtdid.setBackground(Color.WHITE);
		txtdid.setFont(f);

		lbldrugname = new JLabel("DRUGS NAME");
		lbldrugname.setBounds(20, 120, 110, 30);
		frm.add(lbldrugname);
		lbldrugname.setFont(f);
		lbldrugname.setForeground(Color.blue);

		txtdrugname = new JTextField();
		txtdrugname.setBounds(140, 120, 200, 30);
		txtdrugname.setBackground(Color.WHITE);
		txtdrugname.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(txtdrugname);
		txtdrugname.setFont(f);

		lbldescription = new JLabel("DESCRIPTION");
		lbldescription.setBounds(20, 160, 90, 30);
		frm.add(lbldescription);
		lbldescription.setFont(f);
		lbldescription.setForeground(Color.blue);

		JSDescription = new JScrollPane();
		txtdescription = new JTextArea();
		txtdescription.setBackground(Color.WHITE);
		txtdescription.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		JSDescription.setBounds(140, 160, 200, 90);
		JSDescription.setViewportView(txtdescription);
		frm.add(JSDescription);
		txtdescription.setFont(f);

		btnNew = new JButton("NEW");
		btnNew.setBounds(20, 260, 100, 30);
		btnNew.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNew.setBackground(Color.decode("#88d2f6"));
		btnNew.addActionListener(this);
		frm.add(btnNew);

		btnEDIT = new JButton("EDIT");
		btnEDIT.setBounds(130, 260, 100, 30);
		btnEDIT.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEDIT.setBackground(Color.decode("#88d2f6"));
		btnEDIT.addActionListener(this);
		frm.add(btnEDIT);

		btnClear = new JButton("CLEAR");
		btnClear.setBounds(240, 260, 100, 30);
		btnClear.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnClear.setBackground(Color.decode("#88d2f6"));
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
		js.setBounds(350, 120, 320, 170);
		tabview.setFont(f);
		tabview.setForeground(Color.blue);
		tabview.setBackground(Color.WHITE);
		Object[] columns = { "Id", "MEDICINE", "DRUGS", "TYPE", "COMPANY" };
		jtable.setColumnIdentifiers(columns);
		tabview.setModel(jtable);
		tabview.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		ShowTable();
		tabview.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				int i = tabview.getSelectedRow();
				TableModel model = tabview.getModel();
				txtdid.setText(model.getValueAt(i, 0).toString());
				txtdrugname.setText(model.getValueAt(i, 1).toString());
				txtdescription.setText(model.getValueAt(i, 2).toString());
				txtdrugname.grabFocus();
			}
		});

		txtSearch = new JTextField();
		txtSearch.setBounds(450, 80, 210, 30);
		txtSearch.setFont(f);
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
					MedicalOperations mo = new MedicalOperations(txtdid.getText().toUpperCase(), txtdrugname.getText().toUpperCase(), txtdescription.getText().toUpperCase());
					if (mo.Drug_userValidate())
					{
						if (mo.Drug_saveRecord() == 1) 
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
			if (txtdrugname.getText().equals("")) 
			{
				JOptionPane.showConfirmDialog(null, "First Select Value From Table");
			} 
			else 
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
						MedicalOperations mo = new MedicalOperations(txtdid.getText().toUpperCase(), txtdrugname.getText().toUpperCase(), txtdescription.getText().toUpperCase());
						if (mo.Drug_update() == 1)
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
		}
		else if (e.getSource().equals(btnClear)) 
		{
			clear();
		}

	}

	public boolean frmvalidate()
	{
		boolean flag = true;
		if (ValidateForm.validateAnyString(txtdrugname.getText()) == false) 
		{
			JOptionPane.showMessageDialog(null, "Enter Drugs Name ");
			txtdrugname.grabFocus();
			flag = false;
		}
		else if (ValidateForm.validateAnyString(txtdescription.getText()) == false)
		{
			JOptionPane.showMessageDialog(null, "Enter Description ");
			txtdescription.grabFocus();
			flag = false;
		}

		return flag;
	}

	public void getid() 
	{
		MedicalOperations mo = new MedicalOperations();
		txtdid.setText(mo.Drug_getId());
	}

	public void ShowTable() 
	{
		String sqlqry = "SELECT DRUGS_ID,DRUGS_NAME,DESCRIPTION FROM DRUGS_DETAILS";
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

	public void clear()
	{
		txtdid.setText("");
		txtdrugname.setText("");
		txtdescription.setText("");
	}

	public void disabled()
	{
		txtdid.setEditable(false);
		txtdrugname.setEditable(false);
		txtdescription.setEditable(false);
	}

	public void enabled()
	{
		txtdrugname.setEditable(true);
		txtdescription.setEditable(true);
	}
}

public class Drugs 
{
	Drg dr = new Drg();
}
