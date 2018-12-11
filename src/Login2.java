import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class Login2 extends JFrame {
	
	private JPanel contentPane;
	public JFrame frame;
	private JTextField textFieldDoctorLogin;
	private JPasswordField passwordFieldDoctorLogin;
	private JTextField textFieldPatientLoginUsername;
	private JPasswordField passwordFieldPatientLoginPassword;
	private JTextField textFieldRecipUsername;
	private JPasswordField passwordFieldReciptPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login2 frame = new Login2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	Connection conn;
	public Login2() {
		conn = SqliteConnection.dbConnector();
		setTitle("Login");
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		JPanel LoginPanel = new JPanel();
		LoginPanel.setBounds(337, 0, 647, 561);
		frame.getContentPane().add(LoginPanel);
		LoginPanel.setLayout(new CardLayout(0, 0));
		
		JPanel DoctorLoginPanel = new JPanel();
		LoginPanel.add(DoctorLoginPanel, "name_260445108012210");
		DoctorLoginPanel.setLayout(null);
		
		textFieldDoctorLogin = new JTextField();
		textFieldDoctorLogin.setBounds(238, 193, 218, 34);
		DoctorLoginPanel.add(textFieldDoctorLogin);
		textFieldDoctorLogin.setColumns(10);
		/////////////////////////////////////////////DOCTOR LOGIN BUTTON/////////////////////////////////////////////////////////////
		JButton btnLoginDoctor = new JButton("Login");
		btnLoginDoctor.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "DoClick");
		btnLoginDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					String query = "SELECT * FROM Doctor WHERE username = ? AND password = ?";
					PreparedStatement pst = conn.prepareStatement(query);
					pst.setString(1, textFieldDoctorLogin.getText());
					pst.setString(2, passwordFieldDoctorLogin.getText());
					ResultSet rs = pst.executeQuery();

					if(rs.next()){
						DoctorPage.DoctorUsername = textFieldDoctorLogin.getText();
						DoctorPage.DoctorId = rs.getString("id");
						conn.close();
						frame.dispose();
						DoctorPage frame = new DoctorPage();
						frame.setVisible(true);
					}
					else{
						JOptionPane.showMessageDialog(null, "Log in failed");
					}
				}
				catch(Exception e){
					JOptionPane.showMessageDialog(null, "Error Occured");
				}
			}
		});
		btnLoginDoctor.setBounds(238, 314, 218, 34);
		DoctorLoginPanel.add(btnLoginDoctor);
		
		passwordFieldDoctorLogin = new JPasswordField();
		passwordFieldDoctorLogin.setBounds(238, 263, 218, 34);
		DoctorLoginPanel.add(passwordFieldDoctorLogin);
		
		JLabel lblPasswordDoctor = new JLabel("Password");
		lblPasswordDoctor.setBounds(323, 238, 64, 14);
		DoctorLoginPanel.add(lblPasswordDoctor);
		
		JLabel lblUsernameDoctor = new JLabel("Username");
		lblUsernameDoctor.setBounds(323, 166, 64, 14);
		DoctorLoginPanel.add(lblUsernameDoctor);
		
		JLabel lblNewLabel = new JLabel("Background");
		lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/Image/Login/DoctorPanelLogin.jpg")));
		lblNewLabel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		lblNewLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblNewLabel.setBounds(0, 0, 647, 561);
		DoctorLoginPanel.add(lblNewLabel);
		
		JPanel PatientLoginPanel = new JPanel();
		LoginPanel.add(PatientLoginPanel, "name_260456466582165");
		PatientLoginPanel.setLayout(null);
		
		textFieldPatientLoginUsername = new JTextField();
		textFieldPatientLoginUsername.setColumns(10);
		textFieldPatientLoginUsername.setBounds(238, 192, 218, 34);
		PatientLoginPanel.add(textFieldPatientLoginUsername);
		/////////////////////////////////////////////PATIENT LOGIN BUTTON/////////////////////////////////////////////////////////////
		JButton buttonPatientLogin = new JButton("Login");
		buttonPatientLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					String query = "SELECT * FROM Patient WHERE username = ? AND password = ?";
					PreparedStatement pst = conn.prepareStatement(query);
					pst.setString(1, textFieldPatientLoginUsername.getText());
					pst.setString(2, passwordFieldPatientLoginPassword.getText());
					ResultSet rs = pst.executeQuery();

					if(rs.next()){
						PatientPage.PatientUsername = textFieldPatientLoginUsername.getText();
						PatientPage.PatientId = rs.getString("id");
						PatientPage.PatientAssignedDoctor = rs.getString("mydoctor");
						
						conn.close();
						frame.dispose();
						PatientPage frame = new PatientPage();
						frame.setVisible(true);
					}
					else{
						JOptionPane.showMessageDialog(null, "Log in failed");
					}
				}
				catch(Exception e){
					JOptionPane.showMessageDialog(null, "Error Occured");
				}
			}
		});
		buttonPatientLogin.setBounds(238, 313, 218, 34);
		PatientLoginPanel.add(buttonPatientLogin);
		
		passwordFieldPatientLoginPassword = new JPasswordField();
		passwordFieldPatientLoginPassword.setBounds(238, 262, 218, 34);
		PatientLoginPanel.add(passwordFieldPatientLoginPassword);
		
		JLabel label = new JLabel("Password");
		label.setBounds(323, 237, 64, 14);
		PatientLoginPanel.add(label);
		
		JLabel lblUsernamePatient = new JLabel("Username");
		lblUsernamePatient.setBounds(323, 166, 64, 14);
		PatientLoginPanel.add(lblUsernamePatient);
		
		JLabel lblBackground = new JLabel("Background");
		lblBackground.setIcon(new ImageIcon(Login.class.getResource("/Image/Login/PatientLoginBackground.jpg")));
		lblBackground.setBounds(0, 0, 647, 561);
		PatientLoginPanel.add(lblBackground);
		
		JPanel ReceptionistLoginPanel = new JPanel();
		LoginPanel.add(ReceptionistLoginPanel, "name_260458778344573");
		ReceptionistLoginPanel.setLayout(null);
		
		textFieldRecipUsername = new JTextField();
		textFieldRecipUsername.setColumns(10);
		textFieldRecipUsername.setBounds(238, 193, 218, 34);
		ReceptionistLoginPanel.add(textFieldRecipUsername);
		/////////////////////////////////////////////RECIPTIONIST LOGIN BUTTON/////////////////////////////////////////////////////////////
		JButton button_1 = new JButton("Login");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					String query = "SELECT * FROM Receptionist WHERE username = ? AND password = ?";
					PreparedStatement pst = conn.prepareStatement(query);
					pst.setString(1, textFieldRecipUsername.getText());
					pst.setString(2, passwordFieldReciptPassword.getText());
					ResultSet rs = pst.executeQuery();

					if(rs.next()){
						conn.close();
						frame.dispose();
						ReceptionistPage frame = new ReceptionistPage();
						frame.setVisible(true);
					}
					else{
						JOptionPane.showMessageDialog(null, "Log in failed");
					}
				}
				catch(Exception e){
					JOptionPane.showMessageDialog(null, "Error Occured");
				}
			}
		});
		button_1.setBounds(238, 314, 218, 34);
		ReceptionistLoginPanel.add(button_1);
		
		passwordFieldReciptPassword = new JPasswordField();
		passwordFieldReciptPassword.setBounds(238, 263, 218, 34);
		ReceptionistLoginPanel.add(passwordFieldReciptPassword);
		
		JLabel label_1 = new JLabel("Password");
		label_1.setBounds(323, 238, 64, 14);
		ReceptionistLoginPanel.add(label_1);
		
		JLabel label_2 = new JLabel("Username");
		label_2.setBounds(323, 166, 64, 14);
		ReceptionistLoginPanel.add(label_2);
		
		JLabel lblBackground_1 = new JLabel("Background");
		lblBackground_1.setIcon(new ImageIcon(Login.class.getResource("/Image/Login/ReciptioninsLoginBackground.jpg")));
		lblBackground_1.setBounds(0, 0, 647, 561);
		ReceptionistLoginPanel.add(lblBackground_1);
		
		JButton btnDoctor = new JButton("Doctor");
		btnDoctor.setForeground(Color.BLACK);
		btnDoctor.setBackground(Color.WHITE);
		btnDoctor.setBorder(compound);
		btnDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DoctorLoginPanel.setVisible(true);
				PatientLoginPanel.setVisible(false);
				ReceptionistLoginPanel.setVisible(false);
			}
		});
		btnDoctor.setBounds(87, 177, 193, 60);
		frame.getContentPane().add(btnDoctor);
		
		JButton btnPatient = new JButton("Patient");
		btnPatient.setForeground(Color.BLACK);
		btnPatient.setBackground(Color.WHITE);
		btnPatient.setBorder(compound);
		btnPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PatientLoginPanel.setVisible(true);
				DoctorLoginPanel.setVisible(false);				
				ReceptionistLoginPanel.setVisible(false);
			}
		});
		btnPatient.setBounds(87, 257, 193, 60);
		frame.getContentPane().add(btnPatient);
		
		JButton btnReciptionist = new JButton("Receptionist");
		btnReciptionist.setForeground(Color.BLACK);
		btnReciptionist.setBackground(Color.WHITE);
		btnReciptionist.setBorder(compound);
		btnReciptionist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReceptionistLoginPanel.setVisible(true);
				PatientLoginPanel.setVisible(false);
				DoctorLoginPanel.setVisible(false);				
			}
		});
		btnReciptionist.setBounds(87, 337, 193, 60);
		frame.getContentPane().add(btnReciptionist);
		
		JLabel Background_2 = new JLabel();
		Background_2.setIcon(new ImageIcon(Login2.class.getResource("/Backgrounds/LoginImage.jpg")));
		Background_2.setText("Background");
		Background_2.setForeground(new Color(218, 112, 214));
		Background_2.setBackground(new Color(218, 165, 32));
		Background_2.setBounds(0, 0, 341, 561);
		frame.getContentPane().add(Background_2);
	}
	////////////////////////////////////////////////
	//Crating flat button.
	Border line = new LineBorder(Color.BLACK);
	Border margin = new EmptyBorder(5, 15, 5, 15);
	Border compound = new CompoundBorder(line, margin);
	 
	public JButton flatButton(String text) {
		  JButton button = new JButton(text);
		  button.setForeground(Color.BLACK);
		  button.setBackground(Color.WHITE);
		  Border line = new LineBorder(Color.BLACK);
		  Border margin = new EmptyBorder(5, 15, 5, 15);
		  Border compound = new CompoundBorder(line, margin);
		  button.setBorder(compound);
		  return button;
	}
}
