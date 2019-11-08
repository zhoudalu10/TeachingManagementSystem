package com.iweb.entity;

import java.util.ArrayList;
import java.util.List;

public class Month {
	private int year;
	private int month;
	private int dayCount;

	private List<String> weekDay;
	private List<String> dayCountMonth;

	public Month(int year, int month) {
		this.year = year;
		this.month = month;
		weekDay = new ArrayList<String>();
		for (int i = 0; i < weekDay(year, month); i++) {
			weekDay.add("");
		}
		dayCountMonth = new ArrayList<String>();
		for (int i = 0; i < dayCountMonth(year, month); i++) {
			dayCountMonth.add(Integer.toString(i + 1));
		}
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public List<String> getWeekDay() {
		return weekDay;
	}

	public List<String> getDayCountMonth() {
		return dayCountMonth;
	}

	public int getDayCount() {
		return dayCount;
	}

	public void setDayCount(int dayCount) {
		this.dayCount = dayCount;
	}

	public int getCountBlank() {
		return weekDay.size();
	}

	private static int dayCountMonth(int year, int month) {
		int dayCountOut;
		if (month == 4 || month == 6 || month == 9 || month == 11) {
			dayCountOut = 30;
		} else if (month == 2) {
			if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
				dayCountOut = 29;
			} else {
				dayCountOut = 28;
			}
		} else {
			dayCountOut = 31;
		}
		return dayCountOut;
	}

	private static int dayCountYear(int year) {
		return (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) ? 366 : 365;

	}

	private static int dayCount(int year, int month) {
		int dayCountOut = 0;

		for (int y = 1900; y < year; y++) {
			dayCountOut += dayCountYear(y);
		}
		for (int m = 1; m < month; m++) {
			dayCountOut += dayCountMonth(year, m);
		}
		return dayCountOut;

	}

	private static int weekDay(int year, int month) {
		int weekDayOut = dayCount(year, month) % 7 + 1;
		return weekDayOut;

	}

}
