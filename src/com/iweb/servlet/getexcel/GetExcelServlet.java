package com.iweb.servlet.getexcel;

import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iweb.DAO.ExcelDAO;
import jxl.write.WriteException;
import sun.misc.BASE64Encoder;

/**
 * Servlet implementation class GetExcelServlet1
 */
@WebServlet("/GetExcelServlet")
public class GetExcelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		String path = request.getSession().getServletContext().getRealPath("/");

		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		String fileName = fmt.format(new Date()) + ".xls";
		String filePath = path + "excel/" + fileName;
		boolean result = false;
		try {
			result = ExcelDAO.getExcel(filePath);
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ExcelDAO.add(fileName);
		request.setAttribute("result", result);
		out.write(result + "");
	}

}
