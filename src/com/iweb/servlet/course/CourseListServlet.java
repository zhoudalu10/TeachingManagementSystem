package com.iweb.servlet.course;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iweb.DAO.CourseDAO;
import com.iweb.entity.Course;

/**
 * Servlet implementation class CourseListServlet
 */
@WebServlet("/CourseListServlet")
public class CourseListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		List<Course> courses = CourseDAO.all();
		request.setAttribute("courses", courses);
		request.getRequestDispatcher("/page/CourseList.jsp").forward(request, response);
	}

}
