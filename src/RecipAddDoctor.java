import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JToggleButton;
import javax.swing.JSpinner;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class RecipAddDoctor extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldAddDoctorId;
	private JTextField textFieldAddDoctorName;
	private JTextField textFieldAddDoctorDesignation;
	private JTextField textFieldAddDoctorDegrees;
	private JTextField textFieldAddDoctorUsername;
	private JTextField textFieldAddDoctorPassword;
	Border border = BorderFactory.createLineBorder(Color.BLACK);
	public String username;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RecipAddDoctor frame = new RecipAddDoctor();
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
	public RecipAddDoctor() {
		conn = SqliteConnection.dbConnector();
		setTitle("Add Doctor");
		setBounds(100, 100, 931, 527);
		////////////////////////////////////////////////////////////////CLOSING PROPERLY////////////////////////////////
		addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
            	try { conn.close(); } catch (SQLException e1) { e1.printStackTrace(); } 
            	ReceptionistPage frame = new ReceptionistPage();
				frame.setVisible(true);
                System.out.println("Add doctor page Closed");
                e.getWindow().dispose();
            }
        });
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("ID:");
		label.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label.setBounds(76, 106, 42, 37);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("Name:");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_1.setBounds(76, 154, 88, 37);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("Designation:");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_2.setBounds(76, 202, 120, 37);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("Degrees:");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_3.setBounds(76, 250, 94, 37);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("Address :");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_4.setBounds(575, 81, 94, 37);
		contentPane.add(label_4);
		
		JLabel label_5 = new JLabel("About me:");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_5.setBounds(586, 249, 103, 37);
		contentPane.add(label_5);
		
		JLabel lblDegrees = new JLabel("Username:");
		lblDegrees.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDegrees.setBounds(76, 298, 97, 25);
		contentPane.add(lblDegrees);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPassword.setBounds(76, 346, 91, 25);
		contentPane.add(lblPassword);
		
		textFieldAddDoctorId = new JTextField("  WILL BE AUTOMATICALLY ASSIGNED.");
		textFieldAddDoctorId.setEditable(false);
		textFieldAddDoctorId.setBounds(196, 106, 300, 25);
		contentPane.add(textFieldAddDoctorId);
		textFieldAddDoctorId.setColumns(10);
		
		textFieldAddDoctorName = new JTextField();
		textFieldAddDoctorName.setColumns(10);
		textFieldAddDoctorName.setBounds(196, 166, 300, 25);
		contentPane.add(textFieldAddDoctorName);
		
		textFieldAddDoctorDesignation = new JTextField();
		textFieldAddDoctorDesignation.setColumns(10);
		textFieldAddDoctorDesignation.setBounds(196, 214, 300, 25);
		contentPane.add(textFieldAddDoctorDesignation);
		
		textFieldAddDoctorDegrees = new JTextField();
		textFieldAddDoctorDegrees.setColumns(10);
		textFieldAddDoctorDegrees.setBounds(196, 262, 300, 25);
		contentPane.add(textFieldAddDoctorDegrees);
		
		textFieldAddDoctorUsername = new JTextField();
		textFieldAddDoctorUsername.setColumns(10);
		textFieldAddDoctorUsername.setBounds(196, 304, 300, 25);
		contentPane.add(textFieldAddDoctorUsername);
		
		textFieldAddDoctorPassword = new JTextField();
		textFieldAddDoctorPassword.setColumns(10);
		textFieldAddDoctorPassword.setBounds(196, 346, 300, 25);
		contentPane.add(textFieldAddDoctorPassword);
		
		JTextArea textAreaAddDoctorAddress = new JTextArea();
		textAreaAddDoctorAddress.setBounds(575, 116, 294, 123);
		textAreaAddDoctorAddress.setLineWrap(true);
		textAreaAddDoctorAddress.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		contentPane.add(textAreaAddDoctorAddress);
		
		JTextArea textAreaAddDoctorAboutme = new JTextArea();
		textAreaAddDoctorAboutme.setBounds(575, 287, 294, 123);
		textAreaAddDoctorAboutme.setLineWrap(true);
		textAreaAddDoctorAboutme.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		contentPane.add(textAreaAddDoctorAboutme);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String query1 = "INSERT INTO Doctor (name, designation, degree, username, password, address, aboutme) VALUES (?, ?, ?, ?, ?, ?, ?)";
					PreparedStatement pst = conn.prepareStatement(query1);
					pst.setString(1, textFieldAddDoctorName.getText());
					pst.setString(2, textFieldAddDoctorDesignation.getText());
					pst.setString(3, textFieldAddDoctorDegrees.getText());
					pst.setString(4, textFieldAddDoctorUsername.getText());
					pst.setString(5, textFieldAddDoctorPassword.getText());
					pst.setString(6, textAreaAddDoctorAddress.getText());
					pst.setString(7, textAreaAddDoctorAboutme.getText());
					pst.execute();
					
					username = textFieldAddDoctorUsername.getText();
					
					
				}catch(Exception e1){
					JOptionPane.showMessageDialog(null, e1);
				}finally{
					textFieldAddDoctorName.setText("");
					textFieldAddDoctorDesignation.setText("");
					textFieldAddDoctorDegrees.setText("");
					textFieldAddDoctorUsername.setText("");
					textFieldAddDoctorPassword.setText("");
					textAreaAddDoctorAddress.setText("");
					textAreaAddDoctorAboutme.setText("");
					
					JOptionPane.showMessageDialog(null, "New Doctor Assigned. ID: " + getDoctorId());
				}
			}
		});
		btnSubmit.setBounds(76, 398, 435, 48);
		contentPane.add(btnSubmit);
	}
	
	
	//////////////////////////////////////////////////MY FUNCTIONS////////////////////////////////////////////////////////
	public String getDoctorId(){
		String id = "NULL";
		try {
			String query = "SELECT * FROM Doctor WHERE username = ?";
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setString(1, username);
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				id = rs.getString("id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}
}
