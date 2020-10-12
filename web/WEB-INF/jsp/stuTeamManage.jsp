<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="pojo.User" %>
<%@ page import="pojo.Team" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生管理队伍页面</title>
    <link href="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-10 column">
            <div class="page-header">
                <h1><small>队伍管理</small></h1>
            </div>
        </div>

        <%--<%Team team = (Team);%>--%>
        <%User user = (User)session.getAttribute("UserSession");%>
        <div class="row">
            <form class="form-horizontal" role="form" action="${pageContext.request.contextPath}/team/updateTeam" method="post">
                <div class="col-md-2 column"></div>
                <div class="col-md-5 column">
                    <div class="form-group">
                        <label >项目名称</label>
                        <label>
                            <input type="text" id="ProjectName" name="ProjectName" class="form-control" value="${teams.projectName}" required>
                        </label>
                    </div>

                    <div class="form-group">
                        <label >队长ID</label>
                        <label>
                            <input type="text" id="TeamleaderID" name="TeamleaderID" value="${teams.teamleaderID}" class="form-control" readonly>
                        </label>
                    </div>

                    <div class="form-group">
                        <label >队长姓名</label>
                        <label>
                            <input type="text" id="TeamleaderName" name="TeamleaderName" class="form-control" value="${teams.teamleaderName}" required>
                        </label>
                    </div>

                    <div class="form-group">
                        <label >队员1ID</label>
                        <label>
                            <input type="text" id="Teammate1ID" name="Teammate1ID" class="form-control" value="${teams.teammate1ID}">
                        </label>
                    </div>

                    <div class="form-group">
                        <label >队员1姓名</label>
                        <label>
                            <input type="text" id="Teammate1Name" name="Teammate1Name" class="form-control" value="${teams.teammate1Name}">
                        </label>
                    </div>

                    <div class="form-group">
                        <label >队员2ID</label>
                        <label>
                            <input type="text" id="Teammate2ID" name="Teammate2ID" class="form-control" value="${teams.teammate2ID}">
                        </label>
                    </div>

                    <div class="form-group">
                        <label >队员2姓名</label>
                        <label>
                            <input type="text" id="Teammate2Name" name="Teammate2Name" class="form-control" value="${teams.teammate2Name}">
                        </label>
                    </div>
                    <%--下边需要一个选择框--%>
                    <div class="form-group">
                        <label >指导老师</label>
                        <label>
                            <select class="selectpicker" name="TeacherID" id="TeacherID">
                                <c:forEach var="teacher" items="${teachers}">
                                    <option>${teacher.userID} ${teacher.userName}</option>
                                </c:forEach>
                            </select>
                        </label>
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-default" onclick="myconfirm();">保存修改</button>
                    </div>
                </div>
            </form>
            <button type="button" class="btn tn-default"><a href="${pageContext.request.contextPath}/team/exitTeam?id=<%=user.getUserID()%>" id="exitTeam">退出队伍</a></button>
        </div>
    </div>
</div>
<script>
    function setStatus(){//根据用户初始化各种可编辑的属性，队员应该是只能修改自己的名字，队长可以管理全部
        var select1 = document.getElementById("TeacherID");
        for (var i = 0; i < select1.options.length; i++) {
            var innerText = select1[i].innerText;
            for(var j = 0;j < innerText.length;j++){
                if(innerText[j] === ' ')
                    break;
            }
            innerText =innerText.substring(0,j);
            // alert(innerText);
            if(innerText === "${teams.teacherID}"){
                select1.options[i].selected = true;
                break;
            }
        }
        var TeamleaderID = document.getElementById("TeamleaderID");
        if(TeamleaderID.value === "<%=user.getUserID()%>"){//为队长，设置退出队伍按钮为不可用
            var exitTeam = document.getElementById("exitTeam");
            exitTeam.disabled = true;//使其不可用
            exitTeam.removeAttribute("href");
        }else{//为队员，设置除了 自己姓名和 退出队伍按钮 外都为不可用
            var ProjectName = document.getElementById("ProjectName");
            ProjectName.readonly = true;//项目名只可读

            TeamleaderID.readonly = true;//队长id只可读
            var TeamleaderName = document.getElementById("TeamleaderName");
            TeamleaderName.readonly = true;//队长姓名只可读
            var Teammate1ID = document.getElementById("Teammate1ID");
            Teammate1ID.readonly = true;//一号队员id只可读
            var Teammate1Name = document.getElementById("Teammate1Name");
            Teammate1Name.readonly = true;//一号队员姓名只可读
            var Teammate2ID = document.getElementById("Teammate2ID");
            Teammate2ID.readonly = true;//二号队员id只可读
            var Teammate2Name = document.getElementById("Teammate2Name");
            Teammate2Name.readonly = true;//二号队员姓名只可读
            select1.disabled = true;//指导老师不可更改

            if(Teammate1ID.value === "<%=user.getUserID()%>"){//用户为一号队员
                Teammate1Name.readonly = false;//可以改名字
            }else{//为二号队员
                Teammate2Name.readonly = false;
            }

        }
        return true;
    }
    window.onload = setStatus;

    function myconfirm() {
        if(confirm("确认修改？"))
            return true;
        return false;
    }
</script>
<script src="https://cdn.bootcdn.net/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script src="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/3.3.6/js/bootstrap.min.js"></script>
</body>
</html>
