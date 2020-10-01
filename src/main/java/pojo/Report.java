package pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Report {
    private int ReportID;
    private int TeamID;
    private Date PublishTime;
    private String Title;
    private String Path;
}
