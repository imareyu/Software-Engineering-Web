package dao;

import pojo.Material;

import java.util.List;

public interface MaterialMapper {
    //查询所有的资料
    List<Material> queryAllMaterials();
    
    //上传一份资料,记录下信息
    int UploadAMaterial(Material material);
    
    //删除一份资料,根据id删除
    int deleteMaterial(int id);
    
    //根据文件名进行一次模糊查询
    List<Material> queryMaterialByWord(String word);
}
