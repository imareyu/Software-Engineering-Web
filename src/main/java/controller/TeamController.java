package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pojo.Team;
import pojo.User;
import service.TeamService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/team")
public class TeamController {
    @Autowired
    @Qualifier("teamServiceImpl")
    private TeamService teamService;

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
}
