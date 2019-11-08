package com.iweb.servlet.classroom;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iweb.DAO.ClassroomDAO;

/**
 * Servlet implementation class ClassroomRemoveServlet
 */
@WebServlet("/ClassroomRemoveServlet")
public class ClassroomRemoveServlet extends HttpServlet {
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
		boolean result = ClassroomDAO.remove(rno);
		request.setAttribute("result", result);
		request.getRequestDispatcher("/ClassroomListServlet").forward(request, response);

	}

}
