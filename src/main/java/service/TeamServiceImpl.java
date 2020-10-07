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
        return 0;
    }

    public int deleteTeam(int TeamID) {
        return 0;
    }

    public int updateTeam(Team team) {
        return 0;
    }

    public List<Team> queryAllTeams() {
        return null;
    }

    public Team queryTeamByMemberID(int id) {
        return null;
    }

    public List<Team> queryTeamByMemberName(String word) {
        return null;
    }

    public List<Team> queryTeamByProjectName(String word) {
        return null;
    }

    public Team queryMaterialByTeamID(int id) {
        return null;
    }

    public List<Team> queryTeamNopass(int id) {
        return null;
    }

    public List<Team> queryTeamPass(int id) {
        return null;
    }

    public List<Team> queryAnyMaterials(int start, int len) {
        return null;
    }

    public List<Team> queryTeamsByTeacherID(int id) {
        return null;
    }
}
