package controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.QuestionsService;

@Controller
@RequestMapping("/questions")
public class QuestionsController {
    @Autowired
    @Qualifier("questionsServiceImpl")
    private QuestionsService questionsService;
    
}
