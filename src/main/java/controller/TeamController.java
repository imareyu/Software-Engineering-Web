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
import javax.servlet.http.HttpSession;
import java.io.IOException;
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
            System.out.println("其所在队伍"+team);
            model.addAttribute("teams",team);
            List<User> teachers = userService.queryAllTeacher();
//            System.out.println();
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
        user = userService.queryStudentById(user.getUserID());//到数据库进行同步,因为有可能学生类型已经被更改了
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
                            System.out.println("只有队长的队伍"+leader.getUserID());
                            request.getSession().setAttribute("UserSession",leader);//更新一下session中的用户信息
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

    //学生(只有队长可以)修改队伍信息
    @RequestMapping("/updateTeam")
    public String updateTeam(Team team,HttpServletRequest request,Model model){
        User user = (User) request.getSession().getAttribute("UserSession");
        if(user == null){
            return "login";
        }
        if("teamleader".equals(user.getUserType())){//为队长
            Team teamquery = teamService.queryTeamByMemberID(user.getUserID());
            //在这里把所有队员的类型都改为student，队长不改，因为队长id不会变，
            if(!"".equals(teamquery.getTeammate1ID())){//有一号队员
                User teammate1 = userService.queryStudentById(teamquery.getTeammate1ID());
                teammate1.setUserType("student");
                userService.updateStudentUser(teammate1);
            }
            if(!"".equals(teamquery.getTeammate2ID())){
                //二号队员
                User teammate2 = userService.queryStudentById(teamquery.getTeammate2ID());
                teammate2.setUserType("student");
                userService.updateStudentUser(teammate2);
            }
            team.setTeamID(teamquery.getTeamID());
            team.setProjectID(teamquery.getProjectID());
            team.setState(teamquery.getState());

            //下边把教师的id进行处理，因为现在的id其实是id和姓名连在一起的
            String teacherID = team.getTeacherID();
            for(int i = 0;i < teacherID.length();i++){
                if(teacherID.charAt(i) == ' '){
                    teacherID = teacherID.substring(0,i);
                    System.out.println(teacherID+"aaa");//aaa只是用来看看最后有没有空格
                    break;
                }
            }
            team.setTeacherID(teacherID);
            //忽然发现数据库设计不合理了，呜呜
            if("".equals(team.getTeammate1ID()) && "".equals(team.getTeammate2ID())){//不存在一号队员和二号队员,直接放到数据库里边
                teamService.updateTeam(team);
                //然后，修改队长的信息
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
                        teammate.setUserType("teammate");
                        userService.updateStudentUser(teammate);
                        System.out.println("单个队员，队伍成功申请，等待审批");
                        return "successApply";
                    }
                }
            }
        }
        return "failedApply";
    }

    //队员退出队伍
    @RequestMapping("/exitTeam")
    public String exitTeam(String id,HttpServletRequest request){
        System.out.println("/team/exitTeam");
        System.out.println("退出队伍"+id);
        Team team = teamService.queryTeamByMemberID(id);//查询所在的队伍
        if(id.equals(team.getTeammate1ID())){
            //为一号队员，设置为空
            team.setTeammate1ID("");
            team.setTeammate1Name("");
            teamService.updateTeam(team);//修改数据库
        }else{
            if(id.equals(team.getTeammate2ID())){
                //二号队员，设为空
                team.setTeammate2ID("");
                team.setTeammate2Name("");
                teamService.updateTeam(team);//更新数据库
            }
        }
        //接下来修改队员的用户类型
        User user= (User) request.getSession().getAttribute("UserSession");
        user = userService.queryStudentById(user.getUserID());
        user.setUserType("student");
        userService.updateStudentUser(user);
        return "redirect:/team/goToTeamManage";//继续解析
    }

    //教师前往项目审批界面
    @RequestMapping("/teaGoToTeamManage")
    public String teaGoToTeamManage(HttpServletRequest request,Model model){
        User user = (User) request.getSession().getAttribute("UserSession");
        if(user == null){//未登录
            return "login";
        }
        if("teacher".equals(user.getUserType())){//为教师
            List<Team> teams = teamService.queryTeamNopass(user.getUserID());//查询没有通过的队伍数据
            System.out.println(user.getUserID()+"老师查询到的未通过的记录"+teams.size());
            model.addAttribute("teams",teams);
        }else{//不是老师
            return "failed";
        }
        return "teaTeamManage";
    }

    @RequestMapping("/goToProcess")
    public String goToProcess(int TeamID,HttpServletRequest request,Model model){//前往某个项目的审批界面
        Team team = teamService.queryTeamByTeamID(TeamID);
        if(team == null){//队伍已经不存在了，可能被删了
            return "notExist";
        }
        User user = (User) request.getSession().getAttribute("UserSession");
        if(!team.getTeacherID().equals(user.getUserID())){//换了老师了
            return "notExist";
        }
        model.addAttribute("team",team);
        return "processATeam";
    }

    //教师通过某个队伍
    @RequestMapping("/passProject")
    public String passProject(Team team,HttpServletRequest request){
        //需要首先到数据库里边看看队伍是否还存在 并且 为当前的老师用户的名下的队伍
        Team teamQuery = teamService.queryTeamByTeamID(team.getTeamID());
        if(teamQuery == null){
            return "notExist";//队伍已不存在了
        }
        User user = (User) request.getSession().getAttribute("UserSession");
        if(!teamQuery.getTeacherID().equals(user.getUserID())){
            return "notExist";//指导老师不为当前用户
        }
        team.setState("pass");
        teamService.updateTeam(team);
        return "successAndGoTeamManage";
    }

    //判断ProjectID是否已存在
    @RequestMapping("/ajaxCheckProjectID")
    public void ajaxCheckProjectID(String projectID, HttpServletResponse response) throws IOException {
        Team team = teamService.queryTeamByProjectID(projectID);
        if(team == null){
            //没问题，还没用过
            response.getWriter().print("unused");
        }else{
            response.getWriter().print("used");//已经用过了，返回到前端
        }
    }

    //前往查看所有通过的项目
    @RequestMapping("/goToReferenceTeams")
    public String goToReferenceTeams(HttpServletRequest request,Model model){
        User user = (User) request.getSession().getAttribute("UserSession");
        if(user == null){
            return "login";
        }
        List<Team> teams = teamService.queryTeamPass(user.getUserID());//查询此教师名下的通过的项目
        System.out.println("/goToReferenceTeams查询到的信息条数:"+teams.size());
        model.addAttribute("teams",teams);
        return "referenceTeams";
    }

    //前往查看某个已通过的项目
    @RequestMapping("/goToSeeAPassTeam")
    public String goToSeeAPassTeam(int TeamID,Model model){
        Team team = teamService.queryTeamByTeamID(TeamID);
        model.addAttribute("team",team);
        return "seeAPassTeam";
    }

    //教师删除一个项目，学生是没有权限删除项目的
    @RequestMapping("/deleteProject")
    public String deleteProject(Team team){
        //删除项目，需要先恢复学生的身份
        team = teamService.queryTeamByTeamID(team.getTeamID());

        //修改队长的信息
        User teamleader = userService.queryStudentById(team.getTeamleaderID());
        teamleader.setUserType("student");
        userService.updateStudentUser(teamleader);

        if(!"".equals(team.getTeammate1ID())){
            //存在一号队员
            User teammate1 = userService.queryStudentById(team.getTeammate1ID());
            teammate1.setUserType("student");
            userService.updateStudentUser(teammate1);
        }
        if(!"".equals(team.getTeammate2ID())){
            //存在二号队员
            User teammate2 = userService.queryStudentById(team.getTeammate2ID());
            teammate2.setUserType("student");
            userService.updateStudentUser(teammate2);
        }

        teamService.deleteTeam(team.getTeamID());//删除项目
        return "redirect:/team/goToReferenceTeams";
    }
}