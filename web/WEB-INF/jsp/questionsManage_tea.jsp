<%@ page import="pojo.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>教师查看问题页面</title>
    <link href="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    <small>问题查看——学生提出的问题（显示15条）</small>
                </h1>
            </div>
        </div>
    </div>
    <div class="row clearfix">
        <div class="col-md-4 column">
            <a class="btn btn-primary" href="${pageContext.request.contextPath}">显示我的问题</a>
        </div>
        <div class="col-md-4 column"></div>
        <div class="col-md-4 column">
            <span style="color: red;font-weight: bold">${error}</span>
            <form action="${pageContext.request.contextPath}/questions/queryQuesByWord" method="post">
                <input style="display: inline-block;width: 300px" type="text" name="word" class="form-control" placeholder="请输入问题的某个关键词" required>
                <input type="submit" value="查询">
            </form>
        </div>
    </div>

    <%User user = (User) request.getSession().getAttribute("UserSession");%>
    <div class="row clearfix">
        <div class="col-md-12 column">
            <table class="table table-hover table-striped"><%--table-hover是隔行变色,table-striped表示--%>
                <thead>
                <tr>
                    <th>问题编号</th>
                    <th>问题题要</th>
                    <th>资料修改时间</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="question" items="${questions}">
                    <tr>
                        <td>${question.questionID}</td>
                        <td style="display: block;width: 216px;word-break: keep-all;white-space: nowrap;overflow: hidden;text-overflow: ellipsis;" title="${question.content}">${question.content}</td>
                        <td>${question.publishTime}</td>
                        <td>
                            <c:if test="${question.ansState == 'yes'}">
                                已回答
                            </c:if>
                            <c:if test="${question.ansState == 'no'}">
                                未回答
                            </c:if>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/" target="_blank">前往查看|回答问题</a>
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
