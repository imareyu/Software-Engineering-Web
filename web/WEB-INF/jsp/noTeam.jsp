<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>无队伍页面(学生)</title>
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
        <a href="${pageContext.request.contextPath}/team/goToTeamApply" target="_blank">申请项目</a><%--开一个心得界面--%>
    </div>
</div>
</body>
</html>