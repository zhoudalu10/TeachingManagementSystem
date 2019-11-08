package com.iweb.entity;

public class Schedule {
	private int sno;
	private int gno;
	private int rno;
	private String sname;
	private String sdate;
	private String sloc;
	private String sbegindate;
	private String senddate;
	private String sspecialdate;

	public Schedule(int gno, int rno, String sname, String sdate, String sloc, String sbegindate, String senddate,
			String sspecialdate) {
		this.gno = gno;
		this.rno = rno;
		this.sname = sname;
		this.sdate = sdate;
		this.sloc = sloc;
		this.sbegindate = sbegindate;
		this.senddate = senddate;
		this.sspecialdate = sspecialdate;
	}
	
	public Schedule() {
		
	}

	public String toString() {
		return "Schedule [sno=" + sno + ", gno=" + gno + ", rno=" + rno + ", sname=" + sname + ", sdate=" + sdate
				+ ", sloc=" + sloc + ", sbegindate=" + sbegindate + ", senddate=" + senddate + ", sspecialdate="
				+ sspecialdate + "]";
	}

	public int getGno() {
		return gno;
	}

	public void setGno(int gno) {
		this.gno = gno;
	}

	public int getRno() {
		return rno;
	}

	public void setRno(int rno) {
		this.rno = rno;
	}

	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getSdate() {
		return sdate;
	}

	public void setSdate(String sdate) {
		this.sdate = sdate;
	}

	public String getSloc() {
		return sloc;
	}

	public void setSloc(String sloc) {
		this.sloc = sloc;
	}

	public String getSbegindate() {
		return sbegindate;
	}

	public void setSbegindate(String sbegindate) {
		this.sbegindate = sbegindate;
	}

	public String getSenddate() {
		return senddate;
	}

	public void setSenddate(String senddate) {
		this.senddate = senddate;
	}

	public String getSspecialdate() {
		return sspecialdate;
	}

	public void setSspecialdate(String sspecialdate) {
		this.sspecialdate = sspecialdate;
	}

}
