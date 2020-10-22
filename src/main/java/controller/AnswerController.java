package controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.AnswerService;
import service.QuestionsService;
import service.UserService;

@Controller
@RequestMapping("/answer")
public class AnswerController {
    @Autowired
    @Qualifier("questionsServiceImpl")
    private QuestionsService questionsService;

    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

    @Autowired
    @Qualifier("answerServiceImpl")
    private AnswerService answerService;



}
