package com.iweb.DAO;

import java.sql.SQLException;
import java.util.List;

import com.iweb.entity.Grade;
import com.iweb.entity.User;


public class UserDAO extends RegisterBaseDAO {
    /*
     * ����û��б�
     */
    public static boolean add(User user) throws SQLException {
         sqlMapClient.insert("user.add", user);
         return true;
    }
    
    
    public int login(User user) throws SQLException {
        return (int)sqlMapClient.update("user.login", user);
    }
    
    public static int exist(String uname) throws SQLException {
        return (int)sqlMapClient.queryForObject("user.exist", uname);
    }
    
    public static List<Grade> allGrades()throws SQLException{
    	List<Grade> grades = null;
    	try {
    		grades = sqlMapClient.queryForList("Grade.all");
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	return grades;
    	
    }
}
