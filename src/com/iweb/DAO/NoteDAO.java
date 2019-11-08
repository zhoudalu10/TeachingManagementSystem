package com.iweb.DAO;

import java.sql.SQLException;
import java.util.List;

import com.iweb.entity.Grade;
import com.iweb.entity.Note;

public class NoteDAO extends RegisterBaseDAO {

	public static boolean add(Note note) throws SQLException {
		sqlMapClient.insert("note.add", note);
		return true;
	}

	public static List<Note> allNote(String notegrade) throws SQLException {
		List<Note> notes = null;
		try {
			notes = sqlMapClient.queryForList("note.list", notegrade);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return notes;

	}

	public static String select(String notetime) throws SQLException {
		return (String) sqlMapClient.queryForObject("note.select", notetime);
	}

	public static boolean modify(Note note) throws SQLException {
		sqlMapClient.update("note.modify", note);
		return true;
	}

	public static boolean remove(String notetime) throws SQLException {
		sqlMapClient.delete("note.remove", notetime);
		return true;
	}

}
