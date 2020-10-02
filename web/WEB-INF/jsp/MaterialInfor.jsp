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
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/file/goToUploadMaterial">上传文件</a>
            <a class="btn btn-primary" href="${pageContext.request.contextPath}">显示全部书籍</a>
            <input type="text" id="username" onblur="ajaxtest();" hidden>
            <a class="btn btn-primary" href="${pageContext.request.contextPath}" type="file" >上传文件</a>
            <a class="btn btn-primary" href="${pageContext.request.contextPath}" type="file" >管理文件</a>
        </div>
    </div>
</div>
</body>
</html>
