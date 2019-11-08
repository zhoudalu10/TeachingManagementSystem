package com.iweb.entity;

public class DayCount {

	public static int dayCount(int y,int month) {
		int daycount=0;
		switch(month) {
		case 4:
		case 6:
		case 9:
		case 11:
			 return daycount=30;
			
		case 2:
			if(y%4==0&&y%100!=0||y%400==0)
			  {
				return daycount=29;
				
			}
			else {
				return daycount=28;
				
			}
			default:
				return daycount=31;
				
		}
	}

}
