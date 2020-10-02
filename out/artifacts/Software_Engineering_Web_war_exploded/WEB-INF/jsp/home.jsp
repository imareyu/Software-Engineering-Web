<%@ page import="pojo.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>个人主页</title>
    <link href="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <style>
    </style>
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
    <br/>
    <div class="row clearfix">
        <div class="col-md-3 column">
            <div class="panel-group" id="accordion">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion"
                               href="#collapseOne">
                                个人信息管理 </a>
                        </h4>
                    </div>
                    <div id="collapseOne" class="panel-collapse collapse in">
                        <div class="panel-body">
                            <a href="#" onclick="return basicInforManage();">基本信息管理</a>
                        </div>
                        <div class="panel-body">
                            <a href="#" onclick="return modifyPassword();">修改密码</a>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion"
                               href="#collapseTwo">
                                资料中心</a>
                        </h4>
                    </div>
                    <div id="collapseTwo" class="panel-collapse collapse">
                        <div class="panel-body">
                            <a href="${pageContext.request.contextPath}/file/goToMaterialInfor" target="_blank">公共资料</a>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion"
                               href="#collapseThree">
                                报告管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseThree" class="panel-collapse collapse">
                        <div class="panel-body">
                            <a href="#">团队报告</a><%--等到老师上传文件的功能实现之后再完成--%>
                        </div>
                    </div>
                </div>

                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion"
                               href="#collapseFour">
                                问答中心
                            </a>
                        </h4>
                    </div>
                    <div id="collapseFour" class="panel-collapse collapse">
                        <div class="panel-body">
                            <a href="#">我要提问</a>
                        </div>
                        <div class="panel-body">
                            <a href="#">我的问题</a>
                        </div>
                    </div>
                </div>

                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion"
                               href="#collapseFive">
                                项目管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseFive" class="panel-collapse collapse">
                        <div class="panel-body">
                            <a href="#">我的项目</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-9 column">
            <iframe id="rightframe" src="${pageContext.request.contextPath}/index.jsp"></iframe>
        </div>
    </div>
</div>

<script>
    function basicInforManage() {
        document.getElementById("rightframe").src="${pageContext.request.contextPath}/user/Basic_Infor_Manage";
    }
    function modifyPassword() {
        document.getElementById("rightframe").src = "${pageContext.request.contextPath}/user/toModifyPassword";
        // if(top.location !== self.location)
        //     top.location = "../index.jsp";
    }
</script>
<script src="https://cdn.bootcdn.net/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script src="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/3.3.6/js/bootstrap.min.js"></script>
</body>
</html>