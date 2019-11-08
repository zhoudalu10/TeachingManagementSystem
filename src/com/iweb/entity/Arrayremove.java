package com.iweb.entity;

public class Arrayremove {
	public static void remove(String[] str,String string) {
		int count = 0;
		for (int i = 0; i < str.length; i++) {
			if(str.length==0) {
				break;
			}
			if(i==str.length-1) {//最后一个数组元素
				if (!str[i].replaceAll("/\\d+-\\d+", "").equals(string)) {
					str[i] = "0";
				}
			}
			else {
				if (!str[i].replaceAll("/\\d+-\\d+", "").equals(string)) {
					count++;
					System.arraycopy(str, i+1, str, i, str.length-i-1);
					str[str.length-count] = "0";
				}
			}
		}
	}
	
	public static void main(String[] args) {//test
		String[] monthStrings = {"8/3-1","3/1-2","2/1-1","5/5-2"};
		remove(monthStrings, "1");
		System.out.println(monthStrings.length);
		for(String temp:monthStrings) {
			System.out.println(temp);
		}
	}
	
}
