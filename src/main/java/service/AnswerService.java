package service;

import pojo.Answer;

import java.util.List;

public interface AnswerService {
    //查询某个问题的所有回答
    List<Answer> queryByQuestionID(int id);

    //根据AnswerID查询回答
    Answer queryByAnswerID(int id);

    //增加一条回答
    int addAnswer(Answer answer);

    //删除一条回答，根据AnswerID
    int deleteAnswer(int id);

    //修改回答
    int updateAnswer(Answer answer);
}
