<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生提问页面</title>
    <link href="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container">
    <div class="row clearfix">
        <div class="col-md-10 column">
            <div class="page-header">
                <h1>
                    <small>问题提交</small>
                </h1>
            </div>
        </div>
    </div>
    <div class="row clearfix">
        <span style="color: green">${success}</span>
    </div>
    <div class="row clearfix">
        <div class="col-md-10 column">
            <form action="${pageContext.request.contextPath}/questions/addAQuestion" method="post">
                <textarea style="display: inline-block;width: 300px" rows="10" cols="20" maxlength="200" name="content1" class="form-control" placeholder="请输入问题的某个关键词" required></textarea>
                <input type="submit" value="提交问题">
            </form>
        </div>
    </div>
</div>

<script src="https://cdn.bootcdn.net/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script src="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/3.3.6/js/bootstrap.min.js"></script>
</body>
</html>
