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
 * Servlet implementation class GradeModifyServlet2
 */
@WebServlet("/GradeModifyServlet2")
public class GradeModifyServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		String gnoString = request.getParameter("gno");
		String gname = request.getParameter("gname");
		String gcourse = request.getParameter("gcourse");
		String gheadtname = request.getParameter("gheadtname");
		String glecname = request.getParameter("glecname");
		String gsnumString = request.getParameter("gsnum");
		String gpernowString = request.getParameter("gpernow");

		boolean judge = GradeDAO.judge(gnoString, gname, gcourse, gheadtname, glecname, gsnumString, gpernowString);

		if (judge) {
			int gno = Integer.valueOf(gnoString);
			int gsnum = Integer.valueOf(gsnumString);
			int gpernow = Integer.valueOf(gpernowString);
			Grade grade = new Grade(gno, gname, gcourse, gheadtname, glecname, gsnum, gpernow);
			boolean result = GradeDAO.modify(grade);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/page/GradeModify2.jsp").forward(request, response);
		} else {
			request.setAttribute("judge", judge);
			request.getRequestDispatcher("/page/GradeModify2.jsp").forward(request, response);
		}
	}

}
