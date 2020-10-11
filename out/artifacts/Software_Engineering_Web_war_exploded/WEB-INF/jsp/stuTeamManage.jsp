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
        <div class="row">
            <form class="form-horizontal" role="form" action="${pageContext.request.contextPath}/team/updateTeam" method="post">
                <div class="col-md-2 column"></div>
                <div class="col-md-5 column">
                    <div class="form-group">
                        <label >项目名称</label>
                        <label>
                            <input type="text" name="ProjectName" class="form-control" value="${teams.projectName}" required>
                        </label>
                    </div>

                    <div class="form-group">
                        <label >队长ID(你的ID)</label>
                        <label>
                            <input type="text" name="TeamleaderID" value="${teams.teamleaderID}" class="form-control" readonly>
                        </label>
                    </div>

                    <div class="form-group">
                        <label >队长姓名(你的名字)</label>
                        <label>
                            <input type="text" name="TeamleaderName" class="form-control" value="${teams.teamleaderName}" required>
                        </label>
                    </div>

                    <div class="form-group">
                        <label >队员1ID</label>
                        <label>
                            <input type="text" name="Teammate1ID" class="form-control" value="${teams.teammate1ID}">
                        </label>
                    </div>

                    <div class="form-group">
                        <label >队员1姓名</label>
                        <label>
                            <input type="text" name="Teammate1Name" class="form-control" value="${teams.teammate1Name}">
                        </label>
                    </div>

                    <div class="form-group">
                        <label >队员2ID</label>
                        <label>
                            <input type="text" name="Teammate2ID" class="form-control" value="${teams.teammate2ID}">
                        </label>
                    </div>

                    <div class="form-group">
                        <label >队员2姓名</label>
                        <label>
                            <input type="text" name="Teammate2Name" class="form-control" value="${teams.teammate2Name}">
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
                        <button type="submit" class="btn btn-default">保存修改</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<script>
    function setTeacher(){
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
        return true;
    }
    window.onload = setTeacher;
</script>
<script src="https://cdn.bootcdn.net/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script src="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/3.3.6/js/bootstrap.min.js"></script>
</body>
</html>
