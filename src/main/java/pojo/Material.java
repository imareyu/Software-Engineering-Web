package pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Material {
    private int MaterialID;
    private String MaterialName;
    private Timestamp UploadTime;
    private String Path;
}
