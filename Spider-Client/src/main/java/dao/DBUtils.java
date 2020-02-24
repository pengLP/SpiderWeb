package dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import Main.error_log;


public class DBUtils {
	private static String driver;
	private static String url;
	private static String username;
	private static String password;
	
	static {
		Properties db = new Properties();
		InputStream in = DBUtils.class.getClassLoader().getResourceAsStream("db.properties");
		
		try {
			db.load(in);
			driver = db.getProperty("jdbc.driver");
			url = db.getProperty("jdbc.url");
			username = db.getProperty("jdbc.username");
			password = db.getProperty("jdbc.password");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url,username,password); 
			return conn;
		} catch (Exception e) {
			new error_log(e);
			e.printStackTrace();
		}
		return null;
	}
	
	public static void close(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				new error_log(e);
			} catch (Exception e) {
				new error_log(e);
				e.printStackTrace();
			}
		}
	}
}
