import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;

public class RecipRooms extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RecipRooms frame = new RecipRooms();
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
	public RecipRooms() {
		conn = SqliteConnection.dbConnector();

		setTitle("Rooms");
		setBounds(100, 100, 701, 528);
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
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 87, 630, 367);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblRooms = new JLabel("Rooms:");
		lblRooms.setBounds(28, 25, 254, 34);
		contentPane.add(lblRooms);
		
		showTable();
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void showTable(){
		try{
			String query1 = "SELECT * FROM Room"; //id, type, cost, patient
			
			PreparedStatement pst = conn.prepareStatement(query1);
			ResultSet rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
		}catch(Exception e1){
			e1.printStackTrace();
		}
	}
}
