

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.sql.PreparedStatement;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Rectangle;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.ImageIcon;
import javax.swing.border.EtchedBorder;

public class DoctorPage extends JFrame {
	public static String DoctorUsername;
	public static String DoctorName;
	public static String DoctorId;
	private JPanel contentPane;
	JLabel DoctorLabelId;
	JLabel DoctorLabelName;
	JLabel DoctorLabelDesignation;
	JLabel DoctorLabelDegree;
	JTextArea textAreaAboutMe;
	JTextArea textAreaAddress;
	Border border = BorderFactory.createLineBorder(Color.BLACK);
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DoctorPage frame = new DoctorPage();
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
	public DoctorPage() {
		setTitle("Doctor");
		////////////////////////////////////////////////////////////////CLOSING PROPERLY////////////////////////////////
		conn = SqliteConnection.dbConnector();
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
		setBounds(100, 100, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(51, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(111, 200, 42, 37);
		contentPane.add(lblNewLabel);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblName.setBounds(111, 152, 88, 37);
		contentPane.add(lblName);
		
		JLabel lblDesignation = new JLabel("Designation:");
		lblDesignation.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDesignation.setBounds(111, 239, 120, 37);
		contentPane.add(lblDesignation);
		
		JLabel lblDegrees = new JLabel("Degrees:");
		lblDegrees.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDegrees.setBounds(111, 287, 94, 37);
		contentPane.add(lblDegrees);
		
		JLabel lblAddress = new JLabel("Details:");
		lblAddress.setForeground(new Color(0, 102, 204));
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAddress.setBounds(826, 83, 94, 37);
		contentPane.add(lblAddress);
		
		JLabel lblAboutMe = new JLabel("About me:");
		lblAboutMe.setForeground(new Color(0, 102, 204));
		lblAboutMe.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAboutMe.setBounds(811, 252, 103, 37);
		contentPane.add(lblAboutMe);
		
		JButton btnMyPatients = new JButton("My Patients");
		btnMyPatients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		            	try {
							conn.close();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				/////////////////////////////////////////////////////////////////
		            	MyPatients frame = new MyPatients();
		            	frame.setVisible(true);
		            	dispose();
			}
		});
		btnMyPatients.setBounds(62, 450, 168, 43);
		contentPane.add(btnMyPatients);
		
		JButton btnMessages = new JButton("Messages");
		btnMessages.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Message frame = new Message();
				frame.setVisible(true);
			}
		});
		btnMessages.setBounds(292, 450, 168, 43);
		contentPane.add(btnMessages);
		
		JButton btnUpdateInfo = new JButton("Update Info");
		btnUpdateInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateInfo frame = new UpdateInfo();
				frame.setVisible(true);
				try {
					conn.close();
					System.out.println("Connection closed");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
			}
		});
		btnUpdateInfo.setBounds(522, 450, 168, 43);
		contentPane.add(btnUpdateInfo);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try { conn.close(); } catch (SQLException e1) { e1.printStackTrace(); } 
				Login2 frame = new Login2();
				frame.setVisible(true);
				dispose();
			}
		});
		btnLogout.setBounds(752, 450, 168, 43);
		contentPane.add(btnLogout);
		
		
		///////////////////To be filled empty lables////////////////////
		
		
		DoctorLabelId = new JLabel("");
		DoctorLabelId.setFont(new Font("Tahoma", Font.PLAIN, 18));
		DoctorLabelId.setBounds(163, 200, 424, 37);
		contentPane.add(DoctorLabelId);
		
		DoctorLabelName = new JLabel("");
		DoctorLabelName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		DoctorLabelName.setBounds(189, 152, 398, 37);
		contentPane.add(DoctorLabelName);
		
		DoctorLabelDesignation = new JLabel("");
		DoctorLabelDesignation.setFont(new Font("Tahoma", Font.PLAIN, 18));
		DoctorLabelDesignation.setBounds(236, 239, 351, 37);
		contentPane.add(DoctorLabelDesignation);
		
		DoctorLabelDegree = new JLabel("");
		DoctorLabelDegree.setFont(new Font("Tahoma", Font.PLAIN, 18));
		DoctorLabelDegree.setBounds(210, 287, 377, 37);
		contentPane.add(DoctorLabelDegree);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(627, 288, 290, 97);
		contentPane.add(scrollPane);
		
		textAreaAboutMe = new JTextArea();
		scrollPane.setViewportView(textAreaAboutMe);
		textAreaAboutMe.setEditable(false);
		textAreaAboutMe.setHighlighter(null);
		textAreaAboutMe.setLineWrap(true);
		textAreaAboutMe.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		
		JLabel label = new JLabel();
		label.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label.setBounds(522, 35, 364, 37);
		contentPane.add(label);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(617, 117, 300, 124);
		contentPane.add(scrollPane_1);
		
		textAreaAddress = new JTextArea();
		scrollPane_1.setViewportView(textAreaAddress);
		textAreaAddress.setEditable(false);
		textAreaAddress.setHighlighter(null);
		textAreaAddress.setLineWrap(true);
		textAreaAddress.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBackground(UIManager.getColor("Button.background"));
		panel.setForeground(Color.GREEN);
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Doctor", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(60, 84, 527, 308);
		contentPane.add(panel);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(DoctorPage.class.getResource("/Image/Login/white-texture-background.jpg")));
		label_1.setBackground(new Color(153, 204, 255));
		label_1.setBounds(10, 22, 507, 275);
		panel.add(label_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 153, 255));
		panel_1.setBounds(38, 423, 901, 97);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JLabel label_2 = new JLabel("");
		label_2.setBounds(0, 0, 901, 97);
		panel_1.add(label_2);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon(DoctorPage.class.getResource("/Backgrounds/ToomanyDoctors.jpg")));
		lblNewLabel_1.setBounds(0, 0, 984, 561);
		contentPane.add(lblNewLabel_1);
		System.out.println(DoctorUsername);
		System.out.println(DoctorId);
		fetchData();
	}
	
	////////////////////////////////////////////MY FUNCTIONS/////////////////////////////////////////////
	public void fetchData(){
		try{
			String query = "SELECT * FROM Doctor WHERE id = ?";
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setString(1, DoctorPage.DoctorId);
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				DoctorLabelId.setText(rs.getString("id"));
				DoctorLabelName.setText(rs.getString("name"));
				DoctorLabelDesignation.setText(rs.getString("designation"));
				DoctorLabelDegree.setText(rs.getString("degree"));
//				DoctorLabelAddress.setText(rs.getString("address"));
//				DoctorLabelAboutMe.setText("<html>"+rs.getString("aboutme")+"</html>");
				textAreaAboutMe.setText(rs.getString("aboutme"));
				textAreaAddress.setText(rs.getString("address"));
				DoctorName = rs.getString("name");
				
			}
			
		}catch(Exception e1){
			e1.printStackTrace();
		}
	}
}
