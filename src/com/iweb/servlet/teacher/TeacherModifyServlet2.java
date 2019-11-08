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
 * Servlet implementation class TeacherModifyServlet2
 */
@WebServlet("/TeacherModifyServlet2")
public class TeacherModifyServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		String tnoString = request.getParameter("tno");
		String tname = request.getParameter("tname");
		String tsex = request.getParameter("tsex");
		String tageString = request.getParameter("tage");
		String tlev = request.getParameter("tlev");
		String ttel = request.getParameter("ttel");
		String tqq = request.getParameter("tqq");

		boolean judge = TeacherDAO.judge(tnoString, tname, tsex, tageString, tlev, ttel, tqq);

		if (judge) {
			int tno = Integer.valueOf(tnoString);
			int tage = Integer.valueOf(tageString);
			Teacher teacher = new Teacher(tno, tname, tsex, tage, tlev, ttel, tqq);
			boolean result = TeacherDAO.modify(teacher);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/page/TeacherModify2.jsp").forward(request, response);
		} else {
			request.setAttribute("judge", judge);
			request.getRequestDispatcher("/page/TeacherModify2.jsp").forward(request, response);
		}

	}

}
