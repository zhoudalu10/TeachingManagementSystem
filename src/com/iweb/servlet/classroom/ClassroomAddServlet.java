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
 * Servlet implementation class ClassroomAddServlet
 */
@WebServlet("/ClassroomAddServlet")
public class ClassroomAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		String rloc = request.getParameter("rloc");
		String rnumString = request.getParameter("rnum");

		boolean judge = ClassroomDAO.judge(rloc, rnumString);

		if (judge) {
			int rnum = Integer.valueOf(rnumString);
			Classroom classroom = new Classroom(rloc, rnum);
			boolean result = ClassroomDAO.add(classroom);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/page/ClassroomAdd.jsp").forward(request, response);
		} else {
			request.setAttribute("judge", judge);
			request.getRequestDispatcher("/page/ClassroomAdd.jsp").forward(request, response);
		}
	}

}
