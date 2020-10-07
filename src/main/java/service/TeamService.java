package service;

import org.apache.ibatis.annotations.Param;
import pojo.Team;

import java.util.List;

public interface TeamService {
    //添加一个队伍
    int addTeam(Team team);

    //删除一个队伍，根据TeamID,TeamID是由数据库自动递增分配的
    //ProjectID应该是101 I组这样的东西
    int deleteTeam(int TeamID);

    //修改一个记录
    int updateTeam(Team team);

    //查询所有的队伍
    List<Team> queryAllTeams();

    //根据成员的id进行查询
    Team queryTeamByMemberID(int id);

    //根据成员的姓名进行模糊查询，模糊！！！用like
    List<Team> queryTeamByMemberName(String word);

    //根据项目名进行一次模糊查询，模糊！！！
    List<Team> queryTeamByProjectName(String word);

    //根据队伍id查询
    Team queryMaterialByTeamID(int id);

    //查询某个老师手下所有的没有通过的队伍
    List<Team> queryTeamNopass(int id);

    //查询某个老师手下的所有的通过的队伍
    List<Team> queryTeamPass(int id);

    //查询若干记录
    List<Team> queryAnyTeams(int start,int len);

    //根据教师的id查询
    List<Team> queryTeamsByTeacherID(int id);
}
