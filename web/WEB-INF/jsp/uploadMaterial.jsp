<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>上传资料</title>
    <link href="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    <small>上传文件————参考资料</small>
                </h1>
            </div>
        </div>
    </div>
    <div class="row clearfix">
        <div class="col-md-5"></div>
        <div class="col-md-2 column">
            <form action="${pageContext.request.contextPath}/file/uploadMaterial" enctype="multipart/form-data" method="post">
                <input type="file" name="file">
                <input type="submit" value="上传" onclick="return uploadConfirm();">
            </form>
        </div>
        <div class="col-md-5"></div>
    </div>
</div>
<script>
    function uploadConfirm(){
        if(confirm("确认上传"))
            return true;
        return false;
    }
</script>
</body>
</html>
