package com.iweb.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.iweb.entity.Grade;

public class GradeDAO extends BaseDAO {

	public static boolean add(Grade g) {
		String sql = "insert into grade values(SEQ_GRADE_GNO.nextval,?,?,?,?,?,?)";
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, g.getName());
			pstmt.setString(2, g.getCourse());
			pstmt.setString(3, g.getHeadtname());
			pstmt.setString(4, g.getLecname());
			pstmt.setInt(5, g.getSnum());
			pstmt.setInt(6, g.getPernow());
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

	public static boolean remove(int gno) {
		String sql = "delete from grade where gno = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, gno);
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

	public static Grade select(int gno) {
		String sql = "select * from grade where gno = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Grade grade = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, gno);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String gname = rs.getString("gname");
				String gcourse = rs.getString("gcourse");
				String gheadtname = rs.getString("gheadtname");
				String glecname = rs.getString("glecname");
				int gsnum = rs.getInt("gsnum");
				int gpernow = rs.getInt("gpernow");
				grade = new Grade(gno, gname, gcourse, gheadtname, glecname, gsnum, gpernow);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, con);
		}
		return grade;
	}

	public static boolean modify(Grade g) {
		String sql = "update grade set gname = ?,gcourse = ?,gheadtname = ?,glecname = ?,gsnum = ?,gpernow = ? where gno = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, g.getName());
			pstmt.setString(2, g.getCourse());
			pstmt.setString(3, g.getHeadtname());
			pstmt.setString(4, g.getLecname());
			pstmt.setInt(5, g.getSnum());
			pstmt.setInt(6, g.getPernow());
			pstmt.setInt(7, g.getNo());
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

	public static List<Grade> all() {
		String sql = "select * from grade";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Grade> grades = new ArrayList<Grade>();
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int gno = rs.getInt("gno");
				String gname = rs.getString("gname");
				String gcourse = rs.getString("gcourse");
				String gheadtname = rs.getString("gheadtname");
				String glecname = rs.getString("glecname");
				int gsnum = rs.getInt("gsnum");
				int gpernow = rs.getInt("gpernow");
				Grade grade = new Grade(gno, gname, gcourse, gheadtname, glecname, gsnum, gpernow);
				grades.add(grade);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, con);
		}
		return grades;
	}

	public static boolean judge(String gname, String gcourse, String gheadtname, String glecname, String gsnum,
			String gpernow) {

		if (gname == null) {
			return false;
		}

		String strMatcher = "^[1-9]|[1-9][0-9]{1,2}$";
		Pattern p = Pattern.compile(strMatcher);
		Matcher m = p.matcher(gsnum);
		if (!m.find()) {
			return false;
		}

		strMatcher = "^[1-9]|[1-9][0-9]{1,2}$";
		p = Pattern.compile(strMatcher);
		m = p.matcher(gpernow);
		if (!m.find()) {
			return false;
		}

		String sql = "select cper from course where cname = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, gcourse);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int cper = Integer.valueOf(rs.getString("cper"));
				int gper = Integer.valueOf(gpernow);
				if (gper > cper) {
					return false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs, pstmt, con);
		}
		return true;
	}

	public static boolean judge(String gno, String gname, String gcourse, String gheadtname, String glecname,
			String gsnum, String gpernow) {

		String sql = "select gno from grade";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			first: {
				while (rs.next()) {
					String gnoBase = rs.getString("gno");
					if (gnoBase.equals(gno)) {
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
		return judge(gname, gcourse, gheadtname, glecname, gsnum, gpernow);
	}

	public static boolean judgeGno(String gno) {
		String sql = "select gno from grade";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String gnoBase = rs.getString("gno");
				if (gnoBase.equals(gno)) {
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs, pstmt, con);
		}
		return false;
	}

	public static List<String> selectCourse() {
		String sql = "select cname from course";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<String> courses = new ArrayList<String>();
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String cname = rs.getString("cname");
				courses.add(cname);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, con);
		}
		return courses;
	}

	public static List<String> selectHeadteacher() {
		String sql = "select tname from teacher where tlev = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<String> headteachers = new ArrayList<String>();
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "班主任");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String tname = rs.getString("tname");
				headteachers.add(tname);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, con);
		}
		return headteachers;
	}

	public static List<String> selectLecturer() {
		String sql = "select tname from teacher where tlev = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<String> lecturers = new ArrayList<String>();
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "讲师");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String tname = rs.getString("tname");
				lecturers.add(tname);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, con);
		}
		return lecturers;
	}

}
