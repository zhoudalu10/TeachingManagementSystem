package com.iweb.servlet.register;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iweb.DAO.UserDAO;
import com.iweb.entity.User;

/**
 * Servlet implementation class MyServlet
 */
@WebServlet("/RegisteredServlet")
public class RegisteredServlet extends HttpServlet {
	
	private static final long serialVersionid = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String data = request.getParameter("data");
		PrintWriter out = response.getWriter();
		String result = null;
		String matcher="(.*)(-)(.*)(-)(.*)(-)(.*)(-)(.*)";
		Pattern pattern = Pattern.compile(matcher);
		Matcher m = pattern.matcher(data);
		String uname = null;
		String pwd = null;
		String pwded = null;
		String position = null;
		String uclass = null;
		if(m.find()) {
			uname = m.group(1);
			pwd = m.group(3);
			pwded = m.group(5);
			position = m.group(7);
			uclass = m.group(9);
		}
		if (pwd.equals(pwded)) {
			User user = new User(uname,pwd,position,uclass);
			try {
				if (UserDAO.exist(uname) > 0) {
					result="1";
				} else {
					try {
						UserDAO.add(user);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					result="0";

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			result="2";
		}
		request.setAttribute("result", result);
		out.write(result);
	}

}