package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import pojo.Material;
import pojo.Report;
import pojo.Team;
import pojo.User;
import service.MaterialService;
import service.ReportService;
import service.TeamService;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.List;

@Controller
@RequestMapping("/file")
public class FileController {//对所有文件操作相关的进行管理
    @Autowired
    @Qualifier("materialServiceImpl")
    private MaterialService materialService;//对资料的管理

    @Autowired
    @Qualifier("reportServiceImpl")
    private ReportService reportService;//对报告管理

    @Autowired
    @Qualifier("teamServiceImpl")
    private TeamService teamService;//对报告管理

    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

    @RequestMapping("/goToUploadMaterial")
    public String goToUpload(){
        return "uploadMaterial";
    }

    @RequestMapping("/uploadMaterial")
    public String uploadMaterial(@RequestParam("file") CommonsMultipartFile file, HttpServletRequest request, Model model) throws IOException {
        //首先要判断是老师
        Object userSession1 = request.getSession().getAttribute("UserSession");
        if(userSession1 == null) {
            model.addAttribute("alertmess","未登录，不能上传文件");
            return "login";//为空，说明没登录，直接跳回到登录
        }
        //登录了，判断是不是老师，管理员也不能上传
        User user = (User)userSession1;
        if(!"teacher".equals(user.getUserType())) {//不为老师
            return "dontHavePermission";//没有权限
        }
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
        System.out.println("进入跳转资料页面流程");
        List<Material> materials = materialService.query15Materials(0, 15);//查询15条
        System.out.println("完成15条资料查询");
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

    //下载一个材料,设定为不登录也可以下载
    @RequestMapping("/downloadMaterial")
    public String downloadMaterial(int id,Model model,HttpServletRequest request,HttpServletResponse response) throws Exception {
        Material material = materialService.queryMaterialByID(id);
        String path = material.getPath();
        String fileName = material.getMaterialName();

//        if(request.getSession().getAttribute("UserSession") == null)
//            return "login";
        System.out.println("下载文件的path:"+path);
        System.out.println("下载文件的filename:"+fileName);
        //设置response响应头
        response.reset();//设置页面不缓存，清空buffer
        response.setCharacterEncoding("utf-8");//字符编码
        response.setContentType("multipart/form-data");//二进制传输数据
        response.setHeader("Content-Disposition","attachment;fileName="+ URLEncoder.encode(fileName,"UTF-8"));
        File file = new File(path,fileName);
        //读取文件-输入流
        InputStream input = new FileInputStream(file);
        //写出文件--输出流
        OutputStream out = response.getOutputStream();
        byte[] buffer = new byte[1024];
        int index = 0;
        //执行写出操作
        while((index = input.read(buffer)) != -1){
            out.write(buffer,0,index);
            out.flush();
        }
        out.close();
        input.close();
//        System.out.println("下载成功");
//        model.addAttribute("downloadSuccess","下载成功");
        return "MaterialInfor";
        //return "";
    }

    //删除一个材料，只有老师可以删除，管理员和学生都不行，未登录也不行
    @RequestMapping("/deleteAMaterial")
    synchronized public String deleteAMaterial(int id,Model model,HttpServletRequest request){
        System.out.println("要删除的文件id："+id);
        Object userSession = request.getSession().getAttribute("UserSession");
        if(userSession == null){
            //未登录
            return "login";
        }
        else{
            User user = (User)userSession ;
            if("student".equals(user.getUserType()) ||"teamleader".equals(user.getUserType())||"teammate".equals(user.getUserType()) ){
                model.addAttribute("error","学生用户不具有删除权限");
                return "MaterialInfor";//学生用户
            }
            if("admini".equals(user.getUserType())){
                model.addAttribute("error","管理员用户不具有删除权限");
                return "MaterialInfor";//管理员
            }
        }
        Material material = materialService.queryMaterialByID(id);
        List<Material> materials = null;
        if(material == null) {
            model.addAttribute("deleteSuccess", "文件已删除");
            materials = materialService.query15Materials(0, 15);
            model.addAttribute("materials",materials);//显示15条，如果有的话
            return "MaterialInfor";
        }
        String path = material.getPath();
        String materialName = material.getMaterialName();
        File file = new File(path,materialName);
        if(file.delete()){
            materialService.deleteMaterial(id);
            model.addAttribute("deleteSuccess", "文件已删除");
        }else{
            model.addAttribute("error","文件删除失败");
        }
        materials = materialService.query15Materials(0, 15);
        model.addAttribute("materials",materials);//显示15条，如果有的话
        return "MaterialInfor";
    }

    //学生到报告管理页面
    @RequestMapping("/stuToReportManage")
    public String toReportManage(Model model,HttpServletRequest request){
        System.out.println("file/stuToReportManage");
        User user = (User) request.getSession().getAttribute("UserSession");
        if(user == null){
            return "login";
        }
        System.out.println("学生id："+user.getUserID());
        //根据userID查询用户所在的队伍的teamID
        Team team = teamService.queryTeamByMemberID(user.getUserID());
        if(team == null){
            //这个人不在队伍里，不能查看报告
            return "dontHavePermission";
        }
        List<Report> reports = reportService.queryReportsByTeamID(team.getTeamID());
        model.addAttribute("reports",reports);
        return "ReportManage";
    }

    //学生到上传报告的界面(只有类型为teamleader和teammate的学生可以)后来，发现老师，懂？
    @RequestMapping("/goToUploadReport")
    public String goToUploadReport(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("UserSession");
        if(user == null){
            return "login";
        }
        if(!"teacher".equals(user.getUserType())) {//不是老师，看看是不是学生
            user = userService.queryStudentById(user.getUserID());
            if (user == null) {//有可能管理员把账号给删了
                request.getSession().setAttribute("UserSession", null);
                return "login";
            }
        }
        if("teacher".equals(user.getUserType())||"teamleader".equals(user.getUserType()) || "teammate".equals(user.getUserType())){
            return "uploadReport";
        }
        return "dontHavePermission";//没有足够的权限
    }

    //根据关键词从报告表的Title中搜查,这个是学生只能查自己队伍的报告
    @RequestMapping("/queryReportByName")
    public String queryReportByName(String word,Model model,HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("UserSession");
        if(user == null){
            return "login";
        }
        Team team = teamService.queryTeamByMemberID(user.getUserID());
        List<Report> reports = reportService.queryReportByWord(word);
        for(int i = 0;i < reports.size();i++){
            if(reports.get(i).getTeamID() != team.getTeamID()){
                //不相等，不是自己的队伍的，删掉
                reports.remove(i);
                i--;
            }
        }
        model.addAttribute("reports",reports);
        return "ReportManage";
    }

    //下载报告
    @RequestMapping("/downloadReport")
    public String downloadReport(int id,Model model,HttpServletResponse response,HttpServletRequest request) throws IOException {
        User user = (User) request.getSession().getAttribute("UserSession");
        if(user == null){
            return "login";
        }
        if(!"teacher".equals(user.getUserType())){
            user = userService.queryStudentById(user.getUserID());
            if(user == null){
                request.getSession().setAttribute("UserSession",null);
                return "login";
            }
            System.out.println("学生id："+user.getUserID());
        }
        //根据userID查询用户所在的队伍的teamID

        System.out.println("file/downloadReport下载的ReportID为："+id);
        Report report = reportService.queryReportByID(id);
        //获得报告的信息
        String path = report.getPath();
        String fileName = report.getTitle();//title就是文件名，我的锅

        System.out.println("下载文件的path:"+path);
        System.out.println("下载文件的filename:"+fileName);
        //设置response响应头
        response.reset();//设置页面不缓存，清空buffer
        response.setCharacterEncoding("utf-8");//字符编码
        response.setContentType("multipart/form-data");//二进制传输数据
        response.setHeader("Content-Disposition","attachment;fileName="+ URLEncoder.encode(fileName,"UTF-8"));
        File file = new File(path,fileName);
        //读取文件-输入流
        InputStream input = new FileInputStream(file);
        //写出文件--输出流
        OutputStream out = response.getOutputStream();
        byte[] buffer = new byte[1024];
        int index = 0;
        //执行写出操作
        while((index = input.read(buffer)) != -1){
            out.write(buffer,0,index);
            out.flush();
        }
        out.close();
        input.close();
        model.addAttribute("downloadSuccess","下载成功");

        return "forward:stuToReportManage";
    }

    //删除一份报告，学生调用（后来想了想，老师应该也可以删除
    @RequestMapping("/deleteAReport")
    public String deleteAReport(int id,HttpServletRequest request,Model model){
        User user = (User) request.getSession().getAttribute("UserSession");
        if(user == null){
            return "login";
        }

        Report report = reportService.queryReportByID(id);

        String path = report.getPath();
        String filename = report.getTitle();
        File file = new File(path,filename);
        if(file.delete()){
            model.addAttribute("deleteSuccess","成功删除");
        }else{
            model.addAttribute("error","删除失败");
        }
        reportService.deleteReport(report.getReportID());//删除数据库记录
        return "forward:/teaGoToReportManage";//继续解析
    }

    //处理上传的报告数据
    @RequestMapping("/uploadReport")
    public String uploadReport(@RequestParam("file") CommonsMultipartFile file, HttpServletRequest request, Model model) throws IOException {
        User user = (User) request.getSession().getAttribute("UserSession");
        if(user == null) {
            System.out.println("未登录，不能上传文件");
            return "login";//为空，说明没登录，直接跳回到登录
        }
        //登录了，判断是不是学生
        user = userService.queryStudentById(user.getUserID());//到数据库找最新的数据
        if(user == null){
            request.getSession().setAttribute("UserSession",null);
            return "login";
        }
        request.getSession().setAttribute("UserSession",user);
        //为队长或者队员
        if("teamleader".equals(user.getUserType()) || "teammate".equals(user.getUserType())) {
            Team team = teamService.queryTeamByMemberID(user.getUserID());//所在的队伍
            String uploadFileName = file.getOriginalFilename();
            if ("".equals(uploadFileName)) {
                System.out.println("文件名为空");
                return "redirect:/file/goToUploadReport";//返回到上传文件页面
            }
            System.out.println("uploadFileName:" + uploadFileName);
            //需要查询是否有同名文件，如果有同名文件，则需要覆盖，而不是上传

            String path = request.getServletContext().getRealPath("/report/"+team.getTeamID());

            File realPath = new File(path);
            if (!realPath.exists()) {
                System.out.println("文件夹不存在");
                boolean mkdirs = realPath.mkdirs();
                if(!mkdirs){
                    System.out.println("创建文件夹失败");
                    return "failed";
                }
            }
            System.out.println("保存地址realPath：" + realPath);
            System.out.println("保存地址path：" + path);
            InputStream is = file.getInputStream();//文件输入流
            OutputStream os = new FileOutputStream(new File(realPath, uploadFileName));//文件输出流

            //读取写出
            int len = 0;
            byte[] buffer = new byte[1024];
            while ((len = is.read(buffer)) != -1) {
                os.write(buffer, 0, len);
                os.flush();
            }
            os.close();
            is.close();
            System.out.println("上传成功");
            //接下来写入数据库记录
            Report report = new Report();
            report.setTitle(uploadFileName);
            report.setPublishTime(new Timestamp(System.currentTimeMillis()));
            report.setPath(path);
            report.setTeamID(team.getTeamID());

            System.out.println("materialName：" + report.getTitle());
            System.out.println("materialUploadTime：" + report.getPublishTime());
            System.out.println("Path：" + report.getPath());
            //根据路径和文件名在数据库中查询，如果有，则修改时间
            //如果没有，则添加新的记录
            Report resultReport = reportService.queryReportByTitleAndPath(report);
            if (resultReport == null)//为空说明没有这个文件
            {//添加文件记录
                reportService.UploadAReport(report);
                System.out.println("上传成功");
                model.addAttribute("uploadStatus", "上传成功");
                return "forward:goToUploadReport";
            } else {//有文件，修改记录的时间
                report.setReportID(resultReport.getReportID());//获得id
                reportService.updateReport(report);
                System.out.println("文件重名，已覆盖");
                model.addAttribute("uploadStatus", "文件重名，已覆盖");
            }
        }else{
            return "dontHavePermission";
        }
        return "forward:goToUploadMaterial";//继续解析
    }


    //教师查看名下的队伍的报告
    @RequestMapping("/teaGoToReportManage")
    public String teaGoToReportManage(HttpServletRequest request,Model model){
        User user = (User) request.getSession().getAttribute("UserSession");
        if(user == null){
            return "login";
        }
        if("teacher".equals(user.getUserType())){//教师用户和学生用户有不同，学生用户的类型可能会变，教师类型的不会变，所以不需要到数据库进行更新
            List<Report> reports = reportService.queryReportByTeacherID(user.getUserID());//根据指导老师的ID到team和report表中查询
            System.out.println("查询到的记录条数:"+reports.size());
            model.addAttribute("reports",reports);
        }else{
            return "dontHavePermission";
        }
        return "reportManage_tea";//前往教师查看学生报告页面
    }

    //教师根据名称查询报告，和学生的不一样
    @RequestMapping("/teaQueryReportByName")
    public String teaQueryReportByName(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("UserSession");
        if(user == null){
            return "login";
        }
        if(!"teacher".equals(user.getUserType())){
            //不是老师
            return "dontHavePermission";
        }
        //是老师，根据指导老师的Id和文件的title中的关键词进行查询
        return "";
    }
}
