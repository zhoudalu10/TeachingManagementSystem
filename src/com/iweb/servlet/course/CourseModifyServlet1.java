package com.iweb.servlet.course;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iweb.DAO.CourseDAO;
import com.iweb.entity.Course;

/**
 * Servlet implementation class CourseModifyServlet1
 */
@WebServlet("/CourseModifyServlet1")
public class CourseModifyServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		int cno = Integer.valueOf(request.getParameter("cno"));
		Course course = CourseDAO.select(cno);
		request.setAttribute("course", course);
		request.getRequestDispatcher("/page/CourseModify2.jsp").forward(request, response);

	}

}
