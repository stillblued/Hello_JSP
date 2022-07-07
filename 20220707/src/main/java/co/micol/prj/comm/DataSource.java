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
			System.out.println("DB연결성공");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("실패");
		} return conn;
		
	}
	
	

}


