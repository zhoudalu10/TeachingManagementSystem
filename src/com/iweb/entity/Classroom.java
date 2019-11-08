package com.iweb.entity;

public class Classroom {
	private int rno;
	private String rloc;
	private int rnum;

	public Classroom(String rloc, int rnum) {
		this.rloc = rloc;
		this.rnum = rnum;
	}

	public Classroom(int rno, String rloc, int rnum) {
		this.rno = rno;
		this.rloc = rloc;
		this.rnum = rnum;
	}

	public int getNo() {
		return rno;
	}

	public String getLoc() {
		return rloc;
	}

	public int getNum() {
		return rnum;
	}

}
