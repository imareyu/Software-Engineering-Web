package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pojo.User;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    @RequestMapping("/Basic_Infor_Manage")
    public String Basic_Infor_Manage(){
        return "InforManage";
    }

    //到修改密码页面
    @RequestMapping("/toModifyPassword")
    public String toModifyPassword(){
        return "modifyPassword";
    }

    //修改密码并且注销要求重新登录
    @RequestMapping("/modifyPassword")
    public String modifyPassword(String oldPassword, String newPassword, HttpServletRequest request, HttpServletResponse response){
        User user = (User) request.getSession().getAttribute("UserSession");
        if(!user.getUserPassword().equals(oldPassword)){
            //原密码输错了,返回首页
            System.out.println("密码未修改，返回home");

            return "forward:/user/home";
        }
        user.setUserPassword(newPassword);//否则，修改密码,写入数据库，注销，返回登录页
        userService.updateStudentUser(user);//写入数据库
        request.getSession().invalidate();//这一步还需要修改
        System.out.println("密码已修改，返回login");
        return "redirect:/user/login";
    }
}
