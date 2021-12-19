import java.awt.*;
import javax.swing.*;

class TreatProfile
{
	public JFrame frm;
	public JLabel lblVeterinaryManagementSystem, lblTreatment, barlabel, lblDate, lblAppId, lblProblem, lblAnimalName,lblEmailId, lblPrescription, lblRegNo, lblPresc, lblName, lblOwnerName, txtDate, txtAnimalName, txtProblem,txtAppId, txtPresc, txtRegNo, txtRegDate, txtOwnerName, txtName;
	public Color hexa1;
	public JTextArea txtPrescription;

	Font f = new Font("Consolas", Font.BOLD, 13);

	TreatProfile()
	{
		frm = new JFrame();
		frm.setSize(820, 470);

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int framewidth = frm.getSize().width;
		int frameheight = frm.getSize().height;

		int framelocationX = (dim.width - framewidth) / 2;
		int frmlocationY = (dim.height - frameheight) / 2;

		frm.setLocation(framelocationX, frmlocationY);

		frm.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/icon.png")));
		
		frm.setResizable(false);
		Color hexa = Color.decode("#BBF2FA");
		frm.getContentPane().setBackground(hexa);
		frm.setLayout(null);
		frm.setTitle("TREATMENT FORM");

		hexa1 = Color.decode("#82efff");

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
		barlabel.setBounds(0, 0, 900, 105);
		frm.add(barlabel);

		lblDate = new JLabel("DATE");
		lblDate.setBounds(20, 130, 180, 30);
		lblDate.setFont(f);
		lblDate.setForeground(Color.BLUE);
		frm.add(lblDate);

		txtDate = new JLabel();
		txtDate.setBounds(180, 130, 140, 30);
		txtDate.setFont(f);
		txtDate.setBackground(Color.WHITE);
		txtDate.setForeground(Color.BLACK);
		frm.add(txtDate);

		lblPresc = new JLabel("PRESCRIPTION NO.");
		lblPresc.setBounds(400, 130, 150, 30);
		lblPresc.setFont(f);
		lblPresc.setForeground(Color.BLUE);
		frm.add(lblPresc);

		txtPresc = new JLabel();
		txtPresc.setBounds(600, 130, 250, 30);
		txtPresc.setFont(f);
		txtPresc.setForeground(Color.BLACK);
		frm.add(txtPresc);

		lblAppId = new JLabel("APPOINTMENT ID");
		lblAppId.setBounds(20, 180, 100, 30);
		lblAppId.setFont(f);
		lblAppId.setForeground(Color.BLUE);
		frm.add(lblAppId);

		txtAppId = new JLabel();
		txtAppId.setBounds(180, 180, 250, 30);
		txtAppId.setFont(f);
		txtAppId.setForeground(Color.BLACK);
		txtAppId.setBackground(Color.WHITE);
		frm.add(txtAppId);

		lblRegNo = new JLabel("REGISTRATION NO.");
		lblRegNo.setBounds(400, 180, 150, 30);
		lblRegNo.setFont(f);
		lblRegNo.setForeground(Color.BLUE);
		frm.add(lblRegNo);

		txtRegNo = new JLabel();
		txtRegNo.setBounds(600, 180, 250, 30);
		txtRegNo.setFont(f);
		txtRegNo.setBackground(Color.WHITE);
		txtRegNo.setForeground(Color.BLACK);
		frm.add(txtRegNo);

		lblOwnerName = new JLabel("OWNER NAME");
		lblOwnerName.setBounds(20, 230, 100, 30);
		lblOwnerName.setFont(f);
		lblOwnerName.setForeground(Color.BLUE);
		frm.add(lblOwnerName);

		txtOwnerName = new JLabel();
		txtOwnerName.setBounds(180, 230, 250, 30);
		txtOwnerName.setFont(f);
		txtOwnerName.setForeground(Color.BLACK);
		frm.add(txtOwnerName);

		lblAnimalName = new JLabel("PET NAME");
		lblAnimalName.setBounds(400, 230, 180, 30);
		lblAnimalName.setFont(f);
		lblAnimalName.setForeground(Color.BLUE);
		frm.add(lblAnimalName);

		txtAnimalName = new JLabel();
		txtAnimalName.setBounds(600, 230, 250, 30);
		txtAnimalName.setFont(f);
		txtAnimalName.setBackground(Color.WHITE);
		frm.add(txtAnimalName);

		lblName = new JLabel("DOCTOR/EMPLOYEE NAME");
		lblName.setBounds(20, 280, 170, 30);
		lblName.setFont(f);
		frm.add(lblName);
		lblName.setForeground(Color.BLUE);

		txtName = new JLabel();
		txtName.setBounds(180, 280, 250, 30);
		txtName.setFont(f);
		txtName.setForeground(Color.BLACK);
		frm.add(txtName);

		lblProblem = new JLabel("PROBLEM");
		lblProblem.setBounds(400, 280, 180, 30);
		lblProblem.setFont(f);
		lblProblem.setForeground(Color.BLUE);
		frm.add(lblProblem);

		txtProblem = new JLabel();
		txtProblem.setBounds(600, 280, 250, 30);
		txtProblem.setFont(f);
		txtProblem.setForeground(Color.BLACK);
		frm.add(txtProblem);

		lblPrescription = new JLabel("APPOINTMENT TIME");
		lblPrescription.setBounds(20, 330, 180, 30);
		lblPrescription.setFont(f);
		lblPrescription.setForeground(Color.BLUE);
		frm.add(lblPrescription);

		JScrollPane js = new JScrollPane();
		txtPrescription = new JTextArea();
		js.setBounds(180, 330, 580, 60);
		txtPrescription.setFont(f);
		txtPrescription.setBackground(hexa);
		txtPrescription.setForeground(Color.BLACK);
		txtPrescription.setEditable(false);
		js.setViewportView(txtPrescription);
		frm.add(js);

		frm.setVisible(true);
	}
}

public class TreatmentProfile 
{
	TreatProfile tp = new TreatProfile();
}