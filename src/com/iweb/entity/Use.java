package com.iweb.entity;

public class Use {
	private int uno;
	private String uname;
	private String pwd;
	private String uclass;
	private String position;
	public Use() {
		
	}
	public Use(int uno, String uname, String pwd, String uclass, String position) {
		this.uno = uno;
		this.uname = uname;
		this.pwd = pwd;
		this.uclass = uclass;
		this.position = position;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((position == null) ? 0 : position.hashCode());
		result = prime * result + ((pwd == null) ? 0 : pwd.hashCode());
		result = prime * result + ((uclass == null) ? 0 : uclass.hashCode());
		result = prime * result + ((uname == null) ? 0 : uname.hashCode());
		result = prime * result + uno;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Use other = (Use) obj;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		if (pwd == null) {
			if (other.pwd != null)
				return false;
		} else if (!pwd.equals(other.pwd))
			return false;
		if (uclass == null) {
			if (other.uclass != null)
				return false;
		} else if (!uclass.equals(other.uclass))
			return false;
		if (uname == null) {
			if (other.uname != null)
				return false;
		} else if (!uname.equals(other.uname))
			return false;
		if (uno != other.uno)
			return false;
		return true;
	}
	
	
	public String toString() {
		return "Use [uno=" + uno + ", uname=" + uname + ", pwd=" + pwd
				+ ", uclass=" + uclass + ", position=" + position + "]";
	}
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
	public String getUclass() {
		return uclass;
	}
	public void setUclass(String uclass) {
		this.uclass = uclass;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}

}
