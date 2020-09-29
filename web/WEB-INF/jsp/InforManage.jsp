<%@ page import="pojo.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>基本信息管理</title>
    <link href="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h3>
                    <small>基本信息管理</small>
                </h3>
            </div>
        </div>
    </div>
    <%User user = (User) request.getSession().getAttribute("UserSession"); %>
    <div class="row clearfix">
        <div class="col-md-12 column">
            <form action="${pageContext.request.contextPath}" method="get">
                <div class="form-group">
                    <label>用户id</label>
                    <input style="width: 100px" class="form-control" type="text" readonly value="<%=user.getUserID()%>">
                </div>
                <div class="form-group">
                    <label>用户类型</label>
                    <input style="width: 100px" class="form-control" type="text" readonly value="<%=user.getUserType()%>">
                </div>
                <div class="form-group">
                    <input  type="button" disabled value="修改" title="用户无权限修改">
                </div>

            </form>
        </div>
    </div>
</div>
<script src="https://cdn.bootcdn.net/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script src="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/3.3.6/js/bootstrap.min.js"></script>
</body>
</html>
