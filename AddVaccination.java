import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.ParseException;
import javax.swing.*;
import javax.swing.text.MaskFormatter;

class AddVacc extends Base implements ActionListener 
{
	public JFrame frm;
	public PreparedStatement ps;
	public ResultSet rs;
	public Connection conn;
	public JLabel lblvaccName, lblVeterinaryManagementSystem, lbldrugs, lblVaccination, barlabel, lblInterval,lblduration;
	public JTextField txtduration, txtInterval;
	public JComboBox<String> cmbvaccName;
	public JButton btnADD, btncancle;
	public MaskFormatter mf, mf1;
	String addmedicine;
	Font f = new Font("consolas", Font.BOLD, 14);
	boolean flag;

	AddVacc() 
	{
		frm = new JFrame("VACCINATION");
		frm.setSize(390, 285);
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
		lblVeterinaryManagementSystem.setBounds(20, 12, 370, 20);
		frm.add(lblVeterinaryManagementSystem);

		Canvas canvas = new Canvas();
		canvas.setBackground(Color.WHITE);
		canvas.setForeground(Color.RED);
		canvas.setBounds(20, 33, 335, 2);
		frm.add(canvas);

		lblVaccination = new JLabel("Vaccination");
		lblVaccination.setForeground(Color.WHITE);
		lblVaccination.setFont(new Font("Magneto", Font.BOLD, 17));
		lblVaccination.setBounds(150, 40, 204, 27);
		frm.add(lblVaccination);

		barlabel = new JLabel();
		Image userimg = new ImageIcon(this.getClass().getResource("/images/emp_bar.png")).getImage();
		barlabel.setIcon(new ImageIcon(userimg));
		barlabel.setBounds(0, 0, 390, 70);
		frm.add(barlabel);

		lblvaccName = new JLabel("VACCINATION NAME");
		lblvaccName.setBounds(20, 80, 150, 30);
		frm.add(lblvaccName);
		lblvaccName.setFont(f);
		lblvaccName.setForeground(Color.blue);

		cmbvaccName = new JComboBox<String>();
		cmbvaccName.setBounds(160, 80, 200, 30);
		cmbvaccName.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		srccour();
		cmbvaccName.setBackground(Color.WHITE);
		frm.add(cmbvaccName);
		cmbvaccName.setFont(f);

		lblInterval = new JLabel("TYPE");
		lblInterval.setBounds(20, 120, 80, 30);
		frm.add(lblInterval);
		lblInterval.setFont(f);
		lblInterval.setForeground(Color.blue);

		try 
		{
			mf = new MaskFormatter("after ## Days");
		} 
		catch (ParseException e)
		{
			e.printStackTrace();
		}

		txtInterval = new JFormattedTextField(mf);
		txtInterval.setBounds(160, 120, 200, 30);
		txtInterval.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		txtInterval.setBackground(Color.WHITE);
		frm.add(txtInterval);
		txtInterval.setFont(f);

		lblduration = new JLabel("DURATION");
		lblduration.setBounds(20, 160, 120, 30);
		frm.add(lblduration);
		lblduration.setFont(f);
		lblduration.setForeground(Color.blue);

		try 
		{
			mf1 = new MaskFormatter("## Days");
		} 
		catch (ParseException e) 
		{
			e.printStackTrace();
		}

		txtduration = new JFormattedTextField(mf1);
		txtduration.setBounds(160, 160, 200, 30);
		txtduration.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(txtduration);
		txtduration.setFont(f);

		btnADD = new JButton("ADD");
		btnADD.setBounds(10, 200, 170, 35);
		btnADD.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(btnADD);
		btnADD.addActionListener(this);
		btnADD.setBackground(Color.decode("#88d2f6"));

		btncancle = new JButton("CANCLE");
		btncancle.setBounds(195, 200, 170, 35);
		btncancle.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(btncancle);
		btncancle.addActionListener(this);
		btncancle.setBackground(Color.decode("#88d2f6"));

		frm.setVisible(true);
	}

	@SuppressWarnings("static-access")
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource().equals(btnADD))
		{
			addmedicine = cmbvaccName.getSelectedItem().toString() + " - " + txtInterval.getText().toUpperCase() + "    -     " + txtduration.getText() + "\n";
			super.Medical = addmedicine;
			frm.dispose();
		} 
		else if (e.getSource().equals(btncancle)) 
		{
			frm.dispose();
		}
	}

	public void srccour()
	{
		String sqlqry = "select VACCINATION from VACCINATION_DETAILS";
		conn = Connections.getConnections();
		try 
		{
			ps = conn.prepareStatement(sqlqry);
			rs = ps.executeQuery();
			while (rs.next()) 
			{
				cmbvaccName.addItem(rs.getString(1));
				cmbvaccName.setSelectedIndex(-1);
			}
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}

public class AddVaccination 
{
	AddVacc av = new AddVacc();
}
