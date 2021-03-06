package service;

import pojo.User;

import java.util.List;

public interface UserService {
    //增加一名学生用户
    int addStudentUser(User user);

    //增加一名教师用户
    int addTeacherUser(User user);

    //删除一个学生用户
    int deleteStudentUserByID(String id);

    //删除一个教师用户，根据UserID
    int deleteTeacherUserByID(String id);

    //修改一个学生用户姓名,此方法过于复杂，由service层实现
//    int updateStudentUserName(User user);

    //修改一个学生用户的信息（除了姓名之外的信息）
    int updateStudentUser(User user);

    //更新教师的信息
    int updateTeacherUser(User user);

    //更新管理员用户的信息，（密码）
    int updateAdminiUser(User user);

    //查询一个学生的信息（根据用户的用户id，即userID,不是数据库id）
    User queryStudentById(String id);
    //查询所有学生的信息
    List<User> queryAllStudentUser();

    //根据userid查询老师
    User queryTeacherById(String id);

    //查询所有的教师用户
    List<User> queryAllTeacher();

    //根据userid查询管理员
    User queryAdministratorById(String id);

}
