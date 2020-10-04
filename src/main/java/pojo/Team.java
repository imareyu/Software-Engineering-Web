package pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Team {
    private int TeamID;
    private int ProjectID;
    private String  ProjectName;
    private int TeamleaderID;
    private String TeamleaderName;
    private int Teammate1ID;
    private String Teammate1Name;
    private int Teammate2ID;
    private String Teammate2Name;
    private String State;//状态
    private int TeacherID;//指导老师的id
}
