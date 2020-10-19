package pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Questions {
    private int QuestionID;
    private String UserID;
    private Timestamp PublishTime;
    private String Content;
    private String AnsState;
}
