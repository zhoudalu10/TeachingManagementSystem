package com.iweb.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.iweb.DAO.LoginDao;
import com.iweb.entity.Use;

public class LoginDaoImpl implements LoginDao {

	public Use checkLoginDao(String uname, String pwd) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Use u = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@39.106.114.75:1521:orcl", "xjs", "pass");
			String sql = "select * from t_user where uname =? and pwd = ? ";
			ps = conn.prepareStatement(sql);
			ps.setString(1, uname);
			ps.setString(2, pwd);

			rs = ps.executeQuery();
			while (rs.next()) {
				u = new Use();
				u.setUno(rs.getInt("uno"));
				u.setUname(rs.getString("uname"));
				u.setPwd(rs.getString("pwd"));
				u.setUclass(rs.getString("uclass"));
				u.setPosition(rs.getString("position"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return u;
	}

	@Override
	public Use checkUnoDao(String uno) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Use u = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@39.106.114.75:1521:orcl", "xjs", "pass");
			String sql = "select * from t_user where uno =? ";
			ps = conn.prepareStatement(sql);
			ps.setString(1, uno);

			rs = ps.executeQuery();
			while (rs.next()) {
				u = new Use();
				u.setUno(rs.getInt("uno"));
				u.setUname(rs.getString("uname"));
				u.setPwd(rs.getString("pwd"));
				u.setUclass(rs.getString("uclass"));
				u.setPosition(rs.getString("position"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return u;
	}

	@Override
	public Use checkOuttDao(String uname, String pwd) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;

		Use ur = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@39.106.114.75:1521:orcl", "xjs", "pass");
			String sql = ("update iweb_tbl_user set LEAVE vaule = sysdate where uname =? and pwd=?");
			ps = conn.prepareStatement(sql);

			ps.setString(1, uname);
			ps.setString(2, pwd);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ur;
	}

}