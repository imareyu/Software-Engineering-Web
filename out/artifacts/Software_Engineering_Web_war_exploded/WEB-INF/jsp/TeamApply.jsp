<%@ page import="pojo.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="SessionCheckStu.jsp"%><%--进行学生身份验证--%>
<html>
<head>
    <title>申请项目</title>
    <link href="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
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
                    <label>
                        <input type="text" name="ProjectName" class="form-control" required>
                    </label>
                </div>

                <div class="form-group">
                    <label >队长ID(你的ID)</label>
                    <label>
                        <input type="text" name="TeamleaderID" class="form-control" readonly>
                    </label>
                </div>

                <div class="form-group">
                    <label >队长姓名(你的名字)</label>
                    <label>
                        <input type="text" name="TeamleaderName" class="form-control" required>
                    </label>
                </div>

                <div class="form-group">
                    <label >队员1ID</label>
                    <label>
                        <input type="text" name="Teammate1ID" class="form-control">
                    </label>
                </div>

                <div class="form-group">
                    <label >队员1姓名</label>
                    <label>
                        <input type="text" name="Teammate1Name" class="form-control">
                    </label>
                </div>

                <div class="form-group">
                    <label >队员2ID</label>
                    <label>
                        <input type="text" name="Teammate2ID" class="form-control">
                    </label>
                </div>

                <div class="form-group">
                    <label >队员2姓名</label>
                    <label>
                        <input type="text" name="Teammate2Name" class="form-control">
                    </label>
                </div>
                <%--下边需要一个选择框--%>
                <div class="dropdown">
                    <button type="button" class="btn dropdown-toggle" id="dropdownMenu1" data-toggle="dropdown">
                        主题<span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
                        <li role="presentation" class="dropdown-header">下拉菜单标题</li>
                        <li role="presentation" >
                            <a role="menuitem" tabindex="-1" href="#">Java</a>
                        </li>
                        <li role="presentation">
                            <a role="menuitem" tabindex="-1" href="#">数据挖掘</a>
                        </li>
                        <li role="presentation">
                            <a role="menuitem" tabindex="-1" href="#">
                                数据通信/网络         </a>
                        </li>
                        <li role="presentation" class="divider"></li>
                        <li role="presentation" class="dropdown-header">下拉菜单标题</li>
                        <li role="presentation">
                            <a role="menuitem" tabindex="-1" href="#">分离的链接</a>
                        </li>
                    </ul>
                </div>

            </form>
        </div>
    </div>
</div>
<script src="https://cdn.bootcdn.net/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script src="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/3.3.6/js/bootstrap.min.js"></script>
</body>
</html>