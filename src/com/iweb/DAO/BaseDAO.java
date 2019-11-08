package com.iweb.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseDAO {
	private static final String URL = "jdbc:oracle:thin:@39.106.114.75:1521:orcl";
	private static final String USERNAME = "xjs";
	private static final String PASSWORD = "pass";

	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USERNAME, PASSWORD);
	}
	
	public static void main(String[] args) {
		Connection connection = null;
		try {
			connection = getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(connection);
	}

	public static void close(ResultSet rs, PreparedStatement pstmt, Connection con) {
		try {
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
			if (pstmt != null && !pstmt.isClosed()) {
				pstmt.close();
			}
			if (con != null && !con.isClosed()) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
