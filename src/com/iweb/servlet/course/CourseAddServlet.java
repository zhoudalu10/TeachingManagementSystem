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
 * Servlet implementation class CourseAddServlet
 */
@WebServlet("/CourseAddServlet")
public class CourseAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String cname = request.getParameter("cname");
		String cperString = request.getParameter("cper");
		
		boolean judge = CourseDAO.judge(cname, cperString);

		if (judge) {
			int cper = Integer.valueOf(cperString);
			Course course = new Course(cname, cper);
			boolean result = CourseDAO.add(course);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/page/CourseAdd.jsp").forward(request, response);
		} else {
			request.setAttribute("judge", judge);
			request.getRequestDispatcher("/page/CourseAdd.jsp").forward(request, response);
		}
	}

}
