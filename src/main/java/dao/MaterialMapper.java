package dao;

import org.apache.ibatis.annotations.Param;
import pojo.Material;

import java.util.List;

public interface MaterialMapper {
    //查询所有的资料
    List<Material> queryAllMaterials();

    //查询总的条数
    int queryCounts();
    
    //上传一份资料,记录下信息
    int UploadAMaterial(Material material);
    
    //删除一份资料,根据id删除
    int deleteMaterial(int id);
    
    //根据文件名进行一次模糊查询
    List<Material> queryMaterialByWord(String word);

    //根据路径和文件名查询
    Material queryMaterialByPathAndName(Material material);

    //修改一个记录，其实只需要修改时间
    int updateMaterial(Material material);

    //查询前十五条记录,后来改成了查询若干了，，
    List<Material> query15Materials(@Param("start") int start,@Param("len") int len);

    //根据id查询记录
    Material queryMaterialByID(int id);
}
