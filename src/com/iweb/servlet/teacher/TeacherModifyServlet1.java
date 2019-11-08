package com.iweb.servlet.teacher;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iweb.DAO.TeacherDAO;
import com.iweb.entity.Teacher;

/**
 * Servlet implementation class TeacherModifyServlet
 */
@WebServlet("/TeacherModifyServlet1")
public class TeacherModifyServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		int tno = Integer.valueOf(request.getParameter("tno"));
		Teacher teacher = TeacherDAO.select(tno);
		request.setAttribute("teacher", teacher);
		request.getRequestDispatcher("/page/TeacherModify2.jsp").forward(request, response);

	}

}
