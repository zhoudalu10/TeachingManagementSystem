package com.iweb.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.iweb.entity.Classroom;

public class ClassroomDAO extends BaseDAO {

	public static boolean add(Classroom r) {
		String sql = "insert into classroom values(SEQ_CLASSROOM_RNO.nextval,?,?)";
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, r.getLoc());
			pstmt.setInt(2, r.getNum());

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

	public static boolean remove(int rno) {
		String sql = "delete from classroom where rno = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, rno);
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

	public static Classroom select(int rno) {
		String sql = "select * from classroom where rno = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Classroom classroom = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, rno);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String rloc = rs.getString("rloc");
				int rnum = rs.getInt("rnum");
				classroom = new Classroom(rno, rloc, rnum);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, con);
		}
		return classroom;
	}

	public static boolean modify(Classroom r) {
		String sql = "update classroom set rloc = ?,rnum = ? where rno = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, r.getLoc());
			pstmt.setInt(2, r.getNum());
			pstmt.setInt(3, r.getNo());
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

	public static List<Classroom> all() {
		String sql = "select * from classroom";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Classroom> classrooms = new ArrayList<Classroom>();
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int rno = rs.getInt("rno");
				String rloc = rs.getString("rloc");
				int rnum = rs.getInt("rnum");
				Classroom classroom = new Classroom(rno, rloc, rnum);
				classrooms.add(classroom);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, con);
		}
		return classrooms;
	}

	public static boolean judge(String rloc, String rnum) {

		if (rloc == null) {
			return false;
		}

		String strMatcher = "^[1-9][0-9]{1,2}$";
		Pattern p = Pattern.compile(strMatcher);
		Matcher m = p.matcher(rnum);
		if (!m.find()) {
			return false;
		}

		return true;
	}

	public static boolean judge(String rno, String rloc, String rnum) {

		String sql = "select rno from classroom";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			first: {
				while (rs.next()) {
					String rnoBase = rs.getString("rno");
					if (rnoBase.equals(rno)) {
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
		return judge(rloc, rnum);
	}

	public static boolean judgeRno(String rno) {
		String sql = "select rno from classroom";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String rnoBase = rs.getString("rno");
				if (rnoBase.equals(rno)) {
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}

}
