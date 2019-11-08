package com.iweb.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.iweb.entity.Course;

public class CourseDAO extends BaseDAO{
	
	public static boolean add(Course c) {
		String sql = "insert into course values(SEQ_COURSE_CNO.nextval,?,?)";
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, c.getName());
			pstmt.setInt(2, c.getPer());

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
	
	public static boolean remove(int cno) {
		String sql = "delete from course where cno = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cno);
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
	
	public static Course select(int cno) {
		String sql = "select * from course where cno = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Course course = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cno);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String cname = rs.getString("cname");
				int cper = rs.getInt("cper");
				course = new Course(cno, cname, cper);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, con);
		}
		return course;
	}
	
	public static boolean modify(Course c) {
		String sql = "update course set cname = ?,cper = ? where cno = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, c.getName());
			pstmt.setInt(2, c.getPer());
			pstmt.setInt(3, c.getNo());
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
	
	public static List<Course> all() {
		String sql = "select * from course";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Course> courses = new ArrayList<Course>();
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int cno = rs.getInt("cno");
				String cname = rs.getString("cname");
				int cper = rs.getInt("cper");
				Course course = new Course(cno, cname, cper);
				courses.add(course);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, con);
		}
		return courses;
	}
	
	public static boolean judge(String cname, String cper) {

		if (cname == null) {
			return false;
		}

		String strMatcher = "^[1-9][0-9]{1,2}$";
		Pattern p = Pattern.compile(strMatcher);
		Matcher m = p.matcher(cper);
		if (!m.find()) {
			return false;
		}

		return true;
	}
	
	public static boolean judge(String cno, String cname, String cper) {

		String sql = "select cno from course";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			first: {
				while (rs.next()) {
					String cnoBase = rs.getString("cno");
					if (cnoBase.equals(cno)) {
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
		return judge(cname, cper);
	}
	
	public static boolean judgeCno(String cno) {
		String sql = "select cno from course";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String cnoBase = rs.getString("cno");
				if (cnoBase.equals(cno)) {
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}

}
