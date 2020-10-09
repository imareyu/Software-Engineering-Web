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
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/team")
public class TeamController {
    @Autowired
    @Qualifier("teamServiceImpl")
    private TeamService teamService;

    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

    //前往队伍管理页面，根据用户类型进行分流
    @RequestMapping("/goToTeamManage")
    public String goToStuTeamManage(HttpServletRequest request, Model model){
//        System.out.println(teams);
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("UserSession");

        if(user == null){
            System.out.println("team/goToTeamManage:用户未登录");
            return "login";//未登录
        }
        if("teacher".equals(user.getUserType())){
            //教师，应当前往另一个页面
            System.out.println("team/goToTeamManage:教师用户"+user.getUserID());
            List<Team> teams = teamService.queryTeamsByTeacherID(user.getUserID());//首先根据教师获取他名下的项目
            model.addAttribute("teams",teams);
            return "teaTeamManage";
        }
        if("student".equals(user.getUserType())||"teamleader".equals(user.getUserType())||"teammate".equals(user.getUserType())){
            //学生用户
            System.out.println("team/goToTeamManage:学生用户"+user.getUserID());
            Team team = teamService.queryTeamByMemberID(user.getUserID());//根据用户id查询项目中是否存在
            if(team == null){
                //没有队伍
                System.out.println("无队伍");
                return "noTeam";
            }
            model.addAttribute("teams",team);
        }
        else{
            //为管理员用户
            System.out.println("team/goToTeamManage:管理员用户"+user.getUserID());
            List<Team> teams = teamService.queryAnyTeams(0, 15);//查15条返回
            model.addAttribute("teams",teams);
            return "adminiTeamManage";
        }
        return "stuTeamManage";
    }

    //前往申请项目界面
    @RequestMapping("/goToTeamApply")
    public String goToTeamApply(HttpServletRequest request,Model model){
        //验证是不是有项目了
        User user = (User)request.getSession().getAttribute("UserSession");
        if(user == null)
            return "login";
        Team team = teamService.queryTeamByMemberID(user.getUserID());
        if(team == null){//没队伍，可以去申请页面
            //去页面之前要查询一下可以选择的老师
            List<User> teachers = userService.queryAllTeacher();
            System.out.println("查询到教师用户："+teachers.size()+"个");
            model.addAttribute("teachers",teachers);
            return "TeamApply";
        }
        return "failedApply";//有队伍，拦截
    }

    //进行项目申请，只有学生可以调用
    @RequestMapping("/TeamApply")
    public String TeamApply(Team team ,HttpServletRequest request){
        System.out.println("/team/TeamApply");
        User user = (User)request.getSession().getAttribute("UserSession");
        if(user == null){
            //未登录
            System.out.println("未登录");
            return "login";
        }
        if("student".equals(user.getUserType())){
            //为学生，且不是老师、管理员、队员和队长
            System.out.println(team);
            team.setState("nopass");
            return "";
        }
        return "failedApply";//为是老师或管理员或队员或队长
    }
}
