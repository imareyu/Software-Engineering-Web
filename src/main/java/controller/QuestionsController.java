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
import java.sql.Timestamp;
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

    //添加一个问题
    @RequestMapping("/addAQuestion")
    public String addAQuestion(String content1,Model model,HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("UserSession");
        if(user == null){
            return "login";
        }
        user = userService.queryStudentById(user.getUserID());
        request.getSession().setAttribute("UserSession",user);
        if("student".equals(user.getUserType()) || "teamleader".equals(user.getUserType()) || "teammate".equals(user.getUserType())){
            //学生可以
            Questions questions = new Questions();
            questions.setUserID(user.getUserID());
            questions.setPublishTime(new Timestamp(System.currentTimeMillis()));
            questions.setContent(content1);
            questions.setAnsState("no");//未回答
            questionsService.addQuestion(questions);//写到数据库里边
            model.addAttribute("success","问题成功提交");
        }else{
            return "dontHavePermission";
        }
        return "forward:goToAskQuestion";
    }

    @RequestMapping("/queryQuesByWord")
    public String queryQuesByWord(String word,HttpServletRequest request,Model model){
        User user = (User) request.getSession().getAttribute("UserSession");
        if(user == null){
            return "login";
        }
        //学生需要只能查询自己的问题，老师的话，暂时定为查15条
        if("student".equals(user.getUserType()) || "teamleader".equals(user.getUserType()) || "teammate".equals(user.getUserType())){
            List<Questions> questions = questionsService.queryByUserIDAndWord(user.getUserID(), word);
            model.addAttribute("questions",questions);
            return "questionsManage_stu";//前往学生的页面
        }else{//老师，查询所有的问题里边的有关键词的
            if("teacher".equals(user.getUserType())){
                List<Questions> questions = questionsService.queryByWord(word);
                model.addAttribute("questions",questions);
                return "questionsManage_tea";//前往教师的页面
            }
        }
        return "dontHavePermission";
    }


    //教师前往问题查看页面
    @RequestMapping("/teaGoToQuesManage")
    public String teaGoToQuesManage(Model model,HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("UserSession");
        if(user == null){
            return "login";
        }
        if("teacher".equals(user.getUserType())){
            //教师用户
            List<Questions> questions = questionsService.queryAnyQuestions(0, 15);//查先15条数据
            model.addAttribute("questions",questions);
        }else{
            //非教师用户
            return "dontHavePermission";
        }
        return "questionsManage_tea";
    }











}
