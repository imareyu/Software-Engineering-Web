package service;

import dao.TeamMapper;
import pojo.Team;

import java.util.List;

public class TeamServiceImpl implements TeamService {
    private TeamMapper teamMapper;
    public void setTeamMapper(TeamMapper teamMapper){
        this.teamMapper = teamMapper;
    }

    public int addTeam(Team team) {
        return teamMapper.addTeam(team);
    }

    public int deleteTeam(int TeamID) {
        return teamMapper.deleteTeam(TeamID);
    }

    public int updateTeam(Team team) {
        return teamMapper.updateTeam(team);
    }

    public List<Team> queryAllTeams() {
        return teamMapper.queryAllTeams();
    }

    public Team queryTeamByMemberID(int id) {
        return teamMapper.queryTeamByMemberID(id);
    }

    public List<Team> queryTeamByMemberName(String word) {
        return teamMapper.queryTeamByMemberName(word);
    }

    public List<Team> queryTeamByProjectName(String word) {
        return teamMapper.queryTeamByProjectName(word);
    }

    public Team queryTeamByTeamID(int id) {
        return teamMapper.queryTeamByTeamID(id);
    }

    public List<Team> queryTeamNopass(int id) {
        return teamMapper.queryTeamNopass(id);
    }

    public List<Team> queryTeamPass(int id) {
        return teamMapper.queryTeamPass(id);
    }

    public List<Team> queryAnyTeams(int start, int len) {
        return teamMapper.queryAnyTeams(start,len);
    }

    public List<Team> queryTeamsByTeacherID(int id) {
        return teamMapper.queryTeamsByTeacherID(id);
    }
}
