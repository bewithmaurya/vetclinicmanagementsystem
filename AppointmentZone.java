import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import javax.swing.*;
import javax.swing.table.*;
import net.proteanit.sql.DbUtils;

class AppZone extends Base
{
	public PreparedStatement ps;
	public ResultSet rs;
	public Connection conn;
	public DefaultTableModel jtable = new DefaultTableModel();
	public JFrame frm;
	public JLabel lblVeterinaryManagementSystem, lblAppointment, barlabel, lblSearch;
	public JTable tabview;
	public JTextField txtSearch;

	AppZone() 
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
		
		frm.setTitle("Appointment Database");
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

		lblAppointment = new JLabel("Appointment Details");
		lblAppointment.setForeground(Color.WHITE);
		lblAppointment.setFont(new Font("Magneto", Font.BOLD, 23));
		lblAppointment.setBounds(280, 60, 300, 27);
		frm.add(lblAppointment);

		barlabel = new JLabel();
		Image userimg = new ImageIcon(this.getClass().getResource("/images/recbar.png")).getImage();
		barlabel.setIcon(new ImageIcon(userimg));
		barlabel.setBounds(0, 0, 800, 105);
		frm.add(barlabel);

		lblSearch = new JLabel("SEARCH");
		lblSearch.setBounds(460, 130, 100, 30);
		lblSearch.setForeground(Color.BLUE);
		lblSearch.setFont(f);
		lblSearch.setBackground(Color.BLUE);
		frm.add(lblSearch);

		tabview = new JTable(jtable);
		JScrollPane js = new JScrollPane(tabview);
		js.setBounds(8, 180, 770, 425);
		tabview.setFont(f);
		tabview.setForeground(Color.blue);
		Object[] columns = { "DATE", "TIME", "REGISTRATION NO.", "APPOINTMENT NO.", "OWNER", "MOBILE NO", "ANIMAL NAME", "APPOINTMENT DATE", "APPOINTMENT TIME", "PROBLEM", "DOCTOR/EMPLOYEE" };
		jtable.setColumnIdentifiers(columns);
		tabview.setRowHeight(30);
		tabview.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tabview.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e) 
            {
				int i = tabview.getSelectedRow();
				TableModel model = tabview.getModel();
				ProfileApp pa = new ProfileApp();
				try 
				{
					String regdate = new SimpleDateFormat("MMM dd, yyyy").format(model.getValueAt(i, 0));
					pa.txtDate.setText(regdate);
				}
				catch (Exception e1) 
				{
				}
				pa.txtTime.setText(model.getValueAt(i, 1).toString());
				pa.txtRegNo.setText(model.getValueAt(i, 2).toString());
				pa.txtAppId.setText(model.getValueAt(i, 3).toString());
				pa.txtOwnerName.setText(model.getValueAt(i, 4).toString());
				pa.txtAnimalName.setText(model.getValueAt(i, 5).toString());
				pa.txtMobile.setText(model.getValueAt(i, 6).toString());
				try 
				{
					String date = new SimpleDateFormat("MMM dd, yyyy").format(model.getValueAt(i, 7));
					pa.txtAppDate.setText(date);
				}
				catch (Exception e1)
				{
				}
				pa.txtAppTimet.setText(model.getValueAt(i, 8).toString());
				pa.txtProblem.setText(model.getValueAt(i, 9).toString());
				pa.txtDoc_Emp.setText(model.getValueAt(i, 10).toString());
			}
		});
		tabview.setModel(jtable);
		txtSearch = new JTextField();
		txtSearch.setBounds(530, 130, 240, 30);
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
		ShowTable();
		frm.setVisible(true);

	}

	public void ShowTable()
	{
		String sqlqry = "SELECT CURDATE,CURTIME,REG_NO,APPOINTMENT_NO,OWNER_NAME,PET_NAME,MOBILE_NO,APPOINTMENT_DATE,APPOINTMENT_TIME,PROBLEM,DOC_EMP_NAME FROM APPOINTMENT";
		conn = Connections.getConnections();
		try
		{
			ps = conn.prepareStatement(sqlqry);
			rs = ps.executeQuery();
			tabview.setModel(DbUtils.resultSetToTableModel(rs));
		}
		catch (SQLException e)
		{
		}

	}
}

public class AppointmentZone
{
	AppZone az = new AppZone();
}
