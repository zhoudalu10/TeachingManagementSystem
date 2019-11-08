package com.iweb.entity;

public class User {
	private int uno;
	private String uname;
	private String pwd;
	private String position;
	private String uclass;
	public int getUno() {
		return uno;
	}
	public void setUno(int uno) {
		this.uno = uno;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getUclass() {
		return uclass;
	}
	public void setUclass(String uclass) {
		this.uclass = uclass;
	}
	@Override
	public String toString() {
		return "User [uno=" + uno + ", uname=" + uname + ", pwd=" + pwd + ", position=" + position + ", uclass="
				+ uclass + "]";
	}
	public User(int uno, String uname, String pwd, String position, String uclass) {
		this.uno = uno;
		this.uname = uname;
		this.pwd = pwd;
		this.position = position;
		this.uclass = uclass;
	}
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public User( String uname, String pwd, String position, String uclass) {
		
		this.uname = uname;
		this.pwd = pwd;
		this.position = position;
		this.uclass = uclass;
	}
}