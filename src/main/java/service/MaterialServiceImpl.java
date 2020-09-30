package service;

import dao.MaterialMapper;
import pojo.Material;

public class MaterialServiceImpl implements MaterialService {
    private MaterialMapper materialMapper;
    public void setMaterialMapper(MaterialMapper materialMapper){
        this.materialMapper = materialMapper;
    }
    public int teacherUploadMaterial(Material material) {
        return materialMapper.UploadAMaterial(material);
    }

}
