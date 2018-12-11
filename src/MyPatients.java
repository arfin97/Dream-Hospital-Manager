


import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.SystemColor;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.UIManager;
import javax.swing.ImageIcon;

public class MyPatients extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textFieldSearchPatient;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyPatients frame = new MyPatients();
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
	public MyPatients() {
		conn = SqliteConnection.dbConnector();
		////////////////////////////////////////////////////////////////CLOSING PROPERLY////////////////////////////////
		addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
            	try {
					conn.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            	DoctorPage frame = new DoctorPage();
				frame.setVisible(true);
                System.out.println("Closed");
                e.getWindow().dispose();
            }
        });
		/////////////////////////////////////////////////////////////////
		
		setBounds(100, 100, 1104, 619);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMyPatients = new JLabel("My Patients");
		lblMyPatients.setForeground(Color.WHITE);
		lblMyPatients.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblMyPatients.setBounds(61, 26, 235, 69);
		contentPane.add(lblMyPatients);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(50, 171, 981, 337);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JComboBox comboBoxSearchSelect = new JComboBox();
		comboBoxSearchSelect.setModel(new DefaultComboBoxModel(new String[] {"ID", "Name", "Age", "Codition", "Sex"}));
		comboBoxSearchSelect.setBounds(56, 131, 154, 29);
		contentPane.add(comboBoxSearchSelect);
		
		textFieldSearchPatient = new JTextField();
		textFieldSearchPatient.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try{
					String selection = (String) comboBoxSearchSelect.getSelectedItem().toString();
					String query = "SELECT id, name, age, sex, room, condition, medicine FROM Patient WHERE "+selection+" = ?";
					PreparedStatement pst = conn.prepareStatement(query);
					pst.setString(1, textFieldSearchPatient.getText());
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
				}catch(Exception e1){
					e1.printStackTrace();
				}
			}
		});
		textFieldSearchPatient.setBounds(234, 131, 198, 29);
		contentPane.add(textFieldSearchPatient);
		textFieldSearchPatient.setColumns(10);
		
		JLabel lblSearchBy = new JLabel("Search By:");
		lblSearchBy.setBounds(56, 106, 64, 14);
		contentPane.add(lblSearchBy);
		
		JButton btnNewButton = new JButton("Go Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					conn.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				DoctorPage frame = new DoctorPage();
				frame.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(855, 519, 176, 45);
		contentPane.add(btnNewButton);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setOpaque(false);
		panel.setForeground(Color.GREEN);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Load", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBackground(SystemColor.menu);
		panel.setBounds(730, 84, 348, 76);
		contentPane.add(panel);
		
		JButton btnLoadPatients = new JButton("Load my patients");
		btnLoadPatients.setBounds(191, 24, 128, 30);
		panel.add(btnLoadPatients);
		
		JButton btnSeeAllPatients = new JButton("See all patients");
		btnSeeAllPatients.setBounds(26, 24, 128, 30);
		panel.add(btnSeeAllPatients);
		btnSeeAllPatients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					String query1 = "SELECT id, name, age, sex, room, condition, medicine, mydoctor FROM Patient";
					PreparedStatement pst = conn.prepareStatement(query1);
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
				}catch(Exception e1){
					e1.printStackTrace();
				}
			}
		});
		btnLoadPatients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showTable();
			}
		});
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 0, 0));
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setOpaque(false);
		panel_1.setBounds(50, 84, 425, 82);
		contentPane.add(panel_1);
		
		JLabel lblPatientsBackground = new JLabel("Patients Background");
		lblPatientsBackground.setIcon(new ImageIcon(MyPatients.class.getResource("/Backgrounds/beepbeep.jpg")));
		lblPatientsBackground.setBounds(0, 0, 1088, 580);
		contentPane.add(lblPatientsBackground);
	}
	/////////////////////////////////////////MY Functions//////////////////////////
	public void showTable(){
		try{
			String query1 = "SELECT id, name, age, sex, room, condition, medicine, mydoctor FROM Patient WHERE mydoctor = ?";
			PreparedStatement pst = conn.prepareStatement(query1);
			pst.setString(1, DoctorPage.DoctorId);
			ResultSet rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
		}catch(Exception e1){
			e1.printStackTrace();
		}
	}
}
