package controller;

import dao.ReportMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.MaterialService;
import service.ReportService;

@Controller
@RequestMapping("/file")
public class FileController {//对所有文件操作相关的进行管理
    @Autowired
    @Qualifier("materialServiceImpl")
    private MaterialService materialService;//对资料的管理
    @Qualifier("reportServiceImpl")
    private ReportService reportService;//对报告管理
    

}
