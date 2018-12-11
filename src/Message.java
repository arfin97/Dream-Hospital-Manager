

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;

public class Message extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private String tableSelected;
	private String nameSelected;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Message frame = new Message();
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
	public Message() {
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
                System.out.println("Closed");
                e.getWindow().dispose();
            }
        });
		/////////////////////////////////////////////////////////////////
		
		setBounds(100, 100, 689, 609);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMessages = new JLabel("Messages:");
		lblMessages.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblMessages.setBounds(20, 50, 93, 25);
		contentPane.add(lblMessages);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 85, 643, 461);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int row = table.getSelectedRow();
				tableSelected = table.getModel().getValueAt(row, 1).toString();
				nameSelected = table.getModel().getValueAt(row, 0).toString();
				JOptionPane.showMessageDialog(null, tableSelected, "from " + nameSelected , JOptionPane.PLAIN_MESSAGE);
			}
		});
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(Message.class.getResource("/Backgrounds/green.jpg")));
		lblNewLabel.setBounds(0, 0, 673, 570);
		contentPane.add(lblNewLabel);
		showTable();
	}
	///////////////////////////////////Show Table///////////////////////////////////
	public void showTable(){
		try{
			String query1 = "SELECT name, message, id FROM Patient WHERE mydoctor = ?";
			PreparedStatement pst = conn.prepareStatement(query1);
			pst.setString(1, DoctorPage.DoctorId);
			ResultSet rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
		}catch(Exception e1){
			e1.printStackTrace();
		}
	}
}
