package controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pojo.Answer;
import pojo.Questions;
import pojo.User;
import service.AnswerService;
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

    @Autowired
    @Qualifier("answerServiceImpl")
    private AnswerService answerService;

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

                request.getSession().setAttribute("questionsNowPage","1");
                model.addAttribute("questionsNowPage","1");//目前所在的页

                request.getSession().setAttribute("questionsAllPages","1");
                model.addAttribute("questionsAllPages","1");//总共只有一页
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
            int counts = questionsService.queryCounts();
            request.getSession().setAttribute("questionsCounts",Integer.toString(counts));//总的问题条数
            model.addAttribute("questionsCounts",Integer.toString(counts));

            request.getSession().setAttribute("questionsNowPage","1");
            model.addAttribute("questionsNowPage","1");//目前在第一页

            //计算一下总的页数
            int questionsAllPages = counts/15;
            if(counts % 15 != 0){//不满一页，当作一页处理
                questionsAllPages++;
            }
            if(questionsAllPages == 0){
                //没有一个问题，那就当作有一页
                questionsAllPages = 1;
            }
            //总的页数
            request.getSession().setAttribute("questionsAllPages",Integer.toString(questionsAllPages));
            model.addAttribute("questionsAllPages",Integer.toString(questionsAllPages));

            List<Questions> questions = questionsService.queryAnyQuestions(0, 15);//查先15条数据
            model.addAttribute("questions",questions);
        }else{
            //非教师用户
            return "dontHavePermission";
        }
        return "questionsManage_tea";
    }

    //教师 前往下一页问题
    @RequestMapping("/gotoNextQuestionPage_tea")
    public String gotoNextQuestionPage_tea(HttpServletRequest request,Model model){
        User user = (User) request.getSession().getAttribute("UserSession");
        if(user == null){
            return "login";
        }
        int counts = questionsService.queryCounts();
        request.getSession().setAttribute("questionsCounts",Integer.toString(counts));
        model.addAttribute("questionsCounts",Integer.toString(counts));

        int questionsAllPages = counts/15;
        if(counts % 15 != 0){//不满一页，当作一页处理
            questionsAllPages++;
        }
        if(questionsAllPages == 0){
            //没有一个问题，那就当作有一页
            questionsAllPages = 1;
        }
        request.getSession().setAttribute("questionsAllPages",Integer.toString(questionsAllPages));
        model.addAttribute("questionsAllPages",Integer.toString(questionsAllPages));

        String questionsNowPageStr = (String) request.getSession().getAttribute("questionsNowPage");
        int questionsNowPage = Integer.parseInt(questionsNowPageStr);//目前所在的页
        questionsNowPage++;
        request.getSession().setAttribute("questionsNowPage",Integer.toString(questionsNowPage));
        model.addAttribute("questionsNowPage",Integer.toString(questionsNowPage));
        if(questionsNowPage <= questionsAllPages && questionsNowPage >= 1){
            List<Questions> questions = questionsService.queryAnyQuestions(15 * (questionsNowPage - 1), 15);
            if(questions == null||questions.size() == 0)
                return "forward:teaGoToQuesManage";//没有，返回第一页吧
            model.addAttribute("questions",questions);
        }
        return "questionsManage_tea";
    }

    //教师 前往上一页问题
    @RequestMapping("/gotoFrontQuestionPage_tea")
    public String gotoFrontQuestionPage_tea(HttpServletRequest request,Model model){
        User user = (User) request.getSession().getAttribute("UserSession");
        if(user == null){
            return "login";
        }
        int counts = questionsService.queryCounts();
        request.getSession().setAttribute("questionsCounts",Integer.toString(counts));
        model.addAttribute("questionsCounts",Integer.toString(counts));

        int questionsAllPages = counts/15;
        if(counts % 15 != 0){//不满一页，当作一页处理
            questionsAllPages++;
        }
        if(questionsAllPages == 0){
            //没有一个问题，那就当作有一页
            questionsAllPages = 1;
        }
        request.getSession().setAttribute("questionsAllPages",Integer.toString(questionsAllPages));
        model.addAttribute("questionsAllPages",Integer.toString(questionsAllPages));

        String questionsNowPageStr = (String) request.getSession().getAttribute("questionsNowPage");
        int questionsNowPage = Integer.parseInt(questionsNowPageStr);//目前所在的页
        questionsNowPage--;
        request.getSession().setAttribute("questionsNowPage",Integer.toString(questionsNowPage));
        model.addAttribute("questionsNowPage",Integer.toString(questionsNowPage));
        if(questionsNowPage <= questionsAllPages && questionsNowPage > 0){
            List<Questions> questions = questionsService.queryAnyQuestions(15 * (questionsNowPage - 1), 15);
            if(questions == null||questions.size() == 0)
                return "forward:teaGoToQuesManage";//没有，返回第一页吧
            model.addAttribute("questions",questions);
        }
        return "questionsManage_tea";
    }

    //教师     前往问题回答页面
    @RequestMapping("/goToAnswerQuestion")
    public String goToAnswerQuestion(int id, HttpServletRequest request, Model model){
        User user = (User) request.getSession().getAttribute("UserSession");
        if(user == null){
            return "login";
        }
        Questions questions = questionsService.queryByQuestionID(id);
        if(questions == null){
            model.addAttribute("error","不存在此问题");
            return "forward:teaGoToQuesManage";
        }
        if("teacher".equals(user.getUserType())){
            model.addAttribute("questions",questions);
        }else{
            return "dontHavePermission";
        }
        return "answerQuestions_tea";
    }

    //提交一个回答(教师用户
    @RequestMapping("/submitAnswer")
    public String submitAnswer(Answer answer,HttpServletRequest request, Model model){
        User user = (User) request.getSession().getAttribute("UserSession");
        if(user == null){
            return "login";
        }
        if("teacher".equals(user.getUserType())) {//教师用户
            answer.setAnsTime(new Timestamp(System.currentTimeMillis()));
            answerService.addAnswer(answer);
            Questions questions = questionsService.queryByQuestionID(answer.getQuestionID());
            //更新问题的回答状态
            if("no".equals(questions.getAnsState())) {//未回答，更新一下
                questions.setAnsState("yes");
                questionsService.updateQuestion(questions);
                System.out.println("更新问题状态成功");
            }
            model.addAttribute("success","成功提交回答");
        }else{
            return "dontHavePermission";
        }
        return "forward:teaGoToQuesManage";
    }


}
