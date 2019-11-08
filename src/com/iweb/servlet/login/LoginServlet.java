package com.iweb.servlet.login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.iweb.entity.Use;
import com.iweb.service.LoginService;
import com.iweb.service.LoginServiceImpl;

/**
 * Servlet implementation class LoginServlet1
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String uname = request.getParameter("uname");
		String pwd = request.getParameter("pwd");

		LoginService ls = new LoginServiceImpl();
		Use u = ls.checkLoginSerivce(uname, pwd);
		String result = "0";
		if (u != null) {
			HttpSession hs = request.getSession();
			hs.setAttribute("use", u);
			if (u.getPosition().equals("学生")) {
				result = "1";
			} else {
				result = "2";
			}
		}
		request.setAttribute("result", result);
		out.write(result);
	}
}
