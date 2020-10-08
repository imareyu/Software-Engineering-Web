package dao;

import pojo.User;

import java.util.List;

public interface UserMapper {
    //增加一名学生用户
    int addStudentUser(User user);

    //删除一个学生用户
    int deleteStudentUserByID(int id);

    //修改一个学生用户姓名,此方法过于复杂，由service层实现
    //int updateStudentUserName(User user);

    //修改一个学生用户的信息（除了姓名之外的信息）
    int updateStudentUser(User user);

    //查询一个学生的信息（根据用户的用户id，即userID,不是数据库id）
    User queryStudentById(String id);
    //查询所有学生的信息
    List<User> queryAllStudentUser();

    //根据userid查询老师用户
    User queryTeacherById(String id);

    //根据userid查询管理员用户
    User queryAdministratorById(String id);

}
