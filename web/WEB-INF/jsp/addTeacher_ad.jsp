<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%--管理员  添加教师用户界面--%>
    <title>添加教师用户页面</title>
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
                    <small>添加新教师用户</small>
                </h3>
            </div>
        </div>
    </div>
    <div class="row clearfix">
        <div class="col-md-12 column">
            <form action="${pageContext.request.contextPath}/user/addTea_ad" method="post">
                <div class="form-group">
                    <span style="color: green">${mess}</span>
                    <span style="color: red">${error}</span>
                </div>
                <div class="form-group">
                    <label>用户id</label>
                    <input style="width: 200px" id="UserID" class="form-control" maxlength="20" minlength="4" name="UserID" type="text" required autocomplete="new-password" onkeydown="return checkNumberOrLetter(event);">
                </div>
                <div class="form-group">
                    <label>姓名</label>
                    <input style="width: 200px" id="UserName" class="form-control" maxlength="20" name="UserName" type="text" required>
                </div>
                <div class="form-group">
                    <label>输入密码</label>
                    <input name="Password" id="newPassword1" style="width: 200px" minlength="6" maxlength="26" class="form-control" type="password" placeholder="请输入新密码" required onkeydown="return checkNumberOrLetter(event);" onchange="return checknewPassword();" autocomplete="new-password">
                </div>
                <div class="form-group">
                    <label>再次输入密码</label>
                    <div>
                        <label><input id="newPassword2" style="width: 200px" minlength="6" maxlength="26" class="form-control" type="password" placeholder="请再次输入新密码" onkeydown="return checkNumberOrLetter(event);" onchange="return checknewPassword();" required></label>
                        <label style="color: red" id="passwordNotSame"></label>
                    </div>
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-default" onclick="return myconfirm();">添加用户</button>
                </div>
            </form>
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
        if(confirm("确认添加用户？")){
            newpassword1.value = md5(newpassword1.value);
            newpassword2.value = md5(newpassword2.value);
            return true;
        }
        return false;
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

<script src="https://cdn.bootcdn.net/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script src="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/3.3.6/js/bootstrap.min.js"></script>

</body>
</html>
