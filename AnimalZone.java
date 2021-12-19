import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import javax.swing.*;
import javax.swing.table.*;
import net.proteanit.sql.DbUtils;

final class AnimZone extends Base 
{
	public PreparedStatement ps;
	public ResultSet rs;
	public Connection conn;
	public DefaultTableModel jtable = new DefaultTableModel();
	public JFrame frm;
	public JLabel lblVeterinaryManagementSystem, lblPatientDetails, barlabel, lblSearch;
	public JTextField txtSearch;
	public JTable tabview;

	AnimZone() 
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
		
		frm.setTitle("Patient Database");
		Color hexa = Color.decode("#c1fff0");
		frm.getContentPane().setBackground(hexa);

		Font f = new Font("Consolas", Font.BOLD, 13);

		lblVeterinaryManagementSystem = new JLabel("Veterinary Management System");
		lblVeterinaryManagementSystem.setFont(new Font("Algerian", Font.BOLD, 34));
		lblVeterinaryManagementSystem.setForeground(Color.WHITE);
		lblVeterinaryManagementSystem.setBounds(80, 12, 650, 30);
		frm.add(lblVeterinaryManagementSystem);

		Canvas canvas = new Canvas();
		canvas.setBackground(Color.WHITE);
		canvas.setForeground(Color.RED);
		canvas.setBounds(80, 40, 635, 5);
		frm.add(canvas);

		lblPatientDetails = new JLabel("Patient Details");
		lblPatientDetails.setForeground(Color.WHITE);
		lblPatientDetails.setFont(new Font("Magneto", Font.BOLD, 23));
		lblPatientDetails.setBounds(300, 60, 300, 27);
		frm.add(lblPatientDetails);

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
		Object[] columns = { "DATE", "REGISTRATION NO.", "OWNER", "ANIMAL NAME", "AGE", "SPECIES", "MOBILE NO","EMAIL ID", "ADDRESS", "MEDICAL HISTORY" };
		jtable.setColumnIdentifiers(columns);
		tabview.setModel(jtable);
		tabview.setRowHeight(30);
		tabview.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tabview.addMouseListener(new MouseAdapter() 
		{
            @Override
			public void mouseClicked(MouseEvent e) 
            {
				int i = tabview.getSelectedRow();
				TableModel model = tabview.getModel();
				ProfileAnimal pa = new ProfileAnimal();
				try
				{
					String regdate = new SimpleDateFormat("MMM dd, yyyy").format(model.getValueAt(i, 0));
					pa.dateChooser.setText(regdate);
				} 
				catch (Exception e1) 
				{
				}
				pa.txtregistration.setText(model.getValueAt(i, 1).toString());
				pa.txtName.setText(model.getValueAt(i, 2).toString());
				pa.txtAnimalName.setText(model.getValueAt(i, 3).toString());
				pa.cmbGender.setText(model.getValueAt(i, 4).toString());
				pa.txtAge.setText(model.getValueAt(i, 5).toString());
				pa.cmbSpecies.setText(model.getValueAt(i, 6).toString());
				pa.txtBreed.setText(model.getValueAt(i, 7).toString());
				pa.txtHeight.setText(model.getValueAt(i, 8).toString());
				pa.txtWeight.setText(model.getValueAt(i, 9).toString());
				pa.txtMob_No.setText(model.getValueAt(i, 10).toString());
				pa.txtEmailId.setText(model.getValueAt(i, 11).toString());
				pa.txtFull_Address.setText(model.getValueAt(i, 12).toString());
				pa.cmbState.setText(model.getValueAt(i, 13).toString());
				pa.txtMedicalHistory.setText(model.getValueAt(i, 14).toString());
			}
		});
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
				TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(table);
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
		String sqlqry = "SELECT REG_DATE,REG_NO,OWNER_NAME,PET_NAME,GENDER,AGE,SPECIES,BREED,HEIGHT,WEIGHT,MOBILE_NO,EMAILID,ADDRESS,STATE,MEDICAL_HISTORY FROM ANIMAL_DETAILS";
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

public class AnimalZone 
{
	AnimZone az = new AnimZone();
}
