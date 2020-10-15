<%@ page import="pojo.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>审批单个项目</title>
    <link href="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<%--此界面为教师审批某个项目的项目展示界面--%>
<%User user = (User) session.getAttribute("UserSession");%>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1><small>申请项目——填写项目信息</small></h1>
            </div>
        </div>
    </div>
    <div class="row clearfix">
        <%--<div class="col-md-12 column">--%>
        <form class="form-horizontal" role="form" action="${pageContext.request.contextPath}/team/passProject" method="post">
            <div class="col-md-4 column"></div>
            <div class="col-md-4 column">
                <div class="form-group">
                    <label >队伍id</label>
                    <label>
                        <input type="text" name="TeamID" value="${team.teamID}" class="form-control" readonly>
                    </label>
                </div>

                <div class="form-group">
                    <label >项目ID</label>
                    <label>
                        <input type="text" name="ProjectID" value="${team.projectID}" class="form-control" required><%--项目ID，需要老师填写--%>
                    </label>
                </div>

                <div class="form-group">
                    <label >项目名称</label>
                    <label>
                        <input type="text" name="ProjectName" value="${team.projectName}" class="form-control" readonly>
                    </label>
                </div>

                <div class="form-group">
                    <label >队长ID</label>
                    <label>
                        <input type="text" name="TeamleaderID" value="${team.teamleaderID}" class="form-control" readonly>
                    </label>
                </div>

                <div class="form-group">
                    <label >队长姓名</label>
                    <label>
                        <input type="text" name="TeamleaderName" value="${team.teamleaderName}" class="form-control" readonly>
                    </label>
                </div>

                <div class="form-group">
                    <label >队员1ID</label>
                    <label>
                        <input type="text" name="Teammate1ID" value="${team.teammate1ID}" class="form-control" readonly>
                    </label>
                </div>

                <div class="form-group">
                    <label >队员1姓名</label>
                    <label>
                        <input type="text" name="Teammate1Name" value="${team.teammate1Name}" class="form-control" readonly>
                    </label>
                </div>

                <div class="form-group">
                    <label >队员2ID</label>
                    <label>
                        <input type="text" name="Teammate2ID" value="${team.teammate2ID}" class="form-control" readonly>
                    </label>
                </div>

                <div class="form-group">
                    <label >队员2姓名</label>
                    <label>
                        <input type="text" name="Teammate2Name" value="${team.teammate2Name}" class="form-control" readonly>
                    </label>
                </div>

                <div class="form-group">
                    <label>项目状态</label>
                    <label><%--项目的状态--%>
                        <input type="text" name="State" value="nopass" class="form-control" readonly>
                    </label>
                </div>

                <div class="form-group">
                    <label>教师id</label>
                    <label><%--教师的userID--%>
                        <input type="text" name="TeacherID" value="<%=user.getUserID()%>" class="form-control" readonly>
                    </label>
                </div>

                <div class="form-group">
                    <button type="submit" class="btn btn-default" onclick="return myconfirm();">通过项目</button>
                </div>
            </div>
        </form>
    </div>
</div>
<script>
    function myconfirm() {
        return confirm("确定通过此项目？");
    }
</script>


<script src="https://cdn.bootcdn.net/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script src="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/3.3.6/js/bootstrap.min.js"></script>
</body>
</html>
