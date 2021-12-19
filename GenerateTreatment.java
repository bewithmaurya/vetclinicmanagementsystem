import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.*;
import java.awt.print.PrinterException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

class GenerateTreat
{
	public JFrame frm;
	public JPanel contentPane;
	public JFileChooser dialog;
	public JLabel Heading,Normal1,Normal2,Normal3,Rx;
	public JTextArea textArea;
	String filePath;
	Font f = new Font("Times New Roman", Font.BOLD, 15);
	GenerateTreat()
	{
		frm = new JFrame("Prescription Report");
		frm.setSize(550,740);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int framewidth = frm.getSize().width;
		int frameheight = frm.getSize().height;

		int framelocationX = (dim.width - framewidth) / 2;
		int frmlocationY = (dim.height - frameheight) / 2;

		frm.setLocation(framelocationX, frmlocationY);
		frm.setResizable(false);

		frm.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/icon.png")));
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frm.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Heading = new JLabel("               \tLOVING HAND PET CLINIC        ");
		Normal1 = new JLabel("Dr. Rakesh Kumar (B.V.Sc & Ah)                                Kishore (Pandit Jee)");
		Normal2 = new JLabel("(Vety. Physician & Surgeon)                                                 (Vety. Liv. Dev.)");
		Normal3 = new JLabel("Mob.: 9958938441                                                               Mob.:7838556716");
		Rx = new JLabel("Rx                                                                                       Date : ");
		
		textArea = new JTextArea();
		textArea.setBounds(10, 11, 535, 650);
		textArea.setEditable(false);
		contentPane.add(textArea);
		textArea.append("***************************************************************\n"+Heading.getText()+"\n***************************************************************\n"+Normal1.getText()+"\n"+Normal2.getText()+"\n"+Normal3.getText()+"\n-----------------------------------------------------------------------------------------------------\n"+Rx.getText());
		textArea.setFont(f);
		
		JButton btnPrint = new JButton("Print");
		btnPrint.setBackground(Color.WHITE);
		btnPrint.setForeground(new Color(0, 128, 0));
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				try {
					textArea.print();
				} catch (PrinterException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnPrint.setBounds(120, 663, 300, 30);
		btnPrint.addMouseListener(new MouseAdapter()
		{
			public void mouseEntered(MouseEvent e) 
			{
				btnPrint.setBackground(new Color(0, 128, 0));
				btnPrint.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) 
			{
				btnPrint.setBackground(Color.WHITE);
				btnPrint.setForeground(new Color(0, 128, 0));
			}
		});
		btnPrint.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.add(btnPrint);
		
		
		
		JButton btnReset = new JButton("Reset");
		btnReset.setBounds(321, 399, 89, 23);
		contentPane.add(btnReset);
		frm.setVisible(true);
	}
}
public class GenerateTreatment 
{
	GenerateTreat gt = new GenerateTreat();
}
