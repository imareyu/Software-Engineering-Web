<%@ page import="pojo.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>教师主页</title>
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
        <div class="col-md-9 column">
            <h3>
                <small style="color: black">软件工程课设管理系统</small>
            </h3>
        </div>
        <div class="col-md-2 column">
            <h3>
                <%User user = (User) session.getAttribute("UserSession");%>
                <small>教师 <%=user.getUserName()%></small>
            </h3>
        </div>
        <div class="col-md-1 column">
            <a style="background: gainsboro;text-decoration: none" class="loginout" href="#">退 出</a>
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
                            <a href="${pageContext.request.contextPath}/file/goToUploadMaterial" target="_blank">上传资料</a>
                        </div>
                        <div class="panel-body">
                            <a href="${pageContext.request.contextPath}/file/goToMaterialInfor" target="_blank">资料查看</a>
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
                            <a href="#">查看报告</a><%--查看各个项目的报告--%>
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
                            <a href="#">问题列表</a><%--跳转到一个新页面,展示全部问题--%>
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
                            <a href="#">学生项目</a><%--跳转到新页面--%>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-10 column">
            <iframe style="width: 1000px;" id="rightframe" src="${pageContext.request.contextPath}/file/goToMaterialInfor"></iframe>
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