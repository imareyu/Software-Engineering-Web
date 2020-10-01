package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.MaterialService;

@Controller
@RequestMapping("/file")
public class FileController {
    @Autowired
    @Qualifier("materialServiceImpl")
    private MaterialService materialService;
}
