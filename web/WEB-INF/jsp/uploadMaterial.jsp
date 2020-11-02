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

    </div>
    <div class="row clearfix">
        <div class="col-md-5"></div>
        <div class="col-md-2 column">
            <span>注意：.doc文件及包含doc文件的压缩包无法上传，请转为.docx之后再上传</span>
            <h4><span style="color: green;font-weight: bold">${uploadStatus}</span></h4>
            <form action="${pageContext.request.contextPath}/file/uploadMaterial" enctype="multipart/form-data" method="post">
                <input type="file" name="file" id="file1">
                <br/>
                <div  style="text-align: center;vertical-align: middle;">
                    <input type="submit" value="上传" onclick="return uploadConfirm();">
                </div>
            </form>
        </div>
        <div class="col-md-5"></div>
    </div>
</div>
<script>
    function uploadConfirm(){
        //首先判断文件是否为空
        var thefile = document.getElementById("file1");
        if(thefile.value.length === 0){
            alert("未选择文件！请选择！");
            return false;
        }
        if(confirm("确认上传"))
            return true;
        return false;
    }
</script>
</body>
</html>
