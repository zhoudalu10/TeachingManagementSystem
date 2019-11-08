package com.iweb.servlet.course;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iweb.DAO.CourseDAO;

/**
 * Servlet implementation class CourseRemoveServlet
 */
@WebServlet("/CourseRemoveServlet")
public class CourseRemoveServlet extends HttpServlet {
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
		boolean result = CourseDAO.remove(cno);
		request.setAttribute("result", result);
		request.getRequestDispatcher("/CourseListServlet").forward(request, response);

	}
}
