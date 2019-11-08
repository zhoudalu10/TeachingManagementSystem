package com.iweb.servlet.note;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iweb.DAO.NoteDAO;
import com.iweb.entity.Note;

import net.sf.json.JSONArray;

/**
 * Servlet implementation class GradeSelectServlet
 */
@WebServlet("/GradeSelectServlet")
public class GradeSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String notegrade = request.getParameter("notegrade");
		PrintWriter out = response.getWriter();
		List<Note> notes = null;
		try {
			notes = NoteDAO.allNote(notegrade);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		JSONArray jsonArray = JSONArray.fromObject(notes);
		out.print(jsonArray);
	}

}
