<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理员查看修改学生用户信息页面</title>
    <link href="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdn.bootcdn.net/ajax/libs/blueimp-md5/2.18.0/js/md5.js"></script>
</head>
<body>

<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h3>
                    <small>基本信息管理</small>
                </h3>
            </div>
        </div>
    </div>
    <div class="row clearfix">
        <div class="col-md-12 column">
            <form action="${pageContext.request.contextPath}/user/modifyStuPassword_ad" method="get">
                <div class="form-group">
                    <label>id</label>
                    <input style="width: 200px" name="ID" class="form-control" type="text" readonly value="${user.ID}">
                </div>

                <div class="form-group">
                    <label>用户id</label>
                    <input style="width: 200px" name="UserID" class="form-control" type="text" readonly value="${user.userID}">
                </div>

                <div class="form-group">
                    <label>输入新密码</label>
                    <input style="width: 200px" id="newPassword1" maxlength="26" name="UserPassword" class="form-control" type="password" required placeholder="请输入新密码" onkeydown="return checkNumberOrLetter(event);"  onchange="return checknewPassword();">
                </div>

                <div class="form-group">
                    <label>确认新密码</label>
                    <input style="width: 200px" id="newPassword2" maxlength="26" name="UserPasswordAgain" class="form-control" type="password" required placeholder="请再次输入新密码" onkeydown="return checkNumberOrLetter(event);" onchange="return checknewPassword();">
                    <label style="color: red" id="passwordNotSame"></label>
                </div>

                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default" onclick="return myconfirm();">确定修改</button>
                    </div>
                </div>

            </form>
        </div>
    </div>
</div>

<script>
    function myconfirm() {
        //    1：判断两个新密码是否长度至少为6且相等,不相等，则提示
        //    2：判断新密码和旧密码是否相同，相同则提示密码相同，不能修改
        //    3：如果都满足，则提示确认，调用后端代码
        var newpassword1 = document.getElementById("newPassword1");
        var newpassword2 = document.getElementById("newPassword2");
        var oldPassword = "${user.userPassword}";//这个 user是后台查出来 的学生信息，不是管理员的用户信息
        if(newpassword1.value.length < 6 || newpassword2.value.length < 6 ||newpassword1.value !== newpassword2.value){
            alert("新密码过短或不一致");
            return false;
        }
        // alert("新密码"+newpassword1);
        // alert("旧密码"+oldPassword);
        if(md5(newpassword1.value) === oldPassword){
            //新旧密码相同
            alert("新旧密码相同，请使用新密码");
            return false;
        }
        // alert(userPassword);
        if(confirm("确认修改密码？")){
            newpassword1.value = md5(newpassword1.value);
            newpassword2.value = md5(newpassword2.value);
            return true;
        }
        return false;
    }
    
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

    function checkNumberOrLetter(e){
        //判断是否为数字
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
<script src="https://cdn.bootcdn.net/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script src="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/3.3.6/js/bootstrap.min.js"></script>



</body>
</html>
