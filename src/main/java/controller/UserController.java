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
        User user = userService.queryStudentById(userID);
        System.out.println("用户id:"+userID);
        System.out.println("用户密码:"+userPassword);

        if(user == null){//没有此学生,查询老师，原则上来说，老师和学生的用户名不同
            user = userService.queryTeacherById(userID);//查询老师用户
            if(user == null)//不是老师，查询管理员
            {
                user = userService.queryAdministratorById(userID);
                if(user == null){//不是学生，老师和管理员
                    model.addAttribute("error","用户名或密码错误");
                    System.out.println("不是学生、老师和管理员");
                    return "login";
                }else{//管理员用户
                    if(user.getUserPassword().equals(userPassword)){
                        //密码正确
                        request.getSession().setAttribute("UserSession",user);
                        System.out.println("管理员用户"+userID+"：成功登录");
                        return "administratorHome";//返回到教师首页
                    }else{//密码错误
                        model.addAttribute("error","用户名或密码错误");
                        System.out.println("管理员用户：用户名或密码错误");
                        return "login";
                    }
                }
            }else{//是老师
                if(user.getUserPassword().equals(userPassword)){//密码正确
                    request.getSession().setAttribute("UserSession",user);
                    System.out.println("教师用户"+userID+"：成功登录");
                    return "TeacherHome";//返回到教师首页
                }else{//密码错误
                    model.addAttribute("error","用户名或密码错误");
                    System.out.println("教师用户：用户名或密码错误");
                    return "login";
                }
            }
        }
        else{
            if(user.getUserPassword().equals(userPassword))
                request.getSession().setAttribute("UserSession",user);//把对象存下来
            else{
                model.addAttribute("error","用户名或密码错误");
                System.out.println("用户名或密码错误");
                return "login";
            }
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

            return "forward:/user/home";//实际并不会执行
        }
        user = userService.queryStudentById(user.getUserID());//到数据库里进行同步，因为用户类型可能发生了更改
        user.setUserPassword(newPassword);//否则，修改密码,写入数据库，注销，返回登录页
        userService.updateStudentUser(user);//写入数据库
        request.getSession().invalidate();//这一步还需要修改
        System.out.println("密码已修改，返回login");
        return "redirect:/user/goToLogin";
    }

    //注销
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect:/user/goToLogin";
    }

    //管理员    前往查询学生页面
    @RequestMapping("/goToSearchStu")
    public String goToSearchStu(){
        return "searchStu_ad";
    }

}
