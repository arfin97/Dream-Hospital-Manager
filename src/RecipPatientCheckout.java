import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.SystemColor;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;

public class RecipPatientCheckout extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldSearch;
	private JTable table;
	private JTextField textFieldId;
	private JTextField textFieldName;
	private JTextField textFieldDoctor;
	private JTextField textFieldCondition;
	private JTextField textFieldDayIn;
	private JTextField textFieldRoom;
	private JTextField textFieldDayOut;
	private JTextField textFieldDayStayed;
	private JTextField textFieldMedicinePrice;
	private JTextField textFieldTotalCost;
	private String tableSelected;
	private String nameSelected;
	JTextArea textArea;
	////////////////////////////////////////////////////////////
	public static String releasePatient;
	public static int roomNo =  -1;
	public static int daysStayed = -1;
	public static int patientRoomCost = -1;
	public static int totalRoomCost = -1;
	public static int totalCost = -1;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RecipPatientCheckout frame = new RecipPatientCheckout();
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
	private JTextField textFieldRoomCost;
	public RecipPatientCheckout() {	
			////////////////////////////////////////////////////////////////CLOSING PROPERLY////////////////////////////////
			conn = SqliteConnection.dbConnector();
			addWindowListener(new WindowAdapter()
	        {
	            @Override
	            public void windowClosing(WindowEvent e)
	            {
	            	try { conn.close(); } catch (SQLException e1) { e1.printStackTrace(); } 
	            	ReceptionistPage frame = new ReceptionistPage();
					frame.setVisible(true);
	                System.out.println("Doctor page Closed");
	                e.getWindow().dispose();
	            }
	        });
			
		setBounds(100, 100, 1019, 677);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(51, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textFieldSearch = new JTextField();
		textFieldSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				try{
					String query = "SELECT id, name, age, sex, room, condition, medicine FROM Patient WHERE name = ?";
					PreparedStatement pst = conn.prepareStatement(query);
					pst.setString(1, textFieldSearch.getText());
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
				}catch(Exception e1){
					e1.printStackTrace();
				}
			}
		});
		textFieldSearch.setBounds(801, 77, 86, 20);
		contentPane.add(textFieldSearch);
		textFieldSearch.setColumns(10);
		
		JLabel lblSearch = new JLabel("Search By Name:");
		lblSearch.setBounds(711, 80, 93, 14);
		contentPane.add(lblSearch);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(454, 108, 456, 119);
		contentPane.add(scrollPane);
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int row = table.getSelectedRow();
				tableSelected = table.getModel().getValueAt(row, 0).toString();
				nameSelected = table.getModel().getValueAt(row, 1).toString();
				//JOptionPane.showMessageDialog(null, tableSelected, "Selected" , JOptionPane.PLAIN_MESSAGE);
			}
		});
		scrollPane.setViewportView(table);
		///////////////////////////////////////////////////////////////////////////////////////////
		JLabel lblNewLabel = new JLabel("Patient ID");
		lblNewLabel.setBounds(104, 80, 65, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(104, 116, 46, 14);
		contentPane.add(lblName);
		
		JLabel lblCondition = new JLabel("Condition");
		lblCondition.setBounds(104, 153, 46, 14);
		contentPane.add(lblCondition);
		
		JLabel lblDoctor = new JLabel("Doctor");
		lblDoctor.setBounds(104, 192, 46, 14);
		contentPane.add(lblDoctor);
		
		JLabel lblRoom = new JLabel("Room");
		lblRoom.setBounds(104, 234, 46, 14);
		contentPane.add(lblRoom);
		
		JLabel lblDayIn = new JLabel("Day In");
		lblDayIn.setBounds(104, 272, 46, 14);
		contentPane.add(lblDayIn);
		
		JLabel lblDayOut = new JLabel("Day out");
		lblDayOut.setBounds(104, 311, 46, 14);
		contentPane.add(lblDayOut);
		
		textFieldId = new JTextField();
		textFieldId.setEditable(false);
		textFieldId.setBounds(192, 77, 175, 17);
		contentPane.add(textFieldId);
		textFieldId.setColumns(10);
		
		textFieldName = new JTextField();
		textFieldName.setEditable(false);
		textFieldName.setColumns(10);
		textFieldName.setBounds(192, 113, 175, 17);
		contentPane.add(textFieldName);
		
		textFieldDoctor = new JTextField();
		textFieldDoctor.setEditable(false);
		textFieldDoctor.setColumns(10);
		textFieldDoctor.setBounds(192, 189, 175, 17);
		contentPane.add(textFieldDoctor);
		
		textFieldCondition = new JTextField();
		textFieldCondition.setEditable(false);
		textFieldCondition.setColumns(10);
		textFieldCondition.setBounds(192, 153, 175, 17);
		contentPane.add(textFieldCondition);
		
		textFieldDayIn = new JTextField();
		textFieldDayIn.setEditable(false);
		textFieldDayIn.setColumns(10);
		textFieldDayIn.setBounds(192, 270, 175, 17);
		contentPane.add(textFieldDayIn);
		
		textFieldRoom = new JTextField();
		textFieldRoom.setEditable(false);
		textFieldRoom.setColumns(10);
		textFieldRoom.setBounds(192, 234, 175, 17);
		contentPane.add(textFieldRoom);
		
		textFieldDayOut = new JTextField();
		textFieldDayOut.setEditable(false);
		textFieldDayOut.setColumns(10);
		textFieldDayOut.setBounds(192, 308, 175, 17);
		contentPane.add(textFieldDayOut);
		
		JButton btnGenerateToken = new JButton("Generate Token");
		btnGenerateToken.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.setText(
						           "  ========================= Dream Hospital ==================== " + "\n" 
								 + "      Patient Name         : " + textFieldName.getText() + "\n" 
								 + "      ID                              : " + textFieldId.getText() + "\n"
								 + "      Day of Admission  : " + textFieldDayIn.getText() + "\n"
								 + "      Day of Release      : " + textFieldDayOut.getText() + "\n"
								 + "----------------------------------------------------------------------------------" + "\n"
								 + "      Room no                 : " + textFieldRoom.getText() + "\n"
								 + "      Days stayed            : " + daysStayed + "\n"
								 + "      Total Room cost    : " + totalRoomCost + "\n"
								 + "      Medicine cost         : " + textFieldMedicinePrice.getText() + "\n"
								 + "-----------------------------------------------------------------------------------: " + "\n"
								 + "      Total cost                 : " + totalCost + "\n"
								 + "      \n" + "\n"
								 + "      __________________" + "                " + "________________" + "\n"
								 + "         Patient Signature" + "                           " + "Doctor Signature" + "\n" + "\n"
								 + "      Date: 12/04/2017"
						);
			}
		});
		btnGenerateToken.setBounds(464, 518, 266, 64);
		contentPane.add(btnGenerateToken);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(454, 286, 461, 205);
		contentPane.add(scrollPane_1);
		
		textArea = new JTextArea(
						 "  ========================= Dream Hospital ==================== " + "\n" 
						 + "      Patient Name         : " + "\n" 
						 + "      ID                              : " + "\n"
						 + "      Day of Admission  : " + "\n"
						 + "      Day of Release      : " + "\n"
						 + "----------------------------------------------------------------------------------" + "\n"
						 + "      Room no                 : " + "\n"
						 + "      Days stayed            : " + "\n"
						 + "      Total Room cost    : " + "\n"
						 + "      Medicine cost         : " + "\n"
						 + "-----------------------------------------------------------------------------------: " + "\n"
						 + "      Total cost                 : " + "\n"
						 + "      \n" + "\n"
						 + "      __________________" + "                " + "________________" + "\n"
						 + "         Patient Signature" + "                           " + "Doctor Signature" + "\n" + "\n"
						 + "      Date: 12/04/2017"
						);
		scrollPane_1.setViewportView(textArea);

		textArea.setLineWrap(true);
		textArea.setEditable(false);
		////////////////////////////////////////////////////////////////Calculate////////////////////////////////////////
		JButton btnShowAllPatients = new JButton("Show Patients");
		btnShowAllPatients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String query1 = "SELECT id, name, age, sex, room, condition, medicine, dayin, dayout FROM Patient";
					
					PreparedStatement pst = conn.prepareStatement(query1);
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
				}catch(Exception e1){
					e1.printStackTrace();
				}
			}
		});
		btnShowAllPatients.setBounds(464, 76, 160, 23);
		contentPane.add(btnShowAllPatients);
		
		JButton btnReleaseThisPatient = new JButton("Release this patient");
		btnReleaseThisPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String query = "SELECT * FROM Patient WHERE id = '"+tableSelected+"' ";
					PreparedStatement pst = conn.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					
					while(rs.next()){
						textFieldId.setText(rs.getString("id"));
						textFieldName.setText(rs.getString("name"));
						textFieldCondition.setText(rs.getString("condition"));
						textFieldDoctor.setText(rs.getString("mydoctor"));
						textFieldRoom.setText(rs.getString("room"));
						textFieldDayIn.setText(rs.getString("dayin"));
						textFieldDayOut.setText(rs.getString("dayout"));
						
						roomNo = rs.getInt("id");

					}
				}catch(Exception e1){
					e1.printStackTrace();
				}
			}
		});
		btnReleaseThisPatient.setBounds(464, 240, 446, 35);
		contentPane.add(btnReleaseThisPatient);
		
		JButton btnPrintToken = new JButton("Print token");
		btnPrintToken.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//////////////////////////DELETE PATIENT///////////////////////////////////
					try{
						String query1 = "DELETE FROM Patient WHERE id = ?";
						PreparedStatement pst = conn.prepareStatement(query1);
						pst.setString(1, textFieldId.getText());
						pst.execute();
					}catch(Exception e1){
						e1.printStackTrace();
					}
				//////////////////////De OCCUPIE Room//////////////////////////////////////
				try{
					String query1 = "UPDATE Room SET occupied = ? WHERE id = ?";
					PreparedStatement pst = conn.prepareStatement(query1);
					pst.setInt(1, 0);
					pst.setInt(2, roomNo);
					pst.execute();
					
				}catch(Exception e1){
					e1.printStackTrace();
				}
			}
		});
		btnPrintToken.setBounds(760, 518, 127, 64);
		contentPane.add(btnPrintToken);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setOpaque(false);
		panel.setForeground(Color.GREEN);
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Patient Information", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBackground(SystemColor.menu);
		panel.setBounds(85, 39, 315, 308);
		contentPane.add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setOpaque(false);
		panel_1.setForeground(Color.GREEN);
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Calculate", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBackground(SystemColor.menu);
		panel_1.setBounds(85, 455, 315, 127);
		contentPane.add(panel_1);
		////////////////////////////////////////////////////////////////Calculate////////////////////////////////////////
		

		JButton btnNewButton = new JButton("Calculate");
		btnNewButton.setBounds(41, 93, 226, 23);
		panel_1.add(btnNewButton);
		
		textFieldDayStayed = new JTextField();
		textFieldDayStayed.setBounds(140, 27, 127, 17);
		panel_1.add(textFieldDayStayed);
		textFieldDayStayed.setColumns(10);
		
		textFieldMedicinePrice = new JTextField();
		textFieldMedicinePrice.setBounds(140, 55, 127, 17);
		panel_1.add(textFieldMedicinePrice);
		textFieldMedicinePrice.setColumns(10);
		
		JLabel lblMedicinePrice = new JLabel("Medicine Cost:");
		lblMedicinePrice.setBounds(52, 58, 87, 14);
		panel_1.add(lblMedicinePrice);
		
		JLabel lblDaysStayed = new JLabel("Days stayed");
		lblDaysStayed.setBounds(52, 30, 77, 14);
		panel_1.add(lblDaysStayed);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try{
					
					releasePatient = textFieldId.getText();
					daysStayed = Integer.parseInt(textFieldDayStayed.getText());
					
					String query = "SELECT * FROM Room WHERE id = '"+releasePatient+"' ";
					PreparedStatement pst = conn.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					while(rs.next()){
						patientRoomCost = rs.getInt("cost");
					}
				}catch(Exception e1){
					e1.printStackTrace();
				}finally{
					totalRoomCost = daysStayed * patientRoomCost;
					textFieldRoomCost.setText(Integer.toString(totalRoomCost));
					totalCost = Integer.parseInt(textFieldMedicinePrice.getText()) + totalRoomCost;
					textFieldTotalCost.setText(Integer.toString(totalCost));
				}
			}
		});
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setOpaque(false);
		panel_2.setForeground(Color.GREEN);
		panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Result", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBackground(SystemColor.menu);
		panel_2.setBounds(85, 346, 315, 98);
		contentPane.add(panel_2);
		
		textFieldRoomCost = new JTextField();
		textFieldRoomCost.setBounds(138, 28, 127, 21);
		panel_2.add(textFieldRoomCost);
		textFieldRoomCost.setEditable(false);
		textFieldRoomCost.setColumns(10);
		
		textFieldTotalCost = new JTextField();
		textFieldTotalCost.setBounds(138, 60, 127, 25);
		panel_2.add(textFieldTotalCost);
		textFieldTotalCost.setEditable(false);
		textFieldTotalCost.setColumns(10);
		
		JLabel lblTotalCost = new JLabel("Total Cost");
		lblTotalCost.setBounds(50, 58, 67, 14);
		panel_2.add(lblTotalCost);
		
		JLabel lblRoomPrice = new JLabel("Room Cost:");
		lblRoomPrice.setBounds(50, 30, 67, 14);
		panel_2.add(lblRoomPrice);
	}
}
