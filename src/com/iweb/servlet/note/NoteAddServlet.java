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
 * Servlet implementation class NoteAddServlet
 */
@WebServlet("/NoteAddServlet")
public class NoteAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String notedata = request.getParameter("data");
		String notegrade = request.getParameter("notegrade");
		PrintWriter out = response.getWriter();
		Note note = new Note(notedata);
		note.setNotegrade(notegrade);
		boolean result = false;
		try {
			result = NoteDAO.add(note);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("result", result);
		out.write(result + "");

	}

}
