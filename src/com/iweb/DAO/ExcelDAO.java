package com.iweb.DAO;
import java.util.Date;
import java.util.List;

import com.iweb.entity.*;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;

import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class ExcelDAO extends BaseDAO{
	@SuppressWarnings({ "null", "deprecation" })
//------------------------------------------------------------------------------------------------------------------------
	public static boolean getExcel(String path) throws WriteException {
		ArrayList<CoursePlan> list=new ArrayList<CoursePlan>();
		Connection conn=null;
		PreparedStatement pa=null;
		ResultSet rs =null;
		CoursePlan course=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn =DriverManager.getConnection("jdbc:oracle:thin:@39.106.114.75:1521:orcl", "xjs", "pass");
			String sql ="select gheadtname,glecname,sname,sloc,sbegindate,senddate,sspecialdate from schedule t join grade g on t.gno=g.gno";
			pa=conn.prepareStatement(sql);
			rs=pa.executeQuery();
			while(rs.next()) {
				course =new CoursePlan();
				course.setgHeadTname(rs.getString("gheadtname"));
				course.setGlecname(rs.getString("glecname"));
				course.setSname(rs.getString("sname"));
				course.setSloc(rs.getString("sloc"));
				course.setsBeginDate(rs.getString("sbegindate"));
				course.setsEndDate(rs.getString("senddate"));
				course.setsSpecialDate(rs.getString("sspecialdate"));
				list.add(course);
			}
			rs.close();
			pa.close();
			conn.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	     WritableWorkbook book;
	        try {
	            File file = new File(path);
	            if(file.exists()) {
	            	file.delete();
	            	file.createNewFile();
	            }
	            int[] month = new int[list.size()];
	            for(int i=0;i<list.size();i++) {
	            	int begindate = Integer.valueOf(list.get(i).getsBeginDate().replaceAll("/\\d+", ""));
	            	month[i] = begindate;
	            }
	            com.iweb.entity.Sort.bubbleSort(month);
	            int minMonth = month[0];//取出最小月
	            
	            for(int i=0;i<list.size();i++) {
	            	int enddate = Integer.valueOf(list.get(i).getsEndDate().replaceAll("/\\d+", ""));
	            	month[i] = enddate;
	            }
	            com.iweb.entity.Sort.bubbleSort(month);
	            int maxMonth = month[list.size()-1];//取出最大月
	            
	            //创建一个工作簿对象
	            book = Workbook.createWorkbook(file);
	            SimpleDateFormat sim = new SimpleDateFormat("yyyy");
	            Date date =new Date();
//-------------------------------------------------------------------------------------------------------------------------	            
	            //依次生成六张表
	            for(int p =0;p<(maxMonth-minMonth)+1;p++) {
	            	int mm = (minMonth+p)%12;
	            	if(mm==0) {
	            		mm = 12;
	            	}
	            	WritableSheet sheet = book.createSheet(sim.format(date)+"年"+(mm)+"月", p);
	            
		            String[] str= {"班级名","讲师","班主任","上课地点"};
		            Label label =null;
		            for(int i=0;i<str.length;i++) {
		            	WritableCellFormat wc = new WritableCellFormat();
            			wc.setAlignment(Alignment.CENTRE);
		            	label=new Label(i,0,str[i],wc);
		            	sheet.addCell(label);
		            }
		            int bMonth = minMonth+p;//当前月
		            
		            int sdate = Integer.valueOf(sim.format(date));
		            int maxcount = DayCount.dayCount(sdate,bMonth);//当前月的最后一天
		            
		            String[] daycount = new String[maxcount];
		            
		            
		            //备注
		            label = new Label(maxcount+4,0,"备注");
		            sheet.addCell(label);
		            sheet.setColumnView(maxcount+4, 8);
		            //生成日期列
		            		for(int i=0;i<maxcount;i++) {
		            			daycount[i]= bMonth+"-"+(i+1);
		            			label = new Label(i+4,0,daycount[i]);
		            			sheet.addCell(label);
		            			sheet.setColumnView(i+4, 5);
		            		}
		            		
		            for(int i=0;i<list.size();i++) {
		            	//班级名
		            	String sname = list.get(i).sname;
            			WritableCellFormat wc = new WritableCellFormat();
            			wc.setAlignment(Alignment.CENTRE);
		            	label = new Label(0,i+1,sname,wc);
		            	sheet.addCell(label);
		            	sheet.setColumnView(0, 10);
		            }
		            for (int i = 0; i < list.size(); i++) {
		            	//讲师
						String teacher = list.get(i).glecname;
						WritableCellFormat wc = new WritableCellFormat();
            			wc.setAlignment(Alignment.CENTRE);
						label = new Label(1,i+1,teacher,wc);
						sheet.addCell(label);
						sheet.setColumnView(1, 10);
						
					}
		            for (int i = 0; i <list.size(); i++) {
		            	//班主任
						String hname = list.get(i).gHeadTname;
						WritableCellFormat wc = new WritableCellFormat();
            			wc.setAlignment(Alignment.CENTRE);
						label = new Label(2,i+1,hname,wc);
						sheet.addCell(label);
						sheet.setColumnView(2, 8);
					}
		            for (int i = 0; i <list.size(); i++) {
		            	//上课地点
						String loc = list.get(i).sloc;
						WritableCellFormat wc = new WritableCellFormat();
            			wc.setAlignment(Alignment.CENTRE);
						label = new Label(3,i+1,loc,wc);
						sheet.addCell(label);
						sheet.setColumnView(3, 11);
					}
//------------------------------------------------------------------------------------------------------------------------		            
	            for(int i=0;i<list.size();i++) {
		            	String startM = list.get(i).getsBeginDate().replaceAll("/\\d+","");//开始月
		            	String endM = list.get(i).getsEndDate().replaceAll("/\\d+",""); //结束月
//------------------------------------------------------------------------------------------------------------------------            	
		            	if(startM.equals(endM)&&startM.equals(String.valueOf(bMonth))) {//同一个月并在当前月
		            		int minday = Integer.valueOf(list.get(i).getsBeginDate().replaceAll("\\d+/", ""));//起始日
		            		int maxday = Integer.valueOf(list.get(i).getsEndDate().replaceAll("\\d+/", ""));//结束日
		            		String[] plan = new String[maxcount];
		            		
		            		if(list.get(i).getsSpecialDate()!=null) {
		            			plan = list.get(i).getsSpecialDate().split(",");
			            			for(int j=0;j<maxcount;j++) {
			            				if(j<= maxday && j>= minday){
			            					WritableCellFormat wc =new WritableCellFormat();
			            					wc.setBackground(Colour.LIGHT_BLUE);
			            					label = new Label(3+j,i+1,"全天",wc);
			            					sheet.addCell(label);
			            				}
			            				for(int k =0;k<plan.length;k++) {
			            					String c= plan[k].replaceAll("\\d+/", "");
			            					int m = Integer.valueOf(c.replaceAll("-\\d+",""));
			            					//
			            					if(plan[k].replaceAll("\\d+/\\d+-","").equals("1")) {
			            						WritableCellFormat wc =new WritableCellFormat();
			            						wc.setBackground(Colour.LAVENDER);
			            						label = new Label(3+m,i+1,"上午",wc);
			            						sheet.addCell(label);
			            					} 
			            					else if(plan[k].replaceAll("\\d+/\\d+-","").equals("2")) {
			            						WritableCellFormat wc =new WritableCellFormat();
			            						wc.setBackground(Colour.LAVENDER);
			            						label = new Label(3+m,i+1,"下午",wc);
			            						sheet.addCell(label);
			            					}
			            					else if(plan[k].replaceAll("\\d+/\\d+-","").equals("3")) {
			            						WritableCellFormat wc =new WritableCellFormat();
			            						wc.setBackground(Colour.LIGHT_GREEN);
			            						label = new Label(3+m,i+1,"休息",wc);
			            						sheet.addCell(label);
			            					} 
			            				}
			            			}
		            		}
		            		//没有特殊日期
		            		else{
		            			for(int j=minday;j<=maxday;j++) {
		            				WritableCellFormat wc = new WritableCellFormat();
		            				wc.setBackground(Colour.LIGHT_BLUE);
		            				label = new Label(3+j,i+1,"全天",wc);
		            				sheet.addCell(label);
		            			}
		            		}
			            }
//-------------------------------------------------------------------------------------------------------------------------		            	
		            	if(startM.equals(endM)&&!(startM.equals(String.valueOf(bMonth)))) {
		            		for(int j =0;j<maxcount;j++) {
		            			label = new Label(4+j,i+1, "");
		            			sheet.addCell(label);
		            		}
//		            		sheet.removeRow(i+1);
			            }
//发生跨月-------------------------------------------------------------------------------------------------------------------------		            	
		            	if(!startM.equals(endM)) {
			            	if (startM.equals(String.valueOf(bMonth))) {
			            		int minday = Integer.valueOf(list.get(i).getsBeginDate().replaceAll("\\d+/", ""));
			            		int maxday = maxcount;
			            		String[] plan = new String[maxcount];
			            		
			            		if(list.get(i).getsSpecialDate()!=null) {
				            		plan = list.get(i).getsSpecialDate().split(",");
				            		Arrayremove.remove(plan,String.valueOf(bMonth) );//保留该月特殊日期
				            		for(String temp:plan) {
				            		}
				            		for(int j=minday-1;j<maxcount;j++) {
										if(j<= maxday-1 && j>= minday-1){
											WritableCellFormat wc =new WritableCellFormat();
											wc.setBackground(Colour.LIGHT_BLUE);
											label = new Label(4+j,i+1,"全天",wc);
											sheet.addCell(label);
										}
										for(int k =0;k<plan.length;k++) {
											String c= plan[k].replaceAll("\\d+/", "");
											int m = Integer.valueOf(c.replaceAll("-\\d+",""));
											if(plan[k].replaceAll("\\d+/\\d+-","").equals("1")) {
												WritableCellFormat wc =new WritableCellFormat();
												wc.setBackground(Colour.LAVENDER);
												label = new Label(3+m,i+1,"上午",wc);
												sheet.addCell(label);
											}
											else if(plan[k].replaceAll("\\d+/\\d+-","").equals("2")) {
												WritableCellFormat wc =new WritableCellFormat();
												wc.setBackground(Colour.LAVENDER);
												label = new Label(3+m,i+1,"下午",wc);
												sheet.addCell(label);
											}
											else if(plan[k].replaceAll("\\d+/\\d+-","").equals("3")) {
												WritableCellFormat wc =new WritableCellFormat();
												wc.setBackground(Colour.LIGHT_GREEN);
												label = new Label(3+m,i+1,"休息",wc);
												sheet.addCell(label);
											} 
										}
									}
				            		for(int j=4;j<minday+3;j++) {//去重打印
				            			label = new Label(j,i+1,"");
				            			sheet.addCell(label);
				            		}
			            			
			            		}
			            		//没有特殊日期
			            		else{
			            			for(int j=minday;j<=maxday;j++) {
			            				WritableCellFormat wc = new WritableCellFormat();
			            				wc.setBackground(Colour.LIGHT_BLUE);
			            				label = new Label(3+j,i+1,"全天",wc);
			            				sheet.addCell(label);
			            			}
			            		}

			            	}
//--------------------------------------------------------------------------------------------------------------------------			            	
			            	else if(endM.equals(String.valueOf(bMonth))){
			            		int minday = 1;//第一天
			            		int maxday = Integer.valueOf(list.get(i).getsEndDate().replaceAll("\\d+/", ""));
			            		String[] plan = new String[maxcount];
			            		
			            		//没有特殊日期
			            		if(list.get(i).getsSpecialDate()!=null) {
			            			plan = list.get(i).getsSpecialDate().split(",");
			            			Arrayremove.remove(plan,String.valueOf(bMonth) );
			            			for(int j=0;j<maxday;j++) {
										if(j<= maxday-1 && j>= 0){
											WritableCellFormat wc =new WritableCellFormat();
											wc.setBackground(Colour.LIGHT_BLUE);
											label = new Label(4+j,i+1,"全天",wc);
											sheet.addCell(label);
											
										}
										for(int k =0;k<plan.length;k++) {
											String c= plan[k].replaceAll("\\d+/", "");
											int m = Integer.valueOf(c.replaceAll("-\\d+",""));
											if(plan[k].replaceAll("\\d+/\\d+-","").equals("1")) {
												
												WritableCellFormat wc =new WritableCellFormat();
												wc.setBackground(Colour.LAVENDER);
												label = new Label(3+m,i+1,"上午",wc);
												sheet.addCell(label);
											}
											
											else if(plan[k].replaceAll("\\d+/\\d+-","").equals("2")) {
												WritableCellFormat wc =new WritableCellFormat();
												wc.setBackground(Colour.LAVENDER);
												label = new Label(3+m,i+1,"下午",wc);
												sheet.addCell(label);
											}
											else if(plan[k].replaceAll("\\d+/\\d+-","").equals("3")) {
												WritableCellFormat wc =new WritableCellFormat();
												wc.setBackground(Colour.LIGHT_GREEN);
												label = new Label(3+m,i+1,"休息",wc);
												sheet.addCell(label);
											} 
										}
									}
			            			for(int j=maxday+4;j<=maxcount+3;j++) {
				            			label = new Label(j,i+1,"");
				            			sheet.addCell(label);
				            		}
			            			
			            			
			            		}
			            		else {
			            			for(int j=minday;j<=maxday;j++) {
			            				WritableCellFormat wc = new WritableCellFormat();
			            				wc.setBackground(Colour.LIGHT_BLUE);
			            				label = new Label(3+j,i+1,"全天",wc);
			            				sheet.addCell(label);
			            			}
								}
								
							}
//-------------------------------------------------------------------------------------------------------------------------			            	
			            	else if(Integer.valueOf(startM)<bMonth&&Integer.valueOf(endM)>bMonth){
			            		int minday = 1;
			            		int maxday = maxcount;
			            		String[] plan = new String[maxcount];
			            		
			            		if(list.get(i).getsSpecialDate()!=null) {
			            			plan = list.get(i).getsSpecialDate().split(",");
			            			Arrayremove.remove(plan,String.valueOf(bMonth) );
			            			for(int j=0;j<maxcount;j++) {
										if(j<= maxday-1 && j>= minday-1){
											WritableCellFormat wc =new WritableCellFormat();
											wc.setBackground(Colour.LIGHT_BLUE);
											label = new Label(4+j,i+1,"全天",wc);
											sheet.addCell(label);
											
										}
										else {
											label = new Label(4+j,i+1,"");
											sheet.addCell(label);
										}
										for(int k =0;k<plan.length;k++) {
											String c= plan[k].replaceAll("\\d+/", "");
											int m = Integer.valueOf(c.replaceAll("-\\d+",""));
											if(plan[k].replaceAll("\\d+/\\d+-","").equals("1")) {
												WritableCellFormat wc =new WritableCellFormat();
												wc.setBackground(Colour.LAVENDER);
												label = new Label(3+m,i+1,"上午",wc);
												sheet.addCell(label);
											}
											else if(plan[k].replaceAll("\\d+/\\d+-","").equals("2")) {
												WritableCellFormat wc =new WritableCellFormat();
												wc.setBackground(Colour.LAVENDER);
												label = new Label(3+m,i+1,"下午",wc);
												sheet.addCell(label);
											}
											else if(plan[k].replaceAll("\\d+/\\d+-","").equals("3")) {
												WritableCellFormat wc =new WritableCellFormat();
												wc.setBackground(Colour.LIGHT_GREEN);
												label = new Label(3+m,i+1,"休息",wc);
												sheet.addCell(label);
											} 
										}
									}
			            	
			            		}
							}
//--------------------------------------------------------------------------------------------------------------------------			            	
			            	else if(bMonth>Integer.valueOf(endM)) {
			            		for(int j =0;j<maxcount;j++) {
			            			label = new Label(4+j,i+1, "");
			            			sheet.addCell(label);
			            		}
//			            		sheet.removeRow(i+1);
			            	}
			            	
						  }
		            	
			           }
		            }
	            //写入并关闭
	            book.write();
	            book.close();
	            return true;
        }catch (IOException e) {
	            System.out.println(e);
	            return false;
	    }

	}

	
	public static boolean add(String path) {
		String sql = "insert into download values(SEQ_DOWNLOAD_DNO.nextval,?)";
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, path);
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
	
	public static List<String> all() {
		String sql = "select distinct downloadpath from download";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<String> downloadPaths = new ArrayList<String>();
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String downloadPath = rs.getString("downloadpath");
				downloadPaths.add(downloadPath);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, con);
		}
		return downloadPaths;
	}
}
