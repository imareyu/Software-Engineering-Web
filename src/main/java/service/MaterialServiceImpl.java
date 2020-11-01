package service;

import dao.MaterialMapper;
import pojo.Material;

import java.util.List;

public class MaterialServiceImpl implements MaterialService {
    private MaterialMapper materialMapper;
    public void setMaterialMapper(MaterialMapper materialMapper){
        this.materialMapper = materialMapper;
    }
    public int uploadMaterial(Material material) {
        return materialMapper.UploadAMaterial(material);
    }

    //查询总的条数
    public int queryCounts(){
        return materialMapper.queryCounts();
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

    public Material queryMaterialByPathAndName(Material material) {
        return materialMapper.queryMaterialByPathAndName(material);
    }

    public int updateMaterial(Material material) {
        return materialMapper.updateMaterial(material);
    }

    public List<Material> query15Materials(int start, int len) {
        return materialMapper.query15Materials(start,len);
    }

    public Material queryMaterialByID(int id) {
        return materialMapper.queryMaterialByID(id);
    }

}
