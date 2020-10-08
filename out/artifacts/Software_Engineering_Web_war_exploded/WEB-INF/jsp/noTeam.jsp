<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>无队伍页面(学生)</title>
    <link href="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .mya1{
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
    </style>
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-10 column">
            <div class="page-header">
                <h1><small>未参加队伍</small></h1>
            </div>
        </div>
    </div>
    <div class="row clearfix">
        <div class="col-md-10 column">
            <p>你尚未加入任何队伍！如果你为队长，请点击下边的按钮进入项目申报界面！</p>
            <p>如果为队员，请提醒队长尽快进行申请！</p>
            <p style="color: red">注意：项目申请前请保证所有的队员均已注册！如有成员未注册，则项目无法申请成功</p>
        </div>
    </div>
    <div class="row clearfix">
        <div class="col-md-3 column"></div>
        <div class="col-md-2 column">
            <a style="text-decoration: none;" class="mya1" href="${pageContext.request.contextPath}/team/goToTeamApply" target="_blank">申请项目</a><%--开一个新的界面--%>
        </div>
    </div>
</div>
</body>
</html>