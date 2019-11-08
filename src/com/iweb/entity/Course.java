package com.iweb.entity;

public class Course {
	private int cno;
	private String cname;
	private int cper;

	public Course(String cname, int cper) {
		this.cname = cname;
		this.cper = cper;
	}

	public Course(int cno, String cname, int cper) {
		this.cno = cno;
		this.cname = cname;
		this.cper = cper;
	}

	public int getNo() {
		return cno;
	}

	public String getName() {
		return cname;
	}

	public int getPer() {
		return cper;
	}
}
