package pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Report {
    private int ReportID;
    private int TeamID;
    private Timestamp PublishTime;
    private String Title;
    private String Path;
}
