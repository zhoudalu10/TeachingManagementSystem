package com.iweb.entity;

public class CoursePlan {
	public String gHeadTname;
	public String glecname;
	public String sname;
	public String sloc;
	String sdate;
	String sBeginDate;
	String sEndDate;
	public String sSpecialDate;
	public String getSloc() {
		return sloc;
	}
	public void setSloc(String sloc) {
		this.sloc = sloc;
	}
	
	public String getSdate() {
		return sdate;
	}
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}
	
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getsBeginDate() {
		return sBeginDate;
	}
	public void setsBeginDate(String sBeginDate) {
		this.sBeginDate = sBeginDate;
	}
	public String getsEndDate() {
		return sEndDate;
	}
	public void setsEndDate(String sEndDate) {
		this.sEndDate = sEndDate;
	}
	public String getsSpecialDate() {
		return sSpecialDate;
	}
	public void setsSpecialDate(String sSpecialDate) {
		this.sSpecialDate = sSpecialDate;
	}
	public String getgHeadTname() {
		return gHeadTname;
	}
	public void setgHeadTname(String gHeadTname) {
		this.gHeadTname = gHeadTname;
	}
	public String getGlecname() {
		return glecname;
	}
	public void setGlecname(String glecname) {
		this.glecname = glecname;
	}
	@Override
	public String toString() {
		return "Course [gHeadTname=" + gHeadTname + ", glecname=" + glecname + ", sname=" + sname + ", sloc=" + sloc
				+ ", sdate=" + sdate + ", sBeginDate=" + sBeginDate + ", sEndDate=" + sEndDate + ", sSpecialDate="
				+ sSpecialDate + "]";
	}

	
	
	
	

	

}
