package com.iweb.service;

import com.iweb.entity.Use;

public interface LoginService {
	Use checkLoginSerivce(String uname,String pwd);
	
	Use checkUnoService(String uno);

	Use checkOuttSerivce(String uname,String pwd);
}
