package co.micol.prj.comm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {
	
	
	private static DataSource dataSource = new DataSource();
	private Connection conn;
	
	private DataSource() {}
	
	
	public static DataSource getInstance() {
		return dataSource;
	}
	
	
	public Connection getConnetion() {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe" , "micol", "1234");
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		
		} return conn;
		
	}
	
	

}


