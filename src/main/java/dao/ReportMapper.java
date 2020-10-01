package dao;

import pojo.Report;

import java.util.List;

public interface ReportMapper {
    //查询所有的报告
    List<Report> queryAllMaterials();

    //上传一份报告
    int UploadAMaterial(Report report);

    //删除一份报告,根据报告id删除
    int deleteMaterial(int ReportId);

    //根据文件名进行一次模糊查询,Title就是文件名
    List<Report> queryMaterialByWord(String word);

    //根据队伍的id查询报告
    List<Report> queryMaterialsByTeamID(int TeamID);
}
