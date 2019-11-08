package com.iweb.DAO;

import com.iweb.DAO.QuestionDao;
import com.iweb.domain.Question;
import com.iweb.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class QuestionDaoImpl implements QuestionDao {
    @Override
    public List<Question> getQuestions() {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ptmt = null;
        String sql = "select * from question";
        List<Question> list =  new ArrayList<>();
        try {
            conn = JDBCUtils.getConnection();
            ptmt = conn.prepareStatement(sql);
            rs = ptmt.executeQuery();
            while(rs.next()){
                Question question = new Question();
                question.setId(rs.getInt("id"));
                question.setType(rs.getInt("type"));
                question.setItem(rs.getString("item"));
                question.setOptionA(rs.getString("optionA"));
                question.setOptionB(rs.getString("optionB"));
                question.setOptionC(rs.getString("optionC"));
                question.setOptionD(rs.getString("optionD"));
                question.setAnswer(rs.getString("answer"));
                list.add(question);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

	@Override
	public List<Question> getQuestionsById(List<Integer> errorsId) {
		Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<Question> questions = new ArrayList<>();
     if(0==errorsId.size()){
         return null;
     }
     String sql = "select * from question where 1=2";
     for(int i=0;i<errorsId.size();i++){
         sql+=" or id ="+errorsId.get(i);
     }
        try {
            conn = JDBCUtils.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                Question question = new Question();
                question.setId(rs.getInt("id"));
                question.setType(rs.getInt("type"));
                question.setItem(rs.getString("item"));
                question.setOptionA(rs.getString("optionA"));
                question.setOptionB(rs.getString("optionB"));
                question.setOptionC(rs.getString("optionC"));
                question.setOptionD(rs.getString("optionD"));
                question.setAnswer(rs.getString("answer"));
                questions.add(question);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  questions;
	}
}
