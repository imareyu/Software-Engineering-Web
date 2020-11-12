<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理员登录</title>
    <link href="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdn.bootcdn.net/ajax/libs/blueimp-md5/2.18.0/js/md5.js"></script>
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
            <form class="form-horizontal" role="form" action="${pageContext.request.contextPath}/user/login_ad" method="post">
                <span style="color: red">${error}</span>
                <div class="form-group">
                    <label for="input1" class="col-sm-2 control-label">用户名</label>
                    <div class="col-sm-10">
                        <input style="width: 50%;" maxlength="20" name="userID" class="form-control" id="input1" type="text" required/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputPassword3" class="col-sm-2 control-label">密码</label>
                    <div class="col-sm-10">
                        <input style="width: 50%;" maxlength="26" name="userPassword" class="form-control" id="inputPassword3" type="password" required/>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <div class="checkbox">
                            <label><input type="checkbox" />记住密码</label>
                            <label><a href="${pageContext.request.contextPath}/user/forgetPassword">忘记密码</a></label>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default" onclick="return myconfirm();">登录</button>
                    </div>
                </div>
            </form>
        </div>
        <div class="col-md-3 column">
        </div>
    </div>
</div>

<script>
    function myconfirm() {
        var password = document.getElementById("inputPassword3");
        password.value = md5(password.value);
        return true;
    }
</script>

</body>
</html>
