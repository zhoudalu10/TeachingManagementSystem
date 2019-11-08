package com.iweb.entity;

public class Teacher {
	private int tno;
	private String tname;
	private String tsex;
	private int tage;
	private String tlev;
	private String ttel;
	private String tqq;

	public Teacher(String tname, String tsex, int tage, String tlev, String ttel, String tqq) {
		this.tname = tname;
		this.tsex = tsex;
		this.tage = tage;
		this.tlev = tlev;
		this.ttel = ttel;
		this.tqq = tqq;
	}

	public Teacher(int tno, String tname, String tsex, int tage, String tlev, String ttel, String tqq) {
		this.tno = tno;
		this.tname = tname;
		this.tsex = tsex;
		this.tage = tage;
		this.tlev = tlev;
		this.ttel = ttel;
		this.tqq = tqq;
	}

	public int getNo() {
		return tno;
	}

	public String getName() {
		return tname;
	}

	public String getSex() {
		return tsex;
	}

	public int getAge() {
		return tage;
	}

	public String getLev() {
		return tlev;
	}

	public String getTel() {
		return ttel;
	}

	public String getQq() {
		return tqq;
	}

}
