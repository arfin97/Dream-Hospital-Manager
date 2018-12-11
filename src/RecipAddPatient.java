import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;

public class RecipAddPatient extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldAddPatientId;
	private JTextField textFieldAddPatientName;
	private JTextField textFieldAddPatientDateIn;
	private JTextField textFieldAddPatientAge;
	private JTextField textFieldAddPatientUsername;
	private JPasswordField passwordFieldAddPatient;
	Border border = BorderFactory.createLineBorder(Color.BLACK);
	public String gender; //Getting by radio button
	public String username; //Getting by querying
	public String myDoctorNameForId; //Getting from combobox
	public String myDoctorId; //Getting from combobox
	JComboBox comboBoxRoomSelect;
	JComboBox comboBoxSelectDoctor;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RecipAddPatient frame = new RecipAddPatient();
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
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField textFieldDateOut;
	private JTextField textFieldPatientCondition;
	public RecipAddPatient() {
		setTitle("Add Patient.");
		conn = SqliteConnection.dbConnector();
		setBounds(100, 100, 945, 594);
		////////////////////////////////////////////////////////////////////////////////////
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
		
		
		
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 204, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAge = new JLabel("Age:");
		lblAge.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAge.setBounds(75, 321, 97, 25);
		contentPane.add(lblAge);
		
		JLabel label_2 = new JLabel("Room no:");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_2.setBounds(75, 204, 86, 25);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("Date In:");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_3.setBounds(76, 133, 74, 25);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("Name:");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_4.setBounds(75, 91, 59, 25);
		contentPane.add(label_4);
		
		JLabel label_5 = new JLabel("ID:");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_5.setBounds(75, 53, 30, 27);
		contentPane.add(label_5);
		
		textFieldAddPatientId = new JTextField();
		textFieldAddPatientId.setText("  WILL BE AUTOMATICALLY ASSIGNED.");
		textFieldAddPatientId.setEditable(false);
		textFieldAddPatientId.setColumns(10);
		textFieldAddPatientId.setBounds(191, 53, 220, 27);
		contentPane.add(textFieldAddPatientId);
		
		textFieldAddPatientName = new JTextField();
		textFieldAddPatientName.setColumns(10);
		textFieldAddPatientName.setBounds(191, 91, 220, 27);
		contentPane.add(textFieldAddPatientName);
		
		textFieldAddPatientDateIn = new JTextField();
		textFieldAddPatientDateIn.setColumns(10);
		textFieldAddPatientDateIn.setBounds(190, 127, 222, 27);
		contentPane.add(textFieldAddPatientDateIn);
		
		textFieldAddPatientAge = new JTextField();
		textFieldAddPatientAge.setColumns(10);
		textFieldAddPatientAge.setBounds(191, 322, 220, 27);
		contentPane.add(textFieldAddPatientAge);
		
		JLabel lblSex = new JLabel("Gender:");
		lblSex.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSex.setBounds(76, 355, 97, 25);
		contentPane.add(lblSex);
		
		JTextArea textAreaAddPatientPrescription = new JTextArea();
		textAreaAddPatientPrescription.setLineWrap(true);
		textAreaAddPatientPrescription.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		textAreaAddPatientPrescription.setBounds(582, 88, 294, 123);
		contentPane.add(textAreaAddPatientPrescription);
		
		JLabel lblPrescription = new JLabel("Prescription");
		lblPrescription.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPrescription.setBounds(582, 53, 104, 25);
		contentPane.add(lblPrescription);
		
		JTextArea textAreaAddPatientMessage = new JTextArea();
		textAreaAddPatientMessage.setLineWrap(true);
		textAreaAddPatientMessage.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		textAreaAddPatientMessage.setBounds(582, 259, 294, 172);
		contentPane.add(textAreaAddPatientMessage);
		
		JLabel lblMessage = new JLabel("Message");
		lblMessage.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblMessage.setBounds(582, 222, 103, 37);
		contentPane.add(lblMessage);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String query1 = "INSERT INTO Patient (name, room, age, sex, medicine, message, username, password, dayin, dayout, condition, mydoctor) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
					PreparedStatement pst = conn.prepareStatement(query1);
					pst.setString(1, textFieldAddPatientName.getText());
					pst.setString(2, comboBoxRoomSelect.getSelectedItem().toString());
					pst.setString(3, textFieldAddPatientAge.getText());
					pst.setString(4, gender);
					pst.setString(5, textAreaAddPatientPrescription.getText());
					pst.setString(6, textAreaAddPatientMessage.getText());
					pst.setString(7, textFieldAddPatientUsername.getText());
					pst.setString(8, passwordFieldAddPatient.getText());
					pst.setString(9, textFieldAddPatientDateIn.getText());
					pst.setString(10, textFieldDateOut.getText());
					pst.setString(11, textFieldPatientCondition.getText());
					pst.setString(12, myDoctorId);
					pst.execute();
					
					username = textFieldAddPatientUsername.getText();
					
				}catch(Exception e1){
					JOptionPane.showMessageDialog(null, e1);
				}finally{
					textFieldAddPatientName.setText("");
					textFieldAddPatientAge.setText("");
					textAreaAddPatientPrescription.setText("");
					textAreaAddPatientMessage.setText("");
					passwordFieldAddPatient.setText("");
					textFieldAddPatientUsername.setText("");
					JOptionPane.showMessageDialog(null, "Patient Admitted. ID: " + getPatientId());
				}
				
				////////////////////Query to occupy room////////////////////////////////
				try{
					String query1 = "UPDATE Room SET occupied = ? WHERE id = ?";
					PreparedStatement pst = conn.prepareStatement(query1);
					pst.setInt(1, 1);
					pst.setString(2, comboBoxRoomSelect.getSelectedItem().toString());
					pst.execute();
					
				}catch(Exception e1){
					e1.printStackTrace();
				}
				fillComboBox();
			}
		});
		btnSubmit.setBounds(109, 477, 742, 67);
		contentPane.add(btnSubmit);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUsername.setBounds(76, 394, 97, 25);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPassword.setBounds(75, 425, 97, 25);
		contentPane.add(lblPassword);
		
		textFieldAddPatientUsername = new JTextField();
		textFieldAddPatientUsername.setColumns(10);
		textFieldAddPatientUsername.setBounds(191, 394, 220, 22);
		contentPane.add(textFieldAddPatientUsername);
		
		passwordFieldAddPatient = new JPasswordField();
		passwordFieldAddPatient.setBounds(190, 430, 220, 22);
		contentPane.add(passwordFieldAddPatient);
		
		JRadioButton rdbtnMale = new JRadioButton("Male");
		rdbtnMale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gender = "Male";
			}
		});
		buttonGroup.add(rdbtnMale);
		rdbtnMale.setBounds(191, 360, 74, 23);
		contentPane.add(rdbtnMale);
		
		JRadioButton rdbtnFemale = new JRadioButton("Female");
		rdbtnFemale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gender = "Female";
			}
		});
		buttonGroup.add(rdbtnFemale);
		rdbtnFemale.setBounds(282, 360, 109, 23);
		contentPane.add(rdbtnFemale);
		
		comboBoxRoomSelect = new JComboBox();
		comboBoxRoomSelect.setBounds(191, 210, 220, 20);
		contentPane.add(comboBoxRoomSelect);
		
		JLabel lblDateIn = new JLabel("Date Out:");
		lblDateIn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDateIn.setBounds(76, 168, 88, 25);
		contentPane.add(lblDateIn);
		
		textFieldDateOut = new JTextField();
		textFieldDateOut.setColumns(10);
		textFieldDateOut.setBounds(190, 162, 222, 27);
		contentPane.add(textFieldDateOut);
		
		JLabel lblCondition = new JLabel("Condition: ");
		lblCondition.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCondition.setBounds(75, 282, 97, 25);
		contentPane.add(lblCondition);
		
		textFieldPatientCondition = new JTextField();
		textFieldPatientCondition.setColumns(10);
		textFieldPatientCondition.setBounds(191, 284, 220, 27);
		contentPane.add(textFieldPatientCondition);
		
		JLabel lblMyDoctor = new JLabel("My Doctor");
		lblMyDoctor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMyDoctor.setBounds(75, 240, 97, 25);
		contentPane.add(lblMyDoctor);
		
		comboBoxSelectDoctor = new JComboBox();
		comboBoxSelectDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myDoctorNameForId = (String) comboBoxSelectDoctor.getSelectedItem().toString();
				try{
					String query = "SELECT * FROM Doctor WHERE name = ?";
					PreparedStatement pst = conn.prepareStatement(query);
					pst.setString(1, myDoctorNameForId);
					ResultSet rs = pst.executeQuery();
					if(rs.next()){
						myDoctorId = rs.getString("id");
					}
				}catch(Exception e1){
					e1.printStackTrace();
				}
				System.out.println(myDoctorId);
			}
		});
		comboBoxSelectDoctor.setBounds(191, 241, 220, 20);
		contentPane.add(comboBoxSelectDoctor);
		
		
		
		fillComboBox();
		fillComboBoxDoctor();
		
		
	}
	//////////////////////////////////////////////////MY FUNCTIONS////////////////////////////////////////////////////////
	public String getPatientId(){
		String id = "NULL";
		try {
			String query = "SELECT * FROM Patient WHERE username = ?";
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setString(1, username);
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				id = rs.getString("id");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return id;
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void fillComboBox(){
		try{
			String query1 = "SELECT * FROM Room";
			PreparedStatement pst = conn.prepareStatement(query1);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()){
				if(rs.getInt("occupied") != 1){
				 comboBoxRoomSelect.addItem(rs.getString("id"));
				}
			}
		}catch(Exception e1){
			JOptionPane.showMessageDialog(null, e1);
		}
		
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void fillComboBoxDoctor(){
		try{
			String query1 = "SELECT * FROM Doctor";
			PreparedStatement pst = conn.prepareStatement(query1);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()){
					comboBoxSelectDoctor.addItem(rs.getString("name"));
			}
		}catch(Exception e1){
			e1.printStackTrace();
		}
		
	}
}
