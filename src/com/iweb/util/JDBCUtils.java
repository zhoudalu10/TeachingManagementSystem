package com.iweb.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class JDBCUtils {
    private static String url = "jdbc:oracle:thin:@39.106.114.75:1521:orcl";
    private static String user = "xjs";
    private static String password = "pass";
    //private static String driver;

   /* static{
        try {
            Properties pro=new Properties();
            InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            pro.load(is);
            url=pro.getProperty("url");
            user=pro.getProperty("user");
            password=pro.getProperty("password");
            driver=pro.getProperty("driver");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }*/
    
    static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
    //获取连接
    public static Connection getConnection() throws Exception {
  return DriverManager.getConnection(url,user,password);
    }

    //释放资源
    public static void close(Statement stmt, Connection conn){
        if(stmt!=null){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(ResultSet rs,Statement stmt, Connection conn){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(stmt!=null){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
