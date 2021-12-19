import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;
import net.proteanit.sql.DbUtils;

class TreatZone extends Base 
{
	public PreparedStatement ps;
	public ResultSet rs;
	public Connection conn;
	public DefaultTableModel doc_jtable, emp_jtable;
	public JFrame frm;
	public JLabel lblVeterinaryManagementSystem, lblTreatment, barlabel, lblSearch, lblName;
	public JTable doc_tabview, emp_tabview;
	public JTextField doc_txtSearch, emp_txtSearch;

	TreatZone()
	{
		frm = new JFrame();

		frm.setSize(800, 650);
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
		
		frm.setTitle("TREATMENT DATABASE");
		Color hexa = Color.decode("#c1fff0");
		frm.getContentPane().setBackground(hexa);

		Font f = new Font("Consolas", Font.BOLD, 13);

		lblVeterinaryManagementSystem = new JLabel("Veterinary Management System");
		lblVeterinaryManagementSystem.setFont(new Font("Algerian", Font.BOLD, 34));
		lblVeterinaryManagementSystem.setForeground(Color.WHITE);
		lblVeterinaryManagementSystem.setBounds(70, 12, 650, 30);
		frm.add(lblVeterinaryManagementSystem);

		Canvas canvas = new Canvas();
		canvas.setBackground(Color.WHITE);
		canvas.setForeground(Color.RED);
		canvas.setBounds(70, 40, 635, 5);
		frm.add(canvas);

		lblTreatment = new JLabel("Treatment Details");
		lblTreatment.setForeground(Color.WHITE);
		lblTreatment.setFont(new Font("Magneto", Font.BOLD, 23));
		lblTreatment.setBounds(280, 60, 300, 27);
		frm.add(lblTreatment);

		barlabel = new JLabel();
		Image userimg = new ImageIcon(this.getClass().getResource("/images/recbar.png")).getImage();
		barlabel.setIcon(new ImageIcon(userimg));
		barlabel.setBounds(0, 0, 800, 105);
		frm.add(barlabel);

		lblName = new JLabel("DOCTOR");
		lblName.setBounds(10, 110, 100, 30);
		lblName.setFont(f);
		frm.add(lblName);

		lblSearch = new JLabel("SEARCH");
		lblSearch.setBounds(460, 110, 100, 30);
		lblSearch.setForeground(Color.BLUE);
		lblSearch.setFont(f);
		lblSearch.setBackground(Color.BLUE);
		frm.add(lblSearch);

		doc_jtable = new DefaultTableModel();
		doc_tabview = new JTable(doc_jtable);
		JScrollPane js = new JScrollPane(doc_tabview);
		js.setBounds(8, 150, 770, 200);
		doc_tabview.setFont(f);
		doc_tabview.setForeground(Color.blue);
		Object[] columns = { "CURDATE", "PRESC_NO", "APMNT_NO", "REG_NO", "NAME", "OWNER_NAME", "PET_NAME", "SERVICE","PRESCRIPTION" };
		doc_jtable.setColumnIdentifiers(columns);
		doc_tabview.setRowHeight(30);
		doc_tabview.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		doc_tabview.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				int i = doc_tabview.getSelectedRow();
				TableModel model = doc_tabview.getModel();
				TreatProfile pa = new TreatProfile();
				pa.txtDate.setText(model.getValueAt(i, 0).toString());
				pa.txtPresc.setText(model.getValueAt(i, 1).toString());
				pa.txtAppId.setText(model.getValueAt(i, 2).toString());
				pa.txtRegNo.setText(model.getValueAt(i, 3).toString());
				pa.txtName.setText(model.getValueAt(i, 4).toString());
				pa.txtOwnerName.setText(model.getValueAt(i, 5).toString());
				pa.txtAnimalName.setText(model.getValueAt(i, 6).toString());
				pa.txtProblem.setText(model.getValueAt(i, 7).toString());
				pa.txtPrescription.setText(model.getValueAt(i, 8).toString());
			}
		});
		doc_tabview.setModel(doc_jtable);
		doc_txtSearch = new JTextField();
		doc_txtSearch.setBounds(530, 110, 240, 30);
		doc_txtSearch.setFont(f);
		doc_txtSearch.setBackground(Color.WHITE);
		doc_txtSearch.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		doc_txtSearch.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyReleased(KeyEvent e) 
			{
				DefaultTableModel table = (DefaultTableModel) doc_tabview.getModel();
				String search = doc_txtSearch.getText();
				TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(table);
				doc_tabview.setRowSorter(tr);
				tr.setRowFilter(RowFilter.regexFilter(search));
			}

		});
		frm.add(doc_txtSearch);
		frm.add(js);
		Doc_ShowTable();

		lblName = new JLabel("EMPLOYEE");
		lblName.setBounds(10, 360, 100, 30);
		lblName.setFont(f);
		frm.add(lblName);

		lblSearch = new JLabel("SEARCH");
		lblSearch.setBounds(460, 360, 100, 30);
		lblSearch.setForeground(Color.BLUE);
		lblSearch.setFont(f);
		lblSearch.setBackground(Color.BLUE);
		frm.add(lblSearch);

		emp_jtable = new DefaultTableModel();
		emp_tabview = new JTable(emp_jtable);
		JScrollPane js1 = new JScrollPane(emp_tabview);
		js1.setBounds(8, 400, 770, 200);
		emp_tabview.setFont(f);
		emp_tabview.setForeground(Color.blue);
		Object[] columns1 = { "CURDATE", "PRESC_NO", "APMNT_NO", "REG_NO", "NAME", "OWNER_NAME", "PET_NAME", "SERVICE","STATUS" };
		emp_jtable.setColumnIdentifiers(columns1);
		emp_tabview.setRowHeight(30);
		emp_tabview.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		emp_tabview.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				int i = emp_tabview.getSelectedRow();
				TableModel model = emp_tabview.getModel();
				TreatProfile pa = new TreatProfile();
				pa.txtDate.setText(model.getValueAt(i, 0).toString());
				pa.txtPresc.setText(model.getValueAt(i, 1).toString());
				pa.txtAppId.setText(model.getValueAt(i, 2).toString());
				pa.txtRegNo.setText(model.getValueAt(i, 3).toString());
				pa.txtName.setText(model.getValueAt(i, 4).toString());
				pa.txtOwnerName.setText(model.getValueAt(i, 5).toString());
				pa.txtAnimalName.setText(model.getValueAt(i, 6).toString());
				pa.txtProblem.setText(model.getValueAt(i, 7).toString());
				pa.txtPrescription.setText(model.getValueAt(i, 8).toString());
			}
		});
		emp_tabview.setModel(emp_jtable);
		emp_txtSearch = new JTextField();
		emp_txtSearch.setBounds(530, 360, 240, 30);
		emp_txtSearch.setFont(f);
		emp_txtSearch.setBackground(Color.WHITE);
		emp_txtSearch.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		emp_txtSearch.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyReleased(KeyEvent e) 
			{
				DefaultTableModel table = (DefaultTableModel) emp_tabview.getModel();
				String search = emp_txtSearch.getText();
				TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(table);
				emp_tabview.setRowSorter(tr);
				tr.setRowFilter(RowFilter.regexFilter(search));
			}

		});
		frm.add(emp_txtSearch);
		frm.add(js1);
		Emp_ShowTable();

		frm.setVisible(true);

	}

	public void Doc_ShowTable() 
	{
		String sqlqry = "SELECT CURDATE,PRESC_NO,APMNT_NO,REG_NO,DOCNAME,OWNER_NAME,PET_NAME,SERVICE,PRESCRIPTION FROM DOC_TREATMENT";
		conn = Connections.getConnections();
		try
		{
			ps = conn.prepareStatement(sqlqry);
			rs = ps.executeQuery();
			doc_tabview.setModel(DbUtils.resultSetToTableModel(rs));
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}

	}

	public void Emp_ShowTable() 
	{
		String sqlqry = "SELECT CURDATE,PRESC_NO,APMNT_NO,REG_NO,EMPNAME,OWNER_NAME,PET_NAME,SERVICE,STATUS FROM EMP_TREATMENT";
		conn = Connections.getConnections();
		try
		{
			ps = conn.prepareStatement(sqlqry);
			rs = ps.executeQuery();
			emp_tabview.setModel(DbUtils.resultSetToTableModel(rs));
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}

	}
}

public class Treatment
{
	TreatZone tz = new TreatZone();
}
