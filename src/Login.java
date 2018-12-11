import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import java.awt.CardLayout;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Component;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class Login {

	public JFrame frame;
	private JTextField textFieldDoctorLogin;
	private JPasswordField passwordFieldDoctorLogin;
	private JTextField textField;
	private JPasswordField passwordField;
	private JTextField textField_1;
	private JPasswordField passwordField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	Connection conn;
	private void initialize() {
		conn = SqliteConnection.dbConnector();
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel LoginPanel = new JPanel();
		LoginPanel.setBounds(337, 0, 647, 561);
		frame.getContentPane().add(LoginPanel);
		LoginPanel.setLayout(new CardLayout(0, 0));
		
		JPanel DoctorLoginPanel = new JPanel();
		LoginPanel.add(DoctorLoginPanel, "name_260445108012210");
		DoctorLoginPanel.setLayout(null);
		
		textFieldDoctorLogin = new JTextField();
		textFieldDoctorLogin.setBounds(244, 175, 218, 34);
		DoctorLoginPanel.add(textFieldDoctorLogin);
		textFieldDoctorLogin.setColumns(10);
		
		JButton btnLoginDoctor = new JButton("Login");
		btnLoginDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					String query = "SELECT * FROM Doctor WHERE username = ? AND password = ?";
					PreparedStatement pst = conn.prepareStatement(query);
					pst.setString(1, textFieldDoctorLogin.getText());
					pst.setString(2, passwordFieldDoctorLogin.getText());
					ResultSet rs = pst.executeQuery();
//					int count = 0;
//					while(rs.next()){
//						count+=1;
//					}
					if(rs.next()){
//						JOptionPane.showMessageDialog(null, "Log in successful");
						DoctorPage.DoctorUsername = textFieldDoctorLogin.getText();
						DoctorPage.DoctorId = rs.getString("id");
						
						DoctorPage frame = new DoctorPage();
						frame.setVisible(true);
//						window.frame.setVisible(false);
						//Second Frame;
					}
					else{
						JOptionPane.showMessageDialog(null, "Log in unsuccessful");
					}
				}
				catch(Exception e){
					JOptionPane.showMessageDialog(null, "Error Occured");
				}
			}
		});
		btnLoginDoctor.setBounds(244, 296, 218, 34);
		DoctorLoginPanel.add(btnLoginDoctor);
		
		passwordFieldDoctorLogin = new JPasswordField();
		passwordFieldDoctorLogin.setBounds(244, 245, 218, 34);
		DoctorLoginPanel.add(passwordFieldDoctorLogin);
		
		JLabel lblPasswordDoctor = new JLabel("Password");
		lblPasswordDoctor.setBounds(329, 220, 64, 14);
		DoctorLoginPanel.add(lblPasswordDoctor);
		
		JLabel lblUsernameDoctor = new JLabel("Username");
		lblUsernameDoctor.setBounds(329, 148, 64, 14);
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
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(243, 173, 218, 34);
		PatientLoginPanel.add(textField);
		
		JButton button = new JButton("Login");
		button.setBounds(243, 294, 218, 34);
		PatientLoginPanel.add(button);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(243, 243, 218, 34);
		PatientLoginPanel.add(passwordField);
		
		JLabel label = new JLabel("Password");
		label.setBounds(328, 218, 64, 14);
		PatientLoginPanel.add(label);
		
		JLabel lblUsernamePatient = new JLabel("Username");
		lblUsernamePatient.setBounds(328, 147, 64, 14);
		PatientLoginPanel.add(lblUsernamePatient);
		
		JLabel lblBackground = new JLabel("Background");
		lblBackground.setIcon(new ImageIcon(Login.class.getResource("/Image/Login/PatientLoginBackground.jpg")));
		lblBackground.setBounds(0, 0, 647, 561);
		PatientLoginPanel.add(lblBackground);
		
		JPanel ReceptionistLoginPanel = new JPanel();
		LoginPanel.add(ReceptionistLoginPanel, "name_260458778344573");
		ReceptionistLoginPanel.setLayout(null);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(241, 193, 218, 34);
		ReceptionistLoginPanel.add(textField_1);
		
		JButton button_1 = new JButton("Login");
		button_1.setBounds(241, 314, 218, 34);
		ReceptionistLoginPanel.add(button_1);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(241, 263, 218, 34);
		ReceptionistLoginPanel.add(passwordField_1);
		
		JLabel label_1 = new JLabel("Password");
		label_1.setBounds(326, 238, 64, 14);
		ReceptionistLoginPanel.add(label_1);
		
		JLabel label_2 = new JLabel("Username");
		label_2.setBounds(326, 166, 64, 14);
		ReceptionistLoginPanel.add(label_2);
		
		JLabel lblBackground_1 = new JLabel("Background");
		lblBackground_1.setIcon(new ImageIcon(Login.class.getResource("/Image/Login/ReciptioninsLoginBackground.jpg")));
		lblBackground_1.setBounds(0, 0, 647, 561);
		ReceptionistLoginPanel.add(lblBackground_1);
		
		JButton btnDoctor = new JButton("Doctasdfsadfsdafsdafasfor");
		btnDoctor.setForeground(Color.BLACK);
		btnDoctor.setBackground(Color.WHITE);
		btnDoctor.setBorder(compound);
		btnDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DoctorLoginPanel.setVisible(true);
				PatientLoginPanel.setVisible(false);
				ReceptionistLoginPanel.setVisible(false);
				////Temporary
				frame.dispose();
				DoctorPage frame = new DoctorPage();
				frame.setVisible(true);
			}
		});
		btnDoctor.setBounds(103, 155, 150, 60);
		frame.getContentPane().add(btnDoctor);
		
		JButton btnPatient = new JButton("Patsdfsdfdsfient");
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
		btnPatient.setBounds(103, 235, 150, 60);
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
		btnReciptionist.setBounds(103, 315, 150, 60);
		frame.getContentPane().add(btnReciptionist);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Arial", Font.PLAIN, 18));
		lblLogin.setBounds(146, 95, 76, 49);
		frame.getContentPane().add(lblLogin);
		
		JLabel Background_2 = new JLabel();
		Background_2.setIcon(new ImageIcon(Login.class.getResource("/Image/Login/LoginBackground.jpg")));
		Background_2.setText("Background");
		Background_2.setForeground(new Color(218, 112, 214));
		Background_2.setBackground(new Color(218, 165, 32));
		Background_2.setBounds(0, 0, 339, 561);
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
