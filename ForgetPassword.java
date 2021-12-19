import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class ForgetPass implements ActionListener, FocusListener
{
	public JFrame frm;
	public JButton btnUploade, btnChangePass, btnCancel;
	public JLabel lblUserType, lblId, lblcmbSecQueston, lblVeterinaryManagementSystem, lblName, lblForgetPassword, barlabel, lblsec_question, lblPassword, lblConfirmPass, lblAnswer, lblFull_Address, lblMob_No, lblPhoto,lbllogo, txtUser_Id, txtsAnswer, txtName, txtDOB, txtMob_No, txtEmailId, txtFull_Address;
	public JTextField txtId, txtSec_Answer;
	public JPasswordField txtPassword, txtConfirmPass;
	public JComboBox<Object> cmbUser;
	public Color hexa1;
	String arr[] = { "RECEPTIONIST", "DOCTOR", "EMPLOYEE" };

	Font f = new Font("consolas", Font.BOLD, 14);
	Boolean flag;

	ForgetPass() 
	{
		frm = new JFrame();
		frm.setSize(500, 450);

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
		frm.setTitle("FORGET PASSWORD");

		hexa1 = Color.decode("#82efff");

		lblVeterinaryManagementSystem = new JLabel("Veterinary Management System");
		lblVeterinaryManagementSystem.setFont(new Font("Algerian", Font.BOLD, 18));
		lblVeterinaryManagementSystem.setForeground(Color.WHITE);
		lblVeterinaryManagementSystem.setBounds(70, 12, 370, 20);
		frm.add(lblVeterinaryManagementSystem);

		Canvas canvas = new Canvas();
		canvas.setBackground(Color.WHITE);
		canvas.setForeground(Color.RED);
		canvas.setBounds(70, 33, 335, 2);
		frm.add(canvas);

		lblForgetPassword = new JLabel("Forget Password");
		lblForgetPassword.setForeground(Color.WHITE);
		lblForgetPassword.setFont(new Font("Magneto", Font.BOLD, 17));
		lblForgetPassword.setBounds(170, 40, 204, 27);
		frm.add(lblForgetPassword);

		barlabel = new JLabel();
		Image userimg = new ImageIcon(this.getClass().getResource("/images/emp_bar.png")).getImage();
		barlabel.setIcon(new ImageIcon(userimg));
		barlabel.setBounds(0, 0, 830, 70);
		frm.add(barlabel);

		lblUserType = new JLabel("USER TYPE");
		lblUserType.setBounds(20, 80, 100, 30);
		lblUserType.setFont(f);
		lblUserType.setForeground(Color.BLUE);
		frm.add(lblUserType);

		cmbUser = new JComboBox<Object>(arr);
		cmbUser.setBounds(220, 80, 250, 30);
		cmbUser.setFont(f);
		cmbUser.setSelectedIndex(-1);
		cmbUser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cmbUser.setBackground(Color.WHITE);
		cmbUser.setForeground(Color.BLACK);
		frm.add(cmbUser);

		lblId = new JLabel("USER ID");
		lblId.setBounds(20, 120, 100, 30);
		lblId.setFont(f);
		lblId.setForeground(Color.BLUE);
		frm.add(lblId);

		txtId = new JTextField();
		txtId.setBounds(220, 120, 250, 30);
		txtId.setFont(f);
		txtId.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		txtId.setForeground(Color.BLACK);
		txtId.addFocusListener(this);
		frm.add(txtId);

		lblName = new JLabel("NAME");
		lblName.setBounds(20, 160, 100, 30);
		lblName.setFont(f);
		lblName.setForeground(Color.BLUE);
		frm.add(lblName);

		txtName = new JLabel();
		txtName.setBounds(220, 160, 250, 30);
		txtName.setFont(f);
		txtName.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		txtName.setForeground(Color.BLACK);
		frm.add(txtName);

		lblsec_question = new JLabel("SECURITY QUESTION");
		lblsec_question.setBounds(20, 200, 180, 30);
		lblsec_question.setFont(f);
		lblsec_question.setForeground(Color.BLUE);
		frm.add(lblsec_question);

		lblcmbSecQueston = new JLabel();
		lblcmbSecQueston.setBounds(220, 200, 250, 30);
		lblcmbSecQueston.setFont(f);
		lblcmbSecQueston.setForeground(Color.BLACK);
		frm.add(lblcmbSecQueston);

		lblAnswer = new JLabel("ANSWER");
		lblAnswer.setBounds(20, 240, 100, 30);
		lblAnswer.setFont(f);
		lblAnswer.setForeground(Color.BLUE);
		frm.add(lblAnswer);

		txtSec_Answer = new JTextField();
		txtSec_Answer.setBounds(220, 240, 250, 30);
		txtSec_Answer.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		txtSec_Answer.setFont(f);
		frm.add(txtSec_Answer);

		lblPassword = new JLabel("PASSWORD");
		lblPassword.setBounds(20, 280, 100, 30);
		lblPassword.setFont(f);
		frm.add(lblPassword);
		lblPassword.setForeground(Color.BLUE);

		txtPassword = new JPasswordField();
		txtPassword.setBounds(220, 280, 250, 30);
		txtPassword.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		txtPassword.setFont(f);
		txtPassword.setForeground(Color.BLACK);
		frm.add(txtPassword);

		lblConfirmPass = new JLabel("CONFIRM PASSWORD");
		lblConfirmPass.setBounds(20, 320, 180, 30);
		lblConfirmPass.setFont(f);
		lblConfirmPass.setForeground(Color.BLUE);
		frm.add(lblConfirmPass);

		txtConfirmPass = new JPasswordField();
		txtConfirmPass.setBounds(220, 320, 250, 30);
		txtConfirmPass.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		txtConfirmPass.setFont(f);
		txtConfirmPass.setForeground(Color.BLACK);
		frm.add(txtConfirmPass);

		btnChangePass = new JButton("CHANGE PASSWORD");
		btnChangePass.setBounds(10, 365, 225, 35);
		btnChangePass.setFont(f);
		btnChangePass.setBackground(Color.decode("#88d2f6"));
		btnChangePass.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(btnChangePass);
		btnChangePass.addActionListener(this);

		btnCancel = new JButton("CANCEL");
		btnCancel.setBounds(250, 365, 225, 35);
		btnCancel.setFont(f);
		btnCancel.setBackground(Color.decode("#88d2f6"));
		btnCancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frm.add(btnCancel);
		btnCancel.addActionListener(this);

		frm.setVisible(true);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource().equals(btnChangePass)) 
		{
			if (frmValidate()) 
			{
				ForgetPasswordOperation fpo = new ForgetPasswordOperation(txtId.getText(), txtSec_Answer.getText(), txtConfirmPass.getText());
				if (strongPassword(txtPassword.getText())) 
				{
					if (cmbUser.getSelectedItem().equals(""))
					{
						if (fpo.admin_answerValidate()) 
						{
							if (fpo.admin_updatePassword()) 
							{
								JOptionPane.showMessageDialog(null, "Password Changed");
								new Login();
							}
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Answer MisMatch");
						}
					} 
					else if (cmbUser.getSelectedItem().equals("Receptionist"))
					{
						if (fpo.doc_answerValidate()) 
						{
							if (fpo.doc_updatePassword()) 
							{
								JOptionPane.showMessageDialog(null, "Password Updated");
								new Login();
							}
						} 
						else 
						{
							JOptionPane.showMessageDialog(null, "Answer MisMatch");
						}
					} 
					else if (cmbUser.getSelectedItem().equals("Doctor")) 
					{
						if (fpo.doc_answerValidate()) 
						{
							if (fpo.doc_updatePassword()) 
							{
								JOptionPane.showMessageDialog(null, "Password Updated");
								new Login();
							}
						} 
						else 
						{
							JOptionPane.showMessageDialog(null, "Answer MisMatch");
						}
					} 
					else if (cmbUser.getSelectedItem().equals("Employee")) 
					{
						if (fpo.doc_answerValidate()) 
						{
							if (fpo.doc_updatePassword()) 
							{
								JOptionPane.showMessageDialog(null, "Password Updated");
								new Login();
							}
						} 
						else 
						{
							JOptionPane.showMessageDialog(null, "Answer MisMatch");
						}
					}
				} 
				else 
				{
					JOptionPane.showMessageDialog(null,"week password!password must contain capital letter,special symbol & digit[A/0/@]");
					txtPassword.setText("");
					txtConfirmPass.setText("");
					txtPassword.grabFocus();
				}
			}
		} 
		else if (e.getSource().equals(btnCancel)) 
		{
			frm.dispose();
			new Login();
		}
	}

	@Override
	public void focusGained(FocusEvent arg0)
	{
		
	}

	@Override
	public void focusLost(FocusEvent e) 
	{
		txtId.setEditable(false);
		cmbUser.setEditable(false);
		if (cmbUser.getSelectedIndex() == -1) 
		{
			if (txtId.getText().equals("")) 
			{

			} else 
			{
				if (e.getSource().equals(txtId)) 
				{
					RegisterOperation ro = new RegisterOperation(txtId.getText());
					if (cmbUser.getSelectedIndex() == -1) 
					{
						if (ro.admin_userValidateforFP())
						{
							JOptionPane.showMessageDialog(null, "USER NOT EXIST");
						} 
						else 
						{
							ForgetPasswordOperation fpo = new ForgetPasswordOperation(txtId.getText());
							txtName.setText(fpo.admin_getName());
							lblcmbSecQueston.setText(fpo.admin_getSecQuestion());
						}
					}
				}
			}
		} 
		else 
		{
			RegisterOperation ro = new RegisterOperation(txtId.getText());
			if (cmbUser.getSelectedItem().equals("Receptionist")) 
			{
				if (ro.rec_userValidateforFP()) 
				{
					JOptionPane.showMessageDialog(null, "USER NOT EXIST");
				} 
				else 
				{
					ForgetPasswordOperation fpo = new ForgetPasswordOperation(txtId.getText());
					txtName.setText(fpo.rec_getName());
					lblcmbSecQueston.setText(fpo.admin_getSecQuestion());
				}
			} 
			else if (cmbUser.getSelectedItem().equals("Doctor"))
			{
				if (ro.doc_userValidateforFP()) 
				{
					JOptionPane.showMessageDialog(null, "USER NOT EXIST");
				} 
				else 
				{
					ForgetPasswordOperation fpo = new ForgetPasswordOperation(txtId.getText());
					txtName.setText(fpo.doc_getName());
					lblcmbSecQueston.setText(fpo.doc_getSecQuestion());
				}
			} 
			else if (cmbUser.getSelectedItem().equals("Employee")) 
			{
				if (ro.emp_userValidateforFP()) 
				{
					JOptionPane.showMessageDialog(null, "USER NOT EXIST");
				}
				else 
				{
					ForgetPasswordOperation fpo = new ForgetPasswordOperation(txtId.getText());
					txtName.setText(fpo.emp_getName());
					lblcmbSecQueston.setText(fpo.emp_getSecQuestion());
				}
			}
		}

	}

	@SuppressWarnings("deprecation")
	public Boolean frmValidate() 
	{
		Boolean flag = true;
		if (txtSec_Answer.getText().equals("")) 
		{
			JOptionPane.showMessageDialog(null, "ENTER SECURITY ANSWER");
			txtSec_Answer.grabFocus();
			flag = false;
		} 
		else if (txtPassword.getText().equals(""))
		{
			JOptionPane.showMessageDialog(null, "ENTER NEW PASSWORD");
			txtPassword.grabFocus();
			flag = false;
		} 
		else if (txtConfirmPass.getText().equals(""))
		{
			JOptionPane.showMessageDialog(null, "ENTER CONFIRMATION NAME");
			txtConfirmPass.grabFocus();
			flag = false;
		} 
		else if (!(txtPassword.getText().equals(txtConfirmPass.getText()))) 
		{
			JOptionPane.showMessageDialog(null, "password not matched");
			txtPassword.setText("");
			txtConfirmPass.setText("");
			txtPassword.grabFocus();
			flag = false;
		}
		return flag;
	}

	public boolean strongPassword(String pass)
	{
		flag = false;
		int spacecount, digitcount, capcount;
		spacecount = digitcount = capcount = 0;
		char[] ch = pass.toCharArray();
		for (char c : ch) 
		{
			if (c >= 65 && c <= 90)
			{
				capcount++;
			}
			if (c > 48 && c <= 57)
			{
				digitcount++;
			}
			if (!(c >= 48 && c <= 57) && !(c >= 65 && c <= 90) && !(c >= 97 && c <= 122)) 
			{
				spacecount++;
			}
			if (spacecount > 0 && digitcount > 0 && capcount > 0)
			{
				flag = true;
			}
		}
		return flag;
	}
}

public class ForgetPassword 
{
	ForgetPass fp = new ForgetPass();
}