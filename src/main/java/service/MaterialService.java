package service;

import pojo.Material;

import java.util.List;

public interface MaterialService {
    //老师上传参考资料
    int teacherUploadMaterial(Material material);

    //老师删除一个参考资料（根据id
    int deleteMaterial(int id);

    //根据一个关键词查询资料
    List<Material> queryMaterialByWord(String word);

    //查询所有资料
    List<Material> queryAllMaterials();
}
