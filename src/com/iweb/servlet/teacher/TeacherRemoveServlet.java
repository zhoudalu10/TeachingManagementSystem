package com.iweb.servlet.teacher;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iweb.DAO.TeacherDAO;

/**
 * Servlet implementation class TeacherRemove
 */
@WebServlet("/TeacherRemoveServlet")
public class TeacherRemoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		int tno = Integer.valueOf(request.getParameter("tno"));
		boolean result = TeacherDAO.remove(tno);
		request.setAttribute("result", result);
		request.getRequestDispatcher("/TeacherListServlet").forward(request, response);

	}

}
