package com.iweb.service;

import com.iweb.domain.Question;

import java.util.List;

public interface QuestionService {
    //获取所有试题内容
    List<Question> getQuestions(); 
    //根据ID获取试题内容
    List<Question> getQuestionsById(List<Integer> errorsId);
}
