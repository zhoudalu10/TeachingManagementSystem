package com.iweb.servlet.exam;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iweb.domain.Question;
import com.iweb.service.QuestionServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/questionServlet")
public class QuestionServlet extends HttpServlet {
    private QuestionServiceImpl qsi = new QuestionServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Question> questions = qsi.getQuestions();
        //序列化为json传给index.jsp页面
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),questions);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   this.doPost(request,response);
    }
}
