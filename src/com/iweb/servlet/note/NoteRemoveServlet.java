package com.iweb.servlet.note;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iweb.DAO.NoteDAO;

/**
 * Servlet implementation class NoteRemoveServlet
 */
@WebServlet("/NoteRemoveServlet")
public class NoteRemoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String notetime = request.getParameter("notetime");
		PrintWriter out = response.getWriter();
		boolean result = false;
		try {
			result = NoteDAO.remove(notetime);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("result", result);
		out.write(result+"");
		
		
	}

}
