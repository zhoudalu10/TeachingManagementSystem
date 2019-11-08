package com.iweb.servlet.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iweb.entity.Use;
import com.iweb.service.LoginService;
import com.iweb.service.LoginServiceImpl;

public class CookieServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 设置请求编码格式
		req.setCharacterEncoding("utf-8");
		// 设置相应编码格式
		resp.setContentType("text/html;charset=utf-8");
		// 获取Cookie
		Cookie[] cks = req.getCookies();
		if (cks != null) {
			String uno = "";
			for (Cookie c : cks) {
				if ("uno".equals(c.getName())) {
					uno = c.getValue();
				}
			}
			if ("".equals(uno)) {
				// 请求转发
				req.getRequestDispatcher("page").forward(req, resp);
				return;
			} else {
				// 校验ID用户信息
				// 获取业务层对象
				LoginService ls = new LoginServiceImpl();

				Use u = ls.checkUnoService(uno);
				if (u != null) {
					// 将用户数据存储到session对象中
					req.getSession().setAttribute("use", u);
					// 重定向
					resp.sendRedirect("/login/main");
					return;
				} else {
					// 请求转发
					req.getRequestDispatcher("page").forward(req, resp);
					return;
				}
			}
		} else {
			req.getRequestDispatcher("page").forward(req, resp);
		}
	}
}