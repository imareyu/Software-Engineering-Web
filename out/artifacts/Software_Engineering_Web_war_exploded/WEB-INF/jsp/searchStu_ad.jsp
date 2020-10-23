<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理员查询学生用户界面</title>
    <link href="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container">
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <div class="row clearfix">
        <div class="col-md-4 column">
        </div>
        <div class="col-md-5 column">
            <form class="form-horizontal" role="form" action="${pageContext.request.contextPath}/user/queryStu_ad" method="post">
                <span style="color: red">${error}</span>
                <div class="form-group">
                    <label for="input1" style="width: 100px;" class="col-sm-3 control-label">用户名</label>
                    <div class="col-sm-9">
                        <input style="width: 60%;" maxlength="20" name="userID" class="form-control" id="input1" type="text" required/>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default">查询</button>
                    </div>
                </div>
            </form>
        </div>
        <div class="col-md-3 column">
        </div>
    </div>
</div>

</body>
</html>
