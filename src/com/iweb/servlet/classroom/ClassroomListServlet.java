package com.iweb.servlet.classroom;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iweb.DAO.ClassroomDAO;
import com.iweb.entity.Classroom;

/**
 * Servlet implementation class ClassroomListServlet
 */
@WebServlet("/ClassroomListServlet")
public class ClassroomListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		List<Classroom> classrooms = ClassroomDAO.all();
		request.setAttribute("classrooms", classrooms);
		request.getRequestDispatcher("/page/ClassroomList.jsp").forward(request, response);
	}

}
