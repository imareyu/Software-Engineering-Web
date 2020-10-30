<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%--删除学生用户，管理员使用--%>
    <title>删除学生用户</title>
    <link href="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h3>
                    <small>用户基本信息</small>
                </h3>
            </div>
        </div>
    </div>
    <div class="row clearfix">
        <div class="col-md-12 column">
            <form action="${pageContext.request.contextPath}/user/deleteStu" method="post">
                <div class="form-group">
                    <label>id</label>
                    <input style="width: 200px" name="ID" class="form-control" type="text" readonly value="${user.ID}">
                </div>

                <div class="form-group">
                    <label>用户id</label>
                    <input style="width: 200px" name="UserID" class="form-control" type="text" readonly value="${user.userID}">
                </div>

                <div class="form-group">
                    <label>提示信息:</label>
                    <label style="color: red">注意：删除学生用户请先确认此学生<label style="font-weight: 700">没有参加或组建队伍</label>，否则无法删除</label>
                </div>

                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default" onclick="return myconfirm();">确认删除</button>
                    </div>
                </div>

            </form>
        </div>
    </div>
</div>

<script>
    function myconfirm() {
        return confirm("确认删除用户？");
    }
</script>

<script src="https://cdn.bootcdn.net/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script src="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/3.3.6/js/bootstrap.min.js"></script>
</body>
</html>
