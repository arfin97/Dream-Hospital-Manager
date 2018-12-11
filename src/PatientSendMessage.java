import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class PatientSendMessage extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PatientSendMessage frame = new PatientSendMessage();
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
	private JLabel lblWriteMesssage;
	private JTextArea textAreaPatientMessage;
	private JTextArea textArea;
	private JLabel lblBg;
	public PatientSendMessage() {
		conn = SqliteConnection.dbConnector();
		////////////////////////////////////////////////////////////////CLOSING PROPERLY////////////////////////////////
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
		setBounds(100, 100, 483, 474);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String query = "UPDATE Patient SET message = ? WHERE id = ?";
					PreparedStatement pst = conn.prepareStatement(query);
					pst.setString(1, textAreaPatientMessage.getText());
					pst.setString(2, PatientPage.PatientId);
					pst.execute();
					JOptionPane.showMessageDialog(null, "Message Sent");
				}catch(Exception e1){
					e1.printStackTrace();
				}
			}
		});
		btnSend.setBounds(39, 344, 394, 62);
		contentPane.add(btnSend);
		
		lblWriteMesssage = new JLabel("Write Messsage:");
		lblWriteMesssage.setBounds(36, 11, 202, 23);
		contentPane.add(lblWriteMesssage);
		
		textAreaPatientMessage = new JTextArea();
		textAreaPatientMessage.setBounds(39, 45, 394, 265);
		textAreaPatientMessage.setEditable(true);
		textAreaPatientMessage.setLineWrap(true);
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		textAreaPatientMessage.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		contentPane.add(textAreaPatientMessage);
		
		lblBg = new JLabel("bg");
		lblBg.setIcon(new ImageIcon(PatientSendMessage.class.getResource("/Backgrounds/orange.jpg")));
		lblBg.setBounds(0, 0, 467, 435);
		contentPane.add(lblBg);
		
	}
}
