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
 * Servlet implementation class ClassroomModifyServlet2
 */
@WebServlet("/ClassroomModifyServlet2")
public class ClassroomModifyServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		String rnoString = request.getParameter("rno");
		String rloc = request.getParameter("rloc");
		String rnumString = request.getParameter("rnum");

		boolean judge = ClassroomDAO.judge(rnoString, rloc, rnumString);

		if (judge) {
			int rno = Integer.valueOf(rnoString);
			int rnum = Integer.valueOf(rnumString);
			Classroom classroom = new Classroom(rno, rloc, rnum);
			boolean result = ClassroomDAO.modify(classroom);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/page/ClassroomModify2.jsp").forward(request, response);
		} else {
			request.setAttribute("judge", judge);
			request.getRequestDispatcher("/page/ClassroomModify2.jsp").forward(request, response);
		}
	}

}
