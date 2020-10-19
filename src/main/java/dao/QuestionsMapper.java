package dao;


import org.apache.ibatis.annotations.Param;
import pojo.Questions;

import java.util.List;

//考虑的是，所有老师都可以回答问题，
// 否则，没有队伍的学生是没有办法找到对应的老师的，
// 但是没有队伍的学生应该有权限提问题，
// 考虑在answers类中加一个回答的老师的userID
// 之后再说，先把老师的弄完
public interface QuestionsMapper {
    //查询所有的问题,这个应该不常用，问题数量巨大时，会给数据库造成很大的负担
    List<Questions> queryAllQuestions();

    //查询若干个问题
    List<Questions> queryAnyQuestions(@Param("start") int start, @Param("len") int len);


}
