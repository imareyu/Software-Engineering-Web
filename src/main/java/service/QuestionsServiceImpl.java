package service;

import dao.QuestionsMapper;
import pojo.Questions;
import pojo.User;

import java.util.List;

public class QuestionsServiceImpl implements QuestionsService{
    private QuestionsMapper questionsMapper;
    public void setQuestionsMapper(QuestionsMapper questionsMapper){
        this.questionsMapper = questionsMapper;
    }

    public List<Questions> queryAllQuestions() {
        return questionsMapper.queryAllQuestions();
    }

    public List<Questions> queryAnyQuestions(int start, int len) {
        return questionsMapper.queryAnyQuestions(start,len);
    }

    public Questions queryByQuestionID(int QuestionID) {
        return questionsMapper.queryByQuestionID(QuestionID);
    }

    public List<Questions> queryByUserID(String UserID) {
        return questionsMapper.queryByUserID(UserID);
    }

    public List<Questions> queryAllAnswered() {
        return questionsMapper.queryAllAnswered();
    }

    public List<Questions> queryAllNotAnswered() {
        return questionsMapper.queryAllNotAnswered();
    }

    public int addQuestion(Questions questions) {
        return questionsMapper.addQuestion(questions);
    }

    public int deleteAQuestion(int QuestionID) {
        return questionsMapper.deleteAQuestion(QuestionID);
    }

    public int updateQuestion(Questions questions) {
        return questionsMapper.updateQuestion(questions);
    }
}