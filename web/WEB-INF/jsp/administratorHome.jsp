<%@ page import="pojo.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理员主页</title>
    <link href="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <style>
        #rightframe{
            width: 800px;
            height: 800px;
            border: 0;
        }
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

<div class="container">
    <div style="background: cornflowerblue;border-radius: 25px;" class="row clearfix">
        <div class="col-md-9 column">
            <h3>
                <small style="color: black">软件工程课设管理系统</small>
            </h3>
        </div>
        <div class="col-md-2 column">
            <h3>
                <%User user = (User) session.getAttribute("UserSession");%>
                <small>管理员<%=user.getUserID()%>，你好</small>
            </h3>
        </div>
        <div class="col-md-1 column">
            <a style="background: gainsboro;text-decoration: none" class="loginout" href="${pageContext.request.contextPath}/user/logout">注 销</a>
        </div>
    </div>
    <br/>
    <div class="row clearfix">
        <div class="col-md-2 column">
            <div class="panel-group" id="accordion">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion"
                               href="#collapseOne">
                                学生用户信息管理 </a>
                        </h4>
                    </div>
                    <div id="collapseOne" class="panel-collapse collapse in">
                        <div class="panel-body">
                            <a href="#" onclick="return goToSearchStu();">修改密码</a>
                        </div>
                        <div class="panel-body">
                            <a href="#" onclick="return gotoDeleteStu();">删除学生用户</a>
                        </div>
                        <div class="panel-body">
                            <a href="#" onclick="return gotoAddStu();">添加学生用户</a>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion"
                               href="#collapseTwo">
                                教师用户信息管理</a>
                        </h4>
                    </div>
                    <div id="collapseTwo" class="panel-collapse collapse">
                        <div class="panel-body">
                            <a href="#" onclick="return gotoSearchTea();">修改密码</a>
                        </div>
                        <div class="panel-body">
                            <a href="#" onclick="return gotoDeleteTea();">删除教师用户</a>
                        </div>
                        <div class="panel-body">
                            <a href="#" onclick="return gotoAddTea();">添加教师用户</a>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion" href="#collapseThree">
                                我的信息
                            </a>
                        </h4>
                    </div>
                    <div id="collapseThree" class="panel-collapse collapse">
                        <div class="panel-body">
                            <a href="#"  onclick="return modifyPassword();">修改密码</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-10 column">
            <iframe style="width: 1000px;" id="rightframe" src=""></iframe>
        </div>
    </div>
    <span id="alertmess" hidden>${alertmess}</span>
</div>

<script>
    function goToSearchStu() {//iframe替换为搜索学生用户的界面
        document.getElementById("rightframe").src="${pageContext.request.contextPath}/user/goToSearchStu"
    }

    function gotoDeleteStu() {//前往删除学生页面,首先要前往查询学生页面,,,,但是发现前边那个页面不能复用（或者说我不会复用
        document.getElementById("rightframe").src="${pageContext.request.contextPath}/user/goToSearchStu_forDeleteStu_ad"
    }

    function gotoAddStu() {//前往添加学生界面
        document.getElementById("rightframe").src="${pageContext.request.contextPath}/user/gotoAddStu"
    }

    function gotoSearchTea() {//前往搜索教师页面，修改密码流程
        document.getElementById("rightframe").src="${pageContext.request.contextPath}/user/gotoSearchTea"
    }

    function gotoDeleteTea() {//前往搜索教师页面，删除老师用户流程
        document.getElementById("rightframe").src="${pageContext.request.contextPath}/user/goToSearchTea_forDeleteTea_ad"
    }

    function gotoAddTea() {//前往添加教师用户页面
        document.getElementById("rightframe").src="${pageContext.request.contextPath}/user/gotoAddTea";
    }

    function modifyPassword() {
        document.getElementById("rightframe").src = "${pageContext.request.contextPath}/user/toModifyPassword";
    }

</script>

<script src="https://cdn.bootcdn.net/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script src="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/3.3.6/js/bootstrap.min.js"></script>
</body>
</html>
