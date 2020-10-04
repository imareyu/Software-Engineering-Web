<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
//    out.print("正在确定是否已登录");
    Object s1 = session.getAttribute("UserSession");
    if(s1 == null){//没有查到
        //out.print("没有查到session");
        response.sendRedirect("index.jsp");
        return;
    }else{
        //查到了
        User user = (User)s1;
        if(!"student".equals(user.getUserType()) && !"teamleader".equals(user.getUserType()) &&!"teammate".equals(user.getUserType()) ){
            //不为学生用户
            response.sendRedirect("index.jsp");
            return ;
        }
    }
%>