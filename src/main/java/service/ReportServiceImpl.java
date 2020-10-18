package service;

import dao.ReportMapper;
import pojo.Report;

import java.util.List;

public class ReportServiceImpl implements ReportService {

    private ReportMapper reportMapper;
    public void setReportMapper(ReportMapper reportMapper) {
        this.reportMapper = reportMapper;
    }

    public List<Report> queryAllReports() {
        return reportMapper.queryAllReports();
    }

    public int UploadAReport(Report report) {
        return reportMapper.UploadAReport(report);
    }

    public int deleteReport(int ReportId) {
        return reportMapper.deleteReport(ReportId);
    }

    public List<Report> queryReportByWord(String word) {
        return reportMapper.queryReportByWord(word);
    }

    public List<Report> queryReportsByTeamID(int TeamID) {
        return reportMapper.queryReportsByTeamID(TeamID);
    }

    public Report queryReportByID(int ReportID) {
        return reportMapper.queryReportByID(ReportID);
    }

    //根据path和title查询是否有重复的，用于查重
    public Report queryReportByTitleAndPath(Report report){
        return reportMapper.queryReportByTitleAndPath(report);
    }

    public int updateReport(Report report) {
        return reportMapper.updateReport(report);
    }

    public List<Report> queryReportByTeacherID(String TeacherID) {
        return reportMapper.queryReportByTeacherID(TeacherID);
    }
}
