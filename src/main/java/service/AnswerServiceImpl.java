package service;

import dao.AnswerMapper;
import pojo.Answer;

import java.util.List;

public class AnswerServiceImpl implements AnswerService {
    private AnswerMapper answerMapper;
    public void setAnswerMapper(AnswerMapper answerMapper){
        this.answerMapper = answerMapper;
    }

    public List<Answer> queryByQuestionID(int id) {
        return answerMapper.queryByQuestionID(id);
    }

    public Answer queryByAnswerID(int id) {
        return answerMapper.queryByAnswerID(id);
    }

    public int addAnswer(Answer answer) {
        return answerMapper.addAnswer(answer);
    }

    public int deleteAnswer(int id) {
        return answerMapper.deleteAnswer(id);
    }

    public int updateAnswer(Answer answer) {
        return answerMapper.updateAnswer(answer);
    }
}
