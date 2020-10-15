<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>教师管理项目页面</title>
    <link href="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.4.1/jquery.js"></script>
</head>
<body>
<%--根据设想，教师应该只能修改projectID和State属性
最开始的状态下，projectID为null,所以需要教师指定--%>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h3>
                    <small style="color: black">教师管理页面——管理名下所有未通过的项目</small>
                </h3>
            </div>
        </div>
    </div>
    <div class="row clearfix">
        <div class="col-md-12 column">
            <table class="table table-hover table-striped"><%--table-hover是隔行变色,table-striped表示--%>
                <thead>
                <tr>
                    <th>队伍id</th><%--TeamID--%>
                    <th>项目id</th><%--ProjectID--%>
                    <th>项目名称</th>
                    <th>队长</th>
                    <th>队员1</th>
                    <th>队员2</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="team" items="${teams}">
                    <tr>
                        <td>${team.teamID}</td>
                        <td><label for="ProjectID"></label><input style="width: 150px" type="text" class="form-control" id="ProjectID" name="ProjectID"></td><%--最初为空--%>
                        <td>${team.projectName}</td>
                        <td>${team.teamleaderID} ${team.teamleaderName}</td>
                        <td>${team.teammate1ID} ${team.teammate1Name}</td>
                        <td>${team.teammate2ID} ${team.teammate2Name}</td>
                        <td>未审批</td>

                        <td>
                            <a href="#" onclick="return myconfirm(this);">通过项目</a> <%--携带信息进行跳转--%>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<script>
    function myconfirm(obj) {
        if(confirm("确定通过项目？") === false)
            return false;
        alert("确定通过");
        var tr = $(obj).parent.parent;
        var input1 = ($(tr).find("td").eq(1)).children("form-control");
        alert("input1"+input1);
        var ProjectID1 = input1.value;
        if(ProjectID1 === "") {
            alert("队名为空");
            return false;
        }
        alert("ProjectID:"+ProjectID1);
        var tdd = $(obj).parents("td").siblings("td");
        var TeamID = tdd[0].innerText;
        alert("TeamID:"+TeamID);
        return false;
        //确定要通过，使用ajax方式进行请求
        $.post({
            url:"${pageContext.request.contextPath}/team/passProject",
            data:{"TeamID":TeamID,ProjectID:ProjectID1},
            success:function (data) {
                alert(data);
            }
        })
    }
</script>
<script src="https://cdn.bootcdn.net/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script src="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/3.3.6/js/bootstrap.min.js"></script>
</body>
</html>
