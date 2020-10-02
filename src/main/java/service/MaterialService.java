package service;

import pojo.Material;

import java.util.List;

public interface MaterialService {
    //老师上传参考资料
    int uploadMaterial(Material material);

    //老师删除一个参考资料（根据id
    int deleteMaterial(int id);

    //根据一个关键词查询资料
    List<Material> queryMaterialByWord(String word);

    //查询所有资料
    List<Material> queryAllMaterials();

    //根据路径和文件名查询
    Material queryMaterialByPathAndName(Material material);

    //修改记录的时间
    int updateMaterial(Material material);

    List<Material> query15Materials(int start,int len);
}
