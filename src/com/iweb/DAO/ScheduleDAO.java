package com.iweb.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.iweb.entity.Schedule;

public class ScheduleDAO extends BaseDAO {

	public static String[] split(String str) {
		return str.split("\\+");
	}

	public static List<Schedule> splitData(String[] strs) {
		List<Schedule> schedules = new ArrayList<Schedule>();
		String matcher = "(.*)(-)(.*)(-)(.*)(-)(.*)(-)(.*)";
		Pattern pattern = Pattern.compile(matcher);
		Matcher m = null;
		List<String> dateList = dateList();
		for (int i = 0; i < strs.length; i++) {
			m = pattern.matcher(strs[i]);
			if (m.find()) {
				String sname = m.group(1);
				Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String sdate = sdf.format(date);
				int gno = Integer.valueOf(m.group(3));
				String sloc = m.group(5);
				int rno = Integer.valueOf(m.group(7));
				String sdata = m.group(9);
				String sbegindate = null;
				String senddate = null;
				String sspecialdate = "";
				int begin = 0;
				int end = 0;
				for (int j = 0; j < sdata.length(); j++) {
					if (sdata.charAt(j) == '1') {
						if (j % 2 == 0) {
							sbegindate = dateList.get(j / 2);
							begin = j;
						} else {
							sbegindate = dateList.get((j - 1) / 2);
							begin = j - 1;
						}
						break;
					}
				}

				for (int j = sdata.length() - 1; j >= 0; j--) {
					if (sdata.charAt(j) == '1') {
						if (j % 2 == 0) {
							senddate = dateList.get(j / 2);
							end = j + 1;
						} else {
							senddate = dateList.get((j - 1) / 2);
							end = j;
						}
						break;
					}
				}

				for (int j = 0; j < sdata.length(); j++) {
					if (j >= begin && j <= end) {
						if (sdata.charAt(j) == '0') {
							if (j % 2 == 0) {
								if (sdata.charAt(j + 1) == '0') {
									sspecialdate = sspecialdate + dateList.get(j / 2) + "-" + "3" + ",";
									j++;
								} else {
									sspecialdate = sspecialdate + dateList.get(j / 2) + "-" + "2" + ",";
									j++;
								}
							} else {
								sspecialdate = sspecialdate + dateList.get((j - 1) / 2) + "-" + "1" + ",";
							}
						}
					}
				}
				schedules.add(new Schedule(gno, rno, sname, sdate, sloc, sbegindate, senddate, sspecialdate));
			}
		}

		return schedules;
	}

	public static List<String> dateList() {
		Calendar calendar = null;
		calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;

		List<String> dateList = new ArrayList<String>();
		for (int i = 0; i < 6; i++) {
			if (month + i > 12) {
				int dayCountMonth = dayCountMonth(year + 1, month + i - 12);
				for (int j = 0; j < dayCountMonth; j++) {
					dateList.add((month + i - 12) + "/" + (j + 1));
				}
			} else {
				int dayCountMonth = dayCountMonth(year, month + i);
				for (int j = 0; j < dayCountMonth; j++) {
					dateList.add((month + i) + "/" + (j + 1));
				}
			}

		}
		return dateList;
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

	private static boolean add(Schedule schedule) {
		String sql = "insert into schedule values(SEQ_SCHEDULE_SNO.nextval,?,?,?,?,?,?,?,?)";
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, schedule.getGno());
			pstmt.setInt(2, schedule.getRno());
			pstmt.setString(3, schedule.getSname());
			pstmt.setString(4, schedule.getSdate());
			pstmt.setString(5, schedule.getSloc());
			pstmt.setString(6, schedule.getSbegindate());
			pstmt.setString(7, schedule.getSenddate());
			pstmt.setString(8, schedule.getSspecialdate());
			if (pstmt.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(null, pstmt, con);
		}
		return false;
	}

	public static boolean adds(List<Schedule> schedules) {
		for (int i = 0; i < schedules.size(); i++) {
			if (!add(schedules.get(i))) {
				return false;
			}
		}
		return true;
	}

	public static boolean removes() {
		String sql = "delete from schedule";
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			if (pstmt.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(null, pstmt, con);
		}
		return false;
	}

}
