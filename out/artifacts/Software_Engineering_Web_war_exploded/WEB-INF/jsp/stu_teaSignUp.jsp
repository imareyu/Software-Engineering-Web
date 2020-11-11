<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生教师注册页面</title>
    <link href="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container">
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <div class="row clearfix">
        <div class="col-md-4 column">
        </div>
        <div class="col-md-5 column">
            <form class="form-horizontal" role="form" action="${pageContext.request.contextPath}/user/signup" method="post">
                <span style="color: red">${error}</span>
                <div class="form-group">
                    <label for="input1" class="col-sm-2 control-label">用户名</label>
                    <div class="col-sm-10">
                        <input style="width: 50%;" maxlength="20" name="userID" class="form-control" id="input1" type="text" required/>
                    </div>
                </div>

                <div class="form-group">
                    <label  class="col-sm-2 control-label">密码</label>
                    <input name="Password" id="newPassword1" style="width: 200px" minlength="6" maxlength="26" class="form-control" type="password" placeholder="请输入新密码" required onkeydown="return checkNumberOrLetter(event);" onchange="return checknewPassword();" autocomplete="new-password">
                </div>
                <div class="form-group">
                    <label  class="col-sm-2 control-label">密码</label>
                    <div>
                        <label><input id="newPassword2" style="width: 200px" minlength="6" maxlength="26" class="form-control" type="password" placeholder="请再次输入新密码" onkeydown="return checkNumberOrLetter(event);" onchange="return checknewPassword();" required></label>
                        <label style="color: red" id="passwordNotSame"></label>
                    </div>
                </div>

                <div class="form-group">
                    <label  class="col-sm-2 control-label">身份</label>
                    <label>
                        <select class="selectpicker" name="shenfen">
                            <option>teacher</option>
                            <option>student</option>
                        </select>
                    </label>
                </div>

                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default" onclick="return myconfirm();">注册</button>
                    </div>
                </div>
            </form>
        </div>
        <div class="col-md-3 column">
        </div>
    </div>
</div>

<script>
    function checknewPassword(){
        //确认两个新密码是否相等
        var newpassword1 = document.getElementById("newPassword1");
        var newpassword2 = document.getElementById("newPassword2");
        if(newpassword1.value.length>=6 && newpassword2.value.length >= 6 && newpassword1.value === newpassword2.value){//如果两个新密码相等，则提示置空
            // alert(newpassword1.value+ "=" +newpassword2.value);
            document.getElementById("passwordNotSame").innerText = "";
            return true;
        }
        else{//如果不相等，则提示不同
            document.getElementById("passwordNotSame").innerText = "新密码不一致或长度未达到6位";
            // alert(newpassword1.value +"!=" +newpassword2.value);
            return false;
        }
    }

    function myconfirm() {
        var UserID = document.getElementById("UserID");
        if(UserID.value.length < 4){
            //用户id最短为4
            alert("用户id最短为4");
            return false;
        }
        var newpassword1 = document.getElementById("newPassword1");
        var newpassword2 = document.getElementById("newPassword2");
        if(newpassword1.value.length < 6 || newpassword2.value.length < 6 ||newpassword1.value !== newpassword2.value){
            alert("新密码过短或不一致");
            return false;
        }
        return confirm("确认注册？");
    }
    function checkNumberOrLetter(e){
        //判断是否为数字或字母
        var keyCode = e.which;//获取按键码
        if(keyCode === 8)//为backspace
            return true;
        // var oldPass = document.getElementById("oldPassword");
        // alert(oldPass.value);
        var realKey = String.fromCharCode(keyCode);//小写字母会被转换为大写字母
        // alert(realKey);
        if((realKey>='0' && realKey<='9')|| (realKey >= 'A'&&realKey<='Z')){
            return true;
        }
        else{
            return false;
        }
    }
</script>

</body>
</html>
