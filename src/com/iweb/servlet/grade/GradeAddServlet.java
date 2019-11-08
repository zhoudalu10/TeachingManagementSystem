package com.iweb.servlet.grade;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iweb.DAO.GradeDAO;
import com.iweb.entity.Grade;

/**
 * Servlet implementation class GradeAddServlet
 */
@WebServlet("/GradeAddServlet")
public class GradeAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		String gname = request.getParameter("gname");
		String gcourse = request.getParameter("gcourse");
		String gheadtname = request.getParameter("gheadtname");
		String glecname = request.getParameter("glecname");
		String gsnumString = request.getParameter("gsnum");
		String gpernowString = request.getParameter("gpernow");

		boolean judge = GradeDAO.judge(gname, gcourse, gheadtname, glecname, gsnumString, gpernowString);

		if (judge) {
			int gsnum = Integer.valueOf(gsnumString);
			int gpernow = Integer.valueOf(gpernowString);
			Grade grade = new Grade(gname, gcourse, gheadtname, glecname, gsnum, gpernow);
			boolean result = GradeDAO.add(grade);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/page/GradeAdd.jsp").forward(request, response);
		} else {
			request.setAttribute("judge", judge);
			request.getRequestDispatcher("/page/GradeAdd.jsp").forward(request, response);
		}
	}

}
