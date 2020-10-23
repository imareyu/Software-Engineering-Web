<%@ page import="pojo.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改用户密码</title>
    <link href="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h3>
                    <small>修 改 密 码</small>
                </h3>
            </div>
        </div>
    </div>
    <%User user = (User) request.getSession().getAttribute("UserSession"); %>
    <div class="row clearfix">
        <div class="col-md-12 column">
            <form action="${pageContext.request.contextPath}/user/modifyPassword" method="get">
                <div class="form-group">
                    <label>用户id</label>
                    <input style="width: 200px" class="form-control" type="text" readonly value="<%=user.getUserID()%>">
                </div>
                <div class="form-group">
                    <label>输入原密码</label>
                    <input name="oldPassword" id="oldPassword" style="width: 200px" minlength="6" maxlength="26" class="form-control" type="password" placeholder="请输入原密码" required onkeydown="return checkNumberOrLetter(event);">
                </div>
                <div class="form-group">
                    <label>输入新密码</label>
                    <input name="newPassword" id="newPassword1" style="width: 200px" minlength="6" maxlength="26" class="form-control" type="password" placeholder="请输入新密码" required onkeydown="return checkNumberOrLetter(event);">
                </div>
                <div class="form-group">
                    <label>确认新密码</label>
                    <div>
                        <label><input id="newPassword2" style="width: 200px" minlength="6" maxlength="26" class="form-control" type="password" placeholder="请再次输入新密码" onkeydown="return checkNumberOrLetter(event);" onchange="return checknewPassword();" required></label><%--文本改变事件，当文本改变时，对比两个新的密码是否相等--%>
                        <label style="color: red" id="passwordNotSame"></label>
                    </div>
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-default" onclick="return checkPassword();">修改密码</button>
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
    function checkPassword() {
    //    1：判断两个新密码是否长度至少为6且相等,不相等，则提示
    //    2：判断新密码和旧密码是否相同，相同则提示密码相同，不能修改
    //    3：如果都满足，则提示确认，调用后端代码
        var newpassword1 = document.getElementById("newPassword1");
        var newpassword2 = document.getElementById("newPassword2");
        var oldPassword = document.getElementById("oldPassword");
        if(newpassword1.value.length < 6 || newpassword2.value.length < 6 ||newpassword1.value !== newpassword2.value){
            alert("新密码过短或不一致");
            return false;
        }
        if(newpassword1.value === oldPassword.value){
            //新旧密码相同
            alert("新旧密码相同，请使用新密码");
            return false;
        }
        var userPassword = "<%=user.getUserPassword()%>";
        // alert(userPassword);
        if(userPassword !== oldPassword.value) {
            alert("原密码错误");
            return false;//输入的旧密码错误
        }
        if(!confirm("确认修改密码？")){
            return false;//不修改
        }
        //必定可以修改成功，修改地址
        top.location.replace("${pageContext.request.contextPath}/user/goToLogin");
        return true;//修改密码，在这之前要先确保能够跳出iframe
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
