package com.iweb.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.iweb.entity.Teacher;

public class TeacherDAO extends BaseDAO {

	public static boolean add(Teacher t) {
		String sql = "insert into teacher values(SEQ_TEACHER_TNO.nextval,?,?,?,?,?,?)";
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, t.getName());
			pstmt.setString(2, t.getSex());
			pstmt.setInt(3, t.getAge());
			pstmt.setString(4, t.getLev());
			pstmt.setString(5, t.getTel());
			pstmt.setString(6, t.getQq());
			if (pstmt.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(null, pstmt, con);
		}
		return false;
	}

	public static boolean remove(int tno) {
		String sql = "delete from teacher where tno = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, tno);
			if (pstmt.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(null, pstmt, con);
		}
		return false;
	}

	public static Teacher select(int tno) {
		String sql = "select * from teacher where tno = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Teacher teacher = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, tno);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String tname = rs.getString("tname");
				String tsex = rs.getString("tsex");
				int tage = rs.getInt("tage");
				String tlev = rs.getString("tlev");
				String ttel = rs.getString("ttel");
				String tqq = rs.getString("tqq");
				teacher = new Teacher(tno, tname, tsex, tage, tlev, ttel, tqq);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, con);
		}
		return teacher;
	}

	public static boolean modify(Teacher t) {
		String sql = "update teacher set tname = ?,tsex = ?,tage = ?,tlev = ?,ttel = ?,tqq = ? where tno = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, t.getName());
			pstmt.setString(2, t.getSex());
			pstmt.setInt(3, t.getAge());
			pstmt.setString(4, t.getLev());
			pstmt.setString(5, t.getTel());
			pstmt.setString(6, t.getQq());
			pstmt.setInt(7, t.getNo());
			if (pstmt.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(null, pstmt, con);
		}
		return false;
	}

	public static List<Teacher> all() {
		String sql = "select * from teacher";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Teacher> teachers = new ArrayList<Teacher>();
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int tno = rs.getInt("tno");
				String tname = rs.getString("tname");
				String tsex = rs.getString("tsex");
				int tage = rs.getInt("tage");
				String tlev = rs.getString("tlev");
				String ttel = rs.getString("ttel");
				String tqq = rs.getString("tqq");
				Teacher teacher = new Teacher(tno, tname, tsex, tage, tlev, ttel, tqq);
				teachers.add(teacher);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, con);
		}
		return teachers;
	}

	public static boolean judge(String tname, String tsex, String tage, String tlev, String ttel, String tqq) {

		String strMatcher = "^[\\u4e00-\\u9fa5]{2,4}$";
		Pattern p = Pattern.compile(strMatcher);
		Matcher m = p.matcher(tname);
		if (!m.find()) {
			return false;
		}

		if (tsex == null) {
			return false;
		}

		strMatcher = "^[2-5][0-9]$";
		p = Pattern.compile(strMatcher);
		m = p.matcher(tage);
		if (!m.find()) {
			return false;
		}

		strMatcher = "^(1)\\d{10}$";
		p = Pattern.compile(strMatcher);
		m = p.matcher(ttel);
		if (!m.find()) {
			return false;
		}

		strMatcher = "^[1-9][0-9]{4,11}$";
		p = Pattern.compile(strMatcher);
		m = p.matcher(ttel);
		if (!m.find()) {
			return false;
		}

		return true;
	}

	public static boolean judge(String tno, String tname, String tsex, String tage, String tlev, String ttel,
			String tqq) {

		String sql = "select tno from teacher";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			first: {
				while (rs.next()) {
					String tnoBase = rs.getString("tno");
					if (tnoBase.equals(tno)) {
						break first;
					}
				}
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, con);
		}
		return judge(tname, tsex, tage, tlev, ttel, tqq);
	}
	
	public static boolean judgeTno(String tno) {
		String sql = "select tno from teacher";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String tnoBase = rs.getString("tno");
				if (tnoBase.equals(tno)) {
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
		
	}
}
