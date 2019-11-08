package com.iweb.service;

import com.iweb.DAO.LoginDao;
import com.iweb.DAO.LoginDaoImpl;
import com.iweb.entity.Use;





public class LoginServiceImpl implements LoginService{
//	创建dao过度层
	LoginDao ld = new LoginDaoImpl();
//	检验用户登录信息
	
	public Use checkLoginSerivce(String uname, String pwd) {
	
		return ld.checkLoginDao(uname,pwd);
	}

	
	@Override
	public Use checkUnoService(String uno) {
		// TODO Auto-generated method stub
		return ld.checkUnoDao(uno);
	}


	@Override
	public Use checkOuttSerivce(String uname, String pwd) {
		// TODO Auto-generated method stub
		return ld.checkOuttDao(uname,pwd);
	}
}

