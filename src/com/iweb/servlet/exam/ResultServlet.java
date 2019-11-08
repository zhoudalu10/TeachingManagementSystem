package com.iweb.servlet.exam;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iweb.domain.Question;
import com.iweb.service.QuestionService;
import com.iweb.service.QuestionServiceImpl;
import com.iweb.util.AnswerUtil;

@WebServlet("/resultServlet")
public class ResultServlet extends HttpServlet {
	private QuestionService service = new QuestionServiceImpl();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 成绩
		int score = 0;
		// 错题id
		List<Integer> errorsId = new ArrayList<>();
		// 记录错题
		List<Question> errors = new ArrayList<>();
		Map<String, String[]> map = request.getParameterMap();
		Set<Map.Entry<String, String[]>> entries = map.entrySet();
		for (Map.Entry<String, String[]> entry : entries) {
			int id = Integer.parseInt(entry.getKey());
			String answerById = AnswerUtil.getAnswerById(id);
			String answer = AnswerUtil.getAnswer(entry.getValue());
			if (answer.equals(answerById)) {
				if (id <= 30) {
					score += 4;
				} else {
					score += 6;
				}
			} else {
				errorsId.add(id);
			}
		}
		errors = service.getQuestionsById(errorsId);
		request.setAttribute("score", score);
		request.setAttribute("errors", errors);
		// request.getRequestDispatcher(request.getContextPath()+"/result.jsp").forward(request,response);
		request.getRequestDispatcher("page/exam/result.jsp").forward(request, response);

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}
}
