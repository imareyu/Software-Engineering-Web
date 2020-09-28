<%@ page import="pojo.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>个人主页</title>
    <link href="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .loginout {
            width: 50px;
            height: 30px;
            margin: 10px 0 0 20px;
            text-align: center;
            line-height: 30px;
            background: lightgray;
            border-radius: 2px;
            position: absolute;
            font-size: 20px;
            text-decoration: none;
        }
    </style>
</head>
<body>
<%--<div class="container">--%>
    <%--<div class="row clearfix">--%>
        <%--<div class="col-md-12 column">--%>
            <%--<div style="margin-top: 20px;" class="page-header">--%>
                <%--<h3 style="width: 800px">&lt;%&ndash;正常设为1000px&ndash;%&gt;--%>
                    <%--<small>软件工程课设管理系统</small>--%>
                    <%--<div class="pull-right">--%>
                        <%--<h3>--%>
                            <%--<small>--%>
                                <%--<%User user = (User) session.getAttribute("UserSession");%>--%>
                                <%--<%=user.getUserID()%>--%>
                                <%--<a style="text-decoration: none;" class="loginout">注销</a>--%>
                            <%--</small>--%>
                        <%--</h3>--%>
                    <%--</div>--%>
                <%--</h3>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</div>--%>

<div class="container">
    <div style="background: cornflowerblue;" class="row clearfix">
        <div class="col-md-10 column">
                <h3>
                    <small style="color: black">软件工程课设管理系统</small>
                </h3>
        </div>
        <div class="col-md-1 column">
            <h3>
                <%User user = (User) session.getAttribute("UserSession");%>
                <small><%=user.getUserID()%></small>
            </h3>
        </div>
        <div class="col-md-1 column">
            <a style="background: gainsboro;text-decoration: none" class="loginout" href="#">注 销</a>
        </div>
    </div>
    <div class="row clearfix">
        <div class="col-md-4 column">
            <div class="panel-group" id="panel-847596">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <a class="panel-title collapsed" data-toggle="collapse" data-parent="#panel-847596" href="#panel-element-312295">个人信息管理</a>
                    </div>
                    <div id="panel-element-312295" class="panel-collapse collapse">
                        <div class="panel-body">
                            更多管理
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <a class="panel-title collapsed" data-toggle="collapse" data-parent="#panel-847596" href="#panel-element-760259">Collapsible Group Item #2</a>
                    </div>
                    <div id="panel-element-760259" class="panel-collapse collapse">
                        <div class="panel-body">
                            Anim pariatur cliche...
                        </div>
                    </div>
                </div>
            </div>
        </div>
        </div>
    </div>
</div>
</body>
</html>
