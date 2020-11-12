<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%--用于在修改密码成功之后，改变地址到登录页面--%>
    <title>改变topAddress</title>
</head>
<body>
<span id="status">${status}</span>

<script>
    function changeadd(){
        var status = document.getElementById("status");
        if(status.innerText === '1'){//教师或学生
            top.location.replace("${pageContext.request.contextPath}/user/goToLogin");
        }
        else{//管理员
            top.location.replace("${pageContext.request.contextPath}/user/goToLogin_ad");
        }
    }

    window.onload = changeadd;
</script>
</body>
</html>
