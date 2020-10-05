package pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Team {
    private int TeamID;
    private String ProjectID;//比如101 I
    private String  ProjectName;//比如基于xxx的xx系统
    private int TeamleaderID;
    private String TeamleaderName;
    private int Teammate1ID;
    private String Teammate1Name;
    private int Teammate2ID;
    private String Teammate2Name;
    private String State;//状态pass,nopass
    private int TeacherID;//指导老师的Userid
}
