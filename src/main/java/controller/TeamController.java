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
            User teachers = (User) userService.queryAllTeacher();
            model.addAttribute("teachers",teachers);
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
        model.addAttribute("message","已有队伍，请刷新界面");
        return "failedApply";//有队伍，拦截
    }

    //进行项目申请，只有学生可以调用
    @RequestMapping("/TeamApply")
    synchronized public String TeamApply(Team team ,HttpServletRequest request,Model model){
        System.out.println("/team/TeamApply");
        User user = (User)request.getSession().getAttribute("UserSession");
        if(user == null){//未登录
            System.out.println("未登录");
            return "login";
        }
        if("student".equals(user.getUserType())){
            //为学生，且不是老师、管理员、队员和队长
            System.out.println(team);
            //对数据进行处理，获得真正的teacherID
            String teacherID = team.getTeacherID();
            for(int i = 0;i < teacherID.length();i++){
                if(teacherID.charAt(i) == ' '){
                    teacherID = teacherID.substring(0,i);
                    System.out.println(teacherID+"aaa");
                    break;
                }
            }
            team.setTeacherID(teacherID);
            team.setState("nopass");
            //team对象成功构建
            //接下来，需要验证team的各种数据是否合法
            //验证队长id和队员userid是否合法
            if("".equals(team.getTeamleaderID())){
                return "login";//说明没登录，直接返回
            }else{
                User leader = userService.queryStudentById(team.getTeamleaderID());
                if(leader == null) {
                    //不存在这个队长
                    return "login";
                }else{//队长信息有效，接下来判断队员信息
                    if("student".equals(leader.getUserType())){//队长还没有项目
                        if("".equals(team.getTeammate1ID()) && "".equals(team.getTeammate2ID())){
                            //不存在一号队员和二号队员,直接放到数据库里边
                            teamService.addTeam(team);
                            //然后，修改队长的信息
                            leader.setUserType("teamleader");
                            userService.updateStudentUser(leader);
                            return "successApply";
                        }else {
                            //有2个队员
                            if(!"".equals(team.getTeammate1ID()) && !"".equals(team.getTeammate2ID())){
                                User teammate1 = userService.queryStudentById(team.getTeammate1ID());
                                User teammate2 = userService.queryStudentById(team.getTeammate2ID());
                                if(teammate1 == null || teammate2 == null){//有队员不存在
                                    System.out.println("队员信息不存在");
                                    model.addAttribute("message","队员信息不存在");
                                    return "failedApply";
                                }else{
                                    if("student".equals(teammate1.getUserType()) && "student".equals(teammate2.getUserType())){
                                        //两个队员都没加入队伍，可以写入数据库
                                        teamService.addTeam(team);//写队伍表
                                        leader.setUserType("teamleader");
                                        userService.updateStudentUser(leader);//更新队长信息
                                        teammate1.setUserType("teammate");
                                        userService.updateStudentUser(teammate1);//更新一号队员的信息
                                        teammate2.setUserType("teammate");
                                        userService.updateStudentUser(teammate2);//更新二号队员的信息
                                        System.out.println("成功添加队伍");
                                        return "successApply";
                                    }
                                }
                            }else{
                                //有一个队员
                                User teammate = null;
                                if("".equals(team.getTeammate1ID())){//一号队员为空，二号队员有效
                                    teammate = userService.queryStudentById(team.getTeammate2ID());//查到二号队员
                                }else{//二号队员为空，一号队员有效
                                    teammate = userService.queryStudentById(team.getTeammate1ID());//查到一号队员的信息
                                }
                                if(teammate == null){//没有这个队员，失败
                                    System.out.println("一个队员，队员信息不存在");
                                    model.addAttribute("message","队员信息不存在");
                                    return "failedApply";
                                }else{//队员存在修改数据库
                                    leader.setUserType("teamleader");
                                    userService.updateStudentUser(leader);
                                    teammate.setUserType("teammate");
                                    userService.updateStudentUser(teammate);
                                    System.out.println("单个队员，队伍成功申请，等待审批");
                                    return "successApply";
                                }
                            }
                        }
                    }else{//有项目了，返回到失败
                        return "failedApply";
                    }
                }
            }
        }
        return "failedApply";//是老师或管理员或队员或队长
    }

    //学生(队长)修改队伍信息
    @RequestMapping("/updateTeam")
    private String updateTeam(Team team){
        return "";
    }
}
