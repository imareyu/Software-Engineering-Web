<%@ page import="pojo.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>教师查看回答问题页面</title>
    <link href="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    <small>查看|回答问题</small>
                </h1>
            </div>
        </div>
    </div>
    <div class="row clearfix">
        <div class="col-md-4 column">
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/questions/teaGoToQuesManage">查看问题</a>
        </div>
        <div class="col-md-4 column"></div>
        <div class="col-md-4 column">
            <span style="color: red;font-weight: bold">${error}</span>
        </div>
    </div>

    <%User user = (User) request.getSession().getAttribute("UserSession");%>
    <div class="row clearfix">
        <div class="col-md-4 column"></div>
        <div class="col-md-4 column">
            <div>问题编号：<span>${questions.questionID}</span></div>
        </div>
        <div class="row">
            <div class="col-md-4 column"></div>
            <div class="col-md-4 column">
                <div>提问学生id：<span>${questions.userID}</span></div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-4 column"></div>
            <div class="col-md-4 column">
                <div>提问时间：<span>${questions.publishTime}</span></div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-4 column"></div>
            <div class="col-md-4 column">
                <div>问题内容：<span>${questions.content}</span></div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-4 column"></div>
            <div class="col-md-4 column">
                <div>有无回答：<span>${questions.userID}</span></div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-4 column"></div>
            <div class="col-md-4 column">
                <form action="${pageContext.request.contextPath}/questions/submitAnswer" method="post">
                    <label>问题ID</label>
                    <input type="text" name="QuestionID" class="form-control" value="${questions.questionID}" readonly>
                    <textarea name="Answer" rows="10" cols="20" placeholder="在这里输入你的回答，限200字" required></textarea>
                    <button type="submit" onclick="return myconfirm();" value="提交回答"></button>
                </form>
            </div>
        </div>
    </div>
</div>

<script>
    function myconfirm() {
        return confirm("确定提交回答？");
    }
</script>
<script src="https://cdn.bootcdn.net/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script src="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/3.3.6/js/bootstrap.min.js"></script>


</body>
</html>
