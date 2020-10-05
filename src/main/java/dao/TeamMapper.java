package dao;

import pojo.Team;

import java.util.List;

public interface TeamMapper {
    //查询所有的队伍
    List<Team> queryAllMaterials();

    //添加一个队伍
    int addTeam(Team team);

    //删除一个队伍，根据TeamID,TeamID是由数据库自动递增分配的，
    //ProjectID现在的想法应该是101 I组这样的东西
    int deleteTeam(int id);

    //根据文件名进行一次模糊查询
    List<Team> queryMaterialByWord(String word);

    //根据路径和文件名查询
    Team queryMaterialByPathAndName(Team material);

    //修改一个记录，其实只需要修改时间
    int updateMaterial(Team material);

    //查询前十五条记录,后来改成了查询若干了，，
    List<Team> query15Materials();//(@Param("start") int start,@Param("len") int len);

    //根据id查询记录
    Team queryMaterialByID(int id);
}
