import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.ParseException;
import javax.swing.*;
import javax.swing.text.MaskFormatter;

class AddMed extends Base implements ActionListener, ItemListener 
{
	public JFrame frm;
	public PreparedStatement ps;
	public ResultSet rs;
	public Connection conn;
	public JLabel lbldid, lblmedName, lblVeterinaryManagementSystem, lbldrugs, lblMedicine, barlabel, lbltype,
			lbldosage, lblmorn, lblnoon, lbleve, lbldosremark, lblduration;
	public JTextField txtdid, txtdname, txtdosremarks, txtduration, txttypee, txtcompny;
	public JComboBox<String> cmbmedName, cmbmorning, cmbnoon, cmbeve;
	public JButton btnADD, btndrugs, btnupdate, btncancle;
	String dosg[] = { "0", "0.5", "1", };
	String strdrugs, strtype, strdosage, addmedicine;
	public MaskFormatter mf;
	Font f = new Font("consolas", Font.BOLD, 14);
	boolean flag;

	AddMed()
	{
		frm = new JFrame("MEDICINE");
		frm.setSize(370, 350);
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
		lblVeterinaryManagementSystem.setBounds(10, 12, 370, 20);
		frm.add(lblVeterinaryManagementSystem);

		Canvas canvas = new Canvas();
		canvas.setBackground(Color.WHITE);
		canvas.setForeground(Color.RED);
		canvas.setBounds(10, 33, 335, 2);
		frm.add(canvas);

		lblMedicine = new JLabel("Medicine");
		lblMedicine.setForeground(Color.WHITE);
		lblMedicine.setFont(new Font("Magneto", Font.BOLD, 17));
		lblMedicine.setBounds(140, 40, 204, 27);
		frm.add(lblMedicine);

		barlabel = new JLabel();
		Image userimg = new ImageIcon(this.getClass().getResource("/images/emp_bar.png")).getImage();
		barlabel.setIcon(new ImageIcon(userimg));
		barlabel.setBounds(0, 0, 370, 70);
		frm.add(barlabel);

		lblmedName = new JLabel("MEDICINE NAME");
		lblmedName.setBounds(20, 80, 110, 30);
		frm.add(lblmedName);
		lblmedName.setFont(f);
		lblmedName.setForeground(Color.blue);

		cmbmedName = new JComboBox<String>();
		cmbmedName.setBounds(140, 80, 200, 30);
		srccour();
		cmbmedName.setBackground(Color.WHITE);
		cmbmedName.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cmbmedName.addItemListener(this);
		frm.add(cmbmedName);
		cmbmedName.setFont(f);

		lbltype = new JLabel("TYPE");
		lbltype.setBounds(20, 120, 80, 30);
		frm.add(lbltype);
		lbltype.setFont(f);
		lbltype.setForeground(Color.blue);

		txttypee = new JTextField();
		txttypee.setBounds(140, 120, 200, 30);
		txttypee.setBackground(Color.WHITE);
		txttypee.setEditable(false);
		frm.add(txttypee);
		txttypee.setFont(f);

		lblmorn = new JLabel("MORNING");
		lblmorn.setBounds(140, 160, 60, 30);
		frm.add(lblmorn);
		lblmorn.setFont(f);
		lblmorn.setForeground(Color.blue);

		lblnoon = new JLabel("NOON");
		lblnoon.setBounds(210, 160, 50, 30);
		frm.add(lblnoon);
		lblnoon.setFont(f);
		lblnoon.setForeground(Color.blue);

		lbleve = new JLabel("EVENING");
		lbleve.setBounds(270, 160, 70, 30);
		frm.add(lbleve);
		lbleve.setFont(f);
		lbleve.setForeground(Color.blue);

		lbldosage = new JLabel("DOSAGE");
		lbldosage.setBounds(20, 190, 80, 30);
		frm.add(lbldosage);
		lbldosage.setFont(f);
		lbldosage.setForeground(Color.blue);

		cmbmorning = new JComboBox<String>(dosg);
		cmbmorning.setBounds(140, 190, 60, 30);
		cmbmorning.setBackground(Color.WHITE);
		cmbmorning.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(cmbmorning);
		cmbmorning.addItemListener(this);
		cmbmorning.setFont(f);

		cmbnoon = new JComboBox<String>(dosg);
		cmbnoon.setBounds(210, 190, 50, 30);
		cmbnoon.setBackground(Color.WHITE);
		cmbnoon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(cmbnoon);
		cmbnoon.addItemListener(this);
		cmbnoon.setFont(f);

		cmbeve = new JComboBox<String>(dosg);
		cmbeve.setBounds(270, 190, 70, 30);
		cmbeve.setBackground(Color.WHITE);
		cmbeve.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(cmbeve);
		cmbeve.addItemListener(this);
		cmbeve.setFont(f);

		lblduration = new JLabel("DURATION");
		lblduration.setBounds(20, 230, 120, 30);
		frm.add(lblduration);
		lblduration.setFont(f);
		lblduration.setForeground(Color.blue);

		try {
			mf = new MaskFormatter("## Days");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		txtduration = new JFormattedTextField(mf);
		txtduration.setBounds(140, 230, 200, 30);
		txtduration.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(txtduration);
		txtduration.setFont(f);

		btnADD = new JButton("ADD");
		btnADD.setBounds(10, 270, 160, 35);
		btnADD.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(btnADD);
		btnADD.addActionListener(this);
		btnADD.setBackground(Color.decode("#88d2f6"));

		btncancle = new JButton("CANCLE");
		btncancle.setBounds(185, 270, 160, 35);
		btncancle.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(btncancle);
		btncancle.addActionListener(this);
		btncancle.setBackground(Color.decode("#88d2f6"));

		frm.setVisible(true);

	}

	@Override
	public void itemStateChanged(ItemEvent e)
	{
		if (e.getSource().equals(cmbmedName)) 
		{
			type(cmbmedName.getSelectedItem().toString());
		}
	}

	@SuppressWarnings("static-access")
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource().equals(btnADD)) 
		{
			addmedicine = cmbmedName.getSelectedItem().toString() +"  "+txttypee.getText().toUpperCase() + " - " + "MORNING - " + cmbmorning.getSelectedItem().toString() + " , " + "NOON - " + cmbnoon.getSelectedItem().toString() + " , " + "EVENING - " + cmbeve.getSelectedItem().toString() + "   -   " + txtduration.getText() + "\n";
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
		String sqlqry = "select NAME from MEDICINE_DETAILS";
		conn = Connections.getConnections();
		try 
		{
			ps = conn.prepareStatement(sqlqry);
			rs = ps.executeQuery();
			while (rs.next()) 
			{
				cmbmedName.addItem(rs.getString(1));
				cmbmedName.setSelectedIndex(-1);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	public void type(String medicine)
	{
		String query = "select TYPE from MEDICINE_DETAILS WHERE NAME='" + medicine + "'";
		conn = Connections.getConnections();
		try 
		{
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next())
			{
				txttypee.setText(rs.getString(1));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		txttypee.getText();
	}
}

public class AddMedicine 
{
	AddMed am = new AddMed();
}
