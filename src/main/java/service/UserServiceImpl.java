package service;

import dao.UserMapper;
import pojo.User;

import java.util.List;

public class UserServiceImpl implements UserService{

    private UserMapper userMapper;
    public void setUserMapper(UserMapper userMapper){
        this.userMapper = userMapper;//有了此函数，才能在spring-service中注入成功
    }
    public int addStudentUser(User user) {
        return userMapper.addStudentUser(user);
    }

    public int deleteStudentUserByID(int id) {
        return userMapper.deleteStudentUserByID(id);
    }

    public int updateStudentUser(User user) {
        return userMapper.updateStudentUser(user);
    }

    public User queryStudentById(int id) {
        return userMapper.queryStudentById(id);
    }

    public List<User> queryAllStudentUser() {
        return userMapper.queryAllStudentUser();
    }
}
