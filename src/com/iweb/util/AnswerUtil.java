package com.iweb.util;

import com.iweb.DAO.QuestionDaoImpl;
import com.iweb.domain.Question;

import java.util.List;

/**
 *对题目答案的操作
 */
public class AnswerUtil {
    private static List<Question> questions = new QuestionDaoImpl().getQuestions();

    //根据题目id获取题目答案
    public static String getAnswerById(Integer id){
     for (int i=1;i<questions.size()+1;i++){
         if(id==i){
             return questions.get(i-1).getAnswer();
         }
     }
     return null;
    }

    //将String[]转为String字符串
    public static String getAnswer(String[] answers){
        String answer="";
        for(int i=0;i<answers.length;i++){
            answer+=answers[i];
        }
        return answer;
    }

}
