package com.iweb.entity;

import java.util.ArrayList;
import java.util.List;

public class Months {

	private List<Month> months;

	public List<Month> getMonths() {
		return months;
	}

	public Months(int year, int month, int count) {
		months = new ArrayList<Month>();
		for (int i = 0; i < count; i++) {
			if (month + i > 12) {
				months.add(new Month(year + 1, month + i - 12));
			} else {
				months.add(new Month(year, month + i));
			}
			if (i == 0) {
				months.get(i).setDayCount(0);
			} else {
				months.get(i)
						.setDayCount(months.get(i - 1).getDayCount() + months.get(i - 1).getDayCountMonth().size());
			}
		}
	}
	
	public int getCountBlank() {
		return months.get(0).getCountBlank();
	}

}
