package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.TeamService;

@Controller
@RequestMapping("/team")
public class TeamController {
    @Autowired
    @Qualifier("teamServiceImpl")
    private TeamService teamService;
}
