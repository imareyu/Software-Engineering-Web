package pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int ID;//数据库中的id
    private String UserID;//用户登录使用的id
    private String UserPassword;//用户密码
    private String UserType;//用户类型
    private String UserName;//用户姓名
}
