package service;

import dao.MaterialMapper;
import pojo.Material;

import java.util.List;

public class MaterialServiceImpl implements MaterialService {
    private MaterialMapper materialMapper;
    public void setMaterialMapper(MaterialMapper materialMapper){
        this.materialMapper = materialMapper;
    }
    public int teacherUploadMaterial(Material material) {
        return materialMapper.UploadAMaterial(material);
    }

    public int deleteMaterial(int id) {
        return materialMapper.deleteMaterial(id);//这里只是把数据库记录删掉了，还需要在文件夹中删除
    }

    public List<Material> queryMaterialByWord(String word) {
        return materialMapper.queryMaterialByWord(word);
    }

    public List<Material> queryAllMaterials() {
        return materialMapper.queryAllMaterials();
    }


}
