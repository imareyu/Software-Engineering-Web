package pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Answer {
    private int AnswerID;
    private int QuestionID;
    private String Answer;
    private Timestamp AnsTime;
}
