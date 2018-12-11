import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;

public class ReceptionistPage extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReceptionistPage frame = new ReceptionistPage();
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
	public ReceptionistPage() {
		conn = SqliteConnection.dbConnector();
		setTitle("Reciption");
		addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
            	try { conn.close(); } catch (SQLException e1) { e1.printStackTrace(); } 
            	
                System.out.println("Message page Closed");
                e.getWindow().dispose();
            }
        });
		/////////////////////////////////////////////////////////////////
		
		
		setBounds(100, 100, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try { conn.close(); } catch (SQLException e1) { e1.printStackTrace(); } 
				Login2 frame = new Login2();
				frame.setVisible(true);
				dispose();
			}
		});
		btnLogOut.setBounds(707, 513, 193, 23);
		contentPane.add(btnLogOut);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(82, 76, 833, 426);
		contentPane.add(tabbedPane);
		
		JPanel AddTab = new JPanel();
		tabbedPane.addTab("Add", null, AddTab, null);
		AddTab.setLayout(null);
		
		JButton btnAddDoctor = new JButton("Add Doctor");
		btnAddDoctor.setBounds(511, 216, 185, 100);
		AddTab.add(btnAddDoctor);
		
		JButton btnAddPatient = new JButton("Add Patient");
		btnAddPatient.setBounds(511, 87, 185, 100);
		AddTab.add(btnAddPatient);
		
		JLabel AddTabBackground = new JLabel("Background");
		AddTabBackground.setIcon(new ImageIcon(ReceptionistPage.class.getResource("/Backgrounds/medicine-heart-symbol-powerpoint-backgrounds.jpg")));
		AddTabBackground.setBounds(0, 0, 828, 398);
		AddTab.add(AddTabBackground);
		btnAddPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try { conn.close(); } catch (SQLException e1) { e1.printStackTrace(); } 
				RecipAddPatient frame = new RecipAddPatient();
				frame.setVisible(true);
				dispose();
			}
		});
		btnAddDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try { conn.close(); } catch (SQLException e1) { e1.printStackTrace(); } 
				RecipAddDoctor frame = new RecipAddDoctor();
				frame.setVisible(true);
				dispose();
			}
		});
		
		JPanel ReleaseTab = new JPanel();
		tabbedPane.addTab("Release", null, ReleaseTab, null);
		ReleaseTab.setLayout(null);
		
		JButton btnPatientCheckout = new JButton("Release Patient");
		btnPatientCheckout.setBounds(91, 171, 172, 49);
		ReleaseTab.add(btnPatientCheckout);
		
		JLabel ReleaseTabBackground = new JLabel("Background");
		ReleaseTabBackground.setIcon(new ImageIcon(ReceptionistPage.class.getResource("/Backgrounds/d557b-care.png")));
		ReleaseTabBackground.setBounds(-24, 0, 1079, 425);
		ReleaseTab.add(ReleaseTabBackground);
		btnPatientCheckout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try { conn.close(); } catch (SQLException e1) { e1.printStackTrace(); } 
				RecipPatientCheckout frame = new RecipPatientCheckout();
				frame.setVisible(true);
				dispose();
			}
		});
		
		JPanel InfoTab = new JPanel();
		tabbedPane.addTab("Info", null, InfoTab, null);
		InfoTab.setLayout(null);
		
		JButton btnRooms = new JButton("Rooms Information");
		btnRooms.setBounds(528, 113, 230, 79);
		InfoTab.add(btnRooms);
		
		JButton btnHospitalInfo = new JButton("Hospital Status");
		btnHospitalInfo.setBounds(528, 236, 230, 79);
		InfoTab.add(btnHospitalInfo);
		
		JLabel InfoTabBackground = new JLabel("Background");
		InfoTabBackground.setIcon(new ImageIcon(ReceptionistPage.class.getResource("/Backgrounds/docinfo.jpg")));
		InfoTabBackground.setBounds(0, 0, 828, 398);
		InfoTab.add(InfoTabBackground);
		
		JLabel lblReceptionistWindow = new JLabel("Receptionist Window");
		lblReceptionistWindow.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblReceptionistWindow.setBounds(83, 36, 220, 29);
		contentPane.add(lblReceptionistWindow);
		
		JLabel ContantPaneBackground = new JLabel("ContantPaneBG");
		ContantPaneBackground.setIcon(new ImageIcon(ReceptionistPage.class.getResource("/Backgrounds/RedHearBackground.jpg")));
		ContantPaneBackground.setBounds(0, 0, 984, 561);
		contentPane.add(ContantPaneBackground);
		btnHospitalInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try { conn.close(); } catch (SQLException e1) { e1.printStackTrace(); } 
				RecipHospitalInfo frame = new RecipHospitalInfo();
				frame.setVisible(true);
				dispose();
			}
		});
		btnRooms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try { conn.close(); } catch (SQLException e1) { e1.printStackTrace(); } 
				RecipRooms frame = new RecipRooms();
				frame.setVisible(true);
				dispose();
				
			}
		});
	}
}
