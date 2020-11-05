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
        <div class="row">
            <div class="col-md-3 column"></div>
            <div class="col-md-6 column">
                <div><span style="font-size: 16px;color: black">问题编号：</span><span>${questions.questionID}</span></div>
            </div>
        </div>
        <div class="row">
                <div class="col-md-3 column"></div>
                <div class="col-md-6 column">
                    <div><span style="font-size: 16px;color: black">提问学生id：</span><span>${questions.userID}</span></div>
                </div>
        </div>
        <div class="row">
            <div class="col-md-3 column"></div>
            <div class="col-md-6 column">
                <div><span style="font-size: 16px;color: black">提问时间：</span><span>${questions.publishTime}</span></div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-3 column"></div>
            <div class="col-md-6 column">
                <div><span style="font-size: 16px;color: black">问题内容：</span><span>${questions.content}</span></div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-3 column"></div>
            <div class="col-md-6 column">
                <div><span style="font-size: 16px;color: black">有无回答：</span><span>${questions.ansState}</span></div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-3 column"></div>
            <div class="col-md-6 column">
                <div>
                    <table class="table table-hover table-striped"><%--table-hover是隔行变色,table-striped表示--%>
                        <thead>
                        <tr>
                            <th>回答编号</th>
                            <th>内容</th>
                            <th>时间</th>
                        </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="answer" items="${answers}">
                                <tr>
                                    <td>回答<span>${answer.answerID}</span>、</td>
                                    <td>${answer.answer}</td>
                                    <td>${answer.ansTime}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-3 column"></div>
            <div class="col-md-5 column">
                <form  class="form-horizontal" role="form"  action="${pageContext.request.contextPath}/questions/submitAnswer" method="post">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">问题ID</label>
                        <div class="col-sm-5">
                            <input type="text" name="QuestionID" class="form-control" value="${questions.questionID}" readonly>
                        </div>
                    </div>
                    <textarea name="Answer" rows="10" cols="60" placeholder="在这里输入你的回答，限200字" required></textarea>

                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-default" onclick="return myconfirm();">提交回答</button>
                        </div>
                    </div>
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
