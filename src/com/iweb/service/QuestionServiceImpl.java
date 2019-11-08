package com.iweb.service;

import com.iweb.DAO.QuestionDaoImpl;
import com.iweb.domain.Question;
import com.iweb.service.QuestionService;
import com.iweb.util.RandomUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class QuestionServiceImpl implements QuestionService {
    private QuestionDaoImpl qdi = new QuestionDaoImpl();
    @Override
    public List<Question> getQuestions() {
        List<Question> questions = qdi.getQuestions();
        List<Question>  realQuestions = new ArrayList<>();
        Set<Integer> randoms = RandomUtil.getRandoms(0, 29);
        for(Integer random:randoms){
            realQuestions.add(questions.get(random));
        }
        Set<Integer> randoms2 = RandomUtil.getRandoms(30, 49);
        for(Integer random:randoms2){
            realQuestions.add(questions.get(random));
        }
        return realQuestions;
    }

    @Override
    public List<Question> getQuestionsById(List<Integer> errorsId) {
        return qdi.getQuestionsById(errorsId);
	}
}
