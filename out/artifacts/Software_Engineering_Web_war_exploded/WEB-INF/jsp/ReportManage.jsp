<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%--此页面为学生的查看自己的团队报告的页面--%>
    <title>报告管理页面</title>
    <link href="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    <small>文件列表————显示团队报告信息</small>
                </h1>
            </div>
        </div>
    </div>
    <div class="row clearfix">
        <div class="col-md-4 column">
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/file/goToUploadReport" target="_blank">上传报告</a>
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/file/stuToReportManage">显示全部报告</a>
            <%--<input type="text" id="username" onblur="ajaxtest();" hidden>--%>
        </div>
        <div class="col-md-4 column">
            <span style="color: green;font-weight: bold">${downloadSuccess}</span>
            <span style="color: green;font-weight: bold">${deleteSuccess}</span>
        </div>
        <div class="col-md-4 column">
            <span style="color: red;font-weight: bold">${error}</span>
            <form action="${pageContext.request.contextPath}/file/queryReportByName" method="post">
                <input style="display: inline-block;width: 300px" type="text" name="word" class="form-control" placeholder="请输入要查询的报告名称关键词" required>
                <button type="submit" class="btn btn-default">查询</button>
            </form>
        </div>
    </div>

    <div class="row clearfix">
        <div class="col-md-12 column">
            <table class="table table-hover table-striped"><%--table-hover是隔行变色,table-striped表示--%>
                <thead>
                <tr>
                    <th>报告id</th>
                    <th>队伍ID</th>
                    <th>报告修改时间</th>
                    <th>标题</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="report" items="${reports}">
                    <tr>
                        <td>${report.reportID}</td>
                        <td>${report.teamID}</td>
                        <td>${report.publishTime}</td>
                        <td><span style="width: 200px;height:25px;line-height: 25px; white-space: nowrap;overflow: hidden;text-overflow: ellipsis;">${report.title}</span></td>
                        <td>
                            <a id="download1" target="_blank" href="${pageContext.request.contextPath}/file/downloadReport?id=${report.reportID}" onclick="return confirmDownload();">下载</a> <%--携带id进行跳转--%>
                            &nbsp;|
                            <a id="delete1" href="${pageContext.request.contextPath}/file/deleteAReport?id=${report.reportID}" onclick="return myconfirm();">删除</a>
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
        return confirm("确定下载？");
    }
    function myconfirm() {
        return confirm("确定删除文件？");
    }
</script>
<script src="https://cdn.bootcdn.net/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script src="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/3.3.6/js/bootstrap.min.js"></script>
</body>
</html>
