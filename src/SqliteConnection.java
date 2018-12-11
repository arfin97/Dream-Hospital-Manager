import java.sql.Connection;
import java.sql.DriverManager;
import java.util.*;

import javax.swing.JOptionPane;
public class SqliteConnection {
	Connection conn = null;
	public static Connection dbConnector(){
		try{
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:Database//HospitalManagement.db");
			//JOptionPane.showMessageDialog(null, "Database connection successful");
			return conn;
			
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Database connection unsuccessful");
			return null;
		}
	}
}
