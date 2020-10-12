package pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    static private HashMap<String,String> allsession = new HashMap<String, String>();//存储每一个userid对应的sessionid,key为userid，value为session
    //使用静态变量，使得可以所有的对象都可以取到，避免在teamcontroller里无法取到
    private int ID;//数据库中的id
    private String UserID;//用户登录使用的id
    private String UserPassword;//用户密码
    private String UserType;//用户类型
    private String UserName;//用户姓名
}
