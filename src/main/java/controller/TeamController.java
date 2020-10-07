package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pojo.Team;
import service.TeamService;

import java.util.List;

@Controller
@RequestMapping("/team")
public class TeamController {
    @Autowired
    @Qualifier("teamServiceImpl")
    private TeamService teamService;

    //前往学生管理页面
    @RequestMapping("/goToStuTeamManage")
    public String goToStuTeamManage(){
        List<Team> teams = teamService.queryAllTeams();
        System.out.println(teams);
        return "stuTeamManage";
    }
}
