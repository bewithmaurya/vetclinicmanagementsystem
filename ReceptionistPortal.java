import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;

class RecPortal extends Base implements ActionListener
{
	public JFrame frm;
	public JLabel Fullimg, userlabel, idlabel, namelabel, lblupdateprofile, adminlabel, lblVeterinaryManagementSystem,lblAdminPortal, barlabel;
	public JButton oldpatientbtn, newpatientbtn, btnlogof, btnExit;

	RecPortal() 
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
		Image adminimg = new ImageIcon(this.getClass().getResource("/images/recep_icon.png")).getImage();
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

		lblAdminPortal = new JLabel("Receptionist Portal");
		lblAdminPortal.setForeground(Color.WHITE);
		lblAdminPortal.setFont(new Font("Magneto", Font.BOLD, 27));
		lblAdminPortal.setBounds(560, 88, 300, 27);
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

		userlabel = new JLabel("Receptionist");
		userlabel.setFont(new Font("Agency FB", Font.BOLD, 32));
		userlabel.setBounds(250, 220, 200, 40);
		frm.add(userlabel);

		idlabel = new JLabel();

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
				ResultSet rs = ro.rec_sec_recordupdate((idlabel.getText()));
				try 
				{
					if (rs.next()) 
					{
						RecUpdate ru = new RecUpdate();
						ru.lblId.setText(rs.getString(1));
						ru.lblName.setText(rs.getString(2));
						try 
						{
							Date date = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString(3));
							ru.dateChooser.setDate(date);
						} 
						catch (ParseException e1) 
						{
							e1.printStackTrace();
						}
						if (rs.getString(4).equals("MALE")) 
						{
							ru.cmbSecQueston.setSelectedIndex(0);
						}
						else if (rs.getString(4).equals("FEMALE"))
						{
							ru.cmbSecQueston.setSelectedIndex(1);
						}
						ru.cmbGender.setSelectedItem(rs.getString(4));
						ru.txtMob_No.setText(rs.getString(5));
						ru.txtUser_Id.setText(rs.getString(6));
						if (rs.getString(7).equals("NICK NAME")) 
						{
							ru.cmbSecQueston.setSelectedIndex(0);
						}
						else if (rs.getString(7).equals("FAVOURITE COLOR")) 
						{
							ru.cmbSecQueston.setSelectedIndex(1);
						} 
						else if (rs.getString(7).equals("FAVOURITE FOOD")) 
						{
							ru.cmbSecQueston.setSelectedIndex(2);
						} 
						else if (rs.getString(7).equals("FAVOURITE PLACE")) 
						{
							ru.cmbSecQueston.setSelectedIndex(3);
						}
						ru.cmbSecQueston.setSelectedItem(rs.getString(7));
						ru.txtsAnswer.setText(rs.getString(8));
						ru.txtPASS.setText(rs.getString(9));
						ru.txt_ConfPASS.setText(rs.getString(9));
						ru.txtEmailId.setText(rs.getString(10));
						ru.txtFull_Address.setText(rs.getString(11));
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		frm.add(lblupdateprofile);

		Canvas canvaschpass = new Canvas();
		canvaschpass.setBackground(new Color(128, 0, 0));
		canvaschpass.setBounds(250, 325, 150, 4);
		frm.add(canvaschpass);

		barlabel = new JLabel("");
		Image barimg = new ImageIcon(this.getClass().getResource("/images/recbar.png")).getImage();
		barlabel.setIcon(new ImageIcon(barimg));
		barlabel.setBounds(91, 19, 1293, 102);
		frm.add(barlabel);

		newpatientbtn = new JButton();
		Image recimg = new ImageIcon(this.getClass().getResource("/images/newpatientbtn.png")).getImage();
		newpatientbtn.setIcon(new ImageIcon(recimg));
		newpatientbtn.setFont(new Font("Bookman Old Style", Font.BOLD, 20));
		newpatientbtn.setBounds(30, 450, 370, 60);
		newpatientbtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		newpatientbtn.addActionListener(this);
		frm.add(newpatientbtn);

		oldpatientbtn = new JButton();
		Image docimg = new ImageIcon(this.getClass().getResource("/images/oldpatientbtn.png")).getImage();
		oldpatientbtn.setIcon(new ImageIcon(docimg));
		oldpatientbtn.setFont(new Font("Bookman Old Style", Font.BOLD, 20));
		oldpatientbtn.setBounds(30, 550, 370, 60);
		oldpatientbtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		oldpatientbtn.addActionListener(this);
		frm.add(oldpatientbtn);

		Fullimg = new JLabel();
		Image img5 = new ImageIcon(this.getClass().getResource("/images/rec_bg.png")).getImage();
		Fullimg.setIcon(new ImageIcon(img5));
		Fullimg.setBounds(0, 0, framewidth, frameheigth);

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

		frm.add(Fullimg);

		frm.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource().equals(oldpatientbtn))
		{
			new Appointment();
		} 
		else if (e.getSource().equals(newpatientbtn)) 
		{
			new AnimalDetails();
		} 
		else if (e.getSource().equals(btnExit))
		{
			System.exit(0);
		}
		else if (e.getSource().equals(btnlogof))
		{
			new Login();
			frm.dispose();
		}
	}
}

public class ReceptionistPortal
{
	RecPortal rf = new RecPortal();
}
