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
import com.iweb.entity.Note;

/**
 * Servlet implementation class NoteUpdateServlet
 */
@WebServlet("/NoteUpdateServlet")
public class NoteUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String notetime = request.getParameter("notetime");
		String notedata = request.getParameter("notedata");
		PrintWriter out = response.getWriter();
		Note note = new Note(notetime,notedata);
		boolean result = false;
		try {
			result = NoteDAO.modify(note);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("result", result);
		out.write(result+"");
		
	}

}
