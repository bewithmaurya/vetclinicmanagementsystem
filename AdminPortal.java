import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

class Admin extends Base implements ActionListener 
{
	public JFrame frm;
	public JLabel Fullimg, userlabel, namelabel, idlabel, lblupdateprofile, adminlabel, lbldocicon, lblrecicon,lblVeterinaryManagementSystem, lblAdminPortal, barlabel;
	public JButton docbtn, recbtn, empbtn, petbtn, drtimebtn, treatmentbtn, vaccbtn, medbtn, servbtn, appbtn, btnlogof,btnExit;

	Admin() 
	{
		frm = new JFrame();
		frm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		Toolkit toolkit = frm.getToolkit();
		Dimension size = toolkit.getScreenSize();
		frm.setSize(size.width, size.height);
		frm.setLayout(null);
		frm.setResizable(false);
		frm.setUndecorated(true);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int framewidth = frm.getSize().width;
		int frameheigth = frm.getSize().height;
		int framelocationX = (dim.width - framewidth) / 2;
		int framelocationY = (dim.height - frameheigth) / 2;
		frm.setLocation(framelocationX, framelocationY);
		frm.getContentPane().setBackground(Color.WHITE);

		frm.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/icon.png")));
		
		adminlabel = new JLabel("");
		Image adminimg = new ImageIcon(this.getClass().getResource("/images/admin.png")).getImage();
		adminlabel.setIcon(new ImageIcon(adminimg));
		adminlabel.setBounds(0, 5, 128, 128);
		frm.add(adminlabel);

		lblVeterinaryManagementSystem = new JLabel("Veterinary Management System");
		lblVeterinaryManagementSystem.setFont(new Font("Algerian", Font.BOLD, 34));
		lblVeterinaryManagementSystem.setForeground(Color.WHITE);
		lblVeterinaryManagementSystem.setBounds(350, 22, 632, 49);
		frm.add(lblVeterinaryManagementSystem);

		Canvas canvas = new Canvas();
		canvas.setBackground(Color.WHITE);
		canvas.setForeground(Color.RED);
		canvas.setBounds(350, 71, 630, 4);
		frm.add(canvas);

		lblAdminPortal = new JLabel("Admin Portal");
		lblAdminPortal.setForeground(Color.WHITE);
		lblAdminPortal.setFont(new Font("Magneto", Font.BOLD, 27));
		lblAdminPortal.setBounds(593, 88, 204, 27);
		frm.add(lblAdminPortal);

		userlabel = new JLabel();
		Image userimg = new ImageIcon(this.getClass().getResource("/images/usernew.png")).getImage();
		userlabel.setIcon(new ImageIcon(userimg));
		userlabel.setBounds(30, 145, 200, 200);
		frm.add(userlabel);

		namelabel = new JLabel();
		namelabel.setFont(new Font("Agency FB", Font.BOLD, 32));
		namelabel.setBounds(250, 160, 300, 60);
		frm.add(namelabel);

		idlabel = new JLabel();

		userlabel = new JLabel("Admin");
		userlabel.setFont(new Font("Agency FB", Font.BOLD, 32));
		userlabel.setBounds(250, 230, 200, 40);
		frm.add(userlabel);

		lblupdateprofile = new JLabel("Update Profile");
		lblupdateprofile.setForeground(new Color(128, 0, 0));
		lblupdateprofile.setFont(new Font("Agency FB", Font.BOLD, 32));
		lblupdateprofile.setBounds(250, 280, 200, 40);
		lblupdateprofile.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblupdateprofile.addMouseListener(new MouseAdapter()
		{
            @Override
			public void mouseClicked(MouseEvent e) 
            {
				RegisterOperation ro = new RegisterOperation();
				ResultSet rs = ro.admin_sec_record((idlabel.getText()));
				try 
				{
					if (rs.next()) 
					{
						AdminUpdate au = new AdminUpdate();
						au.txtUser_Id.setText(rs.getString(1));
						au.txtName.setText(rs.getString(2));
						switch (rs.getString(3))
						{
						case "NICK NAME":
							au.cmbSecQueston.setSelectedIndex(0);
							break;
						case "FAVOURITE COLOR":
							au.cmbSecQueston.setSelectedIndex(1);
							break;
						case "FAVOURITE FOOD":
							au.cmbSecQueston.setSelectedIndex(2);
							break;
						case "FAVOURITE PLACE":
							au.cmbSecQueston.setSelectedIndex(3);
							break;
						default:
							break;
						}
						au.cmbSecQueston.setSelectedItem(rs.getString(3));
						au.txtsAnswer.setText(rs.getString(4));
						au.txtPASS.setText(rs.getString(5));
						au.txt_ConfPASS.setText(rs.getString(5));
					}
				} 
				catch (SQLException e1)
				{
				}
			}
		});
		frm.add(lblupdateprofile);

		Canvas canvaschpass = new Canvas();
		canvaschpass.setBackground(new Color(128, 0, 0));
		canvaschpass.setBounds(250, 325, 150, 4);
		frm.add(canvaschpass);

		barlabel = new JLabel("");
		Image barimg = new ImageIcon(this.getClass().getResource("/images/BarNew.png")).getImage();
		barlabel.setIcon(new ImageIcon(barimg));
		barlabel.setBounds(91, 19, 1293, 102);
		frm.add(barlabel);

		docbtn = new JButton();
		Image docimg = new ImageIcon(this.getClass().getResource("/images/doctor.png")).getImage();
		docbtn.setIcon(new ImageIcon(docimg));
		docbtn.setFont(new Font("Bookman Old Style", Font.BOLD, 20));
		docbtn.setBounds(20, 470, 245, 60);
		docbtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		docbtn.addActionListener(this);
		frm.add(docbtn);

		recbtn = new JButton();
		Image recimg = new ImageIcon(this.getClass().getResource("/images/Receptionist1.png")).getImage();
		recbtn.setIcon(new ImageIcon(recimg));
		recbtn.setFont(new Font("Bookman Old Style", Font.BOLD, 20));
		recbtn.setBounds(330, 470, 245, 60);
		recbtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		recbtn.addActionListener(this);
		frm.add(recbtn);

		empbtn = new JButton();
		Image empimg = new ImageIcon(this.getClass().getResource("/images/Employee.png")).getImage();
		empbtn.setIcon(new ImageIcon(empimg));
		empbtn.setFont(new Font("Bookman Old Style", Font.BOLD, 20));
		empbtn.setBounds(650, 470, 245, 60);
		empbtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		empbtn.addActionListener(this);
		frm.add(empbtn);

		petbtn = new JButton();
		Image petimg = new ImageIcon(this.getClass().getResource("/images/petsrecord.png")).getImage();
		petbtn.setIcon(new ImageIcon(petimg));
		petbtn.setFont(new Font("Bookman Old Style", Font.BOLD, 20));
		petbtn.setBounds(20, 570, 245, 60);
		petbtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		petbtn.addActionListener(this);
		frm.add(petbtn);

		appbtn = new JButton();
		Image appimg = new ImageIcon(this.getClass().getResource("/images/appointment.png")).getImage();
		appbtn.setIcon(new ImageIcon(appimg));
		appbtn.setBounds(330, 570, 245, 60);
		appbtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		appbtn.addActionListener(this);
		frm.add(appbtn);

		treatmentbtn = new JButton();
		Image drugimg = new ImageIcon(this.getClass().getResource("/images/treatment.png")).getImage();
		treatmentbtn.setIcon(new ImageIcon(drugimg));
		treatmentbtn.setBounds(650, 570, 245, 60);
		treatmentbtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		treatmentbtn.addActionListener(this);
		frm.add(treatmentbtn);

		medbtn = new JButton();
		Image medimg = new ImageIcon(this.getClass().getResource("/images/medicine.png")).getImage();
		medbtn.setIcon(new ImageIcon(medimg));
		medbtn.setBounds(20, 670, 245, 60);
		medbtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		medbtn.addActionListener(this);
		frm.add(medbtn);

		vaccbtn = new JButton();
		Image vaccimg = new ImageIcon(this.getClass().getResource("/images/vaccination.png")).getImage();
		vaccbtn.setIcon(new ImageIcon(vaccimg));
		vaccbtn.setBounds(330, 670, 245, 60);
		vaccbtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		vaccbtn.addActionListener(this);
		frm.add(vaccbtn);

		servbtn = new JButton();
		Image servimg = new ImageIcon(this.getClass().getResource("/images/services.png")).getImage();
		servbtn.setIcon(new ImageIcon(servimg));
		servbtn.setBounds(650, 670, 245, 60);
		servbtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		servbtn.addActionListener(this);
		frm.add(servbtn);

		Fullimg = new JLabel();
		Image img5 = new ImageIcon(this.getClass().getResource("/images/ad_bg.png")).getImage();
		Fullimg.setIcon(new ImageIcon(img5));
		Fullimg.setBounds(0, 0, framewidth, frameheigth);
		frm.add(Fullimg);

		btnlogof = new JButton();
		Image logoffimg = new ImageIcon(this.getClass().getResource("/images/logog.jpg")).getImage();
		btnlogof.setBounds(1280, 725, 35, 35);
		btnlogof.setIcon(new ImageIcon(logoffimg));
		Fullimg.add(btnlogof);
		btnlogof.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnlogof.addActionListener(this);

		btnExit = new JButton();
		Image exitimg = new ImageIcon(this.getClass().getResource("/images/Exit.jpg")).getImage();
		btnExit.setBounds(1322, 725, 35, 35);
		btnExit.setIcon(new ImageIcon(exitimg));
		Fullimg.add(btnExit);
		btnExit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExit.addActionListener(this);

		frm.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource().equals(docbtn)) 
		{
			new Doctor();
		}
		else if (e.getSource().equals(recbtn)) 
		{
			new Receptionist();
		}
		else if (e.getSource().equals(empbtn)) 
		{
			new Employee();
		} 
		else if (e.getSource().equals(petbtn))
		{
			new AnimalZone();
		}
		else if (e.getSource().equals(appbtn))
		{
			new AppointmentZone();
		} 
		else if (e.getSource().equals(treatmentbtn)) 
		{
			new Treatment();
		}
		else if (e.getSource().equals(medbtn))
		{
			new Medicine();
		} 
		else if (e.getSource().equals(vaccbtn))
		{
			new Vaccination();
		} 
		else if (e.getSource().equals(servbtn))
		{
			new Service();
		}
		else if (e.getSource().equals(btnlogof)) 
		{
			new Login();
			frm.dispose();
		} 
		else if (e.getSource().equals(btnExit))
		{
			System.exit(0);
		}
	}

}
public class AdminPortal {
    
}
