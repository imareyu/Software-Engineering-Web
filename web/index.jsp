<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<title>软件工程课程报告管理系统</title>
    <link href="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <style>
        a{
            text-decoration: none;
            color: black;
            font-size: 18px;
        }
        .login{
            width: 180px;
            height: 38px;
            margin: 50px auto;
            text-align: center;
            line-height: 38px;
            background: deepskyblue;
            border-radius: 8px;
            position: absolute;
            font-size: 20px;
            text-decoration: none;
        }
        #welcome{
            width: 800px;
            height: 60px;
            margin: 50px auto;
            text-align: center;
            line-height: 38px;
            background: gainsboro;
            border-radius: 30px;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="page-header">
            <%--<div class="col-md-12 column">--%>
                <h3 id="welcome"><span style="line-height: 60px">欢迎使用软件工程课程设计报告管理系统！</span></h3>
            <%--</div>--%>
        </div>
    </div>
    <div class="row clearfix">
            <div class="col-md-5 column"></div>
            <div class="col-md-2 column">
                <a style="text-decoration: none;" class="login" href="${pageContext.request.contextPath}/user/goToLogin_ad">管理员登录</a>
                <br/>
                <br/>
                <br/>
                <a style="text-decoration: none;" class="login" href="${pageContext.request.contextPath}/user/goToLogin_TeaOrStu">教师/学生登录</a>
                <br/>
                <br/>
                <br/>
                <a style="text-decoration: none;" class="login" href="${pageContext.request.contextPath}/user/goToSignUp">教师/学生注册</a>
                <br/>
                <br/>
                <br/>
                <a style="text-decoration: none;" class="login" href="${pageContext.request.contextPath}/file/goToMaterialInfor">下载文件</a>
                <br/>
                <br/>
                <br/>
            </div>
            <div class="col-md-5 column"></div>
    </div>
</div>
</body>
</html>
