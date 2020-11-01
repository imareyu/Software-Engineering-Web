package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pojo.Team;
import pojo.User;
import service.TeamService;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.PanelUI;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

    @Autowired
    @Qualifier("teamServiceImpl")
    private TeamService teamService;

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
    public String goToSearchStu(HttpServletRequest request){

        return "searchStu_ad";
    }

    //管理员   查询某个学生用户
    @RequestMapping("/queryStu_ad")
    public String queryStu_ad(String userID,HttpServletRequest request,Model model){
        User user = userService.queryStudentById(userID);
        if(user == null){
            model.addAttribute("error","不存在此学生用户");
            return "forward:goToSearchStu";
        }
        model.addAttribute("user",user);
        return "stuPasswordModify_ad";
    }

    //管理员   修改某个学生用户的密码
    @RequestMapping("/modifyStuPassword_ad")
    public String modifyStuPassword_ad(int ID,String UserID,String UserPassword,Model model){
        System.out.println("ID:"+ID);
        System.out.println("UserID:"+UserID);
        System.out.println("UserPassword:"+UserPassword);
        User user = userService.queryStudentById(UserID);//忽然发现ID没有用 ，UserID有用
        if(user == null){
            model.addAttribute("error","密码修改失败，不存在此学生用户");
            return "forward:goToSearchStu";
        }
        user.setUserPassword(UserPassword);
        userService.updateStudentUser(user);//更新数据库信息
        model.addAttribute("mess","学生用户密码修改成功");
        return "forward:goToSearchStu";
    }

    //管理员   前往为了删除学生用户的查询学生用户界面
    @RequestMapping("/goToSearchStu_forDeleteStu_ad")
    public String goToSearchStu_forDeleteStu_ad(){
        return "SearchStu_forDeleteStu_ad";
    }

    //管理员  查询学生用户，查到之后则前往删除确认页面
    @RequestMapping("/queryStu_forDeleteStu_ad")
    public String queryStu_forDeleteStu_ad(String userID,HttpServletRequest request,Model model){
        User user = userService.queryStudentById(userID);
        if(user == null){
            model.addAttribute("error","不存在此学生用户");
            return "forward:goToSearchStu_forDeleteStu_ad";
        }
        model.addAttribute("user",user);
        return "deleteStu_ad";
    }

    //管理员     删除学生用户
    @RequestMapping("/deleteStu")
    public String deleteStu(String UserID,Model model){
        User user = userService.queryStudentById(UserID);
        if(user == null){
            model.addAttribute("error","删除失败，学生用户已不存在");
            return "forward:goToSearchStu_forDeleteStu_ad";
        }
        //存在这个学生，需要看一下ta是否参加了队伍
        if("student".equals(user.getUserType())){
            //没有参加队伍
            userService.deleteStudentUserByID(user.getUserID());//修改数据库
            model.addAttribute("mess","删除学生用户成功");
        }else{//参加了队伍，删除失败
            model.addAttribute("error","删除失败，学生用户在队伍中");
            return "forward:goToSearchStu_forDeleteStu_ad";
        }
        return "forward:goToSearchStu_forDeleteStu_ad";
    }

    //管理员    前往添加学生用户界面
    @RequestMapping("/gotoAddStu")
    public String gotoAddStu(HttpServletRequest request){

        return "AddStudent_ad";
    }

    //管理员  添加一个学生用户
    @RequestMapping("/addStu_ad")
    public String addStu_ad(String UserID,String Password,Model model){
        System.out.println("尝试添加学生用户UserID:"+UserID+";密码:"+Password);
        //首先需要判断此UserID是否已被使用（包括students表、teacher表、administrator表）
        if(userService.queryStudentById(UserID) != null||userService.queryTeacherById(UserID) != null ||userService.queryAdministratorById(UserID) != null){
            //查到了，说明这个userID被占用了，返回错误信息
            model.addAttribute("error","用户名重复，添加失败");
            System.out.println("用户名重复，添加失败");
            return "forward:gotoAddStu";
        }
        //没有被占用，添加
        User user = new User();
        user.setUserID(UserID);
        user.setUserPassword(Password);
        user.setUserType("student");
        userService.addStudentUser(user);
        model.addAttribute("mess","学生用户添加成功");
        System.out.println("学生用户添加成功");
        return "forward:gotoAddStu";
    }

    // 管理员  前往查询教师用户界面
    @RequestMapping("/gotoSearchTea")
    public String gotoSearchTea(){
        return "searchTea_ad";
    }

    // 管理员   查询教师用户（然后前往修改密码页面
    @RequestMapping("/queryTea_ad")
    public String queryTea_ad(String userID,Model model){
        User user = userService.queryTeacherById(userID);
        if(user == null){//没有这个教师用户
            model.addAttribute("error","不存在用户id为："+userID+" 的教师用户");
            return "forward:gotoSearchTea";
        }
        model.addAttribute("user",user);
        return "teaPasswordModify_ad";
    }

    //管理员  修改教师用户密码
    @RequestMapping("/modifyTeaPassword_ad")
    public String modifyTeaPassword_ad(String UserID,String UserPassword,Model model){
        System.out.println("尝试修改教师用户密码：UserID:"+UserID+" 新密码："+UserPassword );
        User user = userService.queryTeacherById(UserID);
        if(user == null){
            model.addAttribute("error","此教师用户不存在："+UserID);
            return "forward:gotoSearchTea";
        }
        user.setUserPassword(UserPassword);
        userService.updateTeacherUser(user);
        model.addAttribute("mess","用户密码修改成功");
        return "forward:gotoSearchTea";
    }

    //管理员  前往为了删除老师用户的查询老师用户界面
    @RequestMapping("/goToSearchTea_forDeleteTea_ad")
    public String goToSearchTea_forDeleteTea_ad(){
        return "SearchTea_forDeleteTea_ad";
    }

    //管理员  查询教师用户，查到之后则前往删除确认页面
    @RequestMapping("/queryTea_forDeleteTea_ad")
    public String queryTea_forDeleteTea_ad(String userID,HttpServletRequest request,Model model){
        User user = userService.queryTeacherById(userID);
        if(user == null){
            model.addAttribute("error","不存在此教师用户");
            return "forward:goToSearchTea_forDeleteTea_ad";
        }
        model.addAttribute("user",user);
        return "deleteTea_ad";
    }

    //管理员     删除教师用户
    @RequestMapping("/deleteTea")
    synchronized public String deleteTea(String UserID,Model model){
        User user = userService.queryTeacherById(UserID);
        if(user == null){
            model.addAttribute("error","删除失败，教师用户已不存在");
            return "forward:goToSearchTea_forDeleteTea_ad";
        }
        //存在这个教师用户，需要看一下ta名下是否有队伍
        List<Team> teams = teamService.queryTeamsByTeacherID(UserID);
        if(teams == null || teams.size() == 0){
            //名下没有队伍
            userService.deleteTeacherUserByID(user.getUserID());//修改数据库
            model.addAttribute("mess","删除教师用户成功");
        }else{//有队伍，删除失败
            model.addAttribute("error","删除失败，教师用户"+UserID+"名下有队伍");
            return "forward:goToSearchTea_forDeleteTea_ad";
        }
        return "forward:goToSearchTea_forDeleteTea_ad";
    }
}
