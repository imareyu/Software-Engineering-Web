package controller;

import dao.ReportMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import pojo.Material;
import service.MaterialService;
import service.ReportService;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

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
        //需要查询是否有同名文件，如果有同名文件，则需要覆盖，而不是上传

        String path = request.getServletContext().getRealPath("/upload");

        File realPath = new File(path);
        if(!realPath.exists()){
            realPath.mkdir();
        }
        System.out.println("保存地址realPath："+realPath);
        System.out.println("保存地址path："+path);
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
        //接下来写入数据库记录
        Material material = new Material();
        material.setMaterialName(uploadFileName);
        material.setUploadTime(new Timestamp(System.currentTimeMillis()));
        material.setPath(path);
        System.out.println("materialName："+ material.getMaterialName());
        System.out.println("materialUploadTime："+ material.getUploadTime());
        System.out.println("Path："+ material.getPath());
        //根据路径和文件名在数据库中查询，如果有，则修改时间
        //如果没有，则添加新的记录
        Material resultMaterial = materialService.queryMaterialByPathAndName(material);
        if(resultMaterial == null)//为空说明没有这个文件
        {//添加文件记录
            materialService.uploadMaterial(material);
            System.out.println("上传成功");
            model.addAttribute("uploadStatus","上传成功");
            return "forward:goToUploadMaterial";
        }else{//有文件，修改记录的时间
            material.setMaterialID(resultMaterial.getMaterialID());//获得id
            materialService.updateMaterial(material);
            System.out.println("文件重名，已覆盖");
            model.addAttribute("uploadStatus","文件重名，已覆盖");
        }
        //        materialService.teacherUploadMaterial(material);
        return "forward:goToUploadMaterial";//继续解析
    }

    @RequestMapping("/goToMaterialInfor")
    public String goToMaterialInfor(Model model){
        //需要先查询15条信息，以便不是一个空页面
        List<Material> materials = materialService.query15Materials(0, 15);//查询15条
        if(materials.size() == 0){//没有查询到相关的资料
            model.addAttribute("error","没有查询到相关的文件");
            return "MaterialInfor";
        }
        model.addAttribute("materials",materials);
        return "MaterialInfor";
    }

    @RequestMapping("/queryMaterialByName")
    public String queryMaterialByName(String queryBookName,Model model){//根据资料名称查询资料，模糊查询
        List<Material> materials = materialService.queryMaterialByWord(queryBookName);
        if(materials.size() == 0){//没有查询到相关的资料
            model.addAttribute("error","没有查询到相关的文件");
            return "MaterialInfor";
        }
        //查询到了
        model.addAttribute("materials",materials);
        return "MaterialInfor";
    }

    //下载一个材料
    @RequestMapping("/downloadMaterial")
    public String downloadMaterial(int id){
        return "";
    }

    //删除一个材料
    @RequestMapping("/deleteAMaterial")
    public String deleteAMaterial(int id){
        return "";
    }
}
