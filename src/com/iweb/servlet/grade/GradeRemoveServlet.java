package com.iweb.servlet.grade;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iweb.DAO.GradeDAO;

/**
 * Servlet implementation class GradeRemoveServlet
 */
@WebServlet("/GradeRemoveServlet")
public class GradeRemoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		int gno = Integer.valueOf(request.getParameter("gno"));
		boolean result = GradeDAO.remove(gno);
		request.setAttribute("result", result);
		request.getRequestDispatcher("/GradeListServlet").forward(request, response);

	}

}
