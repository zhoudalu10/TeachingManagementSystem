package com.iweb.servlet.classroom;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iweb.DAO.ClassroomDAO;
import com.iweb.entity.Classroom;

/**
 * Servlet implementation class ClassroomModifyServlet1
 */
@WebServlet("/ClassroomModifyServlet1")
public class ClassroomModifyServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		int rno = Integer.valueOf(request.getParameter("rno"));
		Classroom classroom = ClassroomDAO.select(rno);
		request.setAttribute("classroom", classroom);
		request.getRequestDispatcher("/page/ClassroomModify2.jsp").forward(request, response);

	}

}
