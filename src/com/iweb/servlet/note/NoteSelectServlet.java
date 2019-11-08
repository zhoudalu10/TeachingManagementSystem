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
 * Servlet implementation class NoteSelectServlet
 */
@WebServlet("/NoteSelectServlet")
public class NoteSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String notetime = request.getParameter("notetime");
		PrintWriter out = response.getWriter();
		String notedata = null;
		try {
			 notedata = NoteDAO.select(notetime);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("notedata", notedata);
		out.write(notedata);
	}

}
