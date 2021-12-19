import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;
import net.proteanit.sql.DbUtils;

class Serv extends Base implements ActionListener {
	public PreparedStatement ps;
	public ResultSet rs;
	public Connection conn;
	public DefaultTableModel jtable = new DefaultTableModel();
	public JFrame frm;
	public JLabel lblServiceName, lblFee, lblServId, barlabel, lblSearch, lblVeterinaryManagementSystem,lblServicePortal;
	public JTextField txtServiceName, txtFee, txtSearch, txtServId;
	public JTable tabview;
	public JButton btnNew, btnEDIT, btnClear;
	public TableRowSorter<TableModel> rowSorter;

	Serv() 
	{
		frm = new JFrame();

		frm.setSize(720, 410);
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

		frm.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/icon.png")));
		
		frm.setTitle("Service Database");
		Color hexa = Color.decode("#BBF2FA");
		frm.getContentPane().setBackground(hexa);
		Color hexa1 = Color.decode("#82efff");

		Font f = new Font("Consolas", Font.BOLD, 13);

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

		lblServicePortal = new JLabel("Service Details");
		lblServicePortal.setForeground(Color.WHITE);
		lblServicePortal.setFont(new Font("Magneto", Font.BOLD, 17));
		lblServicePortal.setBounds(280, 40, 204, 27);
		frm.add(lblServicePortal);

		barlabel = new JLabel();
		Image userimg = new ImageIcon(this.getClass().getResource("/images/emp_bar.png")).getImage();
		barlabel.setIcon(new ImageIcon(userimg));
		barlabel.setBounds(0, 0, 830, 70);
		frm.add(barlabel);

		lblServId = new JLabel("SERVICE ID");
		lblServId.setBounds(20, 150, 100, 30);
		lblServId.setFont(f);
		lblServId.setForeground(Color.BLUE);
		frm.add(lblServId);

		txtServId = new JTextField();
		txtServId.setBounds(120, 150, 200, 30);
		txtServId.setFont(f);
		txtServId.setBackground(Color.WHITE);
		txtServId.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(txtServId);

		lblServiceName = new JLabel("SERVICE NAME");
		lblServiceName.setBounds(20, 200, 100, 30);
		lblServiceName.setFont(f);
		lblServiceName.setForeground(Color.BLUE);
		frm.add(lblServiceName);

		txtServiceName = new JTextField();
		txtServiceName.setBounds(120, 200, 200, 30);
		txtServiceName.setFont(f);
		txtServiceName.setBackground(Color.WHITE);
		txtServiceName.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(txtServiceName);

		lblFee = new JLabel("FEE");
		lblFee.setBounds(20, 250, 100, 30);
		lblFee.setFont(f);
		lblFee.setForeground(Color.BLUE);
		frm.add(lblFee);

		txtFee = new JTextField();
		txtFee.setBounds(120, 250, 200, 30);
		txtFee.setFont(f);
		txtFee.setBackground(Color.WHITE);
		txtFee.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(txtFee);

		lblSearch = new JLabel("SEARCH");
		lblSearch.setBounds(370, 100, 125, 30);
		lblSearch.setForeground(Color.BLUE);
		lblSearch.setFont(f);
		lblSearch.setBackground(Color.BLUE);
		frm.add(lblSearch);

		tabview = new JTable(jtable);
		JScrollPane js = new JScrollPane(tabview);
		js.setBounds(350, 150, 340, 200);
		tabview.setFont(f);
		tabview.setForeground(Color.blue);
		tabview.setBackground(Color.WHITE);
		Object[] columns = { "Id", "SERVICE", "FEE" };
		jtable.setColumnIdentifiers(columns);
		tabview.setModel(jtable);
		tabview.setBackground(Color.WHITE);
		tabview.setForeground(Color.BLACK);
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
				txtServId.setText(model.getValueAt(i, 0).toString());
				txtServiceName.setText(model.getValueAt(i, 1).toString());
				txtFee.setText(model.getValueAt(i, 2).toString());
				txtServiceName.grabFocus();
			}
		});
		txtSearch = new JTextField();
		txtSearch.setBounds(450, 100, 230, 30);
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

		btnNew = new JButton("NEW");
		btnNew.setBounds(20, 320, 100, 30);
		btnNew.setForeground(Color.BLUE);
		btnNew.setBackground(hexa1);
		btnNew.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNew.addActionListener(this);
		frm.add(btnNew);

		btnEDIT = new JButton("EDIT");
		btnEDIT.setBounds(130, 320, 100, 30);
		btnEDIT.setForeground(Color.BLUE);
		btnEDIT.setBackground(hexa1);
		btnEDIT.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEDIT.addActionListener(this);
		frm.add(btnEDIT);

		btnClear = new JButton("CLEAR");
		btnClear.setBounds(240, 320, 100, 30);
		btnClear.setForeground(Color.BLUE);
		btnClear.setBackground(hexa1);
		btnClear.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnClear.addActionListener(this);
		frm.add(btnClear);
		disabled();

		frm.setVisible(true);

	}

	public void ShowTable()
	{
		String sqlqry = "SELECT ID,SERVICE,FEE FROM SERVICE_DETAILS";
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
					ServicesOpertions so = new ServicesOpertions(txtServId.getText(),txtServiceName.getText().toUpperCase(), txtFee.getText().toUpperCase());
					if (so.userValidate())
					{
						if (so.saveRecord() == 1)
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
		} else if (e.getSource().equals(btnEDIT)) 
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
					ServicesOpertions so = new ServicesOpertions(txtServId.getText(),txtServiceName.getText().toUpperCase(), txtFee.getText().toUpperCase());
					if (so.update() == 1) 
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

	}

	public void getid() 
	{
		ServicesOpertions so = new ServicesOpertions();
		txtServId.setText(so.getId());
	}

	public boolean frmvalidate() 
	{
		boolean flag = true;
		if (txtServiceName.getText() == "")
		{
			JOptionPane.showMessageDialog(null, "Enter Service");
			txtServiceName.grabFocus();
			flag = false;
		} 
		else if (ValidateForm.validateAnyNo(txtFee.getText()) == false) 
		{
			JOptionPane.showMessageDialog(null, "Enter Charges ");
			txtFee.grabFocus();
			flag = false;
		}
		return flag;
	}

	public void clear() 
	{
		txtServId.setText("");
		txtServiceName.setText("");
		txtFee.setText("");
	}

	public void disabled()
	{
		txtServId.setEditable(false);
		txtServiceName.setEditable(false);
		txtFee.setEditable(false);
	}

	public void enabled() 
	{
		txtServiceName.setEditable(true);
		txtFee.setEditable(true);
	}
}

public class Service
{
	Serv sv = new Serv();
}
