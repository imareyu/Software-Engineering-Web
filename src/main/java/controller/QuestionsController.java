package controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pojo.Questions;
import pojo.User;
import service.QuestionsService;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/questions")
public class QuestionsController {
    @Autowired
    @Qualifier("questionsServiceImpl")
    private QuestionsService questionsService;

    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

    //学生前往查看问题页面
    @RequestMapping("/stuGoToQuestionsManage")
    public String stuGoToQuestionsManage(Model model, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("UserSession");
        if(user == null){
            return "login";
        }
        if("student".equals(user.getUserType()) || "teamleader".equals(user.getUserType()) || "teammate".equals(user.getUserType())){
            //学生用户，只能看自己的问题,,,,吧
            List<Questions> questions = questionsService.queryByUserID(user.getUserID());
            model.addAttribute("questions",questions);
        }else {
            return "dontHavePermission";
        }
        return "questionsManage_stu";
    }

    //前往问题提交页面，只有学生可以调用
    @RequestMapping("/goToAskQuestion")
    public String goToAskQuestion(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("UserSession");
        if(user == null){
            return "logon";
        }
        user = userService.queryStudentById(user.getUserID());
        request.getSession().setAttribute("UserSession",user);
        if("student".equals(user.getUserType()) || "teamleader".equals(user.getUserType()) || "teammate".equals(user.getUserType())){
            return "askQuestion";
        }
        return "dontHavePermission";
    }
}
