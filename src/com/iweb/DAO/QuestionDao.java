package com.iweb.DAO;

import com.iweb.domain.Question;

import java.util.List;

public interface QuestionDao {
     List<Question> getQuestions();
     List<Question> getQuestionsById(List<Integer> errorsId);
}
