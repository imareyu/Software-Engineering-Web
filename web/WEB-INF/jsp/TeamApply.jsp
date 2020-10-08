<%@ page import="pojo.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="SessionCheckStu.jsp"%><%--进行学生身份验证--%>
<html>
<head>
    <title>申请项目</title>
</head>
<body>
<%User user = (User) session.getAttribute("UserSession");%>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1><small>申请项目</small></h1>
            </div>
        </div>
    </div>
    <div class="row clearfix">
        <div class="col-md-12 column">
            <form action="${pageContext.request.contextPath}/team/TeamApply" method="post">
                <div class="form-group">
                    <label >项目名称</label>
                    <input type="text" name="ProjectName" class="form-control" required>
                </div>

                <div class="form-group">
                    <label >队长ID(你的ID)</label>
                    <input type="text" name="TeamleaderID" class="form-control" readonly>
                </div>

                <div class="form-group">
                    <label >队长姓名(你的名字)</label>
                    <input type="text" name="TeamleaderName" class="form-control" required>
                </div>

                <div class="form-group">
                    <label >队员1ID</label>
                    <input type="text" name="Teammate1ID" class="form-control">
                </div>

                <div class="form-group">
                    <label >队员1姓名</label>
                    <input type="text" name="Teammate1Name" class="form-control">
                </div>

                <div class="form-group">
                    <label >队员2ID</label>
                    <input type="text" name="Teammate2ID" class="form-control">
                </div>

                <div class="form-group">
                    <label >队员2姓名</label>
                    <input type="text" name="Teammate2Name" class="form-control">
                </div>


            </form>
        </div>
    </div>
</div>
</body>
</html>