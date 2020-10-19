package service;

import org.apache.ibatis.annotations.Param;
import pojo.Questions;

import java.util.List;

public interface QuestionsService {
    //查询所有的问题,这个应该不常用，问题数量巨大时，会给数据库造成很大的负担
    List<Questions> queryAllQuestions();

    //查询若干个问题
    List<Questions> queryAnyQuestions(@Param("start") int start, @Param("len") int len);

    //根据QuestionID查询
    Questions queryByQuestionID(int QuestionID);

    //根据UserID查询
    List<Questions> queryByUserID(String UserID);

    //查询AnsState为yes的记录，回答过的问题
    List<Questions> queryAllAnswered();

    //查询AnsState为no的记录，没回答过的问题
    List<Questions> queryAllNotAnswered();

    //增加一条记录
    int addQuestion(Questions questions);

    //删除一条记录，根据QuestionID，有点想要在这里直接把问题的答案也删掉来着,,,
    int deleteAQuestion(int QuestionID);

    //修改一条记录
    int updateQuestion(Questions questions);
}
