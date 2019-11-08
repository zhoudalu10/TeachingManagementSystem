package com.iweb.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Note {
	private int noteno;
	private String notetime;
	private String notedata;
	private String notegrade;
	
	public Note() {
		
	}
	
	public Note(String notedata) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.notetime= sdf.format(date);
		this.notedata = notedata;
	}
	
	public Note(int noteno, String notetime, String notedata) {
		this.noteno = noteno;
		this.notetime = notetime;
		this.notedata = notedata;
	}

	public int getNoteno() {
		return noteno;
	}

	public void setNoteno(int noteno) {
		this.noteno = noteno;
	}

	public String getNotetime() {
		return notetime;
	}

	public void setNotetime(String notetime) {
		this.notetime = notetime;
	}

	public String getNotedata() {
		return notedata;
	}

	public void setNotedata(String notedata) {
		this.notedata = notedata;
	}
	
	
	public String getNotegrade() {
		return notegrade;
	}

	public void setNotegrade(String notegrade) {
		this.notegrade = notegrade;
	}

	public Note( String notetime, String notedata) {

		this.notetime = notetime;
		this.notedata = notedata;
	}
	
	
}
