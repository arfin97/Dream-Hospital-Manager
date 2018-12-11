

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.border.BevelBorder;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;

public class UpdateInfo extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldUpdateInfoDesignation;
	private JTextField textFieldUpdateInfoDegrees;
	JTextArea textFieldUpdateInfoAddress;
	JTextArea textFieldUpdateInfoAboutMe;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateInfo frame = new UpdateInfo();
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
	public UpdateInfo() {
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
		
		
		setBounds(100, 100, 674, 661);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Update");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					String query = "UPDATE Doctor SET  designation = ?, degree = ?, address = ?, aboutme = ? WHERE id = ?";
					PreparedStatement pst = conn.prepareStatement(query);
					pst.setString(1, textFieldUpdateInfoDesignation.getText());
					pst.setString(2, textFieldUpdateInfoDegrees.getText());
					pst.setString(3, textFieldUpdateInfoAddress.getText());
					pst.setString(4, textFieldUpdateInfoAboutMe.getText());
					pst.setString(5, DoctorPage.DoctorId);
					pst.execute();
					pst.close();
					JOptionPane.showMessageDialog(null, "Updated");
				}
				catch(Exception e){
					JOptionPane.showMessageDialog(null, "Error Occured");
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(104, 503, 444, 74);
		contentPane.add(btnNewButton);
		
		JLabel label_5 = new JLabel("About me:");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_5.setBounds(106, 380, 103, 37);
		contentPane.add(label_5);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setOpaque(false);
		panel.setForeground(Color.GREEN);
		panel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Information", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBackground(SystemColor.menu);
		panel.setBounds(89, 87, 474, 394);
		contentPane.add(panel);
		
		JLabel label_1 = new JLabel("Name:");
		label_1.setBounds(20, 43, 88, 37);
		panel.add(label_1);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel updateInfoLabelName = new JLabel("");
		updateInfoLabelName.setBounds(108, 43, 398, 37);
		panel.add(updateInfoLabelName);
		updateInfoLabelName.setText(DoctorPage.DoctorName);
		updateInfoLabelName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		textFieldUpdateInfoDesignation = new JTextField();
		textFieldUpdateInfoDesignation.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		textFieldUpdateInfoDesignation.setBounds(150, 91, 315, 42);
		panel.add(textFieldUpdateInfoDesignation);
		textFieldUpdateInfoDesignation.setColumns(10);
		
		JLabel label_2 = new JLabel("Designation:");
		label_2.setBounds(20, 91, 120, 37);
		panel.add(label_2);
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		textFieldUpdateInfoDegrees = new JTextField();
		textFieldUpdateInfoDegrees.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		textFieldUpdateInfoDegrees.setBounds(150, 156, 315, 42);
		panel.add(textFieldUpdateInfoDegrees);
		textFieldUpdateInfoDegrees.setColumns(10);
		
		JLabel label_3 = new JLabel("Degrees:");
		label_3.setBounds(20, 144, 94, 37);
		panel.add(label_3);
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel label_4 = new JLabel("Address :");
		label_4.setBounds(20, 207, 94, 37);
		panel.add(label_4);
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		textFieldUpdateInfoAddress = new JTextArea();
		textFieldUpdateInfoAddress.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		textFieldUpdateInfoAddress.setWrapStyleWord(true);
		textFieldUpdateInfoAddress.setBounds(147, 209, 317, 62);
		panel.add(textFieldUpdateInfoAddress);
		
		textFieldUpdateInfoAboutMe = new JTextArea();
		textFieldUpdateInfoAboutMe.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		textFieldUpdateInfoAboutMe.setWrapStyleWord(true);
		textFieldUpdateInfoAboutMe.setBounds(148, 294, 317, 62);
		panel.add(textFieldUpdateInfoAboutMe);
		
		JLabel lblBg = new JLabel("Bg");
		lblBg.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblBg.setIcon(new ImageIcon(UpdateInfo.class.getResource("/Backgrounds/heartjossbottom.jpg")));
		lblBg.setBounds(0, 0, 658, 622);
		contentPane.add(lblBg);
		setTextFieldText();
	}
	
	public void setTextFieldText(){
		try{
			String query = "SELECT * FROM Doctor WHERE id = ?";
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setString(1, DoctorPage.DoctorId);
			ResultSet rs = pst.executeQuery();

			if(rs.next()){
				textFieldUpdateInfoDesignation.setText(rs.getString("designation"));
			    textFieldUpdateInfoDegrees.setText(rs.getString("degree"));
				textFieldUpdateInfoAddress.setText(rs.getString("address"));
				textFieldUpdateInfoAboutMe.setText(rs.getString("aboutme"));
			}
			else{
				JOptionPane.showMessageDialog(null, "Faild to fetch data");
			}
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, "Error Occured");
			e.printStackTrace();
		}
	}
}
