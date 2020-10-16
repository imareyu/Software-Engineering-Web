<%@ page import="pojo.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>资料查看</title>
    <link href="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    <small>文件列表————显示文件信息</small>
                </h1>
            </div>
        </div>
    </div>
    <div class="row clearfix">
        <div class="col-md-4 column">
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/file/goToUploadMaterial" target="_blank">上传文件</a>
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/file/goToMaterialInfor">显示前15条数据</a>
            <%--<input type="text" id="username" onblur="ajaxtest();" hidden>--%>
        </div>
        <div class="col-md-4 column">
            <span style="color: green;font-weight: bold">${downloadSuccess}</span>
            <span style="color: green;font-weight: bold">${deleteSuccess}</span>
        </div>
        <div class="col-md-4 column">
            <span style="color: red;font-weight: bold">${error}</span>
            <form action="${pageContext.request.contextPath}/file/queryMaterialByName" method="post">
                <input style="display: inline-block;width: 300px" type="text" name="queryBookName" class="form-control" placeholder="请输入要查询的资料名称" required>
                <input type="submit" value="查询">
            </form>
        </div>
    </div>

    <%Object userSession = request.getSession().getAttribute("UserSession");%>
    <div class="row clearfix">
        <div class="col-md-12 column">
            <table class="table table-hover table-striped"><%--table-hover是隔行变色,table-striped表示--%>
                <thead>
                <tr>
                    <th>资料编号</th>
                    <th>资料名称</th>
                    <th>资料修改时间</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="material" items="${materials}">
                    <tr>
                        <td>${material.materialID}</td>
                        <td>${material.materialName}</td>
                        <td>${material.uploadTime}</td>
                        <td>
                            <a id="download1" target="_blank" href="${pageContext.request.contextPath}/file/downloadMaterial?id=${material.materialID}" onclick="return confirmDownload();">下载</a> <%--携带id进行跳转--%>
                            &nbsp;|
                            <a id="delete1" href="${pageContext.request.contextPath}/file/deleteAMaterial?id=${material.materialID}" onclick="return myconfirm();">删除</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<script>
    function confirmDownload() {
        return confirm("确认下载？");
    }
    function myconfirm(){
        //先判断是否为老师，如果不是，直接false
        var user = "<%=userSession%>";
        // alert(user);
        <%--alert("<%=userSession%>");--%>
        if(user === "null"){
            alert("未登录，不具有删除权限！");
            return false;
        }
        else{
            return confirm("确认删除？");
        }
        // alert("不具有删除权限！");
        // return false;
    }
</script>
</body>
</html>
