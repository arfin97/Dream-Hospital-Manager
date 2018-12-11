import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.SwingConstants;
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
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.ImageIcon;

public class PatientPage extends JFrame {
	
	public static String PatientUsername;
	public static String PatientId;
	public static String PatientAssignedDoctor;
	
	private JPanel contentPane;
	JTextArea textAreaPatientPagePrescription;
	JTextArea textAreaPatientPageDoctorAddress;
	private JTextField textFieldPatientId;
	private JTextField textFieldPatientName;
	private JTextField textFieldPatientDateIn;
	private JTextField textFieldPatientRoomNo;
	private JTextField textFieldPatientCondition;
	private JTextField textFieldPatientAssignedDoctorName;
	private JTextField textFieldPatientAssignedDoctorDesignation;
	private JTextField textFieldPatientAssignedDoctorPhone;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PatientPage frame = new PatientPage();
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
	public PatientPage() {
		conn = SqliteConnection.dbConnector();
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		
		////////////////////////////////////////////////////////////////CLOSING PROPERLY////////////////////////////////
		addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
            	try { conn.close(); } catch (SQLException e1) { e1.printStackTrace(); } 
            	
                System.out.println("Doctor page Closed");
                e.getWindow().dispose();
            }
        });
		/////////////////////////////////////////////////////////////////
		
		setBounds(100, 100, 1127, 658);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel PatientPageLablePrescription = new JLabel("Prescription");
		PatientPageLablePrescription.setFont(new Font("Tahoma", Font.PLAIN, 20));
		PatientPageLablePrescription.setBounds(83, 373, 104, 25);
		contentPane.add(PatientPageLablePrescription);
		
		JLabel PatientPageLableCondition = new JLabel("Condition: ");
		PatientPageLableCondition.setFont(new Font("Tahoma", Font.PLAIN, 20));
		PatientPageLableCondition.setBounds(83, 313, 97, 25);
		contentPane.add(PatientPageLableCondition);
		
		JLabel PatientPageLableRoomNo = new JLabel("Room no:");
		PatientPageLableRoomNo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		PatientPageLableRoomNo.setBounds(82, 261, 86, 25);
		contentPane.add(PatientPageLableRoomNo);
		
		JLabel PatientPageLableDateIn = new JLabel("Date In:");
		PatientPageLableDateIn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		PatientPageLableDateIn.setBounds(82, 208, 74, 25);
		contentPane.add(PatientPageLableDateIn);
		
		JLabel PatientPageLableName = new JLabel("Name:");
		PatientPageLableName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		PatientPageLableName.setBounds(82, 148, 59, 25);
		contentPane.add(PatientPageLableName);
		
		JLabel PatientPageLableId = new JLabel("ID:");
		PatientPageLableId.setBorder(null);
		PatientPageLableId.setFont(new Font("Tahoma", Font.PLAIN, 20));
		PatientPageLableId.setBounds(82, 93, 30, 27);
		contentPane.add(PatientPageLableId);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try { conn.close(); } catch (SQLException e1) { e1.printStackTrace(); } 
				Login2 frame = new Login2();
				frame.setVisible(true);
				dispose();
			}
		});
		btnLogout.setBounds(853, 472, 138, 23);
		contentPane.add(btnLogout);
		
		textAreaPatientPagePrescription = new JTextArea();
		textAreaPatientPagePrescription.setBounds(82, 418, 336, 153);
		textAreaPatientPagePrescription.setEditable(false);
		textAreaPatientPagePrescription.setHighlighter(null);
		textAreaPatientPagePrescription.setLineWrap(true);
		textAreaPatientPagePrescription.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		contentPane.add(textAreaPatientPagePrescription);
		
		JLabel lblQuickAccesss = new JLabel("Quick Accesss");
		lblQuickAccesss.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblQuickAccesss.setBounds(853, 54, 144, 25);
		contentPane.add(lblQuickAccesss);
		
		JLabel lblDetails = new JLabel("Details:");
		lblDetails.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDetails.setBounds(478, 374, 246, 37);
		contentPane.add(lblDetails);
		
		JLabel label_1 = new JLabel("Name:");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_1.setBounds(477, 167, 88, 37);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("Designation:");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_2.setBounds(477, 222, 120, 37);
		contentPane.add(label_2);
		
		textAreaPatientPageDoctorAddress = new JTextArea();
		textAreaPatientPageDoctorAddress.setEditable(false);
		textAreaPatientPageDoctorAddress.setHighlighter(null);
		textAreaPatientPageDoctorAddress.setLineWrap(true);
		textAreaPatientPageDoctorAddress.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		textAreaPatientPageDoctorAddress.setBounds(479, 419, 262, 143);
		contentPane.add(textAreaPatientPageDoctorAddress);
		
		JLabel lblPhone = new JLabel("Phone:");
		lblPhone.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPhone.setBounds(477, 276, 120, 37);
		contentPane.add(lblPhone);
		
		textFieldPatientId = new JTextField();
		textFieldPatientId.setEditable(false);
		textFieldPatientId.setBounds(198, 93, 220, 27);
		contentPane.add(textFieldPatientId);
		textFieldPatientId.setColumns(10);
		
		textFieldPatientName = new JTextField();
		textFieldPatientName.setEditable(false);
		textFieldPatientName.setColumns(10);
		textFieldPatientName.setBounds(198, 148, 220, 27);
		contentPane.add(textFieldPatientName);
		
		textFieldPatientDateIn = new JTextField();
		textFieldPatientDateIn.setEditable(false);
		textFieldPatientDateIn.setColumns(10);
		textFieldPatientDateIn.setBounds(196, 202, 222, 27);
		contentPane.add(textFieldPatientDateIn);
		
		textFieldPatientRoomNo = new JTextField();
		textFieldPatientRoomNo.setEditable(false);
		textFieldPatientRoomNo.setColumns(10);
		textFieldPatientRoomNo.setBounds(196, 264, 222, 27);
		contentPane.add(textFieldPatientRoomNo);
		
		textFieldPatientCondition = new JTextField();
		textFieldPatientCondition.setEditable(false);
		textFieldPatientCondition.setColumns(10);
		textFieldPatientCondition.setBounds(198, 313, 220, 27);
		contentPane.add(textFieldPatientCondition);
		
		textFieldPatientAssignedDoctorName = new JTextField();
		textFieldPatientAssignedDoctorName.setEditable(false);
		textFieldPatientAssignedDoctorName.setBounds(565, 167, 176, 35);
		contentPane.add(textFieldPatientAssignedDoctorName);
		textFieldPatientAssignedDoctorName.setColumns(10);
		
		textFieldPatientAssignedDoctorDesignation = new JTextField();
		textFieldPatientAssignedDoctorDesignation.setEditable(false);
		textFieldPatientAssignedDoctorDesignation.setColumns(10);
		textFieldPatientAssignedDoctorDesignation.setBounds(597, 222, 144, 35);
		contentPane.add(textFieldPatientAssignedDoctorDesignation);
		
		textFieldPatientAssignedDoctorPhone = new JTextField("+1-202-555-0132");
		textFieldPatientAssignedDoctorPhone.setEditable(false);
		textFieldPatientAssignedDoctorPhone.setColumns(10);
		textFieldPatientAssignedDoctorPhone.setBounds(565, 281, 176, 35);
		contentPane.add(textFieldPatientAssignedDoctorPhone);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setOpaque(false);
		panel.setForeground(Color.GREEN);
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBackground(SystemColor.menu);
		panel.setBounds(826, 90, 205, 308);
		contentPane.add(panel);
		
		JButton btnSendMessage = new JButton("Send Message");
		btnSendMessage.setBounds(34, 210, 138, 23);
		panel.add(btnSendMessage);
		
		JButton btnCallNurse = new JButton("Call Nurse");
		btnCallNurse.setBounds(34, 139, 138, 23);
		panel.add(btnCallNurse);
		
		JButton btnCallDoctor = new JButton("Call Doctor");
		btnCallDoctor.setBounds(34, 66, 138, 23);
		panel.add(btnCallDoctor);
		btnCallDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int action = JOptionPane.showConfirmDialog (null, "Do you want to call you doctor?", "Confirm" ,JOptionPane.YES_NO_OPTION);
				if(action == 0){
					JOptionPane.showMessageDialog(null, "Calling...", "Outoing", JOptionPane.CANCEL_OPTION);
				}
			}
		});
		btnCallNurse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Call has sent, Please wait for a few second.", "Outoing", JOptionPane.CANCEL_OPTION);
			}
		});
		btnSendMessage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try { conn.close(); } catch (SQLException e1) { e1.printStackTrace(); } 
				PatientSendMessage frame = new PatientSendMessage();
				frame.setVisible(true);
			}
		});
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setOpaque(false);
		panel_1.setForeground(Color.GREEN);
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Logout", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBackground(SystemColor.menu);
		panel_1.setBounds(820, 432, 214, 95);
		contentPane.add(panel_1);
		
		JLabel label_4 = new JLabel("");
		label_4.setIcon(new ImageIcon(PatientPage.class.getResource("/Backgrounds/Blue.png")));
		label_4.setBackground(new Color(153, 204, 255));
		label_4.setBounds(10, 21, 194, 63);
		panel_1.add(label_4);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setOpaque(false);
		panel_2.setForeground(Color.GREEN);
		panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Patient", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBackground(SystemColor.menu);
		panel_2.setBounds(60, 54, 379, 554);
		contentPane.add(panel_2);
		
		JLabel label_5 = new JLabel("");
		label_5.setBackground(new Color(153, 204, 255));
		label_5.setBounds(10, 22, 507, 275);
		panel_2.add(label_5);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setOpaque(false);
		panel_3.setForeground(Color.GREEN);
		panel_3.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Doctor", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_3.setBackground(SystemColor.menu);
		panel_3.setBounds(449, 54, 324, 554);
		contentPane.add(panel_3);
		
		JLabel label_6 = new JLabel("");
		label_6.setBackground(new Color(153, 204, 255));
		label_6.setBounds(10, 22, 507, 275);
		panel_3.add(label_6);
		
		JLabel lblAssignedDoctor = new JLabel("Assigned Doctor");
		lblAssignedDoctor.setBounds(91, 37, 144, 25);
		panel_3.add(lblAssignedDoctor);
		lblAssignedDoctor.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lblBg = new JLabel("bg");
		lblBg.setIcon(new ImageIcon(PatientPage.class.getResource("/Backgrounds/SteJoss.jpg")));
		lblBg.setBounds(0, 0, 1111, 619);
		contentPane.add(lblBg);
		/////////////////////////*******************************/////////////////////
		fetchData();
		fetchDoctorInfo();
		
	}
	////////////////////////////////////////////MY FUNCTIONS/////////////////////////////////////////////
	public void fetchData(){
		try{
			String query = "SELECT * FROM Patient WHERE id = ?";
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setString(1, PatientPage.PatientId);
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				textFieldPatientId.setText(rs.getString("id"));
				textFieldPatientName.setText(rs.getString("name"));
				textFieldPatientDateIn.setText(rs.getString("sex"));
				textFieldPatientRoomNo.setText(rs.getString("room"));
				textFieldPatientCondition.setText(rs.getString("condition"));
				textAreaPatientPagePrescription.setText(rs.getString("medicine"));
			}
			
		}catch(Exception e1){
			e1.printStackTrace();
		}
	}
	
	public void fetchDoctorInfo(){
		try{
			String query = "SELECT * FROM Doctor WHERE id = ?";
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setString(1, PatientPage.PatientAssignedDoctor);
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				textFieldPatientAssignedDoctorName.setText(rs.getString("name"));
				textFieldPatientAssignedDoctorDesignation.setText(rs.getString("designation"));
				textFieldPatientAssignedDoctorPhone.setText(rs.getString("username"));
				textAreaPatientPageDoctorAddress.setText(rs.getString("address"));
			}
			
		}catch(Exception e1){
			e1.printStackTrace();
		}
	}
}
