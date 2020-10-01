package controller;

import dao.ReportMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import service.MaterialService;
import service.ReportService;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

@Controller
@RequestMapping("/file")
public class FileController {//对所有文件操作相关的进行管理
    @Autowired
    @Qualifier("materialServiceImpl")
    private MaterialService materialService;//对资料的管理
    @Qualifier("reportServiceImpl")
    private ReportService reportService;//对报告管理

    @RequestMapping("/goToUploadMaterial")
    public String goToUpload(){
        return "uploadMaterial";
    }

    @RequestMapping("/uploadMaterial")
    public String uploadMaterial(@RequestParam("file") CommonsMultipartFile file, HttpServletRequest request, Model model) throws IOException {
        String uploadFileName = file.getOriginalFilename();
        if("".equals(uploadFileName)){
            return "redirect:/file/goToUploadMaterial";//返回到上传文件页面
        }
        System.out.println("uploadFileName:"+uploadFileName);

        String path = request.getServletContext().getRealPath("/upload");

        File realPath = new File(path);
        if(!realPath.exists()){
            realPath.mkdir();
        }
        System.out.println("保存地址："+realPath);

        InputStream is = file.getInputStream();//文件输入流
        OutputStream os = new FileOutputStream(new File(realPath,uploadFileName));//文件输出流

        //读取写出
        int len = 0;
        byte[] buffer = new byte[1024];
        while( (len = is.read(buffer)) != -1){
            os.write(buffer,0,len);
            os.flush();
        }
        os.close();
        is.close();
        System.out.println("上传成功");
        model.addAttribute("uploadsuccess","上传成功");
        return "forward:allBook";//继续解析
    }
}
