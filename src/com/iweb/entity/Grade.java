package com.iweb.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.iweb.DAO.BaseDAO;

public class Grade {
	private int gno;
	private String gname;
	private String gcourse;
	private String gheadtname;
	private String glecname;
	private int gsnum;
	private int gpernow;
	private int gperleft;

	public Grade() {
	}

	public int getNo() {
		return gno;
	}

	public void setGno(int gno) {
		this.gno = gno;
	}

	public String getName() {
		return gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
	}

	public String getCourse() {
		return gcourse;
	}

	public void setGcourse(String gcourse) {
		this.gcourse = gcourse;
	}

	public String getHeadtname() {
		return gheadtname;
	}

	public void setGheadtname(String gheadtname) {
		this.gheadtname = gheadtname;
	}

	public String getLecname() {
		return glecname;
	}

	public void setGlecname(String glecname) {
		this.glecname = glecname;
	}

	public int getSnum() {
		return gsnum;
	}

	public void setGsnum(int gsnum) {
		this.gsnum = gsnum;
	}

	public int getPernow() {
		return gpernow;
	}

	public void setGpernow(int gpernow) {
		this.gpernow = gpernow;
	}

	public int getPerleft() {
		return gperleft;
	}

	public Grade(String gname, String gcourse, String gheadtname, String glecname, int gsnum, int gpernow) {
		this.gname = gname;
		this.gcourse = gcourse;
		this.gheadtname = gheadtname;
		this.glecname = glecname;
		this.gsnum = gsnum;
		this.gpernow = gpernow;
		String sql = "select cper from course where cname = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = BaseDAO.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, gcourse);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int cper = Integer.valueOf(rs.getString("cper"));
				int gper = Integer.valueOf(gpernow);
				this.gperleft = cper - gper;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BaseDAO.close(rs, pstmt, con);
		}

	}

	public Grade(int gno, String gname, String gcourse, String gheadtname, String glecname, int gsnum, int gpernow) {
		this.gno = gno;
		this.gname = gname;
		this.gcourse = gcourse;
		this.gheadtname = gheadtname;
		this.glecname = glecname;
		this.gsnum = gsnum;
		this.gpernow = gpernow;
		String sql = "select cper from course where cname = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = BaseDAO.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, gcourse);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int cper = Integer.valueOf(rs.getString("cper"));
				int gper = Integer.valueOf(gpernow);
				this.gperleft = cper - gper;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BaseDAO.close(rs, pstmt, con);
		}
	}

}
