package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pojo.User;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

    @RequestMapping("/goToLogin")
    public String ToLogin(){
//        System.out.println("正在查询");
//        userService.queryAllStudentUser();
        System.out.println("即将跳转");
        return "login";
    }

    //登录
    @RequestMapping("/login")
    public String login(String userID, String userPassword, Model model, HttpServletRequest request){
        User user = userService.queryStudentById(Integer.parseInt(userID));
        System.out.println("用户id:"+userID);
        System.out.println("用户密码:"+userPassword);

        if(user == null || !userPassword.equals(user.getUserPassword())){//不相等
            model.addAttribute("error","用户名或密码错误");
            System.out.println("用户名或密码错误");
            return "login";
        }
        else{
            request.getSession().setAttribute("UserSession",user);//把对象存下来
        }
        return "home";
    }

    //基本信息管理
    @RequestMapping("Basic_Infor_Manage")
    public String Basic_Infor_Manage(){
        return "InforManage";
    }

    //到修改密码页面
    @RequestMapping("toModifyPassword")
    public String modifyPassword(){
        return "modifyPassword";
    }
}
