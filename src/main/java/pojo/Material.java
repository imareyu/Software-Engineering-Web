package pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Material {
    private int MaterialID;
    private String MaterialName;
    private Date UploadTime;
    private String Path;
}
