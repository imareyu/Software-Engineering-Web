package service;

import pojo.Report;

import java.util.List;

public interface ReportService {
    //查询所有的报告
    List<Report> queryAllReports();

    //上传一份报告
    int UploadAReport(Report report);

    //删除一份报告,根据报告id删除
    int deleteReport(int ReportId);

    //根据文件名进行一次模糊查询,Title就是文件名
    List<Report> queryReportByWord(String word);

    //根据队伍的id查询报告
    List<Report> queryReportsByTeamID(int TeamID);

    //根据报告id进行查询，主要用于下载时侯
    Report queryReportByID(int ReportID);

    //根据path和title查询是否有重复的，用于查重
    Report queryReportByTitleAndPath(Report report);
}
