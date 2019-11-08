package com.iweb.servlet.teacher;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iweb.DAO.TeacherDAO;
import com.iweb.entity.Teacher;

/**
 * Servlet implementation class TeacherListServlet
 */
@WebServlet("/TeacherListServlet")
public class TeacherListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		List<Teacher> teachers = TeacherDAO.all();
		request.setAttribute("teachers", teachers);
		request.getRequestDispatcher("/page/TeacherList.jsp").forward(request, response);
	}

}
