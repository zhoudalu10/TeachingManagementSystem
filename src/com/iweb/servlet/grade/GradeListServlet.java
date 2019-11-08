package com.iweb.servlet.grade;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iweb.DAO.GradeDAO;
import com.iweb.entity.Grade;

/**
 * Servlet implementation class GradeListServlet
 */
@WebServlet("/GradeListServlet")
public class GradeListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		List<Grade> grades = GradeDAO.all();
		request.setAttribute("grades", grades);
		request.getRequestDispatcher("/page/GradeList.jsp").forward(request, response);
	}

}
