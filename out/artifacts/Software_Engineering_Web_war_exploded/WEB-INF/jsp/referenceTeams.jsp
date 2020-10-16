<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查看以通过项目</title>
    <link href="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.4.1/jquery.js"></script>
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h3>
                    <small style="color: black">教师管理页面——管理名下所有通过的项目</small>
                </h3>
            </div>
        </div>
    </div>
    <div class="row clearfix">
        <div class="col-md-12 column">
            <table class="table table-hover table-striped"><%--table-hover是隔行变色,table-striped表示--%>
                <thead>
                <tr>
                    <th>队伍id</th><%--TeamID--%>
                    <th>项目id</th>
                    <th>项目名称</th>
                    <th>队长</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="team" items="${teams}">
                    <tr>
                        <td>${team.teamID}</td>
                        <td>${team.projectID}</td>
                        <td>${team.projectName}</td>
                        <td>${team.teamleaderID} ${team.teamleaderName}</td>
                        <td>已审批通过</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/team/goToSeeAPassTeam?TeamID=${team.teamID}" onclick="return myconfirm();">查看信息</a> <%--携带信息进行跳转--%>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<script src="https://cdn.bootcdn.net/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script src="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/3.3.6/js/bootstrap.min.js"></script>
</body>
</html>
