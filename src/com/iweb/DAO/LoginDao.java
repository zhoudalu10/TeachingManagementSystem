package com.iweb.DAO;

import com.iweb.entity.Use;

public interface LoginDao {
	Use checkLoginDao(String uname,String pwd);

	Use checkUnoDao(String uno);

	Use checkOuttDao(String uname, String pwd);

}
