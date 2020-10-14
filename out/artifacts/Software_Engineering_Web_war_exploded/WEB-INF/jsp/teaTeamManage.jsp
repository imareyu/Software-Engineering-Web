<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>教师管理项目页面</title>
    <link href="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<%--根据设想，教师应该只能修改projectID和State属性
最开始的状态下，projectID为null,所以需要教师指定--%>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <h3>
                <small style="color: black">教师管理页面——管理名下所有未通过的项目</small>
            </h3>
        </div>
    </div>
    <div class="row clearfix">
        <div class="col-md-12 column">
            <table class="table table-hover table-striped"><%--table-hover是隔行变色,table-striped表示--%>
                <thead>
                <tr>
                    <th>队伍id</th><%--TeamID--%>
                    <th>项目id</th><%--ProjectID--%>
                    <th>项目名称</th>
                    <th>队长</th>
                    <th>队员1</th>
                    <th>队员2</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="team" items="${teams}">
                    <tr>
                        <td>${team.teamID}</td>
                        <td><input type="text" class="form-control" id="ProjectID" name="ProjectID"></td><%--最初为空--%>
                        <td>${team.projectName}</td>
                        <td>${team.teamleaderID} ${team.teamleaderName}</td>
                        <td>${team.teammate1ID} ${team.teammate1Name}</td>
                        <td>${team.teammate2ID} ${team.teammate2Name}</td>
                        <td>未审批</td>

                        <td>
                            <a href="${pageContext.request.contextPath}/team/passTeam?TeamID=${team.teamID}&ProjectID=1">通过项目</a> <%--携带信息进行跳转--%>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

</body>
</html>
