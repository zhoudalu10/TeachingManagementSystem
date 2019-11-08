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
 * Servlet implementation class CourseModifyServlet2
 */
@WebServlet("/CourseModifyServlet2")
public class CourseModifyServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		String cnoString = request.getParameter("cno");
		String cname = request.getParameter("cname");
		String cperString = request.getParameter("cper");

		boolean judge = CourseDAO.judge(cnoString, cname, cperString);

		if (judge) {
			int cno = Integer.valueOf(cnoString);
			int cper = Integer.valueOf(cperString);
			Course course = new Course(cno, cname, cper);
			boolean result = CourseDAO.modify(course);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/page/CourseModify2.jsp").forward(request, response);
		} else {
			request.setAttribute("judge", judge);
			request.getRequestDispatcher("/page/CourseModify2.jsp").forward(request, response);
		}
	}

}
